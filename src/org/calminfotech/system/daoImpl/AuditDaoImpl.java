package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.AuditDao;
import org.calminfotech.system.models.Audit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDaoImpl implements AuditDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Audit audit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(audit);
	}

	@Override
	public void update(Audit audit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(audit);

	}

	@Override
	public void delete(Audit audit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(audit);

	}

	@Override
	public Audit getAuditById(int auditId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Audit WHERE auditId = ?")
				.setParameter(0, auditId).list();
		if (list.size() > 0)
			return (Audit) list.get(0);
		return null;
	}

	@Override
	public List<Audit> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Audit order by createdDate desc").setMaxResults(30).list();
		return (List<Audit>) list;
	}

}
