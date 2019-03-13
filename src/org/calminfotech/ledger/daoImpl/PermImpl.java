package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.models.GPermission;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PermImpl {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<GPermission> fetchAll() {
		List<GPermission> gPermissions = sessionFactory.getCurrentSession()
					  .createQuery("from GPermission")
					  .list();
		return gPermissions;
	}
	

	@SuppressWarnings({ "unchecked", "null" })
	public List<GPermission> children(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		List<GPermission> gPermissions = null;
		
		// Get All GPermissions
		
		SQLQuery query = session.createSQLQuery("select * from GPermission where parent_id = '" + id + "'");
		List<Object[]> rows = query.list();
		
		for(Object[] row : rows){
			GPermission permission = new GPermission();
			
			permission.setCode(row[0].toString());
			permission.setName(row[1].toString());
			permission.setParent_id(row[2].toString());
			
			gPermissions.add(permission);
		}
		
		return gPermissions;
	}
}
