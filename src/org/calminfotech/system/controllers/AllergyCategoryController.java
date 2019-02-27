package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javassist.runtime.Cflow;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.AllergyCategoryBo;
import org.calminfotech.system.boInterface.AllergyTypeBo;

import org.calminfotech.system.boInterface.AllergyTypeBo;
import org.calminfotech.system.forms.AllergyCategoryForm;

import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.system.models.AllergyType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.State;
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
@RequestMapping(value = "/system/allergycategory")
@Layout(value = "layouts/form_wizard_layout")
public class AllergyCategoryController {

	@Autowired
	private AllergyCategoryBo allergyCategoryBo;

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

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
		model.addAttribute("category", allergyCategoryBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/allergycategory/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("category", allergyCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/allergycategory/indexall";
	}
	
	

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
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
		model.addAttribute("allergyCategoryForm", cForm);
		return "system/allergycategory/viewcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("allergytype",allergyTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", AllergyCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("allergyCategoryForm", new AllergyCategoryForm());
		return "system/allergycategory/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("allergyCategoryForm") AllergyCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/allergycategory/add";
		}
		
		

		AllergyCategory category = new AllergyCategory();
		System.out.print("Form value");
		System.out.print(cForm.getCategoryName());
		
		System.out.print("org ide");
		
		System.out.print(userIdentity.getOrganisation().getId());
		
        AllergyType  catType =this.allergyTypeBo.getAllergyTypeById(cForm.getAllergyTypeId());
	

		category.setParentCategoryId(cForm.getParentCategoryId());
		
		category.setOrganisation(userIdentity.getOrganisation());
		
		category.setCategoryName(cForm.getCategoryName());

	category.setDescription(cForm.getCategoryDescription());
		
		category.setCreatedBy(userIdentity.getUsername());
		category.setAllergyTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		allergyCategoryBo.save(category);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " added Successfully!");
		return "redirect:/system/allergycategory/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		// AllergyCategory gCategory = AllergyCategoryBo.getGlobalCategoryItemById(id);
		
		AllergyCategory category = allergyCategoryBo.getAllergyCategoryById(id);

	//	Integer parent = category.getCategoryId();

	if (category == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/allergycategory";
	}
	
	
		AllergyCategoryForm cForm = new AllergyCategoryForm();
		
		cForm.setCategoryId(category.getCategoryId());
        cForm.setAllergyTypeId(category.getAllergyTypeId().getAllergyTypeId());
        
        cForm.setParentCategoryId(category.getParentCategoryId());
        
     	cForm.setCategoryDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());

	    model.addAttribute("categories", allergyCategoryBo.fetchAllByOrganisationByCategoryType(category.getAllergyTypeId().getAllergyTypeId()));
		model.addAttribute("allergytype", allergyTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("allergyCategoryForm", cForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "AllergyCategory", cForm);

		return "system/allergycategory/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("allergyCategoryForm") AllergyCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//AllergyCategory category = this.AllergyCategoryBo.getAllergyCategoryById(cForm.getParentCategoryId());

		cForm.setModifiedBy(userIdentity.getUsername());
		cForm.setModifiedDate(new GregorianCalendar().getTime());

		
		  AllergyType  catType =this.allergyTypeBo.getAllergyTypeById(cForm.getAllergyTypeId());
			
		  AllergyCategory category = allergyCategoryBo.getAllergyCategoryById(id);
		  
			category.setParentCategoryId(cForm.getParentCategoryId());
			
			category.setOrganisation(userIdentity.getOrganisation());
			
			category.setCategoryName(cForm.getCategoryName());

		category.setDescription(cForm.getCategoryDescription());
			
			category.setModifiedBy(userIdentity.getUsername());
			category.setAllergyTypeId(catType);
		
		
			allergyCategoryBo.update(category);

		
		
		 auditor.after(httpRequest, "AllergyCategory", cForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " edited Successfully!");
		return "redirect:/system/allergycategory/index";
	}

	//@RequestMapping(value = "/delete/{id}")
	//public String delete(@PathVariable("id") Integer id, Model model,
	//		RedirectAttributes redirectAttributes) {
	//	AllergyCategory gCategory = AllergyCategoryBo
	//			.getAllergyCategoryById(id);
	////	if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Allergycategory/index";
		//}
		
	//	AllergyCategoryForm cForm = new AllergyCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//cForm.setCategoryName(gCategory.getCategoryName());
		//model.addAttribute("gCategory", gCategory);
	//	model.addAttribute("cForm", cForm);
//		return "system/Allergycategory/delete";
	//}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") AllergyCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	AllergyCategory gCategory = allergyCategoryBo.getAllergyCategoryById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/Allergycategory/index";
	//	}
		
		
		gCategory.setModifiedBy(userIdentity.getUsername());
		gCategory.setModifiedDate(new GregorianCalendar().getTime());
		gCategory.setDeleted(true);
		allergyCategoryBo.update(gCategory);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category deleted");
		return "redirect:/system/allergycategory/index";
	}
	
	@RequestMapping(value = "/refreshallergycategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshAllergycategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call allergycategory_outerrecursive(?)}");

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
