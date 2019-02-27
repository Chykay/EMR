package org.calminfotech.system.daoImpl;

import java.util.List;
import java.util.Map;

import org.calminfotech.system.daoInterface.ExaminationResultSetupDao;
import org.calminfotech.system.models.ExaminationResultSetup;

//import org.calminfotech.system.models.ExaminationResultSetupCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExaminationResultSetupDaoImpl implements ExaminationResultSetupDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<ExaminationResultSetup> fetchTop50byOrganisation(int id) {
		
		List<ExaminationResultSetup> list = sessionFactory.getCurrentSession()
			   .createQuery("from ExaminationResultSetup  where organisation_id=? and isDeleted=false ORDER BY id DESC")
			 .setParameter(0, id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}
	
	
	
	@Override
	public List<ExaminationResultSetup> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<ExaminationResultSetup> list = sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationResultSetup  where organisation_id=? and isDeleted=false ORDER BY id DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}


	
	
	
	@Override
	public ExaminationResultSetup getExaminationResultSetupById(int itemId) {
		// TODO Auto-generated method stub

		List<ExaminationResultSetup> list = sessionFactory.getCurrentSession()
				.createQuery("from ExaminationResultSetup where id = ? ")
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
	public void save(ExaminationResultSetup ExaminationResultSetup) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(ExaminationResultSetup);
	}

	@Override
	public void update(ExaminationResultSetup category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(ExaminationResultSetup category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*@Override
	public List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypesNew() {
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
	public List<ExaminationResultSetup> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<ExaminationResultSetup> list = sessionFactory.getCurrentSession()
				.createQuery("from ExaminationResultSetup where organisation_id=? ")
				.setParameter(0, organisationId)
				.list();
		return list;
	}

	public List<ExaminationResultSetup> fetchAllCategoryByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		List<ExaminationResultSetup> list = sessionFactory.getCurrentSession()
				.createQuery("from ExaminationResultSetup where organisation_id=? ").setParameter(0, organisationId).list();
		return list;
	}
	

}
