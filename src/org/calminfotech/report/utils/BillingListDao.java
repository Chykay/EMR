package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.BillingListReport;
import org.calminfotech.report.models.ReportCountSum;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingListDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public ReportCountSum getCountsum(Date datefrom, Date dateto) {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  1 as countvalue,isnull(sum(invamt),0.00) as sumvalue, isnull(sum(amtpaid),0.00) as sumpay , isnull(sum(cashmode),0.00) as sumcash ,isnull(sum(posmode),0.00) as sumpos ,isnull(sum(transfermode),0.00) as sumtransfer,isnull(sum(depositmode),0.00) as sumdeposit "

								+ "from vw_report_billing_list where company_id =?  and duedate >=? and duedate <= ?")
				.addEntity(ReportCountSum.class);
		query.setParameter(0,
				userIdentity.getOrganisation().getOrgCoy().getId())
				.setTimestamp(1, datefrom).setTimestamp(2, dateto);

		List list = query.list();

		return (ReportCountSum) list.get(0);
	}

	public List<BillingListReport> fetchAllBill(Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		System.out.println("myorg" + datefrom);
		System.out.println("myorg" + dateto);

		List<BillingListReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillingListReport  where organisation.orgCoy.Id=?  and duedate >=? and duedate <= ?  ORDER BY duedate")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setTimestamp(1, datefrom).setTimestamp(2, dateto).list();

		System.out.print("Thedate" + datefrom);

		return list;
	}
	/*
	 * public List<BillingListReport> fetchAllBill(String datefrom, String
	 * dateto) { System.out.println("myorg" +
	 * userIdentity.getOrganisation().getId()); List<BillingListReport> list =
	 * sessionFactory .getCurrentSession() .createQuery(
	 * "from BillingListReport  where organisation.orgCoy.Id=?  and duedate >=? and duedate <= ?  ORDER BY duedate DESC"
	 * ) .setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
	 * .setParameter(1, datefrom).setParameter(2, dateto).list();
	 * 
	 * System.out.print("Thedate" + datefrom);
	 * 
	 * return list; }
	 */
}
