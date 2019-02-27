package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.daoInterface.PermissionDao;
import org.calminfotech.user.models.Authorization;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void save(Permission permission) {
		this.sessionFactory.getCurrentSession().save(permission);
	}

	@Override
	public void update(Permission permission) {
		this.sessionFactory.getCurrentSession().update(permission);
	}

	@Override
	public void delete(Permission permission) {
		this.sessionFactory.getCurrentSession().delete(permission);
	}

	@Override
	public Permission getPermissionById(int id) {
		List<Permission> list = this.sessionFactory.getCurrentSession()
				.createCriteria(Permission.class)
				.add(Restrictions.eq("id", id)).list();
		if (list.size() > 0)
			return list.get(0);

		return null;
	}

	@Override
	public List<Permission> fetchAll() {
		@SuppressWarnings("unchecked")
		List<Permission> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Permission").list();
		return list;
	}

	@Override
	public List<Permission> getPermissionByRole(Role role) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Permission.class).createAlias("role", "role")
				.add(Restrictions.eq("role.id", role.getRoleId()));
		List<Permission> list = criteria.list();
		return list;
	}

	@Override
	public List<Permission> fetchByOrganisation(Organisation organisation) {
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(Permission.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setProjection(
						Projections.projectionList().add(
								Projections.groupProperty("description")));
		List<Permission> list = criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> fetchMenuPermissionByOrganisation(
			Organisation organisation, String val) {

		// TODO Auto-generated method stub
		List<Permission> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Permission where organisation = ? and category = ? order by permissionId")
				.setParameter(0, organisation)
				// .setParameter(1, val)
				.list();
		if (list.size() > 0) {
			return (List<Permission>) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authorization> fetchAuthorizationlist(Integer userid,
			String authcode) {
		//
		// TODO Auto-generated method stub
		List<Authorization> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Authorization where userId = ? and authCode= ? ")
				.setParameter(0, userid).setParameter(1, authcode)
				// .setParameter(2, userIdentity.getOrganisation().ggetId())
				.list();

		return list;
	}

}
