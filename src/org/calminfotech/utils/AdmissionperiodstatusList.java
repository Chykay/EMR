package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Admissionperiodstatus;

import org.springframework.stereotype.Service;


@Service
public class AdmissionperiodstatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Admissionperiodstatus> fetchAll() {
		List<Admissionperiodstatus> list = getHibernateTemplate().find("from Admissionperiodstatus");
		return list;
	}
	
	
	public Admissionperiodstatus getAdmissionperiodstatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Admissionperiodstatus where Admissionperiodstatus_id = ?", id);
		if (list.size() > 0)
			return (Admissionperiodstatus) list.get(0);
		return null;
	}

}
