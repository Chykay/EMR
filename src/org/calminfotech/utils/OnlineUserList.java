package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.user.models.OnlineUser;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;


@Service
public class OnlineUserList extends CustomHibernateDaoSupport{
	
	
	public List<OnlineUser> fetchAll() {
		List<OnlineUser> list = getHibernateTemplate().find("from OnlineUser");
		return list;
	}
	
	public OnlineUser getOnlineUserById(int id) {
		List list = getHibernateTemplate().find(
				"from OnlineUser where userId = ?", id);
		if (list.size() > 0)
			return (OnlineUser) list.get(0);
		return null;
	}


}
