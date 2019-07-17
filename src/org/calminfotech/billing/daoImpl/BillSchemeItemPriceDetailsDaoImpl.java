package org.calminfotech.billing.daoImpl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDetailsDao;
import org.calminfotech.billing.models.BillSchemeItemPriceDetails;
import org.calminfotech.system.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BillSchemeItemPriceDetailsDaoImpl implements
		BillSchemeItemPriceDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillSchemeItemPriceDetails> fetchAll() {
		// TODO Auto-generated method stub
		List<BillSchemeItemPriceDetails> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("from BillingSchemeItemPriceDetails").list();
		return list;
	}

	@Override
	public List<BillSchemeItemPriceDetails> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(BillSchemeItemPriceDetails.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.orgCoy.Id", organisation
						.getOrgCoy().getId()));

		List list = criteria.list();
		return list;
	}

	@Override
	public void save(BillSchemeItemPriceDetails billSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession()
				.save(billSchemeItemPriceDetails);
	}

	@Override
	public void delete(BillSchemeItemPriceDetails billSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(
				billSchemeItemPriceDetails);
	}

	@Override
	public void update(BillSchemeItemPriceDetails billSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(
				billSchemeItemPriceDetails);
	}

	@Override
	public BillSchemeItemPriceDetails getBillingById(int id) {
		// TODO Auto-generated method stub
		List<BillSchemeItemPriceDetails> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("from BillingSchemeItemPriceDetails where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (BillSchemeItemPriceDetails) list.get(0);
		return null;
	}

	@Override
	public List<BillSchemeItemPriceDetails> getBillingInvoice(
			Integer billingSchemeId, Integer unitofmeasureId,
			Integer globalItemPointId, Organisation organisation) {
		// TODO Auto-generated method stub

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.getNamedQuery("spGetBillingInvoice")
					.setParameter("billingScheme", billingSchemeId)
					.setParameter("unitofMeasure", unitofmeasureId)
					.setParameter("globalItemPoint", globalItemPointId)
					.setParameter("organisation",
							organisation.getOrgCoy().getId());
			List result = query.list();
			System.out.println("Procedure " + result);
			if (result.size() > 0)
				return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}
		return null;
	}

}
