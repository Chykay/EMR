package org.calminfotech.inventory.serviceInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.system.models.GlobalUnitofMeasure;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;
//import org.calminfotech.system.models.UnitItem;

public interface InventoryManagerInterface {

	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId);

	public int getGlobalItemCurrentBalance(int globalItemId)
			throws InvalidStockLevelException;

	// public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
	// int currentBalance, int globalItemId);

	public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
			StockCurrentBalance stockCurrentBalance);

	// public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(int
	// globalItem,int pointId) throws InvalidOpeningStockBalanceException;

	public PointStockCurrentBalance getPointGlobalItemCurrentBalanceByStock(
			int globalItemId, int currentPointId, int currentUnitId)
			throws InvalidStockLevelException;

	public StockCurrentBalance getGlobalItemCurrentBalanceByStock(
			int globalItemId, int currentPointId, int currentUnitId)
			throws InvalidStockLevelException;

	void updatePointGlobalItemCurrentBalance(double newValue, int oldValue,
			PointStockCurrentBalance pointStockCurrentBalance);

	// public List<PointStockCurrentBalance> getPointStockCurrentBalances();

	// public int getPointGlobalItemCurrentBalance(int globalItem,
	// int currentPointId) throws InvalidOpeningStockBalanceException;

	public void loadStockMeasureBalances(List<StockCurrentBalance> stockbalances)
			throws InvalidUnitOfMeasureConfiguration;

	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId, int currentUnitId)
			throws InvalidStockLevelException;

	/*
	 * public List<UnitItem> getGlobalItemUnitOfMeasurements( int globalItemId);
	 */

	public boolean isPointStockAvailble(int globalItemId, int unitOfMeasure,
			int quantity, int currentPointId, int currentUnitId)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidOpeningStockBalanceException;

	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int pointId, int unitId);

	public List<StockCurrentBalance> getStockCurrentBalances(int pointId,
			int unitId);

	public void updateGlobalItemCurrentBalanceNew(Integer qty,
			StockCurrentBalance stockCurrentBalance);

	int getGlobalItemCurrentBalance(int globalItem, int currentPointId,
			int currentUnitId) throws InvalidOpeningStockBalanceException;

	public List<StockCurrentBalance> getAllStockCurrentBalances();

	public List<StockCurrentBalance> getAllStockCurrentBalancesByStock(
			int GlobalItemId);

}
