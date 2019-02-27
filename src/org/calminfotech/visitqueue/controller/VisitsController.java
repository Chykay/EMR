package org.calminfotech.visitqueue.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeInvoiceBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.BedCategoryBo;
import org.calminfotech.system.boInterface.ConsultationStatusBo;
import org.calminfotech.system.boInterface.ExaminationBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemPointBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.Examination;
import org.calminfotech.system.models.ExaminationResultSetup;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.AutoGenerate;
import org.calminfotech.utils.ClockedUsersList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.PrescribedstatusList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Prescribedstatus;
//import org.calminfotech.visitqueue.boInterface.RescheduledVisitBo;
import org.calminfotech.visitqueue.boInterface.VisitBo;
//import org.calminfotech.hmo.boInterface.HmoSubserviceItemBo;
//import org.calminfotech.system.boInterface.LoginSectionBo;
//import org.calminfotech.system.boInterface.PaymentLogBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.boInterface.VisitTimelineBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.forms.ChooseForm;
import org.calminfotech.visitqueue.forms.PrescribedSearchForm;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;
/*import org.calminfotech.consultation.boInterface.VisitConsultationBo;
import org.calminfotech.consultation.boInterface.VisitPrescribedAdmissionBo;
import org.calminfotech.consultation.boInterface.VisitPrescribedDrugBo;
import org.calminfotech.consultation.boInterface.VisitPrescribedLabtestBo;
import org.calminfotech.consultation.boInterface.VisitPrescribedSurgeryBo;
import org.calminfotech.consultation.boInterface.VisitPrescribedXrayBo;
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.consultation.forms.PaymentFormC;
import org.calminfotech.consultation.forms.RescheduleVisitForm;
import org.calminfotech.consultation.forms.VisitCloseForm;
import org.calminfotech.consultation.forms.VisitPaymentForm;
import org.calminfotech.consultation.forms.VisitationForm;*/
//import org.calminfotech.consultation.models.VisitConsultation;
/*import org.calminfotech.consultation.models.VisitConsultationInjectionSpread;
import org.calminfotech.consultation.models.VisitConsultationPrescribedAdmission;
import org.calminfotech.consultation.models.VisitConsultationPrescribedDrug;
import org.calminfotech.consultation.models.PaymentItemType;
import org.calminfotech.consultation.models.VisitConsultationPrescribedLabtest;
import org.calminfotech.consultation.models.VisitConsultationPrescribedSurgery;
import org.calminfotech.consultation.models.VisitConsultationPrescribedXray;*/
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.calminfotech.visitqueue.models.VisitTimeline;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.calminfotech.livestock.form.PenForm;

@Controller
@RequestMapping(value = "/visits")

public class VisitsController {
	
	@Autowired
    private StaffRegBoInterface staffRegBo;
	
	

	@Autowired
    private ClockedUsersList clockuserBo;
	
	

	@Autowired
	private ExaminationBo examBo;
	
	
	@Autowired
	private PatientHmoBo patientHmoBo;
	
	@Autowired
	private HrunitCategoryBo unitBo;
	
	@Autowired
	private SettingBo settingBo;
	

	@Autowired
	private PrescribedstatusList  prescribedstatusBo;
	
	

//	@Autowired
//	private ClockinBoInterface clockinBo;
	
	@Autowired
	private GlobalItemPointBo globalItemPointBo;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity useridentity;

	
/*	@Autowired
	private VisitPrescribedDrugBo  prescribeddrugbo;

		
	@Autowired
	private VisitPrescribedLabtestBo  prescribedlabtestbo;
	
	@Autowired
	private VisitPrescribedSurgeryBo  prescribedsurgerybo;
	
	@Autowired
	private VisitPrescribedAdmissionBo  prescribedadmissionbo;
	
	@Autowired
	private VisitPrescribedXrayBo  prescribedxraybo;*/
	
	@Autowired
	private GlobalItemUnitofMeasureBo  globalitemunitBo;
	
	
	@Autowired
	private GlobalItemBo  globalitemBo;
	
	//@Autowired
	//private RescheduledVisitBo rescheduledVisitBo;
	
	//@Autowired
	//private BillUnitOfMeasureBo billUnitOfMeasureBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private VisitDao visitDao;
	
	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

//	@Autowired
//	private VisitConsultationBo visitConsultationBo;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;
	
	@Autowired
	private VisitTimelineBo visitTimelineBo;

	@Autowired
	private VisitStatusBo visitStatusBo;


	@Autowired
	private BillSchemeBo billSchemeBo;

	@Autowired
	private ConsultationStatusBo consultationStatusBo;

	@Autowired
	private VisitWorkflowPointBo VWfPointBo;
	@Autowired
	private UserBo userBo;

	@Autowired
	private GlobalItemBo itemBo;
	
	private GlobalItemUnitofMeasureBo ItemUnitBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private BillSchemeInvoiceBo billSchemeInvoiceBo;

	@Autowired
	private PatientBo patientBo;
	

	@Autowired
	private BedCategoryBo bedCategoryBo;


	//@Autowired
	//private PaymentLogBo paymentLogBo;

	//@Autowired
	//private HmoBalanceBo hmoBalanceBo;

	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String index(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		model.addAttribute("visits", this.visitBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "visits/index";
	}
		
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/frontdesklist")
	public String viewAction3( Model model,
			RedirectAttributes redirectAttributes) {
	

		
	
		if (userIdentity.getCurrentPointId() != 1)
		{
			return "redirect:/visits/queue/1";
		}
		
	
		
		
		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	

	//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));

		cal.add( Calendar.DAY_OF_YEAR, +30);

	//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));


	

		
		List<Visit> visitlist =this.visitBo.fetchAllByOrganisationmyqueuefrontdesk(userIdentity.getOrganisation().getId());
		
		List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		List<GlobalItem> globalitemlist = this.globalitemBo.fetchAllByKind("vsse");
		//List<GlobalItem> globalitemlist2 = this.globalitemBo.fetchAllByKind("vsse");
		
		
		List<GlobalItemUnitofMeasure> itemunitmeasure  = new ArrayList();
		
	  //  System.out.print("gilist" + globalitemlist.size())
	    
	    
		for (GlobalItem  gil : globalitemlist)
			
		{
			
			for ( GlobalItemUnitofMeasure  unim :  gil.getGlobalitemunit())
			
			{
				itemunitmeasure.add(unim);
				
			}
			
		}



		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();


		//List<fffVisitStatus> billtolist = this.visitStatusBo.fetchAll();

		
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);
	
		model.addAttribute("vlist", visitlist);
		model.addAttribute("unitcategory", unitcategory);
		model.addAttribute("statuslist", statuslist);
		//model.addAttribute("billtolist", billtolist);
		model.addAttribute("itemunitmeasurelist", itemunitmeasure);
		model.addAttribute("globalitemlist", globalitemlist);

		return "visits/frontdesklist";
	}


	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/vitallist")
	public String viewAction( Model model,
			RedirectAttributes redirectAttributes) {

		if (userIdentity.getCurrentPointId() != 2)
		{
			return "redirect:/visits/queue/2";
		}
		
	List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();


	List<Visit> visitlist = visitBo.fetchAllByOrganisationmyqueuevitals(userIdentity.getOrganisation().getId());


	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

	Calendar cal = 	GregorianCalendar.getInstance();
	cal.add( Calendar.DAY_OF_YEAR, -30);	 	

	//prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));

	cal.add( Calendar.DAY_OF_YEAR, +30);

	//prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));


		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();
		
		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		
		
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);
		model.addAttribute("vlist", visitlist);
		model.addAttribute("statuslist", statuslist);
		
		model.addAttribute("unitcategory", unitcategory);
		
		return "visits/myvitallist";
}
	
	


	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/consultationlist")
	public String viewActioncon( Model model,
			RedirectAttributes redirectAttributes) {

		
		if (userIdentity.getCurrentPointId() != 3)
		{
			return "redirect:/visits/queue/3";
		}
		
	List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();


	List<Visit> visitlist = visitBo.fetchAllByOrganisationmyqueue(userIdentity.getOrganisation().getId());


	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

	Calendar cal = 	GregorianCalendar.getInstance();
	cal.add( Calendar.DAY_OF_YEAR, -30);	 	

//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));

	cal.add( Calendar.DAY_OF_YEAR, +30);

//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));


		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();
		
		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		
		
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("vform", vform);
		model.addAttribute("vlist", visitlist);
		model.addAttribute("statuslist", statuslist);
		
		model.addAttribute("unitcategory", unitcategory);
		
		return "visits/myconsultationlist";
}
	
	
	
	
	
	
	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/injectionlist")
	public String viewAction7( Model model,
			RedirectAttributes redirectAttributes) {

		if (userIdentity.getCurrentPointId() != 4)
		{
			return "redirect:/visits/queue/4";
		}
		

	
	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
	//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
//		prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
	 	
	 	System.out.print("abt to call injection..");
//	 	List<VisitConsultationPrescribedDrug> prescribedinjection = this.prescribeddrugbo.fetchallPrescribedinjection();

		
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("unitcategory", unitcategory);
//		model.addAttribute("prescribedinjectionlist", prescribedinjection);
		model.addAttribute("statuslist", statuslist);
	return "visits/myinjectionlist";
}
	

	
	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/druglist")
	public String viewActiondrug( Model model,
			RedirectAttributes redirectAttributes) {

		if (userIdentity.getCurrentPointId() != 5)
		{
			return "redirect:/visits/queue/5";
		}
		

	
	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
	//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
	//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
//	 	List<VisitConsultationPrescribedDrug> prescribeddrug = this.prescribeddrugbo.fetchallPrescribeddrug();

		
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("unitcategory", unitcategory);
//		model.addAttribute("prescribeddruglist", prescribeddrug);
		model.addAttribute("statuslist", statuslist);
	return "visits/mydruglist";
}
	
	
	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/labtestlist")
	public String viewAction78( Model model,
			RedirectAttributes redirectAttributes) {


		if (userIdentity.getCurrentPointId() != 6)
		{
			///userIdentity.setCurrentUrl("visits/labtestlist");
			return "redirect:/visits/queue/6";
		}
		

	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
	//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
	//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
	 	
//	 	List<VisitConsultationPrescribedLabtest> prescribedlabtest = this.prescribedlabtestbo.fetchallPrescribedLabtest();
	 	List<GlobalItem> globalitemlist = this.globalitemBo.fetchAllByKind("lase");
	 	
	//	List<GlobalItem> globalitemlist2 = this.globalitemBo.getGlobalItemById(id);
		
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("unitcategory", unitcategory);
		model.addAttribute("globalitemlist", globalitemlist);
//		model.addAttribute("prescribedlabtestlist", prescribedlabtest);
		model.addAttribute("statuslist", statuslist);
	return "visits/mylabtestlist";
}
	

	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/surgerylist")
	public String viewAction787( Model model,
			RedirectAttributes redirectAttributes) {


		if (userIdentity.getCurrentPointId() != 7)
		{
			return "redirect:/visits/queue/7";
		}
		

	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
		//prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
	//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
//	 	List<VisitConsultationPrescribedSurgery> prescribedsurgery = this.prescribedsurgerybo.fetchallPrescribedSurgery();

	 	List<GlobalItem> globalitemlist = this.globalitemBo.fetchAllByKind("suse");
	 	
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("unitcategory", unitcategory);
//		model.addAttribute("prescribedsurgerylist", prescribedsurgery);
		model.addAttribute("globalitemlist", globalitemlist);
		model.addAttribute("statuslist", statuslist);
	return "visits/mysurgerylist";
}
	

	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/xraylist")
	public String viewAction781( Model model,
			RedirectAttributes redirectAttributes) {


		if (userIdentity.getCurrentPointId() != 8)
		{
			return "redirect:/visits/queue/8";
		}
		

	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
		//prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
		//prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
//	 	List<VisitConsultationPrescribedXray> prescribedxray = this.prescribedxraybo.fetchallPrescribedXray();


	 	List<GlobalItem> globalitemlist = this.globalitemBo.fetchAllByKind("xrse");
	 	
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		model.addAttribute("unitcategory", unitcategory);
//		model.addAttribute("prescribedxraylist", prescribedxray);
		model.addAttribute("globalitemlist", globalitemlist);
		model.addAttribute("statuslist", statuslist);
	return "visits/myxraylist";
}	
	
	
	@SuppressWarnings("unused")
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/admissionlist")
	public String viewAction72( Model model,
			RedirectAttributes redirectAttributes) {


		if (userIdentity.getCurrentPointId() != 9)
		{
			return "redirect:/visits/queue/9";
		}
		

	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
	// List<HrunitCategory> unitcategoryqp = this.unitBo.fetchAllByOrganisationbyqueuebypoint(userIdentity.getCurrentPointId());
	 
	 
	// List<BedCategory> bedroom = this.bedCategoryBo.fetchAllByOrganisationByCategoryHR(userIdentity.getCurrentUnitId());
	 
	 
	 
		
	VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
	 	
	 	
	 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		Calendar cal = 	GregorianCalendar.getInstance();
		
		
		cal.add( Calendar.DAY_OF_YEAR, -30);	 	
		
	//	prescribedSearch.setDat1(DateUtils.formatDateToString(cal.getTime()));
		
		cal.add( Calendar.DAY_OF_YEAR, +30);
		
	//	prescribedSearch.setDat2(DateUtils.formatDateToString(cal.getTime()));
		
		
	 	List<Prescribedstatus> statuslist = this.prescribedstatusBo.fetchAll();
//	 	List<VisitConsultationPrescribedAdmission> prescribedadmission = this.prescribedadmissionbo.fetchallPrescribedAdmission();

		
		model.addAttribute("vform", vform);
		model.addAttribute("prescribedSearch", prescribedSearch);
		
		model.addAttribute("unitcategory", unitcategory);
		
	//	model.addAttribute("bedroom", bedroom);
		
//		model.addAttribute("prescribedadmissionlist", prescribedadmission);
		model.addAttribute("statuslist", statuslist);
		
		
	return "visits/myadmissionlist";
}
	
	
	
	//ibrahim
	@RequestMapping(value = "/configure/{id}" )
	@Layout(value = "layouts/form_wizard_layout")
	public String configureBill(@PathVariable("id") Integer id, Model model,
		//	@Valid @ModelAttribute("vForm") VisitationForm visitForm,
			//@Valid @ModelAttribute("assignForm") AssignForm assignForm,
			//BindingResult result,
			RedirectAttributes redirectAttributes) {
		   try
		   {
				Patient patient = this.patientBo.getPatientById(id);
						
				
			       
				Visit visit = new Visit();			
				visit.setCode(new AutoGenerate().mygen());
				System.out.println("code is"+visit.getCode());
				/*visit.setPatient(patient);
				visit.setPatientbillto(patient);
				visit.setOrganisation(this.userIdentity.getOrganisation());*/
				visit.setCreatedBy(this.userIdentity.getUsername());
				visit.setDeleted(false); 
				visit.setEffectiveDate(new GregorianCalendar().getTime());
				visit.setUser(this.userIdentity.getUserProfile().getUser());
				                                                                                                           
				//visit.setGlobalitem(this.)
				
				//visit.setGlobalitemmeasure(this.settingBo.fetchsettings("")
				
				
				//visit.setPoint(userIdentity.getStaffregistration().getHrunitcategory().getPoint());
				//visit.setPoint(this.wfPointBo.getWorkflowPointById(1));
				if (userIdentity.getCurrentUnit() == null)
				{
					
					return "redirect:/visits/queue/1";
					
				} else
					
				{
					if (userIdentity.getCurrentPointId() != 1)
					
					{
						return "redirect:/visits/queue/1";
						
					}
				}
				
				visit.setPoint(userIdentity.getCurrentUnit().getPoint());
//				visit.setUnit(userIdentity.getCurrentUnit());
//				visit.setCurrentunit(userIdentity.getCurrentUnit());
				
				//visit.setPoint(this.wfPointBo.getWorkflowPointById(1));
				//visit.setUnit(userIdentity.getStaffregistration().getHrunitcategory());
				//visit.setUnit(this.unitBo.getHrunitCategoryById(userIdentity.getCurrentUnitId()));
								

				

				visit.setCreateDate(new GregorianCalendar().getTime());
			
				visit.setStatus(this.visitStatusBo.getStatusById(1));
				
				Visit vst=this.visitBo.save(visit);
			  //  visitForm.setId(vst.getId());
			  //  visitForm.setCode(vst.getCode());
			    
				
			
			//	model.addAttribute("vForm",  visitForm);
				model.addAttribute("vst", vst);
				model.addAttribute("pid", id);
			
				//model.addAttribute("pForm", pForm);
			//	AssignForm assignForm1 = new AssignForm();
			//	assignForm.setVisitId(vst.getId());
				
			//	model.addAttribute("assignForm", assignForm1);
			
				VisitTimeline visitTimeline = new VisitTimeline();
				visitTimeline.setVisit(vst);
				visitTimeline.setUser(vst.getUser());
//			    visitTimeline.setHrunitcategory(vst.getUnit());
			    visitTimeline.setPoint(vst.getPoint());
			    
			 	visitTimeline.setComment("No comment");
			 	visitTimeline.setCreatedBy(userIdentity.getUsername());
			 	visitTimeline.setCreateDate(new GregorianCalendar().getTime());
			    visitTimeline.setStatus(true);
			    
			
			  this.visitTimelineBo.save(visitTimeline);
			  /*this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"You have succesfully craeted visit for " + vst.getPatient().getSurname() + " " + vst.getPatient().getOthernames() );
				  */
		   
	}
		   catch (Exception e){
			   
			  
					this.alert
							.setAlert(redirectAttributes, Alert.DANGER,
									e.getMessage() + " You may also already have an existing visit for the day" );
					return "redirect:/visits/" +
							"list";
				
		   }
		   
			
		   return "redirect:/visits/frontdesklist";
	}
	
	



	
	//ibrahim
	@RequestMapping(value = "/configure", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String configureBillpost( Model model,
//		@Valid @ModelAttribute("visitForm") VisitationForm visitForm,
			//@Valid @ModelAttribute("assignForm") AssignForm assignForm,
			//BindingResult result,
			RedirectAttributes redirectAttributes) {
		   try
		   {
			/*	Patient patient = this.patientBo.getPatientById(visitForm.getId());
						

				Patient patientbill = this.patientBo.getPatientById(visitForm.getBilltoId());
				*/
			       
				Visit visit = new Visit();			
				visit.setCode(new AutoGenerate().mygen());
				System.out.println("code is"+visit.getCode());
				/*visit.setPatient(patient);
				visit.setPatientbillto(patientbill);
				visit.setOrganisation(this.userIdentity.getOrganisation());*/
				visit.setCreatedBy(this.userIdentity.getUsername());
				visit.setDeleted(false); 
//				visit.setEffectiveDate(DateUtils.formatStringToDate(visitForm.getEffectivedate(),"yyyy-MM-dd hh:mm"));
				visit.setUser(this.userIdentity.getUserProfile().getUser());
		                                                                                                        
				//visit.setGlobalitem(this.)
				
				//visit.setGlobalitemmeasure(this.settingBo.fetchsettings("")
				
				
				//visit.setPoint(userIdentity.getStaffregistration().getHrunitcategory().getPoint());
				//visit.setPoint(this.wfPointBo.getWorkflowPointById(1));
				if (userIdentity.getCurrentUnit() == null)
				{
					
					return "redirect:/visits/queue/1";
					
				} else
					
				{
					if (userIdentity.getCurrentPointId() != 1)
					
					{
						return "redirect:/visits/queue/1";
						
					}
				}
				
				visit.setPoint(userIdentity.getCurrentUnit().getPoint());
			/*	visit.setUnit(userIdentity.getCurrentUnit());
				visit.setCurrentunit(userIdentity.getCurrentUnit());
				*/
				//visit.setPoint(this.wfPointBo.getWorkflowPointById(1));
				//visit.setUnit(userIdentity.getStaffregistration().getHrunitcategory());
				//visit.setUnit(this.unitBo.getHrunitCategoryById(userIdentity.getCurrentUnitId()));
								

				

				visit.setCreateDate(new GregorianCalendar().getTime());
			
				visit.setStatus(this.visitStatusBo.getStatusById(1));
				
				Visit vst=this.visitBo.save(visit);
			  //  visitForm.setId(vst.getId());
			  //  visitForm.setCode(vst.getCode());
			    
				
			
			//	model.addAttribute("vForm",  visitForm);
				model.addAttribute("vst", vst);
//				model.addAttribute("pid", patient.getPatientId());
			
				//model.addAttribute("pForm", pForm);
			//	AssignForm assignForm1 = new AssignForm();
			//	assignForm.setVisitId(vst.getId());
				
			//	model.addAttribute("assignForm", assignForm1);
			
				VisitTimeline visitTimeline = new VisitTimeline();
				visitTimeline.setVisit(vst);
				visitTimeline.setUser(vst.getUser());
//			    visitTimeline.setHrunitcategory(vst.getUnit());
			    visitTimeline.setPoint(vst.getPoint());
			    
			 	visitTimeline.setComment("No comment");
			 	visitTimeline.setCreatedBy(userIdentity.getUsername());
			 	visitTimeline.setCreateDate(new GregorianCalendar().getTime());
			    visitTimeline.setStatus(true);
			    
			
			  this.visitTimelineBo.save(visitTimeline);
			  /*this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"You have succesfully created visit for " + vst.getPatient().getSurname() + " " + vst.getPatient().getOthernames() );
				  
		   */
	}
		   catch (Exception e){
			   
			  
					this.alert
							.setAlert(redirectAttributes, Alert.DANGER,
									e.getMessage() + " You may also already have an existing visit for the day" );
					return "redirect:/visits/frontdesklist";
				
		   }
		   
			
		   return "redirect:/visits/frontdesklist";
	}
	

	@RequestMapping(value = "/queue/{pointId}")
	//@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction4(@PathVariable("pointId") Integer pointId, Model model,
			RedirectAttributes redirectAttributes) {
		List <ClockAssignment> clockin =this.clockuserBo.fetchAllByPoint(pointId);
		System.out.print("cccccccccccc");
		System.out.print(clockin.size());
		System.out.print("cccccccccccc");

if (clockin.size()>1)
{
	userIdentity.setCurrentPointId(pointId);
	ChooseForm choose = new ChooseForm();
	
	model.addAttribute("choose", choose);
	model.addAttribute("clockin",clockin);
	/*
	if (pointId==1){
	model.addAttribute("showdate",true);
	}else
	{
		model.addAttribute("showdate",false);
		
	}
	*/
	return "/visits/chooseunit";			
}

// this is for not showing where to work if its only one unit clocked into
if (clockin.size()==1)
{
userIdentity.setCurrentPointId(pointId);
userIdentity.setCurrentUnitId(clockin.get(0).getHrunit().getCategoryId());
userIdentity.setCurrentUnit(clockin.get(0).getHrunit());
System.out.print("This is currentpoint" + userIdentity.getCurrentPointId());
return "redirect:/visits/myqueue";
			
}


if (clockin.size()<1)
{

return "redirect:/hrunit/clocking/index";
}
		
		return "redirect:/visits/frontdesklist";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/myqueue", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction3(Model model, RedirectAttributes redirectAttributes) {
		
		return this.process(model);

	}
	

	
	

	@RequestMapping(value = "/myqueue", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction2(@ModelAttribute("choose") ChooseForm chooseform,
					Model model, RedirectAttributes redirectAttributes) {

userIdentity.setCurrentUnitId(chooseform.getUnitId());
userIdentity.setCurrentUnit(unitBo.getHrunitCategoryById(chooseform.getUnitId()));



return this.process(model);

	}
	
	
	
public String  process (Model model)
{
	

if  (userIdentity.getCurrentPointId() == 1)
{
	return "redirect:/visits/frontdesklist";
}




if  (userIdentity.getCurrentPointId() == 2)
{
	return "redirect:/visits/vitallist";

	}



if  (userIdentity.getCurrentPointId() == 3)
{
	return "redirect:/visits/consultationlist";

	}



if  (userIdentity.getCurrentPointId() == 4)
{
	return "redirect:/visits/injectionlist";

}


if  (userIdentity.getCurrentPointId() == 5)
{
	return "redirect:/visits/druglist";

	
	
}


if  (userIdentity.getCurrentPointId() == 6)
{
	return "redirect:/visits/labtestlist";

}


if  (userIdentity.getCurrentPointId() == 7)
{
	return "redirect:/visits/surgerylist";

}



if  (userIdentity.getCurrentPointId() == 8)
{
	return "redirect:/visits/xraylist";

}



if  (userIdentity.getCurrentPointId() == 9)
{
	return "redirect:/visits/admissionlist";

}

if  (userIdentity.getCurrentPointId() == 12)
{
	return userIdentity.getCurrentUrl();
}


return "redirect:/";

}






@RequestMapping(value = "/frontdesklist/listbyform", method = RequestMethod.POST)
@Layout(value = "layouts/form_wizard_layout")
public String newVisitAction212(@Valid @ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedSearchForm,
				Model model, RedirectAttributes redirectAttributes) {
	
	
	
	 List<HrunitCategory> unitcategory = this.unitBo.fetchAllByOrganisationbyqueue();
		
		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();
		 	
		 	
		 	PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();
		 	
		 	prescribedSearch.setDat1(prescribedSearchForm.getDat1());
		 	prescribedSearch.setDat2(prescribedSearchForm.getDat2());
		 	prescribedSearch.setStatusId(prescribedSearchForm.getStatusId());
		 	
   List<Visit> visitlist = visitBo.fetchAllByOrganisationmyqueuebyparamfrontdesk(DateUtils.formatStringToDate(prescribedSearchForm.getDat1()),DateUtils.formatStringToDate(prescribedSearchForm.getDat2()),prescribedSearchForm.getStatusId());
	 	List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();
			 
		List<GlobalItem> globalitemlist = this.globalitemBo.fetchAllByKind("vsse");
	 	model.addAttribute("globalitemlist", globalitemlist);
			model.addAttribute("vform", vform);
			model.addAttribute("prescribedSearch", prescribedSearch);
			model.addAttribute("statuslist", statuslist);
			model.addAttribute("vlist", visitlist);
			model.addAttribute("unitcategory", unitcategory);
		
		
		//return "redirect:/";
			return "visits/frontdesklist";

}




@Transactional
@SuppressWarnings("unused")
@RequestMapping(value = "/transfer", method = RequestMethod.POST) 
@Layout(value = "layouts/form_wizard_layout")
public String viewActiontrasnfer( Model model,RedirectAttributes redirectAttributes,
		@Valid @ModelAttribute("vform") VisitWorkflowUserConfigurationForm vform) {

	
	 Visit visit = this.visitBo.getVisitationById(vform.getVisitId());


		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
				"Visit does not Exist");
			return "redirect:/";
		}
		
		
		HrunitCategory hrunit  = this.unitBo.getHrunitCategoryById(vform.getUnitId());
	

if ( hrunit.getPoint()==null)
{
		this.alert.setAlert(redirectAttributes, Alert.DANGER,
				"Please Assign Point to UNIT!!!");
		
		return "redirect:/visits/myqueue";

}		
		
		
		
		
		//	VisitWorkflowPoint point  = this.wfPointBo.getWorkflowPointById(hrunit.getPoint().getId());
		User user = userBo.getUserById(vform.getUserId());			
		

		System.out.print("$$$$$$$$$");
		System.out.print(vform.getUserId());
		System.out.print(user.getUserId());
		
		
	visit.setPoint(hrunit.getPoint());	
	
//	visit.setCurrentunit(hrunit);
	visit.setUser(user);
	visit.setModifiedBy(userIdentity.getUsername());
	visit.setModifyDate(new GregorianCalendar().getTime());
	
	 
	
	visitBo.update(visit);
	
	VisitTimeline visitTimeline = new VisitTimeline();
	visitTimeline.setVisit(visit);
	visitTimeline.setUser(visit.getUser());
//    visitTimeline.setHrunitcategory(visit.getCurrentunit());
    visitTimeline.setPoint(visit.getPoint());
    
 	visitTimeline.setComment(vform.getComment());
 	visitTimeline.setCreatedBy(userIdentity.getUsername());
 	visitTimeline.setCreateDate(new GregorianCalendar().getTime());
    visitTimeline.setStatus(true);
    

  this.visitTimelineBo.save(visitTimeline);
  System.out.print("tttttt");
	System.out.print(userIdentity.getCurrentPointId());
	System.out.print("tttttt");
	
	this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
			"Transfer Successful!!!");
	
	return "redirect:/visits/myqueue";


}








@RequestMapping(value = "/changestatus", method = RequestMethod.POST)

public String configurebillAction(@Valid @ModelAttribute("vform") VisitWorkflowUserConfigurationForm vform,
		Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//	Visit visit = this.prescribedBo.fetchallPrescribedinjection()(id);
	//	model.addAttribute("dForm", vwfUserConfigForm);
//
	
Visit  visit =	visitBo.getVisitationById(vform.getId());

VisitWorkflowUserConfigurationForm vformbefore = new VisitWorkflowUserConfigurationForm();

vformbefore.setStatusId(visit.getStatus().getId());
auditor.before(request, "Visit Queue Status", vformbefore);

visit.setStatus(this.visitStatusBo.getStatusById(vform.getStatusId()));
/*
if (vform.getBilltoId()!=null)
{
visit.setPatientbillto(this.patientBo.getPatientById(vform.getBilltoId()));
}*/

visitBo.update(visit);

auditor.after(request, "Visit Queue Status", vform,
		userIdentity.getUsername(),vform.getId());



return "redirect:/visits/myqueue";

		
}




@RequestMapping(value = "/fetchexamresult/{examid}", method = RequestMethod.GET, produces = "text/html")
@ResponseBody
public String ajaxfetchexamresult(@PathVariable("examid") Integer examid, Model model,
		RedirectAttributes redirectAttributes) {
	
	String itemStr = "<option value=''>Select Result</option>";
	String result="";
	
//	model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
	Examination exam = this.examBo.getExaminationById(examid);
	
	//System.out.println("Ajax Size Controller");
	//System.out.println(list.size());
	try 
	{
		
	for (ExaminationResultSetup examresult : exam.getExaminationResultSetup()) {
   
		String ms = examresult.getUnitofmeasure() != null ? examresult.getUnitofmeasure().getName() : "";

		result += "<option value='" + examresult.getId()  + "'>"
				+  examresult.getDescription() + " " +  ms.toString() + "</option>";
	}
	
	}
	
	
	catch(Exception e)
	{
		System.out.print ("ajaxexam" +e.getMessage());
	
	
	}
	
	
	
	
	
	
	
	return result;
}




@RequestMapping(value = "/fetchdrugmeasure/{drugid}", method = RequestMethod.GET, produces = "text/html")
@ResponseBody
public String ajaxfetchdrugmeasure(@PathVariable("drugid") Integer drugid, Model model,
		RedirectAttributes redirectAttributes) {
	
	//String measure = "<option value=''>Select Measure</option>";
String measure="";
	
//	model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
	
	List<GlobalItemUnitofMeasureVw> globalitemunitofmeasurevw = this.globalitemunitBo.fetchAllByItemIdvw(drugid);
	
	//System.out.println("Ajax Size Controller");
	//System.out.println(list.size());
	try 
	{
	for (GlobalItemUnitofMeasureVw measurelist : globalitemunitofmeasurevw) {
		measure += "<option value='" + measurelist.getId()  + "'>"
				+  measurelist.getSearchname() + "</option>";
	}
	
	}
	
	
	catch(Exception e)
	{
		
	
	
	}
	
	
	
	
	
	if (measure.equals("") )
	{
		measure="<option value=''>Measure not Available</option>";
	}
	
	return measure;
}

	


@RequestMapping(value = "/fetchdrugmeasuredrug/{drugid}", method = RequestMethod.GET, produces = "text/html")
@ResponseBody
public String ajaxfetchdrugmeasuredrug(@PathVariable("drugid") Integer drugid, Model model,
		RedirectAttributes redirectAttributes) {
	
	//String measure = "<option value=''>Select Measure</option>";
String measure="";
	
//	model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
	
	List<GlobalItemUnitofMeasureVw> globalitemunitofmeasurevw = this.globalitemunitBo.fetchAllByItemIdvwprescribe(drugid);
	
	//System.out.println("Ajax Size Controller");
	//System.out.println(list.size());
	try 
	{
	for (GlobalItemUnitofMeasureVw measurelist : globalitemunitofmeasurevw) {
		measure += "<option value='" + measurelist.getId()  + "'>"
				+  measurelist.getSearchname() + "</option>";
	}
	
	}
	
	
	catch(Exception e)
	{
		
	
	
	}
	
	
	
	
	
	if (measure.equals("") )
	{
		measure="<option value=''>Measure not Available</option>";
	}
	
	return measure;
}

	



@RequestMapping(value = "/fetchdrugmeasurekind/{drugid}", method = RequestMethod.GET, produces = "text/html")
@ResponseBody
public String ajaxfetchdrugmeasurekind(@PathVariable("drugid") Integer drugid, Model model,
		RedirectAttributes redirectAttributes) {
	
	//String measure = "<option value=''>Select Measure</option>";
	String measure="";
	
//	model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
	GlobalItem gitem = this.globalitemBo.getGlobalItemById(drugid);
	List<GlobalItemUnitofMeasureVw> globalitemunitofmeasurevw = this.globalitemunitBo.fetchAllByItemIdvw(drugid);
	
	//System.out.println("Ajax Size Controller");
	//System.out.println(list.size());
	try 
	{
	for (GlobalItemUnitofMeasureVw measurelist : globalitemunitofmeasurevw) {
		measure += "<option value='" + measurelist.getId()  + "'>"
				+  measurelist.getMeasurename() + "</option>";
	
	}
	
	}
	
	
	catch(Exception e)
	{
		
	
	
	}
	
	

	Integer q;
	
	System.out.print("mycode "  + gitem.getGlobalitemkind().getCode());
	q= gitem.getGlobalitemkind().getCode().equals("brad") ? 0: 1;
	

	if (measure.equals("") )
	{
		measure="<option value=''>Measure not Available</option>";
	}
	
	return measure + "##" + q;
}

	@RequestMapping(value = "/fetchclockedusers/{unitid}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory(@PathVariable("unitid") Integer unitid, Model model,
			RedirectAttributes redirectAttributes) {
		
		String itemcatStr = "<option value=''>Select Category</option>";
		String clocked="";
		
//		model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<ClockAssignment> clockuser = this.clockuserBo.fetchAllByUnit(unitid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (ClockAssignment cuser : clockuser) {
			clocked += "<option value='" + cuser.getUserprofile().getUserId()  + "'>"
					+  cuser.getUserprofile().getLastName() +' '  +  cuser.getUserprofile().getOtherNames() + "</option>";
		}
		
		}
		
		
		catch(Exception e)
		{
			
		
		
		}
		
		
		
		
		
		
		
		return clocked;
	}



}
