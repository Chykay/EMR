package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.OrganisationCompanyDao;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.SysSettings;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrganisationCompanyDaoImpl implements OrganisationCompanyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<OrganisationCompany> fetchAll(int id) {
		// TODO Auto-generated method stub
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			List list = this.sessionFactory.getCurrentSession()
					.createQuery("FROM OrganisationCompany  ORDER BY name ")
					.list();
			return list;
		} else {
			List list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"FROM OrganisationCompany where Id=?  ORDER BY name ")
					.setParameter(0,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();

			return list;
		}

	}

	@Override
	public List<OrganisationCompany> Top50fetchAll(int id) {
		// TODO Auto-generated method stub
		String sql;

		if (this.userIdentity.getOrganisation().getId() == 1) {
			List list = this.sessionFactory.getCurrentSession()
					.createQuery("FROM OrganisationCompany  ORDER BY Id desc")
					// .setParameter(0,
					// this.userIdentity.getOrganisation().getId())
					.setMaxResults(50).list();
			return list;
		} else {
			List list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"FROM OrganisationCompany where Id=? ORDER BY Id desc")
					.setParameter(
							0,
							this.userIdentity.getOrganisation().getOrgCoy()
									.getId()).setMaxResults(50).list();
			return list;

		}

	}

	@Override
	public List<SysSettings> fetchallsetting(int orgid) {
		// TODO Auto-generated method stub
		String sql;

		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM SysSettings where organisation_id=? ORDER BY Id desc")
				.setParameter(0, orgid)
				// .setMaxResults(50)
				.list();

		return list;
	}

	@Override
	public OrganisationCompany getOrganisationById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM OrganisationCompany WHERE id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (OrganisationCompany) list.get(0);

		return null;
	}

	@Override
	public void save(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(organisation);
	}

	@Override
	public void delete(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(organisation);
	}

	@Override
	public void update(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(organisation);
	}

	@Override
	public void update(SysSettings syssettings) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(syssettings);
	}

	@Override
	public SysSettings getSettingsById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM SysSettings WHERE id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (SysSettings) list.get(0);

		return null;
	}

}
