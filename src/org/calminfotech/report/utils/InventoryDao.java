package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.BillingListReport;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InventoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<BillingListReport> fetchAllBill(Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<BillingListReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillingListReport  where organisation_id=?  and duedate >=? and duedate <= ?  ORDER BY duedate DESC")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, datefrom).setParameter(2, dateto).list();
		return list;
	}

}
