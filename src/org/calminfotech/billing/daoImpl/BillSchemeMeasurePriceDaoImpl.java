package org.calminfotech.billing.daoImpl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillSchemeMeasurePriceDao;
import org.calminfotech.billing.models.BillSchemeMeasurePrice;
import org.calminfotech.billing.models.BillSchemeRankingPrice;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BillSchemeMeasurePriceDaoImpl implements BillSchemeMeasurePriceDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillSchemeMeasurePrice getBillSchemeMeasurePriceById(int id) {
		// TODO Auto-generated method stub
		List<BillSchemeMeasurePrice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("from BillSchemeMeasurePrice where id= ? ")
				.setParameter(0, id).list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillSchemeMeasurePrice billSchemeMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billSchemeMeasure);
	}

	@Override
	public void delete(BillSchemeMeasurePrice billSchemeMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billSchemeMeasure);
	}

	@Override
	public void update(BillSchemeMeasurePrice billSchemeMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billSchemeMeasure);
	}

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByOrganisationBytype(
			Organisation organisationId, int billtype) {
		// System.out.println("name");
		List<BillSchemeMeasurePrice> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillSchemeMeasurePrice where organisation.orgCoy.Id=? ORDER BY id DESC")
				.setParameter(0, organisationId.getOrgCoy().getId())
				// .setParameter(1,billtype)

				.list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	public UserIdentity getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(UserIdentity userIdentity) {
		this.userIdentity = userIdentity;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByOrganisation(
			Organisation organisationId) {
		// System.out.println("name");
		List<BillSchemeMeasurePrice> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillSchemeMeasurePrice where organisation.orgCoy.Id=?  and is_deleted = 0 ORDER BY id DESC")
				.setParameter(0, organisationId.getOrgCoy().getId()).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public BillSchemeMeasurePrice getBillSchemeMeasurePriceBySchemeandItemmeasure(
			int schemeid, int itemmeasure) {
		// TODO Auto-generated method stub
		List<BillSchemeMeasurePrice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillSchemeMeasurePrice where billscheme.id= ? and globalitemunitvw.id=? and is_deleted=0")
				.setParameter(0, schemeid).setParameter(1, itemmeasure)

				.list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<BillSchemeRankingPrice> fetchAllRankingByOrganisation(
			Organisation organisationId) {
		// TODO Auto-generated method stub
		List<BillSchemeRankingPrice> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillSchemeRankingPrice where organisation.orgCoy.Id=?  and is_deleted = 0 ORDER BY id DESC")
				.setParameter(0, organisationId.getOrgCoy().getId()).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public BillSchemeRankingPrice getBillSchemeRankingPriceById(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<BillSchemeRankingPrice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("from BillSchemeRankingPrice where id= ? ")
				.setParameter(0, id).list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billSchemeRankingPrice);
	}

	@Override
	public void delete(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billSchemeRankingPrice);
	}

	@Override
	public void update(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billSchemeRankingPrice);
	}

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByMeasure(int measureid) {
		// TODO Auto-generated method stub
		// System.out.println("name");
		List<BillSchemeMeasurePrice> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillSchemeMeasurePrice where globalitemunitvw.id=? and   organisation.orgCoy.Id=? and isDeleted=0  ORDER BY id DESC")
				.setParameter(0, measureid)

				.setParameter(1,
						userIdentity.getOrganisation().getOrgCoy().getId())
				// .setParameter(1,billtype)

				.list();

		System.out.println("no of records" + list.size());
		System.out.println("measure id " + measureid);
		for (BillSchemeMeasurePrice lst : list) {
			System.out.println("bill scheme " + lst.getBillscheme().getName());

		}

		return list;
	}

}
