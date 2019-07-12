package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeItemPriceDetails;
import org.calminfotech.system.models.Organisation;

public interface BillSchemeItemPriceDetailsDao {

	public List<BillSchemeItemPriceDetails> fetchAll();

	public List<BillSchemeItemPriceDetails> fetchAllByOrgainsation(
			Organisation organisation);

	public void save(BillSchemeItemPriceDetails billSchemeItemPriceDetails);

	public void delete(BillSchemeItemPriceDetails billSchemeItemPriceDetails);

	public void update(BillSchemeItemPriceDetails billSchemeItemPriceDetails);

	public BillSchemeItemPriceDetails getBillingById(int id);

	public List<org.calminfotech.billing.models.BillSchemeItemPriceDetails> getBillingInvoice(
			Integer billingSchemeId, Integer unitofmeasureId,
			Integer globalItemPointId, Organisation organisationId);

}
