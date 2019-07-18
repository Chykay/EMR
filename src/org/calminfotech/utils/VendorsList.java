package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class VendorsList extends CustomHibernateDaoSupport {

	public List<Vendor> fetchAll() {
		List<Vendor> list = getHibernateTemplate().find("from Vendor");
		return list;
	}
	
	public Vendor getVendorById(int id) {
		List list = getHibernateTemplate().find(
				"from Vendor where id = ?", id);
		if (list.size() > 0)
			return (Vendor) list.get(0);
		return null;
	}
}
