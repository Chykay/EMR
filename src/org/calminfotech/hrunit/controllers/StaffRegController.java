package org.calminfotech.hrunit.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
//import org.calminfotech.patient.models.PatientAllergyView;

import org.calminfotech.hrunit.forms.UnitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.Staffgroup;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.forms.StaffRegForm;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StaffemploymentmodeList;
import org.calminfotech.utils.StaffgroupList;
import org.calminfotech.utils.StaffgrouprankingList;
import org.calminfotech.utils.StaffstatusList;
import org.calminfotech.utils.StaffgroupspecializationList;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@RequestMapping(value = "/hrunit/staffreg")
public class StaffRegController {
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	OrganisationBo organisationBo;
	
	@Autowired
	StaffRegBoInterface staffRegBo;
	
	@Autowired
	UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private StaffstatusList	staffstatusBo;
	
	@Autowired
    private StaffgroupList	staffgroupBo;
	
	@Autowired
    private StaffgrouprankingList	staffgrouprankingBo;
	
	
	@Autowired
    private StaffgroupspecializationList	staffgroupspecializationBo;
	

	@Autowired
    private StaffemploymentmodeList	staffemploymentBo;
	
	@Autowired
    private MaritalStatusList	maritalstatusBo;
	
	
	
	@Autowired
    private HrunitCategoryBo 	staffunitBo;
	

	@Autowired
	private Authorize authorize;


	@Autowired
	private GenderList genderBo;
	
	
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model,RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("HRS000")) 
		{
		
				alert.setAlert(redirectAttributes, Alert.WARNING,
						"You have no permission to do this");
				
				return "redirect:/";
			
		}
		List<StaffRegistration> staffRegistration = this.staffRegBo
				.fetchTop50byOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("staffreg", staffRegistration);
		return "hrunit/staffreg/index";
	
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("HRS000")) 
		{
		
				alert.setAlert(redirectAttributes, Alert.WARNING,
						"You have no permission to do this");
				
				return "redirect:/";
			
		}
		model.addAttribute("staffreg", staffRegBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "hrunit/staffreg/indexall";
	}
	

	
	
	
	

	@RequestMapping(value = "/save")
	@Layout(value = "layouts/form_wizard_layout")
	public String addsaveAction(Model model,RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("HRS001")) 
		{
		
				alert.setAlert(redirectAttributes, Alert.WARNING,
						"You have no permission to do this");
				
				return "redirect:/";
			
		}
		
		model.addAttribute("staffRegForm", new StaffRegForm());
		model.addAttribute("staffstatus", this.staffstatusBo.fetchAll());
		
		
		model.addAttribute("staffgroup", this.staffgroupBo.fetchAll());
		
		model.addAttribute("staffranking", this.staffgrouprankingBo.fetchAll());
		
		model.addAttribute("staffspecialization", this.staffgroupspecializationBo.fetchAll());
		
		model.addAttribute("staffmode", this.staffemploymentBo.fetchAll());
		model.addAttribute("maritalstatus", this.maritalstatusBo.fetchAll());
		
		
		model.addAttribute("staffunit", this.staffunitBo.fetchAllCatlistByOrganisation(userIdentity.getOrganisation().getId()));
		
		
		model.addAttribute("genders", this.genderBo.fetchAll());
		

		model.addAttribute("coy",this.organisationBo.fetchAll(0));
		
		
		

		return "hrunit/staffreg/add";
	}
	
	
	
	

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("staffRegForm") StaffRegForm staffregform,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		
			if (!authorize.isAllowed("HRS001")) 
			{
			
					alert.setAlert(redirectAttributes, Alert.WARNING,
							"You have no permission to do this");
					
					return "redirect:/";
				
			}
		try{

		
		//System.out.print("{}");
		//System.out.print(patientCodeGenerator.processNumber(patientForm.getDob()));
		//System.out.print("{}");
		
		if (result.hasErrors()) {
			
model.addAttribute("staffgroup", this.staffgroupBo.fetchAll());
		
		model.addAttribute("staffranking", this.staffgrouprankingBo.fetchAll());
		
		model.addAttribute("staffspecialization", this.staffgroupspecializationBo.fetchAll());
		return "redirect:/hrunit/save";	
		}
		
				
		StaffRegistration staffreg = new StaffRegistration();
		
		staffreg.setLastName(staffregform.getLastName());
		staffreg.setFirstName(staffregform.getFirstName());
		staffreg.setOtherName(staffregform.getOtherName());
		
		staffreg.setPractno(staffregform.getPractno());
		
		staffreg.setVerifymode(staffregform.getVerifymode());
		
		
		
		if (!staffregform.getEmail().equals(""))
		{
		staffreg.setEmail(staffregform.getEmail());
		}
		
		
		staffreg.setStaffCode(staffregform.getStaffCode());
		
		staffreg.setAddress(staffregform.getAddress());
		staffreg.setTelephone(staffregform.getTelephone());
		staffreg.setQualifications(staffregform.getQualifications());
			
		
		
		if (!staffregform.getDob().equals("") && DateUtils.isValidDate(staffregform.getDob()))
		
		{
			staffreg.setDob(DateUtils.formatStringToDate(staffregform.getDob()));
		}
		
		

		if (!staffregform.getDoe().equals("") && DateUtils.isValidDate(staffregform.getDoe()))
		
		{
			staffreg.setDoe(DateUtils.formatStringToDate(staffregform.getDoe()));
		}
		
		
		
		if (!staffregform.getStatusdate().equals("") && DateUtils.isValidDate(staffregform.getStatusdate()))
			
		{
			staffreg.setStatusdate(DateUtils.formatStringToDate(staffregform.getStatusdate()));
		}
		
		if (staffregform.getMaritalstatus_id().intValue()!=0)
		{
		staffreg.setMaritalststaus(this.maritalstatusBo.getMartialStatusById(staffregform.getMaritalstatus_id()));
		}
		
		
		if (staffregform.getStaffunit_id().intValue() != 0)
		{

		staffreg.setHrunitcategory(this.staffunitBo.getHrunitCategoryById(staffregform.getStaffunit_id()));
		}
		
		staffreg.setGender(this.genderBo.getGenderById(staffregform.getGender_id()));
		
		staffreg.setStaffmode(this.staffemploymentBo.getStaffemploymentmodeById(staffregform.getStaffmode_id()));
		
		
		if (staffregform.getStaffgroup_id().intValue() != 0)
				{
		staffreg.setStaffgroup(this.staffgroupBo.getStaffgroupById(staffregform.getStaffgroup_id()));
				}
		

		if (staffregform.getStaffranking_id().intValue() != 0)
				{
		
		staffreg.setStaffranking(this.staffgrouprankingBo.getStaffgrouprankingById(staffregform.getStaffranking_id()));
				}
		
		

		if (staffregform.getStaffspecialization_id().intValue() != 0)
				{
		
		staffreg.setStaffspecialization(this.staffgroupspecializationBo.getStaffgroupspecializationById(staffregform.getStaffspecialization_id()));
				}
		
		
		staffreg.setStaffstatus(this.staffstatusBo.getStaffstatusById(staffregform.getStaffstatus_id()));
		staffreg.setCreatedBy(userIdentity.getUsername());
		staffreg.setCreateDate(new GregorianCalendar().getTime());
		
		//staffreg.setOrganisationId(userIdentity.getOrganisation().getId());
		staffreg.setOrganisation(this.organisationBo.getOrganisationById(staffregform.getOrganisation_id()));
		staffRegBo.save(staffreg);
	
		
alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Staff Succesfully Added! Staff code id:  "
						+ staffreg.getId());

		}
		
		catch (Exception e)
		{
			
			alert.setAlert(redirectAttributes, Alert.DANGER, e.getMessage() );
		}
		
		return "redirect:/hrunit/staffreg/index";
		
		}
	
	
	
	
	
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {
		List<StaffRegistration> staffRegistration = this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("staffreg", staffRegistration);
		return "staffreg/index";
	}

	
	
	
	
	
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add")
	public String addAction(Model model) throws HibernateException, SQLException {
		 System.out.println("Before query");
		  

		  Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall(
		      "{?= call unit_outerrecursiveproc}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		model.addAttribute("staffRegForm", new StaffRegForm());
		//model.addAttribute("unit", unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));

		return "staffreg/add";
	}
	
	
	

	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET )
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest httprequest) {
		
			if (!authorize.isAllowed("HRS003")) 
			{
			
					alert.setAlert(redirectAttributes, Alert.WARNING,
							"You have no permission to do this");
					
					return "redirect:/";
				
			}
		StaffRegistration staffreg = this.staffRegBo.getStaffById(id);
	
		StaffRegForm  staffregform = new StaffRegForm();
		
		if (staffreg.getStaffgroup() != null)
		{
		Staffgroup staffgroup  = staffgroupBo.getStaffgroupById(staffreg.getStaffgroup().getId());
		model.addAttribute("staffranking",staffgroup.getStaffgroupranking());
		model.addAttribute("staffspecialization",staffgroup.getStaffgroupspecialization());
		
		}
		else
		{
		//	model.addAttribute("staffranking",null);
		//	model.addAttribute("staffspecialization",staffgroup.getStaffgroupspecialization());
			
		}
		
		
		if (null == staffreg) {
			alert.setAlert(redirectAttributes, Alert.DANGER, "Invalid resource");
		}
		
		
		
		model.addAttribute("staffstatus", this.staffstatusBo.fetchAll());
		model.addAttribute("staffgroup", this.staffgroupBo.fetchAll());
		
		model.addAttribute("staffmode", this.staffemploymentBo.fetchAll());
		model.addAttribute("maritalstatus", this.maritalstatusBo.fetchAll());
		model.addAttribute("staffunit", this.staffunitBo.fetchAllCatlistByOrganisation(userIdentity.getOrganisation().getId()));
		
		
		model.addAttribute("genders", this.genderBo.fetchAll());
		
		
		

		staffregform.setLastName(staffreg.getLastName());
		staffregform.setFirstName(staffreg.getFirstName());
		staffregform.setOtherName(staffreg.getOtherName());
		staffregform.setPractno(staffreg.getPractno());
		staffregform.setVerifymode(staffreg.getVerifymode());
		
		
		
		if (!staffreg.getEmail().equals("") && staffreg.getEmail()!=null )
		{
		staffregform.setEmail(staffreg.getEmail());
		}
		
		
		staffregform.setStaffCode(staffreg.getStaffCode());
		
		staffregform.setAddress(staffreg.getAddress());
		staffregform.setTelephone(staffreg.getTelephone());
		staffregform.setQualifications(staffregform.getQualifications());

		if (staffreg.getDob() !=null)
		{
		staffregform.setDob(DateUtils.formatDateToString(staffreg.getDob()));
		}
		
		if (staffreg.getDoe() !=null)
		{
		staffregform.setDoe(DateUtils.formatDateToString(staffreg.getDoe()));
		}
		
		
		if (staffreg.getStatusdate() !=null)
		{
		staffregform.setStatusdate(DateUtils.formatDateToString(staffreg.getStatusdate()));
		}
		
		if (staffreg.getMaritalststaus() !=null)
		{
		staffregform.setMaritalstatus_id(staffreg.getMaritalststaus().getId());
		}
		
		
		if (staffreg.getHrunitcategory() !=null)
		{

		staffregform.setStaffunit_id(staffreg.getHrunitcategory().getCategoryId());
		}
		
		
		staffregform.setQualifications(staffreg.getQualifications());
		
		
		staffregform.setGender_id(staffreg.getGender().getId());
		
		staffregform.setStaffmode_id(staffreg.getStaffmode().getId());
		
		if (staffreg.getStaffgroup() !=null)
		{
			staffregform.setStaffgroup_id(staffreg.getStaffgroup().getId());
		}
		
		
		if (staffreg.getStaffranking() !=null)
		{
		staffregform.setStaffranking_id(staffreg.getStaffranking().getId());
		}
		
		
		if (staffreg.getStaffspecialization() !=null)
		{
		
		staffregform.setStaffspecialization_id(staffreg.getStaffspecialization().getId());
		}
		
		staffregform.setStaffstatus_id(staffreg.getStaffstatus().getStaffstatus_id());
		//staffregform.setCreatedBy(userIdentity.getUsername());
		//staffregform.setCreateDate(new GregorianCalendar().getTime());
		
	//	staffregform.setOrganisationId(userIdentity.getOrganisation().getId());
		model.addAttribute("staffRegForm", staffregform);
		
		auditor.before(httprequest, "StaffForm", staffregform);

		return "hrunit/staffreg/edit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("staffRegForm") StaffRegForm staffregform,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httprequest) {
		
			if (!authorize.isAllowed("HRS003")) 
			{
			
					alert.setAlert(redirectAttributes, Alert.WARNING,
							"You have no permission to do this");
					
					return "redirect:/";
				
			}


		
		/*
		model.addAttribute("staffstatus", this.staffstatusBo.fetchAll());
		model.addAttribute("staffgroup", this.staffgroupBo.fetchAll());
		model.addAttribute("staffranking", this.staffgrouprankingBo.fetchAll());
		model.addAttribute("staffspecilaization", this.staffgroupspecializationBo.fetchAll());
		model.addAttribute("staffmode", this.staffemploymentBo.fetchAll());
		model.addAttribute("maritalstatus", this.maritalstatusBo.fetchAll());
		model.addAttribute("staffunit", this.staffunitBo.fetchAllCatlistByOrganisation(userIdentity.getOrganisation().getId()));
		
		
		model.addAttribute("genders", this.genderBo.fetchAll());
		
		*/
		if (result.hasErrors()) {
			
			model.addAttribute("staffgroup", this.staffgroupBo.fetchAll());
					
					model.addAttribute("staffranking", this.staffgrouprankingBo.fetchAll());
					
					model.addAttribute("staffspecialization", this.staffgroupspecializationBo.fetchAll());
					return "redirect:/hrunit/edit/"+id;	
					}
		
		
		StaffRegistration staffreg = this.staffRegBo.getStaffById(id);
	
	staffreg.setLastName(staffregform.getLastName());
	staffreg.setFirstName(staffregform.getFirstName());
	staffreg.setOtherName(staffregform.getOtherName());
	
	
	if (!staffregform.getEmail().equals(""))
	{
	staffreg.setEmail(staffregform.getEmail());
	}
	
	else
	{
		staffreg.setEmail(null);
	}
	
	
	staffreg.setStaffCode(staffregform.getStaffCode());
	
	staffreg.setAddress(staffregform.getAddress());
	staffreg.setTelephone(staffregform.getTelephone());
	staffreg.setQualifications(staffregform.getQualifications());
	
	
	
	if (!staffregform.getDob().equals("") && DateUtils.isValidDate(staffregform.getDob()))
	
	{
		staffreg.setDob(DateUtils.formatStringToDate(staffregform.getDob()));
	}
	
	else
	{
		staffreg.setDob(null);
	}
	

	if (!staffregform.getDoe().equals("") && DateUtils.isValidDate(staffregform.getDoe()))
	
	{
		staffreg.setDoe(DateUtils.formatStringToDate(staffregform.getDoe()));
		
	}
	
	else
	{
		staffreg.setDoe(null);
	}
		
	
	if (!staffregform.getStatusdate().equals("") && DateUtils.isValidDate(staffregform.getStatusdate()))
		
	{
		staffreg.setStatusdate(DateUtils.formatStringToDate(staffregform.getStatusdate()));
	}
	else
	{
		staffreg.setStatusdate(null);
	}
	
	
	if (staffregform.getMaritalstatus_id().intValue()!=0)
	{
	staffreg.setMaritalststaus(this.maritalstatusBo.getMartialStatusById(staffregform.getMaritalstatus_id()));
	}
	else
	{
		staffreg.setMaritalststaus(null);
	}
	
	if (staffregform.getStaffunit_id().intValue()!=0)
		
	{
	staffreg.setHrunitcategory(this.staffunitBo.getHrunitCategoryById(staffregform.getStaffunit_id()));
	}
	else
	{
		staffreg.setHrunitcategory(null);
	}
	
	
	staffreg.setGender(this.genderBo.getGenderById(staffregform.getGender_id()));
	
	staffreg.setStaffmode(this.staffemploymentBo.getStaffemploymentmodeById(staffregform.getStaffmode_id()));
	
	
	if (staffregform.getStaffgroup_id().intValue() != 0)
	{
staffreg.setStaffgroup(this.staffgroupBo.getStaffgroupById(staffregform.getStaffgroup_id()));
	}
	else
	{
		staffreg.setStaffgroup(null);
	}

	
	
if (staffregform.getStaffranking_id().intValue() != 0)
	{

staffreg.setStaffranking(this.staffgrouprankingBo.getStaffgrouprankingById(staffregform.getStaffranking_id()));
	}
else
{
	staffreg.setStaffranking(null);
}



if (staffregform.getStaffspecialization_id().intValue() != 0)
	{

staffreg.setStaffspecialization(this.staffgroupspecializationBo.getStaffgroupspecializationById(staffregform.getStaffspecialization_id()));
	}
else
{
	staffreg.setStaffspecialization(null);
}


	staffreg.setStaffstatus(this.staffstatusBo.getStaffstatusById(staffregform.getStaffstatus_id()));
	staffreg.setCreatedBy(userIdentity.getUsername());
	staffreg.setCreateDate(new GregorianCalendar().getTime());
	
//	staffreg.setOrganisation(this.organisationBo.getOrganisationById(staffregform.getOrganisation_id()));
	
	staffRegBo.update(staffreg);

	
		
		
		
			auditor.after(httprequest, "StaffForm", staffregform,
					userIdentity.getUsername(),id);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Staff updated");
System.out.print("updadate");
		
		return "redirect:/hrunit/staffreg/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
	
			if (!authorize.isAllowed("HRS002")) 
			{
			
					alert.setAlert(redirectAttributes, Alert.WARNING,
							"You have no permission to do this");
					
					return "redirect:/";
				
			}
		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(id);
		
		
		model.addAttribute("staffreg", staffRegistration);
		model.addAttribute("id", id);
		
		return "staffreg/view";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") int id,
			Model model, RedirectAttributes redirectAttributes) {

			if (!authorize.isAllowed("HRS004")) 
			{
			
					alert.setAlert(redirectAttributes, Alert.WARNING,
							"You have no permission to do this");
					
					return "redirect:/";
				
			}
		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(id);
		StaffRegForm staffRegForm = new StaffRegForm();
		staffRegForm.setId(id);
		staffRegForm.setFirstName(staffRegistration.getFirstName());

		model.addAttribute("staffRegForm", staffRegForm);
		model.addAttribute("staffreg", staffRegistration);

		return "staffreg/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeAction(
			@ModelAttribute("staffRegForm") StaffRegForm staffRegForm,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("HRS004")) 
		{
		
				alert.setAlert(redirectAttributes, Alert.WARNING,
						"You have no permission to do this");
				
				return "redirect:/";
			
		}

		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(staffRegForm.getId());

		if (null == staffRegistration) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/staffreg/list";
		}

		this.staffRegBo.delete(staffRegistration);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"staff Deleted!");

		return "redirect:/staffreg/list";
	}
}
