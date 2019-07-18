package org.calminfotech.inventory.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.inventory.exceptions.InvalidOrderException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.ProcessPointRequestForm;
import org.calminfotech.inventory.forms.RequestForm;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.models.Storeissue_log;
import org.calminfotech.inventory.service.Utilities;
import org.calminfotech.inventory.serviceInterface.PointRequestManagerInterface;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;
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
@RequestMapping(value = "/inventory/request")
public class PointRequestController extends AbstractBaseController {

	@Autowired
	private UnitOfMeasureConverter unitconvert;

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private Alert alert;

	@Autowired
	private Utilities utilities;

	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private PointRequestManagerInterface orderRequestManager;

	@Autowired
	private Authorize authorize;

	@RequestMapping(value = { "/", "" })
	@Layout("layouts/datatable")
	public String indexAction(ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVPR005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		DateSearchForm pointRequestSearchForm = new DateSearchForm();
		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return displayPointRequestsListPageTop100(pointRequestSearchForm, model);
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("pointRequestSearchForm") DateSearchForm pointRequestSearchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVPR005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}
		// if (!result.hasErrors())
		this.loadPointRequestSearchForm(pointRequestSearchForm, model);
		return this.displayPointRequestsListPage(pointRequestSearchForm, model);
		// }

	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(ModelMap model,
			RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}
		String id = null;
		RequestForm orderRequestForm = this.getOrderRequestForm(id);
		// try {
		this.loadOrderRequestForm(orderRequestForm, model);
		// } catch (Exception e) {
		// }
		return this.displayOrderRequestFormPage(orderRequestForm, model);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String saveAction(
			@Valid @ModelAttribute("orderRequestForm") RequestForm orderRequestForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		Set validOrders = new HashSet();
		Collection orders = new ArrayList();

		/*
		 * this.validate(orderRequestForm.getGlobalItem1(),
		 * orderRequestForm.getQty1(), orderRequestForm.getUnitOfMeasure1(),
		 * result, utilities, 1, validOrders);
		 * 
		 * this.validate(orderRequestForm.getGlobalItem2(),
		 * orderRequestForm.getQty2(), orderRequestForm.getUnitOfMeasure2(),
		 * result, utilities, 2, validOrders);
		 * 
		 * this.validate(orderRequestForm.getGlobalItem3(),
		 * orderRequestForm.getQty3(), orderRequestForm.getUnitOfMeasure3(),
		 * result, utilities, 3, validOrders);
		 */
		boolean success = this.processOptionalOrders(orderRequestForm, req);

		if (!result.hasErrors() && success) {

			try {
				// OrderLine orderLine = null;
				/*
				 * Map map = null;
				 * 
				 * if (validOrders.contains(1)) { map = new HashMap();
				 * map.put("globalItem", orderRequestForm.getGlobalItem1());
				 * map.put("unitOfMeasure", Integer.parseInt(orderRequestForm
				 * .getUnitOfMeasure1())); map.put("qty",
				 * Integer.parseInt(orderRequestForm.getQty1()));
				 * orders.add(map); }
				 * 
				 * if (validOrders.contains(2)) { map = new HashMap();
				 * map.put("globalItem", orderRequestForm.getGlobalItem2());
				 * map.put("unitOfMeasure", Integer.parseInt(orderRequestForm
				 * .getUnitOfMeasure2())); map.put("qty",
				 * Integer.parseInt(orderRequestForm.getQty2()));
				 * orders.add(map); }
				 * 
				 * if (validOrders.contains(3)) { map = new HashMap();
				 * map.put("globalItem", orderRequestForm.getGlobalItem3());
				 * map.put("unitOfMeasure", Integer.parseInt(orderRequestForm
				 * .getUnitOfMeasure3())); map.put("qty",
				 * Integer.parseInt(orderRequestForm.getQty3()));
				 * orders.add(map); }
				 */
				// if optional orders list is not empty then we merge to
				// compulsory orders
				if (orderRequestForm.getOptionalOrders() != null
						&& !orderRequestForm.getOptionalOrders().isEmpty()) {
					orders.addAll(orderRequestForm.getOptionalOrders());
				}

				if ((orders == null || orders != null && orders.isEmpty())) {
					throw new InvalidOrderException("No order selected");
				}

				orderRequestForm.setOptionalOrders(orders);

				PointRequest pointRequest = this.orderRequestManager
						.savePointRequest(orderRequestForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! your request has been submitted succesfully ! ");
				return "redirect:/inventory/request/point_request_success/"
						+ pointRequest.getId();
				// } catch (Exception e) {
				// alert.setAlert(redirectAttributes,
				// Alert.DANGER,Text.UNKNOWN_ERROR);

			} catch (InvalidOrderException e) {
				this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
			}
		}
		this.loadOrderRequestForm(orderRequestForm, model);
		return this.displayOrderRequestFormPage(orderRequestForm, model);
	}

	private void loadPointRequestSearchForm(
			DateSearchForm pointRequestSearchForm, ModelMap model) {
		if (pointRequestSearchForm.getId() != 0
				&& !model.containsKey("pointRequestSearchForm")) {
			/*
			 * try { } catch (RecordNotFoundException e) { }
			 */
		}
		// load vendors list
		// model.addAttribute("vendorsList", this.vendorsList.fetchAll());
		String dte = pointRequestSearchForm.getDateOfRequest();
		// set todays date as default if there exist none
		if (dte == null || dte.isEmpty()) {
			pointRequestSearchForm.setDateOfRequest(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()));
		}

		// load request status
		// model.addAttribute("pointRequestStatusList",
		// PointRequestStatus.values());
	}

	private String displayPointRequestsListPage(
			DateSearchForm pointRequestSearchForm, ModelMap model) {
		// TODO Auto-generated method stub

		List<PointRequest> pointRequests = this.orderRequestManager
				.getPointRequests(pointRequestSearchForm);
		model.addAttribute("pointRequestSearchForm", pointRequestSearchForm);
		model.addAttribute("pointRequestsList", pointRequests);
		if (pointRequests == null) {
			this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND + " !!", model);
		}
		return "/inventory/request/index";
	}

	private String displayPointRequestsListPageTop100(
			DateSearchForm pointRequestSearchForm, ModelMap model) {
		// TODO Auto-generated method stub

		List<PointRequest> pointRequests = this.orderRequestManager
				.getPointRequestsTop100(pointRequestSearchForm);
		model.addAttribute("pointRequestSearchForm", pointRequestSearchForm);
		model.addAttribute("pointRequestsList", pointRequests);
		if (pointRequests == null) {
			this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND + " !!", model);
		}
		return "/inventory/request/index";
	}

	private void loadOrderRequestForm(RequestForm orderRequestForm,
			ModelMap model) {
		/*
		 * if (orderRequestForm.getId() != 0 &&
		 * !model.containsKey("orderRequestForm")) { }
		 */

		// load products list for the point current logged in user belongs
		// model.addAttribute("globalItemsList",
		// this.globalItemList.fetchAll());
		/*
		 * VisitWorkflowPoint visitWorkflowPoint = this.visitWorkflowPointBoImpl
		 * .getWorkflowPointById(this.userIdentity.getCurrentPointId());
		 * 
		 * model.addAttribute("globalItemsList",null);
		 * 
		 * /* try { model.addAttribute("unitOfMeasuresList1",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem1()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * } try { model.addAttribute("unitOfMeasuresList2",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem2()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * } try { model.addAttribute("unitOfMeasuresList3",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem3()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * }
		 */

		/*
		 * load line request form i.e item list,unit of measure list and
		 * corresponding quantities
		 */
		Collection optionalRequests = orderRequestForm.getOptionalOrders();
		if (optionalRequests != null && !optionalRequests.isEmpty()) {
			Map request = null;
			for (Object obj : optionalRequests) {

				if (obj != null) {
					request = (Map) obj;
					if (request.containsKey("globalItem")
							&& request.get("globalItem") != null) {
						try {
							request.put("unitOfMeasuresList", this
									.getGlobalItemUnitOfMeasureList(Integer
											.parseInt((String) request
													.get("globalItem"))));
						} catch (NumberFormatException e) {

						}
					}
				}
			}
		}
		/*
		 * String dte = orderRequestForm.getDateOfRequest(); // set todays date
		 * as default if there exist none if (dte == null || dte.isEmpty()) {
		 * orderRequestForm.setDateOfRequest(new SimpleDateFormat(
		 * "yyyy-MM-dd").format(new Date())); }
		 */

	}

	private RequestForm getOrderRequestForm(String id) {

		RequestForm orderRequestForm = new RequestForm();
		if (id != null) {
			try {
				orderRequestForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				orderRequestForm.setId(-1);
			}
		}
		return orderRequestForm;
	}

	private String displayOrderRequestFormPage(RequestForm orderRequestForm,
			ModelMap model) {
		model.addAttribute("orderRequestForm", orderRequestForm);
		return "/inventory/request/add";
	}

	private void validate(String globalItem, String qty, String unitOfMeasure,
			BindingResult result, Utilities utilities, int index,
			Set<Integer> validOrders) {
		int validOrderCnt = 0;

		if (globalItem != null && !globalItem.isEmpty()) {

			if (qty != null && utilities.isInteger(qty)) {
				validOrderCnt++;
			} else {
				result.rejectValue("qty" + index, "error.orderRequestForm",
						Text.ERROR_QUANTITY_NOT_NUMBER);
			}
			if (unitOfMeasure != null && !unitOfMeasure.isEmpty()) {
				validOrderCnt++;
			} else {
				result.rejectValue("unitOfMeasure" + index,
						"error.orderRequestForm", Text.INVALID_FIELD);
			}
			if (validOrderCnt >= 2) {
				validOrders.add(index);
			}

		} else {
			if ((qty != null && utilities.isInteger(qty))
					|| (unitOfMeasure != null && !unitOfMeasure.isEmpty())) {
				result.rejectValue("globalItem" + index,
						"error.orderRequestForm", Text.INVALID_PRODUCT);
			}
		}
	}

	/* method to process optional request from user if any */
	public boolean processOptionalOrders(RequestForm orderRequestForm,
			HttpServletRequest req) {
		// throw new UnsupportedOperationException("Not yet implemented");
		Collection<Map> optionalRequests = new ArrayList();

		orderRequestForm.setOptionalOrders(optionalRequests);
		Map request = null;
		Enumeration<String> parameters = req.getParameterNames();
		String currentParameter = null;
		int indx = -1;
		String currqty = null;
		String currUnitOfmeasure = null;
		boolean error = false;
		boolean storeData = false;
		// duplicate too
		while (parameters.hasMoreElements()) {
			currentParameter = parameters.nextElement();
			if (currentParameter.contains("globalItem")) {
				try {

					indx = Integer.parseInt(currentParameter
							.substring(("globalItem").length()));
					if (indx <= 3) {
						continue;
					}
					storeData = false;
					request = new HashMap();
					currqty = req.getParameter("qty" + indx);
					currUnitOfmeasure = req
							.getParameter("unitOfMeasure" + indx);
					request.put("prodErr", "");
					request.put("globalItem", "");
					request.put("qty", -1);
					request.put("unitOfMeasure", -1);
					request.put("unitOfMeasuresList", "");

					if (req.getParameter(currentParameter) != null) {
						// prepare to store data
						// we get the quantity associated wit prod
						if (req.getParameter(currentParameter) != null
								&& !req.getParameter(currentParameter)
										.isEmpty()) {
							storeData = true;
							if (this.utilities.isInteger(currqty)) {
							} else {
								error = true;
								// map.put("qty_error",
								// Statics.ERROR_QUANTITY_NOT_NUMBER);
							}

							if (currUnitOfmeasure != null
									&& !currUnitOfmeasure.isEmpty()) {
							} else {
								error = true;
								// map.put("qty_error",
								// Statics.ERROR_QUANTITY_NOT_NUMBER);
							}
						} else {
							if (this.utilities.isInteger(currqty)
									|| currUnitOfmeasure != null
									&& !currUnitOfmeasure.isEmpty()) {
								error = true;
								request.put("prodErr", Text.INVALID_PRODUCT);
								storeData = true;
							}
						}
					}
					if (storeData) {
						request.put("globalItem",
								req.getParameter(currentParameter));
						try {
							request.put("qty", Integer.parseInt(currqty));
						} catch (NumberFormatException nfe) {
							request.put("qty", -1);
						}

						try {
							request.put("unitOfMeasure",
									Integer.parseInt(currUnitOfmeasure));
						} catch (NumberFormatException nfe) {
							request.put("unitOfMeasure", -1);
						}
						request.put("indx", indx);

						optionalRequests.add(request);

					}
				} catch (IndexOutOfBoundsException e) {
					logger.info(e.getMessage());
				} catch (NumberFormatException e) {
					logger.info(e.getMessage());
				}
			}
		}
		if (error) {
			return false;
		} else {
			return true;
		}
	}

	private void loadProcessPointRequestForm(

	ProcessPointRequestForm processPointRequestForm, ModelMap model) {
		model.addAttribute("pointRequestStatusList",
				PointRequestStatus.values());
	}

	private String displayProcessPointRequestFormPage(int id,
			ProcessPointRequestForm processPointRequestForm,
			RedirectAttributes redirectAttributes, ModelMap model) {
		PointRequest pointRequest;
		try {
			pointRequest = this.orderRequestManager.getPointRequestById(id);

			System.out.print("point size"
					+ pointRequest.getPointRequestLines().size());

			model.addAttribute("pointRequest", pointRequest);
			model.addAttribute("processPointRequestForm",
					processPointRequestForm);

			this.orderRequestManager
					.getGlobalItemsQuantityAvailable(pointRequest
							.getPointRequestLines());
			return "/inventory/request/process_request";
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/request";

		}

	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
	public String processRequest(@PathVariable int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVPR005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		ProcessPointRequestForm processPointRequestForm = new ProcessPointRequestForm();

		VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

		model.addAttribute("vform", vform);

		loadProcessPointRequestForm(processPointRequestForm, model);

		return this.displayProcessPointRequestFormPage(id,
				processPointRequestForm, redirectAttributes, model);

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String deletePointRequest(@PathVariable int id, ModelMap model,
			RedirectAttributes redirectAttributes)

	{

		if (!authorize.isAllowed("INVPR004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		PointRequest pointRequest = null;
		try {
			pointRequest = this.orderRequestManager.getPointRequestById(id);
			model.addAttribute("pointRequest", pointRequest);
			model.addAttribute("pageDelete", true);

		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		DateSearchForm pointRequestSearchForm = new DateSearchForm();
		if (pointRequest != null) {
			pointRequestSearchForm.setDateOfRequest(pointRequest
					.getRequestDate().toString());
		}
		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return this.displayPointRequestsListPage(pointRequestSearchForm, model);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String confirmDeleteRequestAction(
			@RequestParam(value = "requestId", required = false) int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		if (!authorize.isAllowed("INVPR004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		PointRequest pointRequest = null;
		String mydt = null;
		System.out.print("mydt before" + mydt);

		try {
			pointRequest = this.orderRequestManager.getPointRequestById(id);
			mydt = pointRequest.getRequestDate().toString();
			System.out.print("mydt after" + mydt);

			// we can go ahead and delete this request status
			this.orderRequestManager.deletePointRequest(pointRequest);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Request deleted successfully");
			return "redirect:/inventory/request";

		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		DateSearchForm pointRequestSearchForm = new DateSearchForm();

		if (pointRequest != null) {

			pointRequestSearchForm.setDateOfRequest(pointRequest
					.getRequestDate().toString());
		}
		System.out.print("mydt" + mydt);

		pointRequestSearchForm.setDateOfRequest(mydt);

		model.addAttribute("pointRequest", pointRequest);
		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return displayUserPointRequestsListPage(pointRequestSearchForm, model);

	}

	@RequestMapping(value = "/point_request_success/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String requestSuccess(@PathVariable int id, ModelMap model,
			RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		try {
			PointRequest pointRequest = this.orderRequestManager
					.getPointRequestById(id);
			model.addAttribute("pointRequest", pointRequest);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return "/inventory/request/point_request_success";
	}

	// line request

	@RequestMapping(value = "/line_request/approve", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String approveLineRequest(ModelMap model, HttpServletRequest req,
			ProcessPointRequestForm processPointRequestForm,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVAP005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		boolean error = false;
		int reqLineId = 0;
		try {
			reqLineId = Integer.parseInt(req.getParameter("request_line_id"));
		} catch (NumberFormatException e) {
			this.alert(true, Alert.DANGER, "Invalid Request Line Id", model);
			error = true;
		}
		int qty = 0;
		try {
			qty = Integer.parseInt(req.getParameter("qty_approved"));
		} catch (NumberFormatException e) {
			this.alert(true, Alert.DANGER, "Quantity entered " + qty
					+ " is not a number", model);
			error = true;
		}
		if (!error) {
			try {
				this.orderRequestManager
						.approvePointLineRequest(reqLineId, qty);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success!!! Request approved successfully");
				return "redirect:/inventory/request/process/"
						+ req.getParameter("request_id");
			} catch (InvalidStockLevelException e) {
				this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
				// alert.setAlert(redirectAttributes, Alert.DANGER, )
			} catch (RecordNotFoundException e) {
				this.alert(true, Alert.DANGER,
						"Error! Invalid resource (Resource id:" + reqLineId
								+ ")", model);
			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
			}
		}
		this.loadProcessPointRequestForm(processPointRequestForm, model);
		int pointReqId = 0;
		try {
			pointReqId = Integer.parseInt(req.getParameter("request_id"));
		}

		catch (NumberFormatException e) {
		}

		return this.displayProcessPointRequestFormPage(pointReqId,
				processPointRequestForm, redirectAttributes, model);

	}

	@RequestMapping(value = "/line_request/disapprove/{id}/batch/{bid}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String disapproveRequest(@PathVariable int id,
			@PathVariable int bid,
			ProcessPointRequestForm processPointRequestForm, ModelMap model,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVDP005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");

		try {
			PointRequestLine pointRequestLine = this.orderRequestManager
					.getPointRequestLineById(id);
			this.orderRequestManager
					.disapprovePointRequestLine(pointRequestLine);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Point request disaproved successfully");
			return "redirect:/inventory/request/process/" + bid;
		} catch (RecordNotFoundException e) {
			this.alert(true,
					"Error! Invalid resource (Resource id:" + id + ")",
					e.getExceptionMsg(), model);
		}
		this.loadProcessPointRequestForm(processPointRequestForm, model);

		return this.displayProcessPointRequestFormPage(bid,
				processPointRequestForm, redirectAttributes, model);
	}

	/*
	 * @RequestMapping(value = "/line_request/issue/{id}/batch/{bid}", method =
	 * RequestMethod.GET)
	 * 
	 * @Layout(value = "layouts/form_wizard_layout") public String
	 * issueRequest(@PathVariable int id, @PathVariable int bid,
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model,
	 * RedirectAttributes redirectAttributes) {
	 * 
	 * userIdentity.setCurrentUrl("redirect:/inventory/request");
	 * 
	 * if (!unitconvert.checkCurrentunit(12)) {
	 * alert.setAlert(redirectAttributes, Alert.DANGER,
	 * "Please choose your current store unit");
	 * 
	 * return "redirect:/visits/queue/12"; }
	 * 
	 * Alert alert = (Alert) model.get("alert"); try { PointRequestLine
	 * pointRequestLine = this.orderRequestManager .getPointRequestLineById(id);
	 * this.orderRequestManager.issuePointRequestLine(pointRequestLine);
	 * alert.setAlert(redirectAttributes, Alert.SUCCESS,
	 * "Success! Point request issued successfully"); return
	 * "redirect:/inventory/request/process/" + bid; } catch
	 * (RecordNotFoundException e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); } catch (InvalidStockLevelException e) {
	 * this.alert(true, Alert.DANGER, e.getExceptionMsg(), model); } catch
	 * (InvalidUnitOfMeasureConfiguration e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); }
	 * this.loadProcessPointRequestForm(processPointRequestForm, model); return
	 * this.displayProcessPointRequestFormPage(bid,
	 * processPointRequestForm,redirectAttributes, model); }
	 * 
	 * 
	 * @RequestMapping(value = "/line_request/reverseissue/{id}/batch/{bid}",
	 * method = RequestMethod.GET)
	 * 
	 * @Layout(value = "layouts/form_wizard_layout") public String
	 * reverseissueRequest(@PathVariable int id, @PathVariable int bid,
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model,
	 * RedirectAttributes redirectAttributes) {
	 * 
	 * userIdentity.setCurrentUrl("redirect:/inventory/request");
	 * 
	 * if (!unitconvert.checkCurrentunit(12)) {
	 * alert.setAlert(redirectAttributes, Alert.DANGER,
	 * "Please choose your current store unit");
	 * 
	 * return "redirect:/visits/queue/12"; }
	 * 
	 * Alert alert = (Alert) model.get("alert"); try { PointRequestLine
	 * pointRequestLine = this.orderRequestManager .getPointRequestLineById(id);
	 * this.orderRequestManager.revissuePointRequestLine(pointRequestLine);
	 * alert.setAlert(redirectAttributes, Alert.SUCCESS,
	 * "Success! Point request issued reversed successfully"); return
	 * "redirect:/inventory/request/process/" + bid; } catch
	 * (RecordNotFoundException e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); } catch (InvalidStockLevelException e) {
	 * this.alert(true, Alert.DANGER, e.getExceptionMsg(), model); } catch
	 * (InvalidUnitOfMeasureConfiguration e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); }
	 * this.loadProcessPointRequestForm(processPointRequestForm, model); return
	 * this.displayProcessPointRequestFormPage(bid,
	 * processPointRequestForm,redirectAttributes, model); }
	 */
	@RequestMapping(value = "/line_request/give", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String issueRequest(VisitWorkflowUserConfigurationForm vform,
			ModelMap model, RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVIR005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		try {

			PointRequestLine pointRequestLine = this.orderRequestManager
					.getPointRequestLineById(vform.getId());

			if (vform.getQty().intValue() == 0) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Qty cannot be zero");

				return "redirect:/inventory/request/process/" + vform.getBid();
			}

			if (pointRequestLine.getTotdisp() + vform.getQty() > pointRequestLine
					.getQuantity()) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Total quantity issued cannot be greater than quantity requested");

				return "redirect:/inventory/request/process/" + vform.getBid();

			}

			this.orderRequestManager.issuePointRequestLine(pointRequestLine,
					vform);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Point request issued successfully");
			return "redirect:/inventory/request/process/" + vform.getBid();
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		} catch (InvalidStockLevelException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		} catch (InvalidUnitOfMeasureConfiguration e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		this.loadProcessPointRequestForm(null, model);
		return this.displayProcessPointRequestFormPage(vform.getId(), null,
				redirectAttributes, model);
	}

	@RequestMapping(value = "/line_request/reverseissue/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String reverseissueRequestlog(@PathVariable int id, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INVRVIS5")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		// Alert alert = (Alert) model.get("alert");
		Storeissue_log storeIssueLog = null;
		try {

			// PointRequestLine pointRequestLine = this.orderRequestManager
			// .getPointRequestLineById(id);

			storeIssueLog = this.orderRequestManager.getStoreIssueById(id);

			if (storeIssueLog != null) {
				if (storeIssueLog.getAction().equals("Reversed"))

				{
					alert.setAlert(redirectAttributes, Alert.DANGER,
							"Already Reversed");

					return "redirect:/inventory/request/line_request/storeissuelog/view/"
							+ storeIssueLog.getPointrequestline().getId();
				}
			} else {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Issue Does not Exist");

				return "redirect:/inventory/request/line_request/storeissuelog/view/"
						+ storeIssueLog.getPointrequestline().getId();

			}

			this.orderRequestManager.revissuePointRequestLine(storeIssueLog);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Point request issued reversed successfully");
			// return "redirect:/inventory/request/process/" + bid;

		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		} catch (InvalidStockLevelException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		} catch (InvalidUnitOfMeasureConfiguration e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		// this.loadProcessPointRequestForm(processPointRequestForm, model);
		// return this.displayProcessPointRequestFormPage(bid,
		// processPointRequestForm,redirectAttributes, model);

		return "redirect:/inventory/request/line_request/storeissuelog/view/"
				+ storeIssueLog.getPointrequestline().getId();

	}

	@RequestMapping(value = "/line_request/storeissuelog/view/{id}", method = RequestMethod.GET)
	public String viewstoreissuelog(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		// if (Authorize.isAllowed("VISPHA002"))
		// {

		// Set<VisitConsultationPrescriptionMeasurement> set;
		PointRequestLine pointRequestLine = null;
		try {

			pointRequestLine = orderRequestManager.getPointRequestLineById(id);

			VisitWorkflowUserConfigurationForm vform = new VisitWorkflowUserConfigurationForm();

			model.addAttribute("vform", vform);

			model.addAttribute("vpd", pointRequestLine);
			return "inventory/request/process_request_log";

		}

		catch (Exception e) {
			alert.setAlert(redirectAttributes, Alert.WARNING, e.getMessage());
			return "redirect:/inventory/request/process/"
					+ pointRequestLine.getPointRequest().getId();

		}

	}

	// else
	// {
	// alert.setAlert(redirectAttributes, Alert.WARNING,
	// "You have no permission to View Dispense Log");
	//
	// return "redirect:/";
	// }
	// }

	@RequestMapping(value = "/line_request/delete/{id}/batch/{bid}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String deleteRequest(@PathVariable int id, @PathVariable int bid,
			RedirectAttributes redirectAttributes, ModelMap model) {
		if (!authorize.isAllowed("INVPR005")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		try {
			PointRequestLine pointRequestLine = this.orderRequestManager
					.getPointRequestLineById(id);
			model.addAttribute("pointRequestLine", pointRequestLine);
			model.addAttribute("pageDelete", true);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		ProcessPointRequestForm processPointRequestForm = new ProcessPointRequestForm();
		loadProcessPointRequestForm(processPointRequestForm, model);
		return this.displayProcessPointRequestFormPage(bid,
				processPointRequestForm, redirectAttributes, model);
	}

	@RequestMapping(value = "/line_request/delete/{id}/batch/{bid}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String confirmDeleteLineRequest(
			@RequestParam(value = "requestLineId", required = false) int id,
			@RequestParam(value = "requestBatchId", required = false) int bid,
			RedirectAttributes redirectAttributes, ModelMap model) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		PointRequestLine pointRequestLine = null;
		try {
			pointRequestLine = this.orderRequestManager
					.getPointRequestLineById(id);

			// we can go ahead and delete this request status
			this.orderRequestManager.deletePointRequestLine(pointRequestLine);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Request line deleted successfully");
			return "redirect:/inventory/request/process/" + bid;

		} catch (RecordNotFoundException e) {
			this.modalAlert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		ProcessPointRequestForm processPointRequestForm = new ProcessPointRequestForm();
		loadProcessPointRequestForm(processPointRequestForm, model);
		return this.displayProcessPointRequestFormPage(bid,
				processPointRequestForm, redirectAttributes, model);
	}

	@RequestMapping(value = { "/point" })
	@Layout("layouts/datatable")
	public String view(ModelMap model, RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		DateSearchForm pointRequestSearchForm = new DateSearchForm();
		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return displayUserPointRequestsListPage(pointRequestSearchForm, model);
	}

	private String displayUserPointRequestsListPage(
			DateSearchForm pointRequestSearchForm, ModelMap model) {
		List<PointRequest> pointRequests = null;
		try {
			pointRequests = this.orderRequestManager
					.getUserPointRequests(pointRequestSearchForm);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND + " !!", model);
		}
		model.addAttribute("pointRequestSearchForm", pointRequestSearchForm);
		model.addAttribute("pointRequestsList", pointRequests);
		return "/inventory/request/point/index";
	}

	@RequestMapping(value = "point/view/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewPointrequestByUser(@PathVariable int id, ModelMap model,
			RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		try {
			PointRequest pointRequest = this.orderRequestManager
					.getPointRequestById(id);
			model.addAttribute("pointRequest", pointRequest);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return "/inventory/request/point/view";
	}

	@RequestMapping(value = "point/delete/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String deletePointrequestByUser(@PathVariable int id,
			ModelMap model, RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}
		PointRequest pointRequest = null;
		try {
			pointRequest = this.orderRequestManager.getPointRequestById(id);
			model.addAttribute("pointRequest", pointRequest);
			model.addAttribute("pageDelete", true);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		DateSearchForm pointRequestSearchForm = new DateSearchForm();
		if (pointRequest != null) {
			System.out.print("point is not null");
			pointRequestSearchForm.setDateOfRequest(pointRequest
					.getRequestDate().toString());
		}
		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return displayUserPointRequestsListPage(pointRequestSearchForm, model);
	}

	@RequestMapping(value = "point/delete/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String confirmDeleteAction(
			@RequestParam(value = "requestId", required = false) int id,
			// @RequestParam(value = "dateOfRequest", required = false) String
			// doreq,
			RedirectAttributes redirectAttributes, ModelMap model) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		Alert alert = (Alert) model.get("alert");
		PointRequest pointRequest = null;
		try {
			pointRequest = this.orderRequestManager.getPointRequestById(id);

			if (pointRequest.getRequestStatus() == PointRequestStatus.Pending
					.getCode()) {
				// we can go ahead and delete this request status
				this.orderRequestManager.deletePointRequest(pointRequest);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Request deleted successfully");
				return "redirect:/inventory/request/point";
			} else {
				this.modalAlert(
						true,
						Alert.DANGER,
						"Request cannot be removed because request is under processing",
						model);
			}
			model.addAttribute("pointRequest", pointRequest);
			model.addAttribute("pageDelete", true);
		} catch (RecordNotFoundException e) {
			this.modalAlert(true, Alert.DANGER,
					"Error! Invalid resource (Resource id:" + id + ")", model);
		}
		DateSearchForm pointRequestSearchForm = new DateSearchForm();
		if (pointRequest != null) {

			pointRequestSearchForm.setDateOfRequest(pointRequest
					.getRequestDate().toString());
		}

		loadPointRequestSearchForm(pointRequestSearchForm, model);
		return displayUserPointRequestsListPage(pointRequestSearchForm, model);

	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "point/search", method = RequestMethod.POST)
	public String searchRequestAction(
			@Valid @ModelAttribute("pointRequestSearchForm") DateSearchForm pointRequestSearchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		userIdentity.setCurrentUrl("redirect:/inventory/request");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		this.loadPointRequestSearchForm(pointRequestSearchForm, model);
		return this.displayUserPointRequestsListPage(pointRequestSearchForm,
				model);

	}

	/*
	 * @RequestMapping(value = "/process/{id}", method = RequestMethod.POST)
	 * 
	 * @Layout(value = "layouts/form_wizard_layout") public String
	 * processRequest(ModelMap model, HttpServletRequest req,
	 * ProcessPointRequestForm processPointRequestForm) {
	 * 
	 * boolean error = false; int reqLineId = 0; try { reqLineId =
	 * Integer.parseInt(req.getParameter("request_line_id")); } catch
	 * (NumberFormatException e) { this.alert(true, Alert.DANGER,
	 * "Invalid Request Line Id", model); error = true; } int qty = 0;
	 * 
	 * try { qty = Integer.parseInt(req.getParameter("qty_approved")); } catch
	 * (NumberFormatException e) { this.alert(true, Alert.DANGER,
	 * "Quantity entered "+qty+" is not a number", model); error = true; } if
	 * (!error) { try { this.orderRequestManager
	 * .approvePointLineRequest(reqLineId, qty); return
	 * "redirect:/inventory/request/process/" + req.getParameter("request_id");
	 * } catch (InvalidStockLevelException e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); } catch (RecordNotFoundException e) {
	 * this.alert(true, Alert.DANGER, e.getExceptionMsg(), model); } catch
	 * (InvalidUnitOfMeasureConfiguration e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); } catch (InvalidOpeningStockBalanceException
	 * e) { e.printStackTrace(); } }
	 * this.loadProcessPointRequestForm(processPointRequestForm, model); return
	 * this .displayProcessPointRequestFormPage(
	 * processPointRequestForm.getId(), processPointRequestForm, model); }
	 */
}
