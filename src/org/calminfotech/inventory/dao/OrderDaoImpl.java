package org.calminfotech.inventory.dao;

import java.util.List;

import org.calminfotech.inventory.daoInterface.InventoryDaoInterface;
import org.calminfotech.inventory.daoInterface.OrderDaoInterface;
import org.calminfotech.inventory.daoInterface.StockInDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockIn;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(order);	
	
	}

	@Override
	public Order getOrderById(int id) throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Order WHERE id = ?").setParameter(0, id)
				.list();

		if (!list.isEmpty())
			return (Order) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	

}
