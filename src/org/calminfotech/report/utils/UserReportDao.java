package org.calminfotech.report.utils;

import java.util.List;

import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReportDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	// not usin this again. Usin the condition obne
	/*
	 * public List<User> fetchAllUsetbystatusold(String status) {
	 * System.out.println("myorg" + userIdentity.getOrganisation().getId());
	 * 
	 * // boolean newstatus = BooleSan.parseBoolean(status); List<User> list =
	 * sessionFactory .getCurrentSession() .createQuery(
	 * "from User where organisation_id=?  and status= ?  ORDER BY status DESC")
	 * .setParameter(0, userIdentity.getOrganisation().getId()) .setParameter(1,
	 * status).list(); return list; }
	 */

	// using this now

	public List<User> fetchAllUsetbystatus(String status) {
		System.out.println("selected status" + status);
		if (status == "All") {
			List list = this.sessionFactory.getCurrentSession()
					.createQuery("from User where organisation_id=?")
					.setParameter(0, userIdentity.getOrganisation().getId())
					.list();
			return (List<User>) list;
		} else {
			System.out.println("selected status" + status);
			// to cast the string back to boolean
			boolean newstatus = Boolean.parseBoolean(status);

			List list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where organisation_id=?  and status= ?  ORDER BY status DESC")

					.setParameter(0, userIdentity.getOrganisation().getId())
					.setParameter(1, newstatus).list();
			return (List<User>) list;

		}

	}

}
