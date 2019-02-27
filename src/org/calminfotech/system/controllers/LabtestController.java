package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.LabtestBo;
import org.calminfotech.system.boInterface.LabtestCategoryBo;
import org.calminfotech.system.boInterface.LabtestTypeBo;


import org.calminfotech.system.forms.GlobalitemSearchForm;
import org.calminfotech.system.forms.LabtestCategoryForm;
import org.calminfotech.system.forms.LabtestForm;


import org.calminfotech.system.models.Labtest;
import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.SearchUtility;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/labtest")
public class LabtestController {

	@Autowired
	private LabtestCategoryBo labtestCategoryBo;
	
	@Autowired
	private LabtestBo labtestBo;

	@Autowired
	private LabtestTypeBo labtestTypeBo;
	

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
	
	
model.addAttribute("labtest", labtestBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/labtest/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("labtest", labtestBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/labtest/indexallresultsetup";
	}
	
	@RequestMapping(value = {"/index/allresultsetup" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall5(Model model) {
		model.addAttribute("globalItemType",this.globalItemTypeBo.fetchAllByOrganisation());
		//model.addAttribute("global", globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		GlobalitemSearchForm  pf = new GlobalitemSearchForm();
		pf.setMysp(0);
		model.addAttribute("globalitemSearch", pf);
		
		return "system/labtest/indexallresultsetup";
	}
	
	@RequestMapping(value = "/index/allresultsetup", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String resultsetup7(
			@ModelAttribute("globalitemSearch") GlobalitemSearchForm globalitemSearchForm,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
	
//List patientList = searchBo.searchPatient(searchForm, session);
		model.addAttribute("globalItemType",this.globalItemTypeBo.fetchAllByOrganisation());
		List globalitemList = searchUtilBo.searchGlobalitem(globalitemSearchForm, session);
			
		model.addAttribute("global", globalitemList);
		
		return "system/labtest/indexallresultsetup";
	}

	
	
	

/*
	@RequestMapping(value = "/view/{Id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("Id") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		//LabtestCategory gCategory = LabtestCategoryBo.getLabtestCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Labtestcategory";
		//		}
		LabtestCategoryForm cForm = new LabtestCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/Labtestcategory/viewcategory";
	}
*/
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("labtesttype", labtestTypeBo.fetchAllByOrganisation());

//	model.addAttribute("categories", LabtestCategoryBo.fetcAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("labtestForm", new LabtestForm());
		return "system/labtest/add";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("labtestForm") LabtestForm dForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/labtest/add";
		}
		
		

		Labtest Labtest = new Labtest();
		LabtestCategory category = labtestCategoryBo.getLabtestCategoryById(dForm.getParentCategoryId());
        LabtestType  catType =this.labtestTypeBo.getLabtestTypeById(dForm.getLabtesttypeId());
	

        Labtest.setLabtestCategory(category);
        Labtest.setOrganisation(userIdentity.getOrganisation());
	
        System.out.println("name");
        System.out.println(dForm.getLabtestName());
        System.out.println("name");
        
	Labtest.setLabtestName(dForm.getLabtestName());

    System.out.println("desc");
    System.out.println(dForm.getLabtestDescription());
    System.out.println("desc");

		Labtest.setDescription(dForm.getLabtestDescription());
		

		Labtest.setCreatedBy(userIdentity.getUsername());
		Labtest.setLabtestTypeId(catType);
//		category.setCreatedDate(new GregorianCalendar().getTime());
		
		//category.setModifiedBy(userIdentity.getUsername());
		
		//category.setModifiedDate(new GregorianCalendar().getTime());
	

		labtestBo.save(Labtest);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getLabtestName() + " added Successfully!");
		return "redirect:/system/labtest/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
	
	//	LabtestCategory gCategory = LabtestCategoryBo.getGlobalCategoryItemById(id);
		
		Labtest labtest = labtestBo.getLabtestById(id);

	//	Integer parent = category.getCategoryId();

		
	if (labtest == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/labtest";
	}
	
	
		LabtestForm dForm = new LabtestForm();
		
		dForm.setParentCategoryId(labtest.getLabtestCategory().getCategoryId());
     dForm.setLabtesttypeId(labtest.getLabtestType().getLabtestTypeId());
        
     
        
	dForm.setLabtestDescription(labtest.getDescription());
		dForm.setLabtestName(labtest.getLabtestName());

	model.addAttribute("categories", labtestCategoryBo.fetchAllByOrganisationByCategoryType(labtest.getLabtestType().getLabtestTypeId()));
		model.addAttribute("labtestType", labtestTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("labtestForm", dForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Labtest", dForm);

		return "system/labtest/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("dForm") LabtestForm dForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		
		dForm.setModifiedBy(userIdentity.getUsername());
		dForm.setModifiedDate(new GregorianCalendar().getTime());

		LabtestCategory category = labtestCategoryBo.getLabtestCategoryById(dForm.getParentCategoryId());
        
		  LabtestType  catType =this.labtestTypeBo.getLabtestTypeById(dForm.getLabtesttypeId());
			
		  Labtest Labtest = labtestBo.getLabtestById(id);
		  
		  Labtest.setLabtestName(dForm.getLabtestName());

		  Labtest.setDescription(dForm.getLabtestDescription());

		  Labtest.setLabtestCategory(category);
			
		  Labtest.setOrganisation(userIdentity.getOrganisation());
			
		  Labtest.setModifiedBy(userIdentity.getUsername());
		  
		  Labtest.setModifiedDate(new GregorianCalendar().getTime());
		  Labtest.setLabtestTypeId(catType);
		
		
			labtestBo.update(Labtest);

		
		
		 auditor.after(httpRequest, "Labtest", dForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + dForm.getLabtestName() + " edited Successfully!");
		return "redirect:/system/labtest/index";
	}

	//@RequestMapping(value = "/delete/{id}")
	//public String delete(@PathVariable("id") Integer id, Model model,
	//		RedirectAttributes redirectAttributes) {
	//	LabtestCategory gCategory = LabtestCategoryBo
	//			.getLabtestCategoryById(id);
	////	if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Labtestcategory/index";
		//}
		
	//	LabtestCategoryForm cForm = new LabtestCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//cForm.setCategoryName(gCategory.getCategoryName());
		//model.addAttribute("gCategory", gCategory);
	//	model.addAttribute("cForm", cForm);
//		return "system/Labtestcategory/delete";
	//}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			//@ModelAttribute("cForm") LabtestCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
	Labtest labtest = labtestBo.getLabtestById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/Labtestcategory/index";
	//	}
	
	
	labtest.setModifiedBy(userIdentity.getUsername());
	labtest.setModifiedDate(new GregorianCalendar().getTime());
	labtest.setDeleted(true);
	labtestBo.update(labtest);
	
	alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Diagnosis  deleted");
		return "redirect:/system/labtest/index";
	}
	
	@RequestMapping(value = "/refreshLabtestcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshLabtestcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call Labtestcategory_outerrecursive(?)}");

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
		
//		model.addAttribute("LabtestType",LabtestTypeBo.fetchAllByOrganisation());

//		model.addAttribute("categories", LabtestCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<LabtestCategory> list = labtestCategoryBo.fetchAllByOrganisationByCategoryType(cattypeid);
		//System.out.println("Ajax Size Controller");
		//System.out.println(list.size());
		try 
		{
		for (LabtestCategory itemcat : list) {
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
