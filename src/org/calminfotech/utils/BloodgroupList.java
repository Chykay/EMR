package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgroup;
import org.springframework.stereotype.Service;

@Service
public class BloodgroupList extends CustomHibernateDaoSupport {

	private String mystr = "Tayor";

	public List<Bloodgroup> fetchAll() {
		List<Bloodgroup> list = getHibernateTemplate().find("from Bloodgroup");
		return list;
	}

	public Bloodgroup getBloodgroupById(int id) {
		List list = getHibernateTemplate().find(
				"from Bloodgroup where bloodgroup_id = ?", id);
		if (list.size() > 0)
			return (Bloodgroup) list.get(0);
		return null;
	}

}
