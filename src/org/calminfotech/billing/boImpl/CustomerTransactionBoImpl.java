package org.calminfotech.billing.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.boInterface.CustomerTransactionBo;
import org.calminfotech.billing.daoInterface.CustomerTransactionDao;
import org.calminfotech.billing.models.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import org.calminfotech.system.daoInterface.BillingSchemeDao;
 import org.calminfotech.system.forms.BillingSchemeForm;
 import org.calminfotech.system.models.BillingScheme;
 import org.calminfotech.user.utils.UserIdentity;*/

@Service
@Transactional
public class CustomerTransactionBoImpl implements CustomerTransactionBo {

	/*
	 * @Autowired private UserIdentity userIdentity;
	 */

	@Autowired
	private CustomerTransactionDao customerTranDao;

	@Override
	public List<CustomerTransaction> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation();
	}

	@Override
	public CustomerTransaction getCustomerTransactionById(int id) {
		// TODO Auto-generated method stub
		return customerTranDao.getCustomerTransactionById(id);
	}

	@Override
	public void save(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub
		customerTranDao.save(CustomerTransaction);
	}

	@Override
	public void delete(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CustomerTransaction CustomerTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerTransaction> fetchAllByOrganisationbyform(
			Date startdate, Date enddate) {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisationbyform(startdate, enddate);
	}

	@Override
	public List<CustomerTransaction> fetchAllByOrganisation50() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation50();
	}
	
	@Override
	public List<CustomerTransaction> fetchAllByCustomer(int customer_id) {
		return customerTranDao.fetchAllByCustomer(customer_id);
	}

}