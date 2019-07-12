package org.calminfotech.billing.boInterface;

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
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.visitqueue.models.Visit;
//import org.calminfotech.visitqueue.models.VisitInvoice;

public interface BillingInvoiceBo {

	List<BillingInvoice> fetchAll();

	List<BillInvoice> fetchAllByOrganisationbyVisit(Visit visit);

	List<BillInvoice> fetchAllByOrganisation(Organisation org);

	List<BillInvoice> fetchAllByPatient(Integer pid);

	// Map getsum(Visit vst);

	void save(BillingInvoice billingInvoicex);

	void delete(BillingInvoice billingInvoice);

	void update(BillingInvoice billingInvoice);

	public BillingInvoice getBillingInvoiceById(int id);

	public BillInvoice getBillInvoiceById(int id);

	void raiseInvoicePoint(BillingForm billingForm);

	void raiseInvoiceFromMenu(BillingForm billingForm);

	public void save(BillInvoice billingInvoice);

	public void update(BillInvoice billingInvoice);

	BillingInvoice raiseInvoicePoint(Integer billingSchemeId,
			Integer unitofmeasureId, Integer itemId, Integer qty,
			Integer visitId);

	public List<GetPatientHmo> GetPatientHmoList(Integer visitid,
			Organisation orgid);

	public List<GetPatientHmo> GetPatientHmoListByHmo(Integer visitid,
			Integer patienthmopackageid, Organisation orgid);

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

	public void save(BillInvoicePayment billpay);

	public void update(BillInvoicePayment billpay);

	public BillInvoicePayment getPaymentbyId(Integer id);

	public List<BillInvoicePayment> fetchPaymentByOrganisationbyVisit(
			Visit visit);

	public List<BillInvoice> fetchAllByOrganisationbyVisitMulti(VisitInvoice vst);

	public List<BillInvoice> fetchAllByOrganisationbycashier(
			Organisation organisation, Date dat1, Date dat2, Integer cashid);

	VisitInvoice save(VisitInvoice visitinv);

}