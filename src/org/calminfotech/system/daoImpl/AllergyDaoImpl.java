package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.AllergyDao;
import org.calminfotech.system.models.Allergy;

//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllergyDaoImpl implements AllergyDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Allergy> fetchTop50byOrganisation(int id) {
		
		List<Allergy> list = sessionFactory.getCurrentSession()
			   .createQuery("from Allergy  where organisation_id=? and isDeleted=false ORDER BY allergyId DESC")
			 .setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<Allergy> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<Allergy> list = sessionFactory.getCurrentSession()
				   .createQuery("from Allergy  where organisation_id=? and isDeleted=false ORDER BY allergyId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}


	
	
	
	@Override
	public Allergy getAllergyById(int itemId) {
		// TODO Auto-generated method stub

		List<Allergy> list = sessionFactory.getCurrentSession()
				.createQuery("from Allergy where allergyId = ? ")
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
	public void save(Allergy Allergy) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(Allergy);
	}

	@Override
	public void update(Allergy category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(Allergy category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<AllergyCategoryOuterRecursive> fetchAllTypesNew() {
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
	public List<Allergy> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Allergy> list = sessionFactory.getCurrentSession()
				.createQuery("from Allergy where organisation_id=? ")
				.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<Allergy> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Allergy> list = sessionFactory.getCurrentSession()
				.createQuery("from Allergy where organisation_id=? ").setParameter(0, organisationId).list();
		return list;
	}
	

}
