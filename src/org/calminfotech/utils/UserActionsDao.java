package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.user.models.UserAction;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class UserActionsDao extends CustomHibernateDaoSupport {

	public List<UserAction> fetchAll() {
		List list = getHibernateTemplate().find("from UserAction");
		return (List<UserAction>) list;
	}

	public UserAction getUserActionById(int id) {

		List list = getHibernateTemplate().find("from UserAction where id = ?",
				id);
		if (list.size() > 0)
			return (UserAction) list.get(0);
		return null;
	}
}