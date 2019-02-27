package org.calminfotech.visitqueue.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
////import org.calminfotech.system.models.Department;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;

public interface VisitWorkflowPointDao {

	public List<VisitWorkflowPoint> fetchAllByOrganisation(int id);
	
	public List<VisitWorkflowPoint> fetchAll();
	

	public VisitWorkflowPoint getWorkflowPointById(int id);
	
	public List<VisitWorkflowPoint> fetchFontDeskPoint(int id ,int section);
	
	public void save(VisitWorkflowPoint workflowPoint);

	public void delete(VisitWorkflowPoint workflowPoint);

	public void update(VisitWorkflowPoint workflowPoint);

	public VisitWorkflowPoint getWorkflowStartPoint();
	
	public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(HrunitCategory unit);

	public VisitWorkflowPoint getWorkflowEndPoint();

	public VisitWorkflowPoint getPointByName(String string);

	List<VisitWorkflowPoint> fetchAllByorg(Organisation organisation);

	

}
