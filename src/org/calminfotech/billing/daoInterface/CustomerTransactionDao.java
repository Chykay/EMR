package org.calminfotech.billing.daoInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.models.CustomerTransaction;

public interface CustomerTransactionDao {

	public List<CustomerTransaction> fetchAllByOrganisationbyform(
			Date startdate, Date enddate);

	public CustomerTransaction getCustomerTransactionById(int id);

	public void save(CustomerTransaction CustomerTransaction);

	public void delete(CustomerTransaction CustomerTransaction);

	public void update(CustomerTransaction CustomerTransaction);

	public List<CustomerTransaction> fetchAllByOrganisation50();

	public List<CustomerTransaction> fetchAllByOrganisation();

}
