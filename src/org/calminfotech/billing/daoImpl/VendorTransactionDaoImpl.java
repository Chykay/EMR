package org.calminfotech.billing.daoImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.daoInterface.VendorTransactionDao;
import org.calminfotech.billing.models.VendorTransaction;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VendorTransactionDaoImpl implements VendorTransactionDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public VendorTransaction getVendorTransactionById(int id) {
		// TODO Auto-generated method stub
		List<VendorTransaction> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VendorTransaction where id= ? ")
				.setParameter(0, id).list();

		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(VendorTransaction);
	}

	@Override
	public void delete(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(VendorTransaction);
	}

	@Override
	public void update(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(VendorTransaction);
	}

	@Override
	public List<VendorTransaction> fetchAllByOrganisation() {
		// System.out.println("name");
		List<VendorTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VendorTransaction where organisation.orgCoy.Id=?   ORDER BY effectivedate DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				// .setParameter(1,billtype)

				.list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<VendorTransaction> fetchAllByOrganisation50() {
		// System.out.println("name");
		List<VendorTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VendorTransaction where organisation.orgCoy.Id=?  ORDER BY effectivedate DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setMaxResults(100).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<VendorTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate) {
		List<VendorTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VendorTransaction  where organisation.orgCoy.Id=?  and effectivedate >=? and effectivedate <= ?  ORDER BY id desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setTimestamp(1, startdate).setTimestamp(2, enddate).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<VendorTransaction> fetchAllByVendor(int vendor_id) {
		//TODO company; branch wahala
		List<VendorTransaction> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VendorTransaction where vendor_id = ? AND organisation.orgCoy.Id=?  ORDER BY effectivedate DESC")
				.setParameter(0, vendor_id)
				.setParameter(1, userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return list;
	}
}
