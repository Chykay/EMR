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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.InvalidOrderException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.forms.RequestForm;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.ProcessPointRequestForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.OrderLine;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.service.Utilities;
import org.calminfotech.inventory.serviceInterface.OrderManagerInterface;
import org.calminfotech.inventory.serviceInterface.PointRequestManagerInterface;
import org.calminfotech.inventory.utils.CustomValidator;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory/point/order")
public class OrdersController extends AbstractBaseController {

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private Utilities utilities;

	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private OrderManagerInterface orderManager;

	@RequestMapping(value = { "/", "" })
	@Layout("layouts/datatable")
	public String indexAction(ModelMap model) {

		DateSearchForm orderSearchForm = new DateSearchForm();
		loadOrderSearchForm(orderSearchForm, model);
		return displayOrdersListPage(orderSearchForm, model);
	}

	private void loadOrderSearchForm(DateSearchForm orderSearchForm,
			ModelMap model) {
		if (orderSearchForm.getId() != 0
				&& !model.containsKey("orderSearchForm")) {
			/*
			 * try { } catch (RecordNotFoundException e) { }
			 */
		}
		// load vendors list
		// model.addAttribute("vendorsList", this.vendorsList.fetchAll());
		String dte = orderSearchForm.getDateOfRequest();
		// set todays date as default if there exist none
		if (dte == null || dte.isEmpty()) {
			orderSearchForm.setDateOfRequest(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
		}

	}

	private String displayOrdersListPage(DateSearchForm orderSearchForm,
			ModelMap model) {
		// TODO Auto-generated method stub

		List<PointRequest> pointRequests = this.orderManager
				.getOrders(orderSearchForm);
		model.addAttribute("orderSearchForm", orderSearchForm);
		model.addAttribute("pointRequestsList", pointRequests);
		if (pointRequests == null) {
			this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND + " !!", model);
		}
		return "/inventory/point/order/index";
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("orderSearchForm") DateSearchForm orderSearchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		// if (!result.hasErrors())
		this.loadOrderSearchForm(orderSearchForm, model);
		return this.displayOrdersListPage(orderSearchForm, model);
		// }

	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(ModelMap model) {

		String id = null;
		OrderForm orderForm = this.getOrderForm(id);
		// try {
		this.loadOrderForm(orderForm, model);
		// } catch (Exception e) {
		// }
		return this.displayOrderFormPage(orderForm, model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String editOrder(ModelMap model, HttpSession session) {

		String id = null;
		OrderForm orderForm = this.getOrderForm(id);
		Object obj = session.getAttribute("order");
		if (obj != null && obj instanceof Order) {
			Order order = (Order) obj;
			if (order != null) {
				Set<OrderLine> orderLines = order.getOrderLines();
				if (orderLines != null) {
					Collection<Map> optionalRequests = new ArrayList();
					orderForm.setOptionalOrders(optionalRequests);
					orderForm.setDateOfOrder(DateUtils.formatDateToString(order.getDateOfOrder()));
					Map request = null;
					for (OrderLine orderLine : orderLines) {
						request = new HashMap();
						request.put("prodErr", "");
						request.put("globalItem", String.valueOf(orderLine
								.getGlobalItem().getItemId()));
						request.put("qty", orderLine.getQuantity());
						request.put("unitOfMeasure", orderLine
								.getGlobalUnitofMeasure().getId());
						request.put("unitOfMeasuresList", "");
						optionalRequests.add(request);
					}
				}
			}
			session.removeAttribute("order");
		} else {
			this.alert(true, Alert.DANGER, "Order not found", model);
		}
		// try {
		this.loadOrderForm(orderForm, model);
		// } catch (Exception e) {
		// }
		return this.displayOrderFormPage(orderForm, model);
	}

	private void loadOrderForm(OrderForm orderForm, ModelMap model) {
		/*
		 * if (orderRequestForm.getId() != 0 &&
		 * !model.containsKey("orderRequestForm")) { }
		 */

		// load products list for the point current logged in user belongs
		// model.addAttribute("globalItemsList",
		// this.globalItemList.fetchAll());
		VisitWorkflowPoint visitWorkflowPoint = this.visitWorkflowPointBoImpl
				.getWorkflowPointById(this.userIdentity.getCurrentPointId());
		model.addAttribute("globalItemsList", null);

		/*
		 * try { model.addAttribute("unitOfMeasuresList1",
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
		Collection optionalOrders = orderForm.getOptionalOrders();
		if (optionalOrders != null && !optionalOrders.isEmpty()) {
			Map request = null;
			for (Object obj : optionalOrders) {

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
		
		String dte = orderForm.getDateOfOrder();
		// set todays date as default if there exist none
		if (dte == null || dte.isEmpty()) {
			orderForm.setDateOfOrder(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()));
		}
	}

	private OrderForm getOrderForm(String id) {

		OrderForm orderForm = new OrderForm();
		if (id != null) {
			try {
				orderForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				orderForm.setId(-1);
			}
		}
		return orderForm;
	}

	private String displayOrderFormPage(OrderForm orderForm, ModelMap model) {
		model.addAttribute("orderForm", orderForm);
		return "/inventory/point/order/add";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String saveAction(
			@Valid @ModelAttribute("orderForm") OrderForm orderForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req,
			HttpSession session) {

		Alert alert = (Alert) model.get("alert");
		Set validOrders = new HashSet();
		Collection orders = new ArrayList();
		/*
		 * this.validate(orderRequestForm.getGlobalItem1(),
		 * orderRequestForm.getQty1(), orderRequestForm.getUnitOfMeasure1(),
		 * result, utilities, 1, validOrders);
		 */
		CustomValidator validator = new CustomValidator();
		String date = orderForm.getDateOfOrder();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateOfOrder", "error.orderForm",
					Text.INVALID_DATE_FORMAT);
		}
		
		boolean success = this.processOptionalOrders(orderForm, req);
		if (!result.hasErrors() && success) {

			try {

				// if optional orders list is not empty then we merge to
				// compulsory orders
				if (orderForm.getOptionalOrders() != null
						&& !orderForm.getOptionalOrders().isEmpty()) {
					orders.addAll(orderForm.getOptionalOrders());
				}

				if ((orders == null || (orders != null && orders.isEmpty()))) {
					throw new InvalidOrderException("No order selected");
				}
				orderForm.setOptionalOrders(orders);

				Order order = this.orderManager.processOrder(orderForm);
				session.setAttribute("order", order);
				return displayOrderConfirmationPage(order, model);

			} catch (InvalidOrderException e) {
				this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
			}
		}
		this.loadOrderForm(orderForm, model);
		return this.displayOrderFormPage(orderForm, model);
	}

	private String displayOrderConfirmationPage(Order order, ModelMap model) {
		// TODO Auto-generated method stub
		model.addAttribute("order", order);
		model.addAttribute("order_date", DateUtils.formatDateToString(order.getDateOfOrder()));
		// we save order in session to process order confirmation
		return "/inventory/point/order/confirm_order";

	}

	@RequestMapping(value = "/confirm_order", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String confirmOrderAction(ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req,
			HttpSession session) {

		Alert alert = (Alert) model.get("alert");
		Object obj = session.getAttribute("order");
		if (obj != null && obj instanceof Order) {
			Order order = (Order) obj;
			try {
				this.orderManager.confirmOrder(order);
				// get the saved order from database for confirmation
				session.removeAttribute("order");
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! order created succesfully!!");
				return "redirect:/inventory/point/order/view/"+order.getId();

			} catch (InvalidOrderException e) {
				session.setAttribute("order", order);
				return displayOrderConfirmationPage(order, model);
			}
		}
		return null;

	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewOrderUser(@PathVariable int id, ModelMap model) {
		try {
			return this.displayOrder(id, model);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return null;
	}

	public String displayOrder(int id, ModelMap model)
			throws RecordNotFoundException {
       Order order= this.orderManager.getOrderById(id);
		model.addAttribute("order",order);
		return "/inventory/point/order/order_success";

	}
	
	/*@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewPointrequestByUser(@PathVariable int id, ModelMap model) {
		try {
			Order order = this.orderManager.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return "/inventory/point/order/view";
	}*/

	/*
	 * @RequestMapping(value = "/confirm_order", method = RequestMethod.POST)
	 * 
	 * @Layout(value = "layouts/form_wizard_layout") public String saveAction(
	 * ModelMap model, RedirectAttributes redirectAttributes, HttpServletRequest
	 * req, HttpSession session) {
	 * 
	 * Alert alert = (Alert) model.get("alert"); Set validOrders = new
	 * HashSet(); Collection orders = new ArrayList(); boolean success =
	 * this.processOptionalOrders(orderForm, req); Order order =null; try {
	 * 
	 * order = this.orderManager.processOrder(orderForm); //we save order in
	 * session to process order confirmation
	 * session.setAttribute("order",order); } catch (InvalidOrderException e) {
	 * model.addAttribute("pageView",true); model.addAttribute("order",order); }
	 * this.loadOrderForm(null, model); return this.displayOrderFormPage(null,
	 * model); }
	 */
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
	public boolean processOptionalOrders(OrderForm orderForm,
			HttpServletRequest req) {
		// throw new UnsupportedOperationException("Not yet implemented");
		Collection<Map> optionalRequests = new ArrayList();

		orderForm.setOptionalOrders(optionalRequests);
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

	@RequestMapping(value = "/order_success/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String requestSuccess(@PathVariable int id, ModelMap model) {
		try {
			Order order = this.orderManager.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return "/inventory/point/order/order_success";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String deleteRequest(@PathVariable int id, ModelMap model) {

		try {
			Order order = this.orderManager.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.DANGER, e.getExceptionMsg(), model);
		}
		return "/inventory/point/order/order_success";
	}

	

	/*
	 * @Layout(value = "layouts/form_wizard_layout")
	 * 
	 * @RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
	 * public String processRequest(@PathVariable int id, ModelMap model) {
	 * 
	 * ProcessPointRequestForm processPointRequestForm = new
	 * ProcessPointRequestForm();
	 * loadProcessPointRequestForm(processPointRequestForm, model); return
	 * this.displayProcessPointRequestFormPage(id, processPointRequestForm,
	 * model); }
	 * 
	 * private void loadProcessPointRequestForm(
	 * 
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model) {
	 * model.addAttribute("pointRequestStatusList",
	 * PointRequestStatus.values()); }
	 * 
	 * private String displayProcessPointRequestFormPage(int id,
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model) { try {
	 * PointRequest pointRequest = this.orderManager .getPointRequestById(id);
	 * model.addAttribute("pointRequest", pointRequest);
	 * model.addAttribute("processPointRequestForm", processPointRequestForm);
	 * 
	 * 
	 * Map m = this.orderManager .getGlobalItemsQuantityAvailable(pointRequest
	 * .getPointRequestLines());
	 * 
	 * } catch (RecordNotFoundException e) { this.alert(true, Alert.DANGER,
	 * e.getExceptionMsg(), model); } return
	 * "/inventory/order_requests/process"; }
	 */

}
