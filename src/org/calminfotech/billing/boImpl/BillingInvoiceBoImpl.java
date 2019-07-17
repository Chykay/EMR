package org.calminfotech.billing.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillingInvoiceBo;
import org.calminfotech.billing.boInterface.VisitInvoice;
import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDetailsDao;
import org.calminfotech.billing.daoInterface.BillingInvoiceDao;
import org.calminfotech.billing.models.BillInvoice;
import org.calminfotech.billing.models.BillInvoicePayment;
import org.calminfotech.billing.models.BillSchemeItemPriceDetails;
import org.calminfotech.billing.models.BillingInvoice;
import org.calminfotech.billing.models.Chargeto;
import org.calminfotech.billing.models.GetBillAmount;
import org.calminfotech.billing.models.GetBillItem;
import org.calminfotech.billing.models.GetCurrentDaterange;
import org.calminfotech.billing.models.GetItemUnitmeasureHmo;
import org.calminfotech.billing.models.GetPatientHmo;
import org.calminfotech.billing.models.Invoice;
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.models.Visit;
//import org.calminfotech.visitqueue.models.VisitInvoice;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.system.boInterface.LoginSectionBo;
//import org.calminfotech.views.boInterface.VwItemBo;
//import org.calminfotech.views.models.VwItem;

@Service
public class BillingInvoiceBoImpl implements BillingInvoiceBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillingInvoiceDao billingInvoiceDao;

	@Autowired
	private VisitBo visitBo;

	// @Autowired
	// private LoginSectionBo sectionBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Autowired
	private GlobalItemBo itemBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired(required = false)
	private BillSchemeBo billingSchemeBo;

	@Autowired
	private BillSchemeItemPriceDetailsDao billingSchemePriceDetails;

	@Override
	public List<BillingInvoice> fetchAll() {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAll();
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyVisit(Visit visit) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByOrganisationbyVisit(visit);
	}

	public List<BillInvoice> fetchAllByOrganisationbyVisitMulti(
			//VisitInvoice visit
			) {
				return null;
		// TODO Auto-generated method stub
		//return this.billingInvoiceDao.fetchAllByOrganisationbyVisitMulti(visit);
	}

	@Override
	public List<BillInvoicePayment> fetchPaymentByOrganisationbyVisit(
			Visit visit) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchPaymentByOrganisationbyVisit(visit);
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByOrganisation(organisation);
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyparam(
			Organisation organisation, Date dat1, Date dat2, Integer hmo) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByOrganisationbyparam(
				organisation, dat1, dat2, hmo);
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbycashier(
			Organisation organisation, Date dat1, Date dat2, Integer cashid) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByOrganisationbyparam(
				organisation, dat1, dat2, cashid);
	}

	@Override
	public void save(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.save(billingInvoice);
	}

	@Override
	public void delete(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.delete(billingInvoice);
	}

	@Override
	public void update(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billingInvoiceDao.update(billingInvoice);
	}

	@Override
	public BillingInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.getBillingInvoiceById(id);
	}

	@Override
	public BillInvoice getBillInvoiceById(int id) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.getBillInvoiceById(id);
	}

	@Override
	public List<BillInvoice> fetchAllByPatientbyparam(Integer pid, Date dat1,
			Date dat2) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByPatientbyparam(pid, dat1, dat2);
	}

	@Override
	public void raiseInvoicePoint(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillingInvoice billingInvoice = new BillingInvoice();
		// LoginSection section =
		// this.sectionBo.getLoginSectionById(userIdentity
		// .getSectionId());
		// billingInvoice.setSection(section);
		billingInvoice.setScheme(userIdentity.getBillId());
		Integer frontDesk = 1;
		VisitWorkflowPoint point = this.pointBo.getWorkflowPointById(frontDesk);
		billingInvoice.setPoint(point);
		Visit visit = this.visitBo.getVisitationById(userIdentity.getVisitId());
		billingInvoice.setVisit(visit);
		billingInvoice.setPatient(visit.getPatient());
		GlobalItem item = this.itemBo.getGlobalItemById(billingForm.getItem());
		billingInvoice.setItem(item);
		billingInvoice.setQuantity(billingForm.getItemType());
		billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		if (billingForm.getPrice() == null) {
			billingInvoice.setPrice(0.00);
		} else {
			billingInvoice.setPrice(billingForm.getPrice());
		}
		billingInvoice.setStatus("Yet to pay");
		billingInvoice.setOrganisation(userIdentity.getOrganisation());
		billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		this.billingInvoiceDao.save(billingInvoice);
		// flush the util bean
		/*
		 * userIdentity.setBillId(null); userIdentity.setBillName(null);
		 * userIdentity.setCurrentPointId(null);
		 * userIdentity.setCurrentPointName(null);
		 */
	}

	// save by raising bill only from menu
	@Override
	public void raiseInvoiceFromMenu(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillingInvoice billingInvoice = new BillingInvoice();
		billingInvoice.setPatient(patientBo.getPatientById(billingForm
				.getPatientId()));
		// billingInvoice.setSection(sectionBo.getLoginSectionById(billingForm
		// .getSectionId()));
		billingInvoice.setScheme(1);
		billingInvoice.setPoint(pointBo.getWorkflowPointById(billingForm
				.getCurrentPiointId()));
		billingInvoice.setVisit(visitBo.getVisitationById(billingForm
				.getVisitId()));
		billingInvoice.setItem(this.itemBo.getGlobalItemById(billingForm
				.getItem()));
		billingInvoice.setQuantity(billingForm.getItemType());
		billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		// save cost amount
		if (billingForm.getPrice() == null) {
			billingInvoice.setPrice(0.00);
		} else {
			billingInvoice.setPrice(billingForm.getPrice());
		}
		// expected payment
		billingInvoice.setAmountPaid(0.00);
		billingInvoice.setStatus("Yet to pay");
		billingInvoice.setOrganisation(userIdentity.getOrganisation());
		billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		billingInvoiceDao.save(billingInvoice);
	}

	public BillingInvoice raiseInvoicePoint(Integer billingSchemeId,
			Integer unitofmeasureId, Integer itemId, Integer qty,
			Integer sectionId, Integer visitId) {
		String strPrice = "";
		Visit visit = this.visitBo.getVisitationById(visitId);
		BillingInvoice billingInvoice = null;
		/* fetch price */

		// System.out.print(billingSchemeId+"/"+unitofmeasureId+"/"+itemId+"/"+this.userIdentity.getOrganisation().getId());

		List<BillSchemeItemPriceDetails> prices = this.billingSchemePriceDetails
				.getBillingInvoice(billingSchemeId, unitofmeasureId, itemId,
						this.userIdentity.getOrganisation());

		/* check if amount exist */
		if (prices == null) {
			strPrice = "0:00";
		} else {
			for (BillSchemeItemPriceDetails price : prices) {
				strPrice = price.getPrice();
			}
		}
		// save bill
		billingInvoice = new BillingInvoice();
		// billingInvoice
		// .setSection(this.sectionBo.getLoginSectionById(sectionId));
		billingInvoice.setScheme(billingSchemeId);
		billingInvoice.setPoint(this.pointBo.getWorkflowPointById(visit
				.getPoint().getId()));
		/*billingInvoice.setUnitId(String
				.valueOf(visit.getUnit().getCategoryId()));*/
		billingInvoice.setVisit(visit);
		billingInvoice.setPatient(visit.getPatient());
		billingInvoice.setItem(this.itemBo.getGlobalItemById(itemId));
		billingInvoice.setQuantity(String.valueOf(qty));
		// StringBuilder sb = new StringBuilder();
		double i = 0;
		try {
			i = qty * Double.parseDouble(strPrice);
		} catch (NumberFormatException e) {
		}
		// Integer obj = new Integer(i);
		billingInvoice.setPrice(i);
		billingInvoice.setStatus("Yet to pay");
		// billingInvoice.setOrganisation(userIdentity.getOrganisation());
		billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		this.billingInvoiceDao.save(billingInvoice);

		return billingInvoice;

	}

	@Override
	public BillingInvoice raiseInvoicePoint(Integer billingSchemeId,
			Integer unitofmeasureId, Integer itemId, Integer qty,
			Integer visitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GetPatientHmo> GetPatientHmoList(Integer visitid,
			Organisation orgid) {
		return this.billingInvoiceDao.GetPatientHmoList(visitid, orgid);

	}

	@Override
	public List<GetPatientHmo> GetPatientHmoListByHmo(Integer visitid,
			Integer patienthmopackageid, Organisation orgid) {
		return this.billingInvoiceDao.GetPatientHmoListByHmo(visitid,
				patienthmopackageid, orgid);
	}

	public List<GetItemUnitmeasureHmo> GetItemUnitmeasureHmoList(Integer itemid) {
		return this.billingInvoiceDao.GetItemUnitmeasureHmoList(itemid);
	}

	public GetCurrentDaterange Getcurrentdaterange(Integer visitid,
			Integer itemid, Integer patienthmopackageid) {
		return this.billingInvoiceDao.Getcurrentdaterange(visitid, itemid,
				patienthmopackageid);
	}

	public List<GetBillItem> GetBillItembyPointList(Integer visitid,
			Organisation orgid) {
		return this.billingInvoiceDao.GetBillItembyPointList(visitid, orgid);
	}

	public List<GetBillAmount> GetBillAmountList(String code, Integer qty,
			Integer itemmeasureid, Integer billschemeid, Integer referenceid) {
		return this.billingInvoiceDao.GetBillAmountList(code, qty,
				itemmeasureid, billschemeid, referenceid);

	}

	@Override
	public void save(BillInvoice billingInvoice) {
		// TODO Auto-generated method stub
		billingInvoiceDao.save(billingInvoice);
	}

	@Override
	public void save(BillInvoicePayment billpay) {
		// TODO Auto-generated method stub
		billingInvoiceDao.save(billpay);
	}

	/*@Override
	public VisitInvoice save(VisitInvoice visitinv) {
		// TODO Auto-generated method stub
		return billingInvoiceDao.save(visitinv);
	}*/

	@Override
	public void update(BillInvoicePayment billpay) {
		// TODO Auto-generated method stub
		billingInvoiceDao.update(billpay);
	}

	@Override
	public void update(BillInvoice billingInvoice) {
		// TODO Auto-generated method stub
		billingInvoiceDao.update(billingInvoice);
	}

	@Override
	public List<GetBillItem> GetBillItembyparam(Integer visitid,
			Organisation orgid, Integer referenceid, String code,
			Integer itemmeasureid, Integer qty, Integer itemid) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.GetBillItembyparam(visitid, orgid,
				referenceid, code, itemmeasureid, qty, itemid);

	}

	@Override
	public List<Invoice> Getinvoicelist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid, Boolean usehmo) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.Getinvoicelist(vst, qty, itemmeasureid,
				invamt, unitid, orgid, usehmo);

	}

	@Override
	public Invoice Getinvoice(Integer vst, Integer qty, Integer itemmeasureid,
			Double invamt, Integer unitid, Organisation orgid, Boolean usehmo) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.Getinvoice(vst, qty, itemmeasureid,
				invamt, unitid, orgid, usehmo);
	}

	@Override
	public List<Chargeto> Getchargetolist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.Getchargetolist(vst, qty, itemmeasureid,
				invamt, unitid, orgid);
	}

	@Override
	public List<BillInvoice> fetchAllByPatient(Integer pid) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.fetchAllByPatient(pid);
	}

	@Override
	public BillInvoicePayment getPaymentbyId(Integer id) {
		// TODO Auto-generated method stub
		return this.billingInvoiceDao.getPaymentbyId(id);
	}

	@Override
	public org.calminfotech.billing.boInterface.VisitInvoice save(
			org.calminfotech.billing.boInterface.VisitInvoice visitinv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillInvoice> fetchAllByOrganisationbyVisitMulti(VisitInvoice vst) {
		// TODO Auto-generated method stub
		return null;
	}

}
