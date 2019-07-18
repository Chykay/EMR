package org.calminfotech.inventory.dao;

import java.util.List;

import org.calminfotech.inventory.daoInterface.StockOpeningBalanceDaoInterface;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StockOpeningBalanceDaoImpl implements
		StockOpeningBalanceDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	// stock opening balances

	@Override
	public void saveStockOpeningBalance(StockOpeningBalance stockOpeningBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockOpeningBalance);
	}

	@Override
	public List<StockOpeningBalance> getStockOpeningBalances(Integer id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		/*
		 * List list = this.sessionFactory.getCurrentSession()
		 * .createCriteria(StockOpeningBalance.class)
		 * .addOrder(Order.desc("id")).list();
		 * 
		 * return list;
		 * 
		 * StockOpeningBalance stockOpeningBalance = null;
		 * List<StockOpeningBalance> retList = null; Query q =
		 * this.sessionFactory .getCurrentSession() .createQuery( "select s.id,"
		 * +
		 * " i.name,s.openingBalance,s.currentBalance,s.dateAdded  from GlobalItem i,StockOpeningBalance s"
		 * + " where i.id=s.globalItem order by s.id desc"); List<Object[]> list
		 * = (List<Object[]>) q.list(); if (list != null && list.size() > 0) {
		 * retList = new ArrayList<>(); for (Object[] obj : list) {
		 * 
		 * stockOpeningBalance = new StockOpeningBalance();
		 * stockOpeningBalance.setId((Integer) obj[0]);
		 * stockOpeningBalance.setGlobalItem((String) obj[1]);
		 * stockOpeningBalance.setOpeningBalance((Integer) obj[2]);
		 * stockOpeningBalance.setCurrentBalance((Integer) obj[3]);
		 * stockOpeningBalance.setDateAdded((String) obj[4]);
		 * retList.add(stockOpeningBalance); } }
		 */
		List<StockOpeningBalance> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockOpeningBalance where unit.categoryId=? and organisation_id = ? and globalItem.itemId =? and isDeleted=0 order by id desc")
				.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, userIdentity.getOrganisation().getId())

				.setParameter(2, id)

				.list();
		if (list.size() > 0)
			return list;

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockOpeningBalance WHERE id = ?")
				.setParameter(0, id).list();

		if (!list.isEmpty())
			return (StockOpeningBalance) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void updateStockOpeningBalance(
			StockOpeningBalance stockOpeningBalance) {

		this.sessionFactory.getCurrentSession().update(stockOpeningBalance);
		/*
		 * int openingBalance = stockOpeningBalance.getOpeningBalance();
		 * 
		 * Query query = this.sessionFactory .getCurrentSession() .createQuery(
		 * "update  StockOpeningBalance " +
		 * "set openingBalance =?,dateAdded=:dateAdded" + " where id = :id");
		 * query.setParameter("id", stockOpeningBalance.getId());
		 * query.setParameter("openingBalance", openingBalance);
		 * query.setParameter("dateAdded", stockOpeningBalance.getDateAdded());
		 * query.executeUpdate();
		 */
	}

	@Override
	public void delete(StockOpeningBalance stockOpeningBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockOpeningBalance);
	}

	@Override
	public boolean isExistStockOpeningBalanceGlobalItem(String globalItemID) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select id FROM StockOpeningBalance WHERE global_item_id = ? and unit.categoryId=?  and organisation.Id=?")
				.setParameter(0, globalItemID)
				.setParameter(1, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(2, userIdentity.getOrganisation().getId())

				.list();

		if (list.size() > 0)
			return true;

		return false;
	}

	@Override
	public boolean isExistStockOpeningBalanceForGlobalItem(int globalItemID,
			int unitofMeasure) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select id FROM StockOpeningBalance WHERE global_item_id = ? and unit_of_measure_id=? and unit.categoryId=? and organisation.Id=? and isDeleted=0");
		query.setParameter(0, globalItemID);
		query.setParameter(1, unitofMeasure);
		query.setParameter(2, userIdentity.getCurrentUnit().getCategoryId());

		query.setParameter(3, userIdentity.getOrganisation().getId());

		List list = query.list();
		if (list.size() > 0)
			return true;

		return false;
	}

	// point stuff

	@Override
	public void savePointStockOpeningBalance(
			PointStockOpeningBalance stockOpeningBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockOpeningBalance);
	}

	/*
	 * @Override public List<PointStockOpeningBalance>
	 * getPointStockOpeningBalances(int pointId) throws RecordNotFoundException
	 * {
	 * 
	 * List<PointStockOpeningBalance> list = this.sessionFactory
	 * .getCurrentSession()
	 * .createQuery("FROM PointStockOpeningBalance order by id desc") .list();
	 * if (list.size() > 0) return list;
	 * 
	 * throw new RecordNotFoundException(Text.RECORD_NOT_FOUND); }
	 */

	@Override
	public List<PointStockOpeningBalance> getPointStockOpeningBalances(
			int pointId, int unitId) throws RecordNotFoundException {

		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM PointStockOpeningBalance WHERE  point_id=?"
						+ " and unit_id=?  order by id desc");
		query.setParameter(0, pointId);
		query.setParameter(1, unitId);

		List list = query.list();
		if (list.size() > 0)
			return list;
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public PointStockOpeningBalance getPointStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointStockOpeningBalance WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PointStockOpeningBalance) list.get(0);
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void updatePointStockOpeningBalance(
			PointStockOpeningBalance stockOpeningBalance) {

		this.sessionFactory.getCurrentSession().update(stockOpeningBalance);

		/*
		 * int openingBalance = stockOpeningBalance.getOpeningBalance();
		 * 
		 * Query query = this.sessionFactory .getCurrentSession() .createQuery(
		 * "update  StockOpeningBalance " +
		 * "set openingBalance =?,dateAdded=:dateAdded" + " where id = :id");
		 * query.setParameter("id", stockOpeningBalance.getId());
		 * query.setParameter("openingBalance", openingBalance);
		 * query.setParameter("dateAdded", stockOpeningBalance.getDateAdded());
		 * query.executeUpdate();
		 */

	}

	@Override
	public void delete(PointStockOpeningBalance stockOpeningBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockOpeningBalance);
	}

	/*
	 * @Override public boolean isExistStockOpeningBalanceForPointGlobalItem(int
	 * globalItemID, int unitofMeasure,int currentPointId) { // TODO
	 * Auto-generated method stub Query query = this.sessionFactory
	 * .getCurrentSession() .createQuery(
	 * "select id FROM PointStockOpeningBalance WHERE global_item_id = ? and unit_of_measure_id=? and point_id=?"
	 * ); query.setParameter(0, globalItemID); query.setParameter(1,
	 * unitofMeasure); query.setParameter(2, currentPointId);
	 * 
	 * List list = query.list(); if (list.size() > 0) return true;
	 * 
	 * return false; }
	 */
	@Override
	public boolean isExistStockOpeningBalanceForPointGlobalItem(
			int globalItemId, int unitofMeasure, Integer currentPointId,
			int currentUnitId) {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select id FROM PointStockOpeningBalance WHERE global_item_id = ? and unit_of_measure_id=? and point_id=?"
								+ " and unit_id=?");
		query.setParameter(0, globalItemId);
		query.setParameter(1, unitofMeasure);
		query.setParameter(2, currentPointId);
		query.setParameter(3, currentUnitId);

		List list = query.list();
		if (list.size() > 0)
			return true;

		return false;
	}

}
