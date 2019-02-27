package org.calminfotech.system.controllers;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
//import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.forms.GlobalItemUnitOfMeasureForm;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalItemkindList;
import org.calminfotech.utils.GlobalUnitofMeasureList;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import org.calminfotech.error.custom.exception.InvalidStockLevelException;

@Controller
@RequestMapping(value = "/system/globalitemunit")
@Layout(value = "layouts/form_wizard_layout")
public class GlobalItemUnitofmeasureController {

	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;

	@Autowired
	private GlobalItemBo globalItemBo;
//
//	@Autowired
//	private InventoryManagerInterface inventoryManagerImpl;
//
//	@Autowired
//	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private GlobalItemkindList drugskindBo;

	@Autowired
	private GlobalItemUnitofMeasureBo globalitemunitBo;

	@Autowired
	private GlobalUnitofMeasureList globalunitofmeasureBo;

	@Autowired
	private GlobalUnitofMeasureBo unitofmeasureBo;

	@Autowired
	private GlobalItemUnitofMeasureBo itemunitofmeasureBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * @RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	 * 
	 * @Layout("layouts/datatable") //@Layout(value =
	 * "layouts/form_wizard_layout") public String index(Model model) {
	 * 
	 * 
	 * model.addAttribute("global",
	 * globalItemBo.fetchTop50byOrganisation(userIdentity
	 * .getOrganisation().getId()));
	 * 
	 * model.addAttribute("orgId", userIdentity.getOrganisation().getId());
	 * return "system/globalitem/index"; }
	 * 
	 * 
	 * @RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	 * 
	 * @Layout("layouts/datatable") //@Layout(value =
	 * "layouts/form_wizard_layout") public String indexall(Model model) {
	 * model.addAttribute("global",
	 * globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
	 * model.addAttribute("orgId", userIdentity.getOrganisation().getId());
	 * return "system/globalitem/indexall"; }
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/view/{Id}")
	 * 
	 * @Layout("layouts/datatable") public String view(@PathVariable("Id")
	 * Integer id, Model model, RedirectAttributes redirectAttributes) {
	 * 
	 * GlobalItem globalitem = this.globalItemBo.getGlobalItemById(id); if (null
	 * == globalitem) { alert.setAlert(redirectAttributes, Alert.DANGER,
	 * "Error! Invalid resource"); return "redirect:/globalitem/index"; }
	 * model.addAttribute("globalitem", globalitem);
	 * 
	 * 
	 * 
	 * //GlobalitemunitofMeasure
	 * 
	 * GlobalItemUnitOfMeasureForm globalitemunitofmeasure = new
	 * GlobalItemUnitOfMeasureForm(); List<GlobalUnitofMeasure>
	 * globalunitofmeasure = unitofmeasureBo.fetchAll();
	 * 
	 * 
	 * model.addAttribute("globalitemunit", globalitemunitofmeasure);
	 * 
	 * model.addAttribute("globalunit", globalunitofmeasure);
	 * 
	 * //globalitemunitofmeasureform.setGlobalitem_id(globalitem.getItemId());
	 * 
	 * // List<GlobalUnitofMeasure> globalunitofmeasure =
	 * 
	 * 
	 * //model.addAttribute("giunform", giunform);
	 * 
	 * 
	 * return "system/globalitem/view"; }
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.GET)
	 * 
	 * @Layout(value = "layouts/form_wizard_layout") public String add(Model
	 * model) {
	 * model.addAttribute("globalItemType",globalItemTypeBo.fetchAllByOrganisation
	 * ()); model.addAttribute("drugsKind",this.drugskindBo.fetchAll()); //
	 * model.addAttribute("categories",
	 * globalItemCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
	 * 
	 * model.addAttribute("GlobalItemForm", new GlobalItemForm());
	 * 
	 * return "system/globalitem/add"; }
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("globalitemunit") GlobalItemUnitOfMeasureForm globalitemunitform,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "redirect:/system/globalitem/view/"
					+ globalitemunitform.getGlobalitem_id();
		}

		if (globalitemunitBo.IsExist(globalitemunitform.getGlobalitem_id(),
				globalitemunitform.getUnitofmeasure_id())) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Unit of Measure Already Existed for this Item!!!");
			return "redirect:/system/globalitem/view/"
					+ globalitemunitform.getGlobalitem_id();
		}

		GlobalItemUnitofMeasure globalItemunitofmeasure = new GlobalItemUnitofMeasure();

		globalItemunitofmeasure.setGlobalitem(this.globalItemBo
				.getGlobalItemById(globalitemunitform.getGlobalitem_id()));
		globalItemunitofmeasure
				.setUnitofmeasure(this.unitofmeasureBo
						.getUnitofMeasureById(globalitemunitform
								.getUnitofmeasure_id()));
		globalItemunitofmeasure.setContainedvalue(globalitemunitform
				.getContainedvalue());

		/*
		 * if (globalitemunitform.getContainedmeasure_id().intValue() != 0) {
		 * globalItemunitofmeasure
		 * .setContainedmeasure_id(globalitemunitform.getContainedmeasure_id());
		 * } else { globalItemunitofmeasure.setContainedmeasure_id(null); }
		 */
		globalItemunitofmeasure.setIs_precribe(globalitemunitform
				.isIs_prescribe());

		globalItemunitofmeasure.setCreatedby(userIdentity.getUsername());
		globalItemunitofmeasure
				.setCreateDate(new GregorianCalendar().getTime());

		globalItemunitofmeasure.setDeleted(false);
		globalItemunitofmeasure.setOrganisation_id(userIdentity
				.getOrganisation().getId());
		itemunitofmeasureBo.save(globalItemunitofmeasure);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!!  Added Successfully!");
		return "redirect:/system/globalitem/view/"
				+ globalitemunitform.getGlobalitem_id();

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	// @Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {

		GlobalItemUnitofMeasure globalItemunitofmeasure = itemunitofmeasureBo
				.getGlobalItemUnitofMeasureById(id);

		if (globalItemunitofmeasure == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/globalitem";
		}

		List<GlobalItemUnitofMeasure> globalitemunitofmeasure = this.globalitemunitBo
				.fetchAllByItemId(globalItemunitofmeasure.getGlobalitem()
						.getItemId());
		List<GlobalItemUnitofMeasureVw> globalitemunitofmeasurevw = this.globalitemunitBo
				.fetchAllByItemIdvw(globalItemunitofmeasure.getGlobalitem()
						.getItemId());

		List<GlobalUnitofMeasure> globalunitofmeasure = this.globalunitofmeasureBo
				.fetchAll();
		GlobalItemUnitOfMeasureForm globalitemunitform = new GlobalItemUnitOfMeasureForm();
		globalitemunitform.setUnitofmeasure_id(globalItemunitofmeasure
				.getUnitofmeasure().getId());
		globalitemunitform.setContainedvalue(globalItemunitofmeasure
				.getContainedvalue());
		globalitemunitform.setIs_prescribe(globalItemunitofmeasure
				.getIs_precribe());

		globalitemunitform.setContainedmeasure_id(globalItemunitofmeasure
				.getContainedmeasure_id());
		model.addAttribute("globalitemunit", globalitemunitform);
		model.addAttribute("globalitemunitofmeasure", globalitemunitofmeasure);
		model.addAttribute("globalunitofmeasure", globalunitofmeasure);
		model.addAttribute("globalitemunitofmeasurevw",
				globalitemunitofmeasurevw);
		model.addAttribute("gitemmeasureid", globalItemunitofmeasure.getId());

		auditor.before(httpRequest, "globalitemunitofmeasure",
				globalitemunitform);

		return "system/globalitemunit/edit";
	}

	@Transactional
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
			@PathVariable("id") Integer id,
			@Valid @ModelAttribute("globalitemunit") GlobalItemUnitOfMeasureForm globalitemunitform,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Integer nullRowId = null;

		GlobalItemUnitofMeasure globalItemunitofmeasure = itemunitofmeasureBo
				.getGlobalItemUnitofMeasureById(id);
		if (globalItemunitofmeasure.getContainedmeasure_id() == null) {
			nullRowId = globalItemunitofmeasure.getContainedmeasure_id();
			// nullGlobalitemId =
			// globalItemunitofmeasure.getGlobalitem().getItemId();
		}

		// globalItemunitofmeasure.setGlobalitem_id(globalitemunitform.getGlobalitem_id());
		globalItemunitofmeasure
				.setUnitofmeasure(this.unitofmeasureBo
						.getUnitofMeasureById(globalitemunitform
								.getUnitofmeasure_id()));
		globalItemunitofmeasure.setContainedvalue(globalitemunitform
				.getContainedvalue());

		globalItemunitofmeasure.setIs_precribe(globalitemunitform
				.isIs_prescribe());

		/*
		 * if (globalitemunitform.getContainedmeasure_id().intValue() != 0) {
		 * globalItemunitofmeasure
		 * .setContainedmeasure_id(globalitemunitform.getContainedmeasure_id());
		 * } else { globalItemunitofmeasure.setContainedmeasure_id(null); }
		 */

		// globalItemunitofmeasure.setContainedmeasure_id(globalitemunitform.getContainedmeasure_id());

		globalItemunitofmeasure.setModifiedBy(userIdentity.getUsername());
		globalItemunitofmeasure.setModifiedDate(new GregorianCalendar()
				.getTime());

		this.itemunitofmeasureBo.update(globalItemunitofmeasure);

		/*
		 * if (globalItemunitofmeasure.getContainedmeasure_id()==null) {
		 * 
		 * try {
		 * 
		 * List<StockCurrentBalance> storebalist =
		 * inventoryManagerImpl.getAllStockCurrentBalancesByStock
		 * (globalItemunitofmeasure.getGlobalitem().getItemId()); for
		 * (StockCurrentBalance storebal : storebalist ) {
		 * 
		 * Integer qtybal=storebal.getCurrentBalance();
		 * 
		 * 
		 * Integer newqtybal =
		 * unitOfMeasureConverter.convertmeasuretounits(qtybal, nullRowId);
		 * 
		 * inventoryManagerImpl.updateGlobalItemCurrentBalance(newqtybal,
		 * qtybal, storebal);
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * catch (InvalidUnitOfMeasureConfiguration e) { // TODO Auto-generated
		 * catch block
		 * 
		 * e.printStackTrace(); alert.setAlert(redirectAttributes, Alert.DANGER,
		 * e.getExceptionMsg()); return "redirect:/system/globalitem/view/" +
		 * globalItemunitofmeasure.getGlobalitem().getItemId(); }
		 * 
		 * 
		 * 
		 * 
		 * }
		 */
		auditor.after(httpRequest, "globalitemunitofmeasure",
				globalitemunitform, userIdentity.getUsername(), id);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!  edited Successfully!");
		return "redirect:/system/globalitem/view/"
				+ globalItemunitofmeasure.getGlobalitem().getItemId();
	}

	// @RequestMapping(value = "/delete/{id}")
	// public String delete(@PathVariable("id") Integer id, Model model,
	// RedirectAttributes redirectAttributes) {
	// GlobalItemCategory gCategory = globalItemCategoryBo
	// .getGlobalItemCategoryById(id);
	// // if (null == gCategory) {
	// alert.setAlert(redirectAttributes, Alert.DANGER,
	// "Error! Invalid resource");
	// return "redirect:/system/globalitemcategory/index";
	// }

	// GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
	// cForm.setParentCategoryId(gCategory.getCategoryId());
	// cForm.setCategoryName(gCategory.getCategoryName());
	// model.addAttribute("gCategory", gCategory);
	// model.addAttribute("cForm", cForm);
	// return "system/globalitemcategory/delete";
	// }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") GlobalItemCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		GlobalItemUnitofMeasure globalItemunitofmeasure = itemunitofmeasureBo
				.getGlobalItemUnitofMeasureById(id);
		// if (null == gCategory) {
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid resource");
		// return "redirect:/system/globalitemcategory/index";
		// }

		globalItemunitofmeasure.setModifiedBy(userIdentity.getUsername());
		globalItemunitofmeasure.setModifiedDate(new GregorianCalendar()
				.getTime());
		globalItemunitofmeasure.setDeleted(true);
		itemunitofmeasureBo.update(globalItemunitofmeasure);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item-unit of measure  deleted");
		return "redirect:/system/globalitem/view/"
				+ globalItemunitofmeasure.getGlobalitem().getItemId();
	}

}
