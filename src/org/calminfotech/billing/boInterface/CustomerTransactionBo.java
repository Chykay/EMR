package org.calminfotech.billing.boInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.models.CustomerTransaction;

public interface CustomerTransactionBo {
	public List<CustomerTransaction> fetchAllByOrganisationbyform(
			Date startdate, Date enddate);

	public CustomerTransaction getCustomerTransactionById(int id);

	public void save(CustomerTransaction CustomerTransaction);

	public void delete(CustomerTransaction CustomerTransaction);

	public void update(CustomerTransaction CustomerTransaction);

	public List<CustomerTransaction> fetchAllByOrganisation50();

	List<CustomerTransaction> fetchAllByOrganisation();

	List<CustomerTransaction> fetchAllByCustomer(int customer_id);
}