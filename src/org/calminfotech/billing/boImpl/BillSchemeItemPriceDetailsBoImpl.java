package org.calminfotech.billing.boImpl;

import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeItemPriceDetailsBo;
import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDetailsDao;
import org.calminfotech.billing.models.BillSchemeItemPriceDetails;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillSchemeItemPriceDetailsBoImpl implements
		BillSchemeItemPriceDetailsBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillSchemeItemPriceDetailsDao billingSchemePriceDetails;

	@Override
	public List<BillSchemeItemPriceDetails> fetchAll() {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails.fetchAll();
	}

	@Override
	public List<BillSchemeItemPriceDetails> fetchAllByOrgainsation() {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails
				.fetchAllByOrgainsation(this.userIdentity.getOrganisation());
	}

	@Override
	public void save(BillSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.save(billing);
	}

	@Override
	public void delete(BillSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.delete(billing);
	}

	@Override
	public void update(BillSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.update(billing);
	}

	@Override
	public BillSchemeItemPriceDetails getBillingById(int id) {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails.getBillingById(id);
	}

	@Override
	public List<BillSchemeItemPriceDetails> getBillingInvoice(
			Integer billingSchemeId, Integer unitofmeasureId,
			Integer globalItemPointId) {
		// TODO Auto-generated method stub
		Organisation OrganisationId = userIdentity.getOrganisation();
		System.out.println("++++++ organisation " + OrganisationId);
		return this.billingSchemePriceDetails.getBillingInvoice(
				billingSchemeId, unitofmeasureId, globalItemPointId,
				OrganisationId);
	}

}
