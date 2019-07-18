package org.calminfotech.inventory.web;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.exceptions.UnableToGenerateVendorCodeException;
import org.calminfotech.inventory.forms.VendorForm;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory/vendor")
public class VendorController extends AbstractBaseController {

	@Autowired
	private VendorManagerInterface vendorManager;


	@Autowired
	private Alert alert;

	@Autowired
	private Authorize authorize;
	
	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/" })
	public String indexAction(ModelMap model,RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVVED000"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		return displayVendorsListPage(model);
	}

	private String displayVendorsListPage(ModelMap model) {
		// TODO Auto-generated method stub

		
		List<Vendor> vendor = null;
		try {
			vendor = this.vendorManager.getVendorsList();
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		model.addAttribute("vendorList", vendor);

		return "/inventory/vendor/index";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(RedirectAttributes redirectAttributes,
			ModelMap model) {

		if (!authorize.isAllowed("INVVED001"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		int id = 0;
		VendorForm vendorForm = this.getVendorForm(id);

		try {
			this.loadVendorForm(vendorForm, model);
			return this.displayVendorFormPage(vendorForm, model);
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER, e.getExceptionMsg());
			return "redirect:/inventory/vendor";

		}

	}

	@RequestMapping(value = "/edit/{vendorId}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String findPet(@PathVariable int vendorId,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVVED003"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		VendorForm vendorForm = this.getVendorForm(vendorId);

		try {
			this.loadVendorForm(vendorForm, model);
			// model.addAttribute("pageEdit", true);
			return this.displayVendorFormPage(vendorForm, model);
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + vendorId + ")");
			return "redirect:/inventory/vendor";

		}

	}

	private VendorForm getVendorForm(int id) {

		VendorForm vendorForm = new VendorForm();
		if (id != 0) {
				vendorForm.setId(id);	
		}
		return vendorForm;
	}

	private void loadVendorForm(VendorForm vendorForm, ModelMap model)
			throws RecordNotFoundException {

		// if vendor id not empty then we are in edit mode so we populate form
		if (vendorForm.getId() != 0 && !model.containsKey("vendorForm")) {
			// get vendor details and populate form
			Vendor vendor = this.vendorManager.getVendorDetailsById(vendorForm
					.getId());
			vendorForm.setContactAddress(vendor.getContactAddress());
			vendorForm.setEmail(vendor.getEmail());
			vendorForm.setFax(vendor.getFax());
			vendorForm.setTelephoneNumber(vendor.getTelephoneNumber());
			vendorForm.setUrl(vendor.getUrl());
			vendorForm.setVendorName(vendor.getVendorName());
			vendorForm.setVendorId(vendor.getVendorId());
		}

	}

	private String displayVendorFormPage(VendorForm vendorForm, ModelMap model) {

		model.addAttribute("vendorForm", vendorForm);
		// model.addAttribute("page", page);
		return "/inventory/vendor/add";
	}

	@RequestMapping(value = "/view/{vendorId}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String viewVendor(@PathVariable int vendorId,RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVVED002"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		try {

		  return this.displayVendorDetailsPage(vendorId, model);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + vendorId + ")");
			return "redirect:/inventory/vendor";
	
		}


	}
	
	
	private String displayVendorDetailsPage(int id, ModelMap model)
			throws RecordNotFoundException {
		Vendor vendor = this.vendorManager.getVendorDetailsById(id);
		model.addAttribute("vendor", vendor);
		model.addAttribute("pageView", true);
		return displayVendorsListPage(model);

	}

	@RequestMapping(value = "/delete/{vendorId}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String deleteVendor(@PathVariable int vendorId, ModelMap model, RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVVED004"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}


		try {

			Vendor vendor = this.vendorManager.getVendorDetailsById(vendorId);
			model.addAttribute("vendor", vendor);
			model.addAttribute("pageDelete", true);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER,"Error! Invalid resource (Resource id:" + vendorId + ")", model);
		}
		return displayVendorsListPage(model);

	}

	@RequestMapping(value = "/delete/{vendorId}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@RequestParam(value = "vendorId", required = false) String vendorId,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVVED004"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		Alert alert = (Alert) model.get("alert");

		try {
			Vendor vendor = this.vendorManager.getVendorDetailsById(Integer
					.parseInt(vendorId));
			this.vendorManager.delete(vendor);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Vendor profile deleted successfully");
			return "redirect:/inventory/vendor/";
		} catch (NumberFormatException e) {
		} catch (RecordNotFoundException e) {
		}
		alert.setAlert(redirectAttributes, Alert.DANGER,
				"Error! Invalid resource (Resource id:" + vendorId + ")");
		return "redirect:/inventory/vendor/";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("vendorForm") VendorForm vendorForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVVED001"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		Alert alert = (Alert) model.get("alert");

		if (!result.hasErrors()) {

			try {
				Vendor vendor = this.vendorManager.save(vendorForm);
				logger.info(vendor.getVendorId());
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! New Vendor Succesfully Added! Vendor id:  "
								+ vendor.getVendorId());
				return "redirect:/inventory/vendor/";
			} catch (UnableToGenerateVendorCodeException e) {
				this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
			}
		}

		try {
			this.loadVendorForm(vendorForm, model);
			return this.displayVendorFormPage(vendorForm, model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.DANGER, e.getExceptionMsg());
			return "redirect:/inventory/vendor";

		}
	}

	@RequestMapping(value = "/edit/{vendorId}", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String editVendor(
			@Valid @ModelAttribute("vendorForm") VendorForm vendorForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVVED003"))
		{
			
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");
			
			return "redirect:/";
		}

		Alert alert = (Alert) model.get("alert");

		if (!result.hasErrors()) {

			Vendor vendor = this.vendorManager.update(vendorForm);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					" Success! Vendor Profile Succesfully Updated!");
			return "redirect:/inventory/vendor/";

		}
		try {
			this.loadVendorForm(vendorForm, model);
			return this.displayVendorFormPage(vendorForm, model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(
					redirectAttributes,
					Alert.DANGER,
					"Error! Invalid resource (Resource id:"
							+ vendorForm.getId() + ")");
			return "redirect:/inventory/vendor";
		}

	}

}
