package org.calminfotech.visitqueue.daoInterface;

import java.util.Date;
import java.util.List;

//import org.calminfotech.consultation.models.VisitConsultation;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.calminfotech.visitqueue.models.VisitTimeline;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;

public interface VisitTimelineDao {


	public List<VisitTimeline> fetchAllByOrganisation(int id);
	
	public List<VisitTimeline> fetchByVisitId(int visitid);

	public VisitTimeline getVisitationById(int id);
	
	public void save(VisitTimeline timeline);

	public void delete(VisitTimeline timeline);
	
	public void update(VisitTimeline timeline);
	
}
