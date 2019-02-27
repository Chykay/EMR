package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Invoicestatus;

import org.springframework.stereotype.Service;


@Service
public class InvoicestatusList extends CustomHibernateDaoSupport{
	
	
	
	public List<Invoicestatus> fetchAll() {
		List<Invoicestatus> list = getHibernateTemplate().find("from Invoicestatus");
		return list;
	}
	
	
	public Invoicestatus getInvoicestatusById(int id) {
		List list = getHibernateTemplate().find(
				"from  Invoicestatus where Invoicestatus_id = ?", id);
		if (list.size() > 0)
			return (Invoicestatus) list.get(0);
		return null;
	}

}
