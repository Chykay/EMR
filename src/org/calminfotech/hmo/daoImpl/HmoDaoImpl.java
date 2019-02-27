package org.calminfotech.hmo.daoImpl;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.calminfotech.system.forms.DataTableForm;
import org.calminfotech.hmo.daoInterface.HmoDao;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.view.helpers.Button;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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
				.createQuery("from Hmo where id = ? ORDER BY id DESC").setParameter(0, id)
				.list();
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
	public List<Hmo> fetchAll(int organisationId) {
		//System.out.println("name");
		List<Hmo> list = sessionFactory.getCurrentSession()
				.createQuery("from Hmo where organisation_id = ?  ORDER BY id DESC")
		     .setParameter(0,organisationId)
			.list();
		//System.out.println(list.get(0).getHmoId());
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
	
	
}


