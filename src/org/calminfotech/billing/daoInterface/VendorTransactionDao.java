package org.calminfotech.billing.daoInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.models.VendorTransaction;

public interface VendorTransactionDao {

	public List<VendorTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate);

	public VendorTransaction getVendorTransactionById(int id);

	public void save(VendorTransaction VendorTransaction);

	public void delete(VendorTransaction VendorTransaction);

	public void update(VendorTransaction VendorTransaction);

	public List<VendorTransaction> fetchAllByOrganisation50();

	public List<VendorTransaction> fetchAllByOrganisation();

	public List<VendorTransaction> fetchAllByVendor(int vendor_id);

}
