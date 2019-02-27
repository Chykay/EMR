package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.DiseasesDao;
import org.calminfotech.system.models.Diseases;

//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseasesDaoImpl implements DiseasesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Diseases> fetchTop50byOrganisation(int id) {
		
		List<Diseases> list = sessionFactory.getCurrentSession()
			   .createQuery("from Diseases  where organisation_id=? and isDeleted=false ORDER BY diseasesId DESC")
			 .setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<Diseases> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<Diseases> list = sessionFactory.getCurrentSession()
				   .createQuery("from Diseases  where organisation_id=? and isDeleted=false ORDER BY diseasesId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}


	
	
	
	@Override
	public Diseases getDiseasesById(int itemId) {
		// TODO Auto-generated method stub

		List<Diseases> list = sessionFactory.getCurrentSession()
				.createQuery("from Diseases where diseasesId = ? ")
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
	public void save(Diseases Diseases) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(Diseases);
	}

	@Override
	public void update(Diseases category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(Diseases category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<DiseasesCategoryOuterRecursive> fetchAllTypesNew() {
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
	public List<Diseases> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Diseases> list = sessionFactory.getCurrentSession()
				.createQuery("from Diseases where organisation_id=? ")
				.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<Diseases> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Diseases> list = sessionFactory.getCurrentSession()
				.createQuery("from Diseases where organisation_id=? ").setParameter(0, organisationId).list();
		return list;
	}
	

}
