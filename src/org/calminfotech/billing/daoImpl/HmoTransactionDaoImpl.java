package org.calminfotech.billing.daoImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.daoInterface.HmoTransactionDao;
import org.calminfotech.hmo.models.HmoTransaction;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoTransactionDaoImpl implements HmoTransactionDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public HmoTransaction getHmoTransactionById(int id) {
		// TODO Auto-generated method stub
		List<HmoTransaction> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoTransaction where id= ? ")
				.setParameter(0, id).list();

		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(HmoTransaction);
	}

	@Override
	public void delete(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(HmoTransaction);
	}

	@Override
	public void update(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(HmoTransaction);
	}

	@Override
	public List<HmoTransaction> fetchAllByOrganisation() {
		// System.out.println("name");
		List<HmoTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoTransaction where organisation.orgCoy.Id=?   ORDER BY id DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				// .setParameter(1,billtype)

				.list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<HmoTransaction> fetchAllByOrganisation50() {
		// System.out.println("name");
		List<HmoTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoTransaction where organisation.orgCoy.Id=?  ORDER BY effectivedate DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setMaxResults(150).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<HmoTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate) {
		System.out.print("the date " + startdate);
		System.out.print("the date " + enddate);

		List<HmoTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoTransaction  where organisation.orgCoy.Id=?  and effectivedate >=? and effectivedate <= ?  ORDER BY effectivedate desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setTimestamp(1, startdate).setTimestamp(2, enddate).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HmoTransaction> fetchAllByHMO(int hmo_id) {

		//TODO company; branch wahala
		List<HmoTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoTransaction  where organisation.orgCoy.Id=?  and hmo_id = ?  ORDER BY effectivedate desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, hmo_id)
				.list();

		return list;
	}
}
