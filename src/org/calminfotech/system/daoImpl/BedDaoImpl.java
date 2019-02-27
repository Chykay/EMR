package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.BedDao;
import org.calminfotech.system.models.Bed;

//import org.calminfotech.system.models.AdmissionRoomOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BedDaoImpl implements BedDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Bed> fetchTop50byOrganisation(int id) {
		
		List<Bed> list = sessionFactory.getCurrentSession()
			   .createQuery("from Bed  where organisation_id=? and isDeleted=false ORDER BY bedId DESC")
			 .setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<Bed> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<Bed> list = sessionFactory.getCurrentSession()
				   .createQuery("from Bed  where organisation_id=?  and  isDeleted=false  ORDER BY bedId DESC")
				 .setParameter(0, organisationid)
				 
				   .list();
				   
		return list;
		
	
	}

	@Override
	public List<Bed> fetchAllByCategory(int catid) {
		// TODO Auto-generated method stub
		
		List<Bed> list = sessionFactory.getCurrentSession()
				   .createQuery("from Bed  where bedCategory.categoryId = ? and organisation_id=? and bedstatus.bedstatus_id=1 and  isDeleted=false ORDER BY bedId DESC")
				 .setParameter(0, catid)
				 .setParameter(1, userIdentity.getOrganisation().getId())
				  
				   .list();
				   
		return list;
		
	
	}
	
	
	
	@Override
	public Bed getBedById(int itemId) {
		// TODO Auto-generated method stub

		List<Bed> list = sessionFactory.getCurrentSession()
				.createQuery("from Bed where bedId = ? ")
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
	public void save(Bed Bed) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(Bed);
	}

	@Override
	public void update(Bed category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(Bed category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<AdmissionRoomOuterRecursive> fetchAllTypesNew() {
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
	public List<Bed> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Bed> list = sessionFactory.getCurrentSession()
				.createQuery("from Bed where organisation_id=? ")
				.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<Bed> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<Bed> list = sessionFactory.getCurrentSession()
				.createQuery("from Bed where organisation_id=? ").setParameter(0, organisationId).list();
		return list;
	}
	

}
