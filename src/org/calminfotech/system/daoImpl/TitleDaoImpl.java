package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.TitleDao;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Title;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TitleDaoImpl implements TitleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Title> fetchAll() {
		// TODO Auto-generated method stub
		List<Title> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Title").list();
		return list;
	}

	@Override
	public Title getTitleById(int id) {
		// TODO Auto-generated method stub
		List<Title> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Title where id = ?").setParameter(0, id)
				.list();
		
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Title title) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(title);
	}

	@Override
	public void delete(Title title) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(title);
	}

	@Override
	public void update(Title title) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(title);
	}

	@Override
	public List<Title> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Title.class);
				//.createAlias("organisation", "organisation");
				//.add(Restrictions.eq("organisation.id", organisation.getId()));
		
		List list = criteria.list();
		return list;
	}

}
