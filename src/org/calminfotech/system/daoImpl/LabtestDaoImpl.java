package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.LabtestDao;
import org.calminfotech.system.models.Labtest;

//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LabtestDaoImpl implements LabtestDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Labtest> fetchTop50byOrganisation(int id) {
		
		List<Labtest> list = sessionFactory.getCurrentSession()
			   .createQuery("from Labtest  where  isDeleted=false ORDER BY labtestId DESC")
			 //.setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<Labtest> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<Labtest> list = sessionFactory.getCurrentSession()
				   .createQuery("from Labtest  where  isDeleted=false ORDER BY labtestId DESC")
				// .setParameter(0, organisationid)
				   .list();
				   //organisation_id=? and
		return list;
		
	
	}


	
	
	
	@Override
	public Labtest getLabtestById(int itemId) {
		// TODO Auto-generated method stub

		List<Labtest> list = sessionFactory.getCurrentSession()
				.createQuery("from Labtest where labtestId = ? ")
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
	public void save(Labtest Labtest) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(Labtest);
	}

	@Override
	public void update(Labtest category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(Labtest category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<LabtestCategoryOuterRecursive> fetchAllTypesNew() {
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
	public List<Labtest> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Labtest> list = sessionFactory.getCurrentSession()
				.createQuery("from Labtest  where  isDeleted=false")
			//	.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<Labtest> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Labtest> list = sessionFactory.getCurrentSession()
				.createQuery("from Labtest where  isDeleted=false")
				//.setParameter(0, organisationId)
				.list();
		return list;
	}
	

}
