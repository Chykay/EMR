package org.calminfotech.hrunit.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.HrunitTypeBo;
import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitType;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
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
@RequestMapping(value = "/hrunit/hrunitcategory")
@Layout(value = "layouts/form_wizard_layout")
public class HrunitCategoryController {

	@Autowired
	private HrunitCategoryBo hrunitCategoryBo;

	@Autowired
	private HrunitTypeBo hrunitTypeBo;
	
	@Autowired
	private	VisitWorkflowPointBo visitWorkflowPointBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BillSchemeBo billschemeBo;
	
	@Autowired
	private Authorize authorize;
	

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String index(RedirectAttributes redirectAttributes,Model model) {
		if (authorize.isAllowed("HRU000"))
		{
		model.addAttribute("category", hrunitCategoryBo.fetchTop50byOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "hrunit/hrunitcategory/index";
}
		
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to List HR Unit");
			
			return "redirect:/";
		}
		

		}
	
	
	@RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	//@Layout(value = "layouts/form_wizard_layout")
	public String indexall(RedirectAttributes redirectAttributes,Model model) {
		if (authorize.isAllowed("HRU000"))
		{
		model.addAttribute("category", hrunitCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "hrunit/hrunitcategory/indexall";
	}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to List HR Unit");
			
			return "redirect:/";
		}
		

		}
	
	
	

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HRU002"))
		{
		//HrunitCategory gCategory = HrunitCategoryBo.getHrunitCategoryById(categoryId);
				
	//	if (null == gCategory) {
		//	alert.setAlert(redirectAttributes, Alert.DANGER,
		//			"Error! Invalid resource");
		//	return "redirect:/system/Hrunitcategory";
		//		}
		HrunitCategoryForm cForm = new HrunitCategoryForm();
		//cForm.setParentCategoryId(gCategory.getCategoryId());
		//model.addAttribute("gCategory", gCategory);
		model.addAttribute("hrunitCategoryForm", cForm);
		return "hrunit/hrunitcategory/viewcategory";
	}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to View HR Unit");
			
			return "redirect:/";
		}
		

		}
	

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(RedirectAttributes redirectAttributes,Model model) {
		if (authorize.isAllowed("HRU001"))
		{
		model.addAttribute("hrunittype",hrunitTypeBo.fetchAllByOrganisation());

	model.addAttribute("categories", hrunitCategoryBo.fetchAllByOrganisationbyqueuebypoint(12));
		//List<BillScheme> billscheme = this.billschemeBo.fetchAllByOrganisationBytype(userIdentity.getOrganisation().getId(), 1);
		List<VisitWorkflowPoint> points = this.visitWorkflowPointBo.fetchAll();
	
		model.addAttribute("pointstore", hrunitCategoryBo.fetchAllByOrganisationbyqueuebypoint(12));
		
		
		model.addAttribute("hrunitCategoryForm", new HrunitCategoryForm());
		//model.addAttribute("billscheme", billscheme);
		model.addAttribute("points", points);
		
		
		return "hrunit/hrunitcategory/add";
		}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to List HR Unit");
			
			return "redirect:/";
		}
		

		}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("hrunitCategoryForm") HrunitCategoryForm hrForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HRU001"))
		{

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "redirect:/hrunit/hrunitcategory/save";
		}
		
		
		BillScheme billscheme = this.billschemeBo.getBillSchemeById(hrForm.getBillschemeId());
		
		
		VisitWorkflowPoint visitPoints = this.visitWorkflowPointBo.getWorkflowPointById(hrForm.getPoint_id());
		
		HrunitType  catType =this.hrunitTypeBo.getHrunitTypeById(hrForm.getHrunitTypeId());
		
	
		HrunitCategory category = new HrunitCategory();
		
		
        category.setIsDeleted(false);
        
		category.setAttendQ(hrForm.isAttendQ());
		
	/*	if (hrForm.getBillschemeId().intValue() !=  0)
		{
		category.setBillingScheme(billscheme);
		}*/
       
        
        if (!hrForm.getPoint_id().equals(""))
        		{
        category.setPoint(visitPoints);
        		}
        
      
category.setPointstoreId(hrForm.getPointstore_id());
		

        
        category.setCategoryName(hrForm.getCategoryName());

		category.setParentCategoryId(hrForm.getParentCategoryId());
		
		category.setOrganisationId(userIdentity.getOrganisation().getId());
		
		category.setCategoryName(hrForm.getCategoryName());

     	 category.setDescription(hrForm.getCategoryDescription());
  	
		category.setCreatedBy(userIdentity.getUsername());
		
		category.setHrunitTypeId(catType);
//		

		hrunitCategoryBo.save(category);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + hrForm.getCategoryName() + " added Successfully!");
		return "redirect:/hrunit/hrunitcategory/index";
	}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Add HR Unit");
			
			return "redirect:/";
		}
		

		}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	//@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		if (authorize.isAllowed("HRU003"))
		{
		// HrunitCategory gCategory = HrunitCategoryBo.getGlobalCategoryItemById(id);
		
		HrunitCategory category = hrunitCategoryBo.getHrunitCategoryById(id);

	//	Integer parent = category.getCategoryId();

	if (category == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,	"Error! Invalid resource");
			return "redirect:/hrunit/hrunitcategory";
	}
	

		HrunitCategoryForm cForm = new HrunitCategoryForm();
		
		cForm.setCategoryId(category.getCategoryId());
        cForm.setHrunitTypeId(category.getHrunitTypeId().getHrunitTypeId());
        
        cForm.setParentCategoryId(category.getParentCategoryId());
        
        cForm.setPointstore_id(category.getPointstoreId());
        
     	cForm.setCategoryDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());
		
		/*if (category.getBillingScheme() != null)
		{
			cForm.setBillschemeId(category.getBillingScheme().getId());
		}*/
		if (category.getPoint() != null)
		{
		cForm.setPoint_id(category.getPoint().getId());
		}
		cForm.setAttendQ(category.getAttendQ());
		

		
		//List<BillScheme> billscheme = this.billschemeBo.fetchAllByOrganisationBytype(userIdentity.getOrganisation().getId(), 1);
		List<VisitWorkflowPoint> visitPoints = this.visitWorkflowPointBo.fetchAll();
		
		
		
		
		//model.addAttribute("billscheme", billscheme);
		model.addAttribute("points", visitPoints);
		model.addAttribute("pointstore", hrunitCategoryBo.fetchAllByOrganisationbyqueuebypoint(12));
		
	    model.addAttribute("categories", hrunitCategoryBo.fetchAllByOrganisationByCategoryType(category.getHrunitTypeId().getHrunitTypeId()));
		model.addAttribute("hrunittype", hrunitTypeBo.fetchAllByOrganisation());
		
		
		model.addAttribute("hrunitCategoryForm", cForm);
		//model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "HrunitCategory", cForm);

		return "hrunit/hrunitcategory/edit";
	}
	else
	{
		alert.setAlert(redirectAttributes, Alert.WARNING,
				"You have no permission to Edit HR Unit");
		
		return "redirect:/";
	}
	

	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("hrunitCategoryForm") HrunitCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (authorize.isAllowed("HRU003"))
		{

	//	HrunitCategory category = this.HrunitCategoryBo.getHrunitCategoryById(cForm.getParentCategoryId());

		
		BillScheme billscheme = this.billschemeBo.getBillSchemeById(cForm.getBillschemeId());
		
		
		VisitWorkflowPoint visitPoints = this.visitWorkflowPointBo.getWorkflowPointById(cForm.getPoint_id());
		
		
	
		
		
		
		
		
		  HrunitType  catType =this.hrunitTypeBo.getHrunitTypeById(cForm.getHrunitTypeId());
			
		  HrunitCategory category = hrunitCategoryBo.getHrunitCategoryById(id);
		  
		
		  category.setParentCategoryId(cForm.getParentCategoryId());
			
			category.setOrganisationId(userIdentity.getOrganisation().getId());
			
			category.setCategoryName(cForm.getCategoryName());

		category.setDescription(cForm.getCategoryDescription());
		
		/*if (cForm.getBillschemeId().intValue() !=  0)
		{
		category.setBillingScheme(billscheme);
		
		}
		else
		{
			category.setBillingScheme(null);
		}
        */
        if (!cForm.getPoint_id().equals(""))
        		{
        category.setPoint(visitPoints);
        		}
		
        else
        {
        	 category.setPoint(null);
        
        }
        
        
        
        
        category.setPointstoreId(cForm.getPointstore_id());
        
        category.setAttendQ(cForm.isAttendQ());
			category.setModifiedBy(userIdentity.getUsername());
			category.setHrunitTypeId(catType);
		

			cForm.setModifiedBy(userIdentity.getUsername());
			cForm.setModifiedDate(new GregorianCalendar().getTime());

			hrunitCategoryBo.update(category);

		
		
		 auditor.after(httpRequest, "HrunitCategory", cForm, userIdentity.getUsername(),id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " edited Successfully!");
		return "redirect:/hrunit/hrunitcategory/index";
		}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Edit HR Unit");
			
			return "redirect:/";
		}
		

		}
	


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
		//	@ModelAttribute("cForm") HrunitCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		if (authorize.isAllowed("HRU004"))
		{
	HrunitCategory gCategory = hrunitCategoryBo.getHrunitCategoryById(id);
		//if (null == gCategory) {
	//		alert.setAlert(redirectAttributes, Alert.DANGER,
	//				"Error! Invalid resource");
	//		return "redirect:/system/Hrunitcategory/index";
	//	}
	//	hrunitCategoryBo.delete(gCategory);
		
	
		gCategory.setModifiedBy(userIdentity.getUsername());
		gCategory.setModifiedDate(new GregorianCalendar().getTime());
		gCategory.setDeleted(true);
		
		
		//auditor.before(httpRequest, "HrunitCategory", new HrunitCategoryForm() );
		
		hrunitCategoryBo.update(gCategory);
		// auditor.after(httpRequest, "HrunitCategory", new HrunitCategoryForm(), userIdentity.getUsername());
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HR Unit Category deleted");
		return "redirect:/hrunit/hrunitcategory/index";
	}
		else
		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to Delete HR Unit");
			
			return "redirect:/";
		}
		

		}
	
	
	@RequestMapping(value = "/refreshhrunitcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshHrunitcategory(@PathVariable("organisationId") Integer organisationId,Model model)
			throws HibernateException, SQLException  {
		
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session.connection().prepareCall("{? = call hrunitcategory_outerrecursive(?)}");

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
