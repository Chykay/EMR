package org.calminfotech.system.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.forms.GroupActionsForm;
import org.calminfotech.system.forms.GroupForm;
import org.calminfotech.system.forms.GroupFormFieldForm;
import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.boInterface.GroupFormFieldBo;
import org.calminfotech.user.models.Group;
import org.calminfotech.user.models.GroupFormField;
import org.calminfotech.user.models.UserAction;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.InputTypeList;
import org.calminfotech.utils.UserActionsDao;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/groups")
public class GroupsController {

	@Autowired
	private InputTypeList inputTypeList;

	@Autowired
	private GroupBo groupBo;

	@Autowired
	private GroupFormFieldBo groupFormFieldBo;

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

		List<Group> groups = groupBo.fetchAllByOrganisation();
		model.addAttribute("groups", groups);

		return "system/umgt/groups/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAction(Model model) {

		model.addAttribute("groupForm", new GroupForm());

		return "system/umgt/groups/add";
	}

	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public String entryAction(
			@Valid @ModelAttribute("groupForm") GroupForm groupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			return "system/umgt/groups/add";
		}

		Group group = new Group();
		group.setGroupName(groupForm.getGroupName());
		group.setGroupDescription(groupForm.getGroupDescription());
		/*group.setOrganisation(userIdentity.getOrganisation());*/
		group.setCreatedBy(userIdentity.getUsername());
		if (groupForm.getAdmin() == 1)
			group.setAdmin(true);

		groupBo.save(group);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Group Added");

		return "redirect:/system/umgt/groups/view/" + group.getId();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Group group = groupBo.getGroupById(id);
		if (null == group) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Resource  not available.");
			return "redirect:/system/umgt/groups";
		}

		model.addAttribute("actions", userActionsDao.fetchAll());
		GroupActionsForm actionsForm = new GroupActionsForm();

		// Populate the form
		Integer[] arrActions = new Integer[group.getUserActions().size()];
		int i = 0;
		for (UserAction userAction : group.getUserActions()) {
			arrActions[i] = userAction.getId();
			i++;
		}

		// Send all the input types to the front
		model.addAttribute("inputTypes", this.inputTypeList.fetchAll());

		actionsForm.setActions(arrActions);

		model.addAttribute("actionsForm", actionsForm);
		model.addAttribute("group", group);

		return "system/umgt/groups/view";
	}

	@ResponseBody
	@RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
	public String groupProcessAction(
			@Valid @ModelAttribute("actionsForm") GroupActionsForm actionsForm,
			BindingResult result, @PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Group group = groupBo.getGroupById(id);
		List<UserAction> userActionList = userActionsDao.fetchAll();

		if (result.hasErrors()) {
			model.addAttribute("group", group);
			model.addAttribute("actions", userActionList);
			return "system/umgt/groups/view";
		}

		group.getUserActions().clear();

		for (int i = 0; i < actionsForm.getActions().length; i++) {
			for (UserAction userAction : userActionList) {
				if (userAction.getId() == actionsForm.getActions()[i]) {
					group.getUserActions().add(userAction); 
				}
			}
		}

		groupBo.update(group);

		String string = "";
		for (UserAction userAction : group.getUserActions()) {
			string += "<tr>" + "<td>" + userAction.getActionName() + "</td>"
					+ "<td>" + userAction.getActionDesc() + "</td>" + "</tr>";
		}

		return string;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request) {

		Group group = groupBo.getGroupById(id);
		GroupForm gForm = new GroupForm();

		gForm.setId(group.getId());
		gForm.setGroupName(group.getGroupName());
		gForm.setGroupDescription(group.getGroupDescription());
		if (group.isAdmin())
			gForm.setAdmin(1);
		else
			gForm.setAdmin(0);

		model.addAttribute("groupForm", gForm);

		auditor.before(request, "Group Form", gForm);

		return "system/umgt/groups/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("groupForm") GroupForm groupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/umgt/groups/add";
		}

		Group group = groupBo.getGroupById(groupForm.getId());
		group.setGroupName(groupForm.getGroupName());
		group.setGroupDescription(groupForm.getGroupDescription());
		if (groupForm.getAdmin() == 1)
			group.setAdmin(true);

		groupBo.update(group);

		auditor.after(request, "Group Form", groupForm,
				userIdentity.getUsername(),groupForm.getId());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Group Updated");

		return "redirect:/system/umgt/groups/view/" + group.getId();
	}

	@RequestMapping(value = "/delete")
	public String confirmDeleteAction() {
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteAction() {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/groupfields/{groupId}/{fieldId}", method = RequestMethod.GET)
	public String groupfieldsAction(@PathVariable("groupId") Integer groupId,
			@PathVariable("fieldId") Integer fieldId) {
		String response = null;

		GroupFormField field = this.groupFormFieldBo.getFieldById(fieldId);
		System.out.println(field.getOptions().size());

		// If null
		if (null != field) {
			if (field.getGroup().getId() == groupId) {
				response = "{\"status\": " + true + ", \"payload\": {";

				// append the label
				response += "\"label\" : \"" + field.getLabel() + "\",";

				// append the inputType
				response += "\"inputType\" : \"" + field.getInputType().getId()
						+ "\",";

				// Get the multiOptions for the field if select, radio or
				// checkbox
				response += "\"multiOptions\" : []";

				response += "}" + "}";
			} else {
				response = "{\"status\":  " + false + ", \"payload\": " + null
						+ "}";
			}
		} else {
			response = "{\"status\":  " + false + ", \"payload\": " + null
					+ "}";
		}

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/groupfields", method = RequestMethod.POST)
	public String saveGroupFieldAction(
			@Valid @ModelAttribute("groupFormFieldForm") GroupFormFieldForm fieldForm,
			BindingResult result) {
		String response = "";

		if (result.hasErrors()) {
			// Send Errors to the front view!
			response = "{\"status\": " + false + ", \"errors\": \"<ul>"
					+ "<li>I am an error amongst others</li>" + "</ul>\"}";
		} else {
			// Send the processed value to the front
			response = "{\"status\": " + true
					+ ", \"payload\": \"Hello World\"}";
		}

		return response;
	}
}
