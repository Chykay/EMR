package org.calminfotech.hmo.daoImpl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.HmoDao;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoCode;
import org.calminfotech.system.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoDaoImpl implements HmoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Hmo getHmoById(int id) {
		// TODO Auto-generated method stub
		List<Hmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Hmo where id = ? ORDER BY id DESC")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hmo);
	}

	@Override
	public List<Hmo> fetchAll(Organisation organisationId) {
		// System.out.println("name");
		List<Hmo> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Hmo where organisation.orgCoy.Id = ?  ORDER BY name ASC")
				.setParameter(0, organisationId.getOrgCoy().getId()).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<HmoCode> fetchAllGlobal() {
		// System.out.println("name");
		List<HmoCode> list = sessionFactory
				.getCurrentSession()
				.createQuery("from HmoCode where isactive=1  ORDER BY name ASC")
				.list();
		// .setParameter(0,
		// userIdentityorganisationId.getOrgCoy().getId()).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public void delete(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(hmo);
	}

	@Override
	public void update(Hmo hmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(hmo);
	}

	@Override
	public HmoCode fetchGlobalByCode(String code) {

		List<HmoCode> list = sessionFactory.getCurrentSession()
				.createQuery("from HmoCode where code=?  ORDER BY name ASC")

				.setParameter(0, code).list();
		// userIdentityorganisationId.getOrgCoy().getId()).list();
		// System.out.println(list.get(0).getHmoId());
		return (HmoCode) list.get(0);

	}
}