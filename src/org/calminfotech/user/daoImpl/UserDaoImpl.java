package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			List list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User  where  userType.id=2 or  userType.id=3 and is_deleted=0 order by createdDate desc")
					// .setParameter(0, userIdentity.getOrganisation().getId())
					.list();
			return (List<User>) list;
		} else {
			List list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from User where organisation.orgCoy.Id= ?   and is_deleted=0 order by createdDate desc")
					.setParameter(0,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();
			return (List<User>) list;

		}

		/*
		 * Criteria criteria = this.sessionFactory.getCurrentSession()
		 * .createCriteria(User.class).addOrder(Order.desc("createdDate"));
		 * 
		 * List list =
		 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		 * return list;
		 */
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User getUserByEmail(String email, Integer orgid) {
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ? and   organisation.orgCoy.Id=? and is_deleted=0  and organisation.orgCoy.active=1 and status=1  and lock=0")
				.setParameter(0, email).setParameter(1, orgid)

				.list();
		if (list.size() > 0)

			return (User) list.get(0);

		System.out.print("Am returning NULL");
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ?  and is_deleted=0  and  organisation.orgCoy.active=1 and status=1 and lock=0")
				.setParameter(0, email)

				.list();
		if (list.size() > 0)

			return (User) list.get(0);

		System.out.print("Am returning NULL");
		return null;
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where userId = ?   ")
				.setParameter(0, userId)

				.list();

		if (list.size() > 0)
			return (User) list.get(0);
		return null;
	}

	// Can't use to check login credential
	@Override
	public User getUserByEmailAndPassword(String email, String password,
			Integer orgid) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ? and password = ? and status=1 and  organisation.orgCoy.Id=? and lock=0 and organisation.orgCoy.active=1 and is_deleted=0 ")
				.setParameter(0, email).setParameter(1, password)
				.setParameter(2, orgid)

				.list();

		if (list.size() > 0)
			return (User) list.get(0);
		return null;
	}

	@Override
	public List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password, Integer orgid) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ? and u.password = ?  and organisation.orgCoy.Id = ? and status=1 and lock=0 and organisation.orgCoy.active=1 and is_deleted=0")
				.setParameter(0, email).setParameter(1, password)
				.setParameter(2, orgid)

				.list();

		// and u.organisation.active=true
		// List<User> list = null;
		System.out.print("list dao size" + list.size());
		return list;
	}

	@Override
	public List<User> checkUserCredentialsByEmailAndPasswordWebAccess(
			String email, String password, Integer orgid) {
		// TODO Auto-generated method stub
		System.out.print("apiorg" + orgid);
		System.out.print("apiemail" + email);
		System.out.print("apipass" + password);

		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ? and u.password = ?  and organisation.orgCoy.Id = ? and status=1 and lock=0 and organisation.orgCoy.active=1 and is_deleted=0 and webaccess=1")
				.setParameter(0, email).setParameter(1, password)
				.setParameter(2, orgid)

				.list();
		// and u.organisation.active=true
		System.out.print("Api" + list.size());
		// List<User> list = null;
		return list;
	}

	@Override
	public List<User> fetchAllByOrganisation(Organisation organisation) {

		// Criteria criteria = this.sessionFactory.getCurrentSession()
		// .createCriteria(User.class)
		//
		// .createAlias("organisation", "organisation")
		// .add(Restrictions.eq("organisation.id", organisation.getId()))
		// .addOrder(Order.desc("userId"));

		// .createAlias("userType", "userType")
		// .add(Restrictions.ne("userType.name", UserType.SYSTEM_USER));

		// List list =
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return null;
	}

	@Override
	public List<Organisation> getOrganisationByEmail(String email) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.username = ?  and is_deleted=0  and organisation.orgCoy.active=1")
				.setParameter(0, email).list();
		if (list.size() > 0)

			return list;

		return null;

	}

}
