package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.GroupFormFieldDao;
import org.calminfotech.user.models.GroupFormField;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GroupFormFieldDaoImpl implements GroupFormFieldDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(groupFormField);
	}

	@Override
	public void update(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(groupFormField);
	}

	@Override
	public void delete(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(groupFormField);
	}

	@Override
	public List<GroupFormField> fetchAllByGroupId(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GroupFormField.class)
				.createAlias("group", "group").createCriteria("group.id", id);

		List<GroupFormField> list = criteria.list();
		return list;
	}

	@Override
	public List<GroupFormField> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GroupFormField.class);

		List<GroupFormField> list = criteria.list();
		return list;
	}
	
	public GroupFormField getFieldById(int id){
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GroupFormField.class).add(Restrictions.eq("id", id));
		List<GroupFormField> list = criteria.list();
		
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

}
