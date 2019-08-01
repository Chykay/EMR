package org.calminfotech.inventory.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.calminfotech.billing.boInterface.VendorTransactionBo;
import org.calminfotech.billing.models.VendorTransaction;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.inventory.daoInterface.StockInDaoInterface;
import org.calminfotech.inventory.daoInterface.VendorDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.PointStockInForm;
import org.calminfotech.inventory.forms.StockInForm;
import org.calminfotech.inventory.forms.StockInLineForm;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.inventory.serviceInterface.StockInManagerInterface;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.PaymodeList;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockInManagerImpl implements StockInManagerInterface {

	@Autowired
	private InventoryManagerInterface inventoryManagerImpl;

	@Autowired
	private StockInDaoInterface stockInDaoImpl;

	@Autowired
	private VendorDaoInterface vendorDaoImpl;

	@Autowired
	private Utilities utilities;

	@Autowired
	private PaymodeList paymodeBo;

	@Autowired
	private VendorTransactionBo vendortranBo;

	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Override
	public List<StockIn> getStockInBatchesList(DateSearchForm stockInSearchForm)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getStockInBatchesList(stockInSearchForm);
	}

	@Override
	public List<PointStockIn> getPointStockInBatchesList(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl
				.getPointStockInBatchesList(stockInSearchForm);
	}

	@Override
	public List<StockIn> getStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl
				.getStockInBatchesListTop100(stockInSearchForm);
	}

	@Override
	public List<PointStockIn> getPointStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl
				.getPointStockInBatchesListTop100(stockInSearchForm);
	}

	@Override
	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getStockInBatchDetailsById(id);
	}

	@Override
	public StockIn getBatchSupplyDetailsView(String batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getStockInBatchDetailsView(batchId);
	}

	@Override
	// @Transactional
	public StockIn saveStockInBatch(StockInForm stockInBatchForm) {
		// TODO Auto-generated method stub

		StockIn stockInBatch = null;

		try {
			Vendor vendor = this.vendorDaoImpl.getVendorDetailsById(Integer
					.parseInt(stockInBatchForm.getVendor()));
			stockInBatch = new StockIn();

			HrunitCategory unit = new HrunitCategory();

			stockInBatch.setBatchId(this.codeGenerator
					.generateSupplyBatchCode());

			stockInBatch.setUnit(this.userIdentity.getCurrentUnit());
			stockInBatch.setDescription(stockInBatchForm.getDescription());

			stockInBatch.setVendor(vendor);
			stockInBatch.setCreatedBy(this.userIdentity.getUsername());
			stockInBatch.setSupplyDate(DateUtils
					.formatStringToDate(stockInBatchForm.getDateOfSupply()));
			stockInBatch.setCreateDate(new Date());
			stockInBatch.setOrganisation(this.userIdentity.getOrganisation());

			this.stockInDaoImpl.saveStockInBatch(stockInBatch);
		} catch (RecordNotFoundException e) {
		} catch (NumberFormatException e) {
		}
		return stockInBatch;

	}

	@Override
	public StockIn editStockInBatch(StockInForm stockInBatchForm)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		StockIn stockInBatch = null;

		try {
			Vendor vendor = this.vendorDaoImpl.getVendorDetailsById(Integer
					.parseInt(stockInBatchForm.getVendor()));

			stockInBatch = this.stockInDaoImpl
					.getStockInBatchDetailsById(stockInBatchForm.getId());
			// stockInBatch.setVendor(stockInBatchForm.getVendor());
			stockInBatch.setVendor(vendor);
			stockInBatch.setDescription(stockInBatchForm.getDescription());
			stockInBatch.setSupplyDate(DateUtils
					.formatStringToDate(stockInBatchForm.getDateOfSupply()));
			stockInBatch.setModifiedDate(new Date());
			stockInBatch.setOrganisation(this.userIdentity.getOrganisation());

			this.stockInDaoImpl.editStockInBatch(stockInBatch);
		} catch (RecordNotFoundException e) {
		} catch (NumberFormatException e) {
		}
		return stockInBatch;
	}

	// @Transactional
	@Override
	public void delete(StockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration {
		//
		Set<StockInLine> stockInLineItems = stockInBatch.getStockInLines();
		if (stockInLineItems != null) {
			for (StockInLine stockInLineItem : stockInLineItems) {
				System.out.print("Am updating ..");
				try {
					this.updateStockInLineItemCurrentBalance(stockInLineItem);
				} catch (InvalidUnitOfMeasureConfiguration e) {
					System.out.print("updating error" + e.getExceptionMsg());

				}

			}
		}
		System.out.print("Before delete entity ");
		this.stockInDaoImpl.delete(stockInBatch);
		System.out.print("After delete entity ");

	}

	// line supply

	@Override
	public List<StockInLine> getStockInLineItems(int batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getStockInLineItems(batchId);
	}

	@Override
	public StockInLine saveStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException {
		// TODO Auto-generated method stub

		StockInLine stockInLineItem = null;

		System.out.print(stockInLineItemForm.getUnitOfMeasure());

		try {
			int globalItemId = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int unitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			int currentBalance = this.inventoryManagerImpl
					.getGlobalItemCurrentBalance(globalItemId);

			GlobalItem g = this.globalItemBo.getGlobalItemById(globalItemId);
			// GlobalUnitofMeasure u =
			// pointRequestLine.getGlobalUnitofMeasure();

			VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
			visitWorkflowPoint.setId(this.userIdentity.getCurrentPointId());

			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {

			}
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionB(globalItemId, unitofMeasure,
			 * qty, AppConfig.UNIT_OF_MEASURE_UNIT);
			 */
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,
			 * qty);
			 */

			int newQtyInUnits = this.unitOfMeasureConverter
					.convertmeasuretounits(qty, unitofMeasure);

			// update current balance b4 saving line item: newQtyInUnits is new
			// value and old value is value 0;
			StockCurrentBalance stockCurrentBalance = null;

			try {

				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(globalItemId,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				stockCurrentBalance = new StockCurrentBalance();
				stockCurrentBalance.setGlobalItem(g);
				stockCurrentBalance.setCurrentBalance(0);
				stockCurrentBalance.setOrganisation(userIdentity
						.getOrganisation());

				stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());
				stockCurrentBalance.setVisitWorkflowPoint(visitWorkflowPoint);

				stockCurrentBalance.setCreateDate(new Date());
				stockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());

			}

			this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
					newQtyInUnits, 0, stockCurrentBalance);

			// save line item now
			// GlobalUnitofMeasure globalUnitofMeasure = new
			// GlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw globalUnitofMeasure = new GlobalItemUnitofMeasureVw();

			GlobalItem globalItem = new GlobalItem();
			StockIn stockInBatch = new StockIn();

			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setItemId(globalItemId);
			stockInBatch.setId(stockInLineItemForm.getBatchId());

			stockInLineItem = new StockInLine();

			stockInLineItem.setGlobalUnitofMeasure(globalUnitofMeasure);
			stockInLineItem.setGlobalItem(globalItem);
			stockInLineItem.setStockInBatch(stockInBatch);

			stockInLineItem.setCreatedBy(this.userIdentity.getUsername());
			stockInLineItem.setCreateDate(new Date());
			stockInLineItem.setQuantity(qty);
			stockInLineItem.setCost(stockInLineItemForm.getCost());

			stockInLineItem
					.setOrganisation(this.userIdentity.getOrganisation());

			stockInLineItem.setBatchno(stockInLineItemForm.getBatchno());

			stockInLineItem.setTinno(stockInLineItemForm.getTinno());

			stockInLineItem.setSerialno(stockInLineItemForm.getSerialno());

			if (!stockInLineItemForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getManufacturedate()))

			{
				stockInLineItem.setManufacturedate(DateUtils
						.formatStringToDate(stockInLineItemForm
								.getManufacturedate()));
			}

			if (!stockInLineItemForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getExpirydate()))

			{
				stockInLineItem
						.setExpirydate(DateUtils
								.formatStringToDate(stockInLineItemForm
										.getExpirydate()));
			}

			// this.stockInDaoImpl.saveStockInLineItem(stockInLineItem);
			StockInLine stl = this.stockInDaoImpl
					.saveStockInLineItem2(stockInLineItem);

			VendorTransaction custtran = new VendorTransaction();

			custtran.setEffectivedate(new Date(System.currentTimeMillis()));
			custtran.setDescription("Supply of " + g.getGlobalitemName());
			try {
				custtran.setVendor(this.vendorDaoImpl
						.getVendorDetailsById(stockInLineItemForm.getBatchId()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			custtran.setDrcr("Cr");

			try {

				custtran.setAmount(stockInLineItemForm.getCost().doubleValue()
						* Integer.parseInt(stockInLineItemForm.getQuantity()
								.toString()));

			} catch (Exception e) {

				custtran.setAmount(0.00);
			}
			custtran.setTrantype("Supply");

			custtran.setTranrefno("ven#" + stl.getId());

			custtran.setCreatedBy(userIdentity.getUsername());
			custtran.setUser(userIdentity.getUser());
			custtran.setCreatedDate(new Date(System.currentTimeMillis()));
			// custtran.setCreatedDate(new GregorianCalendar().getTime());
			custtran.setOrganisation(userIdentity.getOrganisation());

			custtran.setPaymode(this.paymodeBo.getPaymodeTypeById(1));
			custtran.setDeleted(false);

			this.vendortranBo.save(custtran);

		} catch (NumberFormatException e) {

		}

		return stockInLineItem;
	}

	@Override
	public StockInLine editStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration, RecordNotFoundException,
			InvalidStockLevelException {

		StockInLine stockInLineItem = this.stockInDaoImpl
				.getStockInLineItemDetailById(stockInLineItemForm.getId());

		try {
			int newGlobalItem = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			int currentBalance;
			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockInLineItem.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getItemId();
			}
			// GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw u = stockInLineItem
					.getGlobalUnitofMeasure();

			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			int newQtyInUnits;
			int currQtyInUnits;
			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {
			}

			StockCurrentBalance stockCurrentBalance = null;

			try {

				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(g.getItemId(),
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				stockCurrentBalance = new StockCurrentBalance();
				stockCurrentBalance.setGlobalItem(g);
				stockCurrentBalance.setCurrentBalance(0);
				stockCurrentBalance.setOrganisation(userIdentity
						.getOrganisation());

				stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());

				VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
				visitWorkflowPoint.setId(12);
				stockCurrentBalance.setVisitWorkflowPoint(visitWorkflowPoint);

				stockCurrentBalance.setCreateDate(new Date());
				stockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());

			}

			if (currGlobalItem != newGlobalItem) {

				/*
				 * currentBalance = this.inventoryManagerImpl
				 * .getGlobalItemCurrentBalance(currGlobalItem);
				 */
				// update current product (by setting to zero)
				newQtyInUnits = 0;
				/*
				 * 
				 * do review re analyse here currQtyInUnits =
				 * this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(currGlobalItem,
				 * currUnitOfMeasure, stockInLineItem.getQuantity(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(stockInLineItem.getQuantity(),
								currUnitOfMeasure);

				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

				// update new product

				try {

					stockCurrentBalance = this.inventoryManagerImpl
							.getGlobalItemCurrentBalanceByStock(newGlobalItem,
									this.userIdentity.getCurrentPointId(),
									this.userIdentity.getCurrentUnitId());

				} catch (InvalidStockLevelException e) {

					// if stock has not yet been registered in opening balance
					// table

					stockCurrentBalance = new StockCurrentBalance();
					stockCurrentBalance.setGlobalItem(g);
					stockCurrentBalance.setCurrentBalance(0);
					stockCurrentBalance.setOrganisation(userIdentity
							.getOrganisation());

					stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());

					VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
					visitWorkflowPoint.setId(12);
					stockCurrentBalance
							.setVisitWorkflowPoint(visitWorkflowPoint);

					stockCurrentBalance.setCreateDate(new Date());
					stockCurrentBalance.setCreatedBy(this.userIdentity
							.getUsername());

				}

				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, qty, AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(qty, newUnitofMeasure);
				currQtyInUnits = 0;

				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

			} else {
				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(stockInLineItem.getQuantity(),
								currUnitOfMeasure);
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(qty, newUnitofMeasure);

				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

			}

			// update line item now
			StockIn stockInBatch = stockInLineItem.getStockInBatch();

			// do this cos hibernate can't reset id of persistent object --
			if (currUnitOfMeasure != newUnitofMeasure) {
				// u = new GlobalUnitofMeasure();
				u = new GlobalItemUnitofMeasureVw();
			}
			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}
			// -- --
			u.setId(newUnitofMeasure);
			g.setItemId(newGlobalItem);

			// stockInBatch.setId(stockInLineItemForm.getBatchId());
			stockInLineItem.setGlobalUnitofMeasure(u);
			stockInLineItem.setGlobalItem(g);
			// stockInLineItem.setBatchSupply(stockInBatch);
			stockInLineItem.setModifiedDate(new Date());
			stockInLineItem.setQuantity(qty);

			stockInLineItem.setCost(stockInLineItemForm.getCost());

			stockInLineItem
					.setOrganisation(this.userIdentity.getOrganisation());

			stockInLineItem.setBatchno(stockInLineItemForm.getBatchno());

			stockInLineItem.setTinno(stockInLineItemForm.getTinno());

			stockInLineItem.setSerialno(stockInLineItemForm.getSerialno());

			if (!stockInLineItemForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getManufacturedate()))

			{
				stockInLineItem.setManufacturedate(DateUtils
						.formatStringToDate(stockInLineItemForm
								.getManufacturedate()));
			} else {
				stockInLineItem.setManufacturedate(null);
			}

			if (!stockInLineItemForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getExpirydate()))

			{
				stockInLineItem
						.setExpirydate(DateUtils
								.formatStringToDate(stockInLineItemForm
										.getExpirydate()));
			}

			else {
				stockInLineItem.setExpirydate(null);
			}

			this.stockInDaoImpl.editStockInLineItem(stockInLineItem);

		} catch (NumberFormatException e) {

		}

		return stockInLineItem;
	}

	@Override
	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getStockInLineItemDetailById(id);
	}

	@Override
	public void deleteStockInLineItem(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration { // update current balance
														// b4 deletion

		// try {
		this.updateStockInLineItemCurrentBalance(stockInLineItem);
		// soft delete stock opening balance
		this.stockInDaoImpl.deleteStockInLineItem(stockInLineItem);
		// } catch (InvalidUnitOfMeasureConfiguration e) {

		// }
	}

	/*
	 * private void updateStockInLineItemCurrentBalance(StockInLine
	 * stockInLineItem) throws InvalidUnitOfMeasureConfiguration {
	 * 
	 * // update current balance b4 deletion int globalItem = 0; int
	 * currUnitOfMeasure = 0;
	 * 
	 * GlobalItem g = stockInLineItem.getGlobalItem(); if (g != null) {
	 * globalItem = g.getItemId(); }
	 * 
	 * //GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
	 * GlobalItemUnitofMeasureVw u =stockInLineItem.getGlobalUnitofMeasure();
	 * 
	 * if (u != null) { currUnitOfMeasure = u.getId(); }
	 * 
	 * /* int currQtyInUnits = this.unitOfMeasureConverter
	 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
	 * stockInLineItem.getQuantity(), AppConfig.UNIT_OF_MEASURE_UNIT);
	 */
	/*
	 * int currQtyInUnits = this.unitOfMeasureConverter
	 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
	 * stockInLineItem.getQuantity()); int currQtyInUnits =
	 * this.unitOfMeasureConverter
	 * .convertUnitOfMeasureToContainedUnitOptionBRwId
	 * (stockInLineItem.getQuantity(),currUnitOfMeasure);
	 * 
	 * 
	 * this.inventoryManagerImpl.updateGlobalItemCurrentBalance(0,
	 * currQtyInUnits, g.getBalance(), globalItem);
	 * 
	 * }
	 */

	private void updateStockInLineItemCurrentBalance(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration {

		System.out.print("Now inside update ");
		// update current balance b4 deletion
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockInLineItem.getGlobalItem();
		if (g != null) {
			globalItem = g.getItemId();
		}

		// GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw u = stockInLineItem.getGlobalUnitofMeasure();

		if (u != null) {
			currUnitOfMeasure = u.getId();
		}
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
		 * stockInLineItem.getQuantity(), AppConfig.UNIT_OF_MEASURE_UNIT);
		 */
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
		 * stockInLineItem.getQuantity());
		 */

		int currQtyInUnits = this.unitOfMeasureConverter.convertmeasuretounits(
				stockInLineItem.getQuantity(), currUnitOfMeasure);

		// update current balance b4 table: newQtyInUnits is new value
		// and old value is current value in store;
		StockCurrentBalance stockCurrentBalance;

		try {
			stockCurrentBalance = this.inventoryManagerImpl
					.getGlobalItemCurrentBalanceByStock(globalItem,
							this.userIdentity.getCurrentPointId(),
							this.userIdentity.getCurrentUnitId());

			this.inventoryManagerImpl.updateGlobalItemCurrentBalance(0,
					currQtyInUnits, stockCurrentBalance);

		} catch (InvalidStockLevelException e) {
			System.out.print("I don catch am");

			System.out.print(e.getExceptionMsg());

		} catch (Exception e) {
			System.out.print("I don catch am in exception");

			System.out.print(e.getMessage());

		}

	}

	// point stuff

	@Override
	public List<PointStockIn> getStockInBatchesListByPoint(int currentPointId) {

		return this.stockInDaoImpl.getStockInBatchesListByPoint(currentPointId);

	}

	@Override
	public PointStockIn getPointStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		return this.stockInDaoImpl.getPointStockInBatchDetailsById(id);
	}

	@Override
	public PointStockIn savePointStockInBatch(PointStockInForm stockInBatchForm) {

		PointStockIn stockInBatch = null;

		VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
		HrunitCategory unit = new HrunitCategory();

		visitWorkflowPoint.setId(this.userIdentity.getCurrentPointId());

		stockInBatch = new PointStockIn();

		stockInBatch.setBatchId(this.codeGenerator.generateSupplyBatchCode());

		// stockInBatch.setVisitWorkflowPoint(visitWorkflowPoint);
		stockInBatch.setUnit(this.userIdentity.getCurrentUnit());

		stockInBatch.setDescription(stockInBatchForm.getDescription());
		unit.setCategoryId(stockInBatchForm.getUnitId());
		stockInBatch.setUnitfrom(unit);
		// stockInBatch.setVisitWorkflowPoint()
		stockInBatch.setCreatedBy(this.userIdentity.getUsername());
		stockInBatch.setStockInDate(DateUtils
				.formatStringToDate(stockInBatchForm.getStockInDate()));
		stockInBatch.setCreateDate(new Date());

		stockInBatch.setOrganisation(this.userIdentity.getOrganisation());

		this.stockInDaoImpl.savePointStockInBatch(stockInBatch);

		return stockInBatch;

	}

	@Override
	public PointStockIn editPointStockInBatch(PointStockInForm stockInBatchForm)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub

		PointStockIn stockInBatch = null;

		try {

			// Vendor vendor = this.vendorDaoImpl.getVendorDetailsById(Integer
			// .parseInt(stockInBatchForm.getVendor()));

			stockInBatch = this.stockInDaoImpl
					.getPointStockInBatchDetailsById(stockInBatchForm.getId());
			// stockInBatch.setVendor(stockInBatchForm.getVendor());
			stockInBatch.setDescription(stockInBatchForm.getDescription());

			stockInBatch.setStockInDate(DateUtils
					.formatStringToDate(stockInBatchForm.getStockInDate()));
			stockInBatch.setModifiedDate(new Date());
			stockInBatch.setOrganisation(this.userIdentity.getOrganisation());
			HrunitCategory unit = new HrunitCategory();
			unit.setCategoryId(stockInBatchForm.getUnitId());
			stockInBatch.setUnitfrom(unit);
			this.stockInDaoImpl.editPointStockInBatch(stockInBatch);
		} catch (RecordNotFoundException e) {
		} catch (NumberFormatException e) {
		}
		return stockInBatch;

	}

	@Override
	public void deletePointStockIn(PointStockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration {

		Set<PointStockInLine> stockInLineItems = stockInBatch.getStockInLines();
		if (stockInLineItems != null) {
			for (PointStockInLine stockInLineItem : stockInLineItems) {
				try {
					this.updatePointStockInLineItemCurrentBalance(stockInLineItem);
				} catch (InvalidUnitOfMeasureConfiguration e) {
					System.out.print("updating error" + e.getExceptionMsg());

				}

			}
		}
		this.stockInDaoImpl.deletePointStockIn(stockInBatch);
	}

	private void updatePointStockInLineItemCurrentBalance(
			PointStockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration {

		// update current balance b4 deletion
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockInLineItem.getGlobalItem();
		if (g != null) {
			globalItem = g.getItemId();
		}

		// GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw u = stockInLineItem.getGlobalUnitofMeasure();

		if (u != null) {
			currUnitOfMeasure = u.getId();
		}
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
		 * stockInLineItem.getQuantity(), AppConfig.UNIT_OF_MEASURE_UNIT);
		 */
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
		 * stockInLineItem.getQuantity());
		 */

		int currQtyInUnits = this.unitOfMeasureConverter.convertmeasuretounits(
				stockInLineItem.getQuantity(), currUnitOfMeasure);

		// update current balance b4 table: newQtyInUnits is new value
		// and old value is current value in store;
		PointStockCurrentBalance pointStockCurrentBalance;
		try {
			pointStockCurrentBalance = this.inventoryManagerImpl
					.getPointGlobalItemCurrentBalanceByStock(globalItem,
							this.userIdentity.getCurrentPointId(),
							this.userIdentity.getCurrentUnitId());
			this.inventoryManagerImpl.updatePointGlobalItemCurrentBalance(0,
					currQtyInUnits, pointStockCurrentBalance);

		} catch (InvalidStockLevelException e) {
		}
	}

	@Override
	public PointStockInLine savePointStockInLineItem(
			StockInLineForm stockInLineItemForm)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration {

		PointStockInLine stockInLineItem = null;

		try {
			int globalItemId = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int unitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			int currentBalance = this.inventoryManagerImpl
					.getGlobalItemCurrentBalance(globalItemId);

			GlobalItem g = this.globalItemBo.getGlobalItemById(globalItemId);
			// GlobalUnitofMeasure u =
			// pointRequestLine.getGlobalUnitofMeasure();

			VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
			visitWorkflowPoint.setId(this.userIdentity.getCurrentPointId());

			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {

			}
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionB(globalItemId, unitofMeasure,
			 * qty, AppConfig.UNIT_OF_MEASURE_UNIT);
			 */
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,
			 * qty);
			 */
			int newQtyInUnits = this.unitOfMeasureConverter
					.convertmeasuretounits(qty, unitofMeasure);

			// update current balance b4 saving line item: newQtyInUnits is new
			// value and old value is value 0;
			StockCurrentBalance stockCurrentBalance = null;

			try {

				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(globalItemId,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				stockCurrentBalance = new StockCurrentBalance();
				stockCurrentBalance.setGlobalItem(g);
				stockCurrentBalance.setCurrentBalance(0);
				stockCurrentBalance.setOrganisation(userIdentity
						.getOrganisation());

				stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());
				stockCurrentBalance.setVisitWorkflowPoint(visitWorkflowPoint);

				stockCurrentBalance.setCreateDate(new Date());
				stockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());

			}

			this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
					newQtyInUnits, 0, stockCurrentBalance);
			// save line item now
			// GlobalUnitofMeasure globalUnitofMeasure = new
			// GlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw globalUnitofMeasure = new GlobalItemUnitofMeasureVw();

			GlobalItem globalItem = new GlobalItem();

			PointStockIn stockInBatch = new PointStockIn();

			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setItemId(globalItemId);
			stockInBatch.setId(stockInLineItemForm.getBatchId());

			stockInLineItem = new PointStockInLine();

			stockInLineItem.setGlobalUnitofMeasure(globalUnitofMeasure);
			stockInLineItem.setGlobalItem(globalItem);
			stockInLineItem.setStockInBatch(stockInBatch);

			stockInLineItem.setCreatedBy(this.userIdentity.getUsername());
			stockInLineItem.setCreateDate(new Date());
			stockInLineItem.setQuantity(qty);
			stockInLineItem
					.setOrganisation(this.userIdentity.getOrganisation());

			stockInLineItem.setBatchno(stockInLineItemForm.getBatchno());

			stockInLineItem.setTinno(stockInLineItemForm.getTinno());

			stockInLineItem.setSerialno(stockInLineItemForm.getSerialno());

			if (!stockInLineItemForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getManufacturedate()))

			{
				stockInLineItem.setManufacturedate(DateUtils
						.formatStringToDate(stockInLineItemForm
								.getManufacturedate()));
			}

			if (!stockInLineItemForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getExpirydate()))

			{
				stockInLineItem
						.setExpirydate(DateUtils
								.formatStringToDate(stockInLineItemForm
										.getExpirydate()));
			}

			this.stockInDaoImpl.savePointStockInLineItem(stockInLineItem);

		} catch (NumberFormatException e) {

		}

		return stockInLineItem;
	}

	@Override
	public PointStockInLine getPointStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.getPointStockInLineItemDetailById(id);
	}

	@Override
	public PointStockInLine editPointStockInLineItem(
			StockInLineForm stockInLineItemForm)
			throws InvalidStockLevelException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration {
		PointStockInLine stockInLineItem = this.stockInDaoImpl
				.getPointStockInLineItemDetailById(stockInLineItemForm.getId());

		try {

			int newGlobalItem = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			StockCurrentBalance stockCurrentBalance = null;
			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockInLineItem.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getItemId();
			}
			// GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw u = stockInLineItem
					.getGlobalUnitofMeasure();

			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			int newQtyInUnits;
			int currQtyInUnits;
			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {
			}

			// start to compare here

			try {
				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(currGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				stockCurrentBalance = new StockCurrentBalance();
				stockCurrentBalance.setGlobalItem(g);
				stockCurrentBalance.setCurrentBalance(0);
				stockCurrentBalance.setOrganisation(userIdentity
						.getOrganisation());

				stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());

				VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
				visitWorkflowPoint.setId(12);
				stockCurrentBalance.setVisitWorkflowPoint(visitWorkflowPoint);

				stockCurrentBalance.setCreateDate(new Date());
				stockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());

			}

			if (currGlobalItem != newGlobalItem) {

				// update current product
				newQtyInUnits = 0;
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(currGlobalItem,
				 * currUnitOfMeasure, stockInLineItem.getQuantity(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(currGlobalItem,
				 * currUnitOfMeasure, stockInLineItem.getQuantity());
				 */

				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(stockInLineItem.getQuantity(),
								currUnitOfMeasure);

				// this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
				// newQtyInUnits, currQtyInUnits, currentBalance,
				// currGlobalItem);

				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

				// update new product
				try {
					stockCurrentBalance = this.inventoryManagerImpl
							.getGlobalItemCurrentBalanceByStock(newGlobalItem,
									this.userIdentity.getCurrentPointId(),
									this.userIdentity.getCurrentUnitId());

				} catch (InvalidStockLevelException e) {

					// if stock has not yet been registered in opening balance
					// table

					stockCurrentBalance = new StockCurrentBalance();
					stockCurrentBalance.setGlobalItem(g);
					stockCurrentBalance.setCurrentBalance(0);
					stockCurrentBalance.setOrganisation(userIdentity
							.getOrganisation());

					stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());

					VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
					visitWorkflowPoint.setId(12);
					stockCurrentBalance
							.setVisitWorkflowPoint(visitWorkflowPoint);

					stockCurrentBalance.setCreateDate(new Date());
					stockCurrentBalance.setCreatedBy(this.userIdentity
							.getUsername());

				}

				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(qty, newUnitofMeasure);

				currQtyInUnits = 0;

				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);
			} else {

				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, qty, AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * newUnitofMeasure, qty);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(qty, newUnitofMeasure);
				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(stockInLineItem.getQuantity(),
								currUnitOfMeasure);

				// update current balance: newQtyInUnits is new value , old
				// value is
				// current value in store;
				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);
			}

			// update line item now
			PointStockIn stockInBatch = stockInLineItem.getStockInBatch();

			// do this cos hibernate can't reset id of persistent object --
			if (currUnitOfMeasure != newUnitofMeasure) {
				// u = new GlobalUnitofMeasure();
				u = new GlobalItemUnitofMeasureVw();
			}
			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}
			// -- --
			u.setId(newUnitofMeasure);
			g.setItemId(newGlobalItem);
			stockInBatch.setId(stockInLineItemForm.getBatchId());
			stockInLineItem.setGlobalUnitofMeasure(u);
			stockInLineItem.setGlobalItem(g);
			// stockInLineItem.setBatchSupply(stockInBatch);
			stockInLineItem.setModifiedDate(new Date());
			stockInLineItem.setQuantity(qty);
			stockInLineItem
					.setOrganisation(this.userIdentity.getOrganisation());

			stockInLineItem.setBatchno(stockInLineItemForm.getBatchno());

			stockInLineItem.setTinno(stockInLineItemForm.getTinno());

			stockInLineItem.setSerialno(stockInLineItemForm.getSerialno());

			if (!stockInLineItemForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getManufacturedate()))

			{
				stockInLineItem.setManufacturedate(DateUtils
						.formatStringToDate(stockInLineItemForm
								.getManufacturedate()));
			} else {
				stockInLineItem.setManufacturedate(null);
			}

			if (!stockInLineItemForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockInLineItemForm
							.getExpirydate()))

			{
				stockInLineItem
						.setExpirydate(DateUtils
								.formatStringToDate(stockInLineItemForm
										.getExpirydate()));
			}

			else {
				stockInLineItem.setExpirydate(null);
			}

			this.stockInDaoImpl.editPointStockInLineItem(stockInLineItem);
		} catch (NumberFormatException e) {

		}

		return stockInLineItem;
	}

	@Override
	public void deletePointStockInLineItem(PointStockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		// update current balance b4 deletion

		try {
			this.updatePointStockInLineItemCurrentBalance(stockInLineItem);
			// soft delete stock opening balance
			this.stockInDaoImpl.deletePointStockInLineItem(stockInLineItem);
		} catch (InvalidUnitOfMeasureConfiguration e) {
		}
	}

	@Override
	public StockInLine saveStockInLineItem2(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		return this.stockInDaoImpl.saveStockInLineItem2(stockInLineItem);
	}

	/*
	 * private int getPoint() { User user =
	 * userBo.getUserByEmail(userIdentity.getUsername()); int loginPoint = 0; if
	 * (user != null) { LoginSectionPoint loginSectionPoint =
	 * user.getLoginSectionPoint(); if (loginSectionPoint != null) { loginPoint
	 * = loginSectionPoint.getId(); } } return loginPoint; }
	 */

}
