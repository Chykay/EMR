package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javassist.runtime.Cflow;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.GlobalItemCategoryBo;

import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.forms.GlobalItemCategoryForm;

import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemKind;
import org.calminfotech.system.models.GlobalItemType;
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
@RequestMapping(value = "/system/globalitemcategory")
@Layout(value = "layouts/form_wizard_layout")
public class GlobaltemCategoryController {

	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

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
		model.addAttribute("category", globalItemCategoryBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/globalitemcategory/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("category", globalItemCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/globalitemcategory/indexall";
	}
	
	

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		//GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalItemCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/globalitemcategory";
		//		}
		GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/globalitemcategory/viewcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("globalItemType",globalItemTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", globalItemCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("GlobalItemCategoryForm", new GlobalItemCategoryForm());
		return "system/globalitemcategory/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("GlobalItemCategoryForm") GlobalItemCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		try
		{

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/globalitemcategory/add";
		}
		
		

		GlobalItemCategory category = new GlobalItemCategory();
		System.out.print("Form value");
		System.out.print(cForm.getCategoryName());
		System.out.print(cForm.getCategoryTypeId());
		System.out.print("org ide");
		
		System.out.print(userIdentity.getOrganisation().getId());
		
        GlobalItemType  catType =this.globalItemTypeBo.getGlobalItemTypeById(cForm.getCategoryTypeId());
	

		category.setParentCategoryId(cForm.getParentCategoryId());
		
		category.setOrganisation(userIdentity.getOrganisation());
		
		category.setCategoryName(cForm.getCategoryName());

	category.setDescription(cForm.getCategoryDescription());
		
		category.setCreatedBy(userIdentity.getUsername());
		category.setGlobalitemTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		globalItemCategoryBo.save(category);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " added Successfully!");
		}
		
		catch (Exception e)
		{

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());
			
		}
		
		return "redirect:/system/globalitemcategory/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		// GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalCategoryItemById(id);
		
		GlobalItemCategory category = globalItemCategoryBo.getGlobalItemCategoryById(id);

	//	Integer parent = category.getCategoryId();

	if (category == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/globalitemcategory";
	}
	
		GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		
		cForm.setCategoryId(category.getCategoryId());
     cForm.setCategoryTypeId(category.getGlobalitemTypeId().getGlobalitemTypeId());
        
     cForm.setParentCategoryId(category.getParentCategoryId());
        
	cForm.setCategoryDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());

	model.addAttribute("categories", globalItemCategoryBo.fetchAllByOrganisationByCategoryType(category.getGlobalitemTypeId().getGlobalitemTypeId()));
		model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("GlobalItemCategoryForm", cForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Category", cForm);

		return "system/globalitemcategory/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("cForm") GlobalItemCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
try
{
		//GlobalItemCategory category = this.globalItemCategoryBo.getGlobalItemCategoryById(cForm.getParentCategoryId());

		cForm.setModifiedBy(userIdentity.getUsername());
		cForm.setModifiedDate(new GregorianCalendar().getTime());

		
		  GlobalItemType  catType =this.globalItemTypeBo.getGlobalItemTypeById(cForm.getCategoryTypeId());
			
		  GlobalItemCategory category = globalItemCategoryBo.getGlobalItemCategoryById(id);
		  if (category.getOrganisation()==userIdentity.getOrganisation() || userIdentity.getOrganisation().getId()==1 )
		  {
			category.setParentCategoryId(cForm.getParentCategoryId());
			
			category.setOrganisation(userIdentity.getOrganisation());
			
			category.setCategoryName(cForm.getCategoryName());

		category.setDescription(cForm.getCategoryDescription());
			
			category.setCreatedBy(userIdentity.getUsername());
			category.setGlobalitemTypeId(catType);
		
		
			globalItemCategoryBo.update(category);

		
		
		 auditor.after(httpRequest, "HMO", cForm, userIdentity.getUsername(),id);
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

		  
		return "redirect:/system/globalitemcategory/index";
	}

	//@RequestMapping(value = "/delete/{id}")
	//public String delete(@PathVariable("id") Integer id, Model model,
	//		RedirectAttributes redirectAttributes) {
	//	GlobalItemCategory gCategory = globalItemCategoryBo
	//			.getGlobalItemCategoryById(id);
	////	if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/globalitemcategory/index";
		//}
		
	//	GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//cForm.setCategoryName(gCategory.getCategoryName());
		//model.addAttribute("gCategory", gCategory);
	//	model.addAttribute("cForm", cForm);
//		return "system/globalitemcategory/delete";
	//}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") GlobalItemCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalItemCategoryById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/globalitemcategory/index";
	//	}
	
	gCategory.setModifiedBy(userIdentity.getUsername());
	gCategory.setModifiedDate(new GregorianCalendar().getTime());
	gCategory.setDeleted(true);
	globalItemCategoryBo.update(gCategory);
		//globalItemCategoryBo.delete(gCategory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category deleted");
		return "redirect:/system/globalitemcategory/index";
	}
	
	@RequestMapping(value = "/refreshglobalitemcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshglobalitemcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call globalitemcategory_outerrecursive(?)}");

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
		String itemcatStr = "<option value=''>Select Category..</option>";
		String itemkindStr = "<option value=''>Select Kind..</option>";
		
//		model.addAttribute("globalItemType",globalItemTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", globalItemCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<GlobalItemCategory> list = globalItemCategoryBo.fetchAllByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (GlobalItemCategory itemcat : list) {
			itemcatStr += "<option value='" + itemcat.getCategoryId() + "'>"
					+ itemcat.getCategoryName() + "</option>";
		}
		}
		catch(Exception e)
		{
			}
		
		
		
		List<GlobalItemKind> listkind = globalItemCategoryBo.fetchAllKindByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (GlobalItemKind kindcat : listkind) {
			itemkindStr += "<option value='" + kindcat.getCode() + "'>"
					+ kindcat.getName() + "</option>";
		}
		}
		catch(Exception e)
		{
			}
		
		
		
		return itemcatStr + "##" +itemkindStr ;
	}
}
