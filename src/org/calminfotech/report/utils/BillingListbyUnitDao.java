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
public class BillingListbyUnitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public ReportCountSum getCountsum(Integer unitId, Date datefrom, Date dateto) {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  1 as countvalue,sum(invamt) as sumvalue, sum(amtpaid) as sumpay from vw_report_billing_list where organisation_id =?  and hrunitcategory_id=?  and duedate >=? and duedate <= ? order by duedate")
				.addEntity(ReportCountSum.class);
		query.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, unitId).setParameter(2, datefrom)
				.setParameter(3, dateto);

		List list = query.list();

		return (ReportCountSum) list.get(0);
	}

	public List<BillingListReport> fetchAllBillbyUnit(Integer unitid,
			Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<BillingListReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillingListReport  where organisation_id=?  and hrunitcategory_id=?  and duedate >=? and duedate <= ? ORDER BY id DESC")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, unitid)

				.setParameter(2, datefrom).setParameter(3, dateto).list();
		return list;
	}
}
