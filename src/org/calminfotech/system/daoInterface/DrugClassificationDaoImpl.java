package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.DrugClassification;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DrugClassificationDaoImpl implements DrugClassificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DrugClassification> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM DrugClassification ORDER BY name").list();
		return list;
	}

	@Override
	public DrugClassification getDrugClassificationById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM DrugClassification WHERE id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (DrugClassification) list.get(0);
		return null;
	}

	@Override
	public void save(DrugClassification drugClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(drugClassification);
	}

	@Override
	public void delete(DrugClassification drugClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(drugClassification);
	}

	@Override
	public void update(DrugClassification drugClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(drugClassification);
	}

}
