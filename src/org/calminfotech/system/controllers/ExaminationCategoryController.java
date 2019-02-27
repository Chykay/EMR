package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javassist.runtime.Cflow;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.ExaminationCategoryBo;
import org.calminfotech.system.boInterface.ExaminationTypeBo;

import org.calminfotech.system.boInterface.ExaminationTypeBo;
import org.calminfotech.system.forms.ExaminationCategoryForm;

import org.calminfotech.system.models.ExaminationCategory;
import org.calminfotech.system.models.ExaminationType;
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
@RequestMapping(value = "/system/examinationcategory")
@Layout(value = "layouts/form_wizard_layout")
public class ExaminationCategoryController {

	@Autowired
	private ExaminationCategoryBo examinationCategoryBo;

	@Autowired
	private ExaminationTypeBo examinationTypeBo;

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
		model.addAttribute("category", examinationCategoryBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/examinationcategory/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("category", examinationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/examinationcategory/indexall";
	}
	
	

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
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
		model.addAttribute("examinationCategoryForm", cForm);
		return "system/examinationcategory/viewcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("examinationtype",examinationTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("examinationCategoryForm", new ExaminationCategoryForm());
		return "system/examinationcategory/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("examinationCategoryForm") ExaminationCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
try
{
		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/examinationcategory/add";
		}
		
		

		ExaminationCategory category = new ExaminationCategory();
		System.out.print("Form value");
		System.out.print(cForm.getCategoryName());
		
		System.out.print("org ide");
		
		System.out.print(userIdentity.getOrganisation().getId());
		
        ExaminationType  catType =this.examinationTypeBo.getExaminationTypeById(cForm.getExaminationTypeId());
	

		category.setParentCategoryId(cForm.getParentCategoryId());
		
		category.setOrganisation(userIdentity.getOrganisation());
		
		category.setCategoryName(cForm.getCategoryName());

	category.setDescription(cForm.getCategoryDescription());
		
		category.setCreatedBy(userIdentity.getUsername());
		category.setExaminationTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		examinationCategoryBo.save(category);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " added Successfully!");
	}
		catch (Exception e)
		{

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());
			
		}
		
		
		return "redirect:/system/examinationcategory/index";
		
		
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		// ExaminationCategory gCategory = ExaminationCategoryBo.getGlobalCategoryItemById(id);
		
		ExaminationCategory category = examinationCategoryBo.getExaminationCategoryById(id);

	//	Integer parent = category.getCategoryId();

	if (category == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/examinationcategory";
	}
	
	
		ExaminationCategoryForm cForm = new ExaminationCategoryForm();
		
		cForm.setCategoryId(category.getCategoryId());
        cForm.setExaminationTypeId(category.getExaminationTypeId().getExaminationTypeId());
        
        cForm.setParentCategoryId(category.getParentCategoryId());
        
     	cForm.setCategoryDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());

	    model.addAttribute("categories", examinationCategoryBo.fetchAllByOrganisationByCategoryType(category.getExaminationTypeId().getExaminationTypeId()));
		model.addAttribute("examinationtype", examinationTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("examinationCategoryForm", cForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "ExaminationCategory", cForm);

		return "system/examinationcategory/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("examinationCategoryForm") ExaminationCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		try
		{
		//ExaminationCategory category = this.ExaminationCategoryBo.getExaminationCategoryById(cForm.getParentCategoryId());

		cForm.setModifiedBy(userIdentity.getUsername());
		cForm.setModifiedDate(new GregorianCalendar().getTime());

		
		  ExaminationType  catType =this.examinationTypeBo.getExaminationTypeById(cForm.getExaminationTypeId());
			
		  ExaminationCategory category = examinationCategoryBo.getExaminationCategoryById(id);
	

		  if (category.getOrganisation()==userIdentity.getOrganisation() || userIdentity.getOrganisation().getId()==1 )
		  {
			category.setParentCategoryId(cForm.getParentCategoryId());
			
			category.setOrganisation(userIdentity.getOrganisation());
			
			category.setCategoryName(cForm.getCategoryName());

		category.setDescription(cForm.getCategoryDescription());
			
			category.setModifiedBy(userIdentity.getUsername());
			category.setExaminationTypeId(catType);
		
		
			examinationCategoryBo.update(category);

		
		
		 auditor.after(httpRequest, "ExaminationCategory", cForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " edited Successfully!");
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
			
	return "redirect:/system/examinationcategory/index";
	}

	//@RequestMapping(value = "/delete/{id}")
	//public String delete(@PathVariable("id") Integer id, Model model,
	//		RedirectAttributes redirectAttributes) {
	//	ExaminationCategory gCategory = ExaminationCategoryBo
	//			.getExaminationCategoryById(id);
	////	if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Examinationcategory/index";
		//}
		
	//	ExaminationCategoryForm cForm = new ExaminationCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//cForm.setCategoryName(gCategory.getCategoryName());
		//model.addAttribute("gCategory", gCategory);
	//	model.addAttribute("cForm", cForm);
//		return "system/Examinationcategory/delete";
	//}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") ExaminationCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	ExaminationCategory gCategory = examinationCategoryBo.getExaminationCategoryById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/Examinationcategory/index";
	//	}
	gCategory.setModifiedBy(userIdentity.getUsername());
	gCategory.setModifiedDate(new GregorianCalendar().getTime());
	gCategory.setDeleted(true);
	examinationCategoryBo.update(gCategory);
	
	
	
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category deleted");
		return "redirect:/system/examinationcategory/index";
	}
	
	@RequestMapping(value = "/refreshexaminationcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshExaminationcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call examinationcategory_outerrecursive(?)}");

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
