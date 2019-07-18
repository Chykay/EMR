package org.calminfotech.inventory.dao;

import java.util.Date;
import java.util.List;

import org.calminfotech.inventory.daoInterface.StockInDaoInterface;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;

@Repository
@Transactional
public class StockInDaoImpl implements StockInDaoInterface {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveStockInBatch(StockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().save(stockInBatch);

	}

	@Override
	public void editStockInBatch(StockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().update(stockInBatch);

	}

	@Override
	public void editPointStockInBatch(PointStockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().update(stockInBatch);

	}

	@Override
	public StockIn getStockInBatchDetailsView(String batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public List<StockIn> getStockInBatchesList(DateSearchForm stockInSearchForm) {
		// TODO Auto-generated method stub

		Date date = DateUtils.formatStringToDate(stockInSearchForm
				.getDateOfRequest());

		List<StockIn> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockIn where unit.categoryId = ? and supplyDate = ? and organisation.Id=?   order by id desc")
				.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, date)
				.setParameter(2, userIdentity.getOrganisation().getId()).list();
		if (!list.isEmpty())
			return list;

		return null;

		/*
		 * Query qry = this.sessionFactory .getCurrentSession() .createQuery(
		 * "select b.id,b.batchId,b.supplyDate,v.vendorName  from StockIn b,Vendor v"
		 * + " where b.vendor=v.vendorId  order by  b.id desc"); List<Object[]>
		 * list = (List<Object[]>) qry.list(); if (list != null && list.size() >
		 * 0) { retList = new ArrayList<>(); for (Object[] obj : list) { stockIn
		 * = new StockIn(); stockIn.setId((Integer) obj[0]);
		 * stockIn.setBatchId((String) obj[1]); stockIn.setSupplyDate((String)
		 * obj[2]); stockIn.setVendor((String) obj[3]); retList.add(stockIn); }
		 * }
		 */

	}

	@Override
	public List<StockIn> getStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) {
		// TODO Auto-generated method stub

		Date date = DateUtils.formatStringToDate(stockInSearchForm
				.getDateOfRequest());

		// List<StockIn> list =
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockIn where unit.categoryId = ?  and organisation.Id=?   order by id desc");

		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())

		.setParameter(1, userIdentity.getOrganisation().getId())
				.setMaxResults(100);
		List<StockIn> list = query.list();
		if (!list.isEmpty())
			return list;

		return null;

		/*
		 * Query qry = this.sessionFactory .getCurrentSession() .createQuery(
		 * "select b.id,b.batchId,b.supplyDate,v.vendorName  from StockIn b,Vendor v"
		 * + " where b.vendor=v.vendorId  order by  b.id desc"); List<Object[]>
		 * list = (List<Object[]>) qry.list(); if (list != null && list.size() >
		 * 0) { retList = new ArrayList<>(); for (Object[] obj : list) { stockIn
		 * = new StockIn(); stockIn.setId((Integer) obj[0]);
		 * stockIn.setBatchId((String) obj[1]); stockIn.setSupplyDate((String)
		 * obj[2]); stockIn.setVendor((String) obj[3]); retList.add(stockIn); }
		 * }
		 */

	}

	@Override
	public List<PointStockIn> getPointStockInBatchesList(
			DateSearchForm stockInSearchForm) {
		// TODO Auto-generated method stub

		Date date = DateUtils.formatStringToDate(stockInSearchForm
				.getDateOfRequest());

		List<PointStockIn> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PointStockIn where unit.categoryId = ? and stockInDate = ? and organisation.Id=?   order by id desc")
				.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, date)
				.setParameter(2, userIdentity.getOrganisation().getId()).list();

		if (!list.isEmpty())
			return list;

		return null;

		/*
		 * Query qry = this.sessionFactory .getCurrentSession() .createQuery(
		 * "select b.id,b.batchId,b.supplyDate,v.vendorName  from StockIn b,Vendor v"
		 * + " where b.vendor=v.vendorId  order by  b.id desc"); List<Object[]>
		 * list = (List<Object[]>) qry.list(); if (list != null && list.size() >
		 * 0) { retList = new ArrayList<>(); for (Object[] obj : list) { stockIn
		 * = new StockIn(); stockIn.setId((Integer) obj[0]);
		 * stockIn.setBatchId((String) obj[1]); stockIn.setSupplyDate((String)
		 * obj[2]); stockIn.setVendor((String) obj[3]); retList.add(stockIn); }
		 * }
		 */

	}

	@Override
	public List<PointStockIn> getPointStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) {
		// TODO Auto-generated method stub

		Date date = DateUtils.formatStringToDate(stockInSearchForm
				.getDateOfRequest());

		// List<PointStockIn> list =

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PointStockIn where unit.categoryId = ? and organisation.Id=?   order by id desc");

		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())

		.setParameter(1, userIdentity.getOrganisation().getId())
				.setMaxResults(100);
		List<PointStockIn> list = query.list();

		if (!list.isEmpty())
			return list;

		return null;

		/*
		 * Query qry = this.sessionFactory .getCurrentSession() .createQuery(
		 * "select b.id,b.batchId,b.supplyDate,v.vendorName  from StockIn b,Vendor v"
		 * + " where b.vendor=v.vendorId  order by  b.id desc"); List<Object[]>
		 * list = (List<Object[]>) qry.list(); if (list != null && list.size() >
		 * 0) { retList = new ArrayList<>(); for (Object[] obj : list) { stockIn
		 * = new StockIn(); stockIn.setId((Integer) obj[0]);
		 * stockIn.setBatchId((String) obj[1]); stockIn.setSupplyDate((String)
		 * obj[2]); stockIn.setVendor((String) obj[3]); retList.add(stockIn); }
		 * }
		 */

	}

	@Override
	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockIn WHERE id = ?").setParameter(0, id)
				.list();

		if (!list.isEmpty())
			return (StockIn) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void delete(StockIn stockIn) {
		System.out.print("Before InsideDeleting the stock in in ");
		this.sessionFactory.getCurrentSession().delete(stockIn);
		System.out.print("After InsideDeleting the stock in in ");

	}

	// line supply

	/*
	 * @Override public List<StockIn> getSupplyLines(String batchId) { // TODO
	 * Auto-generated method stub
	 * 
	 * 
	 * List<StockIn> list = this.sessionFactory.getCurrentSession()
	 * .createQuery("FROM StockIn order by id desc").list(); if (list.size() >
	 * 0) return list;
	 * 
	 * return null;
	 * 
	 * 
	 * }
	 */

	@Override
	public List<StockInLine> getStockInLineItems(int batchId) {
		// TODO Auto-generated method stub
		List<StockInLine> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockInLine where stockInBatch.id=? order by id desc")
				.setParameter(0, batchId).list();
		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void saveStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockInLineItem);

	}

	@Override
	public StockInLine saveStockInLineItem2(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockInLineItem);
		return stockInLineItem;

	}

	@Override
	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockInLine WHERE id = ?")
				.setParameter(0, id).list();

		if (!list.isEmpty())
			return (StockInLine) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void editStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(stockInLineItem);

	}

	@Override
	public void deleteStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockInLineItem);

	}

	// point stuff

	@Override
	public List<PointStockIn> getStockInBatchesListByPoint(int currentPointId) {
		List<PointStockIn> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointStockIn order by id desc").list();
		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void savePointStockInBatch(PointStockIn stockInBatch) {

		this.sessionFactory.getCurrentSession().save(stockInBatch);

	}

	@Override
	public PointStockIn getPointStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointStockIn WHERE id = ?")
				.setParameter(0, id).list();
		if (!list.isEmpty())
			return (PointStockIn) list.get(0);
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void deletePointStockIn(PointStockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().delete(stockInBatch);
	}

	@Override
	public void savePointStockInLineItem(PointStockInLine stockInLineItem) {
		this.sessionFactory.getCurrentSession().save(stockInLineItem);
	}

	@Override
	public PointStockInLine getPointStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointStockInLine WHERE id = ?")
				.setParameter(0, id).list();

		if (!list.isEmpty())
			return (PointStockInLine) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public void editPointStockInLineItem(PointStockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(stockInLineItem);

	}

	@Override
	public void deletePointStockInLineItem(PointStockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockInLineItem);

	}

	// stock opening balances

}
