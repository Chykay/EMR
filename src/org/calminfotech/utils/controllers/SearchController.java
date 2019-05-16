package org.calminfotech.utils.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hrunit.forms.PersonnelSearchForm;
import org.calminfotech.ledger.forms.CustAccSearchForm;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.system.forms.AdmissionSearchForm;
import org.calminfotech.system.forms.AllergySearchForm;
import org.calminfotech.system.forms.BillingSearchForm;
import org.calminfotech.system.forms.DiseasesSearchForm;
import org.calminfotech.system.forms.DrugSearchForm;
import org.calminfotech.system.forms.ExaminationSearchForm;
import org.calminfotech.system.forms.GlobalitemSearchForm;
import org.calminfotech.system.forms.InventorySearchForm;
import org.calminfotech.system.forms.LaboratorySearchForm;
import org.calminfotech.system.forms.SurgerySearchForm;
import org.calminfotech.system.forms.XraySearchForm;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private SearchUtility searchUtilBo;

	// search test
	@RequestMapping(value = { "/patientreportsearchwin" })
	@Layout("layouts/blank")
	public String searchblankrep(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		PatientSearchForm pf = new PatientSearchForm();
		pf.setMysp(0);
		model.addAttribute("patientSearch", pf);
		return "search/patientreportsearchwin";
	}

	@RequestMapping(value = { "/patientreportsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostrep(
			Model model,
			HttpSession session,
			@ModelAttribute("patientSearch") PatientSearchForm patientSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List patientList = searchUtilBo.searchPatientwin(patientSearchForm,
				session);

		model.addAttribute("patient", patientList);

		return "search/patientreportsearchwin";
	}

	// search test
	@RequestMapping(value = { "/patientsearchwin" })
	@Layout("layouts/blank")
	public String searchblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		PatientSearchForm pf = new PatientSearchForm();
		pf.setMysp(0);
		model.addAttribute("patientSearch", pf);
		return "search/patientsearchwin";
	}

	@RequestMapping(value = { "/patientsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("patientSearch") PatientSearchForm patientSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List patientList = searchUtilBo.searchPatientwin(patientSearchForm,
				session);

		model.addAttribute("patient", patientList);

		return "search/patientsearchwin";
	}

	// search test
	@RequestMapping(value = { "/appointmentpatientsearchwin" })
	@Layout("layouts/blank")
	public String searchblankapp(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		PatientSearchForm pf = new PatientSearchForm();
		pf.setMysp(0);
		model.addAttribute("patientSearch", pf);
		return "search/appointmentpatientsearchwin";
	}

	@RequestMapping(value = { "/appointmentpatientsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostapp(
			Model model,
			HttpSession session,
			@ModelAttribute("patientSearch") PatientSearchForm patientSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List patientList = searchUtilBo.searchPatientwin(patientSearchForm,
				session);

		model.addAttribute("patient", patientList);

		return "search/appointmentpatientsearchwin";
	}

	// search test
	@RequestMapping(value = { "/appointmentpatientsearchwin2" })
	@Layout("layouts/blank")
	public String searchblankapp2(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		PatientSearchForm pf = new PatientSearchForm();
		pf.setMysp(0);
		model.addAttribute("patientSearch", pf);
		return "search/appointmentpatientsearchwin2";
	}

	@RequestMapping(value = { "/appointmentpatientsearchwin2" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostapp2(
			Model model,
			HttpSession session,
			@ModelAttribute("patientSearch") PatientSearchForm patientSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List patientList = searchUtilBo.searchPatientwin(patientSearchForm,
				session);

		model.addAttribute("patient", patientList);

		return "search/appointmentpatientsearchwin2";
	}

	// search test
	@RequestMapping(value = { "/allergysearchwin" })
	@Layout("layouts/blank")
	public String searchallergyblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		AllergySearchForm pf = new AllergySearchForm();
		pf.setMysp(0);
		model.addAttribute("allergySearch", pf);
		return "search/allergysearchwin";
	}

	@RequestMapping(value = { "/allergysearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchallergyblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("allergySearch") AllergySearchForm allergySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List allergyList = searchUtilBo.searchAllergywin(allergySearchForm,
				session);

		model.addAttribute("allergy", allergyList);

		return "search/allergysearchwin";
	}

	// search test
	@RequestMapping(value = { "/personnelsearchwin" })
	@Layout("layouts/blank")
	public String searchpersonnelblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		PersonnelSearchForm pf = new PersonnelSearchForm();
		pf.setMysp(0);
		model.addAttribute("personnelSearch", pf);
		return "search/personnelsearchwin";
	}

	@RequestMapping(value = { "/personnelsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchpersonnelblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("personnelSearch") PersonnelSearchForm personnelSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List personnelList = searchUtilBo.searchPersonnelwin(
				personnelSearchForm, session);
		System.out.print("ggggggggg");
		System.out.print(personnelList.size());
		System.out.print("ggggggggg");

		model.addAttribute("personnel", personnelList);

		return "search/personnelsearchwin";
	}

	// search test
	@RequestMapping(value = { "/billingitemsearchwin" })
	@Layout("layouts/blank")
	public String searchbillingitemblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		BillingSearchForm pf = new BillingSearchForm();
		pf.setMysp(0);
		model.addAttribute("billingSearch", pf);
		// return "search/patientsearchwin";
		return "search/billingitemsearchwin";
	}

	@RequestMapping(value = { "/billingitemsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchbillingitemblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("billingSearch") BillingSearchForm billingitemSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List billingitemList = searchUtilBo.searchBillingitemwin(
				billingitemSearchForm, session);

		model.addAttribute("billingitem", billingitemList);

		return "search/billingitemsearchwin";
	}

	// search test
	@RequestMapping(value = { "/examinationsearchwin" })
	@Layout("layouts/blank")
	public String searchblankexam(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		ExaminationSearchForm pf = new ExaminationSearchForm();
		pf.setMysp(0);
		model.addAttribute("examinationSearch", pf);

		return "search/examinationsearchwin";
	}

	@RequestMapping(value = { "/examinationsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostexam(
			Model model,
			HttpSession session,
			@ModelAttribute("examinationSearch") ExaminationSearchForm examinationSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List examinationList = searchUtilBo.searchExaminationwin(
				examinationSearchForm, session);

		model.addAttribute("examination", examinationList);

		// system.out
		return "search/examinationsearchwin";
	}

	// search test
	@RequestMapping(value = { "/laboratorysearchwin" })
	@Layout("layouts/blank")
	public String searchblankla(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		LaboratorySearchForm pf = new LaboratorySearchForm();
		pf.setMysp(0);
		model.addAttribute("laboratorySearch", pf);

		return "search/laboratorysearchwin";
	}

	@RequestMapping(value = { "/laboratorysearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostlab(
			Model model,
			HttpSession session,
			@ModelAttribute("laboratorySearch") LaboratorySearchForm laboratorySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List laboratoryList = searchUtilBo.searchLaboratorywin(
				laboratorySearchForm, session);

		model.addAttribute("laboratory", laboratoryList);

		// system.out
		return "search/laboratorysearchwin";
	}

	// search test
	@RequestMapping(value = { "/diseasessearchwin" })
	@Layout("layouts/blank")
	public String searchblankdiag(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		DiseasesSearchForm pf = new DiseasesSearchForm();
		pf.setMysp(0);
		model.addAttribute("diseasesSearch", pf);

		return "search/diseasessearchwin";
	}

	@RequestMapping(value = { "/diseasessearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostdiag(
			Model model,
			HttpSession session,
			@ModelAttribute("diseasesSearch") DiseasesSearchForm diseasesSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List diseasesList = searchUtilBo.searchDiseaseswin(diseasesSearchForm,
				session);

		model.addAttribute("diseases", diseasesList);

		// system.out
		return "search/diseasessearchwin";
	}

	// search test
	@RequestMapping(value = { "/surgerysearchwin" })
	@Layout("layouts/blank")
	public String searchblanksur(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		SurgerySearchForm pf = new SurgerySearchForm();
		pf.setMysp(0);
		model.addAttribute("surgerySearch", pf);

		return "search/surgerysearchwin";
	}

	@RequestMapping(value = { "/surgerysearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostsur(
			Model model,
			HttpSession session,
			@ModelAttribute("surgerySearch") SurgerySearchForm surgerySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List surgeryList = searchUtilBo.searchSurgerywin(surgerySearchForm,
				session);

		model.addAttribute("surgery", surgeryList);

		// system.out
		return "search/surgerysearchwin";
	}

	// search test
	@RequestMapping(value = { "/drugsearchwin" })
	@Layout("layouts/blank")
	public String searchblankdru(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		DrugSearchForm pf = new DrugSearchForm();
		pf.setMysp(0);
		model.addAttribute("drugSearch", pf);

		return "search/drugsearchwin";
	}

	@RequestMapping(value = { "/drugsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostdru(Model model, HttpSession session,
			@ModelAttribute("drugSearch") DrugSearchForm drugSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List drugList = searchUtilBo.searchDrugwin(drugSearchForm, session);

		model.addAttribute("drug", drugList);

		// system.out
		return "search/drugsearchwin";
	}

	// search test
	@RequestMapping(value = { "/xraysearchwin" })
	@Layout("layouts/blank")
	public String searchblankxr(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		XraySearchForm pf = new XraySearchForm();
		pf.setMysp(0);
		model.addAttribute("xraySearch", pf);

		return "search/xraysearchwin";
	}

	@RequestMapping(value = { "/xraysearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostxr(Model model, HttpSession session,
			@ModelAttribute("xraySearch") XraySearchForm xraySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List xrayList = searchUtilBo.searchXraywin(xraySearchForm, session);

		model.addAttribute("xray", xrayList);

		// system.out
		return "search/xraysearchwin";
	}

	// search test
	@RequestMapping(value = { "/admissionsearchwin" })
	@Layout("layouts/blank")
	public String searchblankadm(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		AdmissionSearchForm pf = new AdmissionSearchForm();
		pf.setMysp(0);
		model.addAttribute("admissionSearch", pf);

		return "search/admissionsearchwin";
	}

	@RequestMapping(value = { "/admissionsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchblankpostadm(
			Model model,
			HttpSession session,
			@ModelAttribute("admissionSearch") AdmissionSearchForm admissionSearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List admissionList = searchUtilBo.searchAdmissionwin(
				admissionSearchForm, session);

		model.addAttribute("admission", admissionList);

		// system.out
		return "search/admissionsearchwin";
	}

	// search test
	@RequestMapping(value = { "/inventorysearchwin" })
	@Layout("layouts/blank")
	public String searchinventoryblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		InventorySearchForm pf = new InventorySearchForm();
		pf.setMysp(0);
		model.addAttribute("inventorySearch", pf);
		return "search/inventorysearchwin";
	}

	@RequestMapping(value = { "/inventorysearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchinventoryblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("inventorySearch") InventorySearchForm inventorySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List inventoryList = searchUtilBo.searchInventorywin(
				inventorySearchForm, session);

		model.addAttribute("inventory", inventoryList);

		return "search/inventorysearchwin";
	}

	// search test
	@RequestMapping(value = { "/inventorysearchrequestwin" })
	@Layout("layouts/blank")
	public String searchinventoryblankreq(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		InventorySearchForm pf = new InventorySearchForm();
		pf.setMysp(0);
		model.addAttribute("inventorySearch", pf);
		return "search/inventorysearchrequestwin";
	}

	@RequestMapping(value = { "/inventorysearchrequestwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchinventoryblankpostreq(
			Model model,
			HttpSession session,
			@ModelAttribute("inventorySearch") InventorySearchForm inventorySearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List inventoryList = searchUtilBo.searchInventorywin(
				inventorySearchForm, session);

		model.addAttribute("inventory", inventoryList);

		return "search/inventorysearchrequestwin";
	}

	// search test
	@RequestMapping(value = { "/globalitemsearchwin" })
	@Layout("layouts/blank")
	public String searchglobalblank(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		GlobalitemSearchForm pf = new GlobalitemSearchForm();
		pf.setMysp(0);
		model.addAttribute("globalitemSearch", pf);
		return "search/globalitemsearchwin";
	}

	@RequestMapping(value = { "/globalitemsearchwin" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String searchglobalblankpost(
			Model model,
			HttpSession session,
			@ModelAttribute("globalitemSearch") GlobalitemSearchForm globalItemsearchForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List globalitemList = searchUtilBo.searchGlobalitem(
				globalItemsearchForm, session);

		model.addAttribute("globalitemlist", globalitemList);

		return "search/globalitemsearchwin";
	}

	@RequestMapping(value = { "/datetimer" })
	@Layout("layouts/datetimer")
	public String datetimer(Model model) {

		return "search/datetimercontent";
	}
	
	@RequestMapping(value = { "/customer_acc" })
	@Layout("layouts/blank")
	public String custAccSearch(Model model, HttpSession session) {
		// List<Patient> plist =
		// this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		// model.addAttribute("plist",plist);
		CustAccSearchForm cForm = new CustAccSearchForm();
		cForm.setMysp(0);
		model.addAttribute("custAccSearch", cForm);
		return "search/cust_acc_search_win";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/customer_acc" }, method = RequestMethod.POST)
	@Layout("layouts/blank")
	public String custAccSearch(
			Model model,
			HttpSession session,
			@ModelAttribute("custAccSearch") CustAccSearchForm custAccSearch,
			BindingResult result, RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);
		List<CustomerAccount> custAccList = searchUtilBo.searchCustAccWin(custAccSearch,
				session);

		model.addAttribute("customerAccs", custAccList);

		return "search/cust_acc_search_win";
	}

}
