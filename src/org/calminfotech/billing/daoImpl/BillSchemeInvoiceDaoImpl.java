package org.calminfotech.billing.daoImpl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillSchemeInvoiceDao;
import org.calminfotech.billing.models.BillSchemeInvoice;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BillSchemeInvoiceDaoImpl implements BillSchemeInvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillSchemeInvoice> fetchAll() {
		// TODO Auto-generated method stub
		List<BillSchemeInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice").list();
		if (list.size() > 0)
			return list;
		return null;
	}

	@Override
	public List<BillSchemeInvoice> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		List<BillSchemeInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillingInvoice where organisation.orgCoy.Id = ?")
				.setParameter(0, organisation.getOrgCoy().getId()).list();
		return list;
	}

	@Override
	public void save(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingInvoice);
	}

	@Override
	public void delete(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingInvoice);
	}

	@Override
	public void update(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingInvoice);
	}

	@Override
	public BillSchemeInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		List<BillSchemeInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (BillSchemeInvoice) list.get(0);
		return null;
	}

	@Override
	public List<BillSchemeInvoice> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		List<BillSchemeInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where patient = ?")
				.setParameter(0, patient).list();
		if (list.size() > 0)
			return list;
		return null;
	}

}
