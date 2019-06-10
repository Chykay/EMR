package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.ConsultationCount;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationCountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<ConsultationCount> fetchAllconsultationcount(Date datefrom,
			Date dateto) {

		Session session = sessionFactory.getCurrentSession();

		// String sql =
		// "SELECT   NEWID() AS id, a.created_by, COUNT(a.visit_id) AS Appearances, a.organisation_id, dbo.user_profiles.title_id, dbo.user_profiles.last_name, dbo.user_profiles.other_names, dbo.titles.acronym FROM   dbo.consultations AS a INNER JOIN  dbo.user_profiles ON a.user_id = dbo.user_profiles.user_id INNER JOIN  dbo.titles ON dbo.user_profiles.title_id = dbo.titles.id WHERE  organisation_id=? and effectivedate BETWEEN ? AND ?  GROUP BY a.created_by, a.organisation_id, dbo.user_profiles.title_id, dbo.user_profiles.last_name, dbo.user_profiles.other_names, dbo.titles.acronym";

		String sql = "SELECT   NEWID() AS id,  a.created_by ,COUNT(a.visit_id) AS Appearances, a.organisation_id, dbo.user_profiles.title_id, dbo.user_profiles.last_name, dbo.user_profiles.other_names, dbo.titles.acronym FROM   dbo.consultations AS a INNER JOIN  dbo.user_profiles ON a.user_id = dbo.user_profiles.user_id INNER JOIN  dbo.titles ON dbo.user_profiles.title_id = dbo.titles.id WHERE effectivedate BETWEEN ? AND ?  GROUP BY a.created_by, a.organisation_id, dbo.user_profiles.title_id, dbo.user_profiles.last_name, dbo.user_profiles.other_names, dbo.titles.acronym";

		SQLQuery query = session.createSQLQuery(sql);

		query.addEntity(ConsultationCount.class);
		// query.setParameter(0, userIdentity.getOrganisation().getId());
		query.setParameter(0, datefrom);
		query.setParameter(1, dateto);

		List list = query.list();

		return list;
	}

}
