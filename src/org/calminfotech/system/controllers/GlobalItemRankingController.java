package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
//import org.calminfotech.hmo.forms.HmoPackageForm;
//import org.calminfotech.hmo.models.Hmo;
//import org.calminfotech.hrunit.models.Staffgroupranking;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryOuterrecursiveBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;

import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.forms.GlobalItemAssignmentForm;
import org.calminfotech.system.forms.GlobalItemRankingForm;
import org.calminfotech.system.forms.GlobalItemUnitOfMeasureForm;

import org.calminfotech.system.models.GlobalItem;

import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemRanking;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalItemkindList;
import org.calminfotech.utils.GlobalUnitofMeasureList;
import org.calminfotech.utils.StaffgrouprankingList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Hmostatus;
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
@RequestMapping(value = "/system/globalitemranking")
@Layout(value = "layouts/form_wizard_layout")
public class GlobalItemRankingController {

	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;
	
	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;
	
	@Autowired
	private GlobalItemkindList drugskindBo;
	
	@Autowired
	private GlobalItemUnitofMeasureBo  globalitemunitBo;
	
	@Autowired
	private GlobalUnitofMeasureList  globalunitofmeasureBo;
	
	@Autowired
	private GlobalUnitofMeasureBo  unitofmeasureBo;
	
	@Autowired
	private GlobalItemUnitofMeasureBo  itemunitofmeasureBo;
	
	@Autowired
	private StaffgrouprankingList  staffrankinglistBo;
	
	@Autowired
	private StaffgrouprankingList  rankingBo;
	
	
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;

	/*
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
	
	
model.addAttribute("global", globalItemBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/globalitem/index";
	}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("global", globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/globalitem/indexall";
	}
	
	

	@RequestMapping(value = "/view/{Id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("Id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		
		GlobalItem  globalitem = this.globalItemBo.getGlobalItemById(id);
		if (null == globalitem) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/globalitem/index";
		}
		model.addAttribute("globalitem", globalitem);
		

		
		//GlobalitemunitofMeasure
		
		GlobalItemUnitOfMeasureForm globalitemunitofmeasure = new GlobalItemUnitOfMeasureForm();
		List<GlobalUnitofMeasure> globalunitofmeasure = unitofmeasureBo.fetchAll();
		
		
		model.addAttribute("globalitemunit", globalitemunitofmeasure);
	
		model.addAttribute("globalunit", globalunitofmeasure);
		
		//globalitemunitofmeasureform.setGlobalitem_id(globalitem.getItemId());
		
	//	List<GlobalUnitofMeasure> globalunitofmeasure = 
		
	
		//model.addAttribute("giunform", giunform);
			
		
		return "system/globalitem/view";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("globalItemType",globalItemTypeBo.fetchAllByOrganisation());
		model.addAttribute("drugsKind",this.drugskindBo.fetchAll());
//	model.addAttribute("categories", globalItemCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		
		model.addAttribute("GlobalItemForm", new GlobalItemForm());
		
		return "system/globalitem/add";
	}
	
*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("globalitemranking") GlobalItemRankingForm globalitemrankingform,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
try{
		
		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "redirect:/system/globalitem/view/" + globalitemrankingform.getGlobalitem_id() ;
		}
		
		

		GlobalItemRanking globalItemranking = new GlobalItemRanking();

		
//		globalItemranking.setGlobalitem(this.globalItemBo.getGlobalItemById(globalitemrankingform.getGlobalitem_id()));
//		globalItemranking.setStaffranking(this.staffrankinglistBo.getStaffgrouprankingById(globalitemrankingform.getRanking_id()));
//		globalItemranking.setCreatedby(userIdentity.getUsername());
//		globalItemranking.setCreateDate(new GregorianCalendar().getTime());
//		
		globalItemranking.setDeleted(false);
		globalItemranking.setOrganisation(userIdentity.getOrganisation());
		itemunitofmeasureBo.save(globalItemranking);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!!  Added Successfully!");
	
}
catch (Exception e )
{
	alert.setAlert(redirectAttributes, Alert.DANGER,
			e.getMessage() + " May be duplicate");
}
	
	
	return "redirect:/system/globalitem/view/" + globalitemrankingform.getGlobalitem_id();
				
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		
		GlobalItemRanking globalItemranking = itemunitofmeasureBo.getGlobalItemRankingById(id);
		
	if (globalItemranking == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/system/globalitem";
	}
	
	
//	List<Staffgroupranking> staffranking = rankingBo.fetchAll();
//	
	
 //List<GlobalUnitofMeasure> globalunitofmeasure =	this.globalunitofmeasureBo.fetchAll();
	
 GlobalItemRankingForm  globalrankingform = new GlobalItemRankingForm();

// globalrankingform.setRanking_id(globalItemranking.getStaffranking().getId());
 
 globalrankingform.setGlobalitem_id(globalItemranking.getGlobalitem().getItemId());
	model.addAttribute("globalitemrankingform", globalrankingform);

	
 	//model.addAttribute("globalunitofmeasure", globalunitofmeasure);
	//model.addAttribute("staffranking", staffranking);
	
 auditor.before(httpRequest, "globalitemunitofmeasure", globalrankingform);

		return "system/globalitemranking/edit";
	}

	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("globalitemranking") GlobalItemRankingForm globalitemrankingform,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try
		{
			
			GlobalItemRanking globalItemranking = itemunitofmeasureBo.getGlobalItemRankingById(id);
	

//		
		
		globalItemranking.setGlobalitem(this.globalItemBo.getGlobalItemById(globalitemrankingform.getGlobalitem_id()));
		//globalItemranking.setStaffranking(this.staffrankinglistBo.getStaffgrouprankingById(globalitemrankingform.getRanking_id()));
		
		
		//globalItemranking.setContainedmeasure_id(globalitemunitform.getContainedmeasure_id());
		
		globalItemranking.setModifiedBy(userIdentity.getUsername());
		globalItemranking.setModifiedDate(new GregorianCalendar().getTime());
	
		this.itemunitofmeasureBo.update(globalItemranking);
		 auditor.after(httpRequest, "globalitemunitofmeasure", globalitemrankingform, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + globalitemrankingform.getGlobalitem_id() + " edited Successfully!");
		
		
	}
	catch (Exception e )
	{
		alert.setAlert(redirectAttributes, Alert.DANGER,
				e.getMessage() + " May be duplicate");
	}
		
		
		return "redirect:/system/globalitem/view/" + globalitemrankingform.getGlobalitem_id();
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
		
		
		
		GlobalItemRanking globalItemranking = itemunitofmeasureBo.getGlobalItemRankingById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/globalitemcategory/index";
	//	}
	
	
		globalItemranking.setModifiedBy(userIdentity.getUsername());
		globalItemranking.setModifiedDate(new GregorianCalendar().getTime());
		globalItemranking.setDeleted(true);
		itemunitofmeasureBo.update(globalItemranking);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item-Ranking deleted");
		 return "redirect:/system/globalitem/view/" + globalItemranking.getGlobalitem().getItemId();
	}
	
	}
