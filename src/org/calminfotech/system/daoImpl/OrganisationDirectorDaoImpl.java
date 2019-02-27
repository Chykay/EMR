package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.OrganisationDirectorDao;
import org.calminfotech.system.models.OrganisationDirector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrganisationDirectorDaoImpl implements OrganisationDirectorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrganisationDirector> fetchAll() {
		List<OrganisationDirector> list = sessionFactory.getCurrentSession()
								   .createQuery("from OrganisationDirector").list();
		return list;
	}

	@Override
	public OrganisationDirector getOrganisationDirectorId(int id) {
		List<OrganisationDirector> list = sessionFactory.getCurrentSession()
				                   .createQuery("from OrganisationDirector where directorId = ? ")
				                   .setParameter(0, id).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(OrganisationDirector director) {
		sessionFactory.getCurrentSession().save(director);
	}

	@Override
	public void update(OrganisationDirector director) {
		sessionFactory.getCurrentSession().update(director);
	}

	@Override
	public void delete(OrganisationDirector director) {
		sessionFactory.getCurrentSession().delete(director);
	}

}
