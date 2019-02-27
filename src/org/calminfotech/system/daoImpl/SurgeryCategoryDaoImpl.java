package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.SurgeryCategoryDao;
import org.calminfotech.system.models.SurgeryCategory;
import org.calminfotech.system.models.SurgeryCategoryList;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;

@Repository
public class SurgeryCategoryDaoImpl implements SurgeryCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;


	@Override
	public List<SurgeryCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub
		
		List<SurgeryCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from SurgeryCategoryVw  where organisation_id=? and isDeleted=false ORDER BY Surgery_category_id DESC")
				 .setParameter(0, id)
				  .setMaxResults(50)
				   .list();
				   
		return  list;
		//organisation_id=? and isDeleted=false
	}
	
	
	
	@Override
	public List<SurgeryCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<SurgeryCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from SurgeryCategoryVw v where v.organisation_id=? and v.isDeleted=false Order by v.categoryId DESC")
				 .setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}

	@Override
	public SurgeryCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<SurgeryCategory> list = sessionFactory.getCurrentSession()
                .createQuery("from SurgeryCategory where categoryId = ? and organisation_id=? and is_deleted=false")
                .setParameter(0, categoryId)
		 .setParameter(1, this.userIdentity.getOrganisation().getId()).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(SurgeryCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(SurgeryCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(SurgeryCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}
/*
	@Override
	public List<SurgeryCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try{

			Query query = sessionFactory.getCurrentSession().createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(SurgeryCategoryOuterRecursive.class);
			
			 List<SurgeryCategoryOuterRecursive> list = query.list();
						 
					return  list;
					
			}catch (Exception e){
				
				e.printStackTrace();
			}
			 
			return null;
		}
*/
	@Override
	public List<SurgeryCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<SurgeryCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		List<SurgeryCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from SurgeryCategoryList where organisation_id=?")
				   .setParameter(0, organisationid)
				   .list();
				   
		return list;
	}

	
	
	
	@Override
	public List<SurgeryCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
		//
		//TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(SurgeryCategory.class)
				//	.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisationId", this.userIdentity.getOrganisation().getId()))
				.add(Restrictions.eq("surgeryTypeId.surgeryTypeId",cattypeid))
				.add(Restrictions.eq("isDeleted", false))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SurgeryCategory> list = criteria.list();
		System.out.println("dddd");
		System.out.println(list.size());
		
		/*
		
		List<SurgeryCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from SurgeryCategory where organisation_id=? and ")
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
