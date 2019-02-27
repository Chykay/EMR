package org.calminfotech.visitqueue.boInterface;

import java.util.List;

import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitTimeline;

public interface VisitTimelineBo {
	

	public List<VisitTimeline> fetchAllByOrganisation(int id);
	
	public List<VisitTimeline> fetchByVisitId(int visitid);

	public VisitTimeline getVisitationById(int id);
	
	public void save(VisitTimeline timeline);

	public void delete(VisitTimeline timeline);
	
	public void update(VisitTimeline timeline);
}
