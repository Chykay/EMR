package org.calminfotech.hrunit.controllers;

import java.sql.CallableStatement;
import org.calminfotech.utils.Test;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.calminfotech.hrunit.boInterface.HrunitBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.HrunitTypeBo;
import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.forms.HrunitForm;
import org.calminfotech.hrunit.models.Hrunit;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitType;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientTelephoneForm;
import org.calminfotech.patient.models.Patient;




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
@RequestMapping(value = "/system/hrunit")
public class HrunitController {

	@Autowired
	private HrunitCategoryBo hrunitCategoryBo;
	
	@Autowired
	private HrunitBo hrunitBo;
	

	@Autowired
	private HrunitBo hrunitBou;

	@Autowired
	private HrunitTypeBo hrunitTypeBo;

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
	 
model.addAttribute("hrunit", hrunitBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/hrunit/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
	//	/System.out.println("ttt");
	//	System.out.println(test.getH());
		//System.out.println("ttt");
		//model.addAttribute("ttt",j.getH());
		model.addAttribute("hrunit", hrunitBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/hrunit/indexall";
	}
	
	
/*
	@RequestMapping(value = "/view/{Id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("Id") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		//HrunitCategory gCategory = HrunitCategoryBo.getHrunitCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Hrunitcategory";
		//		}
		HrunitCategoryForm cForm = new HrunitCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/Hrunitcategory/viewcategory";
	}
*/
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		
		model.addAttribute("hrunittype", hrunitTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", HrunitCategoryBo.fetcAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("hrunitForm", new HrunitForm());

	
		
		
		
		
		return "system/hrunit/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("hrunitForm") HrunitForm dForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/hrunit/add";
		}
		
		

		Hrunit Hrunit = new Hrunit();
		HrunitCategory category = hrunitCategoryBo.getHrunitCategoryById(dForm.getParentCategoryId());
        HrunitType  catType =this.hrunitTypeBo.getHrunitTypeById(dForm.getHrunittypeId());
	

        Hrunit.setHrunitCategory(category);
        Hrunit.setOrganisationId(userIdentity.getOrganisation().getId());
	
        System.out.println("name");
        System.out.println(dForm.getHrunitName());
        System.out.println("name");
        
	Hrunit.setHrunitName(dForm.getHrunitName());

    System.out.println("desc");
    System.out.println(dForm.getHrunitDescription());
    System.out.println("desc");

		Hrunit.setDescription(dForm.getHrunitDescription());
		

		Hrunit.setCreatedBy(userIdentity.getUsername());
		Hrunit.setHrunitTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		hrunitBo.save(Hrunit);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getHrunitName() + " added Successfully!");
		return "redirect:/system/hrunit/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
	
	//	HrunitCategory gCategory = HrunitCategoryBo.getGlobalCategoryItemById(id);
		
		Hrunit hrunit = hrunitBo.getHrunitById(id);

	//	Integer parent = category.getCategoryId();

		
	if (hrunit == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/hrunit";
	}
	
	
		HrunitForm dForm = new HrunitForm();
		
		dForm.setParentCategoryId(hrunit.getHrunitCategory().getCategoryId());
     dForm.setHrunittypeId(hrunit.getHrunitType().getHrunitTypeId());
        
     
        
	dForm.setHrunitDescription(hrunit.getDescription());
		dForm.setHrunitName(hrunit.getHrunitName());

	model.addAttribute("categories", hrunitCategoryBo.fetchAllByOrganisationByCategoryType(hrunit.getHrunitType().getHrunitTypeId()));
		model.addAttribute("hrunitType", hrunitTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("hrunitForm", dForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Hrunit", dForm);

		return "system/hrunit/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("dForm") HrunitForm dForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		
		dForm.setModifiedBy(userIdentity.getUsername());
		dForm.setModifiedDate(new GregorianCalendar().getTime());

		HrunitCategory category = hrunitCategoryBo.getHrunitCategoryById(dForm.getParentCategoryId());
        
		  HrunitType  catType =this.hrunitTypeBo.getHrunitTypeById(dForm.getHrunittypeId());
			
		  Hrunit Hrunit = hrunitBo.getHrunitById(id);
		  
		  Hrunit.setHrunitName(dForm.getHrunitName());

		  Hrunit.setDescription(dForm.getHrunitDescription());

		  Hrunit.setHrunitCategory(category);
			
		  Hrunit.setOrganisationId(userIdentity.getOrganisation().getId());
			
		  Hrunit.setModifiedBy(userIdentity.getUsername());
		  
		  Hrunit.setModifiedDate(new GregorianCalendar().getTime());
		  Hrunit.setHrunitTypeId(catType);
		
		
			hrunitBo.update(Hrunit);

		
		
		 auditor.after(httpRequest, "Hrunit", dForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getHrunitName() + " edited Successfully!");
		return "redirect:/system/Hrunit/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") HrunitCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	Hrunit hrunit = hrunitBo.getHrunitById(id);

		
		hrunit.setModifiedBy(userIdentity.getUsername());
		hrunit.setModifiedDate(new GregorianCalendar().getTime());
		hrunit.setDeleted(true);
		hrunitBo.update(hrunit);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Unit  deleted");
		return "redirect:/system/hrunit/index";
	}
	
	@RequestMapping(value = "/refreshHrunitcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshHrunitcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call Hrunitcategory_outerrecursive(?)}");

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
		
//		model.addAttribute("HrunitType",HrunitTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", HrunitCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<HrunitCategory> list = hrunitCategoryBo.fetchAllByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (HrunitCategory itemcat : list) {
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
