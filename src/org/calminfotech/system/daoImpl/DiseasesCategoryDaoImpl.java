package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.DiseasesCategoryDao;
import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesCategoryList;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseasesCategoryDaoImpl implements DiseasesCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;


	@Override
	public List<DiseasesCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub
		
		List<DiseasesCategory> list = sessionFactory.getCurrentSession()
				  // .createQuery("from DiseasesCategoryVw  where organisation_id=? and isDeleted=false ORDER BY Diseases_category_id DESC")
				 .createQuery("from DiseasesCategoryVw  where  isDeleted=false ORDER BY Diseases_category_id DESC")
				
				//   .setParameter(0, id)
				  .setMaxResults(50)
				   .list();
				   
		return  list;
		//organisation_id=? and isDeleted=false
	}
	
	
	
	@Override
	public List<DiseasesCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub
		
		List<DiseasesCategory> list = sessionFactory.getCurrentSession()
				  // .createQuery("from DiseasesCategoryVw v where v.organisation_id=? and v.isDeleted=false Order by v.categoryId DESC")
				.createQuery("from DiseasesCategoryVw v   where v.isDeleted=false Order by v.categoryId DESC")
				
				//.setParameter(0, organisationid)
				   .list();
				   
		return list;
		
	
	}

	@Override
	public DiseasesCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<DiseasesCategory> list = sessionFactory.getCurrentSession()
                //.createQuery("from DiseasesCategory where categoryId = ? and organisation_id=? and is_deleted=false")
				.createQuery("from DiseasesCategory where categoryId = ?  and is_deleted=false")
	              
				.setParameter(0, categoryId)
		 //.setParameter(1, this.userIdentity.getOrganisation().getId())
		 .list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}

	@Override
	public void save(DiseasesCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(category);
		
	}

	@Override
	public void update(DiseasesCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(DiseasesCategory category) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(category);
	}
/*
	@Override
	public List<DiseasesCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try{

			Query query = sessionFactory.getCurrentSession().createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(DiseasesCategoryOuterRecursive.class);
			
			 List<DiseasesCategoryOuterRecursive> list = query.list();
						 
					return  list;
					
			}catch (Exception e){
				
				e.printStackTrace();
			}
			 
			return null;
		}
*/
	@Override
	public List<DiseasesCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<DiseasesCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		List<DiseasesCategoryList> list = sessionFactory.getCurrentSession()
				  // .createQuery("from DiseasesCategoryList where organisation_id=?")
				    .createQuery("from DiseasesCategoryList")
				 
				   // .setParameter(0, organisationid)
				   .list();
				   
		return list;
	}

	
	
	
	@Override
	public List<DiseasesCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
		//
		//TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(DiseasesCategory.class)
				//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisationId", this.userIdentity.getOrganisation().getId()))
				.add(Restrictions.eq("diseasesTypeId.diseasesTypeId",cattypeid))
				.add(Restrictions.eq("isDeleted", false))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DiseasesCategory> list = criteria.list();
		System.out.println("dddd");
		System.out.println(list.size());
		
		/*
		
		List<DiseasesCategory> list = sessionFactory.getCurrentSession()
				   .createQuery("from DiseasesCategory where organisation_id=? and ")
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
