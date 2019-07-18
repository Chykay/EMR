package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;

public interface OrderDaoInterface {

	void saveOrder(Order order);

	Order getOrderById(int id) throws RecordNotFoundException;


}
