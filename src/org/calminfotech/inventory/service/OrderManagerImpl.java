package org.calminfotech.inventory.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.calminfotech.inventory.daoInterface.OrderDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.InvalidOrderException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.OrderLine;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.inventory.serviceInterface.OrderManagerInterface;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.system.daoInterface.BillingSchemeItemPriceDetailsDao;
//import org.calminfotech.system.models.BillingSchemeItemPriceDetails;

@Service
public class OrderManagerImpl implements OrderManagerInterface {

	@Autowired
	private OrderDaoInterface OrderDaoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Autowired
	private InventoryManagerInterface inventoryManagerImpl;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private GlobalUnitofMeasureBo globalUnitofMeasureBo;

	@Autowired
	private CodeGenerator codeGenerator;

	@Override
	public List<PointRequest> getOrders(DateSearchForm orderSearchForm) {
		return null;
	}

	// @Autowired
	// private BillingSchemeItemPriceDetailsDao billingSchemePriceDetails;

	@Override
	public Order saveOrder(OrderForm orderForm)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration {

		/*
		 * Collection<Map> orderLinesMapList = orderForm.getOptionalOrders();
		 * Date date = new Date(); if (orderLinesMapList != null &&
		 * !orderLinesMapList.isEmpty()) {
		 * 
		 * Set<OrderLine> orderLines = new HashSet(); OrderLine orderLine =
		 * null; StringBuilder msgBuffer=new StringBuilder();
		 * 
		 * GlobalItem globalItem = null; GlobalUnitofMeasure globalUnitofMeasure
		 * = null; int unitOfMeasure=0; int globalItemId=0; int qty=0;
		 * 
		 * for (Map orderLineMap : orderLinesMapList) {
		 * 
		 * unitOfMeasure=(Integer) orderLineMap.get("unitOfMeasure");
		 * globalItemId=Integer.parseInt((String)
		 * orderLineMap.get("globalItem")); qty=(Integer)
		 * orderLineMap.get("qty");
		 * 
		 * if(this.isStockAvailable(globalItemId, unitOfMeasure, qty,0)){
		 * orderLine = new OrderLine(); orderLine.setQuantity(qty);
		 * orderLine.setCreateDate(date);
		 * orderLine.setCreatedBy(this.userIdentity.getUsername());
		 * 
		 * globalItem = new GlobalItem(); globalItem.setId(globalItemId);
		 * 
		 * orderLine.setGlobalItem(globalItem); globalUnitofMeasure = new
		 * GlobalUnitofMeasure(); globalUnitofMeasure.setId((Integer)
		 * orderLineMap .get("unitOfMeasure"));
		 * 
		 * orderLine.setGlobalUnitofMeasure(globalUnitofMeasure);
		 * orderLine.setQuantity((Integer) orderLineMap.get("qty"));
		 * orderLine.setOrder(order); orderLines.add(orderLine); orderLine = new
		 * OrderLine();
		 * 
		 * }else{ msgBuffer.append("Invalid stock level:<br>");
		 * 
		 * } } String msg=msgBuffer.toString(); if(){
		 * 
		 * } //pointRequest.setPointRequestLines(orderLines); }
		 */
		return null;
	}

	@Override
	public Order processOrder(OrderForm orderForm) {

		Collection<Map> orderLinesMapList = orderForm.getOptionalOrders();

		Order order = null;

		if (orderLinesMapList != null && !orderLinesMapList.isEmpty()) {

			Set<OrderLine> orderLines = new HashSet();
			order = new Order();
			order.setOrderLines(orderLines);
			// order.setOrderDate(DateUtils.formatStringToDate(orderForm
			// .getOrderDate()));
			// Date date=new Date();

			Date date = DateUtils.formatStringToDate(
					orderForm.getDateOfOrder(), "yyyy-MM-dd");

			order.setDateOfOrder(date);
			order.setCreateDate(date);
			order.setOrganisation(this.userIdentity.getOrganisation());
			order.setCreatedBy(this.userIdentity.getUsername());

			OrderLine orderLine = null;

			StringBuilder msgBuffer = new StringBuilder();

			GlobalItem globalItem = null;
			// GlobalUnitofMeasure globalUnitofMeasure = null;
			GlobalItemUnitofMeasureVw globalUnitofMeasure = null;

			int unitOfMeasure = 0;
			int globalItemId = 0;
			int qtyOrderedInMeasure = 0;
			// double totalPrice = 0;
			double unitPrice = 0;

			// used to cache global item current balance while checkin stock
			// level
			Map<Integer, Integer> checkedQtyMap = new HashMap();
			int currQtyChecked = 0;
			int qtyOrderedInUnit = 0;

			for (Map orderLineMap : orderLinesMapList) {

				unitOfMeasure = (Integer) orderLineMap.get("unitOfMeasure");
				globalItemId = Integer.parseInt((String) orderLineMap
						.get("globalItem"));
				qtyOrderedInMeasure = (Integer) orderLineMap.get("qty");
				// orderLineMap.put("unit_price",0);

				globalItem = globalItemBo.getGlobalItemById(globalItemId);
				// globalUnitofMeasure = globalUnitofMeasureBo
				// .getUnitofMeasureById(unitOfMeasure);

				orderLine = new OrderLine();
				orderLine.setQuantity(qtyOrderedInMeasure);

				orderLine.setGlobalItem(globalItem);
				orderLine.setGlobalUnitofMeasure(globalUnitofMeasure);
				orderLine.setCreateDate(date);
				orderLine.setOrganisation(this.userIdentity.getOrganisation());
				orderLine.setCreatedBy(this.userIdentity.getUsername());

				try {
					/*
					 * qtyOrderedInUnit = this.unitOfMeasureConverter
					 * .convertUnitOfMeasureToUnitOptionA(globalItemId,
					 * unitOfMeasure, qtyOrderedInMeasure);
					 */

					qtyOrderedInUnit = this.unitOfMeasureConverter
							.convertmeasuretounits(qtyOrderedInMeasure,
									unitOfMeasure);

					if (checkedQtyMap.containsKey(globalItemId)) {
						currQtyChecked = checkedQtyMap.get(globalItemId);
						// increment qty ordered by qty checked to get actual
						// qty ordered since we are doin
						// incremental processing
						checkedQtyMap.put(globalItemId,
								qtyOrderedInUnit += currQtyChecked);
					} else {
						checkedQtyMap.put(globalItemId, qtyOrderedInUnit);
					}

					// we then check stock availablity
					// if (this.isStockAvailable(globalItemId, unitOfMeasure,
					// qtyOrderedInUnit, 0)) {

					if (this.inventoryManagerImpl.isPointStockAvailble(
							globalItemId, unitOfMeasure, qtyOrderedInUnit,

							this.userIdentity.getCurrentPointId(),
							this.userIdentity.getCurrentUnitId())) {

						// get unit price of global item and unit of measure
						// ordered

						/*
						 * List<BillingSchemeItemPriceDetails> prices =
						 * this.billingSchemePriceDetails
						 * .getBillingInvoice(userIdentity.getBillId(),
						 * unitOfMeasure, globalItemId,
						 * this.userIdentity.getOrganisation() .getId()); if
						 * (prices != null) { for (BillingSchemeItemPriceDetails
						 * price : prices) { try { unitPrice =
						 * Double.parseDouble(price .getPrice()); } catch
						 * (NumberFormatException e) { } } }
						 */

						// orderLineMap.put("unit_price",unitPrice);
						orderLine.setUnitPrice(unitPrice);
						// calculatePrice(globalItemId,
						// unitOfMeasure,qtyOrderedInMeasure);
						// totalPrice += orderLine.getQuantity() * unitPrice;

					} else {
						msgBuffer.append("Invalid stock level:<br>");

						orderLine.setStatus(Text.INVALID_STOCK_LEVEL);
					}
				} catch (InvalidUnitOfMeasureConfiguration e) {
					orderLine.setStatus(e.getExceptionMsg());
				} catch (InvalidOpeningStockBalanceException e) {
					orderLine.setStatus(e.getExceptionMsg());
				}
				orderLines.add(orderLine);
			}
			// order.setTotalPrice(totalPrice);
		}
		return order;
	}

	/*
	 * private boolean isStockAvailable(int globalItem, int unitOfMeasure, int
	 * qtyOrderedInUnit, int existingOrder) throws
	 * InvalidUnitOfMeasureConfiguration, InvalidOpeningStockBalanceException {
	 * 
	 * int currentBalance = 0; currentBalance = this.inventoryManagerImpl
	 * .getPointGlobalItemCurrentBalance(globalItem,
	 * this.userIdentity.getCurrentPointId
	 * (),this.userIdentity.getCurrentUnitId());
	 * 
	 * currentBalance += existingOrder; if (currentBalance >= qtyOrderedInUnit)
	 * { return true; } return false; }
	 */
	@Override
	public Order getOrderById(int id) throws RecordNotFoundException {
		return this.OrderDaoImpl.getOrderById(id);
	}

	@Override
	public void confirmOrder(Order order) throws InvalidOrderException {

		if (order != null) {

			Map<Integer, Integer> checkedQtyMap = new HashMap();
			int currQtyChecked = 0;
			int qtyOrderedInUnit = 0;
			int unitOfMeasure = 0;
			int globalItemId = 0;
			int qtyOrderedInMeasure = 0;
			boolean error = false;

			Set<OrderLine> orderLines = order.getOrderLines();
			Set<OrderLine> invalidOrderLines = new HashSet();
			if (orderLines != null) {
				for (OrderLine orderLine : orderLines) {

					unitOfMeasure = orderLine.getGlobalUnitofMeasure().getId();
					globalItemId = orderLine.getGlobalItem().getItemId();
					qtyOrderedInMeasure = orderLine.getQuantity();

					// if our order is initially valid , we need to revalidate
					// here else add order into invalid orders
					// to remove from orders to save in our database

					if (qtyOrderedInMeasure != 0
							&& orderLine.getUnitPrice() != 0) {
						try {

							/*
							 * qtyOrderedInUnit = this.unitOfMeasureConverter
							 * .convertUnitOfMeasureToUnitOptionA( globalItemId,
							 * unitOfMeasure, qtyOrderedInMeasure);
							 */
							qtyOrderedInUnit = this.unitOfMeasureConverter
									.convertmeasuretounits(qtyOrderedInMeasure,
											unitOfMeasure);

							if (checkedQtyMap.containsKey(globalItemId)) {
								currQtyChecked = checkedQtyMap
										.get(globalItemId);
								// increment qty ordered by qty checked to get
								// actual
								// qty ordered since we are doin
								// incremental processing
								checkedQtyMap.put(globalItemId,
										qtyOrderedInUnit += currQtyChecked);
							} else {
								checkedQtyMap.put(globalItemId,
										qtyOrderedInUnit);
							}
							// we then check stock availablity
							/*
							 * if (this.isStockAvailable(globalItemId,
							 * unitOfMeasure, qtyOrderedInUnit, 0)) { } else {
							 * orderLine.setStatus(Text.INVALID_STOCK_LEVEL); }
							 */

							if (!this.inventoryManagerImpl
									.isPointStockAvailble(globalItemId,
											unitOfMeasure, qtyOrderedInUnit,
											this.userIdentity
													.getCurrentPointId(),
											this.userIdentity
													.getCurrentUnitId())) {
								orderLine.setStatus(Text.INVALID_STOCK_LEVEL);
							}

						} catch (InvalidUnitOfMeasureConfiguration e) {
							if (orderLine.getQuantity() != 0) {
								error = true;
								orderLine.setStatus(e.getExceptionMsg());
							}

						} catch (InvalidOpeningStockBalanceException e) {
							if (orderLine.getQuantity() != 0) {
								error = true;
								orderLine.setStatus(e.getExceptionMsg());
							}
						}
						orderLine.setOrder(order);

					} else {
						invalidOrderLines.add(orderLine);
					}
				}
				if (error) {
					throw new InvalidOrderException("");
				} else {
					if (!invalidOrderLines.isEmpty()) {
						orderLines.removeAll(invalidOrderLines);
					}
					updateStockCurrentBalances(order);
					order.setCreatedBy(this.userIdentity.getUsername());
					order.setCreateDate(new Date());
					VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
					visitWorkflowPoint.setId(this.userIdentity
							.getCurrentPointId());
					order.setVisitWorkflowPoint(visitWorkflowPoint);
					order.setOrderId(this.codeGenerator.generateOrderCode());
					this.OrderDaoImpl.saveOrder(order);
				}
			}
		}

	}

	private void updateStockCurrentBalances(Order order) {
		// TODO Auto-generated method stub
		if (order != null) {
			Set<OrderLine> orderLines = order.getOrderLines();
			if (orderLines != null) {
				for (OrderLine orderLine : orderLines) {
					try {
						this.updateStockCurrentBalance(orderLine);
					} catch (InvalidUnitOfMeasureConfiguration e) {
					}
				}
			}
		}
	}

	private void updateStockCurrentBalance(OrderLine orderLine)
			throws InvalidUnitOfMeasureConfiguration {
		// update current balance b4 deletion
		int globalItem = 0;
		int currUnitOfMeasure = 0;
		GlobalItem g = orderLine.getGlobalItem();
		if (g != null) {
			globalItem = g.getItemId();
		}
		// GlobalUnitofMeasure u = orderLine.getGlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw u = orderLine.getGlobalUnitofMeasure();

		if (u != null) {
			currUnitOfMeasure = u.getId();
		}
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
		 * stockInLineItem.getQuantity(), AppConfig.UNIT_OF_MEASURE_UNIT);
		 */
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
		 * orderLine.getQuantity());
		 */
		int currQtyInUnits = this.unitOfMeasureConverter.convertmeasuretounits(
				orderLine.getQuantity(), currUnitOfMeasure);

		// update current balance b4 table: newQtyInUnits is new value
		// and old value is current value in store;
		PointStockCurrentBalance pointStockCurrentBalance;
		try {
			pointStockCurrentBalance = this.inventoryManagerImpl
					.getPointGlobalItemCurrentBalanceByStock(globalItem,
							this.userIdentity.getCurrentPointId(),
							this.userIdentity.getCurrentUnitId());
			this.inventoryManagerImpl.updatePointGlobalItemCurrentBalance(0,
					currQtyInUnits, pointStockCurrentBalance);

		} catch (InvalidStockLevelException e) {
		}
	}

}
