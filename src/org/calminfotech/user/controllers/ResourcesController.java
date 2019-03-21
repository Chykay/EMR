package org.calminfotech.user.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.forms.ResourceActionForm;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.models.Group;
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
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/resources")
public class ResourcesController {

	@Autowired
	private RequestMappingHandlerMapping mappingHandler;

	@Autowired
	private Alert alert;

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GroupBo groupbo;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String indexAction(Model model) {

		model.addAttribute("resources", this.resourceBo.fetchAllByOrganisation());
		return "system/umgt/resources/index";
	}

	@RequestMapping(value = "/reindex", method = RequestMethod.GET)
	public String confirmIndexAction() {
		return null;
	}

	@RequestMapping(value = "/reindex", method = RequestMethod.POST)
	public String reIndexAction(RedirectAttributes redirectAttributes) {

		if (this.resourceBo.fetchAll().size() > 0) {
			// Truncate the table
			this.resourceBo.clearResources();
		}

		// Rebuild the url resources
		this.resourceBo.reBuildResources();

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Resources reindexed");
		return "redirect:/system/umgt/resources";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model) {

		// For a resource, list all the groups or roles,
		// Weave the current permissions into the object and list it.

		Resource resource = this.resourceBo.getResourceById(id);
		model.addAttribute("resource", resource);
		System.out.println("Resource Permission: "
				+ resource.getGroups().size());

		// Create a temporal class to weave the group and permission status
		// together
		class GroupPermission {
			private Group group;
			private boolean access = false;

			public Group getGroup() {
				return group;
			}

			public void setGroup(Group group) {
				this.group = group;
			}

			public boolean isAccess() {
				return access;
			}

			public void setAccess(boolean access) {
				this.access = access;
			}
		}

		List<Group> groups = this.groupbo.fetchAllByOrganisation();
		List<GroupPermission> gPermissions = new ArrayList();
		for (Group group : groups) {
			GroupPermission gp = new GroupPermission();
			gp.setGroup(group);

			// Check if resource has group included in it
			if (resource.getGroups().contains(group)) {
				gp.setAccess(true);
			}

			// Add to list
			gPermissions.add(gp);
		}
		model.addAttribute("gPermission", gPermissions);

		return "system/umgt/resources/view";
	}

	@RequestMapping(value = "/deny")
	public String denyAction(
			@Valid @ModelAttribute("resourceActionForm") ResourceActionForm resourceActionForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid request");
			return "redirect:/system/umgt/resources";
		}

		Group group = this.resourceBo.denyAccess(resourceActionForm);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				group.getGroupName()
						+ " group no longer has permission to this resource");
		
		// Get the userIdentity Group and repopulate the userIdentity
		group = this.groupbo.getGroupById(this.userIdentity.getGroup().getId());
		//this.userIdentity.setLinks(group.getResources());
		return "redirect:/system/umgt/resources/view/"
				+ resourceActionForm.getResourceId();
	}

	@RequestMapping(value = "/allow")
	public String allowAction(
			@Valid @ModelAttribute("resourceActionForm") ResourceActionForm resourceActionForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid request");
			return "redirect:/system/umgt/resources";
		}

		Group group = this.resourceBo.allowAccess(resourceActionForm);
		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS,
						group.getGroupName()
								+ " group has permission to this resource");
		
		// Get the userIdentity Group and repopulate the userIdentity
		group = this.groupbo.getGroupById(this.userIdentity.getGroup().getId());
		//this.userIdentity.setLinks(group.getResources());

		return "redirect:/system/umgt/resources/view/"
				+ resourceActionForm.getResourceId();
	}

}
