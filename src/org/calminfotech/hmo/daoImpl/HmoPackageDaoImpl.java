package org.calminfotech.hmo.daoImpl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.HmoPackageDao;
import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.system.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPackageDaoImpl implements HmoPackageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HmoPackage> fetchAll(Organisation organisationId) {
		List<HmoPackage> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackage where organisation.orgCoy.Id=? ")
				.setParameter(0, organisationId.getOrgCoy().getId()).list();

		return list;
	}

	@Override
	public List<HmoPackage> fetchAllForuse(Organisation organisationId) {
		List<HmoPackage> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HmoPackage   where organisation.orgCoy.Id=? and hmostatus.hmostatus_id=1 and hmo.hmostatus.hmostatus_id=1 ")
				.setParameter(0, organisationId.getOrgCoy().getId()).list();

		return list;
	}

	@Override
	public HmoPackage getHmoPackageById(int id) {
		List<HmoPackage> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackage where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void delete(HmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(hmoPackage);

	}

	@Override
	public void save(HmoPackage hmoPackage) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(hmoPackage);
	}

	@Override
	public void update(HmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(hmoPackage);
	}

}
