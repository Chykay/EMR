package org.calminfotech.inventory.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.inventory.exceptions.DuplicateDataException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.serviceInterface.StockOpeningBalanceManagerInterface;
import org.calminfotech.inventory.utils.CustomValidator;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.forms.GlobalitemSearchForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory/stock_opening_balance")
public class StockOpeningBalanceController extends AbstractBaseController {

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private StockOpeningBalanceManagerInterface stockOpeningBalanceManager;

	@Autowired
	private UnitOfMeasureConverter unitconvert;

	@Autowired
	private Alert alert;

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Authorize authorize;

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVOB000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		String curl = "";

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		// model.addAttribute("globalItemType",this.globalItemTypeBo.fetchAllByOrganisation());
		// model.addAttribute("global",
		// globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));

		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		model.addAttribute("globalSearch", new GlobalitemSearchForm());

		GlobalitemSearchForm pf = new GlobalitemSearchForm();
		pf.setMysp(0);
		model.addAttribute("globalSearch", pf);

		return "inventory/stock_opening_balance/index";
	}

	@RequestMapping(value = "/index/all", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String indexallp(
			@Valid @ModelAttribute("globalSearch") GlobalitemSearchForm globalSearchForm,
			BindingResult result, ModelMap model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVOB000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		String curl = "";

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		String ids = null;

		try {
			model.addAttribute("pageRegister", true);

			StockOpeningBalanceForm stockOpeningBalanceForm = this
					.getStockOpeningBalanceForm(globalSearchForm
							.getGlobalitemId().toString());

			// this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);

			stockOpeningBalanceForm.setGlobalItem(globalSearchForm
					.getGlobalitemId().toString());

			System.out.print("before passing to displayopbalFormpage"
					+ stockOpeningBalanceForm.getGlobalItem());

			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model,
					globalSearchForm.getGlobalitemId());

		} catch (RecordNotFoundException e) {
			// Alert alert = (Alert) model.get("alert");

			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());

			return "redirect:/inventory/stock_opening_balance/index/all";
			// ""+ globalSearchForm.getGlobalitemId();
		}

	}

	@Layout("layouts/datatable")
	@RequestMapping(value = { "{id}", "/{id}" }, method = RequestMethod.GET)
	public String fetchOpeningBalances(ModelMap model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVOB000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		try {

			StockOpeningBalanceForm stockOpeningBalanceForm = this
					.getStockOpeningBalanceForm(id.toString());

			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model, id);
			// return displayStockOpeningBalancesListPage(model,id);

		} catch (RecordNotFoundException e) {

			// this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND, model);
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());
			return "/inventory/stock_opening_balance/index";
		}

	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new/{id}", method = RequestMethod.POST)
	public String saveOpeningBal(
			@Valid @ModelAttribute("stockOpeningBalanceForm") StockOpeningBalanceForm stockOpeningBalanceForm,
			@PathVariable("id") Integer id, BindingResult result,
			ModelMap model, RedirectAttributes redirectAttributes,
			HttpServletRequest req) {

		if (!authorize.isAllowed("INVOB001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockOpeningBalanceForm.getDateAdded();
		if (!date.isEmpty() && !DateUtils.isValidDate(date)) {

			result.rejectValue("dateAdded", "error.stockOpeningBalanceForm",
					"Date must be in specified format");
		}

		if (!result.hasErrors()) {
			try {
				System.out.print("there are no form errors");

				this.stockOpeningBalanceManager
						.saveStockOpeningBalance(stockOpeningBalanceForm);

				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! New Stock Opening Balance Succesfully added! ");
				return "redirect:/inventory/stock_opening_balance";
			} catch (DuplicateDataException e) {
				// this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
				// result.rejectValue("globalItem",
				// "error.stockOpeningBalanceForm", e.getExceptionMsg());
				this.modalAlert(true, Alert.DANGER, e.getExceptionMsg(), model);
			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.modalAlert(true, Alert.DANGER, e.getExceptionMsg(), model);
			}
		} else {
			System.out.print("there are form errors");
		}

		try {
			this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model, id);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());
			return "redirect:/inventory/stock_opening_balance" + id;
		}
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveOpeningBal2(
			@Valid @ModelAttribute("stockOpeningBalanceForm") StockOpeningBalanceForm stockOpeningBalanceForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {
		if (!authorize.isAllowed("INVOB001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		// Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockOpeningBalanceForm.getDateAdded();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateAdded", "error.stockOpeningBalanceForm",
					"Date must be in specified format");
		}
		if (!result.hasErrors()) {

			try {

				// model.addAttribute("pageRegister", false);

				this.stockOpeningBalanceManager
						.saveStockOpeningBalance(stockOpeningBalanceForm);
				System.out.print("passed form again"
						+ stockOpeningBalanceForm.getGlobalItem());

				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! New Stock Opening Balance Succesfully added!!! ");

			} catch (DuplicateDataException e) {
				// this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
				// result.rejectValue("globalItem",
				// "error.stockOpeningBalanceForm", e.getExceptionMsg());

				// this.modalAlert(true, Alert.DANGER, e.getExceptionMsg(),
				// model);
				alert.setAlert(redirectAttributes, Alert.DANGER,
						e.getExceptionMsg());

				System.out.print("duplicatedata again"
						+ stockOpeningBalanceForm.getGlobalItem());

				// return "redirect:/inventory/stock_opening_balance/index/" +
				// stockOpeningBalanceForm.getGlobalItem();
			} catch (InvalidUnitOfMeasureConfiguration e) {

				// this.modalAlert(true, Alert.DANGER, e.getExceptionMsg(),
				// model);
				alert.setAlert(redirectAttributes, Alert.DANGER,
						e.getExceptionMsg());
				System.out.print("invalid unitofmeasure"
						+ stockOpeningBalanceForm.getGlobalItem());

				// return "redirect:/inventory/stock_opening_balance/" +
				// stockOpeningBalanceForm.getGlobalItem();

			}

			System.out.print("redirect again"
					+ stockOpeningBalanceForm.getGlobalItem());

		} else {
			System.out.print("form error reject value");
		}

		System.out.print("redirect again"
				+ stockOpeningBalanceForm.getGlobalItem());

		return "redirect:/inventory/stock_opening_balance/"
				+ stockOpeningBalanceForm.getGlobalItem();

		/*
		 * try { //this.loadStockOpeningBalanceForm(stockOpeningBalanceForm,
		 * model); return
		 * this.displayStockOpeningBalanceFormPage(stockOpeningBalanceForm,
		 * model,Integer.valueOf(stockOpeningBalanceForm.getGlobalItem())); }
		 * catch (RecordNotFoundException e) {
		 * alert.setAlert(redirectAttributes, Alert.DANGER,
		 * e.getExceptionMsg()); // return
		 * "redirect:/inventory/stock_opening_balance" +
		 * stockOpeningBalanceForm.getGlobalItem(); }
		 */
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
	// @Layout("layouts/datatable")
	public String openingBalanceForm(@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes, ModelMap model) {
		if (!authorize.isAllowed("INVOB001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		String ids = null;
		StockOpeningBalanceForm stockOpeningBalanceForm = this
				.getStockOpeningBalanceForm(ids);
		try {
			model.addAttribute("pageRegister", true);

			// this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);

			stockOpeningBalanceForm.setId(id);

			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model, id);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());
			return "redirect:/inventory/stock_opening_balance" + id;
		}

	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editStockOpeningBalance(@PathVariable("id") Integer id,
			ModelMap model, RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVOB003")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Integer myid = null;
		StockOpeningBalanceForm stockOpeningBalanceForm = this
				.getStockOpeningBalanceForm(id.toString());

		try {

			model.addAttribute("pageEdit", true);

			this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
			System.out.print("modeller" + model.get("globalitemid").toString());

			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model,
					Integer.valueOf(model.get("globalitemid").toString()));
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_opening_balance/" + myid;

		}

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String editVendor(
			@Valid @ModelAttribute("stockOpeningBalanceForm") StockOpeningBalanceForm stockOpeningBalanceForm,
			BindingResult result, ModelMap model,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVOB003")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockOpeningBalanceForm.getDateAdded();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateAdded", "error.stockOpeningBalanceForm",
					Text.INVALID_DATE_FORMAT);
		}
		if (!result.hasErrors()) {

			try {
				StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManager
						.editStockOpeningBalance(stockOpeningBalanceForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! Stock Opening Balance updated succesfully ! ");
				/*
				 * return "redirect:/inventory/stock_opening_balance/view/" +
				 * stockOpeningBalance.getId();
				 */
				return "redirect:/inventory/stock_opening_balance/"
						+ stockOpeningBalanceForm.getGlobalItem();
			} catch (DuplicateDataException e) {
				// this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
				// result.rejectValue("globalItem","error.stockOpeningBalanceForm",
				// e.getExceptionMsg());

				alert.setAlert(redirectAttributes, Alert.DANGER,
						e.getExceptionMsg());

			} catch (RecordNotFoundException e) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource (Resource id:"
								+ stockOpeningBalanceForm.getId() + ")");
			} catch (InvalidUnitOfMeasureConfiguration e) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						e.getExceptionMsg());

			} catch (InvalidStockLevelException e) {
				// TODO Auto-generated catch block
				// this.modalAlert(true, Alert.DANGER, e.getExceptionMsg(),
				// model);
				alert.setAlert(redirectAttributes, Alert.DANGER,
						e.getExceptionMsg());
			}
		}

		try {
			this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
			model.addAttribute("pageEdit", true);
			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model, id);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:"
							+ stockOpeningBalanceForm.getId() + ")");
			return "redirect:/inventory/stock_opening_balance/"
					+ stockOpeningBalanceForm.getGlobalItem();

		}
		// return "redirect:/inventory/stock_opening_balance/" +
		// stockOpeningBalanceForm.getGlobalItem();
	}

	@RequestMapping(value = "/view/{rid}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewVendor(@PathVariable int rid,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVOB002")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		try {
			return displayStockOpeningBalanceDetailPage(rid, model);
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + rid + ")");
			return "redirect:/inventory/stock_opening_balance";
		}

	}

	// stock opening balance delete

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/datatable")
	public String deleteVendor(@PathVariable Integer id,
			RedirectAttributes redirectAttributes, ModelMap model) {
		if (!authorize.isAllowed("INVOB004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Integer myid = null;
		try {
			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManager
					.getStockOpeningBalanceDetailById(id);
			myid = stockOpeningBalance.getGlobalItem().getItemId();
			model.addAttribute("stockOpeningBalance", stockOpeningBalance);
			model.addAttribute("pageDelete", true);

			StockOpeningBalanceForm stockOpeningBalanceForm = this
					.getStockOpeningBalanceForm(id.toString());

			return this.displayStockOpeningBalanceFormPage(
					stockOpeningBalanceForm, model, myid);

			// return
			// this.displayStockOpeningBalancesListPage(model,stockOpeningBalance.getGlobalItem().getItemId());

		} catch (RecordNotFoundException e) {
			// Alert alert = (Alert) model.get("alert");
			System.out.print("delete error");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_opening_balance/" + myid;
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@RequestParam(value = "id", required = false) int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVOB004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity
				.setCurrentUrl("redirect:/inventory/stock_opening_balance/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		// Alert alert = (Alert) model.get("alert");
		Integer myid = null;
		try {
			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManager
					.getStockOpeningBalanceDetailById(id);
			myid = stockOpeningBalance.getGlobalItem().getItemId();

			this.stockOpeningBalanceManager.delete(stockOpeningBalance);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! opening balance deleted successfully");
			return "redirect:/inventory/stock_opening_balance/" + myid;

		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")");
			// return "redirect:/inventory/stock_opening_balance/delete/" + id;

		} catch (InvalidUnitOfMeasureConfiguration e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());
			// return "redirect:/inventory/stock_opening_balance/" + myid;
		}

		catch (InvalidStockLevelException e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());
			// return "redirect:/inventory/stock_opening_balance/" + myid;
		}
		return "redirect:/inventory/stock_opening_balance/" + myid;

	}

	private String displayStockOpeningBalanceDetailPage(int id, ModelMap model)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManager
				.getStockOpeningBalanceDetailById(id);
		model.addAttribute("stockOpeningBalance", stockOpeningBalance);
		model.addAttribute("pageView", true);
		return "/inventory/stock_opening_balance/view";

	}

	private StockOpeningBalanceForm getStockOpeningBalanceForm(String id) {
		StockOpeningBalanceForm stockOpeningBalanceForm = new StockOpeningBalanceForm();
		if (id != null) {
			try {
				stockOpeningBalanceForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// stockOpeningBalanceForm.setId(0);
			}
		}
		return stockOpeningBalanceForm;
	}

	private void loadStockOpeningBalanceForm(
			StockOpeningBalanceForm stockOpeningBalanceForm, ModelMap model)
			throws RecordNotFoundException {

		// if BatchSupply id not empty then we are in edit mode so we populate
		// form
		System.out.print("key" + model.containsKey("stockOpeningBalanceForm"));
		if (stockOpeningBalanceForm.getId() != 0
				&& !model.containsKey("stockOpeningBalanceForm")) {
			// get BatchSupply details and populate form
			System.out.print("BatchSupply");
			int id = stockOpeningBalanceForm.getId();
			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManager
					.getStockOpeningBalanceDetailById(id);

			// set opening balance
			stockOpeningBalanceForm.setOpeningBalance(String
					.valueOf(stockOpeningBalance.getOpeningBalance()));

			// set reorder level
			stockOpeningBalanceForm.setReorderlevel(String
					.valueOf(stockOpeningBalance.getReorderlevel()));

			// set global item
			GlobalItem globalItem = stockOpeningBalance.getGlobalItem();
			if (globalItem != null) {
				stockOpeningBalanceForm.setGlobalItem(String.valueOf(globalItem
						.getItemId()));
			}

			// stockOpeningBalanceForm.setGlobalItem(stockOpeningBalanceForm.setGlobalItem(globalSearchForm.getGlobalitemId().toString()););
			// stockOpeningBalanceForm.setGlobalItem(globalSearchForm.getGlobalitemId().toString());
			stockOpeningBalanceForm.setDateAdded(DateUtils
					.formatDateToString(stockOpeningBalance.getDateAdded()));

			if (stockOpeningBalance.getManufacturedate() != null) {

				stockOpeningBalanceForm.setManufacturedate(DateUtils
						.formatDateToString(stockOpeningBalance
								.getManufacturedate()));
			}

			if (stockOpeningBalance.getExpirydate() != null) {

				stockOpeningBalanceForm
						.setExpirydate(DateUtils
								.formatDateToString(stockOpeningBalance
										.getExpirydate()));
			}

			stockOpeningBalanceForm.setSerialno(stockOpeningBalance
					.getSerialno());
			stockOpeningBalanceForm
					.setBatchno(stockOpeningBalance.getBatchno());
			stockOpeningBalanceForm.setTinno(stockOpeningBalance.getTinno());
			stockOpeningBalanceForm.setCost(stockOpeningBalance.getCost());

			String dateAdded = stockOpeningBalanceForm.getDateAdded();

			// set todays date as default if there exist none
			if (dateAdded == null || dateAdded.isEmpty()) {
				stockOpeningBalanceForm.setDateAdded(new SimpleDateFormat(
						"yyyy-MM-dd").format(new Date()));
			}

			// set unit of measure
			/*
			 * GlobalUnitofMeasure globalUnitofMeasure = stockOpeningBalance
			 * .getGlobalUnitofMeasure(); if (globalUnitofMeasure != null) {
			 * stockOpeningBalanceForm.setUnitOfMeasure(String
			 * .valueOf(globalUnitofMeasure.getId())); }
			 */

			GlobalItemUnitofMeasureVw globalUnitofMeasure = stockOpeningBalance
					.getGlobalUnitofMeasure();
			if (globalUnitofMeasure != null) {
				stockOpeningBalanceForm.setUnitOfMeasure(String
						.valueOf(globalUnitofMeasure.getId()));
			}

			// set date added

			model.put("globalitemid", stockOpeningBalance.getGlobalItem()
					.getItemId());

		}

		// load vendors list *********
		// model.addAttribute("globalItemsList",
		// this.globalItemList.fetchAll());

		/*
		 * // List<GlobalUnitofMeasure> globalUnitOfMeasure = null;
		 * List<GlobalItemUnitofMeasureVw> globalUnitOfMeasure = null; String
		 * globalItem = stockOpeningBalanceForm.getGlobalItem(); if (globalItem
		 * != null && !globalItem.isEmpty()) { try { globalUnitOfMeasure =
		 * (List) this .getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(globalItem)); /****** GlobalItem item =
		 * this.globalItemBo.getGlobalItemById(Integer .parseInt(globalItem));
		 * if (item != null) { globalUnitOfMeasure = item.getMeasurement(); if
		 * (globalUnitOfMeasure == null || globalUnitOfMeasure.isEmpty()) {
		 * this.modalAlert(true, Alert.DANGER, "No unit of measurement found",
		 * model); } }**** } catch (NumberFormatException e) { }
		 * 
		 * } model.addAttribute("unitOfMeasuresList", globalUnitOfMeasure);
		 */
	}

	private String displayStockOpeningBalanceFormPage(
			StockOpeningBalanceForm stockOpeningBalanceForm, ModelMap model,
			Integer id) throws RecordNotFoundException {

		System.out.print("inside displayopbalFormpage"
				+ stockOpeningBalanceForm.getGlobalItem());

		GlobalitemSearchForm gisf = new GlobalitemSearchForm();

		gisf.setGlobalitemId(id);

		gisf.setGlobalitemname(this.globalItemBo.getGlobalItemById(id)
				.getGlobalitemName());

		// / gisf.setGlobalitemname("oya");

		model.addAttribute("globalSearch", gisf);

		System.out.print("oya opening stock id" + id.toString());

		model.addAttribute("stockOpeningBalanceForm", stockOpeningBalanceForm);

		// model.addAttribute("pageRegister", true);
		return this.displayStockOpeningBalancesListPage(model, id);
	}

	/*
	 * method to get stock opening balance list from persistent store and
	 * display to user
	 */
	private String displayStockOpeningBalancesListPage(ModelMap model,
			Integer id) throws RecordNotFoundException {
		// TODO Auto-generated method stub

		List<StockOpeningBalance> stockOpeningBalances = null;
		try {
			stockOpeningBalances = this.stockOpeningBalanceManager
					.getStockOpeningBalances(id);
			model.addAttribute("stockOpeningBalanceList", stockOpeningBalances);

		} catch (RecordNotFoundException e) {

			// this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND, model);
			System.out.print("fff" + e.getExceptionMsg());
		}

		System.out.print("ggg" + id);

		model.addAttribute("gid", id);

		List<GlobalItemUnitofMeasureVw> lst = this.vwGlobalItemUnitofMeasureBo
				.fetchAllByItemIdvw(id);

		model.addAttribute("globalitemunitofmeasurevw", lst);
		if (lst.size() > 0) {
			model.addAttribute("gname", lst.get(0).getGlobalitemname());
		}
		return "/inventory/stock_opening_balance/index";
	}

}
