package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;
//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;

public interface StockOpeningBalanceDaoInterface {

	// stock opening

	public void saveStockOpeningBalance(StockOpeningBalance stockOpeningBalance);

	public List<StockOpeningBalance> getStockOpeningBalances(Integer id)
			throws RecordNotFoundException;

	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException;

	public void updateStockOpeningBalance(
			StockOpeningBalance stockOpeningBalance);

	public void delete(StockOpeningBalance stockOpeningBalance);

	public boolean isExistStockOpeningBalanceGlobalItem(String globalItemID);

	public boolean isExistStockOpeningBalanceForGlobalItem(
			int newGlobalItemIdInt, int newUnitofMeasureInt);

	// point stuff

	public void savePointStockOpeningBalance(
			PointStockOpeningBalance stockOpeningBalance);

	// public List<PointStockOpeningBalance> getPointStockOpeningBalances(int
	// pointId)throws RecordNotFoundException ;
	public List<PointStockOpeningBalance> getPointStockOpeningBalances(
			int pointId, int unitId) throws RecordNotFoundException;

	public void delete(PointStockOpeningBalance stockOpeningBalance);

	// public boolean isExistStockOpeningBalanceForPointGlobalItem(int
	// globalItemId,
	// int unitofMeasure, int currentPointId);
	public boolean isExistStockOpeningBalanceForPointGlobalItem(
			int globalItemId, int unitofMeasure, Integer currentPointId,
			int currentUnitId);

	public PointStockOpeningBalance getPointStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException;

	public void updatePointStockOpeningBalance(
			PointStockOpeningBalance stockOpeningBalance);

}
