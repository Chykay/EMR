package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.BillingInvoiceSum;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInvoiceSumDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<BillingInvoiceSum> fetchAllBillSum(Date datefrom, Date dateto) {

		Session session = sessionFactory.getCurrentSession();

		String sql = "SELECT NEWID() as id, CASE WHEN month(duedate) = 1 THEN 'January' WHEN month(duedate) = 2 THEN 'February' WHEN month(duedate) = 3 THEN 'March' WHEN month(duedate) = 4 THEN 'April' WHEN month(duedate) = 5 THEN 'May' WHEN month(duedate) = 6 THEN 'June' WHEN month(duedate) = 7 THEN 'July' WHEN month(duedate) = 8 THEN 'August' WHEN month(duedate) = 9 THEN 'September' WHEN month(duedate) = 10 THEN 'October' WHEN month(duedate)   = 11 THEN 'November' WHEN month(duedate) = 12 THEN 'December' END AS Monthname, YEAR(duedate) AS yearname, SUM(invamt) AS Grandtotal, organisation_id FROM  dbo.bill_invoice WHERE organisation_id=? and duedate BETWEEN ? AND ?  GROUP BY MONTH(duedate), YEAR(duedate), organisation_id";

		SQLQuery query = session.createSQLQuery(sql);

		query.addEntity(BillingInvoiceSum.class);
		query.setParameter(0, userIdentity.getOrganisation().getId());
		query.setParameter(1, datefrom);
		query.setParameter(2, dateto);

		List list = query.list();

		return list;
	}

	/*
	 * public List<BillingInvoiceSum> fetchAllBillSum(Date datefrom, Date
	 * dateto) { System.out.println("myorg" +
	 * userIdentity.getOrganisation().getId()); List<BillingInvoiceSum> list =
	 * sessionFactory .getCurrentSession() .createQuery(
	 * "from BillingInvoiceSum  where organisation_id=?  and duedate >=? and duedate <= ?  ORDER BY duedate DESC"
	 * ) .setParameter(0, userIdentity.getOrganisation().getId())
	 * .setParameter(1, datefrom).setParameter(2, dateto).list(); return list; }
	 */

}
