package org.calminfotech.billing.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.billing.boInterface.HmoTransactionBo;
import org.calminfotech.billing.daoInterface.HmoTransactionDao;
import org.calminfotech.hmo.models.HmoTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import org.calminfotech.system.daoInterface.BillingSchemeDao;
 import org.calminfotech.system.forms.BillingSchemeForm;
 import org.calminfotech.system.models.BillingScheme;
 import org.calminfotech.user.utils.UserIdentity;*/

@Service
@Transactional
public class HmoTransactionBoImpl implements HmoTransactionBo {

	/*
	 * @Autowired private UserIdentity userIdentity;
	 */

	@Autowired
	private HmoTransactionDao customerTranDao;

	@Override
	public List<HmoTransaction> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation();
	}

	@Override
	public HmoTransaction getHmoTransactionById(int id) {
		// TODO Auto-generated method stub
		return customerTranDao.getHmoTransactionById(id);
	}

	@Override
	public void save(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub
		customerTranDao.save(HmoTransaction);
	}

	@Override
	public void delete(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(HmoTransaction HmoTransaction) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HmoTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate) {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisationbyform(startdate, enddate);
	}

	@Override
	public List<HmoTransaction> fetchAllByOrganisation50() {
		// TODO Auto-generated method stub
		return customerTranDao.fetchAllByOrganisation50();
	}

	@Override
	public List<HmoTransaction> fetchAllByHMO(int hmo_id) {
		return customerTranDao.fetchAllByHMO(hmo_id);
	}

}