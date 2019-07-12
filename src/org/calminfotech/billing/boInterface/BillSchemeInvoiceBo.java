package org.calminfotech.billing.boInterface;

import java.util.List;

import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.billing.models.BillSchemeInvoice;
import org.calminfotech.patient.models.Patient;

public interface BillSchemeInvoiceBo {

	List<BillSchemeInvoice> fetchAll();
	List<BillSchemeInvoice> fetchAllByOrganisation();
	void save(BillSchemeInvoice billingInvoicex);
	void delete(BillSchemeInvoice billingInvoice);
	void update(BillSchemeInvoice billingInvoice);
	public BillSchemeInvoice getBillingInvoiceById(int id);
	void raiseInvoicePoint(BillingForm billingForm);
	void raiseInvoiceFromMenu(BillingForm billingForm);
	
	List<BillSchemeInvoice> fetchAllByPatient(Patient patient);

	
	BillSchemeInvoice raiseInvoicePoint(Integer billingSchemeId,
			Integer unitofmeasureId, Integer itemId, Integer qty,
			Integer sectionId, Integer visitId); 
}