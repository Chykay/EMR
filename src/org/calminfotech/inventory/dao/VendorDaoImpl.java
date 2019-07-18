package org.calminfotech.inventory.dao;

import java.util.List;

import org.calminfotech.inventory.daoInterface.VendorDaoInterface;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VendorDaoImpl implements VendorDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void save(Vendor vendor) {

		this.sessionFactory.getCurrentSession().save(vendor);
	}

	@Override
	public void update(Vendor vendor) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(vendor);

	}

	@Override
	public List<Vendor> getVendorsList() throws RecordNotFoundException {
		// TODO Auto-generated method stub
		/*
		 * List<Vendor> list = this.sessionFactory.getCurrentSession()
		 * .createQuery
		 * ("select id,email,telephoneNumber,vendorId,vendorName FROM Vendor ")
		 * .list();
		 * 
		 * if (list.size() > 0) return list;
		 * 
		 * throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
		 */
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Vendor where organisation.orgCoy.Id=? order by id desc")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.list();

		return list;
	}

	@Override
	public Vendor getVendorDetailsById(int id) throws RecordNotFoundException {

		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Vendor WHERE id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (Vendor) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void delete(Vendor vendor) {

		this.sessionFactory.getCurrentSession().delete(vendor);

	}

	@Override
	public String getLastVendorCodeGenWithShortName(String vendorShortNameCode) {
		// TODO Auto-generated method stub

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				"select max(vendorId) from Vendor as v where   v.vendorName like '%"
						+ vendorShortNameCode + "%'");
		qry.setMaxResults(1);

		List list = (List) qry.list();

		if (list != null && list.size() > 0) {
			return (String) list.get(0);

		}
		return null;
	}

}
