package org.calminfotech.report.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.boInterface.PatientTelephoneBo;/*
import org.calminfotech.patient.forms.PatientreportsearchForm;*/
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.report.models.BillingInvoiceSum;
import org.calminfotech.report.models.BillingListReport;
import org.calminfotech.report.utils.BillingInvoiceSumDao;
import org.calminfotech.report.utils.BillingListDao;
import org.calminfotech.report.utils.BillingListbyHmoDao;
import org.calminfotech.report.utils.BillingListbyUnitDao;
import org.calminfotech.report.utils.BilllistbypatientDao;
import org.calminfotech.report.utils.HmoListDao;
import org.calminfotech.report.utils.PatientList;
import org.calminfotech.report.utils.PatientListbyHmoDao;
import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AddressTypeList;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.HistoryTypeList;
import org.calminfotech.utils.HmostatusList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.PhoneTypeList;
import org.calminfotech.utils.RelationshipTypeList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.SurgicalList;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/billingreport")
public class BillingReportController {
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private Authorize authorize;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private MaritalStatusList MSList;

	@Autowired
	private OccupationList occuList;

	@Autowired
	private BloodgroupList groupList;

	@Autowired
	private BloodgenotypeList genoList;

	@Autowired
	private LifestatusList lifeList;

	@Autowired
	private HmostatusList hmoList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private HrunitCategoryBo hrunitCategoryBo;

	/*
	 * @Autowired private EhmoBo ehmoBo;
	 */

	@Autowired
	private PhoneTypeList telephonetypeBo;

	@Autowired
	private AddressTypeList addresstypeBo;

	@Autowired
	private HistoryTypeList historytypeBo;

	@Autowired
	private PatientHmoBo patientHmoBo;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private LanguageBo languageBo;

	@Autowired
	private GenderList genderBo;

	@Autowired
	private SurgicalList surgicalList;

	@Autowired
	private PatientDocumentBo patientDocumentBo;

	@Autowired
	private HmoPackageBo hmoPackageBo;

	@Autowired
	private PatientHistoryBo patientMedicalHistoryBo;

	@Autowired
	private PatientFamilyHistoryBo patientFamilyHistoryBo;

	@Autowired
	private PatientTelephoneBo patienttelephoneBo;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	@Autowired
	private PatientCodeGenerator patientCodeGenerator;

	@Autowired
	private PatientTelephoneBo patientTelephoneBo;

	@Autowired
	private RelationshipTypeList relationshipTypeBo;

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private PatientList patientListBo;

	@Autowired
	private BillingListDao billingListDao;

	@Autowired
	private PatientListbyHmoDao patientListbyHmoDao;

	@Autowired
	private BillingListbyHmoDao bListbyHmoDao;

	@Autowired
	private BillingListbyUnitDao bListbyUnitDao;

	@Autowired
	private BilllistbypatientDao billpatientDao;

	@Autowired
	private HmoListDao hmoListDao;

	@Autowired
	private BillingInvoiceSumDao billingInvoiceSumDao;

	// this is to show invoice / bill list by due date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchbill")
	public String searchBillbyDueDateform(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();*/

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);
/*
		model.addAttribute("PRSForm", PRSForm);*/

		return "report/billing/billlistsearch";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allbilllist/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchBillREsult(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		System.out.println("My first date " + datefrom);
		System.out.println("My second  date " + dateto);

		System.out.print("yesyes"
				+ DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));

		List<BillingListReport> billingListReport = billingListDao
				.fetchAllBill(DateUtils.formatStringToDate(datefrom,
						"yyyy-MM-dd hh:mm"), DateUtils.formatStringToDate(
						dateto, "yyyy-MM-dd hh:mm"));

		System.out.println("what is the size" + billingListReport.size());

		model.addAttribute("billreport", billingListReport);
		model.addAttribute("totals", billingListDao.getCountsum(
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd HH:mm"),
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd HH:mm")));

		// for the header sake

		model.addAttribute("datefrom",
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));
		model.addAttribute("dateto",
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));
		/*
		 * int r; if (r == 0) { }
		 */
		return "report/billing/billlistprint";

	}

	// this is to show patient list by HMO form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchbillbyHMO")
	public String billbyHMoreport(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();*/

		model.addAttribute("billhmoList", this.bListbyHmoDao.fetchAll());/*
		model.addAttribute("PRSForm", PRSForm);*/

		return "report/billing/billlistbyhmosearch";
	}

	// this is to show patient list by HMO result in table fromat
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allblistbyHMO/{hmoId}/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchbillbyHmo(@PathVariable("hmoId") Integer hmoId,
			@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		System.out.println("My first date " + hmoId);

		List<BillingListReport> bListbyHmoresult = bListbyHmoDao
				.fetchAllBillbyHmo(hmoId, DateUtils.formatStringToDate(
						datefrom, "yyyy-MM-dd hh:mm"), DateUtils
						.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		model.addAttribute("billreport", bListbyHmoresult);
		model.addAttribute("totals", bListbyHmoDao.getCountsum(hmoId,
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"),
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm")));

		// for the header sake
		if (bListbyHmoresult.size() > 0) {
			model.addAttribute("hmohold", bListbyHmoresult.get(0).getHmoname());
		}

		model.addAttribute("datefrom",
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));
		model.addAttribute("dateto",
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		return "report/billing/billlistbyhmoprint";

	}

	// this is to show patient list by HMO form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchbillbyUnit")
	public String billbyUnitreport(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();*/

		model.addAttribute(
				"staffunit",
				this.hrunitCategoryBo.fetchAll(userIdentity.getOrganisation()
						.getOrgCoy().getId()));/*
		model.addAttribute("PRSForm", PRSForm);*/

		return "report/billing/billlistbyunitsearch";
	}

	// this is to show patient list by HMO result in table fromat
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allblistbyUnit/{unitId}/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchbillbyUnit(@PathVariable("unitId") Integer unitId,
			@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		System.out.println("My first date " + unitId);

		List<BillingListReport> billreport = bListbyUnitDao.fetchAllBillbyUnit(
				unitId,
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"),
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));
		model.addAttribute("totals", bListbyUnitDao.getCountsum(unitId,
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"),
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm")));

		model.addAttribute("billreport", billreport);

		// for the header sake
		if (billreport.size() > 0) {
			model.addAttribute("unithold", billreport.get(0).getCategory_name());
		}
		model.addAttribute("datefrom",
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));
		model.addAttribute("dateto",
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		return "report/billing/billlistbyunitprint";

	}

	// this is to show patient list by HMO form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchbillbypatient", method = RequestMethod.GET)
	public String billbypatientreport(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PXForm = new PatientreportsearchForm();*/

		/*// model.addAttribute("billpatientList", this.bListbyHmoDao.fetchAll());
		model.addAttribute("PXForm", PXForm);*/
		return "report/billing/billbypatientsearch";
	}

	// this is to show patient list by HMO result in table fromat
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allblistbypatient/{pId}/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String allblistbypatient(@PathVariable("pId") Integer pId,
			@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		System.out.println("My first date " + pId);

		List<BillingListReport> bListbypatientresult = billpatientDao
				.fetchAllBillbyPatient(pId, DateUtils.formatStringToDate(
						datefrom, "yyyy-MM-dd hh:mm"), DateUtils
						.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		model.addAttribute("billreport", bListbypatientresult);
		model.addAttribute("totals", billpatientDao.getCountsum(pId,
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"),
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm")));

		// for the header sake
		if (bListbypatientresult.size() > 0) {
			model.addAttribute("pname", bListbypatientresult.get(0)
					.getSurname()
					+ ' '
					+ bListbypatientresult.get(0).getFirstName());
		}
		model.addAttribute("datefrom",
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));
		model.addAttribute("dateto",
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		return "report/billing/billbypatientprint";

	}

	// this is to show invoice / bill list by due date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchinvoicesum")
	public String searchsumbyDueDateform(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm SUMForm = new PatientreportsearchForm();*/

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);/*

		model.addAttribute("SumForm", SUMForm);*/

		return "report/billing/invoicesumsearch";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allbillinvoicesumlist/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchBillPrint(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		/*
		 * System.out.println("My first date " +datefrom);
		 * System.out.println("My second  date " +dateto);
		 */
		List<BillingInvoiceSum> BillingInvoiceSum = this.billingInvoiceSumDao
				.fetchAllBillSum(DateUtils.formatStringToDate(datefrom,
						"yyyy-MM-dd hh:mm"), DateUtils.formatStringToDate(
						dateto, "yyyy-MM-dd hh:mm"));

		model.addAttribute("BillingInvoiceSum", BillingInvoiceSum);

		// for the header sake

		model.addAttribute("datefrom",
				DateUtils.formatStringToDate(datefrom, "yyyy-MM-dd hh:mm"));
		model.addAttribute("dateto",
				DateUtils.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));
		/*
		 * int r; if (r == 0) { }
		 */
		return "report/billing/invoicesumprint";

	}

}
