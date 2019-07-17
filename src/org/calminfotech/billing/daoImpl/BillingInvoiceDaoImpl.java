package org.calminfotech.billing.daoImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.daoInterface.BillingInvoiceDao;
import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.billing.models.BillInvoicePayment;
import org.calminfotech.billing.models.BillingInvoice;
import org.calminfotech.billing.models.Chargeto;
import org.calminfotech.billing.models.GetBillAmount;
import org.calminfotech.billing.models.GetBillItem;
import org.calminfotech.billing.models.GetCurrentDaterange;
import org.calminfotech.billing.models.GetItemUnitmeasureHmo;
import org.calminfotech.billing.models.GetPatientHmo;
import org.calminfotech.billing.models.Invoice;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitInvoice;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BillingInvoiceDaoImpl implements BillingInvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<BillingInvoice> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice").list();

		if (list.size() > 0)
			return list;
		return null;
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyVisitMulti(
			VisitInvoice vstinv) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoice where  visitinv  = ? and organisation_id = ? and isDeleted=0 order by duedate ASC")
				.setParameter(0, vstinv)
				.setParameter(1, this.userIdentity.getOrganisation().getId())

				.list();
		return list;
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyVisit(Visit visit) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoice where  visitinv.visit = ? and organisation_id = ? and isDeleted=0 order by id DESC")
				.setParameter(0, visit)
				.setParameter(1, this.userIdentity.getOrganisation().getId())

				.list();
		return list;
	}

	public List<BillInvoicePayment> fetchPaymentByOrganisationbyVisit(
			Visit visit) {
		// TODO Auto-generated method stub
		List<BillInvoicePayment> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoicePayment where  visitinv.visit = ? and organisation_id = ? and isDeleted=0 order by id DESC")
				.setParameter(0, visit)
				.setParameter(1, this.userIdentity.getOrganisation().getId())

				.list();
		return list;
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoice where   organisation.orgCoy.Id = ? and isDeleted=0 order by duedate DESC")
				.setParameter(0, organisation.getOrgCoy().getId())
				.setMaxResults(100)

				.list();
		return list;
	}

	@Override
	public List<BillInvoice> fetchAllByPatient(Integer pid) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoice where   visitinv.visit.patient.patientId = ? and isDeleted=0 order by duedate DESC")
				.setParameter(0, pid).setMaxResults(100).list();
		return list;
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyparam(
			Organisation organisation, Date dat1, Date dat2, Integer hmo) {
		// TODO Auto-generated method stub

		System.out.print("Billinvoicep");

		System.out.print(dat1);
		System.out.print(organisation);
		System.out.print(dat2);
		System.out.print("Billinvoicep");

		if (hmo.intValue() == 0) {
			List<BillInvoice> list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from BillInvoice where   organisation.orgCoy.Id=? and isDeleted=0 and duedate >= ?  and duedate <= ? order by duedate DESC ")
					.setParameter(0, organisation.getOrgCoy().getId())
					.setParameter(1, dat1).setParameter(2, dat2)

					.list();
			return list;
		} else {
			List<BillInvoice> list = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from BillInvoice where   organisation.orgCoy.Id=? and isDeleted=0 and duedate >= ?  and duedate <= ? and hmo.id=?order by duedate DESC ")
					.setParameter(0, organisation.getOrgCoy().getId())
					.setParameter(1, dat1).setParameter(2, dat2)
					.setParameter(3, hmo)

					.list();
			return list;

		}

	}

	@Override
	public List<BillInvoice> fetchAllByPatientbyparam(Integer pid, Date dat1,
			Date dat2) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"from BillInvoice where   visitinv.visit.patient.patientId = ? and isDeleted=0 and duedate >= ?  and duedate <= ? order by duedate DESC")
				.setParameter(0, pid).setParameter(1, dat1)
				.setParameter(2, dat2)

				.list();
		return list;
	}

	@Override
	public void save(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingInvoice);
	}

	@Override
	public VisitInvoice save(VisitInvoice visitinv) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visitinv);
		return visitinv;
	}

	@Override
	public void save(BillInvoice billingInvoice) {
		// TODO Auto-generated method stub

		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();

		this.sessionFactory.getCurrentSession().save(billingInvoice);
	}

	@Override
	public void delete(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingInvoice);
	}

	@Override
	public void update(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingInvoice);
	}

	@Override
	public BillingInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (BillingInvoice) list.get(0);
		return null;
	}

	@Override
	public BillInvoice getBillInvoiceById(int id) {
		// TODO Auto-generated method stub
		List<BillInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillInvoice where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (BillInvoice) list.get(0);
		return null;
	}

	@Override
	public List<BillingInvoice> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where patient = ?")
				.setParameter(0, patient).list();
		if (list.size() > 0)
			return list;
		return null;
	}

	public List<GetPatientHmo> GetPatientHmoList(Integer visitid,
			Organisation orgid) {
		try {

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select *  from GetPatientHmo(?,?)")
					.addEntity(GetPatientHmo.class).setParameter(0, visitid)
					.setParameter(1, orgid.getOrgCoy().getId())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<GetPatientHmo> GetPatientHmoListByHmo(Integer visitid,
			Integer hmopackageid, Organisation orgid) {
		try {

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select *  from GetPatientHmo(?,?) where hmopackageid =?")
					.addEntity(GetPatientHmo.class).setParameter(0, visitid)
					.setParameter(1, orgid.getOrgCoy().getId())
					.setParameter(2, hmopackageid)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<GetItemUnitmeasureHmo> GetItemUnitmeasureHmoList(Integer itemid) {
		try {

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select *  from GetItemUnitmeasureHmo(?,?) where hmopackageid =?")
					.addEntity(GetItemUnitmeasureHmo.class)
					.setParameter(0, itemid)
					// .setParameter(1, orgid)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public GetCurrentDaterange Getcurrentdaterange(Integer visitid,
			Integer itemid, Integer patienthmopackageid)

	{
		try {

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select *  from GetCurrentDaterange(?,?,?) ")
					.addEntity(GetItemUnitmeasureHmo.class)
					.setParameter(0, visitid).setParameter(1, itemid)
					.setParameter(2, patienthmopackageid);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return (GetCurrentDaterange) list.get(0);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<GetBillItem> GetBillItembyPointList(Integer visitid,
			Organisation orgid) {
		try {

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select *  from Getbillitems(?,?) ")
					.addEntity(GetBillItem.class).setParameter(0, visitid)
					.setParameter(1, orgid.getOrgCoy().getId())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();

			System.out.print("gggggg" + list.size());

			return list;

		} catch (Exception e) {
			System.out.print("eeeeee");
			e.printStackTrace();
			return null;
		}
	}

	public List<GetBillAmount> GetBillAmountList(String code, Integer qty,
			Integer itemmeasureid, Integer billschemeid, Integer referenceid) {

		try {

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select *  from GetBillAmount(?,?,?,?,?) ")
					.addEntity(GetBillAmount.class).setParameter(0, code)
					.setParameter(1, qty).setParameter(2, itemmeasureid)
					.setParameter(3, billschemeid).setParameter(4, referenceid)

					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public List<GetBillItem> GetBillItembyparam(Integer visitid,
			Organisation orgid, Integer referenceid, String code,
			Integer itemmeasureid, Integer qty, Integer itemid) {
		try {

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select *  from Getbillitems(?,?) where referenceid=? and code=? and itemmeasureid=? and qty=? and itemid=?")
					.addEntity(GetBillItem.class).setParameter(0, visitid)
					.setParameter(1, orgid.getOrgCoy().getId())
					.setParameter(2, referenceid).setParameter(3, code)
					.setParameter(4, itemmeasureid).setParameter(5, qty)
					.setParameter(6, itemid)

					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Invoice> Getinvoicelist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid, Boolean usehmo) {
		// TODO Auto-generated method stub
		try {

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select *  from invoicelist(?,?,?,?,?,?,?)")
					.addEntity(Invoice.class).setParameter(0, vst)
					.setParameter(1, qty).setParameter(2, itemmeasureid)
					.setParameter(3, invamt).setParameter(4, unitid)
					.setParameter(5, orgid.getOrgCoy().getId())
					.setParameter(6, usehmo);
			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Invoice Getinvoice(Integer vst, Integer qty, Integer itemmeasureid,
			Double invamt, Integer unitid, Organisation orgid, Boolean usecash)

	{
		try

		{

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select *  from getinvoice(?,?,?,?,?,?,?)")
					.addEntity(Invoice.class).setParameter(0, vst)
					.setParameter(1, qty).setParameter(2, itemmeasureid)
					.setParameter(3, invamt).setParameter(4, unitid)
					.setParameter(5, orgid.getOrgCoy().getId())
					.setParameter(6, usecash);

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();

			if (list.size() > 0)
				return (Invoice) list.get(0);
			return null;

		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Chargeto> Getchargetolist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid)
	// TODO Auto-generated method stub

	{
		try

		{

			Query query = sessionFactory.getCurrentSession()
					.createSQLQuery("select *  from chargetolist(?,?,?,?,?,?)")
					.addEntity(Chargeto.class).setParameter(0, vst)
					.setParameter(1, qty).setParameter(2, itemmeasureid)
					.setParameter(3, invamt).setParameter(4, unitid)
					.setParameter(5, orgid.getOrgCoy().getId());

			// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			return list;

		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(BillInvoicePayment billpay) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billpay);
	}

	@Override
	public void save(BillInvoicePayment billpay) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billpay);
	}

	@Override
	public BillInvoicePayment getPaymentbyId(Integer id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<BillInvoicePayment> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillInvoicePayment where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (BillInvoicePayment) list.get(0);
		return null;
	}

	@Override
	public void update(BillInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingInvoice);
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyVisitMulti(
			org.calminfotech.billing.daoInterface.VisitInvoice visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.calminfotech.billing.daoInterface.VisitInvoice save(
			org.calminfotech.billing.daoInterface.VisitInvoice visitinv) {
		// TODO Auto-generated method stub
		return null;
	}

}
