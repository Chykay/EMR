package org.calminfotech.billing.boImpl;

import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeMeasurePriceBo;
import org.calminfotech.billing.daoInterface.BillSchemeMeasurePriceDao;
import org.calminfotech.billing.models.BillSchemeMeasurePrice;
import org.calminfotech.billing.models.BillSchemeRankingPrice;
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
public class BillSchemeMeasurePriceBoImpl implements BillSchemeMeasurePriceBo {

	/*
	 * @Autowired private UserIdentity userIdentity;
	 */

	@Autowired
	private BillSchemeMeasurePriceDao billSchemeMeasureDao;

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByOrganisation(
			Organisation organisationId) {
		return this.billSchemeMeasureDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByOrganisationBytype(
			Organisation organisationId, int billtype) {
		return this.billSchemeMeasureDao.fetchAllByOrganisationBytype(
				organisationId, billtype);
	}

	@Override
	public BillSchemeMeasurePrice getBillSchemeMeasurePriceById(int id) {
		return this.billSchemeMeasureDao.getBillSchemeMeasurePriceById(id);
	}

	@Override
	public void save(BillSchemeMeasurePrice billSchemeMeasure) {
		/*
		 * BillSchemeMeasurePriceForm billSchemeMeasureForm = new
		 * BillSchemeMeasurePriceForm();
		 * billSchemeMeasureForm.setName(billSchemeMeasure.getName());
		 * billSchemeMeasureForm.setBillId(billSchemeMeasure.getBillId());
		 * billSchemeMeasureForm
		 * .setBillingType(billSchemeMeasure.getBillingType());
		 * billSchemeMeasureForm
		 * .setDescription(billSchemeMeasure.getDescription());
		 * billSchemeMeasureForm.setName(billSchemeMeasure.getName());
		 * billSchemeMeasureForm
		 * .setOrganisation(userIdentity.getOrganisation());
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

		this.billSchemeMeasureDao.save(billSchemeMeasure);
		// return billSchemeMeasure;
	}

	@Override
	public void delete(BillSchemeMeasurePrice billSchemeMeasure) {
		this.billSchemeMeasureDao.delete(billSchemeMeasure);
	}

	@Override
	public void update(BillSchemeMeasurePrice billSchemeMeasure) {
		/*
		 * BillSchemeMeasurePrice bill =
		 * this.billSchemeMeasureMeasureDao.getBillSchemeMeasurePriceById
		 * (billSchemeMeasure.getBillId());
		 * bill.setName(billSchemeMeasure.getName());
		 * bill.setDescription(billSchemeMeasure.getDescription());
		 * bill.setBillingType(billSchemeMeasure.getBillingType());
		 * 
		 * //ehmo.setOrganisationId(27); //
		 * ehmo.setOrganisation(userIdentity.getOrganisation());
		 * billSchemeMeasure.setModifiedBy(userIdentity.getUsername());
		 * billSchemeMeasure.setModifiedDate(new GregorianCalendar().getTime());
		 */

		this.billSchemeMeasureDao.update(billSchemeMeasure);
	}

	public BillSchemeMeasurePrice getBillSchemeMeasurePriceBySchemeandItemmeasure(
			int schemeid, int itemmeasure)

	{
		return this.billSchemeMeasureDao
				.getBillSchemeMeasurePriceBySchemeandItemmeasure(schemeid,
						itemmeasure);

	}

	@Override
	public List<BillSchemeRankingPrice> fetchAllRankingByOrganisation(
			Organisation organisationId) {
		// TODO Auto-generated method stub
		return this.billSchemeMeasureDao
				.fetchAllRankingByOrganisation(organisationId);
	}

	@Override
	public BillSchemeRankingPrice getBillSchemeRankingPriceById(int id) {
		// TODO Auto-generated method stub
		return this.billSchemeMeasureDao.getBillSchemeRankingPriceById(id);
	}

	@Override
	public void save(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.billSchemeMeasureDao.save(billSchemeRankingPrice);
	}

	@Override
	public void delete(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.billSchemeMeasureDao.delete(billSchemeRankingPrice);
	}

	@Override
	public void update(BillSchemeRankingPrice billSchemeRankingPrice) {
		// TODO Auto-generated method stub
		this.billSchemeMeasureDao.update(billSchemeRankingPrice);
	}

	@Override
	public List<BillSchemeMeasurePrice> fetchAllByMeasure(int measureid) {
		// TODO Auto-generated method stub

		return this.billSchemeMeasureDao.fetchAllByMeasure(measureid);

	}
}
