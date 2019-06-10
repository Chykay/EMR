package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.BillingHmoListCombobox;
import org.calminfotech.report.models.BillingListReport;
import org.calminfotech.report.models.ReportCountSum;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingListbyHmoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	/*
	 * //thiis is to list HMO in repo public List<PatientHmoListCombo>
	 * fetchAll() { List list = this.sessionFactory.getCurrentSession()
	 * .createQuery("FROM PatientHmoListCombo ").list();
	 * 
	 * return list; }
	 */

	public ReportCountSum getCountsum(Integer hmoid, Date datefrom, Date dateto) {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  1 as countvalue,sum(hmoamt) as sumvalue, sum(amtpaid) as sumpay from vw_report_billing_list where organisation_id =? and hmoid  =? and duedate >=? and duedate <= ?")
				.addEntity(ReportCountSum.class);
		query.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, hmoid).setParameter(2, datefrom)
				.setParameter(3, dateto);

		List list = query.list();

		return (ReportCountSum) list.get(0);
	}

	// thiis is to list HMO in repo
	public List<BillingHmoListCombobox> fetchAll() {
		Query query = this.sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  h.id, h.name FROM hmo h inner join organisations o on h.organisation_id=o.id where o.company_id=? and h.is_deleted=0")
				.addEntity(BillingHmoListCombobox.class);
		query.setParameter(0, userIdentity.getOrganisation().getOrgCoy()
				.getId());

		List list = query.list();

		return list;
	}

	public List<BillingListReport> fetchAllBillbyHmo(Integer hmoid,
			Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<BillingListReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillingListReport  where organisation_id=?  and hmoId = ? and duedate >=? and duedate <= ? ORDER BY duedate")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, hmoid).setParameter(2, datefrom)
				.setParameter(3, dateto).list();
		return list;
	}
}
