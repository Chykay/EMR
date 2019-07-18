package org.calminfotech.inventory.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.calminfotech.inventory.daoInterface.PointRequestDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.models.Storeissue_log;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PointRequestDaoImpl implements PointRequestDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Override
	public void savePointRequest(PointRequest pointRequest) {

		this.sessionFactory.getCurrentSession().save(pointRequest);

	}

	@Override
	public void updatePointRequest(PointRequest pointRequest) {
		this.sessionFactory.getCurrentSession().update(pointRequest);
	}

	@Override
	public PointRequest getPointRequestById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointRequest WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PointRequest) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public Storeissue_log getStoreIssueById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Storeissue_log WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (Storeissue_log) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public List<PointRequest> getPointRequestsMake(String dateOfRequest,
			String searchCriteria) {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		System.out.print("the date " + date);

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from  PointRequest  where unit.categoryId = ? and requestDate = ? and organisation.Id=?  ");
		// unit.categoryId = ? and

		// query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())

		// query.setParameter(0,
		// userIdentity.getStaffregistration().getHrunitcategory().getCategoryId())
		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, DateUtils.formatStringToDate(dateOfRequest))
				.setParameter(2, userIdentity.getOrganisation().getId());

		List list = query.list();
		if (list.size() > 0) {
			return list;
		}

		return null;

		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * .createCriteria(PointRequest.class);
		 * 
		 * if (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * //criteria.add(Restrictions.eq("requestDate", dateOfRequest)); } if
		 * (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * criteria.add(Restrictions.eq("requestStatus", searchCriteria)); }
		 * List list = criteria.list(); return list;
		 */

	}

	@Override
	public List<PointRequest> getPointRequests(String dateOfRequest,
			String searchCriteria) {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		System.out.print("the date " + date);

		/*
		 * StringBuilder qryBuffer = new StringBuilder(); List paramList = new
		 * ArrayList(); qryBuffer.append("FROM PointRequest "); // if
		 * (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * qryBuffer.append(" WHERE   requestDate=? "); paramList.add(date); //
		 * }
		 * 
		 * if (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * qryBuffer.append(" and requestStatus=? ");
		 * paramList.add(searchCriteria); }
		 * 
		 * qryBuffer.append(" order by id ");
		 * 
		 * Query query = this.sessionFactory.getCurrentSession().createQuery(
		 * qryBuffer.toString());
		 * 
		 * if (paramList != null && !paramList.isEmpty()) { int indx = 0; for
		 * (Object param : paramList) { query.setParameter(indx, param); indx++;
		 * } }
		 */

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from  PointRequest  where unitto.categoryId = ? and requestDate = ? and organisation.Id=?  ");

		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, DateUtils.formatStringToDate(dateOfRequest))
				.setParameter(2, userIdentity.getOrganisation().getId());

		List list = query.list();
		if (list.size() > 0) {
			return list;
		}

		return null;

		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * .createCriteria(PointRequest.class);
		 * 
		 * if (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * //criteria.add(Restrictions.eq("requestDate", dateOfRequest)); } if
		 * (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * criteria.add(Restrictions.eq("requestStatus", searchCriteria)); }
		 * List list = criteria.list(); return list;
		 */

	}

	@Override
	public List<PointRequest> getPointRequestsMakeTop100(String dateOfRequest,
			String searchCriteria) {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		System.out.print("the date " + date);

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from  PointRequest  where unit.categoryId = ?  and organisation.Id=? order by id desc ");
		// unit.categoryId = ? and

		// query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())

		// query.setParameter(0,
		// userIdentity.getStaffregistration().getHrunitcategory().getCategoryId())
		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())

		.setParameter(1, userIdentity.getOrganisation().getId());
		query.setMaxResults(100);

		List list = query.list();
		if (list.size() > 0) {
			return list;
		}

		return null;

		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * .createCriteria(PointRequest.class);
		 * 
		 * if (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * //criteria.add(Restrictions.eq("requestDate", dateOfRequest)); } if
		 * (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * criteria.add(Restrictions.eq("requestStatus", searchCriteria)); }
		 * List list = criteria.list(); return list;
		 */

	}

	@Override
	public List<PointRequest> getPointRequestsTop100(String dateOfRequest,
			String searchCriteria) {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		System.out.print("the date " + date);

		/*
		 * StringBuilder qryBuffer = new StringBuilder(); List paramList = new
		 * ArrayList(); qryBuffer.append("FROM PointRequest "); // if
		 * (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * qryBuffer.append(" WHERE   requestDate=? "); paramList.add(date); //
		 * }
		 * 
		 * if (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * qryBuffer.append(" and requestStatus=? ");
		 * paramList.add(searchCriteria); }
		 * 
		 * qryBuffer.append(" order by id ");
		 * 
		 * Query query = this.sessionFactory.getCurrentSession().createQuery(
		 * qryBuffer.toString());
		 * 
		 * if (paramList != null && !paramList.isEmpty()) { int indx = 0; for
		 * (Object param : paramList) { query.setParameter(indx, param); indx++;
		 * } }
		 */

		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from  PointRequest  where unitto.categoryId = ?  and organisation.Id=? order by id desc ");

		query.setParameter(0, userIdentity.getCurrentUnit().getCategoryId())
				.setParameter(1, userIdentity.getOrganisation().getId());
		query.setMaxResults(100);
		List list = query.list();
		if (list.size() > 0) {
			return list;
		}

		return null;

		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * .createCriteria(PointRequest.class);
		 * 
		 * if (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		 * //criteria.add(Restrictions.eq("requestDate", dateOfRequest)); } if
		 * (searchCriteria != null && !searchCriteria.isEmpty()) {
		 * criteria.add(Restrictions.eq("requestStatus", searchCriteria)); }
		 * List list = criteria.list(); return list;
		 */

	}

	@Override
	public PointRequestLine getPointRequestLineById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointRequestLine WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PointRequestLine) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public int getTotalGlobalItemRequestApproved(int globalItem)
			throws InvalidUnitOfMeasureConfiguration {

		Query qry = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select globalUnitofMeasure.id,quantityApproved From PointRequestLine  where global_item_id=? and pointRequest.unitto.categoryId=? and organisation.Id=? "
								+ " and requestStatus=? and isDeleted=0");
		qry.setParameter(0, globalItem);

		qry.setParameter(1, userIdentity.getCurrentUnit().getCategoryId());

		qry.setParameter(2, userIdentity.getOrganisation().getId());
		qry.setParameter(3, PointRequestStatus.Approved.getCode());

		int totalQtyApproved = 0;
		// List<PointRequestLine> list = qry.list();
		List<Object[]> list = qry.list();

		if (list != null && list.size() > 0) {
			for (Object[] aRow : list) {
				// for (PointRequestLine line : list) {
				// convert current request, convert to unit and update total qty
				/*
				 * totalQtyApproved += this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(globalItem, (Integer)
				 * aRow[0], (Integer) aRow[1]);
				 */

				totalQtyApproved += this.unitOfMeasureConverter
						.convertmeasuretounits((Integer) aRow[1],
								(Integer) aRow[0]);

				/*
				 * totalQtyApproved += this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(globalItem, (Integer)
				 * aRow[0], (Integer) aRow[1], AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * totalQtyApproved += this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnit(globalItem, line
				 * .getGlobalUnitofMeasure().getId(), line
				 * .getQuantityApproved(), AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
			}
		}
		return totalQtyApproved;
	}

	@Override
	public void updatePointRequestLine(PointRequestLine pointRequestLine) {
		this.sessionFactory.getCurrentSession().update(pointRequestLine);
	}

	@Override
	public List<PointRequest> getPointRequestsByPoint(String dateOfRequest,
			int currentPointId, int unitId) throws RecordNotFoundException {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		Query qry = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select id,requestStatus,requestBatchId,requestDate From PointRequest "
								+ "where visitWorkflowPoint.id=? and unitto.categoryId =? and requestDate=?  and organisation_id=?");

		qry.setParameter(0, 12);
		qry.setParameter(1, userIdentity.getCurrentUnit().getCategoryId());
		qry.setParameter(2, date);
		qry.setParameter(3, userIdentity.getOrganisation().getId());
		List<Object[]> list = qry.list();
		if (list != null && list.size() > 0) {

			PointRequest pointRequest = null;
			List<PointRequest> retList = new ArrayList();
			for (Object[] aRow : list) {
				pointRequest = new PointRequest();
				pointRequest.setId((Integer) aRow[0]);
				pointRequest.setRequestStatus((Character) aRow[1]);
				pointRequest.setRequestBatchId((String) aRow[2]);
				pointRequest.setRequestDate((Date) aRow[3]);
				retList.add(pointRequest);
			}
			return retList;
		}
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public Collection<Character> getPntRequestLineRequestsStatuses(
			int pointRequestLineId, int pointRequestId) {

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				"select requestStatus From PointRequestLine "
						+ "where pointRequest.id=? and id!=?");

		qry.setParameter(0, pointRequestId);
		qry.setParameter(1, pointRequestLineId);

		List<Character> list = qry.list();
		return list;
	}

	@Override
	public void deletePointRequest(PointRequest pointRequest) {
		this.sessionFactory.getCurrentSession().delete(pointRequest);
	}

	@Override
	public void deletePointRequestLine(PointRequestLine pointRequestLine) {
		this.sessionFactory.getCurrentSession().delete(pointRequestLine);

	}

	/*
	 * @Override public void approvePointLineRequest(int reqLineId, int
	 * qtyToApprove) {
	 * 
	 * Query query = this.sessionFactory.getCurrentSession().createQuery(
	 * "update  PointRequestLine " + "set quantityApproved =?,requestStatus=?" +
	 * " where id =?"); query.setParameter(0, qtyToApprove);
	 * query.setParameter(1
	 * ,String.valueOf(PointRequestStatus.Approved.getCode()) );
	 * query.setParameter(2, reqLineId); query.executeUpdate(); }
	 */

	@Override
	public void save(Storeissue_log storeissuelog) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(storeissuelog);

	}

	@Override
	public void issuePointRequestLine(PointRequestLine pointRequestLine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Storeissue_log storeissuelog) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(storeissuelog);

	}

}
