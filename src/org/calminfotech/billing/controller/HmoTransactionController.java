package org.calminfotech.billing.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemPriceBo;
import org.calminfotech.billing.boInterface.BillSchemeMeasurePriceBo;
import org.calminfotech.billing.boInterface.BillingInvoiceBo;
import org.calminfotech.billing.boInterface.HmoTransactionBo;
//import org.calminfotech.billing.forms.HmoTransactionForm;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoTransaction;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BillschemestatusList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.PaymodeList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.forms.PrescribedSearchForm;
import org.calminfotech.visitqueue.models.VisitStatus;
//import org.calminfotech.visitqueue.models.Visitoutstanding;
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
public class HmoTransactionController {

	@Autowired
	private BillSchemeBo billschemeBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired
	private GlobalItemUnitofMeasureBo globalitemunitBo;

	@Autowired
	private GlobalItemBo globalitemBo;

	@Autowired
	private HmoTransactionBo hmoTranBo;

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
	@RequestMapping(value = "/hmo/indexall")
	public String viewAction3(Model model, RedirectAttributes redirectAttributes) {
		// if (userIdentity.getCurrentPointId() != 10) {
		// userIdentity.setCurrentUrl("redirect:/transaction/hmo/indexall");
		// return "redirect:/visits/queue/10";
		// }
		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		List<HmoTransaction> list = this.hmoTranBo.fetchAllByOrganisation50();

		model.addAttribute("tlist", list);
		model.addAttribute("prescribedSearch", prescribedSearch);
		return "invoice/hmotransactionlist";
	}

	@RequestMapping(value = "/hmo/indexall", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction212(
			@Valid @ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedSearchForm,
			Model model, RedirectAttributes redirectAttributes) {

		// if (userIdentity.getCurrentPointId() != 10) {
		// userIdentity.setCurrentUrl("redirect:/transaction/hmo/indexall");
		// return "redirect:/visits/queue/10";
		// }

		List<HmoTransaction> list = this.hmoTranBo
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

		model.addAttribute("tlist", list);
		model.addAttribute("prescribedSearch", prescribedSearch);

		return "invoice/hmotransactionlist";

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/hmo/{cid}")
	public String viewAction(@PathVariable("cid") Integer cid, Model model,
			RedirectAttributes redirectAttributes) {
		//HmoTransactionForm ctform = new HmoTransactionForm();
		Hmo pt = this.hmoBo.getHmoById(cid);

		/*ctform.setHmoName(pt.getName());
		ctform.setHmoId(cid);
		model.addAttribute("custtranForm", ctform);*/
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());
		// model.addAttribute("vid", vid);

		return "invoice/hmotransaction";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/hmo/{cid}/{vid}")
	public String viewAction(@PathVariable("cid") Integer cid,
			@PathVariable("vid") Integer vid, Model model,
			RedirectAttributes redirectAttributes) {
		Hmo pt = this.hmoBo.getHmoById(cid);
		/*HmoTransactionForm ctform = new HmoTransactionForm();

		ctform.setHmoName(pt.getName());
		ctform.setHmoId(cid);
		model.addAttribute("Form", ctform);*/
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());
		model.addAttribute("vid", vid);

		return "invoice/hmotransaction";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/hmo")
	public String viewAction(Model model, RedirectAttributes redirectAttributes) {
		//HmoTransactionForm ctform = new HmoTransactionForm();
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());
		//model.addAttribute("custtranForm", ctform);

		return "invoice/hmotransaction";
	}

	@RequestMapping(value = "/hmo", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveActionpay(
			//@Valid @ModelAttribute("custtranForm") HmoTransactionForm tranform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		// if (userIdentity.getCurrentPointId() != 10) {
		// userIdentity.setCurrentUrl("redirect:/transaction/hmo");
		// return "redirect:/visits/queue/10";
		// }

		HmoTransaction hmotran = new HmoTransaction();
		Double crl = 0.00;
		Double gcrl = 0.00;
		Double pcrl = 0.00;
		/*
		 * SettingsAssignment setass1 = this.settingBo.fetchsettings(
		 * "CREDIT_LIMIT", userIdentity.getOrganisation().getId());
		 * 
		 * if (setass1 != null) { try { gcrl =
		 * Double.parseDouble(setass1.getSettings_value()); } catch (Exception
		 * e)
		 * 
		 * { gcrl = 0.00;
		 * 
		 * } }
		 * 
		 * try { Double vt =
		 * this.patientBo.getPatientById(tranform.getPatientId())
		 * .getCreditlimit().doubleValue(); if (vt != 0.00) { crl = vt; } else {
		 * crl = gcrl; } }
		 * 
		 * catch (Exception e) { crl = gcrl; }
		 */
		hmotran.setEffectivedate(new Date(System.currentTimeMillis()));
		/*hmotran.setDescription(tranform.getDescription());
		hmotran.setHmo(this.hmoBo.getHmoById(tranform.getHmoId()));
		hmotran.setDrcr(tranform.getDrcr());
		hmotran.setTranrefno(new AutoGenerate().mygen());

		if (tranform.getDrcr().equalsIgnoreCase("Dr")) {

			System.out.print ("DEBIT OOOO");
			System.out.print (tranform.getAmount());
			System.out.print ("DEBIT OOOO");
			
			hmotran.setAmount(tranform.getAmount().doubleValue());
			hmotran.setTrantype("claims");

		} else {

			System.out.print ("CREDIT  OOOO");
			System.out.print (-tranform.getAmount());
			System.out.print ("CREDIT  OOOO");
			hmotran.setAmount(-(tranform.getAmount().doubleValue()));

			hmotran.setTrantype("claimspaid");
		}*/


		hmotran.setCreatedBy(userIdentity.getUsername());
		hmotran.setUser(userIdentity.getUser());
		hmotran.setCreatedDate(new Date(System.currentTimeMillis()));
		hmotran.setOrganisation(userIdentity.getOrganisation());
		//hmotran.setPaymode(this.paymodeBo.getPaymodeTypeById(tranform
		//		.getPaymode_id()));
		hmotran.setDeleted(false);

		this.hmoTranBo.save(hmotran);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Transaction created succesfully!!!");
		// return "redirect:/invoice/invoicelist/" + payform.getVisitId();
		return "redirect:/transaction/hmo";
		// + payform.getBillinvoiceId();

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/hmoout/{pid}")
	public String viewActionout(@PathVariable("pid") Integer pid, Model model,
			RedirectAttributes redirectAttributes) {
		// HmoTransactionForm ctform = new HmoTransactionForm();
		// Patient pt = this.patientBo.getPatientById(cid);
		//List<Visitoutstanding> vout = this.visitBo.fetchByPatientIdout(pid);

		//model.addAttribute("outlist", vout);

		return "invoice/visitoutstanding";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/hmo/printreceipt/{id}")
	public String viewActionprintinvoice(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		HmoTransaction hmotran = this.hmoTranBo.getHmoTransactionById(id);
		model.addAttribute("printtime", new Date(System.currentTimeMillis()));
		model.addAttribute("hmotran", hmotran);
		return "invoice/printcustreceipt";
	}
}
