package org.calminfotech.report.utils;

import java.util.List;

import org.calminfotech.report.models.PrescribedDrugReport;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopPrescribedDrug {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List<PrescribedDrugReport> fetchAllPrescribedDrugReport(
			int organisationId) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<PrescribedDrugReport> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PrescribedDrugReport where organisation_id=?")
				.setParameter(0, userIdentity.getOrganisation().getId()).list();
		return list;
	}

}
