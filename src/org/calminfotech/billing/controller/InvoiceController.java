/*package org.calminfotech.billing.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemBo;
import org.calminfotech.billing.boInterface.BillSchemeItemPriceBo;
import org.calminfotech.billing.boInterface.BillSchemeMeasurePriceBo;
import org.calminfotech.billing.boInterface.BillingInvoiceBo;
import org.calminfotech.billing.boInterface.CustomerTransactionBo;
import org.calminfotech.billing.boInterface.VisitInvoice;
import org.calminfotech.billing.forms.InvoiceBillForm;
import org.calminfotech.billing.forms.InvoiceBill_ChargetoForm;
import org.calminfotech.billing.forms.PaymentForm;
import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.billing.models.BillInvoicePayment;
import org.calminfotech.billing.models.BillSchemeMeasurePrice;
import org.calminfotech.billing.models.CustomerTransaction;
import org.calminfotech.billing.models.Invoice;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.report.utils.BillingListDao;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.AutoGenerate;
import org.calminfotech.utils.BillschemestatusList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.InvoicestatusList;
import org.calminfotech.utils.PaymodeList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.forms.PrescribedSearchForm;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitInvoice;
import org.calminfotech.visitqueue.models.Visitoutstanding;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.google.gson.Gson;
import com.lowagie.text.DocumentException;

//*.*;

@Controller
@RequestMapping(value = "/invoice")
public class InvoiceController {

	@Autowired
	private BillSchemeBo billschemeBo;

	@Autowired
	private InvoicestatusList invstatusBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired
	private BillingListDao billingListDao;

	@Autowired
	private GlobalItemUnitofMeasureBo globalitemunitBo;

	@Autowired
	private GlobalItemBo globalitemBo;

	@Autowired
	private CustomerTransactionBo customerTranBo;
	@Autowired
	private BillSchemeItemBo billschemeItemBo;

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
	private Authorize authorize;

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

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/raiseinvoice")
	public String viewAction(
			Model model,
			@Valid @ModelAttribute("vForm") VisitWorkflowUserConfigurationForm vfc,
			BindingResult result, RedirectAttributes redirectAttributes) {

		System.out.print("vstsearchraise" + vfc.getVisitId());
		Visit vst = this.visitBo.getVisitationById(vfc.getVisitId());
		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			System.out.print("vstsearchraiserror" + vfc.getVisitId());

			return this.process(model);

		}

		System.out.print(vfc.getVisitId());
		System.out.print(vfc.getQty());
		System.out.print(vfc.getItemmeasureid());

		System.out.print(vfc.getAmt());

		System.out.print(vfc.getUnitId());
		System.out.print(userIdentity.getOrganisation().getId());

		Double vat = 0.00;
		SettingsAssignment setass = this.settingBo.fetchsettings(
				"VAT_PERCENT_VALU", userIdentity.getOrganisation().getOrgCoy()
						.getId());

		if (setass != null) {
			try {
				vat = Double.parseDouble(setass.getSettings_value());
			} catch (Exception e)

			{
				vat = 0.00;

			}

		} else {
			vat = 0.00;

		}

		System.out.println("use hmo" + vfc.getUsehmo());
		// System.out.println("use hmo" + vfc.getUsehmo().toString());

		// System.out.println("use hmo" + vfc.getUsehmo().booleanValue());

		Invoice invoice = this.billinvoiceBo.Getinvoice(vfc.getVisitId(),
				vfc.getQty(), vfc.getItemmeasureid(), vfc.getAmt(),
				vfc.getUnitId(), userIdentity.getOrganisation(),
				vfc.getUsehmo());

		if (invoice == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Check if Scheme has been assigned to unit and other settings!");

			return this.process(model);

		}

		InvoiceBillForm invoiceform = new InvoiceBillForm();

		invoiceform.setGlobalitemname(invoice.getGlobalitemname());

		invoiceform.setSurname(vst.getPatient().getSurname());

		invoiceform.setFirst_name(vst.getPatient().getFirstName());

		invoiceform.setItemmeasurename(invoice.getItemmeasurename());

		invoiceform.setUsehmo(vfc.getUsehmo());

		System.out.print("myqty " + invoice.getQty());

		System.out.print("invamt " + invoice.getInvamt());
		System.out.print("vatamt " + invoice.getVatamt());

		// invoiceform.setVatamt(invoice.getInvamt()
		// - (invoice.getInvamt() / (1 + vat)));

		invoiceform.setVatamt(invoice.getVatamt());

		invoiceform.setQty(invoice.getQty());

		invoiceform.setDuedate(DateUtils.formatDateToString(
				new Date(System.currentTimeMillis()), "yyyy-MM-dd hh:mm"));

		// invoiceform.setQty(20);

		System.out.print("myqtyform " + invoiceform.getQty());

		invoiceform.setInvamt(invoice.getInvamt());

		invoiceform.setGroamt(invoice.getInvamt() - invoice.getVatamt());
		invoiceform.setVat(vat);
		invoiceform.setAddamt(0.00);
		invoiceform.setDedamt(0.00);

		invoiceform.setNetamt(invoice.getInvamt() - invoice.getVatamt());

		// invoiceform.setVatamt(0.1*invoice.getInvamt());

		// invoiceform.setVisitid(this.visitBo.ginvoice.getVisitinvid());
		invoiceform.setVisitinvid(invoice.getVisitinvid());
		invoiceform.setGlobalitemid(invoice.getGlobalitemid());

		invoiceform.setUnitid(invoice.getUnitid());
		invoiceform.setDescription(invoice.getGlobalitemname());

		invoiceform.setReferenceid(invoice.getVisitinvid());

		invoiceform.setItemmeasureid(invoice.getItemmeasureid());

		System.out.print("ppppppppp");

		System.out.print(invoiceform);

		System.out.print(invoiceform.getUsehmo());

		System.out.print("ppppppppp");

		model.addAttribute("invoiceForm", invoiceform);

		model.addAttribute("invoice", invoice);

		model.addAttribute("stafflist", this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		model.addAttribute("v", vst);

		if (invoice.getItemmeasurename().equalsIgnoreCase("unit")) {
			model.addAttribute("rdnl", "rd");
		} else {
			model.addAttribute("rdnl", "ed");
		}
		return "invoice/show";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("invoiceForm") InvoiceBillForm invoiceform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		Visit vst = this.visitBo.getVisitationById(invoiceform.getVisitid());

		System.out.print("vstsearchpost" + invoiceform.getVisitid());
		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			// VisitsController h = new VisitsController();
			System.out.print("vstsearchposterrror" + invoiceform.getVisitid());
			return this.process(model);

		}

		// System.out.print(invoiceform.getVisitid());
		System.out.print(invoiceform.getQty());
		System.out.print(invoiceform.getItemmeasureid());
		System.out.print(invoiceform.getInvamt());

		System.out.print(invoiceform.getUnitid());
		System.out.print(userIdentity.getOrganisation().getId());

		System.out.print("addaddaddadd");

		// System.out.println("use hmo" + invoiceform.getUsehmo().toString());

		System.out.println("use hmo" + invoiceform.getUsehmo());

		System.out.print("addaddaddadd");

		Invoice invoice = this.billinvoiceBo.Getinvoice(
				invoiceform.getVisitinvid(), invoiceform.getQty(),
				invoiceform.getItemmeasureid(), invoiceform.getNetamt(),
				invoiceform.getUnitid(), userIdentity.getOrganisation(),
				invoiceform.getUsehmo());

		if (invoice == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Check if Scheme has been assigned to unit and other settings!!");
			System.out.print("vstsearchpostnoinvoive"
					+ invoiceform.getVisitid());
			return this.process(model);
			// return "redirect:/visits/labtestlist";
		}

		BillInvoice billinv = new BillInvoice();

		if (invoice.getGlobalitemid() != null) {
			billinv.setGlobalitem(this.globalItemBo.getGlobalItemById(invoice
					.getGlobalitemid()));
		}

		billinv.setVisitinv(this.visitBo.getVisitationInvoiceById(invoice
				.getVisitinvid()));

		// billinv.setDuedate(vst.getEffectiveDate());
		billinv.setDuedate(DateUtils.formatStringToDate(invoiceform
				.getDuedate()));

		billinv.setReferenceid(invoiceform.getReferenceid());

		if (invoice.getBillschemeid() != null) {
			billinv.setBillscheme(billschemeBo.getBillSchemeById(invoice
					.getBillschemeid()));
		}

		if (invoice.getPatienthmopackageid() != null) {
			billinv.setPatienthmopackage(patienthmopackageBo
					.getPatientHmoById(invoice.getPatienthmopackageid()));
		}

		if (invoice.getHmoid() != null) {
			billinv.setHmo(hmoBo.getHmoById(invoice.getHmoid()));
		}

		if (invoice.getHmopackageid() != null) {
			billinv.setHmopackage(hmopackageBo.getHmoPackageById(invoice
					.getHmopackageid()));
		}

		if (invoice.getHmopackageitemid() != null) {
			billinv.setHmopackageitem(PackageItemBo.getHmoItemById(invoice
					.getHmopackageitemid()));
		}

		if (invoice.getItemmeasureid() != null) {

			billinv.setItemmeasure(this.itemUnitOfMeasureBo
					.getGlobalItemUnitofMeasureByIdvw(invoice
							.getItemmeasureid()));
		}

		System.out.print("qqqqq" + invoice.getQty());
		billinv.setQty(invoice.getQty());

		System.out.print("uuuuu" + invoice.getUnitid());

		if (invoice.getUnitid() != null) {
			billinv.setUnit(this.unitBo.getHrunitCategoryById(invoice
					.getUnitid()));
		}

		billinv.setDescription(invoiceform.getDescription());

		billinv.setGroamt(invoiceform.getGroamt());
		billinv.setAddamt(invoiceform.getAddamt());
		billinv.setDedamt(invoiceform.getDedamt());
		billinv.setNetamt(invoiceform.getNetamt());

		billinv.setInvamt(invoice.getInvamt());
		billinv.setVatamt(invoiceform.getVatamt());

		
		 * invoiceform.setVat(invoice.getInvamt() - (invoice.getInvamt() / (1 +
		 * vat)));
		 

		billinv.setCashamt(invoice.getCashamt());

		billinv.setHmoamt(invoice.getHmoamt());

		billinv.setCreatedBy(userIdentity.getUsername());
		billinv.setCreatedDate(new GregorianCalendar().getTime());
		billinv.setOrganisation(userIdentity.getOrganisation());
		billinv.setDeleted(false);
		// billinv.setCreateuser(userIdentity.getUser());

		billinv.setPatient(this.patientBo.getPatientById(invoice.getPatientid()));

		billinv.setUser(userIdentity.getUser());

		if (invoiceform.getStaffid().intValue() != 0) {
			billinv.setStaffreg(this.staffRegBo.getStaffById(invoiceform
					.getStaffid()));

		}

		if (invoice.getInvamt().intValue() <= 0) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invoice Amount cannot be less or equal to zero!!!");
		} else {
			this.billinvoiceBo.save(billinv);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Invoice created succesfully!!!");

		}
		return "redirect:/invoice/invoicelist/" + gvst;
		// return this.process(model);
		// return "redirect:/visits/frontdesklist";

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/raiseinvoiceallitem/{vid}", method = RequestMethod.GET)
	public String viewActionallitem(@PathVariable("vid") Integer vid,
			Model model,

			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("BILLR001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/invoice/raiseinvoiceallitem/"
					+ vid);
			return "redirect:/visits/queue/10";
		}

		Double gcrl = 0.00;
		Double pcrl = 0.00;
		Double crl = 0.00;

		// System.out.print("vstsearchraise" + vfc.getVisitId());
		VisitInvoice vst = (VisitInvoice) this.visitBo.getVisitationInvoiceById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit Invoice");
			// System.out.print("vstsearchraiserror" + vst.getId());

			return this.process(model);

		}

		if ((((Double) vst.getMfig().get("totcash")).doubleValue() == ((Double) vst
				.getMfig().get("totpaymt")).doubleValue())
				&& (((Double) vst.getMfig().get("totcash")).doubleValue() != 0)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"You Cannot Add More Bill!!! Invoice has been fully settled ");
			return "redirect:/invoice/invoicelist/" + vst.getId();

		}

		gvst = vst.getId();
		Double vat = 0.00;
		SettingsAssignment setass = this.settingBo.fetchsettings(
				"VAT_PERCENT_VALU", userIdentity.getOrganisation().getId());

		if (setass != null) {
			try {
				vat = Double.parseDouble(setass.getSettings_value());
			} catch (Exception e)

			{
				vat = 0.00;

			}
		}

		if (this.hrUnitBo
				.getHrunitCategoryById(userIdentity.getCurrentUnitId()) == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Check if Scheme has been assigned to unit and other settings!");

			return this.process(model);

		}

		InvoiceBillForm invoiceform = new InvoiceBillForm();

		// invoiceform.setGlobalitemname(invoice.getGlobalitemname());

		invoiceform.setSurname(vst.getVisit().getPatient().getSurname());

		invoiceform.setFirst_name(vst.getVisit().getPatient().getFirstName());
		invoiceform.setOrgid(userIdentity.getOrganisation().getId());

		// invoiceform.setVatamt(invoice.getInvamt()- (invoice.getInvamt() / (1
		// + vat)));

		invoiceform.setVat(vat);

		invoiceform.setQty(1);
		invoiceform.setUnitid(userIdentity.getCurrentUnitId());

		// invoiceform.setQty(20);

		System.out.print("myqtyform " + invoiceform.getQty());

		invoiceform.setInvamt(0.00);

		invoiceform.setGroamt(0.00);

		invoiceform.setAddamt(0.00);
		invoiceform.setDedamt(0.00);

		// invoiceform.setVatamt(0.1*invoice.getInvamt());
		invoiceform.setDuedate(DateUtils.formatDateToString(
				new Date(System.currentTimeMillis()), "yyyy-MM-dd hh:mm"));
		invoiceform.setVisitid(vst.getVisit().getId());
		invoiceform.setVisitinvid(vst.getId());
		// invoiceform.setGlobalitemid(invoic.getGlobalitemid());

		invoiceform.setUnitid(vst.getVisit().getUnit().getCategoryId());

		// invoiceform.setDescription(invoice.getGlobalitemname());

		// invoiceform.setReferenceid(invoice.getVisitid());

		// invoiceform.setItemmeasureid(invoice.getItemmeasureid());

		System.out.print("ppppppppp");

		System.out.print(invoiceform);

		System.out.print(invoiceform.getUsehmo());

		System.out.print("ppppppppp");

		model.addAttribute("invoiceForm", invoiceform);

		// model.addAttribute("invoice", invoice);

		model.addAttribute("stafflist", this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		model.addAttribute("v", vst);

		model.addAttribute("crl", crl);

		
		 * if (invoice.getItemmeasurename().equalsIgnoreCase("unit")) {
		 * model.addAttribute("rdnl", "rd"); } else { model.addAttribute("rdnl",
		 * "ed"); }
		 
		return "invoice/showallitem";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/raiseinvoiceitempham/{vid}/{measureid}/{qty}", method = RequestMethod.GET)
	public String viewActionitempham(@PathVariable("vid") Integer vid,
			@PathVariable("measureid") Integer measureid,
			@PathVariable("qty") Integer qty, Model model,

			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("BILLR001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		if (userIdentity.getCurrentPointId() != 10) {
			// userIdentity.setCurrentUrl("redirect:/visits/druglist/" + vid);
			// return "redirect:/visits/queue/10";
		}

		Double gcrl = 0.00;
		Double pcrl = 0.00;
		Double crl = 0.00;

		// System.out.print("vstsearchraise" + vfc.getVisitId());
		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(vid);
		GlobalItemUnitofMeasureVw itemunitofmeasure = globalitemunitBo
				.getGlobalItemUnitofMeasureByIdvw(measureid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit Invoice");
			// System.out.print("vstsearchraiserror" + vst.getId());

			return this.process(model);

		}

		if ((((Double) vst.getMfig().get("totcash")).doubleValue() == ((Double) vst
				.getMfig().get("totpaymt")).doubleValue())
				&& (((Double) vst.getMfig().get("totcash")).doubleValue() != 0)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"You Cannot Add More Bill!!! Invoice has been fully settled ");
			return "redirect:/invoice/invoicelist/" + vst.getId();

		}

		gvst = vst.getId();
		Double vat = 0.00;
		SettingsAssignment setass = this.settingBo.fetchsettings(
				"VAT_PERCENT_VALU", userIdentity.getOrganisation().getId());

		if (setass != null) {
			try {
				vat = Double.parseDouble(setass.getSettings_value());
			} catch (Exception e)

			{
				vat = 0.00;

			}
		}

		if (this.hrUnitBo
				.getHrunitCategoryById(userIdentity.getCurrentUnitId()) == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Check if Scheme has been assigned to unit and other settings!");

			return this.process(model);

		}

		InvoiceBillForm invoiceform = new InvoiceBillForm();

		invoiceform.setGlobalitemname(itemunitofmeasure.getGlobalitemname());
		invoiceform.setGlobalitemid(itemunitofmeasure.getGlobalitem_id());

		invoiceform.setSurname(vst.getVisit().getPatient().getSurname());

		invoiceform.setFirst_name(vst.getVisit().getPatient().getFirstName());
		invoiceform.setOrgid(userIdentity.getOrganisation().getId());

		// invoiceform.setVatamt(invoice.getInvamt()- (invoice.getInvamt() / (1
		// + vat)));

		invoiceform.setVat(vat);

		invoiceform.setQty(qty);
		invoiceform.setUnitid(userIdentity.getCurrentUnitId());

		// invoiceform.setQty(20);

		System.out.print("myqtyform " + invoiceform.getQty());

		invoiceform.setInvamt(0.00);

		invoiceform.setGroamt(0.00);

		invoiceform.setAddamt(0.00);
		invoiceform.setDedamt(0.00);

		// invoiceform.setVatamt(0.1*invoice.getInvamt());
		invoiceform.setDuedate(DateUtils.formatDateToString(
				new Date(System.currentTimeMillis()), "yyyy-MM-dd hh:mm"));
		invoiceform.setVisitid(vst.getVisit().getId());
		invoiceform.setVisitinvid(vst.getId());
		// invoiceform.setGlobalitemid(invoic.getGlobalitemid());

		invoiceform.setUnitid(vst.getVisit().getUnit().getCategoryId());

		// invoiceform.setDescription(invoice.getGlobalitemname());

		// invoiceform.setReferenceid(invoice.getVisitid());

		// invoiceform.setItemmeasureid(invoice.getItemmeasureid());

		// System.out.print("ppppppppp");

		// System.out.print(invoiceform);

		System.out.print(invoiceform.getUsehmo());

		System.out.print("ppppppppp");

		model.addAttribute("invoiceForm", invoiceform);

		// model.addAttribute("invoice", invoice);

		model.addAttribute("stafflist", this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		// model.addAttribute("measurelist", this.globalitemunitBo.
		// .fetchAllByItemIdvw(itemunitofmeasure.getGlobalitem_id()));

		// model.addAttribute("measurelist", this.globalitemunitBo
		// .getGlobalItemUnitofMeasureByIdvw(measureid));

		model.addAttribute("measurelist", itemunitofmeasure);

		model.addAttribute("v", vst);

		model.addAttribute("crl", crl);

		
		 * if (invoice.getItemmeasurename().equalsIgnoreCase("unit")) {
		 * model.addAttribute("rdnl", "rd"); } else { model.addAttribute("rdnl",
		 * "ed"); }
		 
		return "invoice/showallitem";
	}

	@RequestMapping(value = "/addallitem", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveActionallitem(
			@Valid @ModelAttribute("invoiceForm") InvoiceBillForm invoiceform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		// gross amt is gross amt per measure
		// netamt is net amount per measure +addamt -dedamt
		// inv amt is netamt *qty + (% of netamt *qty)

		if (!authorize.isAllowed("BILLR001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(invoiceform
				.getVisitinvid());

		// System.out.print("vstsearchpost" + invoiceform.getVisitid());
		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			// VisitsController h = new VisitsController();
			System.out.print("vstsearchposterrror" + invoiceform.getVisitid());
			return this.process(model);

		}

		// System.out.print(invoiceform.getVisitid());
		System.out.print(invoiceform.getQty());
		System.out.print(invoiceform.getItemmeasureid());
		System.out.print(invoiceform.getInvamt());

		System.out.print(invoiceform.getUnitid());
		System.out.print(userIdentity.getOrganisation().getId());

		System.out.print("addaddaddadd");

		// System.out.println("use hmo" + invoiceform.getUsehmo().toString());

		System.out.println("use hmo" + invoiceform.getUsehmo());

		System.out.print("addaddaddadd");

		Invoice invoice = this.billinvoiceBo.Getinvoice(
				invoiceform.getVisitinvid(), invoiceform.getQty(),
				invoiceform.getItemmeasureid(), invoiceform.getNetamt(),
				invoiceform.getUnitid(), userIdentity.getOrganisation(),
				invoiceform.getUsehmo());

		if (invoice == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Check if Scheme has been assigned to unit and other settings!!");
			System.out.print("vstsearchpostnoinvoive"
					+ invoiceform.getVisitid());
			return this.process(model);
			// return "redirect:/visits/labtestlist";
		}

		if (invoice.getInvamt().intValue() <= 0) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invoice Amount cannot be less or equal to zero!!!");
		} else {

			BillInvoice billinv = new BillInvoice();

			if (invoice.getGlobalitemid() != null) {
				billinv.setGlobalitem(this.globalItemBo
						.getGlobalItemById(invoice.getGlobalitemid()));
			}

			billinv.setVisitinv(vst);

			// billinv.setDuedate(vst.getEffectiveDate());

			// billinv.setDuedate(DateUtils.formatStringToDate(invoiceform
			// .getDuedate()));

			System.out.print("The time is ");
			System.out.print(new Date(System.currentTimeMillis()));

			billinv.setDuedate(new GregorianCalendar().getTime());

			System.out.print("finished");
			billinv.setReferenceid(invoiceform.getReferenceid());

			if (invoice.getBillschemeid() != null) {
				billinv.setBillscheme(billschemeBo.getBillSchemeById(invoice
						.getBillschemeid()));
			}

			if (invoice.getPatienthmopackageid() != null) {
				billinv.setPatienthmopackage(patienthmopackageBo
						.getPatientHmoById(invoice.getPatienthmopackageid()));
			}

			if (invoice.getHmoid() != null) {
				billinv.setHmo(hmoBo.getHmoById(invoice.getHmoid()));
			}

			if (invoice.getHmopackageid() != null) {
				billinv.setHmopackage(hmopackageBo.getHmoPackageById(invoice
						.getHmopackageid()));
			}

			if (invoice.getHmopackageitemid() != null) {
				billinv.setHmopackageitem(PackageItemBo.getHmoItemById(invoice
						.getHmopackageitemid()));
			}

			if (invoice.getItemmeasureid() != null) {

				billinv.setItemmeasure(this.itemUnitOfMeasureBo
						.getGlobalItemUnitofMeasureByIdvw(invoice
								.getItemmeasureid()));
			}

			System.out.print("qqqqq" + invoice.getQty());
			billinv.setQty(invoice.getQty());

			System.out.print("uuuuu" + invoice.getUnitid());

			if (invoice.getUnitid() != null) {
				billinv.setUnit(this.unitBo.getHrunitCategoryById(invoice
						.getUnitid()));
			}

			billinv.setDescription(invoiceform.getDescription());

			billinv.setGroamt(invoiceform.getGroamt());
			billinv.setAddamt(invoiceform.getAddamt());
			billinv.setDedamt(invoiceform.getDedamt());
			billinv.setNetamt(invoiceform.getNetamt());

			billinv.setInvamt(invoice.getInvamt());
			billinv.setVatamt(invoiceform.getVatamt());

			
			 * invoiceform.setVat(invoice.getInvamt() - (invoice.getInvamt() /
			 * (1 + vat)));
			 

			billinv.setCashamt(invoice.getCashamt());

			billinv.setHmoamt(invoice.getHmoamt());

			billinv.setCreatedBy(userIdentity.getUsername());
			billinv.setCreatedDate(new GregorianCalendar().getTime());
			billinv.setOrganisation(userIdentity.getOrganisation());
			billinv.setDeleted(false);
			// billinv.setCreateuser(userIdentity.getUser());
			billinv.setPatient(this.patientBo.getPatientById(invoice
					.getPatientid()));

			billinv.setUser(userIdentity.getUser());

			if (invoiceform.getStaffid().intValue() != 0) {
				billinv.setStaffreg(this.staffRegBo.getStaffById(invoiceform
						.getStaffid()));

			}

			this.billinvoiceBo.save(billinv);

			
			 * CustomerTransaction custtran = new CustomerTransaction();
			 * 
			 * custtran.setEffectivedate(new Date(System.currentTimeMillis()));
			 * custtran.setDescription("Purchase of " +
			 * invoice.getGlobalitemname() + " Inv Code - " + vst.getCode() +
			 * invoiceform.getDescription());
			 * custtran.setPatient(vst.getPatientbillto());
			 * custtran.setDrcr("dr"); custtran.setTrantype("Billing");
			 * custtran.setAmount(invoice.getCashamt());
			 * custtran.setTranrefno(vst.getCode().toString());
			 * 
			 * custtran.setCreatedBy(userIdentity.getUsername());
			 * custtran.setUser(userIdentity.getUser());
			 * custtran.setCreatedDate(new Date(System.currentTimeMillis())); //
			 * custtran.setCreatedDate(new // GregorianCalendar().getTime());
			 * custtran.setOrganisation(userIdentity.getOrganisation());
			 * 
			 * custtran.setPaymode(this.paymodeBo.getPaymodeTypeById(4));
			 * 
			 * custtran.setDeleted(false);
			 * 
			 * this.customerTranBo.save(custtran);
			 

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Invoice created succesfully!!!");

		}

		return "redirect:/invoice/invoicelist/" + gvst;
		// return this.process(model);
		// return "redirect:/visits/frontdesklist";

	}

	@RequestMapping(value = "/billdelete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/blank")
	public String confirmDeletebill(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") VisitPresentingComplainCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("BILLR001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		BillInvoice billinvoice = this.billinvoiceBo.getBillInvoiceById(id);

		if (billinvoice != null
				&& (Double) billinvoice.getMfig().get("totpaymt") > 0) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot delete bill that has been paid");

			return "redirect:/invoice/invoicelist/"
					+ billinvoice.getVisitinv().getId();

		}

		billinvoice.setModifiedBy(userIdentity.getUsername());
		billinvoice.setModifiedDate(new GregorianCalendar().getTime());
		billinvoice.setDeleted(true);
		billinvoiceBo.update(billinvoice);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Bill Deleted");
		return "redirect:/invoice/invoicelist/"
				+ billinvoice.getVisitinv().getId();

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/invoicelist/{vid}")
	public String viewActionil(Model model, @PathVariable("vid") Integer vid,
			RedirectAttributes redirectAttributes) {

		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/visits/frontdesklist";
		}

		// List<BillInvoice> billinvoicelist = billinvoiceBo
		// .fetchAllByOrganisationbyVisit(vst);
		// List <BillInvoicePayment> billpaymentlist =
		// billinvoiceBo.fetchPaymentByOrganisationbyVisit(vst);

		Double crl = 0.00;
		Double gcrl = 0.00;
		Double pcrl = 0.00;

		SettingsAssignment setass1 = this.settingBo.fetchsettings(
				"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

		if (setass1 != null) {
			try {
				gcrl = Double.parseDouble(setass1.getSettings_value());
			} catch (Exception e)

			{
				gcrl = 0.00;

			}
		}

		try {

			Double vt = vst.getVisit().getPatientbillto().getCreditlimit()
					.doubleValue();

			if (vt != 0.00) {
				crl = vt;

			}

			else {
				crl = gcrl;
			}
		}

		catch (Exception e) {
			crl = gcrl;
		}

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		PaymentForm paymentForm = new PaymentForm();
		paymentForm.setEffectivedate(DateUtils.formatDateToString(new Date(
				System.currentTimeMillis()), "yyyy-MM-dd hh:mm"));
		paymentForm.setVisitId(vst.getVisit().getId());
		paymentForm.setVisitinvId(vst.getId());

		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
		// model.addAttribute("mfig",getsum(vst));
		vform.setVisitId(vst.getVisit().getId());
		vform.setVisitinvId(vst.getId());

		// paymentForm.setVisitId(vst.getId());

		// paymentForm.setBillinvoiceId(inv.getId());

		// model.addAttribute("mfig",getsum(vst));

		model.addAttribute("paymentForm", paymentForm);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);

		// model.addAttribute("invoicelist",billinvoicelist);
		// model.addAttribute("billpaymentlist",billpaymentlist);

		model.addAttribute("crl", crl);
		model.addAttribute("v", vst);
		// model.addAttribute("billlist", billinvoicelist);
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());

		return "invoice/invoicelistbyvisittab";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/invoicelistglobal/{vid}")
	public String viewActionilglo(Model model,
			@PathVariable("vid") Integer vid,
			RedirectAttributes redirectAttributes) {

		Visit vst = this.visitBo.getVisitationById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/visits/frontdesklist";
		}

		// List<BillInvoice> billinvoicelist = billinvoiceBo
		// .fetchAllByOrganisationbyVisit(vst);
		// List <BillInvoicePayment> billpaymentlist =
		// billinvoiceBo.fetchPaymentByOrganisationbyVisit(vst);

		Double crl = 0.00;
		Double gcrl = 0.00;
		Double pcrl = 0.00;

		SettingsAssignment setass1 = this.settingBo.fetchsettings(
				"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

		if (setass1 != null) {
			try {
				gcrl = Double.parseDouble(setass1.getSettings_value());
			} catch (Exception e)

			{
				gcrl = 0.00;

			}
		}

		try {

			Double vt = vst.getPatientbillto().getCreditlimit().doubleValue();

			if (vt != 0.00) {
				crl = vt;

			}

			else {
				crl = gcrl;
			}
		}

		catch (Exception e) {
			crl = gcrl;
		}

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		PaymentForm paymentForm = new PaymentForm();
		paymentForm.setEffectivedate(DateUtils.formatDateToString(new Date(
				System.currentTimeMillis()), "yyyy-MM-dd hh:mm"));
		paymentForm.setVisitId(vst.getId());
		paymentForm.setVisitinvId(0);

		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
		// model.addAttribute("mfig",getsum(vst));
		vform.setId(vid);
		// paymentForm.setVisitId(vst.getId());

		// paymentForm.setBillinvoiceId(inv.getId());

		// model.addAttribute("mfig",getsum(vst));

		model.addAttribute("paymentForm", paymentForm);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);

		// model.addAttribute("invoicelist",billinvoicelist);
		// model.addAttribute("billpaymentlist",billpaymentlist);

		model.addAttribute("crl", crl);
		model.addAttribute("v", vst);
		// model.addAttribute("billlist", billinvoicelist);
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());

		return "invoice/invoicelistglobalbyvisittab";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/visitinvoicelist/{vid}", method = RequestMethod.GET)
	public String viewActionalvslst(@PathVariable("vid") Integer vid,
			Model model,

			RedirectAttributes redirectAttributes) {

		Visit vst = this.visitBo.getVisitationById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/visits/frontdesklist";
		}

		Double crl = 0.00;
		Double gcrl = 0.00;
		Double pcrl = 0.00;

		SettingsAssignment setass1 = this.settingBo.fetchsettings(
				"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

		if (setass1 != null) {
			try {
				gcrl = Double.parseDouble(setass1.getSettings_value());
			} catch (Exception e)

			{
				gcrl = 0.00;

			}
		}

		try {

			Double vt = vst.getPatientbillto().getCreditlimit().doubleValue();

			if (vt != 0.00) {
				crl = vt;

			}

			else {
				crl = gcrl;
			}
		}

		catch (Exception e) {
			crl = gcrl;
		}

		model.addAttribute("crl", crl);
		model.addAttribute("v", vst);
		return "invoice/visitinvoicelist";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/visitinvoicenew/{vid}", method = RequestMethod.GET)
	public String viewActionalvslstnew(@PathVariable("vid") Integer vid,
			Model model,

			RedirectAttributes redirectAttributes) {

		Visit vst = this.visitBo.getVisitationById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/visits/frontdesklist";
		}

		
		 * if (invstatusBo.getTodayInvoice(vid).size() > 0) {
		 * alert.setAlert(redirectAttributes, Alert.DANGER,
		 * "You cannot raise another invoice until Tomorrow!!!"); return
		 * "redirect:/invoice/visitinvoicelist/" + vid; }
		 

		
		 * List<Visitoutstanding> lsv = this.visitBo.fetchoutbyvisit(vid);
		 * System.out.print("confused" + lsv.size()); if (lsv.size() > 0) {
		 * alert.setAlert(redirectAttributes, Alert.DANGER,
		 * "You still have an open invoice not fully paid!!!"); return
		 * "redirect:/invoice/visitinvoicelist/" + vid; }
		 

		VisitInvoice visitinv = new VisitInvoice();
		visitinv.setCode(new AutoGenerate().mygen());
		visitinv.setComment("");
		visitinv.setVisit(vst);
		visitinv.setCreatedate(new Date(System.currentTimeMillis()));
		visitinv.setCreateuser(userIdentity.getUser());
		visitinv.setEffectiveDate(new Date(System.currentTimeMillis()));
		visitinv.setOrganisation(userIdentity.getOrganisation());
		VisitInvoice vinv = this.billinvoiceBo.save(visitinv);
		// get the new bill invoice save id and add it to the redirect stuff
		// down
		// model.addAttribute("crl", crl);
		// model.addAttribute("v", vst);
		// return "redirect:/invoice/invoicelist/" + vinv.getId();
		return "redirect:/invoice/visitinvoicelist/" + vid;

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/invoicepaymentlist/{vid}")
	public String viewActionilinvb(Model model,
			@PathVariable("vid") Integer vid,
			RedirectAttributes redirectAttributes) {

		BillInvoice inv = this.billinvoiceBo.getBillInvoiceById(vid);

		if (null == inv) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/visits/frontdesklist";
		}

		// List <BillInvoice> billinvoicelist =
		// billinvoiceBo.fetchAllByOrganisationbyVisit(vst);
		// List <BillInvoicePayment> billpaymentlist =
		// billinvoiceBo.fetchPaymentByOrganisationbyVisit(vst);

		Double crl = 0.00;
		Double gcrl = 0.00;
		Double pcrl = 0.00;

		SettingsAssignment setass1 = this.settingBo.fetchsettings(
				"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

		if (setass1 != null) {
			try {
				gcrl = Double.parseDouble(setass1.getSettings_value());
			} catch (Exception e)

			{
				gcrl = 0.00;

			}
		}

		try {

			Double vt = inv.getVisitinv().getVisit().getPatientbillto()
					.getCreditlimit().doubleValue();

			if (vt != 0.00) {
				crl = vt;

			}

			else {
				crl = gcrl;
			}
		}

		catch (Exception e) {
			crl = gcrl;
		}
		model.addAttribute("crl", crl);

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		PaymentForm paymentForm = new PaymentForm();
		paymentForm.setVisitId(inv.getVisitinv().getVisit().getId());
		paymentForm.setVisitinvId(inv.getVisitinv().getId());

		paymentForm.setBillinvoiceId(inv.getId());

		// model.addAttribute("mfig",getsum(vst));

		model.addAttribute("paymentForm", paymentForm);
		model.addAttribute("prescribedSearch", prescribedSearch);
		// model.addAttribute("invoicelist",billinvoicelist);
		// model.addAttribute("billpaymentlist",billpaymentlist);
		model.addAttribute("v", inv);
		model.addAttribute("paymodelist", this.paymodeBo.fetchAll());

		return "invoice/paymentlistbyvisittab";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/invoicelist")
	public String viewActionil(Model model,
			RedirectAttributes redirectAttributes) {
		
		 * Visit vst = this.visitBo.getVisitationById(vid);
		 * 
		 * if (null == vst) { alert.setAlert(redirectAttributes, Alert.DANGER,
		 * "Error! Invalid Visit"); return "redirect:/visits/frontdesklist"; }
		 

		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/invoice/visitlist");
			return "redirect:/visits/queue/10";
		}

		List<BillInvoice> billinvoicelist = billinvoiceBo
				.fetchAllByOrganisation(userIdentity.getOrganisation());

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		model.addAttribute("hmolist",
				this.HmoBo.fetchAll(userIdentity.getOrganisation()));
		model.addAttribute("invoicelist", billinvoicelist);
		// model.addAttribute("v",vst);

		model.addAttribute("prescribedSearch", prescribedSearch);

		return "invoice/invoicelist";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/invoicelist", method = RequestMethod.POST)
	public String viewActionil(
			Model model,
			@ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		 * Visit vst = this.visitBo.getVisitationById(vid);
		 * 
		 * if (null == vst) { alert.setAlert(redirectAttributes, Alert.DANGER,
		 * "Error! Invalid Visit"); return "redirect:/visits/frontdesklist"; }
		 

		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/invoice/visitlist");
			return "redirect:/visits/queue/10";
		}

		List<BillInvoice> billinvoicelist = billinvoiceBo
				.fetchAllByOrganisationbyparam(userIdentity.getOrganisation(),
						DateUtils.formatStringToDate(prescribedSearchForm
								.getDat1()), DateUtils
								.formatStringToDate(prescribedSearchForm
										.getDat2()), prescribedSearchForm
								.getHmoId());

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		model.addAttribute("invoicelist", billinvoicelist);
		// model.addAttribute("v",vst);
		model.addAttribute("hmolist",
				this.HmoBo.fetchAll(userIdentity.getOrganisation()));
		model.addAttribute("prescribedSearch", prescribedSearch);

		return "invoice/invoicelist";
	}

	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/visitlist")
	public String viewAction3(Model model, RedirectAttributes redirectAttributes) {

		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/invoice/visitlist");
			return "redirect:/visits/queue/10";
		}

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		// prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));

		cal.add(Calendar.DAY_OF_YEAR, +30);

		// prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));

		List<Visit> visitlist = this.visitBo
				.fetchAllByOrganisationmyqueueaccount(userIdentity
						.getOrganisation().getId());

		
		 * List<HrunitCategory> unitcategory = this.unitBo
		 * .fetchAllByOrganisationbyqueue();
		 
		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		// List<GlobalItemUnitofMeasure> itemunitmeasure = new ArrayList();

		// List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		// List<fffVisitStatus> billtolist = this.visitStatusBo.fetchAll();

		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);

		model.addAttribute("vlist", visitlist);
		// model.addAttribute("unitcategory", unitcategory);
		// model.addAttribute("statuslist", statuslist);
		// model.addAttribute("billtolist", billtolist);
		// model.addAttribute("itemunitmeasurelist", itemunitmeasure);
		// model.addAttribute("globalitemlist", globalitemlist);

		return "invoice/visitlist";
	}

	@RequestMapping(value = "/visitlist/listbyform", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction212(
			@Valid @ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedSearchForm,
			Model model, RedirectAttributes redirectAttributes) {

		if (userIdentity.getCurrentPointId() != 10) {
			userIdentity.setCurrentUrl("redirect:/invoice/visitlist");
			return "redirect:/visits/queue/10";
		}

		// List<HrunitCategory> unitcategory = this.unitBo
		// .fetchAllByOrganisationbyqueue();

		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		prescribedSearch.setDat1(prescribedSearchForm.getDat1());
		prescribedSearch.setDat2(prescribedSearchForm.getDat2());
		prescribedSearch.setStatusId(prescribedSearchForm.getStatusId());

		List<Visit> visitlist = visitBo
				.fetchAllByOrganisationmyqueuebyparamaccount(DateUtils
						.formatStringToDate(prescribedSearchForm.getDat1()),
						DateUtils.formatStringToDate(prescribedSearchForm
								.getDat2()), prescribedSearchForm.getStatusId());
		// List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		// List<GlobalItem> globalitemlist = this.globalitemBo
		// .fetchAllByKind("vsse");
		// model.addAttribute("globalitemlist", globalitemlist);
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		// model.addAttribute("statuslist", statuslist);
		model.addAttribute("vlist", visitlist);
		// model.addAttribute("unitcategory", unitcategory);

		// return "redirect:/";
		return "invoice/visitlist";

	}

	@RequestMapping(value = "/multiplepaymentsave", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveActionpaymultiple(
			@Valid @ModelAttribute("paymentForm") PaymentForm payform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(payform
				.getVisitinvId());

		// BillInvoice inv = this.billinvoiceBo.getBillInvoiceById(payform
		// .getBillinvoiceId());

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");

			return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();
		}

		Double tot = (Double) vst.getMfig().get("totpaymt")
				+ payform.getAmtpaid();
		if (tot > (Double) vst.getMfig().get("totcash")) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Payment Amount cannot be more than all Outstanding Bill Amount!!!");

			return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();

		}

		List<BillInvoice> invlist = this.billinvoiceBo
				.fetchAllByOrganisationbyVisitMulti(vst);

		Double payamountbal = payform.getAmtpaid();
		Double payamount = 0.00;
		String msg = "";
		String msgbase = "";
		// for (BillInvoice invl : vst.getBillinvoice()) {
		for (BillInvoice invl : invlist) {

			if (payamountbal >= (Double) invl.getMfig().get("totbal")) {
				payamount = (Double) invl.getMfig().get("totbal");

			} else {
				payamount = payamountbal;
			}

			if (payamount > 0) {

				if (payform.getPaymode_id().intValue() == 4) {

					Double pcrl = 0.00;
					Double gcrl = 0.00;
					Double crl = 0.00;

					try {
						pcrl = vst.getVisit().getPatientbillto()
								.getCreditlimit().doubleValue();
					} catch (Exception e) {
						pcrl = 0.00;
					}

					SettingsAssignment setass = this.settingBo.fetchsettings(
							"CREDIT_LIMIT", userIdentity.getOrganisation()
									.getId());

					if (setass != null) {
						try {
							gcrl = Double.parseDouble(setass
									.getSettings_value());
						} catch (Exception e)

						{
							gcrl = 0.00;

						}
					}

					try {

						Double vt = vst.getVisit().getPatientbillto()
								.getCreditlimit().doubleValue();
						if (vt != 0.00) {
							crl = vt;
						}

						else {
							crl = gcrl;
						}
					}

					catch (Exception e) {
						crl = gcrl;
					}

					if (((Double) invl.getVisitinv().getVisit()
							.getPatientbillto().getMfig().get("totcustbal") + payamount) > crl) {
						msg = msg + " Bill Id - " + invl.getId();
						msgbase = " Account Balance has Exceeded Credit Limit!! for the following bills ";
						continue;
						// return "redirect:/invoice/invoicelist/" +
						// payform.getVisitId();

						// return "redirect:/invoice/invoicepaymentlist/"
						// + payform.getBillinvoiceId();

						// move loop to next one

					}

					CustomerTransaction custtran = new CustomerTransaction();

					custtran.setEffectivedate(new Date(System
							.currentTimeMillis()));
					custtran.setDescription("Settlement of Bill id #"
							+ invl.getId() + "  Visit Invoice Code - "
							+ invl.getVisitinv().getCode() + " "
							+ payform.getDescription());
					custtran.setPatient(vst.getVisit().getPatientbillto());
					custtran.setDrcr("dr");
					custtran.setTrantype("Settlebill");
					custtran.setAmount(payamount);
					custtran.setTranrefno(invl.getId().toString());

					custtran.setCreatedBy(userIdentity.getUsername());
					custtran.setUser(userIdentity.getUser());
					custtran.setCreatedDate(new Date(System.currentTimeMillis()));
					// custtran.setCreatedDate(new
					// GregorianCalendar().getTime());
					custtran.setOrganisation(userIdentity.getOrganisation());

					custtran.setPaymode(this.paymodeBo
							.getPaymodeTypeById(payform.getPaymode_id()));
					custtran.setDeleted(false);

					this.customerTranBo.save(custtran);

				} else {

					
					 * CustomerTransaction custtran = new CustomerTransaction();
					 * 
					 * custtran.setEffectivedate(new Date(System
					 * .currentTimeMillis()));
					 * custtran.setDescription("Settlement of Bill id #" +
					 * invl.getId() + "  Visit Code - " +
					 * invl.getVisit().getCode() + " " +
					 * payform.getDescription());
					 * custtran.setPatient(vst.getPatientbillto());
					 * custtran.setDrcr("cr");
					 * custtran.setTrantype("Settlebill");
					 * custtran.setAmount(-payamount);
					 * custtran.setTranrefno(invl.getId().toString());
					 * 
					 * custtran.setCreatedBy(userIdentity.getUsername());
					 * custtran.setUser(userIdentity.getUser());
					 * custtran.setCreatedDate(new
					 * Date(System.currentTimeMillis())); //
					 * custtran.setCreatedDate(new //
					 * GregorianCalendar().getTime());
					 * custtran.setOrganisation(userIdentity.getOrganisation());
					 * 
					 * custtran.setPaymode(this.paymodeBo
					 * .getPaymodeTypeById(payform.getPaymode_id()));
					 * custtran.setDeleted(false);
					 * 
					 * this.customerTranBo.save(custtran);
					 
				}
				BillInvoicePayment billpay = new BillInvoicePayment();

				billpay.setEffectivedate(new GregorianCalendar().getTime());
				billpay.setDescription(invl.getGlobalitem().getGlobalitemName());
				// billpay.setDescription(payform.getDescription());

				billpay.setAmtpaid(payamount);

				billpay.setCreatedBy(userIdentity.getUsername());
				billpay.setCreateuser(userIdentity.getUser());
				billpay.setCreatedDate(new GregorianCalendar().getTime());
				billpay.setOrganisation(userIdentity.getOrganisation());
				billpay.setBillInv(invl);
				billpay.setPayer(vst.getVisit().getPatientbillto());
				billpay.setPaymode(this.paymodeBo.getPaymodeTypeById(payform
						.getPaymode_id()));

				billpay.setDeleted(false);

				this.billinvoiceBo.save(billpay);

				payamountbal = payamountbal - payamount;
			}

		}

		if (msgbase.equalsIgnoreCase("")) {
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Payment created succesfully!! " + msgbase + " " + msg);
		} else {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Payment created Partly Succesfully!! " + msgbase + " "
							+ msg);
		}

		return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();
		// return "redirect:/invoice/invoicepaymentlist/"
		// + payform.getBillinvoiceId();

	}

	@RequestMapping(value = "/paymentsave", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveActionpay(
			@Valid @ModelAttribute("paymentForm") PaymentForm payform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(payform
				.getVisitinvId());

		BillInvoice inv = this.billinvoiceBo.getBillInvoiceById(payform
				.getBillinvoiceId());

		if (null == vst || null == inv) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");

			return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();

		}

		Double tot = (Double) inv.getMfig().get("totpaymt")
				+ payform.getAmtpaid();
		if (tot > inv.getCashamt()) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Payment cannot be more than Bill Amount!!!");
			return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();

			// return "redirect:/invoice/invoicepaymentlist/"
			// + payform.getBillinvoiceId();

		}

		if (payform.getPaymode_id().intValue() == 4) {

			Double pcrl = 0.00;
			Double gcrl = 0.00;
			Double crl = 0.00;

			try {
				pcrl = vst.getVisit().getPatientbillto().getCreditlimit()
						.doubleValue();
			} catch (Exception e) {
				pcrl = 0.00;
			}

			SettingsAssignment setass = this.settingBo.fetchsettings(
					"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

			if (setass != null) {
				try {
					gcrl = Double.parseDouble(setass.getSettings_value());
				} catch (Exception e)

				{
					gcrl = 0.00;

				}
			}

			try {

				Double vt = inv.getVisitinv().getVisit().getPatientbillto()
						.getCreditlimit().doubleValue();
				if (vt != 0.00) {
					crl = vt;
				}

				else {
					crl = gcrl;
				}
			}

			catch (Exception e) {
				crl = gcrl;
			}

			if (((Double) inv.getVisitinv().getVisit().getPatientbillto()
					.getMfig().get("totcustbal") + payform.getAmtpaid()) > crl) {

				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Account Balance has Exceeded Credit Limit!!");
				return "redirect:/invoice/invoicelist/"
						+ payform.getVisitinvId();
				// return "redirect:/invoice/invoicepaymentlist/"
				// + payform.getBillinvoiceId();

			}

			CustomerTransaction custtran = new CustomerTransaction();

			custtran.setEffectivedate(new Date(System.currentTimeMillis()));
			custtran.setDescription("Settlement of Bill id #"
					+ payform.getBillinvoiceId() + "  Visit Invoice Code - "
					+ inv.getVisitinv().getCode() + " "
					+ payform.getDescription());
			custtran.setPatient(vst.getVisit().getPatientbillto());
			custtran.setDrcr("dr");
			custtran.setTrantype("Settlebill");
			custtran.setAmount(payform.getAmtpaid());
			custtran.setTranrefno(inv.getId().toString());

			custtran.setCreatedBy(userIdentity.getUsername());
			custtran.setUser(userIdentity.getUser());
			custtran.setCreatedDate(new Date(System.currentTimeMillis()));
			// custtran.setCreatedDate(new GregorianCalendar().getTime());
			custtran.setOrganisation(userIdentity.getOrganisation());

			custtran.setPaymode(this.paymodeBo.getPaymodeTypeById(payform
					.getPaymode_id()));
			custtran.setDeleted(false);

			this.customerTranBo.save(custtran);

		}

		else

		{
			
			 * CustomerTransaction custtran = new CustomerTransaction();
			 * 
			 * custtran.setEffectivedate(new Date(System.currentTimeMillis()));
			 * custtran.setDescription("Settlement of Bill id #" +
			 * payform.getBillinvoiceId() + "  Visit Code - " + vst.getCode() +
			 * " " + payform.getDescription());
			 * custtran.setPatient(vst.getPatientbillto());
			 * custtran.setDrcr("cr"); custtran.setTrantype("Settlebill");
			 * custtran.setAmount(-payform.getAmtpaid());
			 * custtran.setTranrefno(inv.getId().toString());
			 * 
			 * custtran.setCreatedBy(userIdentity.getUsername());
			 * custtran.setUser(userIdentity.getUser());
			 * custtran.setCreatedDate(new Date(System.currentTimeMillis())); //
			 * custtran.setCreatedDate(new GregorianCalendar().getTime());
			 * custtran.setOrganisation(userIdentity.getOrganisation());
			 * 
			 * custtran.setPaymode(this.paymodeBo.getPaymodeTypeById(payform
			 * .getPaymode_id())); custtran.setDeleted(false);
			 * 
			 * this.customerTranBo.save(custtran);
			 

		}

		BillInvoicePayment billpay = new BillInvoicePayment();

		
		 * billpay.setEffectivedate(DateUtils.formatStringToDate(payform
		 * .getEffectivedate())); /*
		 
		billpay.setEffectivedate(new GregorianCalendar().getTime());
		billpay.setDescription(payform.getDescription());
		// billpay.setDescription(payform.getDescription());

		billpay.setAmtpaid(payform.getAmtpaid());

		billpay.setCreatedBy(userIdentity.getUsername());
		billpay.setCreateuser(userIdentity.getUser());
		billpay.setCreatedDate(new GregorianCalendar().getTime());
		billpay.setOrganisation(userIdentity.getOrganisation());
		billpay.setVisit(vst.getVisit());
		billpay.setBillInv(inv);
		billpay.setPayer(vst.getVisit().getPatientbillto());

		billpay.setPaymode(this.paymodeBo.getPaymodeTypeById(payform
				.getPaymode_id()));

		billpay.setDeleted(false);

		this.billinvoiceBo.save(billpay);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Payment created succesfully!!!");
		// return "redirect:/invoice/invoicelist/" + payform.getVisitId();
		return "redirect:/invoice/invoicelist/" + payform.getVisitinvId();

	}

	@RequestMapping(value = "/paymentdelete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/blank")
	public String confirmDelete(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") VisitPresentingComplainCategoryForm cForm,
			RedirectAttributes redirectAttributes) {

		return null;

		// BillInvoicePayment billpay = this.billinvoiceBo.getPaymentbyId(id);

		// billpay.setModifiedBy(userIdentity.getUsername());
		// billpay.setModifiedDate(new GregorianCalendar().getTime());
		// billpay.setDeleted(true);
		// billinvoiceBo.update(billpay);
		// alert.setAlert(redirectAttributes, Alert.SUCCESS,
		// "Success! Payment Deleted");
		// /// return "redirect:/invoice/invoicelist/" +
		// billpay.getVisit().getId();

		// return "redirect:/invoice/invoicepaymentlist/"
		// + billpay.getBillInv().getId();
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/chargelist/{id}")
	public String viewActionicl(Model model, @PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		BillInvoice inv = this.billinvoiceBo.getBillInvoiceById(id);

		// if (null == inv) {
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid Visit");
		// return "redirect:/invoice/accountlist";
		// }

		InvoiceBill_ChargetoForm invoicechargeform = new InvoiceBill_ChargetoForm();

		// invoiceform.setVisitId(vst.getId());
		// model.addAttribute("patienthmolist",
		// this.billinvoiceBo.GetPatientHmoList(vst.getId(),userIdentity.getOrganisation().getId()));
		// model.addAttribute("billitemlist",
		// this.billinvoiceBo.GetBillItembyPointList(vst.getId(),userIdentity.getOrganisation().getId()));

		model.addAttribute("v", inv);
		// model.addAttribute("v",vst);
		model.addAttribute("invoicechargeForm", invoicechargeform);

		return "invoice/chargelist";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/charge/{id}")
	public String viewAction4(Model model, @PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		Visit vst = this.visitBo.getVisitationById(id);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit");
			return "redirect:/invoice/accountlist";
		}

		InvoiceBillForm invoiceform = new InvoiceBillForm();

		// invoiceform.setVisitId(vst.getId());

		return "invoice/add";
	}

	@RequestMapping(value = "/getinvoice/{vid}/{qty}/{itemmeasureid}/{amt}/{unitid}/{orgid}/{usehmo}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory(@PathVariable("vid") String vid,
			@PathVariable("qty") String qty,
			@PathVariable("itemmeasureid") String itemmeasureid,
			@PathVariable("amt") String amt,
			@PathVariable("unitid") String unitid,
			@PathVariable("orgid") String orgid,
			@PathVariable("usehmo") String usehmo, Model model,
			RedirectAttributes redirectAttributes) {

		String err = "0";
		Double myinvamt = 0.00;
		Double myvatamt = 0.00;

		Double mycashamt = 0.00;
		Double myhmoamt = 0.00;
		String mypackage = "";
		String myhmo = "";
		String myscheme = "";

		Gson gsonObj = new Gson();
		Map<Object, Object> map = new HashMap<Object, Object>();

		try {

			Invoice invoice = this.billinvoiceBo.Getinvoice(
					Integer.parseInt(vid), Integer.parseInt(qty),
					Integer.parseInt(itemmeasureid), Double.parseDouble(amt),
					Integer.parseInt(unitid),
					this.orgBo.getOrganisationById(Integer.parseInt(orgid)),

					Boolean.parseBoolean(usehmo));

			if (null == invoice) {
				err = "1";
				myinvamt = 0.00;
				mycashamt = 0.00;
				myhmoamt = 0.00;
				myvatamt = 0.00;
				mypackage = "";
				myhmo = "";
				myscheme = "";

			} else

			{

				err = "0";
				myinvamt = invoice.getInvamt();
				mycashamt = invoice.getCashamt();
				myhmoamt = invoice.getHmoamt();
				myvatamt = invoice.getVatamt();
				mypackage = invoice.getHpname();
				myhmo = invoice.getHmoname();
				myscheme = invoice.getBillschemename();

			}

		}

		catch (Exception e) {
			err = "0";
			myinvamt = 0.00;
			mycashamt = 0.00;
			myhmoamt = 0.00;
			mypackage = "";
			myhmo = "";
			myvatamt = 0.00;
			myscheme = "";
		}

		map.put("err", err);
		map.put("invamt", myinvamt);
		map.put("vatamt", myvatamt);

		map.put("cashamt", mycashamt);
		map.put("hmoamt", myhmoamt);
		map.put("hmoname", myhmo);
		map.put("hmopackage", mypackage);
		map.put("hmoscheme", myscheme);
		return (String) gsonObj.toJson(map);

	}

	@RequestMapping(value = "/getamount/{visitid}/{referenceid}/{code}/{itemmeasureid}/{qty}/{amt}/{itemid}/{unitid}/{patienthmopackageid}/{billschemeid}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory(@PathVariable("visitid") Integer visitid,
			@PathVariable("referenceid") String referenceid,
			@PathVariable("code") String code,
			@PathVariable("itemmeasureid") String itemmeasureid,
			@PathVariable("qty") String qty, @PathVariable("amt") String amt,
			@PathVariable("itemid") String itemid,
			@PathVariable("unitid") String unitid,
			@PathVariable("patienthmopackageid") String patienthmopackageid,
			@PathVariable("billschemeid") String billschemeid, Model model,
			RedirectAttributes redirectAttributes) {

		try {
			if (amt.equalsIgnoreCase("null"))

			{
				BillSchemeMeasurePrice bsmp = this.billschememeasurepriceBo
						.getBillSchemeMeasurePriceBySchemeandItemmeasure(
								Integer.parseInt(billschemeid),
								Integer.parseInt(itemmeasureid));

				if (bsmp == null) {
					amt = "0.0";
				} else {
					amt = Double.toString(bsmp.getPrice()
							* Double.parseDouble(URLDecoder.decode(qty)));

				}

			}

			else {

				// else stuff

			}

		}

		catch (Exception e) {

			amt = "0.00";

		}

		return referenceid + "##" + code + "##" + itemmeasureid + "##"
				+ URLDecoder.decode(qty) + "##" + amt + "##" + itemid + "##"
				+ unitid + "##" + patienthmopackageid + "##" + billschemeid;

		// +"##" + hmoamt + "##" + cashamt;

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/raisepham/{vid}/{measureid}/{qty}", method = RequestMethod.GET)
	public String viewActionallitempham(@PathVariable("vid") Integer vid,
			@PathVariable("measureid") Integer measureid,
			@PathVariable("qty") Integer qty, Model model,

			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("BILLR001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		// if (userIdentity.getCurrentPointId() != 10) {
		// userIdentity
		// .setCurrentUrl("redirect:/invoice/raiseinvoiceallitempham/"
		// + vid);
		// return "redirect:/visits/queue/10";
		// }

		Double gcrl = 0.00;
		Double pcrl = 0.00;
		Double crl = 0.00;

		// System.out.print("vstsearchraise" + vfc.getVisitId());
		// VisitInvoice vst = this.visitBo.getVisitationInvoiceById(vid);
		Visit vst = this.visitBo.getVisitationById(vid);

		if (null == vst) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Visit Invoice");
			// System.out.print("vstsearchraiserror" + vst.getId());

			return this.process(model);

		}

		List<Visitoutstanding> vout = this.invstatusBo
				.getOutstandingInvoice(vid);
		System.out.print("vout size" + vout.size());
		if (vout.size() == 0) {
			System.out.print("vout is zero" + vout.size());

			VisitInvoice visitinv = new VisitInvoice();
			visitinv.setCode(new AutoGenerate().mygen());
			visitinv.setComment("");
			visitinv.setVisit(vst);
			visitinv.setCreatedate(new Date(System.currentTimeMillis()));
			visitinv.setCreateuser(userIdentity.getUser());
			visitinv.setEffectiveDate(new Date(System.currentTimeMillis()));
			visitinv.setOrganisation(userIdentity.getOrganisation());
			VisitInvoice vinv = this.billinvoiceBo.save(visitinv);
			// get the new bill invoice save id and add it to the redirect stuff
			// down
			// model.addAttribute("crl", crl);
			// model.addAttribute("v", vst);
			return "redirect:/invoice/raiseinvoiceitempham/" + vinv.getId()
					+ "/" + measureid + "/" + qty;

		} else {
			System.out.print("vout is not zero" + vout.size());

			return "redirect:/invoice/raiseinvoiceitempham/"
					+ vout.get(0).getVisitinvid() + "/" + measureid + "/" + qty;

		}
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/printinvoice/{id}")
	public String viewActionprintinvoice(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		VisitInvoice vst = this.visitBo.getVisitationInvoiceById(id);

		if (null == vst) {
			return null;
		}
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid Visit");
		// return "redirect:/invoice/accountlist";
		// }

		// List<BillingListReport> billingListReport = billingListDao
		// .fetchAllBillById(id);

		// List<BillInvoice> billingListReport = this.billinvoiceBo
		// .fetchAllByOrganisationbyVisit(vst);

		// System.out.println("what is the size" + billingListReport.size());

		// model.addAttribute("billreport", billingListReport);
		// List<BillInvoice> billlist = this.billingListDao
		// .fetchAllBillvisitrec(id);

		model.addAttribute("printtime", new Date(System.currentTimeMillis()));
		model.addAttribute("v", vst);
		return "invoice/printreceipt";
	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/printinvoiceglobal/{id}")
	public String viewActionprintinvoiceglobal(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {

		Visit vst = this.visitBo.getVisitationById(id);

		if (null == vst) {
			return null;
		}
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid Visit");
		// return "redirect:/invoice/accountlist";
		// }

		// List<BillingListReport> billingListReport = billingListDao
		// .fetchAllBillById(id);

		// List<BillInvoice> billingListReport = this.billinvoiceBo
		// .fetchAllByOrganisationbyVisit(vst);

		// System.out.println("what is the size" + billingListReport.size());

		// model.addAttribute("billreport", billingListReport);
		// List<BillInvoice> billlist = this.billingListDao
		// .fetchAllBillvisitrec(id);

		model.addAttribute("printtime", new Date(System.currentTimeMillis()));
		model.addAttribute("v", vst);
		return "invoice/printreceiptglobal";

	}

	@SuppressWarnings("unused")
	@Layout(value = "layouts/blank")
	@RequestMapping(value = "/printinvoicep/{id}")
	public String viewActionprintinvoicepw(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) throws IOException,
			DocumentException {

		Visit vst = this.visitBo.getVisitationById(id);

		// if (null == vst) {
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid Visit");
		// return "redirect:/invoice/accountlist";
		// }
		model.addAttribute("v", vst);

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("name", "v");

		// Get the plain HTML with the resolved ${name} variable!
		String html = templateEngine.process("template", context);
		OutputStream outputStream = new FileOutputStream("message.pdf");
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(html);
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();

		return null;
	}

	private Map<String, String> getInvoiceData(String p)

	{

		Map<String, String> invdata = new HashMap<String, String>();

		if (p.equals("1")) {
			invdata.put("Qty", "1");
		}

		return invdata;

	}

	public String process(Model model) {

		System.out.print("adeprocess" + userIdentity.getCurrentPointId());
		if (userIdentity.getCurrentPointId() == 1) {
			return "redirect:/visits/frontdesklist";
		}

		if (userIdentity.getCurrentPointId() == 2) {
			return "redirect:/visits/vitallist";

		}

		if (userIdentity.getCurrentPointId() == 3) {
			return "redirect:/visits/consultationlist";

		}

		if (userIdentity.getCurrentPointId() == 4) {
			return "redirect:/visits/injectionlist";

		}

		if (userIdentity.getCurrentPointId() == 5) {
			return "redirect:/visits/druglist";

		}

		if (userIdentity.getCurrentPointId() == 6) {
			return "redirect:/visits/labtestlist";

		}

		if (userIdentity.getCurrentPointId() == 7) {
			return "redirect:/visits/surgerylist";

		}

		if (userIdentity.getCurrentPointId() == 8) {
			return "redirect:/visits/xraylist";

		}

		if (userIdentity.getCurrentPointId() == 9) {
			return "redirect:/visits/admissionlist";

		}

		if (userIdentity.getCurrentPointId() == 10) {
			return "redirect:/invoice/invoicelist/" + gvst;

		}

		return "redirect:/";

	}

}
*/