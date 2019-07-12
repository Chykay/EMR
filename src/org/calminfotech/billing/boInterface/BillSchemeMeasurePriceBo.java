package org.calminfotech.billing.boInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeMeasurePrice;
import org.calminfotech.billing.models.BillSchemeRankingPrice;
import org.calminfotech.system.models.Organisation;

public interface BillSchemeMeasurePriceBo {

	public List<BillSchemeMeasurePrice> fetchAllByOrganisationBytype(
			Organisation organisationId, int billtype);

	public List<BillSchemeMeasurePrice> fetchAllByMeasure(int measureid);

	public List<BillSchemeMeasurePrice> fetchAllByOrganisation(
			Organisation organisationId);

	public BillSchemeMeasurePrice getBillSchemeMeasurePriceById(int id);

	public BillSchemeMeasurePrice getBillSchemeMeasurePriceBySchemeandItemmeasure(
			int schemeid, int itemmeasure);

	public void save(BillSchemeMeasurePrice billSchemeMeasure);

	public void delete(BillSchemeMeasurePrice billSchemeMeasure);

	public void update(BillSchemeMeasurePrice billSchemeMeasure);

	public List<BillSchemeRankingPrice> fetchAllRankingByOrganisation(
			Organisation organisationId);

	public BillSchemeRankingPrice getBillSchemeRankingPriceById(int id);

	public void save(BillSchemeRankingPrice billSchemeRankingPrice);

	public void delete(BillSchemeRankingPrice billSchemeRankingPrice);

	public void update(BillSchemeRankingPrice billSchemeRankingPrice);

}
