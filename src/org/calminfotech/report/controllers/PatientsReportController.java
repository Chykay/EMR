package org.calminfotech.report.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
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
import org.calminfotech.report.models.HmoListReport;
import org.calminfotech.report.models.PatientListbyHmo;
import org.calminfotech.report.models.PatientListing;
import org.calminfotech.report.utils.BillingListDao;
import org.calminfotech.report.utils.BillingListbyHmoDao;
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
@RequestMapping(value = "/patientsreport")
public class PatientsReportController {

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
	private HmoListDao hmoListDao;

	// this is to show patient list by registration date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/search")
	public String viewAction3(Model model, RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();*/

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);
/*
		model.addAttribute("PRSForm", PRSForm);*/

		return "report/patient/patientlistsearch";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allpatientlist/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchPatient(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		/*
		 * System.out.println("My first date " +datefrom);
		 * System.out.println("My second  date " +dateto);
		 */
		List<PatientListing> patientListresult = patientListBo.fetchAllPatient(
				DateUtils.formatStringToDate(datefrom),
				DateUtils.formatStringToDate(dateto));

		model.addAttribute("patientreport", patientListresult);

		// for the header sake
		model.addAttribute("datefrom", datefrom);
		model.addAttribute("dateto", dateto);

		return "report/patient/patientlistprint";

	}

	// this is to show patient list by HMO form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchbyHMO")
	public String patientbyHMoreport(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();*/

		model.addAttribute("hmoList", this.patientListbyHmoDao.fetchAll());/*
		model.addAttribute("PRSForm", PRSForm);*/

		return "report/patient/patientlistbyhmosearch";
	}

	// this is to show patient list by HMO result in table fromat
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allpatientlistbyHMO/{hmoId}", method = RequestMethod.GET)
	public String searchPatientbyHmo(@PathVariable("hmoId") Integer hmoId,
			Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {

		System.out.println("My HMO " + hmoId);

		List<PatientListbyHmo> patientListbyHmoresult = patientListbyHmoDao
				.fetchAllPatientbyHmo(hmoId);

		model.addAttribute("patientListbyHmoresult", patientListbyHmoresult);
		// patientListbyHmoresult.get(0).getHmoname();

		model.addAttribute("hmoname",
				patientListbyHmoresult != null ? patientListbyHmoresult.get(0)
						.getHmoname() : "");

		return "report/patient/patientlistbyhmoprint";

	}

	// this is to show hmo list by creation date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchallhmo")
	public String searchhmoform(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm HSFForm = new PatientreportsearchForm();
*/
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);
/*
		model.addAttribute("HSFForm", HSFForm);*/

		return "report/patient/hmolistsearch";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allhmolist/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String searchHMOREsult(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		/*
		 * System.out.println("My first date " +datefrom);
		 * System.out.println("My second  date " +dateto);
		 */
		List<HmoListReport> HmoListReport = this.hmoListDao.fetchAllHMO(
				DateUtils.formatStringToDate(datefrom),
				DateUtils.formatStringToDate(dateto));

		model.addAttribute("HmoListReport", HmoListReport);

		// for the header sake
		model.addAttribute("datefrom", datefrom);
		model.addAttribute("dateto", dateto);
		/*
		 * int r; if (r == 0) { }
		 */
		return "report/patient/hmolistprint";

	}
}
