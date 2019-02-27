package org.calminfotech.utils.dao;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.NotificationEmail;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NotificationEmailDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<NotificationEmail> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationEmail.class);

		List list = criteria.list();
		return list;
	}

	public List<NotificationEmail> fetchAllByOrganisation(
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationEmail.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List list = criteria.list();
		return list;
	}

	public List<NotificationEmail> fetchAllUnSent(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationEmail.class)
				.add(Restrictions.eq("isSent", false))
				.add(Restrictions.eq("failed", false));

		List list = criteria.list();
		return list;
	}

	public List<NotificationEmail> fetchAllUnSent() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationEmail.class)
				.add(Restrictions.eq("isSent", false));

		List list = criteria.list();
		return list;
	}

	@Transactional(noRollbackFor = RuntimeException.class)
	public void save(NotificationEmail notificationEmail) {
		this.sessionFactory.getCurrentSession().save(notificationEmail);
	}

	public void update(NotificationEmail notificationEmail) {
		this.sessionFactory.getCurrentSession().update(notificationEmail);
	}

	public void delete(NotificationEmail notificationEmail) {
		this.sessionFactory.getCurrentSession().delete(notificationEmail);
	}

}
