package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.SettingDao;
import org.calminfotech.system.models.GetsettingsAssignmentProc;
import org.calminfotech.system.models.Setting;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SettingDaoImpl implements SettingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public Setting getSetting() {
		// TODO Auto-generated method stub
		List<Setting> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Setting").list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	// Create the record or update it. There should be only one object for
	// setting
	@Override
	public void update(Setting setting) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(setting);
	}

	@Override
	public List<GetsettingsAssignmentProc> fectallbyorg(Integer orgid) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("I am in Dao");

			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetSettingsProc")
					.setParameter("orgid", orgid);

			System.out.println("the size is" + query.list().size());
			List<GetsettingsAssignmentProc> result = query.list();
			System.out.println("the size is" + query.list().size());
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			// /sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

	public void save(SettingsAssignment settingsassignment,
			SettingsAssignment_log settingsassignment_log) {
		this.sessionFactory.getCurrentSession().save(settingsassignment);
		System.out.println("Saved");
		// System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		// System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		// System.out.println("clear");

		this.sessionFactory.getCurrentSession().save(settingsassignment_log);
		System.out.println("Saved");
		// System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		// System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		/* sessionFactory.getCurrentSession().close(); */
	}

	@Override
	public void deleteassingment(Integer orgid) {
		// TODO Auto-generated method stub
		/*
		 * try { Query query = sessionFactory.getCurrentSession()
		 * .getNamedQuery("spGetSettingsdeleteProc") .setParameter("orgid",
		 * orgid); List result = query.list();
		 * 
		 * } catch (Exception ex) { ex.printStackTrace();
		 * System.out.println("Error for parameters is: " + ex.getMessage()); }
		 */

		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()

		.createQuery("delete from  SettingsAssignment where company_id = ?")
				.setParameter(0, orgid);

		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public SettingsAssignment fetchsettings(String code, Integer orgId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		List<SettingsAssignment> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from SettingsAssignment where settings_code = ? and company_id=?")
				.setParameter(0, code)
				.setParameter(1, orgId)
				.list();

		if (list.size() > 0)
			return list.get(0);
		
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<String> fetchAllGLSettings(int company_id) {
		
		List<String> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select settings_value from SettingsAssignment where company_id=?")
				.setParameter(0, company_id)
				.list();

		if (list.size() > 0)
			return list;
		
		return null;

	}

	@Override
	public void update(SettingsAssignment settingsAssignment) {
		 this.sessionFactory.getCurrentSession().saveOrUpdate(settingsAssignment);
	}

}
