package org.calminfotech.visitqueue.daoImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.models.Visit;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitDaoImpl implements VisitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Visit> fetchAllByOrganisation(int id) {
		// TODO Auto-generated method stub

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and organisation_id=? ORDER BY id DESC")
				.setParameter(0, id).setMaxResults(100).list();

		return list;
	}

	@Override
	public List<Visit> fetchAllbytoday(int organisationid) {
		// TODO Auto-generated method stub

		Calendar cal = GregorianCalendar.getInstance();

		cal.add(Calendar.DAY_OF_YEAR, -30);

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and organisation_id=? ORDER BY id DESC")
				.setParameter(0, organisationid).setMaxResults(100).list();

		return list;

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueue(int organisationid) {
		// TODO Auto-generated method stub

		/*
		 * System.out.print(userIdentity.getCurrentPointId()); Calendar cal =
		 * GregorianCalendar.getInstance();
		 * 
		 * cal.add( Calendar.DAY_OF_YEAR, -30);
		 * 
		 * System.out.print("effective dat lower "+cal.getTime());
		 * 
		 * Criterion org = Restrictions.eq("organisation.id", organisationid);
		 * Criterion user = Restrictions.eq("user.userId",
		 * userIdentity.getUserId()); Criterion unit =
		 * Restrictions.eq("unit.categoryId", userIdentity.getCurrentUnitId());
		 * Criterion point = Restrictions.eq("point.id",
		 * userIdentity.getCurrentPointId()); Criterion dat=
		 * Restrictions.gt("effectiveDate", cal.getTime());
		 * 
		 * Criterion allcond
		 * =Restrictions.conjunction().add(org).add(user).add(unit
		 * ).add(point).add(dat);
		 * 
		 * 
		 * Criteria criteria = this.sessionFactory .getCurrentSession()
		 * .createCriteria(Visit.class) .add(allcond)
		 * .addOrder(Order.desc("id"))
		 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * 
		 * List list = criteria.list();
		 */
		if (userIdentity.getOrganisation().getOrgCoy().getOrganisationType()
				.getOrganisationTypeId().intValue() == 1) {

			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=? and user.userId=? and currentunit.categoryId=?  and point.id=? ORDER BY id DESC")
					.setParameter(0, organisationid)
					.setParameter(1, userIdentity.getUserId())
					.setParameter(2, userIdentity.getCurrentUnitId())
					.setParameter(3, userIdentity.getCurrentPointId())

					.setMaxResults(100).list();

			return list;
		} else {
			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=? and currentunit.categoryId=?  and point.id=? ORDER BY id DESC")
					.setParameter(0, organisationid)
					// .setParameter(1,userIdentity.getUserId())
					.setParameter(1, userIdentity.getCurrentUnitId())
					.setParameter(2, userIdentity.getCurrentPointId())

					.setMaxResults(100).list();

			return list;

		}

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparam(Date dat1,
			Date dat2, int statusid) {
		// TODO Auto-generated method stub

		// Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		// Criterion stat = Restrictions.eq("status.id", statusid);
		// Criterion org =
		// Restrictions.eq("organisation.id",userIdentity.getOrganisation().getId());
		// Criterion user = Restrictions.eq("user.userId",
		// userIdentity.getUserId());
		// Criterion unit = Restrictions.eq("unit.categoryId",
		// userIdentity.getCurrentUnitId());
		// Criterion point = Restrictions.eq("point.id",
		// userIdentity.getCurrentPointId());
		// Criterion datc1= Restrictions.gt("effectiveDate", dat1);
		// Criterion datc2= Restrictions.lt("effectiveDate", dat2);

		if (statusid != 0) {

			// Criterion stat = Restrictions.eq("status.id", statusid);

			// Criterion allcond
			// =Restrictions.conjunction().add(org).add(user).add(unit).add(point).add(datc1).add(datc2).add(stat);

			// Criteria criteria = this.sessionFactory
			// .getCurrentSession()
			// .createCriteria(Visit.class)
			// .add(allcond)
			// .addOrder(Order.desc("id"))
			// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// List list = criteria.list();
			// return list;
			if (userIdentity.getOrganisation().getOrgCoy()
					.getOrganisationType().getOrganisationTypeId().intValue() == 1) {

				List<Visit> list = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from Visit  where  isDeleted=false and organisation_id=? and  user.userId=? and currentunit.categoryId=? and status.id=? and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
						.setParameter(0, userIdentity.getOrganisation().getId())
						.setParameter(1, userIdentity.getUserId())
						.setParameter(2, userIdentity.getCurrentUnitId())
						// .setParameter(3,userIdentity.getCurrentPointId())
						.setParameter(3, statusid).setParameter(4, dat1)
						.setParameter(5, dat2).list();

				return list;
			} else

			{

				List<Visit> list = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from Visit  where  isDeleted=false and organisation_id=? and currentunit.categoryId=? and status.id=? and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
						.setParameter(0, userIdentity.getOrganisation().getId())
						// .setParameter(1,userIdentity.getUserId())
						.setParameter(1, userIdentity.getCurrentUnitId())
						// .setParameter(3,userIdentity.getCurrentPointId())
						.setParameter(2, statusid).setParameter(3, dat1)
						.setParameter(4, dat2).list();

				return list;

			}

		} else {

			if (userIdentity.getOrganisation().getOrgCoy()
					.getOrganisationType().getOrganisationTypeId().intValue() == 1) {

				List<Visit> list = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from Visit  where  isDeleted=false and organisation_id=?  and user.userId=? and currentunit.categoryId=?  and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
						.setParameter(0, userIdentity.getOrganisation().getId())
						.setParameter(1, userIdentity.getUserId())
						.setParameter(2, userIdentity.getCurrentUnitId())
						// .setParameter(3,userIdentity.getCurrentPointId())
						.setParameter(3, dat1).setParameter(4, dat2)

						.list();

				return list;
			} else {

				List<Visit> list = sessionFactory
						.getCurrentSession()
						.createQuery(
								"from Visit  where  isDeleted=false and organisation_id=? and currentunit.categoryId=?  and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
						.setParameter(0, userIdentity.getOrganisation().getId())
						// .setParameter(1,userIdentity.getUserId())
						.setParameter(1, userIdentity.getCurrentUnitId())
						// .setParameter(3,userIdentity.getCurrentPointId())
						.setParameter(2, dat1).setParameter(3, dat2)

						.list();

				return list;

			}

		}

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuevitals(int organisationid) {
		// TODO Auto-generated method stub

		/*
		 * System.out.print(userIdentity.getCurrentPointId()); Calendar cal =
		 * GregorianCalendar.getInstance();
		 * 
		 * cal.add( Calendar.DAY_OF_YEAR, -30);
		 * 
		 * System.out.print("effective dat lower "+cal.getTime());
		 * 
		 * Criterion org = Restrictions.eq("organisation.id", organisationid);
		 * Criterion user = Restrictions.eq("user.userId",
		 * userIdentity.getUserId()); Criterion unit =
		 * Restrictions.eq("unit.categoryId", userIdentity.getCurrentUnitId());
		 * Criterion point = Restrictions.eq("point.id",
		 * userIdentity.getCurrentPointId()); Criterion dat=
		 * Restrictions.gt("effectiveDate", cal.getTime());
		 * 
		 * Criterion allcond
		 * =Restrictions.conjunction().add(org).add(user).add(unit
		 * ).add(point).add(dat);
		 * 
		 * 
		 * Criteria criteria = this.sessionFactory .getCurrentSession()
		 * .createCriteria(Visit.class) .add(allcond)
		 * .addOrder(Order.desc("id"))
		 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * 
		 * List list = criteria.list();
		 */

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and organisation_id=?  and currentunit.categoryId=?  and point.id=? ORDER BY id DESC")
				.setParameter(0, organisationid)
				// .setParameter(1,userIdentity.getUserId())
				.setParameter(1, userIdentity.getCurrentUnitId())
				.setParameter(2, userIdentity.getCurrentPointId())

				.setMaxResults(100).list();
		// and user.userId=?

		return list;
	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamvitals(Date dat1,
			Date dat2, int statusid) {
		// TODO Auto-generated method stub

		// Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		// Criterion stat = Restrictions.eq("status.id", statusid);
		// Criterion org =
		// Restrictions.eq("organisation.id",userIdentity.getOrganisation().getId());
		// Criterion user = Restrictions.eq("user.userId",
		// userIdentity.getUserId());
		// Criterion unit = Restrictions.eq("unit.categoryId",
		// userIdentity.getCurrentUnitId());
		// Criterion point = Restrictions.eq("point.id",
		// userIdentity.getCurrentPointId());
		// Criterion datc1= Restrictions.gt("effectiveDate", dat1);
		// Criterion datc2= Restrictions.lt("effectiveDate", dat2);

		if (statusid != 0) {

			// Criterion stat = Restrictions.eq("status.id", statusid);

			// Criterion allcond
			// =Restrictions.conjunction().add(org).add(user).add(unit).add(point).add(datc1).add(datc2).add(stat);

			// Criteria criteria = this.sessionFactory
			// .getCurrentSession()
			// .createCriteria(Visit.class)
			// .add(allcond)
			// .addOrder(Order.desc("id"))
			// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// List list = criteria.list();
			// return list;

			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=?  and currentunit.categoryId=? and status.id=? and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
					.setParameter(0, userIdentity.getOrganisation().getId())
					// .setParameter(1,userIdentity.getUserId())
					.setParameter(1, userIdentity.getCurrentUnitId())
					// .setParameter(3,userIdentity.getCurrentPointId())
					.setParameter(2, statusid).setParameter(3, dat1)
					.setParameter(4, dat2).list();

			// and user.userId=?

			return list;

		} else {

			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=?   and currentunit.categoryId=?  and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
					.setParameter(0, userIdentity.getOrganisation().getId())
					// .setParameter(1,userIdentity.getUserId())
					.setParameter(1, userIdentity.getCurrentUnitId())
					// .setParameter(3,userIdentity.getCurrentPointId())
					.setParameter(2, dat1).setParameter(3, dat2)

					.list();
			// and user.userId=?

			return list;

		}

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuefrontdesk(int organisationid) {
		// TODO Auto-generated method stub
		/*
		 * Calendar cal = GregorianCalendar.getInstance();
		 * 
		 * cal.add( Calendar.DAY_OF_YEAR, -30);
		 * 
		 * Criterion org = Restrictions.eq("organisation.id", organisationid);
		 * //Criterion user = Restrictions.eq("user.userId",
		 * userIdentity.getUserId()); //Criterion unit =
		 * Restrictions.eq("unit.categoryId", userIdentity.getCurrentUnitId());
		 * //Criterion point = Restrictions.eq("point.id",
		 * userIdentity.getCurrentPointId()); Criterion dat=
		 * Restrictions.gt("effectiveDate", cal.getTime());
		 * 
		 * Criterion allcond =Restrictions.conjunction().add(org).add(dat);
		 * 
		 * 
		 * Criteria criteria = this.sessionFactory .getCurrentSession()
		 * .createCriteria(Visit.class) .add(allcond)
		 * .addOrder(Order.desc("id"))
		 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * 
		 * List list = criteria.list(); return list;
		 */

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and organisation_id=? and unit.categoryId=?  ORDER BY id DESC")
				.setParameter(0, organisationid)
				.setParameter(1, userIdentity.getCurrentUnitId())
				.setMaxResults(100).list();
		return list;

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueueaccount(int organisationid) {
		// TODO Auto-generated method stub
		/*
		 * Calendar cal = GregorianCalendar.getInstance();
		 * 
		 * cal.add( Calendar.DAY_OF_YEAR, -30);
		 * 
		 * Criterion org = Restrictions.eq("organisation.id", organisationid);
		 * //Criterion user = Restrictions.eq("user.userId",
		 * userIdentity.getUserId()); //Criterion unit =
		 * Restrictions.eq("unit.categoryId", userIdentity.getCurrentUnitId());
		 * //Criterion point = Restrictions.eq("point.id",
		 * userIdentity.getCurrentPointId()); Criterion dat=
		 * Restrictions.gt("effectiveDate", cal.getTime());
		 * 
		 * Criterion allcond =Restrictions.conjunction().add(org).add(dat);
		 * 
		 * 
		 * Criteria criteria = this.sessionFactory .getCurrentSession()
		 * .createCriteria(Visit.class) .add(allcond)
		 * .addOrder(Order.desc("id"))
		 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * 
		 * List list = criteria.list(); return list;
		 */

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and organisation_id=?  ORDER BY id DESC")
				.setParameter(0, organisationid).setMaxResults(100).list();

		return list;
	}

	@Override
	public Visit getVisitationById(int id) {
		// TODO Auto-generated method stub
		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * 
		 * .createCriteria(Visit.class).add(Restrictions.eq("id", id))
		 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 * 
		 * 
		 * List list = criteria.list();
		 * 
		 * if (list.size() > 0) return (Visit) list.get(0);
		 * 
		 * return null;
		 */

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit  where  isDeleted=false and  id=?  ORDER BY id DESC")
				.setParameter(0, id)

				.list();

		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;

	}

	@Override
	public Visit save(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visit);
		return visit;
	}

	@Override
	public void delete(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(visit);
	}

	@Override
	public void update(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(visit);
	}

	@Override
	public List<Visit> fetchByPatientId(int patientId) {
		// TODO Auto-generated method stub

		List<Visit> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Visit where isDeleted=false and patient.PatientId=?")
				.setParameter(0, patientId).list();

		return list;
	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamfrontdesk(Date dat1,
			Date dat2, int statusid) {
		// TODO Auto-generated method stub

		// Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);
		// Criterion allcond;
		// Criterion org =
		// Restrictions.eq("organisation.id",userIdentity.getOrganisation().getId());
		// Criterion user = Restrictions.eq("user.userId",
		// userIdentity.getUserId());
		// Criterion unit = Restrictions.eq("unit.categoryId",
		// userIdentity.getCurrentUnitId());
		// Criterion point = Restrictions.eq("point.id",
		// userIdentity.getCurrentPointId());
		// Criterion datc1= Restrictions.gt("effectiveDate", dat1);
		// Criterion datc2= Restrictions.lt("effectiveDate", dat2);

		if (statusid != 0) {

			// Criterion stat = Restrictions.eq("status.id", statusid);
			// Criterion allcond
			// =Restrictions.conjunction().add(org).add(datc1).add(datc2).add(stat);

			// Criteria criteria = this.sessionFactory
			// .getCurrentSession()
			// .createCriteria(Visit.class)
			// .add(allcond)
			// .addOrder(Order.desc("id"))
			// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// List list = criteria.list();
			// return list;

			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=?  and unit.categoryId=?  and status.id=? and effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
					.setParameter(0, userIdentity.getOrganisation().getId())
					.setParameter(1, userIdentity.getCurrentUnitId())
					// .setParameter(1,userIdentity.getUserId())
					// .setParameter(2,userIdentity.getCurrentUnitId())
					// .setParameter(3,userIdentity.getCurrentPointId())
					.setParameter(2, statusid).setParameter(3, dat1)
					.setParameter(4, dat2).list();

			return list;

		}

		else {

			List<Visit> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Visit  where  isDeleted=false and organisation_id=? and unit.categoryId=? and  effectiveDate >=? and effectiveDate <= ?  ORDER BY id DESC")
					.setParameter(0, userIdentity.getOrganisation().getId())
					.setParameter(1, userIdentity.getCurrentUnitId())
					// .setParameter(1,userIdentity.getUserId())
					// .setParameter(2,userIdentity.getCurrentUnitId())
					// .setParameter(3,userIdentity.getCurrentPointId())
					// .setParameter(1,statusid)
					.setParameter(2, dat1).setParameter(3, dat2).list();

			return list;

		}

	}

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamaccount(Date dat1,
			Date dat2, int statusid) {
		// TODO Auto-generated method stub

		// Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);
		// Criterion allcond;
		Criterion org = Restrictions.eq("organisation.id", userIdentity
				.getOrganisation().getId());
		// Criterion user = Restrictions.eq("user.userId",
		// userIdentity.getUserId());
		// Criterion unit = Restrictions.eq("unit.categoryId",
		// userIdentity.getCurrentUnitId());
		// Criterion point = Restrictions.eq("point.id",
		// userIdentity.getCurrentPointId());
		Criterion datc1 = Restrictions.gt("effectiveDate", dat1);
		Criterion datc2 = Restrictions.lt("effectiveDate", dat2);

		if (statusid != 0) {

			Criterion stat = Restrictions.eq("status.id", statusid);
			Criterion allcond = Restrictions.conjunction().add(org).add(datc1)
					.add(datc2).add(stat);

			Criteria criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(Visit.class).add(allcond)
					.addOrder(Order.desc("id"))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List list = criteria.list();
			return list;

		}

		else {
			Criterion allcond = Restrictions.conjunction().add(org).add(datc1)
					.add(datc2);
			Criteria criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(Visit.class).add(allcond)
					.addOrder(Order.desc("id"))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List list = criteria.list();
			return list;

		}

	}

	@Override
	public List<Visit> fetchAllByThese(int userId, Date from, Date to,
			int mstatus, String chkothers) {

		if (chkothers == null) {
			System.out.println("I have not been checked");
			Criteria criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(Visit.class);

			int y = 6;
			/*
			 * Date mydate = new Date(2015-05-10); Date mydate1 = new
			 * Date(2015-05-11);
			 */
			criteria.add(Restrictions.eq("point.id", y));
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.ge("createDate", from));
			criteria.add(Restrictions.le("createDate", to));
			criteria.add(Restrictions.eq("status.id", mstatus));
			/*
			 * Conjunction objConjunction = Restrictions.conjunction();
			 * objConjunction
			 * .add(Restrictions.and(Restrictions.eq("point.id",y),
			 * Restrictions.eq("userId", 46))); Date mydate = new
			 * Date(2015-05-10); Date mydate1 = new Date(2015-05-11);
			 * objConjunction
			 * .add(Restrictions.and(Restrictions.ge("createDate",mydate ),
			 * Restrictions.le("createDate",mydate1 )));
			 * objConjunction.add(Restrictions.eq("status.id", 2));
			 * 
			 * criteria.add(objConjunction);
			 */
			// List list = criteria.list();
			// return list;
			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
		}

		else {
			System.out.println("I have been checked");
			Criteria criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(Visit.class);
			// Conjunction objConjunction = Restrictions.conjunction();
			int y = 6;
			criteria.add(Restrictions.eq("point.id", y));
			criteria.add(Restrictions.ge("createDate", from));
			criteria.add(Restrictions.le("createDate", to));
			criteria.add(Restrictions.eq("status.id", mstatus));

			// List list = criteria.list();

			// return list;
			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
		}

	}

	@Override
	public List<Visit> fetchAllByThese(int userId, int pointId, Date from,
			Date to, int mstatus, boolean chkothers) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class);

		criteria.add(Restrictions.eq("point.id", pointId));

		criteria.add(Restrictions.ge("createDate", from));
		criteria.add(Restrictions.le("createDate", to));
		criteria.add(Restrictions.eq("status.id", mstatus));// status e.
															// open,close e.t.c

		if (!chkothers) {
			/*
			 * System.out.println("I have not been checked"); Conjunction
			 * objConjunction = Restrictions.conjunction(); objConjunction
			 * .add(Restrictions.and(Restrictions.eq("point.id",y),
			 * Restrictions.eq("userId", 46))); Date mydate = new
			 * Date(2015-05-10); Date mydate1 = new Date(2015-05-11);
			 * objConjunction
			 * .add(Restrictions.and(Restrictions.ge("createDate",mydate ),
			 * Restrictions.le("createDate",mydate1 )));
			 * objConjunction.add(Restrictions.eq("status.id", 2));
			 * criteria.add(objConjunction);
			 */
			// List list = criteria.list();
			// return list;
			criteria.add(Restrictions.eq("userId", userId));
		}
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
