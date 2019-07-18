package org.calminfotech.billing.boInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.models.VendorTransaction;

public interface VendorTransactionBo {
	public List<VendorTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate);

	public VendorTransaction getVendorTransactionById(int id);

	public void save(VendorTransaction VendorTransaction);

	public void delete(VendorTransaction VendorTransaction);

	public void update(VendorTransaction VendorTransaction);

	public List<VendorTransaction> fetchAllByOrganisation50();

	List<VendorTransaction> fetchAllByOrganisation();

	public void save(org.calminfotech.inventory.models.VendorTransaction custtran);
}