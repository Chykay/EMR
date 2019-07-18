package org.calminfotech.inventory.dao;

import java.util.List;

import org.calminfotech.inventory.daoInterface.InventoryDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import org.calminfotech.inventory.models.PointStockCurrentBalance;

@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemBo itemBo;

	@Override
	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId) {

		/*
		 * List list = this.sessionFactory.getCurrentSession()
		 * .createQuery("FROM GlobalItem WHERE id = ? and organisation_id=?")
		 * 
		 * .setParameter(0, globalItemId)
		 * .setParameter(1,userIdentity.getOrganisation().getId()) .list();
		 * 
		 * if (!list.isEmpty()){ return ((GlobalItem)
		 * list.get(0)).getMeasurement(); }
		 * 
		 * return null;
		 */
		/*
		 * GlobalUnitofMeasure globalUnitofMeasure = null;
		 * List<GlobalUnitofMeasure> retList = null; Query qry =
		 * this.sessionFactory .getCurrentSession() .createQuery(
		 * "select iu.unit_of_measure,u.unit_of_measure  from GlobalUnitofMeasure u,GlobalUnitofMeasure iu,GlobalItem g"
		 * + " where g.id =:item and u.unit=iu.unit_of_measure");
		 * qry.setParameter("item", 3);
		 * 
		 * List<Object[]> list = (List<Object[]>) qry.list(); if (list != null
		 * && list.size() > 0) { retList = new ArrayList<>(); for (Object[] obj
		 * : list) { globalUnitofMeasure = new GlobalUnitofMeasure();
		 * globalUnitofMeasure.setUnit((String) obj[0]);
		 * globalUnitofMeasure.setUnit_of_measure((String) obj[1]);
		 * retList.add(globalUnitofMeasure); } }
		 */
		return null;
	}

	/*
	 * @Override public GlobalItemUnitofMeasure getUnitOfMeasureToDetails(int
	 * globalItemId, int currentUnitOfMeasureFrom) {
	 * 
	 * Query query = this.sessionFactory .getCurrentSession() .createQuery(
	 * "FROM GlobalItemUnitofMeasure WHERE globalItemId = ? and  unitOfMeasureFrom=?"
	 * ); query.setParameter(0, globalItemId); query.setParameter(1,
	 * currentUnitOfMeasureFrom);
	 * 
	 * List list = query.list();
	 * 
	 * if (!list.isEmpty()) return (GlobalItemUnitofMeasure) list.get(0);
	 * 
	 * return null; }
	 * 
	 * @Override public HrunitCategoryItem getUnitOfMeasureToDetails(int
	 * globalItemId, int currentUnitOfMeasureFrom) {
	 * 
	 * Query query = this.sessionFactory .getCurrentSession() .createQuery(
	 * "FROM UnitItem WHERE globalitem_item_id = ? and  unit_of_measure_id=?");
	 * query.setParameter(0, globalItemId); query.setParameter(1,
	 * currentUnitOfMeasureFrom);
	 * 
	 * List list = query.list();
	 * 
	 * if (!list.isEmpty()) return (UnitItem) list.get(0);
	 * 
	 * return null; }
	 */
	@Override
	public int getGlobalItemCurrentBalance(int globalItemId)
			throws InvalidStockLevelException {
		// TODO Auto-generated method stub

		List<Object[]> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select currentBalance FROM StockCurrentBalance WHERE globalItem.itemId = ? and unit.categoryId=? and organisation_id=?")
				.setParameter(0, globalItemId)
				.setParameter(1, userIdentity.getCurrentUnit().getCategoryId())

				.setParameter(2, userIdentity.getOrganisation().getId()).list();

		if (list != null && !list.isEmpty()) {

			return Integer.parseInt(String.valueOf(list.get(0)));

		} else {

			System.out.print("there is wahala here 0");
			return 0;
			// throw new
			// InvalidOpeningStockBalanceException("Unable to create or get stock opening balance for this item");

		}

	}

	/**/
	@Override
	public void updateGlobalItemCurrentBalance(int currentBalance,
			int globalItemId) {
		// TODO Auto-generated method stub

		// Query query = this.sessionFactory.getCurrentSession().createQuery(
		// "update  GlobalItem " + "set currentBalance =? " + " where id = ?");
		// query.setParameter(0, currentBalance);
		// query.setParameter(1, globalItemId);
		// query.executeUpdate();

	}

	@Override
	public GlobalItemUnitofMeasureVw getUnitOfMeasureFromDetailsRwId(
			int unitOfMeasureid) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM GlobalItemUnitofMeasureVw WHERE id = ?  ");
		query.setParameter(0, unitOfMeasureid);

		List list = query.list();

		if (!list.isEmpty())
			return (GlobalItemUnitofMeasureVw) list.get(0);

		return null;
	}

	/*
	 * @Override public HrunitCategoryItem getUnitOfMeasureFromDetailsRwId(int
	 * unitOfMeasureid) { Query query = this.sessionFactory .getCurrentSession()
	 * .createQuery( "FROM UnitItem WHERE id = ?  ");
	 * query.setParameter(0,unitOfMeasureid);
	 * 
	 * List list = query.list();
	 * 
	 * if (!list.isEmpty()) return (UnitItem) list.get(0);
	 * 
	 * return null; }
	 */

	/*
	 * @Override public HrunitCategoryItem getUnitOfMeasureFromDetails( int
	 * globalItemId, int unitOfMeasure) { Query query = this.sessionFactory
	 * .getCurrentSession() .createQuery(
	 * "FROM UnitItem WHERE globalitem_item_id = ? and  unit_of_measure_id = ? "
	 * ); query.setParameter(0, globalItemId); query.setParameter(1,
	 * unitOfMeasure);
	 * 
	 * List list = query.list();
	 * 
	 * if (!list.isEmpty()) return (UnitItem) list.get(0);
	 * 
	 * return null; }
	 */
	/*
	 * @Override public GlobalItemUnitofMeasure getUnitOfMeasureFromDetails( int
	 * globalItemId, int unitOfMeasure) { Query query = this.sessionFactory
	 * .getCurrentSession() .createQuery(
	 * "FROM GlobalItemUnitofMeasure WHERE globalItemId = ? and  unitOfMeasureTo=?"
	 * ); query.setParameter(0, globalItemId); query.setParameter(1,
	 * unitOfMeasure);
	 * 
	 * List list = query.list();
	 * 
	 * if (!list.isEmpty()) return (GlobalItemUnitofMeasure) list.get(0);
	 * 
	 * return null; }
	 */

	@Override
	public int getGlobalItemUnitOfMeasureContainedUnit(int globalItemId,
			int unitOfMeasure) {

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				"select contdValue From UnitItem "
						+ "where itemId=? and unitId=?");

		qry.setParameter(0, globalItemId);
		qry.setParameter(1, unitOfMeasure);

		List list = qry.list();
		if (list != null && !list.isEmpty() && list.get(0) instanceof Integer) {
			return (Integer) list.get(0);
		}
		return 0;
	}

	/*
	 * @Override public int getGlobalItemUnitOfMeasureContainedUnit(int
	 * globalItemId, int unitOfMeasure) {
	 * 
	 * Query qry = this.sessionFactory.getCurrentSession().createQuery(
	 * "select containedValue From GlobalItemUnitofMeasure " +
	 * "where globalItemId=? and unitOfMeasureFrom=?");
	 * 
	 * qry.setParameter(0, globalItemId); qry.setParameter(1, unitOfMeasure);
	 * 
	 * List list = qry.list(); if (list != null && !list.isEmpty() &&
	 * list.get(0) instanceof Integer) { return (Integer) list.get(0); } return
	 * 0; }
	 */

	@Override
	public void updatePointGlobalItemCurrentBalance(
			PointStockCurrentBalance pointStockCurrentBalance) {
		// this.sessionFactory.getCurrentSession().lock(arg0, arg1);
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				pointStockCurrentBalance);
		// this.sessionFactory.getCurrentSession().lock(arg0, arg1);

	}

	/*
	 * @Override public PointStockCurrentBalance
	 * getGlobalItemCurrentBalanceByPoint( int globalItem, int pointId) throws
	 * InvalidOpeningStockBalanceException { Query query = this.sessionFactory
	 * .getCurrentSession() .createQuery(
	 * "FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?"
	 * ); query.setParameter(0,globalItem); query.setParameter(1,pointId); List
	 * list = query.list(); if (!list.isEmpty()) return
	 * (PointStockCurrentBalance) list.get(0);
	 * 
	 * throw new InvalidOpeningStockBalanceException(
	 * "Invalid stock opening balance for this item");
	 * 
	 * }
	 */

	@Override
	public PointStockCurrentBalance getPointGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException {
		// try
		// {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?"
						+ " and unit_id=? and organisation.Id=?");
		query.setParameter(0, globalItem);
		query.setParameter(1, pointId);
		query.setParameter(2, unitId);
		query.setParameter(3, userIdentity.getOrganisation().getId());
		List list = query.list();
		if (!list.isEmpty()) {
			return (PointStockCurrentBalance) list.get(0);
		}

		else {
			/*
			 * PointStockCurrentBalance pscb = new PointStockCurrentBalance();
			 * pscb.setGlobalItem(this.itemBo.getGlobalItemById(globalItem));
			 * pscb.setCurrentBalance(0); pscb.setCreateDate(new
			 * Date(System.currentTimeMillis()));
			 * pscb.setCreatedBy(userIdentity.getUsername());
			 * pscb.setDeleted(false);
			 * pscb.setOrganisation(userIdentity.getOrganisation().getId());
			 * pscb.setUnit(userIdentity.getCurrentUnit());
			 * pscb.setVisitWorkflowPoint
			 * (userIdentity.getCurrentUnit().getPoint());
			 * 
			 * this.sessionFactory.getCurrentSession().save(pscb);
			 * 
			 * return pscb;
			 * 
			 * 
			 * }
			 * 
			 * } catch(Exception e)
			 * 
			 * {
			 */
			System.out.print("there is wahala here 0");

			throw new InvalidStockLevelException(
					"Stock current balance object does not exist!!");
			// return null;

		}

	}

	/*
	 * @Override public List<PointStockCurrentBalance>
	 * getPointStockCurrentBalances( int currentPointId) {
	 * List<PointStockCurrentBalance> list = this.sessionFactory
	 * .getCurrentSession() .createQuery(
	 * "FROM PointStockCurrentBalance where point_id=? order by visitWorkflowPoint.name"
	 * ) .setParameter(0,currentPointId).list(); if (!list.isEmpty()) return
	 * list;
	 * 
	 * return null; }
	 */

	@Override
	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int pointId, int unitId) {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PointStockCurrentBalance WHERE  point_id=?"
								+ " and unit.id=? and  organisation_id=? order by visitWorkflowPoint.name");
		query.setParameter(0, pointId);
		query.setParameter(1, unitId);
		query.setParameter(2, userIdentity.getOrganisation().getId());

		List list = query.list();
		if (!list.isEmpty())
			return list;
		return null;
	}

	/*
	 * @Override public int getPointGlobalItemCurrentBalance(int globalItem, int
	 * currentPointId) throws InvalidOpeningStockBalanceException {
	 * 
	 * Query query= this.sessionFactory.getCurrentSession() .createQuery(
	 * "select currentBalance FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?"
	 * );
	 * 
	 * query.setParameter(0, globalItem); query.setParameter(1, currentPointId);
	 * 
	 * 
	 * List<Object[]> list =query.list();
	 * 
	 * if (list != null && !list.isEmpty()) { try { return
	 * Integer.parseInt(String.valueOf(list.get(0))); } catch
	 * (NumberFormatException e) { } } throw new
	 * InvalidOpeningStockBalanceException
	 * ("Invalid stock opening balance for this item");
	 * 
	 * }
	 */

	@Override
	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId, int currentUnitId)
			throws InvalidStockLevelException {

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select currentBalance FROM PointStockCurrentBalance WHERE global_item_id = ? and  point_id=?"
								+ " and unit_id=? and  organisation_id=? ");

		query.setParameter(0, globalItem);
		query.setParameter(1, currentPointId);
		query.setParameter(2, currentUnitId);
		query.setParameter(3, userIdentity.getOrganisation().getId());
		List<Object[]> list = query.list();

		if (list != null && !list.isEmpty()) {

			return Integer.parseInt(String.valueOf(list.get(0)));

		} else {

			System.out.print("there is wahala here 0");

			// throw new
			// InvalidOpeningStockBalanceException("Unable to create or get stock opening balance for this item");
			return 0;

		}
	}

	@Override
	public void updateGlobalItemCurrentBalance(
			StockCurrentBalance stockCurrentBalance) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				stockCurrentBalance);

	}

	@Override
	public StockCurrentBalance getGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException {
		// try
		// {
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM StockCurrentBalance WHERE global_item_id = ? and  point_id=?"
						+ " and unit_id=? and organisation.Id=? ");
		query.setParameter(0, globalItem);
		query.setParameter(1, pointId);
		query.setParameter(2, unitId);
		query.setParameter(3, userIdentity.getOrganisation().getId());

		// LockOptions lockOptions = new LockOptions(LockMode.PESSIMISTIC_READ);

		// query.setLockOptions(lockOptions);

		System.out.print("YES AFTER LOCK");

		Query query2 = this.sessionFactory.getCurrentSession().createQuery(
				"FROM StockCurrentBalance WHERE global_item_id = ? and  point_id=?"
						+ " and unit_id=? and organisation.Id=? ");
		query2.setParameter(0, globalItem);
		query2.setParameter(1, pointId);
		query2.setParameter(2, unitId);
		query2.setParameter(3, userIdentity.getOrganisation().getId());
		List list2 = query2.list();

		System.out.print("YES REQUERY");

		List list = query.list();
		if (!list.isEmpty()) {
			return (StockCurrentBalance) list.get(0);
		}

		else {

			/*
			 * StockCurrentBalance pscb = new StockCurrentBalance();
			 * pscb.setGlobalItem(this.itemBo.getGlobalItemById(globalItem));
			 * pscb.setCurrentBalance(0); pscb.setCreateDate(new
			 * Date(System.currentTimeMillis()));
			 * pscb.setCreatedBy(userIdentity.getUsername());
			 * pscb.setDeleted(false);
			 * pscb.setOrganisation(userIdentity.getOrganisation().getId());
			 * pscb.setUnit(userIdentity.getCurrentUnit());
			 * pscb.setVisitWorkflowPoint
			 * (userIdentity.getCurrentUnit().getPoint());
			 * 
			 * this.sessionFactory.getCurrentSession().save(pscb);
			 * 
			 * return pscb;
			 * 
			 * 
			 * }
			 * 
			 * } catch(Exception e)
			 * 
			 * {
			 */
			// System.out.print("there is wahala here 0");

			throw new InvalidStockLevelException(
					"Stock current balance object does not exist!!");

			// return null;
		}

	}

	@Override
	public List<StockCurrentBalance> getStockCurrentBalances(
			int currentPointId, int currentUnitId) {
		// TODO Auto-generated method stub

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockCurrentBalance WHERE  unit_id=? and point_id=? and organisation_id=? ");

		query.setParameter(0, this.userIdentity.getCurrentUnitId());
		query.setParameter(1, this.userIdentity.getCurrentPointId());

		query.setParameter(2, userIdentity.getOrganisation().getId());

		List list = query.list();

		return list;
	}

	@Override
	public List<StockCurrentBalance> getAllStockCurrentBalances() {
		// TODO Auto-generated method stub

		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"FROM StockCurrentBalance ");

		List list = query.list();
		return list;
	}

	@Override
	public List<StockCurrentBalance> getAllStockCurrentBalancesByStock(
			int Globalitem) {
		// TODO Auto-generated method stub

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockCurrentBalance  where global_item_id = ?")
				.setParameter(0, Globalitem);

		List list = query.list();
		return list;
	}

	@Override
	public void save(StockCurrentBalance stockCurrentBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				stockCurrentBalance);
	}

	@Override
	public void save(PointStockCurrentBalance stockCurrentBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(
				stockCurrentBalance);
	}

	@Override
	public int getGlobalItemCurrentBalance(int globalItem, int currentPointId,
			int currentUnitId) {

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select currentBalance FROM StockCurrentBalance WHERE global_item_id = ? and  point_id=?"
								+ " and unit_id=? and  organisation_id=? ");

		query.setParameter(0, globalItem);
		query.setParameter(1, currentPointId);
		query.setParameter(2, currentUnitId);
		query.setParameter(3, userIdentity.getOrganisation().getId());
		List<Object[]> list = query.list();

		if (list != null && !list.isEmpty()) {

			return Integer.parseInt(String.valueOf(list.get(0)));

		} else {

			System.out.print("there is wahala here 0");

			// throw new
			// InvalidOpeningStockBalanceException("Unable to create or get stock opening balance for this item");
			return 0;

		}

	}

	/*
	 * @Override public List<UnitItem> getGlobalItemUnitOfMeasurements( int
	 * globalItemId) { Query query = this.sessionFactory .getCurrentSession()
	 * .createQuery( "FROM UnitItem WHERE  globalitem_item_id=?" +
	 * "  and is_active='1' order by contained_value desc");
	 * query.setParameter(0,globalItemId); List list =query.list(); if
	 * (!list.isEmpty()) return list; return null; }
	 */

}
