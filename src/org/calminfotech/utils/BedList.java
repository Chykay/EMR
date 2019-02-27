package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.system.models.Bed;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class BedList extends CustomHibernateDaoSupport {

	public List<Bed> fetchAllforadd(int id) {
		List<Bed> list = getHibernateTemplate().find(
				"from Bed where beditem.itemId = ? and isDeleted =0", id);
		return list;
	}

	public List<Bed> fetchAllforedit(int id) {
		List<Bed> list = getHibernateTemplate()
				.find("from Bed where beditem.itemId = ? and beditem.itemId != ? and isDeleted =0",
						id, id);
		return list;
	}

	/*
	 * public Bed getBedById(int id) { List list = getHibernateTemplate().find(
	 * "from Bed where bloodgroup_id = ?", id); if (list.size() > 0) return
	 * (Bed) list.get(0); return null; }
	 */
}
