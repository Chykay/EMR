package org.calminfotech.inventory.service;

import java.util.List;

import org.calminfotech.inventory.daoInterface.InventoryDaoInterface;
import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.system.models.UnitItem;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;

@Service
public class InventoryManagerImpl implements InventoryManagerInterface {

	@Autowired
	private InventoryDaoInterface inventoryDao;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private GlobalItemBo itemBo;

	// @Autowired
	// private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	/*
	 * public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
	 * int currentBalance, int globalItemId) { double currBalIncrementVal = 0;
	 * if (oldValue == 0) { currBalIncrementVal = newValue; } else { double
	 * percentageChange = ((newValue - oldValue) / oldValue) * 100;
	 * currBalIncrementVal = (percentageChange / 100) * oldValue; } //
	 * System.out.print(currBalIncrementVal + "/in-updt/" + currentBalance);
	 * currentBalance += currBalIncrementVal;
	 * this.inventoryDao.updateGlobalItemCurrentBalance(currentBalance,
	 * globalItemId);
	 * 
	 * }
	 */
	@Override
	public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
			StockCurrentBalance stockCurrentBalance) {

		if (stockCurrentBalance != null) {
			double currBalIncrementVal = 0;

			int currentBalance = stockCurrentBalance.getCurrentBalance();
			/*
			 * when old val is 0 then this is new instance we simply increase
			 * current balance with new value
			 */

			if (oldValue == 0) {
				currBalIncrementVal = newValue;
			} else {
				double percentageChange = ((newValue - oldValue) / oldValue) * 100;
				currBalIncrementVal = (percentageChange / 100) * oldValue;
			}

			System.out.print("New Value " + newValue);

			System.out.print("Old Value " + oldValue);

			System.out.print("current " + currentBalance);

			System.out.print("incomming " + currBalIncrementVal);

			currentBalance += currBalIncrementVal;

			System.out.print("New Balance  " + currentBalance);

			stockCurrentBalance.setCurrentBalance(currentBalance);

			this.inventoryDao
					.updateGlobalItemCurrentBalance(stockCurrentBalance);
		}

	}

	@Override
	public void updateGlobalItemCurrentBalanceNew(Integer qty,
			StockCurrentBalance stockCurrentBalance) {

		if (stockCurrentBalance != null) {
			double currBalIncrementVal = 0;

			Integer currentBalance = stockCurrentBalance.getCurrentBalance();
			if (currentBalance == null)

			{
				currentBalance = 0;
			}
			/*
			 * when old val is 0 then this is new instance we simply increase
			 * current balance with new value
			 */

			currentBalance += qty;

			stockCurrentBalance.setCurrentBalance(currentBalance);

			this.inventoryDao
					.updateGlobalItemCurrentBalance(stockCurrentBalance);
		}

	}

	@Override
	public void updatePointGlobalItemCurrentBalance(double newValue,
			int oldValue, PointStockCurrentBalance pointStockCurrentBalance) {
		double currBalIncrementVal = 0.00;
		int currentBalance = 0;
		if (pointStockCurrentBalance != null) {

			currentBalance = pointStockCurrentBalance.getCurrentBalance();

			/*
			 * wen old val is 0 then this is new instance we simply increase
			 * current balance with new value
			 */
			if (oldValue == 0) {
				currBalIncrementVal = newValue;
			} else {
				// double percentageChange = ((newValue - oldValue) / oldValue)
				// * 100;
				// currBalIncrementVal = (percentageChange / 100) * oldValue;
				currBalIncrementVal = newValue - oldValue;
			}
			// System.out.print(currBalIncrementVal + "/in-updt/" +
			// currentBalance);
			currentBalance += currBalIncrementVal;

			pointStockCurrentBalance.setCurrentBalance(currentBalance);
			// pointStockCurrentBalance.setGlobalItem(globalItem);

			this.inventoryDao
					.updatePointGlobalItemCurrentBalance(pointStockCurrentBalance);

		}

	}

	@Override
	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId) {
		// TODO Auto-generated method stub
		return this.inventoryDao.fetchGlobalItemUnitOfMeasure(globalItemId);
	}

	@Override
	public int getGlobalItemCurrentBalance(int globalItemId)
			throws InvalidStockLevelException {
		return this.inventoryDao.getGlobalItemCurrentBalance(globalItemId);
	}

	/*
	 * @Override public PointStockCurrentBalance
	 * getGlobalItemCurrentBalanceByPoint(int globalItem,int pointId) throws
	 * InvalidOpeningStockBalanceException{
	 * 
	 * return
	 * this.inventoryDaoImpl.getGlobalItemCurrentBalanceByPoint(globalItem,
	 * pointId);
	 * 
	 * }
	 */

	@Override
	public PointStockCurrentBalance getPointGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException {

		return this.inventoryDao.getPointGlobalItemCurrentBalanceByStock(
				globalItem, pointId, unitId);

	}

	@Override
	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int pointId, int unitId) {

		return this.inventoryDao.getPointStockCurrentBalances(pointId, unitId);
	}

	/*
	 * @Override public int getPointGlobalItemCurrentBalance(int globalItem, int
	 * currentPointId) throws InvalidOpeningStockBalanceException { // TODO
	 * Auto-generated method stub return
	 * this.inventoryDaoImpl.getPointGlobalItemCurrentBalance(globalItem,
	 * currentPointId); }
	 */

	@Override
	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId, int currentUnitId)
			throws InvalidStockLevelException {
		return this.inventoryDao.getPointGlobalItemCurrentBalance(globalItem,
				currentPointId, currentUnitId);

	}

	@Override
	public int getGlobalItemCurrentBalance(int globalItem, int currentPointId,
			int currentUnitId) throws InvalidOpeningStockBalanceException {
		return this.inventoryDao.getGlobalItemCurrentBalance(globalItem,
				currentPointId, currentUnitId);

	}

	/*
	 * @Override public List<UnitItem> getGlobalItemUnitOfMeasurements( int
	 * globalItemId) { return this.inventoryDaoImpl
	 * .getGlobalItemUnitOfMeasurements(globalItemId); }
	 * 
	 * @Override public List<GlobalItemUnitofMeasure>
	 * getGlobalItemUnitOfMeasurements( int globalItemId) { return
	 * this.inventoryDaoImpl .getGlobalItemUnitOfMeasurements(globalItemId); }
	 */

	@Override
	public boolean isPointStockAvailble(int globalItemId, int unitOfMeasure,
			int quantity, int currentPointId, int currentUnitId)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidOpeningStockBalanceException {
		// TODO Auto-generated method stub
		int currentBalance;
		// try
		// {
		currentBalance = this.getGlobalItemCurrentBalance(globalItemId,
				currentPointId, currentUnitId);
		// }
		// catch (InvalidOpeningStockBalanceException e)

		// {

		// StockCurrentBalance pscb = new StockCurrentBalance();
		// pscb.setGlobalItem(this.itemBo.getGlobalItemById(globalItemId));
		// pscb.setCurrentBalance(0);
		// pscb.setCreateDate(new Date(System.currentTimeMillis()));
		// pscb.setCreatedBy(userIdentity.getUsername());
		// pscb.setDeleted(false);
		// / pscb.setOrganisation(userIdentity.getOrganisation().getId());
		// pscb.setUnit(userIdentity.getCurrentUnit());
		// pscb.setVisitWorkflowPoint(userIdentity.getCurrentUnit().getPoint());

		// pscb.setCreateDate(new Date(System.currentTimeMillis()));
		// System.out.print("Egbami oo ");

		// inventoryDao.updatePointGlobalItemCurrentBalance(pscb);
		// currentBalance=0;
		// }

		// convert unit of measure to unit quantity
		/*
		 * int qtyInUnit = this.unitOfMeasureConverter
		 * .convertUnitOfMeasureToUnitOptionA(globalItemId, unitOfMeasure,
		 * quantity);
		 */

		int qtyInUnit = this.unitOfMeasureConverter.convertmeasuretounits(
				quantity, unitOfMeasure);

		if (currentBalance >= qtyInUnit) {
			return true;
		}

		return true;

	}

	@Override
	public StockCurrentBalance getGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException {
		// TODO Auto-generated method stub
		return this.inventoryDao.getGlobalItemCurrentBalanceByStock(globalItem,
				pointId, unitId);
	}

	@Override
	public List<StockCurrentBalance> getStockCurrentBalances(int pointId,
			int unitId) {
		// TODO Auto-generated method stub
		return this.inventoryDao.getStockCurrentBalances(pointId, unitId);
	}

	@Override
	public List<StockCurrentBalance> getAllStockCurrentBalancesByStock(
			int GlobalItemId) {
		// TODO Auto-generated method stub
		return this.inventoryDao
				.getAllStockCurrentBalancesByStock(GlobalItemId);
	}

	@Override
	public void loadStockMeasureBalances(List<StockCurrentBalance> stockbalances)
			throws InvalidUnitOfMeasureConfiguration {
		// TODO Auto-generated method stub
		// Set<StockMeasurement> prescriptionmeasurements= null;

		// List<StockCurrentBalance> lst=
		// this.inventoriesManager.getStockCurrentBalances(12,
		// this.userIdentity.getCurrentUnitId());

		// /loading it
		for (StockCurrentBalance lstv : stockbalances)

		// System.out.print("Am passing in the get"+
		// this.globalItem.getItemId());
		{
			lstv.setPrescriptionmeasurements(unitOfMeasureConverter
					.convertunitstomeasure(lstv.getCurrentBalance(),
							lstv.getGlobalItem()));

		}
		/*
		 * // /printing it out System.out.print("size" + stockbalances.size());
		 * 
		 * for (StockCurrentBalance lstv : stockbalances) {
		 * 
		 * for (StockMeasurement pm : lstv.getPrescriptionmeasurements()) {
		 * System.out.print("GlobalName" +
		 * lstv.getGlobalItem().getGlobalitemName());
		 * System.out.print("Measure Name" + pm.getMeasurename());
		 * System.out.print("Quantity" + pm.getQuantity());
		 * 
		 * }
		 * 
		 * }
		 */

	}

	@Override
	public List<StockCurrentBalance> getAllStockCurrentBalances() {
		// TODO Auto-generated method stub
		return this.inventoryDao.getAllStockCurrentBalances();
	}

}
