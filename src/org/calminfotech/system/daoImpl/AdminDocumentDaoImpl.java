package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.AdminDocumentDao;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationDocument;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AdminDocumentDaoImpl implements AdminDocumentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<OrganisationDocument> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDocument.class).list();
	}

	@Override
	public List<OrganisationDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDocument.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<OrganisationDocument> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(OrganisationDocument.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId())).list();
	}

	@Override
	public void save(OrganisationDocument adminDocument) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(adminDocument);
	}

	@Override
	public OrganisationDocument getAdminDocumentById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AdminDocument WHERE id = ? and status = 'active'")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (OrganisationDocument) list.get(0);

		return null;
	}

	@Override
	public void delete(OrganisationDocument adminDocument) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(adminDocument);
	}

}
