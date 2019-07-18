package org.calminfotech.inventory.serviceInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.PointStockInForm;
import org.calminfotech.inventory.forms.StockInForm;
import org.calminfotech.inventory.forms.StockInLineForm;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;

public interface StockInManagerInterface {

	public List<StockIn> getStockInBatchesList(DateSearchForm stockInSearchForm)
			throws RecordNotFoundException;

	public List<PointStockIn> getPointStockInBatchesList(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException;

	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public StockIn saveStockInBatch(StockInForm stockInBatchForm);

	public StockIn editStockInBatch(StockInForm stockInBatchForm)
			throws RecordNotFoundException;

	public StockIn getBatchSupplyDetailsView(String batchId)
			throws RecordNotFoundException;

	public void delete(StockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration;

	// line supply

	public List<StockInLine> getStockInLineItems(int batchId)
			throws RecordNotFoundException;

	public StockInLine saveStockInLineItem(StockInLineForm stockInlineItemForm)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException;

	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException;

	public StockInLine editStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration, RecordNotFoundException,
			InvalidStockLevelException;

	public void deleteStockInLineItem(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration;

	// point stuffs

	public List<PointStockIn> getStockInBatchesListByPoint(int currentPointId);

	public PointStockIn getPointStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public PointStockIn savePointStockInBatch(PointStockInForm stockInBatchForm);

	public PointStockIn editPointStockInBatch(PointStockInForm stockInBatchForm)
			throws RecordNotFoundException;

	public void deletePointStockIn(PointStockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration;

	public PointStockInLine savePointStockInLineItem(
			StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException;

	public PointStockInLine getPointStockInLineItemDetailById(int id)
			throws RecordNotFoundException;

	public PointStockInLine editPointStockInLineItem(
			StockInLineForm stockInLineItemForm)
			throws InvalidStockLevelException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration;

	public void deletePointStockInLineItem(PointStockInLine stockInLineItem);

	public List<StockIn> getStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException;

	public List<PointStockIn> getPointStockInBatchesListTop100(
			DateSearchForm stockInSearchForm) throws RecordNotFoundException;

	public StockInLine saveStockInLineItem2(StockInLine stockInLineItem);

}
