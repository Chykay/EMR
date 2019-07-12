package org.calminfotech.billing.daoImpl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDao;
import org.calminfotech.billing.models.BillSchemeItemPrice;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BillItemPriceDaoImpl implements BillSchemeItemPriceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillSchemeItemPrice> fetchAll() {
		List<BillSchemeItemPrice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillItemPrice").list();
		return list;
	}

	@Override
	public BillSchemeItemPrice getBillItemPriceById(int id) {
		// TODO Auto-generated method stub
		List<BillSchemeItemPrice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillItemPrice where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillSchemeItemPrice billSchemeItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billSchemeItemPrice);
		// return ehmoItem;

	}

	@Override
	public void delete(BillSchemeItemPrice billSchemeItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billSchemeItemPrice);

	}

	@Override
	public void update(BillSchemeItemPrice billSchemeItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billSchemeItemPrice);

	}

}
