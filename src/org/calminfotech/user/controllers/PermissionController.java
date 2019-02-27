package org.calminfotech.user.controllers;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletResponse;

import org.calminfotech.system.forms.GetRoleAssignmentProcForm;
import org.calminfotech.system.forms.GetUserAssignmentProcForm;
import org.calminfotech.user.boInterface.GetRoleAssignmentProcBo;
import org.calminfotech.user.boInterface.GetUserAssignmentProcBo;
import org.calminfotech.user.boInterface.PermissionBo;
import org.calminfotech.user.boInterface.RoleAssgnmentBo;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserAssgnmentBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.RoleAssignment;
import org.calminfotech.user.models.RoleAssignment_log;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.UserAssignment_log;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/permission")
public class PermissionController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private GetRoleAssignmentProcBo roleAssignProcBo;

	@Autowired
	private GetUserAssignmentProcBo userAssignProcBo;

	@Autowired
	private RoleAssgnmentBo roleAssgned;

	@Autowired
	private UserAssgnmentBo userAssigned;// delete

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private PermissionBo permBo;

	@RequestMapping(value = { "/role/index" }, method = RequestMethod.GET)
	public String indexRole(Model model) {
		int roleId = 0;
		model.addAttribute(
				"dRoleAssign",
				this.roleAssignProcBo.fetchRolePermission(roleId, userIdentity
						.getOrganisation().getOrgCoy().getId()));
		model.addAttribute("rForm", new GetRoleAssignmentProcForm());
		model.addAttribute("roles", roleBo
				.fetchAllRoleByOrganisation(userIdentity.getOrganisation()));
		return "system/umgt/permission/role/index";
	}

	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public String entryAction(
			@ModelAttribute("rForm") GetRoleAssignmentProcForm rForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			return "/";
		}
		if (rForm.getSaveButton() == null) {
			return "redirect:/system/umgt/permission/role/request/"
					+ rForm.getpRole();
		}
		// delete all initial checked value
		this.roleAssgned.deleteAllCheckedValues(rForm.getpRole().intValue());
		// Save
		// fetch role by Id
		Role role = roleBo.getRoleById(rForm.getpRole());
		if (role == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please select a role ");
			return "redirect:/system/umgt/permission/role/request/"
					+ rForm.getpRole();
		}

		RoleAssignment roleAssign = new RoleAssignment();
		RoleAssignment_log roleAssign_log = new RoleAssignment_log();

		String[] checkboxes = rForm.getPermissionCheckboxVals();

		// ArrayList<String> d = rForm.getPermissionCheckboxVals();
		System.out.print("***");
		System.out.print("***");

		if (checkboxes != null) {
			// iterate array list
			for (String checkbox : checkboxes) {
				roleAssign.setRoleId(role.getRoleId());
				// roleAssign.setPermissionId(Integer.parseInt(checkbox));
				roleAssign.setPermissionCode(checkbox);

				roleAssign.setOrganisation(userIdentity.getOrganisation()
						.getOrgCoy());

				roleAssign.setCreatedBy(userIdentity.getUsername());
				roleAssign.setCreatedDate(new GregorianCalendar().getTime());

				roleAssign_log.setRoleId(role.getRoleId());
				// roleAssign.setPermissionId(Integer.parseInt(checkbox));
				roleAssign_log.setPermissionCode(checkbox);

				roleAssign_log.setCreatedBy(userIdentity.getUsername());
				roleAssign_log
						.setCreatedDate(new GregorianCalendar().getTime());
				roleAssign_log.setOrganisation(userIdentity.getOrganisation()
						.getOrgCoy());

				roleAssgned.save(roleAssign, roleAssign_log);

			}

		}
		// redirect
		model.addAttribute("dRoleAssign", this.roleAssignProcBo
				.fetchRolePermission(rForm.getpRole(), userIdentity
						.getOrganisation().getId()));
		model.addAttribute("rForm", new GetRoleAssignmentProcForm());
		model.addAttribute("roles", roleBo
				.fetchAllRoleByOrganisation(userIdentity.getOrganisation()));

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Role Permission Saved!!");
		return "redirect:/system/umgt/permission/role/request/"
				+ rForm.getpRole();
	}

	@RequestMapping(value = "/role/request/{roleId}", method = RequestMethod.GET)
	public String request(@PathVariable("roleId") Integer Id, Model model,
			HttpServletResponse response) {
		GetRoleAssignmentProcForm rForm = new GetRoleAssignmentProcForm();
		rForm.setpRole(Id);
		model.addAttribute(
				"dRoleAssign",
				this.roleAssignProcBo.fetchRolePermission(Id, userIdentity
						.getOrganisation().getOrgCoy().getId()));
		model.addAttribute("rForm", rForm);
		model.addAttribute("roles", roleBo
				.fetchAllRoleByOrganisation(userIdentity.getOrganisation()));
		return "system/umgt/permission/role/index";
	}

	// users start from here
	@RequestMapping(value = { "/user/index" }, method = RequestMethod.GET)
	public String indexUser(Model model, RedirectAttributes redirectAttributes) {
		if (true) {
			int userId = 0;
			model.addAttribute("dUserAssign", this.userAssignProcBo
					.fetchUserPermission(userId, userIdentity.getOrganisation()
							.getOrgCoy().getId()));
			model.addAttribute("uForm", new GetUserAssignmentProcForm());
			model.addAttribute("users", this.userBo.fetchAll());
			return "system/umgt/permission/user/index";
		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String entryUserAction(
			@ModelAttribute("uForm") GetUserAssignmentProcForm uForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (true) {

			if (result.hasErrors()) {
				return "";
			}
			if (uForm.getSaveButton() == null) {
				return "redirect:/system/umgt/permission/user/request/"
						+ uForm.getpUser();
			}
			System.out.print("useruserid1 ");

			System.out.print("useruserid " + uForm.getpUser());
			System.out.print("useruserid2 ");
			// delete all initial checked value
			this.userAssigned.deleteAllUserCheckedValues(uForm.getpUser()
					.intValue());
			// Save
			// fetch role by Id
			User user = this.userBo.getUserById(uForm.getpUser());
			if (user == null) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Please select a user ");
				return "redirect:/system/umgt/permission/user/request/"
						+ uForm.getpUser();
			}
			/*
			 * if
			 * (user.getUsername().toString().equals(userIdentity.getUsername(
			 * ).toString()))
			 * 
			 * { alert.setAlert(redirectAttributes, Alert.SUCCESS,
			 * "You cannot Edit Your own Info!");
			 * 
			 * return "system/umgt/permission/user/index";
			 * 
			 * }
			 */

			UserAssignment userAssign = new UserAssignment();
			UserAssignment_log userAssign_log = new UserAssignment_log();

			String[] checkboxes = uForm.getPermissionCheckboxVals();
			// iterate array list
			if (checkboxes != null) {
				for (String checkbox : checkboxes) {
					userAssign.setUserId(user.getUserId());
					// userAssign.setPermissionId(Integer.parseInt(checkbox));
					userAssign.setPermissionCode(checkbox);

					userAssign.setOrganisation(user.getOrganisation()
							.getOrgCoy());

					userAssign.setCreatedBy(userIdentity.getUsername());
					userAssign
							.setCreatedDate(new GregorianCalendar().getTime());

					userAssign_log.setUserId(user.getUserId());
					// roleAssign.setPermissionId(Integer.parseInt(checkbox));
					userAssign_log.setPermissionCode(checkbox);

					userAssign_log.setCreatedBy(userIdentity.getUsername());
					userAssign_log.setCreatedDate(new GregorianCalendar()
							.getTime());
					userAssign_log.setOrganisation(userIdentity
							.getOrganisation().getOrgCoy());
					userAssigned.save(userAssign, userAssign_log);

				}
			}
			// redirect
			model.addAttribute("dUserAssign", this.userAssignProcBo
					.fetchUserPermission(uForm.getpUser(), userIdentity
							.getOrganisation().getId()));
			model.addAttribute("uForm", new GetUserAssignmentProcForm());
			model.addAttribute("users", userBo.fetchAll());
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! User Permission Saved!!");
			return "redirect:/system/umgt/permission/user/request/"
					+ uForm.getpUser();
		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/user/request/{userId}", method = RequestMethod.GET)
	public String requestUser(@PathVariable("userId") Integer Id, Model model,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {
		if (true) {
			User user = this.userBo.getUserById(Id);

			GetUserAssignmentProcForm uForm = new GetUserAssignmentProcForm();
			uForm.setpUser(Id);
			model.addAttribute(
					"dUserAssign",
					this.userAssignProcBo.fetchUserPermission(Id, user
							.getOrganisation().getOrgCoy().getId()));
			model.addAttribute("uForm", uForm);
			model.addAttribute("users", userBo.fetchAll());
			return "system/umgt/permission/user/index";
		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}
}
