package org.calminfotech.report.utils;

import java.util.List;

import org.calminfotech.report.models.PatientHmoListCombo;
import org.calminfotech.report.models.PatientListbyHmo;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientListbyHmoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	// thiis is to list HMO in repo
	public List<PatientHmoListCombo> fetchAll() {
		Query list = this.sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  h.id, h.name FROM hmo h inner join organisations o on h.organisation_id=o.id where o.company_id=? and h.is_deleted=0")
				.addEntity(PatientHmoListCombo.class)
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId());

		List<PatientHmoListCombo> lst = list.list();
		return lst;
	}

	public List<PatientListbyHmo> fetchAllPatientbyHmo(Integer hmoid) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<PatientListbyHmo> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PatientListbyHmo  where company_id=?  and hmoid = ? ORDER BY hmoid DESC")
				.setParameter(0,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, hmoid).list();
		return list;
	}

}
