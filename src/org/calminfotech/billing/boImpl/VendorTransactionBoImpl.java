package org.calminfotech.billing.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.boInterface.VendorTransactionBo;
import org.calminfotech.billing.daoInterface.VendorTransactionDao;
import org.calminfotech.billing.models.VendorTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import org.calminfotech.system.daoInterface.BillingSchemeDao;
 import org.calminfotech.system.forms.BillingSchemeForm;
 import org.calminfotech.system.models.BillingScheme;
 import org.calminfotech.user.utils.UserIdentity;*/

@Service
@Transactional
public class VendorTransactionBoImpl implements VendorTransactionBo {

	/*
	 * @Autowired private UserIdentity userIdentity;
	 */

	@Autowired
	private VendorTransactionDao customerTranDao;

	@Override
	public List<VendorTransaction> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation();
	}

	@Override
	public VendorTransaction getVendorTransactionById(int id) {
		// TODO Auto-generated method stub
		return customerTranDao.getVendorTransactionById(id);
	}

	@Override
	public void save(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub
		customerTranDao.save(VendorTransaction);
	}

	@Override
	public void delete(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(VendorTransaction VendorTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VendorTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate) {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisationbyform(startdate, enddate);
	}

	@Override
	public List<VendorTransaction> fetchAllByOrganisation50() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation50();
	}

	@Override
	public void save(org.calminfotech.inventory.models.VendorTransaction custtran) {
		// TODO Auto-generated method stub
		
	}

}