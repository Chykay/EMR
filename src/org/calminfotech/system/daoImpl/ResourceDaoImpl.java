package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.ResourceDao;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.models.Group;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Resource> fetchAll() {
		// TODO Auto-generated method stub

		List<Resource> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class).list();

		return list;
	}

	@Override
	public Resource getReourceById(int id) {
		// TODO Auto-generated method stub
		List<Resource> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class).add(Restrictions.eq("id", id))
				.list();

		if (list.size() > 0)
			return list.get(0);

		return null;
	}

	@Override
	public Resource getResourceByUrlPattern(String urlPattern) {
		List<Resource> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class)
				.add(Restrictions.eq("urlPattern", urlPattern)).list();

		if (list.size() > 0)
			return list.get(0);

		return null;
	}

	@Override
	public Resource getResourceByUrlPattern(String urlPattern,
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class)
				.add(Restrictions.eq("urlPattern", urlPattern))
				// Restrict to user organisation
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List<Resource> list = criteria.list();
		if (list.size() > 0)
			return list.get(0);

		return null;
	}

	@Override
	public void save(Resource resource) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(resource);
	}

	@Override
	public void update(Resource resource) {
		this.sessionFactory.getCurrentSession().update(resource);
	}

	@Override
	public void clearResources(Organisation organisation) {
		// TODO Auto-generated method stub

		List<Resource> list = this.fetchAllByOrganisation(organisation);
		for (Resource r : list) {
			r.getGroups().clear(); // clear intermediate table
			this.sessionFactory.getCurrentSession().delete(r); // delete
		}

	}

	@Override
	public List<Resource> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List<Resource> list = criteria.list();
		return list;
	}

	@Override
	public List<Resource> getResourcesByGroup(Group group) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Resource.class).createAlias("group", "group")
				.add(Restrictions.eq("group.id", group.getId()));

		@SuppressWarnings("unchecked")
		List<Resource> list = criteria.list();
		return list;
	}

}
