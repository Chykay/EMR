package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.VwGlobalItemUnitofMeasureDao;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class VwGlobalItemUnitofMeasureDaoImpl implements
		VwGlobalItemUnitofMeasureDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemUnitofMeasureVw> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwGlobalItemUnitofMeasure ").list();
		return list;
	}

	@Override
	public GlobalItemUnitofMeasureVw getVwGlobalItemUnitofMeasureById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwGlobalItemUnitofMeasure where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0) {
			return (GlobalItemUnitofMeasureVw) list.get(0);
		}
		return null;
	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllById(int id) {
		// TODO Auto-generated method stub
		List<GlobalItemUnitofMeasureVw> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwGlobalItemUnitofMeasure where globalitem_item_id = ? ")
				.setParameter(0, id).list();
		
		return list;
	}

}
