package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.daoInterface.GlobalItemUnitofMeasureDao;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemRanking;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class GlobalItemUnitofMeasureDaoImpl implements
		GlobalItemUnitofMeasureDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemBo glomaitembo;

	@Override
	public List<GlobalItemUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemUnitofMeasure> list = sessionFactory
				.getCurrentSession()
				// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and organisation_id=?")
				.createQuery(
						"from GlobalItemUnitofMeasure where is_deleted = 0")

				// .setParameter(0, userIdentity.getOrganisation().getId())
				.list();
		return list;
	}

	@Override
	public GlobalItemUnitofMeasureVw getGlobalItemUnitofMeasureByIdvw(int id) {
		// TODO Auto-generated method stub
		List<GlobalItemUnitofMeasureVw> list = sessionFactory
				.getCurrentSession()
				.createQuery("from GlobalItemUnitofMeasureVw where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (GlobalItemUnitofMeasureVw) list.get(0);
		return null;
	}

	@Override
	public GlobalItemUnitofMeasure getGlobalItemUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
		List<GlobalItemUnitofMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemUnitofMeasure where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (GlobalItemUnitofMeasure) list.get(0);
		return null;
	}

	@Override
	public void save(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(globalItemUnitofMeasure);
	}

	@Override
	public void delete(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(globalItemUnitofMeasure);
	}

	@Override
	public void update(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(globalItemUnitofMeasure);
	}

	@Override
	public Boolean IsExist(int itemid, int uomid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 24 5 67 general
		// 138 personalised

		GlobalItem gitem = this.glomaitembo.getGlobalItemById(itemid);
		if (gitem.getGlobalitemtype().getGlobalitemTypeId() == 1
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 2
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 3
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 8) {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and organisation_id=? ")
					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and unitofmeasure.id= ? and organisation.orgCoy.Id=? ")

					.setParameter(0, itemid)
					.setParameter(1, uomid)

					.setParameter(2,
							userIdentity.getOrganisation().getOrgCoy().getId())

					.list();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} else {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and unitofmeasure.id= ?   ")
					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? ")

					.setParameter(0, itemid).setParameter(1, uomid)

					// .setParameter(1, userIdentity.getOrganisation().getId())
					.list();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		}
	}

	@Override
	public List<GlobalItemUnitofMeasure> fetchAllByItemId(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 24 5 67 general
		// 138 personalised

		GlobalItem gitem = this.glomaitembo.getGlobalItemById(id);

		if (gitem.getGlobalitemtype().getGlobalitemTypeId() == 1
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 2
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 3
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 8) {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and organisation_id=? ")
					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and organisation.orgCoy.Id=? ")

					.setParameter(0, id)
					.setParameter(1,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();
			return list;
		} else {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =?  ")
					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? ")

					.setParameter(0, id)
					// .setParameter(1, userIdentity.getOrganisation().getId())
					.list();
			return list;

		}
	}

	@Override
	public List<GlobalItemUnitofMeasure> fetchAllByItemIdprescribe(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 24 5 67 general
		// 138 personalised

		GlobalItem gitem = this.glomaitembo.getGlobalItemById(id);
		if (gitem.getGlobalitemtype().getGlobalitemTypeId() == 1
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 2
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 3
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 8) {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and organisation_id=? ")
					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? and is_prescribe=1 and organisation.orgCoy.Id=?")

					.setParameter(0, id)
					.setParameter(1,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();
			return list;
		} else {
			List<GlobalItemUnitofMeasure> list = sessionFactory
					.getCurrentSession()

					.createQuery(
							"from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =?   and is_prescribe=1 ")
					// .createQuery("from GlobalItemUnitofMeasure where is_deleted = 0 and globalitem_id =? ")

					.setParameter(0, id)
					// .setParameter(1, userIdentity.getOrganisation().getId())
					.list();
			return list;

		}
	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvw(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		GlobalItem gitem = this.glomaitembo.getGlobalItemById(id);
		if (gitem.getGlobalitemtype().getGlobalitemTypeId() == 1
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 2
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 3
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 8) {
			List<GlobalItemUnitofMeasureVw> list = sessionFactory
					.getCurrentSession()
					// .createQuery("from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =? and organisation_id=? ")
					.createQuery(
							"from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?  and organisation.orgCoy.Id=?")

					.setParameter(0, id)
					.setParameter(1,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();

			return list;
		} else {

			List<GlobalItemUnitofMeasureVw> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?  ")
					// .createQuery("from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?  ")

					.setParameter(0, id)
					// .setParameter(1, userIdentity.getOrganisation().getId())
					.list();
			return list;
		}

	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvwprescribe(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		GlobalItem gitem = this.glomaitembo.getGlobalItemById(id);
		if (gitem.getGlobalitemtype().getGlobalitemTypeId() == 1
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 2
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 3
				|| gitem.getGlobalitemtype().getGlobalitemTypeId() == 8) {
			List<GlobalItemUnitofMeasureVw> list = sessionFactory
					.getCurrentSession()
					// .createQuery("from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =? and organisation_id=? ")
					.createQuery(
							"from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?  and is_prescribe=1  and organisation.orgCoy.Id=?")

					.setParameter(0, id)
					.setParameter(1,
							userIdentity.getOrganisation().getOrgCoy().getId())
					.list();

			return list;
		} else {

			List<GlobalItemUnitofMeasureVw> list = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?   and is_prescribe=1  ")
					// .createQuery("from GlobalItemUnitofMeasureVw where is_deleted = 0 and globalitem_id =?  ")

					.setParameter(0, id)
					// .setParameter(1, userIdentity.getOrganisation().getId())
					.list();
			return list;
		}

	}

	@Override
	public List<GlobalItemRanking> fetchAllRankingByItemId(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<GlobalItemRanking> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GlobalItemRanking where is_deleted = 0 and globalitem_id =? and organisation.orgCoy.Id=? ")
				.setParameter(0, id)
				.setParameter(1,
						userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return list;
	}

	@Override
	public GlobalItemRanking getGlobalItemRankingById(int id) {
		// TODO Auto-generated method stub
		List<GlobalItemRanking> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemRanking where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (GlobalItemRanking) list.get(0);
		return null;
	}

	@Override
	public void save(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(globalItemRanking);
	}

	@Override
	public void delete(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(globalItemRanking);
	}

	@Override
	public void update(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(globalItemRanking);
	}

}
