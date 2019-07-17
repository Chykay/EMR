package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeInvoice;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;

public interface BillSchemeInvoiceDao {

	List<BillSchemeInvoice> fetchAll();
	List<BillSchemeInvoice> fetchAllByOrganisation(Organisation organisation);
	void save(BillSchemeInvoice billingInvoice);
	void delete(BillSchemeInvoice billingInvoice);
	void update(BillSchemeInvoice billingInvoice);
	public BillSchemeInvoice getBillingInvoiceById(int id);
	List<BillSchemeInvoice> fetchAllByPatient(Patient patient);

}
