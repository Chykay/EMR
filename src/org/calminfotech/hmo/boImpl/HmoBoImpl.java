package org.calminfotech.hmo.boImpl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.daoInterface.HmoDao;
import org.calminfotech.hmo.forms.HmoForm;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoCode;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BankList;
//import org.calminfotech.utils.HmoTypesList;
import org.calminfotech.utils.HmostatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoBoImpl implements HmoBo {

	@Autowired
	private HmoDao hmoDao;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BankList bankList;

	@Autowired
	private HmostatusList hmostatusList;

	/*@Autowired
	private HmoTypesList hmoTypeBo;*/

	@Autowired
	private Authorize authorize;

	@Autowired
	private Auditor auditor;

	@Autowired
	private BillSchemeBo billSchemeBo;

	@Override
	public List<Hmo> fetchAll(Organisation organisationId) {

		return this.hmoDao.fetchAll(organisationId);
	}

	@Override
	public List<HmoCode> fetchAllGlobal() {

		return this.hmoDao.fetchAllGlobal();
	}

	@Override
	public HmoCode fetchGlobalByCode(String code) {

		return this.hmoDao.fetchGlobalByCode(code);
	}

	@Override
	public Hmo getHmoById(int id) {

		return this.hmoDao.getHmoById(id);
	}

	@Override
	public Hmo save(HmoForm hmoForm) {
		Hmo hmo = new Hmo();
		// hmo.setHmoId(hmoForm.getHmoId());
		if (!hmoForm.getGlobalhmocode().equals("")) {
			hmo.setHmocode(this.hmoDao.fetchGlobalByCode(hmoForm
					.getGlobalhmocode()));
		}
		hmo.setName(hmoForm.getName());
		hmo.setAddress(hmoForm.getAddress());
		hmo.setEmail(hmoForm.getEmail());
		hmo.setPhone(hmoForm.getPhone());
		hmo.setAdminName(hmoForm.getAdmin_name());
		hmo.setAccoutno(hmoForm.getAccountno());
		// if (!hmoForm.getBank_id().equals(""))
		// {
		// hmo.setBank(this.bankList.getBankById(hmoForm.getBank_id()));
		// }
		//hmo.setHmoType(this.hmoTypeBo.getHmoTypeById(hmoForm.getHmotype_id()));
		hmo.setHmostatus(this.hmostatusList.getHmostatusById(hmoForm
				.getStatus_id()));
		/*
		 * hmo.setBillScheme(this.billSchemeBo.getBillSchemeById(hmoForm
		 * .getBillingscheme_id()));
		 */
		hmo.setCreatedBy(userIdentity.getUsername());
		hmo.setOrganisation(userIdentity.getOrganisation());
		hmo.setCreatedDate(new GregorianCalendar().getTime());

		this.hmoDao.save(hmo);
		return hmo;
	}

	@Override
	public void delete(Hmo hmo) {
		this.hmoDao.delete(hmo);
	}

	@Override
	public void update(HmoForm hmoForm) {
		Hmo hmo = this.hmoDao.getHmoById(hmoForm.getId());
		if (!hmoForm.getGlobalhmocode().equals("")) {
			hmo.setHmocode(this.hmoDao.fetchGlobalByCode(hmoForm
					.getGlobalhmocode()));
		} else {
			hmo.setHmocode(null);

		}
		hmo.setName(hmoForm.getName());
		hmo.setAddress(hmoForm.getAddress());
		hmo.setEmail(hmoForm.getEmail());
		hmo.setPhone(hmoForm.getPhone());
		// if (!hmoForm.getBank_id().equals(""))
		// {
		// hmo.setBank(this.bankList.getBankById(hmoForm.getBank_id()));
		// }

		if (authorize.isAllowed("HMO004")) {
			hmo.setHmostatus(this.hmostatusList.getHmostatusById(hmoForm
					.getStatus_id()));
		}

		/*
		 * hmo.setBillScheme(this.billSchemeBo.getBillSchemeById(hmoForm
		 * .getBillingscheme_id()));
		 */
		//hmo.setHmoType(this.hmoTypeBo.getHmoTypeById(hmoForm.getHmotype_id()));

		hmo.setAdminName(hmoForm.getAdmin_name());
		// hmo.setOrganisation(userIdentity.getOrganisation());
		hmo.setModifiedBy(userIdentity.getUsername());
		hmo.setModifiedDate(new GregorianCalendar().getTime());

		this.hmoDao.update(hmo);
	}

}
