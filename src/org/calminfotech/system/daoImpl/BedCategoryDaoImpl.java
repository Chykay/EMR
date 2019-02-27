package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.BedCategoryDao;
import org.calminfotech.system.models.BedCategory;
import org.calminfotech.system.models.BedCategoryList;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.calminfotech.system.models.BedCategoryOuterRecursive;

@Repository
public class BedCategoryDaoImpl implements BedCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<BedCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub

		List<BedCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BedCategory  where organisation_id=? and isDeleted=false ORDER BY Bed_category_id DESC")
				.setParameter(0, id).setMaxResults(50).list();

		return list;
		// organisation_id=? and isDeleted=false
	}

	@Override
	public List<BedCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		List<BedCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BedCategory v where organisation_id=? and v.isDeleted=false Order by v.categoryId DESC")
				.setParameter(0, organisationid).list();

		return list;

	}

	@Override
	public BedCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<BedCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BedCategory where categoryId = ? and organisation_id=? and is_deleted=false")
				.setParameter(0, categoryId)
				.setParameter(1, this.userIdentity.getOrganisation().getId())
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BedCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(category);

	}

	@Override
	public void update(BedCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(BedCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	/*
	 * @Override public List<BedCategoryOuterRecursive> fetchAllTypes() { //
	 * TODO Auto-generated method stub try{
	 * 
	 * Query query = sessionFactory.getCurrentSession().createSQLQuery(
	 * "exec dbo.outerrecursivenew")
	 * .addEntity(BedCategoryOuterRecursive.class);
	 * 
	 * List<BedCategoryOuterRecursive> list = query.list();
	 * 
	 * return list;
	 * 
	 * }catch (Exception e){
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return null; }
	 */
	@Override
	public List<BedCategory> fetchAll() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<BedCategoryList> fetchAllCatlistByOrganisation(
			int organisationid) {

		List<BedCategoryList> list = sessionFactory.getCurrentSession()
				.createQuery("from BedCategoryList where organisation_id=?")
				.setParameter(0, organisationid).list();

		return list;
	}

	@Override
	public List<BedCategory> fetchAllByOrganisationByCategoryType(
			Integer cattypeid) {
		//

		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(BedCategory.class)
				// .createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.Id", this.userIdentity
						.getOrganisation().getId()))
				.add(Restrictions.eq("bedTypeId.bedTypeId", cattypeid))
				.add(Restrictions.eq("isDeleted", false))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<BedCategory> list = criteria.list();

		if (list.size() > 0)
			return list;
		return null;
	}

	@Override
	public List<BedCategory> fetchAllByOrganisationByCategoryHR(Integer hr) {
		//
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<BedCategory> list = sessionFactory.getCurrentSession()
				.createQuery("from BedCategory where  organisation_id=?  ")
				// .setParameter(0, hr)
				.setParameter(0, this.userIdentity.getOrganisation().getId())
				.list();

		return list;

	}
}
