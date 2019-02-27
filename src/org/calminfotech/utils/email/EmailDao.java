package org.calminfotech.utils.email;

import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDao extends CustomHibernateDaoSupport {

	public void save(Email email) {
		getHibernateTemplate().save(email);
	}

	public Email getEmailById(int id) {
		List list = getHibernateTemplate().find("from Email where id = ?", id);
		if (list.size() > 0)
			return (Email) list.get(0);
		return null;
	}

	public List<Email> fetchAllBySender(String email) {
		List list = getHibernateTemplate().find("from Email where sender = ?",
				email);
		return (List<Email>) list;
	}

	public List<Email> fetchAllByReceiver(String email) {
		List list = getHibernateTemplate().find("from Email where receiver = ?",
				email);
		return (List<Email>) list;
	}
	
	public List<Email> fetchAll() {
		List list = getHibernateTemplate().find("from Email");
		return (List<Email>) list;
	}
}
