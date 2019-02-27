package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import org.calminfotech.utils.Test;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientTelephoneForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.AllergyBo;
import org.calminfotech.system.boInterface.AllergyCategoryBo;
import org.calminfotech.system.boInterface.AllergyTypeBo;


import org.calminfotech.system.forms.AllergyCategoryForm;
import org.calminfotech.system.forms.AllergyForm;


import org.calminfotech.system.models.Allergy;
import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.system.models.AllergyType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/system/allergy")
public class AllergyController {

	@Autowired
	private AllergyCategoryBo allergyCategoryBo;
	
	@Autowired
	private AllergyBo allergyBo;
	

	@Autowired
	private AllergyBo allergyBou;

	@Autowired
	private AllergyTypeBo allergyTypeBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;

	public Test	test;

	
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
	
	
	//test.setH("2222222jjjjjj");
	 
model.addAttribute("allergy", allergyBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/allergy/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
	//	/System.out.println("ttt");
	//	System.out.println(test.getH());
		//System.out.println("ttt");
		//model.addAttribute("ttt",j.getH());
		model.addAttribute("allergy", allergyBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/allergy/indexall";
	}
	
	
/*
	@RequestMapping(value = "/view/{Id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("Id") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		//AllergyCategory gCategory = AllergyCategoryBo.getAllergyCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Allergycategory";
		//		}
		AllergyCategoryForm cForm = new AllergyCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/Allergycategory/viewcategory";
	}
*/
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("allergytype", allergyTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", AllergyCategoryBo.fetcAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("allergyForm", new AllergyForm());
		return "system/allergy/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("allergyForm") AllergyForm dForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/allergy/add";
		}
		
		

		Allergy Allergy = new Allergy();
		AllergyCategory category = allergyCategoryBo.getAllergyCategoryById(dForm.getParentCategoryId());
        AllergyType  catType =this.allergyTypeBo.getAllergyTypeById(dForm.getAllergytypeId());
	

        Allergy.setAllergyCategory(category);
        Allergy.setOrganisation(userIdentity.getOrganisation());
	
        System.out.println("name");
        System.out.println(dForm.getAllergyName());
        System.out.println("name");
        
	Allergy.setAllergyName(dForm.getAllergyName());

    System.out.println("desc");
    System.out.println(dForm.getAllergyDescription());
    System.out.println("desc");

		Allergy.setDescription(dForm.getAllergyDescription());
		

		Allergy.setCreatedBy(userIdentity.getUsername());
		Allergy.setAllergyTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		allergyBo.save(Allergy);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getAllergyName() + " added Successfully!");
		return "redirect:/system/allergy/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
	
	//	AllergyCategory gCategory = AllergyCategoryBo.getGlobalCategoryItemById(id);
		
		Allergy allergy = allergyBo.getAllergyById(id);

	//	Integer parent = category.getCategoryId();

		
	if (allergy == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/allergy";
	}
	
	
		AllergyForm dForm = new AllergyForm();
		
		dForm.setParentCategoryId(allergy.getAllergyCategory().getCategoryId());
     dForm.setAllergytypeId(allergy.getAllergyType().getAllergyTypeId());
        
     
        
	dForm.setAllergyDescription(allergy.getDescription());
		dForm.setAllergyName(allergy.getAllergyName());

	model.addAttribute("categories", allergyCategoryBo.fetchAllByOrganisationByCategoryType(allergy.getAllergyType().getAllergyTypeId()));
		model.addAttribute("allergyType", allergyTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("allergyForm", dForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Allergy", dForm);

		return "system/allergy/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("dForm") AllergyForm dForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		
		dForm.setModifiedBy(userIdentity.getUsername());
		dForm.setModifiedDate(new GregorianCalendar().getTime());

		AllergyCategory category = allergyCategoryBo.getAllergyCategoryById(dForm.getParentCategoryId());
        
		  AllergyType  catType =this.allergyTypeBo.getAllergyTypeById(dForm.getAllergytypeId());
			
		  Allergy Allergy = allergyBo.getAllergyById(id);
		  
		  Allergy.setAllergyName(dForm.getAllergyName());

		  Allergy.setDescription(dForm.getAllergyDescription());

		  Allergy.setAllergyCategory(category);
			
		  Allergy.setOrganisation(userIdentity.getOrganisation());
			
		  Allergy.setModifiedBy(userIdentity.getUsername());
		  
		  Allergy.setModifiedDate(new GregorianCalendar().getTime());
		  Allergy.setAllergyTypeId(catType);
		
		
			allergyBo.update(Allergy);

		
		
		 auditor.after(httpRequest, "Allergy", dForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getAllergyName() + " edited Successfully!");
		return "redirect:/system/Allergy/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") AllergyCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	Allergy allergy = allergyBo.getAllergyById(id);

		
		allergy.setModifiedBy(userIdentity.getUsername());
		allergy.setModifiedDate(new GregorianCalendar().getTime());
		allergy.setDeleted(true);
		allergyBo.update(allergy);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Diagnosis  deleted");
		return "redirect:/system/allergy/index";
	}
	
	@RequestMapping(value = "/refreshAllergycategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshAllergycategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call Allergycategory_outerrecursive(?)}");

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
		
//		model.addAttribute("AllergyType",AllergyTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", AllergyCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<AllergyCategory> list = allergyCategoryBo.fetchAllByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (AllergyCategory itemcat : list) {
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
