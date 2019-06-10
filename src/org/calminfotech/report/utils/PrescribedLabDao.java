package org.calminfotech.report.utils;

import java.util.Date;
import java.util.List;
/*
import org.calminfotech.consultation.models.VisitConsultationPrescribedLabtest;*/
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescribedLabDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	/*public List<VisitConsultationPrescribedLabtest> fetchAlltest(Date datefrom,
			Date dateto) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());
		List<VisitConsultationPrescribedLabtest> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VisitConsultationPrescribedLabtest  where visitConsultation.organisation.Id=?  and duedate >=? and duedate <= ?  ORDER BY duedate DESC")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, datefrom).setParameter(2, dateto).list();
		return list;
	}

	public List<VisitConsultationPrescribedLabtest> fetchAllresult(Integer id) {
		System.out.println("myorg" + userIdentity.getOrganisation().getId());

		System.out.println("myDAOID	" + id);
		List<VisitConsultationPrescribedLabtest> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VisitConsultationPrescribedLabtest  where visitConsultation.organisation.Id=?  and id =?")
				.setParameter(0, userIdentity.getOrganisation().getId())
				.setParameter(1, id).list();
		return list;
	}*/

}
