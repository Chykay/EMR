package org.calminfotech.hrunit.daoImpl;

import java.util.List;

import org.calminfotech.hrunit.daoInterface.HrunitCategoryDao;
import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitCategoryList;
//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HrunitCategoryDaoImpl implements HrunitCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;


	@Override
	public List<HrunitCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub
		
		List<HrunitCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from HrunitCategoryVw  where organisationId = ? and isDeleted=false ORDER BY Hrunit_category_id DESC")
				 .setParameter(0, id)
				  .setMaxResults(50)
				   .list();
				   
		return  list;
		//organisationId = ? and isDeleted=false
	}
	
	
	
	@Override
	public List<HrunitCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<HrunitCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from HrunitCategoryVw v where v.organisationId = ? and v.isDeleted=false Order by v.categoryId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}

	@Override
	public HrunitCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<HrunitCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from HrunitCategory where categoryId = ? and organisationId=? and is_deleted=false")
                .setParameter(0, categoryId)
		        .setParameter(1, this.userIdentity.getOrganisation().getId())
		 .list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(HrunitCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(HrunitCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(HrunitCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}
/*
	@Override
	public List<HrunitCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try{

			Query query = sessionFactory.getCurrentSession().createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(HrunitCategoryOuterRecursive.class);
			
			 List<HrunitCategoryOuterRecursive> list = query.list();
						 
					return  list;
					
			}catch (Exception e){
				
				e.printStackTrace();
			}
			 
			return null;
		}
*/
	@Override
	public List<HrunitCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<HrunitCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		
		List<HrunitCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from HrunitCategoryList where organisationId = ?")
				   .setParameter(0, organisationid)
				   .list();
				   
		return list;
		}


	
	public List<HrunitCategory>  fetchAllByOrganisationbyqueue() {
		
	List<HrunitCategory> list = sessionFactory.getCurrentSession()
			   .createQuery("from HrunitCategory where organisationId = ? and attendQ = true and (point.id=2 or point.id=3 or point.id=4 ) ")
			   .setParameter(0, userIdentity.getOrganisation().getId())
			   
			   .list();
			   
	return list;
}
	
	
	public List<HrunitCategory>  fetchAllByOrganisationbyqueuebypoint(Integer pointid) {
		
	List<HrunitCategory> list = sessionFactory.getCurrentSession()
		.createQuery("from HrunitCategory where organisationId = ? and attendQ = true and point.id=?")
		.setParameter(0, userIdentity.getOrganisation().getId())
		.setParameter(1, pointid)
		.list();
		return list;
	}
		
	@Override
	public List<HrunitCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
		//
		//TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HrunitCategory.class)
				//	.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisationId", this.userIdentity.getOrganisation().getId()))
				.add(Restrictions.eq("hrunitTypeId.hrunitTypeId",cattypeid))
				.add(Restrictions.eq("isDeleted", false))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<HrunitCategory> list = criteria.list();
		System.out.println("dddd");
		System.out.println(list.size());
		
		/*
		
		List<HrunitCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from HrunitCategory where organisationId = ? and ")
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



	@Override
	public List<HrunitCategoryList> fetchAllCategoryList(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<HrunitCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		List<HrunitCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from HrunitCategoryVw v where v.organisationId = ?  Order by v.categoryId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
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
