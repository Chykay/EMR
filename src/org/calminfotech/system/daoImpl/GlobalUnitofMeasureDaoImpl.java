package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalUnitofMeasureDao;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class GlobalUnitofMeasureDaoImpl implements GlobalUnitofMeasureDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalUnitofMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalUnitofMeasure where is_deleted = 0 order by name").list();
		return list;
	}

	


	@Override
	public void save(GlobalUnitofMeasure globalUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(globalUnitofMeasure);
	}

	@Override
	public void delete(GlobalUnitofMeasure globalUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(globalUnitofMeasure);
	}

	@Override
	public void update(GlobalUnitofMeasure globalUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(globalUnitofMeasure);
	}




	@Override
	public GlobalUnitofMeasure getUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
				List<GlobalUnitofMeasure> list = sessionFactory.getCurrentSession()
						.createQuery("from GlobalUnitofMeasure where id = ?")
						.setParameter(0, id).list();
				if(list.size()>0)
					return (GlobalUnitofMeasure) list.get(0);
				return null;
	}



}
