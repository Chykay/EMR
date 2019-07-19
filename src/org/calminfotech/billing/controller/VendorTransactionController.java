package org.calminfotech.billing.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemPriceBo;
import org.calminfotech.billing.boInterface.BillSchemeMeasurePriceBo;
import org.calminfotech.billing.boInterface.BillingInvoiceBo;
import org.calminfotech.billing.boInterface.VendorTransactionBo;
import org.calminfotech.billing.forms.VendorTransactionForm;
import org.calminfotech.billing.models.VendorTransaction;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;
/*import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;*/
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.AutoGenerate;
import org.calminfotech.utils.BillschemestatusList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.PaymodeList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.forms.PrescribedSearchForm;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//*.*;

@Controller
@RequestMapping(value = "/transaction")
public class VendorTransactionController {

	@Autowired
	private BillSchemeBo billschemeBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired
	private GlobalItemUnitofMeasureBo globalitemunitBo;

	@Autowired
	private VendorManagerInterface vendorBo;

	@Autowired
	private GlobalItemBo globalitemBo;

	@Autowired
	private VendorTransactionBo vendorTranBo;

	@Autowired
	private BillSchemeItemPriceBo billschemeItemPriceBo;

	@Autowired
	private BillSchemeMeasurePriceBo billschememeasurepriceBo;

	@Autowired
	private StaffRegBoInterface staffRegBo;

	@Autowired
	private GlobalItemUnitofMeasureBo itemUnitOfMeasureBo;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private org.calminfotech.hmo.boInterface.HmoBo hmoBo;

	@Autowired
	private Alert alert;

	@Autowired
	private SettingBo settingBo;

	@Autowired
	private BillschemestatusList billschemestatusList;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private VisitBo visitBo;

	@Autowired
	private VisitDao visitDao;

	@Autowired
	private OrganisationBo orgBo;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private HrunitCategoryBo unitBo;

	@Autowired
	private BillingInvoiceBo billinvoiceBo;

	@Autowired
	private PatientHmoBo patienthmopackageBo;

	@Autowired
	private HmoPackageBo hmopackageBo;

	@Autowired
	private PaymodeList paymodeBo;

	@Autowired
	private HmoPackageItemBo PackageItemBo;

	@Autowired
	private HrunitCategoryBo hrUnitBo;

	@Autowired
	private org.calminfotech.hmo.boInterface.HmoBo HmoBo;

	Integer gvst = null;

	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/vendor/indexall")
	public String viewAction3(Model model, RedirectAttributes redirectAttributes) {
		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/transaction/vendor/indexall");
			return "redirect:/visits/queue/10";
		}
		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		List<VendorTransaction> custtranlist = this.vendorTranBo
				.fetchAllByOrganisation50();
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("tlist", custtranlist);

		return "invoice/vendortransactionlist";
	}

	@RequestMapping(value = "/vendor/indexall", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction212(
			@Valid @ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedSearchForm,
			Model model, RedirectAttributes redirectAttributes) {
		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/transaction/vendor/indexall");
			return "redirect:/visits/queue/10";
		}

		List<VendorTransaction> custtranlist = this.vendorTranBo
				.fetchAllByOrganisationbyform(DateUtils.formatStringToDate(
						prescribedSearchForm.getDat1(), "yyyy-MM-dd HH:mm"),
						DateUtils.formatStringToDate(
								prescribedSearchForm.getDat2(),
								"yyyy-MM-dd HH:mm"));

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		prescribedSearch.setDat1(prescribedSearchForm.getDat1());
		prescribedSearch.setDat2(prescribedSearchForm.getDat2());
		prescribedSearch.setStatusId(prescribedSearchForm.getStatusId());
		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("tlist", custtranlist);

		return "invoice/vendortransactionlist";

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/vendor")
	public String viewAction(Model model, RedirectAttributes redirectAttributes) {
		VendorTransactionForm ctform = new VendorTransactionForm();
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());
		model.addAttribute("custtranForm", ctform);

		return "invoice/vendortransaction";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/vendor/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		VendorTransactionForm ctform = new VendorTransactionForm();
		//Vendor pt = null;
	/*	try {
			pt = this.vendorBo.getVendorDetailsById(id);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctform.setVendorName(pt.getVendorName());
		ctform.setVendorId(pt.getId());*/
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());
		model.addAttribute("custtranForm", ctform);

		return "invoice/vendortransaction";

	}

	@RequestMapping(value = "/vendor", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveActionpay(
			@Valid @ModelAttribute("custtranForm") VendorTransactionForm tranform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/transaction/vendor");
			return "redirect:/visits/queue/10";
		}
		VendorTransaction custtran = new VendorTransaction();

		custtran.setEffectivedate(DateUtils.formatStringToDate(
				tranform.getEffectivedate(), "yyyy-MM-dd HH:mm"));
		custtran.setDescription(tranform.getDescription());
		try {
			custtran.setVendor(this.vendorBo.getVendorDetailsById(tranform
					.getVendorId()));
		} catch (RecordNotFoundException e) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error saving Customer Information!");
			// return "redirect:/invoice/invoicelist/" + payform.getVisitId();
			return "redirect:/transaction/vendor";
		}
		custtran.setDrcr(tranform.getDrcr());

		if (tranform.getDrcr().equalsIgnoreCase("dr")) {
			custtran.setAmount(-tranform.getAmount());
			custtran.setTrantype("Payment");
		} else {
			custtran.setAmount(tranform.getAmount());
			custtran.setTrantype("Supply");
		}

		// custtran.setTranrefno()
		custtran.setCode(new AutoGenerate().mygen());

		custtran.setCreatedBy(userIdentity.getUsername());
		custtran.setUser(userIdentity.getUser());
		custtran.setCreatedDate(new Date(System.currentTimeMillis()));
		// custtran.setCreatedDate(new GregorianCalendar().getTime());
		custtran.setOrganisation(userIdentity.getOrganisation());

		custtran.setPaymode(this.paymodeBo.getPaymodeTypeById(tranform
				.getPaymode_id()));
		custtran.setDeleted(false);

		this.vendorTranBo.save(custtran);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Transaction created succesfully!!!");
		// return "redirect:/invoice/invoicelist/" + payform.getVisitId();
		return "redirect:/transaction/vendor";
		// + payform.getBillinvoiceId();

	}
}
