package org.calminfotech.inventory.serviceInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.InvalidOrderException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.PointRequest;

public interface OrderManagerInterface {

	public List<PointRequest> getOrders(DateSearchForm orderSearchForm);

	public Order saveOrder(OrderForm orderForm)throws InvalidStockLevelException,
	InvalidUnitOfMeasureConfiguration;

	public Order getOrderById(int id)throws RecordNotFoundException;
	
	public Order processOrder(OrderForm orderForm);

	public void confirmOrder(Order order) throws InvalidOrderException;


}
