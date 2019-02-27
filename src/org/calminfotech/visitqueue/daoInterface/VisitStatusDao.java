package org.calminfotech.visitqueue.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.visitqueue.models.VisitStatus;

public interface VisitStatusDao {

	public List<VisitStatus> fetchAll();

	public List<VisitStatus> fetchAll(Organisation organisation);

	public VisitStatus getStatusById(int id);

	public void save(VisitStatus status);

	public void delete(VisitStatus status);

	public void update(VisitStatus status);

	public VisitStatus getStartPointStatus(Organisation organisation);

	public VisitStatus getEndPointStatus(Organisation organisation);

	public VisitStatus getNotEndPointStatus(Organisation organisation);

}
