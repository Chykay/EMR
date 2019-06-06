package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;

import org.calminfotech.report.models.PatientListing;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientList {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<PatientListing> fetchAllPatient(Date datefrom, Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<PatientListing> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PatientListing  where company_id=?  and startdate >=? and startdate <= ? ORDER BY startdate DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, datefrom).setParameter(2, dateto).list();
		return list;
	}

	// List<PatientListing> list =
	// getHibernateTemplate().find("from PatientListing where startdate BETWEEN ? and isDeleted =0",
	// id);
	// return list;
}
