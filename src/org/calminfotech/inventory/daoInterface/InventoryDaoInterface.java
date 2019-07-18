package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.system.models.GlobalUnitofMeasure;
//import org.calminfotech.system.models.UnitItem;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;

public interface InventoryDaoInterface {

	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId);

	// public HrunitCategoryItem getUnitOfMeasureToDetails(int globalItemId,
	// int currentUnitOfMeasureFrom);

	public int getGlobalItemCurrentBalance(int globalItemIdInt)
			throws InvalidStockLevelException;

	public void updateGlobalItemCurrentBalance(int currentBalance,
			int globalItemId);

	// public HrunitCategoryItem getUnitOfMeasureFromDetails(
	// int globalItemId, int unitOfMeasureToConvert);

	public int getGlobalItemUnitOfMeasureContainedUnit(int globalItemId,
			int unitOfMeasure);

	public void updatePointGlobalItemCurrentBalance(
			PointStockCurrentBalance pointStockCurrentBalance);

	public void updateGlobalItemCurrentBalance(
			StockCurrentBalance stockCurrentBalance);

	public void save(StockCurrentBalance stockCurrentBalance);

	public void save(PointStockCurrentBalance stockCurrentBalance);

	// public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(
	// int globalItem, int pointId) throws InvalidOpeningStockBalanceException;

	public PointStockCurrentBalance getPointGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException;

	public StockCurrentBalance getGlobalItemCurrentBalanceByStock(
			int globalItem, int pointId, int unitId)
			throws InvalidStockLevelException;

	// public List<PointStockCurrentBalance> getPointStockCurrentBalances(
	// int currentPointId);

	public List<StockCurrentBalance> getStockCurrentBalances(
			int currentPointId, int currentUnitId);

	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int currentPointId, int currentUnitId);

	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId, int currentUnitId)
			throws InvalidStockLevelException;

	// public List<UnitItem> getGlobalItemUnitOfMeasurements(
	// int globalItemId);

	// public UnitItem getUnitOfMeasureFromDetailsRwId(int unitOfMeasureid);
	public GlobalItemUnitofMeasureVw getUnitOfMeasureFromDetailsRwId(
			int unitOfMeasureid);

	public int getGlobalItemCurrentBalance(int globalItem, int currentPointId,
			int currentUnitId);

	public List<StockCurrentBalance> getAllStockCurrentBalances();

	public List<StockCurrentBalance> getAllStockCurrentBalancesByStock(
			int Globalitem);

	// public int getPointGlobalItemCurrentBalance(int globalItem,
	// int currentPointId) throws InvalidOpeningStockBalanceException;

}
