package org.calminfotech.billing.boInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeItemPriceDetails;

public interface BillSchemeItemPriceDetailsBo {

	public List<BillSchemeItemPriceDetails> fetchAll();
	public List<BillSchemeItemPriceDetails> fetchAllByOrgainsation();
	public void save(BillSchemeItemPriceDetails billing);
	public void delete(BillSchemeItemPriceDetails billing);
	public void update(BillSchemeItemPriceDetails billing);
	public BillSchemeItemPriceDetails getBillingById(int id);
	public List<BillSchemeItemPriceDetails> getBillingInvoice(Integer billingSchemeId, Integer unitofmeasureId, Integer globalItemPointId);
}
