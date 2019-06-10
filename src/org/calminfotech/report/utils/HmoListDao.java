package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.HmoListReport;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoListDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<HmoListReport> fetchAllHMO(Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<HmoListReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoListReport  where organisation_id=?  and createdate >=? and createdate <= ?  ORDER BY createdate DESC")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, datefrom).setParameter(2, dateto).list();
		return list;
	}

}
