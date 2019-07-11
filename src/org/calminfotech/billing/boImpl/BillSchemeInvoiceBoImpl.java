package org.calminfotech.billing.boImpl;

import java.util.List;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeInvoiceBo;
import org.calminfotech.billing.daoInterface.BillSchemeInvoiceDao;
import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDetailsDao;
import org.calminfotech.billing.models.BillSchemeInvoice;
import org.calminfotech.billing.models.BillSchemeItemPriceDetails;
import org.calminfotech.consultation.forms.BillingForm;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.VwItemBo;
import org.calminfotech.system.models.ItemVw;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.calminfotech.system.models.LoginSection;

@Service
public class BillSchemeInvoiceBoImpl implements BillSchemeInvoiceBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillSchemeInvoiceDao billSchemeInvoiceDao;

	@Autowired
	private VisitBo visitBo;

	// @Autowired
	// private LoginSectionBo sectionBo;

	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Autowired
	private VwItemBo itemBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired(required = false)
	private BillSchemeBo billingSchemeBo;

	@Autowired
	private BillSchemeItemPriceDetailsDao billingSchemePriceDetails;

	@Override
	public List<BillSchemeInvoice> fetchAll() {
		// TODO Auto-generated method stub
		return this.billSchemeInvoiceDao.fetchAll();
	}

	@Override
	public List<BillSchemeInvoice> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.billSchemeInvoiceDao
				.fetchAllByOrganisation(this.userIdentity.getOrganisation());
	}

	@Override
	public void save(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billSchemeInvoiceDao.save(billingInvoice);
	}

	@Override
	public void delete(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billSchemeInvoiceDao.delete(billingInvoice);
	}

	@Override
	public void update(BillSchemeInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.billSchemeInvoiceDao.update(billingInvoice);
	}

	@Override
	public BillSchemeInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		return this.billSchemeInvoiceDao.getBillingInvoiceById(id);
	}

	@Override
	public List<BillSchemeInvoice> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.billSchemeInvoiceDao.fetchAllByPatient(patient);
	}

	@Override
	public void raiseInvoicePoint(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillSchemeInvoice billingInvoice = new BillSchemeInvoice();
		// LoginSection section =
		// this.sectionBo.getLoginSectionById(userIdentity
		// .getSectionId());
		// billingInvoice.setScheme(userIdentity.getBillId());
		Integer frontDesk = 1;
		VisitWorkflowPoint point = this.pointBo.getWorkflowPointById(frontDesk);
		// billingInvoice.setPoint(point);
		Visit visit = this.visitBo.getVisitationById(userIdentity.getVisitId());
		// billingInvoice.setVisit(visit);
		// billingInvoice.setPatient(visit.getPatient());
		ItemVw item = this.itemBo.getVwItemById(billingForm.getItem());
		// billingInvoice.setItem(item);
		// billingInvoice.setQuantity(billingForm.getItemType());
		// billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		if (billingForm.getPrice() == null) {
			// billingInvoice.setPrice(0.00);
		} else {
			// billingInvoice.setPrice(billingForm.getPrice());
		}
		// billingInvoice.setStatus("Yet to pay");
		// billingInvoice.setOrganisation(userIdentity.getOrganisation());
		// billingInvoice.setCreatedby(userIdentity.getUsername());
		// billingInvoice.setDeleted(false);
		this.billSchemeInvoiceDao.save(billingInvoice);
		// flush the util bean
		/*
		 * userIdentity.setBillId(null); userIdentity.setBillName(null);
		 * userIdentity.setCurrentPointId(null);
		 * userIdentity.setCurrentPointName(null);
		 */
	}

	// save by raising bill only from menu
	@Override
	public void raiseInvoiceFromMenu(BillingForm billingForm) {
		// TODO Auto-generated method stub
		BillSchemeInvoice billingInvoice = new BillSchemeInvoice();
		// billingInvoice.setPatient(patientBo.getPatientById(billingForm
		// .getPatientId()));
		// billingInvoice.setSection(sectionBo.getLoginSectionById(billingForm
		// .getSectionId()));
		// billingInvoice.setScheme(billingSchemeBo.getBillingSchemeById(sectionBo.getLoginSectionById(billingForm.getSectionId()).getBillingSscheme().getId()).getId());
		// billingInvoice.setPoint(pointBo.(billingForm
		// .getCurrentPiointId()));
		// billingInvoice.setVisit(visitBo.getVisitationById(billingForm
		// .getVisitId()));
		// billingInvoice
		// .setItem(this.itemBo.getVwItemById(billingForm.getItem()));
		// billingInvoice.setQuantity(billingForm.getItemType());
		// billingInvoice.setUnitId(billingForm.getUnitofmeasure());
		// save cost amount
		if (billingForm.getPrice() == null) {
			// billingInvoice.setPrice(0.00);
		} else {
			// billingInvoice.setPrice(billingForm.getPrice());
		}
		// expected payment
		billingInvoice.setAmountPaid(0.00);
		billingInvoice.setStatus("Yet to pay");
		// billingInvoice.setOrganisation(userIdentity.getOrganisation());
		// billingInvoice.setCreatedby(userIdentity.getUsername());
		billingInvoice.setDeleted(false);
		billSchemeInvoiceDao.save(billingInvoice);
	}

	public BillSchemeInvoice raiseInvoicePoint(Integer billingSchemeId,
			Integer unitofmeasureId, Integer itemId, Integer qty,
			Integer sectionId, Integer visitId) {
		String strPrice = "";
		Visit visit = this.visitBo.getVisitationById(visitId);
		BillSchemeInvoice billingInvoice = null;
		/* fetch price */

		// System.out.print(billingSchemeId+"/"+unitofmeasureId+"/"+itemId+"/"+this.userIdentity.getOrganisation().getId());

		List<BillSchemeItemPriceDetails> prices = this.billingSchemePriceDetails
				.getBillingInvoice(billingSchemeId, unitofmeasureId, itemId,
						this.userIdentity.getOrganisation());

		/* check if amount exist */
		if (prices == null) {
			strPrice = "0:00";
		} else {
			for (BillSchemeItemPriceDetails price : prices) {
				strPrice = price.getPrice();
			}
		}
		// save bill
		billingInvoice = new BillSchemeInvoice();
		// billingInvoice
		// .setSection(this.sectionBo.getLoginSectionById(sectionId));
		// billingInvoice.setScheme(billingSchemeId);
		// billingInvoice.setPoint(this.pointBo.getWorkflowPointById(visit
		// .getPoint().getId()));
		// billingInvoice.setUnitId(String.valueOf(visit.getId()));
		// billingInvoice.setVisit(visit);
		// billingInvoice.setPatient(visit.getPatient());
		// billingInvoice.setItem(this.itemBo.getVwItemById(itemId));
		// billingInvoice.setQuantity(String.valueOf(qty));
		// StringBuilder sb = new StringBuilder();
		double i = 0;
		try {
			i = qty * Double.parseDouble(strPrice);
		} catch (NumberFormatException e) {
		}
		// // Integer obj = new Integer(i);
		// billingInvoice.setPrice(i);
		// billingInvoice.setStatus("Yet to pay");
		// billingInvoice.setOrganisation(userIdentity.getOrganisation());
		// billingInvoice.setCreatedby(userIdentity.getUsername());
		// billingInvoice.setDeleted(false);
		this.billSchemeInvoiceDao.save(billingInvoice);

		return billingInvoice;

	}

}
