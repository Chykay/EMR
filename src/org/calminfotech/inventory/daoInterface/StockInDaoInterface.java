package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.models.PointStockIn;
import org.calminfotech.inventory.models.PointStockInLine;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;

public interface StockInDaoInterface {

	public void saveStockInBatch(StockIn stockInBatch);

	public StockIn getStockInBatchDetailsView(String batchId)
			throws RecordNotFoundException;

	public List<StockIn> getStockInBatchesList(DateSearchForm stockInSearchForm);

	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public void editStockInBatch(StockIn stockInBatch);

	public void delete(StockIn stockInBatch);

	// line supply

	public void saveStockInLineItem(StockInLine stockInLineItem);

	public List<StockInLine> getStockInLineItems(int batchId);

	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException;

	public void editStockInLineItem(StockInLine stockInLineItem);

	public void deleteStockInLineItem(StockInLine stockInLineItem);

	// point stuff

	public List<PointStockIn> getStockInBatchesListByPoint(int currentPointId);

	public void savePointStockInBatch(PointStockIn stockInBatch);

	public PointStockIn getPointStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public void deletePointStockIn(PointStockIn stockInBatch);

	public void savePointStockInLineItem(PointStockInLine stockInLineItem);

	public PointStockInLine getPointStockInLineItemDetailById(int id)
			throws RecordNotFoundException;

	public void editPointStockInLineItem(PointStockInLine stockInLineItem);

	public void deletePointStockInLineItem(PointStockInLine stockInLineItem);

	public List<PointStockIn> getPointStockInBatchesList(
			DateSearchForm stockInSearchForm);

	public void editPointStockInBatch(PointStockIn stockInBatch);

	public List<StockIn> getStockInBatchesListTop100(
			DateSearchForm stockInSearchForm);

	public List<PointStockIn> getPointStockInBatchesListTop100(
			DateSearchForm stockInSearchForm);

	public StockInLine saveStockInLineItem2(StockInLine stockInLineItem);

}
