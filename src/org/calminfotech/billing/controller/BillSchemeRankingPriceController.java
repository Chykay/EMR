package org.calminfotech.billing.controller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemBo;
import org.calminfotech.billing.boInterface.BillSchemeItemPriceBo;
import org.calminfotech.billing.boInterface.BillSchemeMeasurePriceBo;
import org.calminfotech.billing.forms.BillSchemeForm;
import org.calminfotech.billing.forms.BillSchemeRankingPriceForm;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.billing.models.BillSchemeRankingPrice;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BillschemestatusList;
import org.calminfotech.utils.annotations.Layout;
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
@RequestMapping(value = "/billrankingprice")
public class BillSchemeRankingPriceController {

	@Autowired
	private BillSchemeBo billschemeBo;
	@Autowired
	private BillSchemeItemBo billschemeItemBo;
	@Autowired
	private BillSchemeItemPriceBo billschemeItemPriceBo;
	@Autowired
	private GlobalItemUnitofMeasureBo billUnitOfRankingBo;
	@Autowired
	private GlobalItemBo globalItemBo;
	@Autowired
	private Alert alert;

	@Autowired
	private BillschemestatusList billschemestatusList;

	@Autowired
	private BillSchemeMeasurePriceBo billschemerankingpriceBo;

	@Autowired
	private UserIdentity userIdentity;
	@Autowired
	private Auditor auditor;

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		// model.addAttribute("scheme", billschemeBo.fetchAll());
		List<BillScheme> list = billschemeBo
				.fetchAllByOrganisation(userIdentity.getOrganisation());
		model.addAttribute("scheme", list);

		// List<BillSchemeItem> listItem = billschemeItemBo.fetchAll();
		// model.addAttribute("item", listItem);

		// List<BillSchemeItemPrice> listPrice =
		// billschemeItemPriceBo.fetchAll();
		// model.addAttribute("pric", listPrice);

		// List<GlobalItemUnitofRanking> listUnit =
		// this.billUnitOfRankingBo(userIdentity.getOrganisation().getId());
		// model.addAttribute("uni", listUnit);

		return "billscheme/index";

	}

	@RequestMapping(value = "/add")
	@Layout(value = "layouts/datatable")
	public String addAction(Model model) {
		model.addAttribute("aForm", new BillSchemeForm());
		model.addAttribute("scheme", this.billschemeBo
				.fetchAllByOrganisation(userIdentity.getOrganisation()));
		return "billscheme/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("aForm") BillSchemeForm billschemeForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("scheme", this.billschemeBo
					.fetchAllByOrganisation(userIdentity.getOrganisation()));
			return "billscheme/add";
		}
		BillScheme billscheme = new BillScheme();
		billscheme.setId(billschemeForm.getBillId());
		billscheme.setName(billschemeForm.getName());
		billscheme.setDescription(billschemeForm.getDescription());
		// billscheme.setBillingType(billschemeForm.getBillingType());
		billscheme.setCreatedBy(userIdentity.getUsername());
		billscheme.setOrganisation(userIdentity.getOrganisation());
		billscheme.setCreatedDate(new GregorianCalendar().getTime());
		billscheme.setBillschemestatus(this.billschemestatusList
				.getBillschemestatusById(1));
		this.billschemeBo.save(billscheme);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service Billing Scheme added!");
		return "redirect:/billscheme";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("mpForm") BillSchemeRankingPriceForm billschemepricerankingform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		// if (result.hasErrors()) {
		// model.addAttribute("scheme",
		// this.billschemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		// return "billscheme/add";
		// }
		try {

			BillSchemeRankingPrice billschemerankingprice = new BillSchemeRankingPrice();

			billschemerankingprice.setBillscheme(this.billschemeBo
					.getBillSchemeById(billschemepricerankingform
							.getBillscheme_id()));
			billschemerankingprice
					.setGlobalitemranking(this.billUnitOfRankingBo
							.getGlobalItemRankingById(billschemepricerankingform
									.getGlobalitemranking_id()));
			billschemerankingprice.setPrice(billschemepricerankingform
					.getPrice());
			billschemerankingprice.setCreatedBy(userIdentity.getUsername());
			billschemerankingprice.setOrganisation(userIdentity
					.getOrganisation());
			billschemerankingprice.setCreatedDate(new GregorianCalendar()
					.getTime());
			billschemerankingprice.setDeleted(false);

			billschemerankingpriceBo.save(billschemerankingprice);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Ranking Billing Price added!");

		}

		catch (Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER, e.getMessage()
					+ " May be duplicate");
		}
		return "redirect:/billscheme/billrankingprice/"
				+ billschemepricerankingform.getGlobalitemranking_id();

	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		BillScheme billscheme = billschemeBo.getBillSchemeById(id);
		// System.out.println( "Before1");
		BillSchemeRankingPrice billschemerankingprice = this.billschemerankingpriceBo
				.getBillSchemeRankingPriceById(id);

		if (null == billschemerankingprice) {
			alert.setAlert(redirectAttributes, Alert.DANGER, "Invalid resource");
			return "redirect:/billscheme/billprice/" + id;
		}

		BillSchemeRankingPriceForm billschemerankingForm = new BillSchemeRankingPriceForm();

		billschemerankingForm.setBillscheme_id(billschemerankingprice
				.getBillscheme().getId());

		billschemerankingForm.setGlobalitemranking_id(billschemerankingprice
				.getGlobalitemranking().getId());

		billschemerankingForm.setPrice(billschemerankingprice.getPrice());
		// billschemeForm.setBillingType(billscheme.getBillingType());
		// billschemeForm.setStatus_id(billscheme.getBillschemestatus().getBillschemestatus_id());

		List<BillScheme> billschemelist = billschemeBo
				.fetchAllByOrganisation(userIdentity.getOrganisation());

		model.addAttribute("billschemelist", billschemelist);
		model.addAttribute("mpForm", billschemerankingForm);
		// model.addAttribute("scheme",
		// this.billschemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		// System.out.println( "Before2");
		this.auditor.before(request, "billing Ranking Price Form",
				billschemerankingForm);

		return "billscheme/rankingprice/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@PathVariable("id") Integer id,
			@Valid @ModelAttribute("mpForm") BillSchemeRankingPriceForm billschemerankingpriceForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
		try {

			BillSchemeRankingPrice billschemerankingprice = this.billschemerankingpriceBo
					.getBillSchemeRankingPriceById(id);

			if (null == billschemerankingprice) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Invalid resource");
				return "redirect:/billscheme/billprice/" + id;
			}
			// BillSchemeRankingPriceForm billschemerankingForm = new
			// BillSchemeRankingPriceForm();
			billschemerankingprice.setBillscheme(this.billschemeBo
					.getBillSchemeById(billschemerankingpriceForm
							.getBillscheme_id()));
			billschemerankingprice
					.setGlobalitemranking(this.billUnitOfRankingBo
							.getGlobalItemRankingById(billschemerankingpriceForm
									.getGlobalitemranking_id()));
			billschemerankingprice.setPrice(billschemerankingpriceForm
					.getPrice());

			billschemerankingpriceBo.update(billschemerankingprice);

			auditor.after(request, "billing Ranking Price Form",
					billschemerankingpriceForm, userIdentity.getUsername(),
					billschemerankingprice.getId());

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Ranking Scheme updated");

		}

		catch (Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER, e.getMessage()
					+ " May be duplicate");
		}

		return "redirect:/billscheme/billprice/"
				+ billschemerankingpriceForm.getGlobalitemranking_id();

	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		BillSchemeRankingPrice billschemerankingprice = this.billschemerankingpriceBo
				.getBillSchemeRankingPriceById(id);

		if (null == billschemerankingprice) {
			alert.setAlert(redirectAttributes, Alert.DANGER, "Invalid resource");
			return "redirect:/billscheme/billprice/" + id;
		}

		billschemerankingprice.setModifiedBy(userIdentity.getUsername());
		billschemerankingprice.setModifiedDate(new GregorianCalendar()
				.getTime());
		billschemerankingprice.setDeleted(true);
		billschemerankingpriceBo.update(billschemerankingprice);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ranking-Price  deleted");

		// BillSchemeForm billschemeForm = new BillSchemeForm();
		// billschemeForm.setBillId(billscheme.getId());
		// billschemeForm.setDescription(billscheme.getDescription());
		// billschemeForm.setName(billscheme.getName());
		// billschemeForm.setBillingType(billscheme.getBillingType());
		// model.addAttribute("dForm", billschemeForm);
		// model.addAttribute("scheme", billscheme);
		return "redirect:/billscheme/billprice/"
				+ billschemerankingprice.getGlobalitemranking().getId();
	}

}
