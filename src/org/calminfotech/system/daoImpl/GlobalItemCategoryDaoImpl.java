package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemCategoryDao;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemKind;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.calminfotech.system.models.GlobalCategory;

@Service
public class GlobalItemCategoryDaoImpl implements GlobalItemCategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<GlobalItemCategory> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub

		List<GlobalItemCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemCategoryVw  where  isDeleted=false ORDER BY globalitem_category_id DESC")
				// .setParameter(0, id)
				.setMaxResults(50).list();

		return list;
		// organisation_id=? and isDeleted=false
	}

	@Override
	public List<GlobalItemCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		List<GlobalItemCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemCategoryVw v where  v.isDeleted=false Order by v.categoryId DESC")
				// .setParameter(0, organisationid)
				.list();

		return list;

	}

	@Override
	public GlobalItemCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		List<GlobalItemCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemCategory where categoryId = ? and is_deleted=false")
				.setParameter(0, categoryId).list();
		if (list.size() > 0)
			// .setParameter(1,
			// this.userIdentity.getOrganisation().getId()).list();
			if (list.size() > 0)
				return list.get(0);
		return null;
	}

	@Override
	public void save(GlobalItemCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().save(category);

	}

	@Override
	public void update(GlobalItemCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(GlobalItemCategory category) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public List<GlobalItemCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		try {

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("exec dbo.outerrecursivenew")
					.addEntity(GlobalItemCategoryOuterRecursive.class);

			List<GlobalItemCategoryOuterRecursive> list = query.list();

			return list;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<GlobalItemCategory> fetchAll() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<GlobalItemCategory> fetchAllCatlistByOrganisation(
			int organisationid) {

		List<GlobalItemCategory> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemCategory")
				// .setParameter(0, organisationid)
				.list();

		return list;
	}

	@Override
	public List<GlobalItemCategory> fetchAllByOrganisationByCategoryType(
			Integer cattypeid) {
		//
		// TODO Auto-generated method stub
		List<GlobalItemCategory> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemCategory where GlobalitemTypeId.globalitemTypeId=? and isDeleted=0")
				.setParameter(0, cattypeid).list();

		if (list.size() > 0)
			return list;
		return null;
	}

	@Override
	public List<GlobalItemKind> fetchAllKindByOrganisationByCategoryType(
			Integer cattypeid) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		//
		// TODO Auto-generated method stub
		List<GlobalItemKind> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemKind where globalitemtype.globalitemTypeId=? and isDeleted=0")
				.setParameter(0, cattypeid).list();

		if (list.size() > 0)
			return list;
		return null;

	}

	/*
	 * List list = this.sessionFactory.getCurrentSession()
	 * .createCriteria(Consultation.class) .createAlias("organisation",
	 * "organisation") .add(Restrictions.eq("organisation.id",
	 * organisation.getId())) .addOrder(Order.asc("createDate"))
	 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) .list();
	 * System.out.println(list.size());
	 */

}
