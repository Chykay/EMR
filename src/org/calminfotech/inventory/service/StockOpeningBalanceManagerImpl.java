package org.calminfotech.inventory.service;

import java.util.Date;
import java.util.List;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.inventory.daoInterface.StockOpeningBalanceDaoInterface;
import org.calminfotech.inventory.daoInterface.VendorDaoInterface;
import org.calminfotech.inventory.exceptions.DuplicateDataException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.inventory.serviceInterface.StockOpeningBalanceManagerInterface;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockOpeningBalanceManagerImpl implements
		StockOpeningBalanceManagerInterface {

	@Autowired
	private InventoryManagerInterface inventoryManagerImpl;

	@Autowired
	private StockOpeningBalanceDaoInterface StockOpeningBalanceDaoImpl;

	@Autowired
	private VendorDaoInterface vendorDaoImpl;

	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	// stock opening bal
	@Override
	public List<StockOpeningBalance> getStockOpeningBalances(Integer id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.StockOpeningBalanceDaoImpl.getStockOpeningBalances(id);
	}

	@Override
	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub

		return this.StockOpeningBalanceDaoImpl
				.getStockOpeningBalanceDetailById(id);
	}

	// @Transactional
	@Override
	public void saveStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, InvalidUnitOfMeasureConfiguration {
		// TODO Auto-generated method stub
		// check if global item stock opening balance has not yet been set
		// if already set then duplicate data exception is thrown else continue

		// get object by natural id to
		StockOpeningBalance stockOpeningBalance = null;
		System.out.println("am inside savestock");

		int unitofMeasure = 0;
		int globalItemId = 0;
		int openingBalance = 0;
		int reorderLevel = 0;
		try {

			globalItemId = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
		} catch (Exception e) {
			System.out.println("Globalitemtry" + e.getMessage());

		}

		try {

			unitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());
		} catch (Exception e) {

			System.out.println("unitofmeasuretry" + e.getMessage());

		}

		try {
			openingBalance = Integer.parseInt(stockOpeningBalanceForm
					.getOpeningBalance());
		} catch (Exception e) {

			System.out.println("openingbalancetry" + e.getMessage());

		}

		try {
			reorderLevel = Integer.parseInt(stockOpeningBalanceForm
					.getReorderlevel());
		} catch (Exception e) {

			System.out.println("reordertry" + e.getMessage());

		}

		try {

			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionB(globalItemId, unitofMeasure,
			 * openingBalance, AppConfig.UNIT_OF_MEASURE_UNIT);
			 */
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,
			 * openingBalance);
			 */

			int newQtyInUnits = this.unitOfMeasureConverter
					.convertmeasuretounits(openingBalance, unitofMeasure);
			Date createdDate = new Date();

			// GlobalUnitofMeasure globalUnitofMeasure = new
			// GlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw globalUnitofMeasure = new GlobalItemUnitofMeasureVw();
			GlobalItem globalItem = new GlobalItem();
			VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
			HrunitCategory unit = new HrunitCategory();

			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setItemId(globalItemId);
			visitWorkflowPoint.setId(this.userIdentity.getCurrentPointId());

			// unit.setCategoryId(this.userIdentity.getCurrentUnitId());

			stockOpeningBalance = new StockOpeningBalance();

			stockOpeningBalance.setGlobalUnitofMeasure(globalUnitofMeasure);
			stockOpeningBalance.setGlobalItem(globalItem);
			stockOpeningBalance.setCreatedBy(this.userIdentity.getUsername());
			stockOpeningBalance.setCreateDate(new Date());
			stockOpeningBalance.setCost(stockOpeningBalanceForm.getCost());
			stockOpeningBalance.setDescription(stockOpeningBalanceForm
					.getDescription());
			stockOpeningBalance.setAction(stockOpeningBalanceForm.getAction());

			stockOpeningBalance.setUnit(userIdentity.getCurrentUnit());

			stockOpeningBalance
					.setBatchno(stockOpeningBalanceForm.getBatchno());
			stockOpeningBalance.setSerialno(stockOpeningBalanceForm
					.getSerialno());
			stockOpeningBalance.setTinno(stockOpeningBalanceForm.getTinno());
			if (!stockOpeningBalanceForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockOpeningBalanceForm
							.getManufacturedate()))

			{
				stockOpeningBalance.setManufacturedate(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getManufacturedate()));
			}

			if (!stockOpeningBalanceForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockOpeningBalanceForm
							.getExpirydate()))

			{
				stockOpeningBalance.setExpirydate(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getExpirydate()));
			}

			// stockOpeningBalance.setUnit(userIdentity.getUser().getUnit());

			stockOpeningBalance.setVisitWorkflowPoint(userIdentity
					.getCurrentUnit().getPoint());

			stockOpeningBalance.setOrganisation(userIdentity.getOrganisation());
			visitWorkflowPoint.setId(userIdentity.getCurrentPointId());
			stockOpeningBalance.setVisitWorkflowPoint(visitWorkflowPoint);
			stockOpeningBalance.setOpeningBalance(openingBalance);
			// stockOpeningBalance.setReorderlevel(reorderLevel);
			stockOpeningBalance.setOrganisation(userIdentity.getOrganisation());

			stockOpeningBalance
					.setDateAdded(DateUtils
							.formatStringToDate(stockOpeningBalanceForm
									.getDateAdded()));

			System.out.print("just wanna save");

			this.StockOpeningBalanceDaoImpl
					.saveStockOpeningBalance(stockOpeningBalance);

			// update current balance:newQtyInUnits is new value, old value is
			// zero;
			/*
			 * int currentBalance = 0; try { currentBalance =
			 * this.inventoryManagerImpl
			 * .getGlobalItemCurrentBalance(globalItemId); } catch
			 * (InvalidOpeningStockBalanceException e) { }
			 * 
			 * this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
			 * newQtyInUnits, 0, currentBalance, globalItemId);
			 * this.inventoryManagerImpl.updateGlobalItemCurrentBalance();
			 * 
			 * } catch (NumberFormatException e) {
			 * 
			 * }
			 */

			StockCurrentBalance stockCurrentBalance = null;
			try {
				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(globalItemId,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				stockCurrentBalance = new StockCurrentBalance();
				stockCurrentBalance.setGlobalItem(globalItem);
				stockCurrentBalance.setCurrentBalance(0);
				stockCurrentBalance.setOrganisation(userIdentity
						.getOrganisation());

				stockCurrentBalance.setUnit(userIdentity.getCurrentUnit());
				stockCurrentBalance.setVisitWorkflowPoint(visitWorkflowPoint);

				stockCurrentBalance.setCreateDate(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getDateAdded()));
				stockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());

			}

			this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
					newQtyInUnits, 0, stockCurrentBalance);
		} catch (NumberFormatException e) {
			System.out.println("last  number exemption" + e.getMessage());
		}

	}

	// @Transactional
	@Override
	public StockOpeningBalance editStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration, InvalidStockLevelException {

		// check if global item stock opening balance and corresponding unit of
		// measure has not yet been set
		// if already set then duplicate data exception is thrown else continue
		StockOpeningBalance stockOpeningBalance = null;

		try {
			stockOpeningBalance = this
					.getStockOpeningBalanceDetailById(stockOpeningBalanceForm
							.getId());

			int newGlobalItem = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());

			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockOpeningBalance.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getItemId();
			}
			// GlobalUnitofMeasure u = stockOpeningBalance
			// .getGlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw u = stockOpeningBalance
					.getGlobalUnitofMeasure();
			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			if (currGlobalItem != newGlobalItem
					|| currUnitOfMeasure != newUnitofMeasure) {
				if (this.StockOpeningBalanceDaoImpl
						.isExistStockOpeningBalanceForPointGlobalItem(
								newGlobalItem, newUnitofMeasure,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId())) {
					// throw new DuplicateDataException(
					// "Opening balance has already been registered for this Global item and unit of measure");
				}
			}

			// check if product has changed, if yes update both products else
			// update only edited product
			int newQtyInUnits;
			int currQtyInUnits;
			// int currentBalance;
			int openingBalance = 0;

			int reorderLevel = 0;
			StockCurrentBalance stockCurrentBalance = null;

			try {
				openingBalance = Integer.parseInt(stockOpeningBalanceForm
						.getOpeningBalance());
			} catch (NumberFormatException e) {
			}
			try {
				reorderLevel = Integer.parseInt(stockOpeningBalanceForm
						.getReorderlevel());
			} catch (NumberFormatException e)

			{

			}

			if (currGlobalItem != newGlobalItem) {

				// update current product
				newQtyInUnits = 0;
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(currGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(currGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance());
				 */

				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(
								stockOpeningBalance.getOpeningBalance(),
								currUnitOfMeasure);

				// currentBalance = this.getCurrentBalance(currGlobalItem);
				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(currGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

				// update new product
				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, openingBalance,
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */

				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * newUnitofMeasure, openingBalance);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(openingBalance, newUnitofMeasure);

				currQtyInUnits = 0;
				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(newGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

			} else {

				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, openingBalance,
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * newUnitofMeasure, openingBalance);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(openingBalance, newUnitofMeasure);

				/*
				 * calculate current qty in units already in dbase b4 reseting
				 * stock opening balance model to reflect new values
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance());
				 */
				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(
								stockOpeningBalance.getOpeningBalance(),
								currUnitOfMeasure);

				// update current balance b4 table: newQtyInUnits is new value
				// and old value is current value in store;
				stockCurrentBalance = this.inventoryManagerImpl
						.getGlobalItemCurrentBalanceByStock(newGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl.updateGlobalItemCurrentBalance(
						newQtyInUnits, currQtyInUnits, stockCurrentBalance);

			}

			// update table now
			if (currUnitOfMeasure != newUnitofMeasure) {
				// u = new GlobalUnitofMeasure();
				u = new GlobalItemUnitofMeasureVw();

			}

			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}

			g.setItemId(newGlobalItem);
			u.setId(newUnitofMeasure);

			stockOpeningBalance.setId(stockOpeningBalanceForm.getId());
			stockOpeningBalance.setGlobalItem(g);
			stockOpeningBalance.setGlobalUnitofMeasure(u);
			stockOpeningBalance.setOpeningBalance(openingBalance);
			stockOpeningBalance.setReorderlevel(reorderLevel);
			stockOpeningBalance.setCost(stockOpeningBalanceForm.getCost());

			stockOpeningBalance.setDescription(stockOpeningBalanceForm
					.getDescription());
			stockOpeningBalance.setAction(stockOpeningBalanceForm.getAction());

			stockOpeningBalance.setModifiedDate(new Date());
			stockOpeningBalance
					.setCreateDate(DateUtils
							.formatStringToDate(stockOpeningBalanceForm
									.getDateAdded()));
			stockOpeningBalance.setOrganisation(userIdentity.getOrganisation());
			stockOpeningBalance.setCreatedBy(userIdentity.getUsername());

			stockOpeningBalance
					.setBatchno(stockOpeningBalanceForm.getBatchno());
			stockOpeningBalance.setSerialno(stockOpeningBalanceForm
					.getSerialno());

			stockOpeningBalance.setTinno(stockOpeningBalanceForm.getTinno());
			if (!stockOpeningBalanceForm.getManufacturedate().equals("")
					&& DateUtils.isValidDate(stockOpeningBalanceForm
							.getManufacturedate()))

			{
				stockOpeningBalance.setManufacturedate(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getManufacturedate()));
			} else {
				stockOpeningBalance.setManufacturedate(null);
			}

			if (!stockOpeningBalanceForm.getExpirydate().equals("")
					&& DateUtils.isValidDate(stockOpeningBalanceForm
							.getExpirydate()))

			{
				stockOpeningBalance.setExpirydate(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getExpirydate()));
			}

			else {
				stockOpeningBalance.setExpirydate(null);
			}

			this.StockOpeningBalanceDaoImpl
					.updateStockOpeningBalance(stockOpeningBalance);

		} catch (NumberFormatException e) {

			System.out.print("last edit error " + e.getMessage());

		}
		return stockOpeningBalance;
	}

	// @Transactional
	@Override
	public void delete(StockOpeningBalance stockOpeningBalance)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException {

		// update current balance b4 deletion
		// set current balance to null or do below
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockOpeningBalance.getGlobalItem();
		if (g != null) {
			globalItem = g.getItemId();
		}

		// GlobalUnitofMeasure u = stockOpeningBalance.getGlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw u = stockOpeningBalance
				.getGlobalUnitofMeasure();

		if (u != null) {
			currUnitOfMeasure = u.getId();
		}

		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
		 * stockOpeningBalance.getOpeningBalance(),
		 * AppConfig.UNIT_OF_MEASURE_UNIT);
		 */
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
		 * stockOpeningBalance.getOpeningBalance());
		 */

		int currQtyInUnits = this.unitOfMeasureConverter.convertmeasuretounits(
				stockOpeningBalance.getOpeningBalance(), currUnitOfMeasure);

		StockCurrentBalance stockCurrentBalance;
		// try {
		stockCurrentBalance = this.inventoryManagerImpl
				.getGlobalItemCurrentBalanceByStock(globalItem,
						this.userIdentity.getCurrentPointId(),
						this.userIdentity.getCurrentUnitId());
		this.inventoryManagerImpl.updateGlobalItemCurrentBalance(0,
				currQtyInUnits, stockCurrentBalance);

		this.StockOpeningBalanceDaoImpl.delete(stockOpeningBalance);
		// } catch (InvalidOpeningStockBalanceException e) {
		// }
		// soft delete stock opening balance
	}

	/*
	 * private int getPoint() { User user =
	 * userBo.getUserByEmail(userIdentity.getUsername()); int loginPoint = 0; if
	 * (user != null) { LoginSectionPoint loginSectionPoint =
	 * user.getLoginSectionPoint(); if (loginSectionPoint != null) { loginPoint
	 * = loginSectionPoint.getId(); } } return loginPoint; }
	 */

	// point stuff

	@Override
	public List<PointStockOpeningBalance> getPointStockOpeningBalances(
			int pointId, int unitId) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.StockOpeningBalanceDaoImpl.getPointStockOpeningBalances(
				pointId, unitId);
	}

	@Override
	public PointStockOpeningBalance getPointStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub

		return this.StockOpeningBalanceDaoImpl
				.getPointStockOpeningBalanceDetailById(id);
	}

	// @Transactional
	@Override
	public void savePointStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, InvalidUnitOfMeasureConfiguration {
		// TODO Auto-generated method stub
		// check if global item stock opening balance has not yet been set
		// if already set then duplicate data exception is thrown else continue

		// get object by natural id to

		/*
		 * User user = userBo.getUserByEmail(userIdentity.getUsername()); int
		 * loginPoint = 0; int loginUnit=0; if (user != null) {
		 * LoginSectionPoint loginSectionPoint = user.getLoginSectionPoint(); if
		 * (loginSectionPoint != null) { loginPoint = loginSectionPoint.getId();
		 * } loginUnit=6; }
		 */

		PointStockOpeningBalance stockOpeningBalance = null;

		try {
			int globalItemId = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
			int unitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());

			if (this.StockOpeningBalanceDaoImpl
					.isExistStockOpeningBalanceForPointGlobalItem(globalItemId,
							unitofMeasure,
							this.userIdentity.getCurrentPointId(),
							this.userIdentity.getCurrentUnitId())) {
				throw new DuplicateDataException(
						"Opening balance has already been registered for this Global item and unit of measure");
			}

			int openingBalance = 0;

			try {
				openingBalance = Integer.parseInt(stockOpeningBalanceForm
						.getOpeningBalance());
			} catch (NumberFormatException e) {

			}
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionB(globalItemId, unitofMeasure,
			 * openingBalance, AppConfig.UNIT_OF_MEASURE_UNIT);
			 */
			/*
			 * int newQtyInUnits = this.unitOfMeasureConverter
			 * .convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,
			 * openingBalance);
			 */

			int newQtyInUnits = this.unitOfMeasureConverter
					.convertmeasuretounits(openingBalance, unitofMeasure);

			Date createdDate = new Date();

			// GlobalUnitofMeasure globalUnitofMeasure = new
			// GlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw globalUnitofMeasure = new GlobalItemUnitofMeasureVw();

			GlobalItem globalItem = new GlobalItem();
			VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
			HrunitCategory unit = new HrunitCategory();

			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setItemId(globalItemId);
			visitWorkflowPoint.setId(this.userIdentity.getCurrentPointId());

			unit.setCategoryId(this.userIdentity.getCurrentUnitId());

			PointStockOpeningBalance pointstockOpeningBalance = new PointStockOpeningBalance();

			pointstockOpeningBalance
					.setGlobalUnitofMeasure(globalUnitofMeasure);
			pointstockOpeningBalance.setGlobalItem(globalItem);
			pointstockOpeningBalance.setVisitWorkflowPoint(visitWorkflowPoint);
			pointstockOpeningBalance.setUnit(userIdentity.getCurrentUnit());

			pointstockOpeningBalance.setCreatedBy(this.userIdentity
					.getUsername());
			pointstockOpeningBalance.setCreateDate(createdDate);
			pointstockOpeningBalance.setOpeningBalance(openingBalance);
			pointstockOpeningBalance.setOrganisation(userIdentity
					.getOrganisation());
			pointstockOpeningBalance
					.setDateAdded(DateUtils
							.formatStringToDate(stockOpeningBalanceForm
									.getDateAdded()));

			this.StockOpeningBalanceDaoImpl
					.savePointStockOpeningBalance(pointstockOpeningBalance);

			// lets update point global item current balance here
			// update current balance:newQtyInUnits is new value, old value is
			// zero;
			PointStockCurrentBalance pointStockCurrentBalance = null;
			try {
				pointStockCurrentBalance = this.inventoryManagerImpl
						.getPointGlobalItemCurrentBalanceByStock(globalItemId,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());

			} catch (InvalidStockLevelException e) {

				// if stock has not yet been registered in opening balance table

				pointStockCurrentBalance = new PointStockCurrentBalance();
				pointStockCurrentBalance.setGlobalItem(globalItem);
				// pointStockCurrentBalance.setGlobalUnitofMeasure(globalUnitofMeasure);

				pointStockCurrentBalance
						.setVisitWorkflowPoint(visitWorkflowPoint);

				pointStockCurrentBalance.setUnit(unit);

				pointStockCurrentBalance.setDateAdded(DateUtils
						.formatStringToDate(stockOpeningBalanceForm
								.getDateAdded()));
				pointStockCurrentBalance.setCreatedBy(this.userIdentity
						.getUsername());
				pointStockCurrentBalance.setCreateDate(createdDate);

			}
			this.inventoryManagerImpl.updatePointGlobalItemCurrentBalance(
					newQtyInUnits, 0, pointStockCurrentBalance);
		} catch (NumberFormatException e) {

		}
	}

	// @Transactional
	@Override
	public PointStockOpeningBalance editPointStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration, InvalidStockLevelException {

		// check if global item stock opening balance and corresponding unit of
		// measure has not yet been set
		// if already set then duplicate data exception is thrown else continue
		PointStockOpeningBalance stockOpeningBalance = null;

		try {
			stockOpeningBalance = this
					.getPointStockOpeningBalanceDetailById(stockOpeningBalanceForm
							.getId());

			int newGlobalItem = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());

			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockOpeningBalance.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getItemId();
			}
			// GlobalUnitofMeasure u = stockOpeningBalance
			// .getGlobalUnitofMeasure();
			GlobalItemUnitofMeasureVw u = stockOpeningBalance
					.getGlobalUnitofMeasure();
			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			if (currGlobalItem != newGlobalItem
					|| currUnitOfMeasure != newUnitofMeasure) {
				if (this.StockOpeningBalanceDaoImpl
						.isExistStockOpeningBalanceForPointGlobalItem(
								newGlobalItem, newUnitofMeasure,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId())) {
					throw new DuplicateDataException(
							"Opening balance has already been registered for this Global item and unit of measure");
				}
			}

			// check if product has changed, if yes update both products else
			// update only edited product
			int newQtyInUnits;
			int currQtyInUnits;
			// int currentBalance;
			int openingBalance = 0;
			PointStockCurrentBalance pointStockCurrentBalance = null;

			try {
				openingBalance = Integer.parseInt(stockOpeningBalanceForm
						.getOpeningBalance());
			} catch (NumberFormatException e) {
			}

			if (currGlobalItem != newGlobalItem) {

				// update current product
				newQtyInUnits = 0;
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(currGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(currGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance());
				 */

				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(
								stockOpeningBalance.getOpeningBalance(),
								currUnitOfMeasure);

				// currentBalance = this.getCurrentBalance(currGlobalItem);
				pointStockCurrentBalance = this.inventoryManagerImpl
						.getPointGlobalItemCurrentBalanceByStock(
								currGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl
						.updatePointGlobalItemCurrentBalance(newQtyInUnits,
								currQtyInUnits, pointStockCurrentBalance);

				// update new product
				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, openingBalance,
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */

				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * newUnitofMeasure, openingBalance);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(openingBalance, newUnitofMeasure);

				currQtyInUnits = 0;
				pointStockCurrentBalance = this.inventoryManagerImpl
						.getPointGlobalItemCurrentBalanceByStock(newGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl
						.updatePointGlobalItemCurrentBalance(newQtyInUnits,
								currQtyInUnits, pointStockCurrentBalance);

			} else {

				// calculate new qty in units entered by user
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * newUnitofMeasure, openingBalance,
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * newQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * newUnitofMeasure, openingBalance);
				 */
				newQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(openingBalance, newUnitofMeasure);

				/*
				 * calculate current qty in units already in dbase b4 reseting
				 * stock opening balance model to reflect new values
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionB(newGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance(),
				 * AppConfig.UNIT_OF_MEASURE_UNIT);
				 */
				/*
				 * currQtyInUnits = this.unitOfMeasureConverter
				 * .convertUnitOfMeasureToUnitOptionA(newGlobalItem,
				 * currUnitOfMeasure, stockOpeningBalance.getOpeningBalance());
				 */
				currQtyInUnits = this.unitOfMeasureConverter
						.convertmeasuretounits(
								stockOpeningBalance.getOpeningBalance(),
								currUnitOfMeasure);

				// update current balance b4 table: newQtyInUnits is new value
				// and old value is current value in store;
				pointStockCurrentBalance = this.inventoryManagerImpl
						.getPointGlobalItemCurrentBalanceByStock(newGlobalItem,
								this.userIdentity.getCurrentPointId(),
								this.userIdentity.getCurrentUnitId());
				this.inventoryManagerImpl
						.updatePointGlobalItemCurrentBalance(newQtyInUnits,
								currQtyInUnits, pointStockCurrentBalance);

			}

			// update table now
			if (currUnitOfMeasure != newUnitofMeasure) {
				// u = new GlobalUnitofMeasure();
				u = new GlobalItemUnitofMeasureVw();

			}

			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}

			g.setItemId(newGlobalItem);
			u.setId(newUnitofMeasure);

			stockOpeningBalance.setId(stockOpeningBalanceForm.getId());
			stockOpeningBalance.setGlobalItem(g);
			stockOpeningBalance.setGlobalUnitofMeasure(u);
			stockOpeningBalance.setOpeningBalance(openingBalance);
			stockOpeningBalance.setModifiedDate(new Date());
			stockOpeningBalance
					.setDateAdded(DateUtils
							.formatStringToDate(stockOpeningBalanceForm
									.getDateAdded()));
			this.StockOpeningBalanceDaoImpl
					.updatePointStockOpeningBalance(stockOpeningBalance);

		} catch (NumberFormatException e) {

		}
		return stockOpeningBalance;
	}

	private int getCurrentBalance(int globalItem) {
		// TODO Auto-generated method stub
		int currentBalance;
		try {
			currentBalance = this.inventoryManagerImpl
					.getGlobalItemCurrentBalance(globalItem);
		} catch (InvalidStockLevelException e) {
			currentBalance = 0;
		}
		return currentBalance;
	}

	// @Transactional
	@Override
	public void delete(PointStockOpeningBalance stockOpeningBalance)
			throws InvalidUnitOfMeasureConfiguration {

		// update current balance b4 deletion
		// set current balance to null or do below
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockOpeningBalance.getGlobalItem();
		if (g != null) {
			globalItem = g.getItemId();
		}

		// GlobalUnitofMeasure u = stockOpeningBalance.getGlobalUnitofMeasure();
		GlobalItemUnitofMeasureVw u = stockOpeningBalance
				.getGlobalUnitofMeasure();

		if (u != null) {
			currUnitOfMeasure = u.getId();
		}

		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
		 * stockOpeningBalance.getOpeningBalance(),
		 * AppConfig.UNIT_OF_MEASURE_UNIT);
		 */
		/*
		 * int currQtyInUnits = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
		 * stockOpeningBalance.getOpeningBalance());
		 */

		int currQtyInUnits = this.unitOfMeasureConverter.convertmeasuretounits(
				stockOpeningBalance.getOpeningBalance(), currUnitOfMeasure);

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
		// soft delete stock opening balance
		this.StockOpeningBalanceDaoImpl.delete(stockOpeningBalance);
	}

	/*
	 * private int getPoint() { User user =
	 * userBo.getUserByEmail(userIdentity.getUsername()); int loginPoint = 0; if
	 * (user != null) { LoginSectionPoint loginSectionPoint =
	 * user.getLoginSectionPoint(); if (loginSectionPoint != null) { loginPoint
	 * = loginSectionPoint.getId(); } } return loginPoint; }
	 */

}
