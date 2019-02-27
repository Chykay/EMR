package org.calminfotech.visitqueue.boInterface;

import java.util.List;

import org.calminfotech.system.forms.ConsultationStatusForm;
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.visitqueue.forms.VisitStatusForm;
import org.calminfotech.visitqueue.models.VisitStatus;

public interface VisitStatusBo {

	public List<VisitStatus> fetchAll();

	public List<VisitStatus> fetchAllByOrganisation();

	public VisitStatus getStatusById(int id);

	public VisitStatus save(VisitStatusForm statusForm);

	public void delete(VisitStatus status);

	public void update(VisitStatusForm statusForm);
	
	public void changeStartPoint(VisitStatusForm form);

	public void changeEndPoint(VisitStatusForm form);

	public VisitStatus getStartPointStatus();

	public VisitStatus getEndPointStatus();

	public VisitStatus getNotEndPointStatus();

}
