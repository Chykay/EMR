package org.calminfotech.billing.daoInterface;

import java.util.Date;
import java.util.List;

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
import org.calminfotech.visitqueue.models.Visit;
//import org.calminfotech.visitqueue.models.VisitInvoice;
import org.calminfotech.visitqueue.models.VisitInvoice;

public interface BillingInvoiceDao {

	List<BillingInvoice> fetchAll();

	public void save(BillingInvoice billingInvoice);

	public void save(BillInvoice billingInvoice);

	void delete(BillingInvoice billingInvoice);

	void update(BillingInvoice billingInvoice);

	public BillingInvoice getBillingInvoiceById(int id);

	public BillInvoice getBillInvoiceById(int id);

	List<BillingInvoice> fetchAllByPatient(Patient patient);

	public List<GetPatientHmo> GetPatientHmoList(Integer visitid,
			Organisation orgid);

	public List<GetPatientHmo> GetPatientHmoListByHmo(Integer visitid,
			Integer hmopackageid, Organisation orgid);

	public List<GetItemUnitmeasureHmo> GetItemUnitmeasureHmoList(Integer itemid);

	public GetCurrentDaterange Getcurrentdaterange(Integer visitid,
			Integer itemid, Integer patienthmopackageid);

	public List<GetBillItem> GetBillItembyPointList(Integer visitid,
			Organisation orgid);

	public List<GetBillItem> GetBillItembyparam(Integer visitid,
			Organisation orgid, Integer referenceid, String code,
			Integer itemmeasureid, Integer qty, Integer itemid);

	public List<GetBillAmount> GetBillAmountList(String code, Integer qty,
			Integer itemmeasureid, Integer billschemeid, Integer referenceid);

	// public List<GetBillItem> GetBillItembyparam(Integer referenceid,String
	// code,Integer itemmeasureid,Integer qty,Integer itemid, Organisation
	// orgid);

	List<BillInvoice> fetchAllByOrganisationbyVisit(Visit visit);

	List<BillInvoice> fetchAllByOrganisation(Organisation organisation);

	List<BillInvoice> fetchAllByPatient(Integer pid);

	public List<Invoice> Getinvoicelist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid, Boolean usehmo);

	public Invoice Getinvoice(Integer vst, Integer qty, Integer itemmeasureid,
			Double invamt, Integer unitid, Organisation orgid, Boolean usehmo);

	public List<Chargeto> Getchargetolist(Integer vst, Integer qty,
			Integer itemmeasureid, Double invamt, Integer unitid,
			Organisation orgid);

	public List<BillInvoice> fetchAllByOrganisationbyparam(
			Organisation organisation, Date dat1, Date dat2, Integer hmo);

	public List<BillInvoice> fetchAllByPatientbyparam(Integer pid, Date dat1,
			Date dat2);

	public void update(BillInvoicePayment billpay);

	public void save(BillInvoicePayment billpay);

	public List<BillInvoicePayment> fetchPaymentByOrganisationbyVisit(
			Visit visit);

	BillInvoicePayment getPaymentbyId(Integer id);

	public void update(BillInvoice billingInvoice);


	public VisitInvoice save(VisitInvoice visitinv);

	List<BillInvoice> fetchAllByOrganisationbyVisitMulti(VisitInvoice vstinv);

	List<BillInvoice> fetchAllByOrganisationbyVisitMulti(org.calminfotech.billing.daoInterface.VisitInvoice visit);

	org.calminfotech.billing.daoInterface.VisitInvoice save(
			org.calminfotech.billing.daoInterface.VisitInvoice visitinv);

}
