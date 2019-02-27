package org.calminfotech.user.controllers;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.forms.RoleForm;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.InputTypeList;
import org.calminfotech.utils.UserActionsDao;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/roles")
public class RolesController {

	@Autowired
	private InputTypeList inputTypeList;

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UserActionsDao userActionsDao;

	@Layout(value = "layouts/datatable")
	@RequestMapping(method = RequestMethod.GET)
	public String indexAction(Model model) {

		List<Role> roles = roleBo.fetchAllRoleByOrganisation(userIdentity
				.getOrganisation());
		model.addAttribute("roles", roles);

		return "system/umgt/roles/index";

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAction(Model model) {
		model.addAttribute("roleForm", new RoleForm());
		return "system/umgt/roles/add";
	}

	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public String entryAction(
			@Valid @ModelAttribute("roleForm") RoleForm roleForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			return "system/umgt/roles/add";
		}
		// Set and save role-setup
		Role role = new Role();
		role.setRoleName(roleForm.getRoleName());
		role.setRoleDescription(roleForm.getRoleDescription());
		role.setOrganisation(userIdentity.getOrganisation().getOrgCoy());
		role.setCreatedBy(userIdentity.getUsername());
		// check if it's admin
		// if(roleForm.getAdmin() == 1){
		// role.setAdmin(true);
		// role.setDeletable(false);
		// }
		role.setCreatedDate(new Date(System.currentTimeMillis()));

		role.setDeletable(false);
		role.setAdmin(false);
		roleBo.save(role);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Role Setup Successfully");

		return "redirect:/system/umgt/roles/view/" + role.getRoleId();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Role role = roleBo.getRoleById(id);
		if (role == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Resource  not available.");
			return "redirect:/system/umgt/roles";
		}
		model.addAttribute("role", role);
		return "system/umgt/roles/view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		Role role = this.roleBo.getRoleById(id);
		RoleForm rForm = new RoleForm();
		// set role value to beam
		rForm.setRoleId(role.getRoleId());
		rForm.setRoleName(role.getRoleName());
		rForm.setRoleDescription(role.getRoleDescription());
		if (role.isAdmin()) {
			rForm.setAdmin(1);
		} else {
			rForm.setAdmin(0);
		}
		auditor.before(request, "Role Form", rForm);
		model.addAttribute("roleForm", rForm);

		return "system/umgt/roles/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("roleForm") RoleForm roleForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			String message = "Error! Resource  not available. Try again";
			alert.setAlert(redirectAttributes, Alert.WARNING, message);
			return "system/umgt/roles/add";
		}
		Role role = roleBo.getRoleById(roleForm.getRoleId());
		role.setRoleName(roleForm.getRoleName());
		role.setRoleDescription(roleForm.getRoleDescription());

		// if(roleForm.getAdmin() == 1){
		// role.setAdmin(true);
		// }

		roleBo.update(role);
		auditor.after(request, "Role-Setup Form", roleForm,
				userIdentity.getUsername(), roleForm.getRoleId());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Role-Setup form Updated");

		return "redirect:/system/umgt/roles/view/" + role.getRoleId();
	}

	// Role Question Delete
	@RequestMapping(value = "/confirmdelete/{id}", method = RequestMethod.GET)
	public String confirmDeleteAction(@PathVariable("id") Integer id,
			ModelMap model) {
		Role role = roleBo.getRoleById(id);
		model.addAttribute("role", role);
		return "system/umgt/roles/delete";
	}

	// Delete Role
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteAction(@ModelAttribute("role") Role role,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		Role r = roleBo.getRoleById(role.getRoleId());

		// roleBo.delete(r);

		r.setModifiedBy(userIdentity.getUsername());
		r.setModifiedDate(new GregorianCalendar().getTime());
		r.setDeleted(true);
		roleBo.update(r);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item  deleted");

		alert.setAlert(redirectAttributes, "success", r.getRoleName()
				+ " was successfully deleted.!!!");
		return "redirect:/system/umgt/roles";
	}

}
