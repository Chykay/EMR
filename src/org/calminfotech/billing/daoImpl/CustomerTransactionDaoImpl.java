package org.calminfotech.billing.daoImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.daoInterface.CustomerTransactionDao;
import org.calminfotech.billing.models.CustomerTransaction;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerTransactionDaoImpl implements CustomerTransactionDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CustomerTransaction getCustomerTransactionById(int id) {
		// TODO Auto-generated method stub
		List<CustomerTransaction> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("from CustomerTransaction where id= ? ")
				.setParameter(0, id).list();

		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(CustomerTransaction);
	}

	@Override
	public void delete(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(CustomerTransaction);
	}

	@Override
	public void update(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(CustomerTransaction);
	}

	@Override
	public List<CustomerTransaction> fetchAllByOrganisation() {
		// System.out.println("name");
		List<CustomerTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CustomerTransaction where organisation.orgCoy.Id=?   ORDER BY id DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				// .setParameter(1,billtype)

				.list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<CustomerTransaction> fetchAllByOrganisation50() {
		// System.out.println("name");
		List<CustomerTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CustomerTransaction where organisation.orgCoy.Id=?  ORDER BY effectivedate DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setMaxResults(150).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<CustomerTransaction> fetchAllByOrganisationbyform(
			Date startdate, Date enddate) {
		System.out.print("the date " + startdate);
		System.out.print("the date " + enddate);

		List<CustomerTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CustomerTransaction  where organisation.orgCoy.Id=?  and effectivedate >=? and effectivedate <= ?  ORDER BY effectivedate desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setTimestamp(1, startdate).setTimestamp(2, enddate).list();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerTransaction> fetchAllByCustomer(int customer_id) {

		//TODO company; branch wahala
		List<CustomerTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CustomerTransaction  where organisation.orgCoy.Id=?  and patient_id = ?  ORDER BY effectivedate desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, customer_id)
				.list();

		return list;
	}
}
