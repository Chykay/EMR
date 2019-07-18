package org.calminfotech.hmo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hmo.forms.HmoForm;
import org.calminfotech.hmo.forms.HmoPackageForm;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BankList;
//import org.calminfotech.utils.HmoTypesList;
import org.calminfotech.utils.HmostatusList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Hmostatus;
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
@RequestMapping(value = "/hmo")
public class HmoController {

	@Autowired
	private Alert alert;

	@Autowired
	private HmoPackageBo hmoPackageBo;

	// @Autowired
	// private ItemServiceGroupBo itemServiceGroupBo;

	@Autowired
	private HmoPackageItemBo hmoItemBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private BankList bankList;

	@Autowired
	private HmostatusList hmostatusList;

	/*@Autowired
	private HmoTypesList hmotypeBo;
*/
	@Autowired
	private Auditor auditor;

	@Autowired
	private BillSchemeBo billSchemeBo;

	@Autowired
	private Authorize authorize;

	@RequestMapping(value = { "", "/index" })
	@Layout(value = "layouts/datatable")
	public String indexAction(RedirectAttributes redirectAttributes, Model model) {
		if (authorize.isAllowed("HMO000")) {
			List<Hmo> list = hmoBo.fetchAll(userIdentity.getOrganisation());

			model.addAttribute("hmos", list);
			return "/hmo/index";
		}

		else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to List HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/add")
	public String addAction1(RedirectAttributes redirectAttributes, Model model) {
		if (authorize.isAllowed("HMO001")) {
			model.addAttribute("bank", this.bankList.fetchAll());
			model.addAttribute("hmostatus", this.hmostatusList.fetchAll());

			model.addAttribute("billscheme", this.billSchemeBo
					.fetchAllByOrganisation(userIdentity.getOrganisation()));

			model.addAttribute("globalhmolist", this.hmoBo.fetchAllGlobal());

			//model.addAttribute("hmotype", hmotypeBo.fetchAll());
			HmoForm hmoForm = new HmoForm();
			model.addAttribute("hmoForm", hmoForm);

			return "hmo/add";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Add HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction1(
			@Valid @ModelAttribute("hmoForm") HmoForm hmoForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (authorize.isAllowed("HMO001")) {
			if (result.hasErrors()) {
				model.addAttribute("hmo",
						this.hmoBo.fetchAll(userIdentity.getOrganisation()));
				return "hmo/index";
			}

			this.hmoBo.save(hmoForm);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Hmo saved");
			return "redirect:/hmo/index";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Add HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/form_wizard_layout")
	public String viewActionAll1(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HMO002")) {
			// Hmo ehmo = this.ehmoBo.getHmoById(id);
			Hmo hmo = hmoBo.getHmoById(id);
			if (null == hmo) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "redirect:/hmo/index";
			}
			model.addAttribute("hmo", hmo);
			model.addAttribute("billscheme", this.billSchemeBo
					.fetchAllByOrganisation(userIdentity.getOrganisation()));
			// HmoPackage
			HmoPackageForm hmoPackageForm = new HmoPackageForm();
			hmoPackageForm.setHmo_id(hmo.getId());
			List<Hmostatus> hmostatus = hmostatusList.fetchAll();
			model.addAttribute("hmostatus", hmostatus);
			model.addAttribute("hmopackForm", hmoPackageForm);

			// History
			// PatientHistoryForm histForm = new PatientHistoryForm();
			// histForm.setPatientId(patient.getPatientId());
			// List<Historytype> historytype = historytypeBo.fetchAll();
			// model.addAttribute("historytypelist",historytype);
			// model.addAttribute("histForm", histForm);

			return "hmo/view";

		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to View HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction1(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (authorize.isAllowed("HMO003")) {
			Hmo hmo = this.hmoBo.getHmoById(id);
			if (null == hmo) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "redirect:/hmo/index";
			}

			HmoForm hmoForm = new HmoForm();
			// hmoForm.setId(hmo.getId());

			if (hmo.getHmocode() != null) {
				hmoForm.setGlobalhmocode(hmo.getHmocode().getHmocode());
			}

			hmoForm.setName(hmo.getName());
			hmoForm.setAddress(hmo.getAddress());
			hmoForm.setPhone(hmo.getPhone());
			// hmoForm.setPostalNumber(hmo.getPostalNumber());
			hmoForm.setEmail(hmo.getEmail());
			hmoForm.setAdmin_name(hmo.getAdminName());
			hmoForm.setEmail(hmo.getEmail());
			hmoForm.setAccountno(hmo.getAccoutno());
			if (hmo.getBank() != null) {
				hmoForm.setBank_id(hmo.getBank().getBank_id());
			}

			/*
			 * if (hmo.getBillScheme() != null) {
			 * hmoForm.setBillingscheme_id(hmo.getBillScheme().getId()); }
			 */
			hmoForm.setStatus_id(hmo.getHmostatus().getHmostatus_id());
			hmoForm.setHmotype_id(hmo.getHmoType().getId());

			// hmoForm.setPhone(hmo.getPhone());

			model.addAttribute("bank", this.bankList.fetchAll());
			model.addAttribute("hmostatus", this.hmostatusList.fetchAll());
			model.addAttribute("globalhmolist", this.hmoBo.fetchAllGlobal());
			model.addAttribute("billscheme", this.billSchemeBo
					.fetchAllByOrganisation(userIdentity.getOrganisation()));
			//model.addAttribute("hmotype", hmotypeBo.fetchAll());

			model.addAttribute("hmoForm", hmoForm);
			// model.addAttribute("hmo", hmo);
			this.auditor.before(request, "HMOForm", hmoForm);
			return "hmo/edit";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Edit HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction1(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("hmoForm") HmoForm hmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (authorize.isAllowed("HMO003")) {
			if (result.hasErrors()) {
				return "hmo/index";
			}

			hmoBo.update(hmoForm);
			this.auditor.after(request, "HMOForm", hmoForm,
					this.userIdentity.getUsername(), id);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! HMO details updated");
			// return "redirect:/admin/hmos/hmoView/" + ehmoForm.getId();
			// return "redirect:/hmo/view/" + id;
			return "redirect:/hmo/index";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Edit HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction1(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HMO004")) {
			Hmo hmo = this.hmoBo.getHmoById(id);
			if (null == hmo) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "redirect:/admin/hmos";
			}
			HmoForm hmoForm = new HmoForm();
			hmoForm.setId(hmo.getId());
			model.addAttribute("hForm", hmoForm);
			model.addAttribute("hmo", hmo);

			this.auditor.before(request, "HMOForm", hmoForm);
			return "admin/hmos/delete";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Delete HMO");

			return "redirect:/";
		}

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction1(
			@ModelAttribute("hForm") HmoForm hmoForm,
			RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HMO004")) {

			Hmo hmo = this.hmoBo.getHmoById(hmoForm.getId());
			if (null == hmo) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "redirect:/admin/hmos";
			}

			this.hmoBo.delete(hmo);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Hmo Deleted");
			return "redirect:/admin/hmos";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Delete HMO");

			return "redirect:/";
		}

	}

}
