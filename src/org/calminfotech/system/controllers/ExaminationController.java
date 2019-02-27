package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientTelephoneForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.ExaminationBo;
import org.calminfotech.system.boInterface.ExaminationCategoryBo;
import org.calminfotech.system.boInterface.ExaminationTypeBo;


import org.calminfotech.system.forms.ExaminationCategoryForm;
import org.calminfotech.system.forms.ExaminationForm;
import org.calminfotech.system.forms.ExaminationResultSetupForm;
import org.calminfotech.system.forms.ExaminationSearchForm;
import org.calminfotech.system.forms.GlobalitemSearchForm;


import org.calminfotech.system.models.Examination;
import org.calminfotech.system.models.ExaminationCategory;
import org.calminfotech.system.models.ExaminationType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalUnitofMeasureList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Phonetype;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/examination")
public class ExaminationController {

	@Autowired
	private ExaminationCategoryBo examinationCategoryBo;
	
	@Autowired
	private ExaminationBo examinationBo;

	@Autowired
	private ExaminationTypeBo examinationTypeBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;


	@Autowired
	GlobalUnitofMeasureList gunitofMeasure;
	
	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
	
	
model.addAttribute("examination", examinationBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));

		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/examination/index";
	}
	
	/*
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("examination", examinationBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/examination/indexall";
	}
	
	*/
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("examinationType",this.examinationTypeBo.fetchAllByOrganisation());
		//model.addAttribute("global", globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		ExaminationSearchForm  pf = new ExaminationSearchForm();
		pf.setMysp(0);
		model.addAttribute("examinationSearch", pf);
		
		return "system/examination/indexall";
	}
	
	@RequestMapping(value = "/index/all", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String searchPatient(
			@ModelAttribute("examinationSearch") ExaminationSearchForm examinationSearchForm,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
	
//List patientList = searchBo.searchPatient(searchForm, session);
		
		model.addAttribute("examinationType",this.examinationTypeBo.fetchAllByOrganisation());
		List examinationList = searchUtilBo.searchExamination(examinationSearchForm, session);
			
		model.addAttribute("examination", examinationList);
		
		return "system/examination/indexall";
	}

	
	
	
/*
	@RequestMapping(value = "/view/{Id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("Id") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		//ExaminationCategory gCategory = ExaminationCategoryBo.getExaminationCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Examinationcategory";
		//		}
		ExaminationCategoryForm cForm = new ExaminationCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/Examinationcategory/viewcategory";
	}
*/
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("examinationtype", examinationTypeBo.fetchAllByOrganisation());

	model.addAttribute("categories", examinationCategoryBo.fetchAll (userIdentity.getOrganisation().getId()));
		
		model.addAttribute("examinationForm", new ExaminationForm());
		return "system/examination/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("examinationForm") ExaminationForm dForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
try 
{
		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/examination/add";
		}
		
		

		Examination Examination = new Examination();
		ExaminationCategory category = examinationCategoryBo.getExaminationCategoryById(dForm.getParentCategoryId());
        ExaminationType  catType =this.examinationTypeBo.getExaminationTypeById(dForm.getExaminationtypeId());
	

        Examination.setExaminationCategory(category);
        Examination.setOrganisation(userIdentity.getOrganisation());
	
        System.out.println("name");
        System.out.println(dForm.getExaminationName());
        System.out.println("name");
        
	Examination.setExaminationName(dForm.getExaminationName());

    System.out.println("desc");
    System.out.println(dForm.getExaminationDescription());
    System.out.println("desc");

		Examination.setDescription(dForm.getExaminationDescription());
		

		Examination.setCreatedBy(userIdentity.getUsername());
		Examination.setExaminationTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		examinationBo.save(Examination);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getExaminationName() + " added Successfully!");
	}
		catch (Exception e)
		{

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());
			
		}
		
		return "redirect:/system/examination/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
	
	//	ExaminationCategory gCategory = ExaminationCategoryBo.getGlobalCategoryItemById(id);
		
		Examination examination = examinationBo.getExaminationById(id);

	//	Integer parent = category.getCategoryId();

		
	if (examination == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/examination";
	}
	
	
		ExaminationForm dForm = new ExaminationForm();
		
		dForm.setParentCategoryId(examination.getExaminationCategory().getCategoryId());
     dForm.setExaminationtypeId(examination.getExaminationType().getExaminationTypeId());
        
     
        
	dForm.setExaminationDescription(examination.getDescription());
		dForm.setExaminationName(examination.getExaminationName());

	model.addAttribute("categories", examinationCategoryBo.fetchAllByOrganisationByCategoryType(examination.getExaminationType().getExaminationTypeId()));
		model.addAttribute("ExaminationType", examinationTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("examinationForm", dForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Examination", dForm);

		return "system/examination/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("dForm") ExaminationForm dForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		try 
		{
			
		 
		dForm.setModifiedBy(userIdentity.getUsername());
		dForm.setModifiedDate(new GregorianCalendar().getTime());

		ExaminationCategory category = examinationCategoryBo.getExaminationCategoryById(dForm.getParentCategoryId());
        
		  ExaminationType  catType =this.examinationTypeBo.getExaminationTypeById(dForm.getExaminationtypeId());
			
		  Examination examination = examinationBo.getExaminationById(id);
		
		  if (examination.getOrganisation()==userIdentity.getOrganisation() || userIdentity.getOrganisation().getId()==1 )
		  {
		  
		  examination.setExaminationName(dForm.getExaminationName());

		  examination.setDescription(dForm.getExaminationDescription());

		  examination.setExaminationCategory(category);
			
		  examination.setOrganisation(userIdentity.getOrganisation());
			
		  examination.setModifiedBy(userIdentity.getUsername());
		  
		  examination.setModifiedDate(new GregorianCalendar().getTime());
		  examination.setExaminationTypeId(catType);
		
		
			examinationBo.update(examination);

		
		
		 auditor.after(httpRequest, "Examination", dForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getExaminationName() + " edited Successfully!");
		  }
		  else
		  {
			  alert.setAlert(redirectAttributes, Alert.DANGER,
						"You are not the owner of the item. you cannot edit it.");
					  
		  }
		
	}
		  catch (Exception e)
			{

				alert.setAlert(redirectAttributes, Alert.DANGER,
						"May be Item exists " + e.getMessage());
				
			}
			
		  
		  
		return "redirect:/system/examination/index";
	
		
		
		  }

	
	

	@Layout("layouts/datatable")
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) 
	{

	Examination examination = this.examinationBo.getExaminationById(id);
	ExaminationResultSetupForm examForm = new ExaminationResultSetupForm();
	examForm.setExaminationId(examination.getExaminationId());
	model.addAttribute("exam",examination);
	model.addAttribute("examresultForm",examForm);
	model.addAttribute("unitM", gunitofMeasure.fetchAll());
	
	return "system/examination/view";
	}

	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") ExaminationCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	Examination examination = examinationBo.getExaminationById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/Examinationcategory/index";
	//	}
	
	  if (examination.getOrganisation()==userIdentity.getOrganisation() || userIdentity.getOrganisation().getId()==1 )
	  {
	examination.setModifiedBy(userIdentity.getUsername());
	examination.setModifiedDate(new GregorianCalendar().getTime());
	examination.setDeleted(true);
	examinationBo.update(examination);
	
	alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Diagnosis  deleted");
		
	}
	  else
	  {
		  alert.setAlert(redirectAttributes, Alert.DANGER,
					"You are not the owner of the item. you cannot edit it.");
				  
	  }
	return "redirect:/system/examination/index";
		
	}
	
	@RequestMapping(value = "/refreshExaminationcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshExaminationcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call Examinationcategory_outerrecursive(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		
		
		
		
	}
	
	@RequestMapping(value = "/fetchcategorybytype/{cattypeid}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory(@PathVariable("cattypeid") Integer cattypeid, Model model,
			RedirectAttributes redirectAttributes) {
		String itemcatStr = "<option value=''>Select Category</option>";
		
//		model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<ExaminationCategory> list = examinationCategoryBo.fetchAllByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (ExaminationCategory itemcat : list) {
			itemcatStr += "<option value='" + itemcat.getCategoryId() + "'>"
					+ itemcat.getCategoryName() + "</option>";
		}
		}
		catch(Exception e)
		{
			}
		
		
		
		
		 
		
		
		return itemcatStr;
	}
	
	
}
