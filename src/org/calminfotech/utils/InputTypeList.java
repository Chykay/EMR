package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.models.InputType;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InputTypeList {

	@Autowired
	private SessionFactory sessionFactory;

	public List<InputType> fetchAll() {
		List<InputType> list = this.sessionFactory.getCurrentSession()
				.createCriteria(InputType.class).list();
		return list;
	}

	public InputType getInputTypeById(int id) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(InputType.class).add(Restrictions.eq("id", id));
		
		List<InputType> list = criteria.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
}
