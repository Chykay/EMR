package org.calminfotech.inventory.daoInterface;

import java.util.Collection;
import java.util.List;

import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.models.Storeissue_log;

public interface PointRequestDaoInterface {

	public void savePointRequest(PointRequest pointRequest);

	public void updatePointRequest(PointRequest pointRequest);

	public PointRequest getPointRequestById(int reqId)
			throws RecordNotFoundException;

	public List<PointRequest> getPointRequests(String dateOfRequest,
			String searchCriteria);

	public void deletePointRequest(PointRequest pointRequest);

	public PointRequestLine getPointRequestLineById(int reqLineId)
			throws RecordNotFoundException;

	public int getTotalGlobalItemRequestApproved(int globalItem)
			throws InvalidUnitOfMeasureConfiguration;

	public void updatePointRequestLine(PointRequestLine pointRequestLine);

	// public List<PointRequest> getPointRequestsByPoint(String dateOfReq,int
	// currentPointId)throws RecordNotFoundException ;

	public Collection<Character> getPntRequestLineRequestsStatuses(
			int pointRequestLineId, int pointRequestId);

	public void deletePointRequestLine(PointRequestLine pointRequestLine);

	public List<PointRequest> getPointRequestsByPoint(String dateOfRequest,
			int loginPoint, int loginUnit) throws RecordNotFoundException;

	public List<PointRequest> getPointRequestsMake(String dateOfRequest,
			String searchCriteria);

	public void save(Storeissue_log storeissuelog);

	public void update(Storeissue_log storeissuelog);

	public void issuePointRequestLine(PointRequestLine pointRequestLine);

	public Storeissue_log getStoreIssueById(int id)
			throws RecordNotFoundException;

	public List<PointRequest> getPointRequestsMakeTop100(String dateOfRequest,
			String searchCriteria);

	public List<PointRequest> getPointRequestsTop100(String dateOfRequest,
			String searchCriteria);

}
