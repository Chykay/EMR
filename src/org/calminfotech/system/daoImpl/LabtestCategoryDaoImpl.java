package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.LabtestCategoryDao;
import org.calminfotech.system.forms.LabtestCategoryForm;
import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestCategoryList;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LabtestCategoryDaoImpl implements LabtestCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;


	@Override
	public List<LabtestCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub
		
		List<LabtestCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from LabtestCategoryVw  where organisation_id=? and isDeleted=false ORDER BY Labtest_category_id DESC")
				 .setParameter(0, id)
				  .setMaxResults(50)
				   .list();
				   
		return  list;
		//organisation_id=? and isDeleted=false
	}
	
	
	
	@Override
	public List<LabtestCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<LabtestCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from LabtestCategoryVw v where v.organisation_id=? and v.isDeleted=false Order by v.categoryId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}

	@Override
	public LabtestCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<LabtestCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from LabtestCategory where categoryId = ? and organisation_id=? and is_deleted=false")
                .setParameter(0, categoryId)
		 .setParameter(1, this.userIdentity.getOrganisation().getId()).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(LabtestCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(LabtestCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(LabtestCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}
/*
	@Override
	public List<LabtestCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try{

			Query query = sessionFactory.getCurrentSession().createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(LabtestCategoryOuterRecursive.class);
			
			 List<LabtestCategoryOuterRecursive> list = query.list();
						 
					return  list;
					
			}catch (Exception e){
				
				e.printStackTrace();
			}
			 
			return null;
		}
*/
	@Override
	public List<LabtestCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<LabtestCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		List<LabtestCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from LabtestCategoryList where organisation_id=?")
				   .setParameter(0, organisationid)
				   .list();
				   
		return list;
	}

	
	
	
	@Override
	public List<LabtestCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
		//
		//TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(LabtestCategory.class)
				//	.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisationId", this.userIdentity.getOrganisation().getId()))
				.add(Restrictions.eq("labtestTypeId.labtestTypeId",cattypeid))
				.add(Restrictions.eq("isDeleted", false))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<LabtestCategory> list = criteria.list();
		System.out.println("dddd");
		System.out.println(list.size());
		
		/*
		
		List<LabtestCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from LabtestCategory where organisation_id=? and ")
				   .setParameter(0, organisationid)
				   .list();
		
		*/
		System.out.println("Ajax Size");
		System.out.println(list.size());
		System.out.println("Organisation");
		System.out.println(this.userIdentity.getOrganisation().getId());

		
		if (list.size() > 0) 
		return list;
		return null;
	}
	
	/*
	List list = this.sessionFactory.getCurrentSession()
			.createCriteria(Consultation.class)
			.createAlias("organisation", "organisation")
			.add(Restrictions.eq("organisation.id", organisation.getId()))
			.addOrder(Order.asc("createDate"))
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	System.out.println(list.size()); */
}
