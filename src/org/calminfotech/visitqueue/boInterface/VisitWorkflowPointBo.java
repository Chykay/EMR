package org.calminfotech.visitqueue.boInterface;

import java.util.List;

import org.calminfotech.visitqueue.forms.VisitWorkflowPointForm;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;



public interface VisitWorkflowPointBo {

	public List<VisitWorkflowPoint> fetchAllByOrganisation(int id);
	
	public List<VisitWorkflowPoint> fetchAll();

	public VisitWorkflowPoint getWorkflowPointById(int id);
	
	List<VisitWorkflowPoint> fetchFontDeskPoint(int section);


	public void delete(VisitWorkflowPoint workflowPoint);

	public void update(VisitWorkflowPointForm workflowPoint);

	List<VisitWorkflowPoint> getvWorkflowPointByUnitId(int unit_id);

	VisitWorkflowPoint getPointByName(String string);

	VisitWorkflowPoint save(VisitWorkflowPointForm form);

	public VisitWorkflowPoint getWorkflowStartPoint(Integer id);


}
