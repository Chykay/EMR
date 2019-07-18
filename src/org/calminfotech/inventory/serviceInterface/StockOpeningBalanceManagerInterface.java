package org.calminfotech.inventory.serviceInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.DuplicateDataException;
import org.calminfotech.inventory.exceptions.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;

public interface StockOpeningBalanceManagerInterface {

	//stock opening balance
	
	
	public List<StockOpeningBalance> getStockOpeningBalances(Integer id)throws RecordNotFoundException;

	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)throws RecordNotFoundException;

	public void saveStockOpeningBalance(StockOpeningBalanceForm stockOpeningBalanceForm)throws
	DuplicateDataException,InvalidUnitOfMeasureConfiguration;

	//public StockOpeningBalance editStockOpeningBalance(StockOpeningBalanceForm stockOpeningBalanceForm)throws
	//DuplicateDataException,RecordNotFoundException, InvalidUnitOfMeasureConfiguration;

	public StockOpeningBalance editStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)throws
			DuplicateDataException,RecordNotFoundException,InvalidUnitOfMeasureConfiguration, InvalidStockLevelException;

	

	public void delete(StockOpeningBalance stockOpeningBalance)throws InvalidUnitOfMeasureConfiguration, InvalidStockLevelException ;


	//point stuff
	
	//public List<PointStockOpeningBalance> getStockOpeningBalances(int pointId)throws RecordNotFoundException;
	List<PointStockOpeningBalance> getPointStockOpeningBalances(int pointId,
			int unitId) throws RecordNotFoundException;
  
	public void savePointStockOpeningBalance(StockOpeningBalanceForm stockOpeningBalanceForm)throws
	DuplicateDataException,InvalidUnitOfMeasureConfiguration;

	public void delete(PointStockOpeningBalance stockOpeningBalance)throws InvalidUnitOfMeasureConfiguration ;

	public PointStockOpeningBalance getPointStockOpeningBalanceDetailById(int id)throws RecordNotFoundException;

	public PointStockOpeningBalance editPointStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)throws
			DuplicateDataException,RecordNotFoundException,InvalidUnitOfMeasureConfiguration, InvalidStockLevelException;

	




}
