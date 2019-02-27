package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.models.Symptom;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SymptomList {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Symptom> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Symptom.class);

		return criteria.list();
	}

	public Symptom getSymptomsById(int id) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Symptom.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();
		if (list.size() > 0)
			return (Symptom) list.get(0);
		return null;
	}
	
	
	

}
