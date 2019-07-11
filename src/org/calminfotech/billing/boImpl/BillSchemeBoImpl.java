package org.calminfotech.billing.boImpl;

import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.daoInterface.BillSchemeDao;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.system.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import org.calminfotech.system.daoInterface.BillingSchemeDao;
 import org.calminfotech.system.forms.BillingSchemeForm;
 import org.calminfotech.system.models.BillingScheme;
 import org.calminfotech.user.utils.UserIdentity;*/

@Service
@Transactional
public class BillSchemeBoImpl implements BillSchemeBo {

	/*
	 * @Autowired private UserIdentity userIdentity;
	 */

	@Autowired
	private BillSchemeDao billSchemeDao;

	@Override
	public List<BillScheme> fetchAllByOrganisation(Organisation organisationId) {
		return this.billSchemeDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<BillScheme> fetchAllByOrganisationBytype(
			Organisation organisationId, int billtype) {
		return this.billSchemeDao.fetchAllByOrganisationBytype(organisationId,
				billtype);
	}

	@Override
	public BillScheme getBillSchemeById(int id) {
		return this.billSchemeDao.getBillSchemeById(id);
	}

	@Override
	public void save(BillScheme billScheme) {
		/*
		 * BillSchemeForm billSchemeForm = new BillSchemeForm();
		 * billSchemeForm.setName(billScheme.getName());
		 * billSchemeForm.setBillId(billScheme.getBillId());
		 * billSchemeForm.setBillingType(billScheme.getBillingType());
		 * billSchemeForm.setDescription(billScheme.getDescription());
		 * billSchemeForm.setName(billScheme.getName());
		 * billSchemeForm.setOrganisation(userIdentity.getOrganisation());
		 * ehmo.setAddress(ehmoForm.getAddress());
		 * ehmo.setEmail(ehmoForm.getEmail());
		 * ehmo.setState(ehmoForm.getState()); ehmo.setLga(ehmoForm.getLga());
		 * ehmo.setBank(ehmoForm.getBank());
		 * ehmo.setBankAccount(ehmoForm.getBankAccount());
		 * ehmo.setPhoneNumber(ehmoForm.getPhoneNumber());
		 * ehmo.setPostalNumber(ehmoForm.getPostalNumber());
		 * ehmo.setAdminName(ehmoForm.getAdminName());
		 * ehmo.setAdminEmail(ehmoForm.getAdminEmail());
		 * ehmo.setAdminPhone(ehmoForm.getAdminPhone());
		 * //ehmo.setOrganisationId(ehmoForm.getOrganisationId());
		 * ehmo.setCreatedBy(userIdentity.getUsername());
		 * ehmo.setOrganisation(userIdentity.getOrganisation());
		 * ehmo.setCreatedDate(new GregorianCalendar().getTime());
		 * ehmo.setStatus("Active");
		 */

		this.billSchemeDao.save(billScheme);
		// return billScheme;
	}

	@Override
	public void delete(BillScheme billScheme) {
		this.billSchemeDao.delete(billScheme);
	}

	@Override
	public void update(BillScheme billScheme) {
		/*
		 * BillScheme bill =
		 * this.billSchemeDao.getBillSchemeById(billScheme.getBillId());
		 * bill.setName(billScheme.getName());
		 * 
		 * bill.setDescription(billScheme.getDescription());
		 * bill.setBillingType(billScheme.getBillingType());
		 * 
		 * //ehmo.setOrganisationId(27); //
		 * ehmo.setOrganisation(userIdentity.getOrganisation());
		 * billScheme.setModifiedBy(userIdentity.getUsername());
		 * billScheme.setModifiedDate(new GregorianCalendar().getTime());
		 */
		this.billSchemeDao.update(billScheme);
	}
}