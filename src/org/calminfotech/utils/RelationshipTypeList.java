package org.calminfotech.utils;

import java.util.List;


import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Relationshiptype;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Lifestatus;
import org.calminfotech.utils.models.Phonetype;
import org.springframework.stereotype.Service;


@Service
public class RelationshipTypeList extends CustomHibernateDaoSupport{
	
	
	
	public List<Relationshiptype> fetchAll() {
		List<Relationshiptype> list = getHibernateTemplate().find("from  Relationshiptype");
		return list;
	}
	
	
	public Relationshiptype getRelationshipTypeById(int id) {
		List list = getHibernateTemplate().find(
				"from Relationshiptype where relationshiptype_id = ?", id);
		if (list.size() > 0)
			return (Relationshiptype) list.get(0);
		return null;
	}

}
