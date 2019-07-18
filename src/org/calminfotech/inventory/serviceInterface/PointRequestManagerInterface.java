package org.calminfotech.inventory.serviceInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.calminfotech.inventory.exceptions.InvalidStockLevelException;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.RequestForm;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.models.Storeissue_log;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;

public interface PointRequestManagerInterface {

	public PointRequest savePointRequest(RequestForm orderRequestForm);

	public PointRequest getPointRequestById(int reqId)
			throws RecordNotFoundException;

	public Storeissue_log getStoreIssueById(int logId)
			throws RecordNotFoundException;

	public List<PointRequest> getPointRequests(
			DateSearchForm pointRequestSearchForm);

	public List<PointRequest> getUserPointRequests(
			DateSearchForm pointRequestSearchForm)
			throws RecordNotFoundException;

	public void deletePointRequest(PointRequest pointRequest);

	public void approvePointLineRequest(int reqLineId, int qtyToApprove)
			throws RecordNotFoundException, InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException;

	public PointRequestLine getPointRequestLineById(int id)
			throws RecordNotFoundException;

	public void disapprovePointRequestLine(PointRequestLine pointRequestLine);

	public void issuePointRequestLine(PointRequestLine pointRequestLine)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration;

	public void revissuePointRequestLine(PointRequestLine pointRequestLine)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException;

	public void revissuePointRequestLine(Storeissue_log storissueLog)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidStockLevelException;

	public Map getGlobalItemsQuantityAvailable(
			Set<PointRequestLine> pointRequestLines);

	public void deletePointRequestLine(PointRequestLine pointRequestLine);

	public List<PointRequest> getPointRequestsMake(
			DateSearchForm pointRequestSearchForm);

	public void issuePointRequestLine(PointRequestLine pointRequestLine,
			VisitWorkflowUserConfigurationForm vform)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration;

	public List<PointRequest> getPointRequestsTop100(
			DateSearchForm pointRequestSearchForm);

	public List<PointRequest> getPointRequestsMakeTop100(
			DateSearchForm pointRequestSearchForm);

}
