package org.calminfotech.hrunit.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.hrunit.daoInterface.HrunitDao;
import org.calminfotech.hrunit.models.Hrunit;

//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HrunitDaoImpl implements HrunitDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Hrunit> fetchTop50byOrganisation(int id) {
		
		List<Hrunit> list = sessionFactory.getCurrentSession()
			   .createQuery("from Hrunit  where organisationId = ? and isDeleted=false ORDER BY hrunitId DESC")
			 .setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<Hrunit> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<Hrunit> list = sessionFactory.getCurrentSession()
				   .createQuery("from Hrunit  where organisationId = ? and isDeleted=false ORDER BY hrunitId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}


	
	
	
	@Override
	public Hrunit getHrunitById(int itemId) {
		// TODO Auto-generated method stub

		List<Hrunit> list = sessionFactory.getCurrentSession()
				.createQuery("from Hrunit where hrunitId = ? ")
				.setParameter(0, itemId)
				 //.setParameter(1, this.userIdentity.getOrganisation().getId())
				.list();
		System.out.print("ogaooo");
		System.out.print(list.size());
		
		System.out.print("ogaooo");
		
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Hrunit Hrunit) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(Hrunit);
	}

	@Override
	public void update(Hrunit category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(Hrunit category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<HrunitCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub

		try {

			
			 * Query query = sessionFactory.getCurrentSession().createSQLQuery(
			 * "exec dbo.outerrecursivenew") .addEntity(OuterRecursive.class);
			 			String sql = "exec dbo.outerrecursiveproc";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

			for (Object object : data) {
				Map row = (Map) object;
				
				System.out.print("Row Id: " + row.get("rowid"));
				System.out.println(", Names: " + row.get("names"));
			}
			// tx.commit();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}*/

	@Override
	public List<Hrunit> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Hrunit> list = sessionFactory.getCurrentSession()
				.createQuery("from Hrunit where organisationId = ? ")
				.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<Hrunit> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Hrunit> list = sessionFactory.getCurrentSession()
				.createQuery("from Hrunit where organisationId = ? ").setParameter(0, organisationId).list();
		return list;
	}
	

}
