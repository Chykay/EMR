package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.ConsultationStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ConsultationStatusDao {

	public List<ConsultationStatus> fetchAll();

	public List<ConsultationStatus> fetchAllByOrganisation(
			Organisation organisation);

	public ConsultationStatus getStatusById(int id);

	public void save(ConsultationStatus consultationStatus);

	public void delete(ConsultationStatus consultationStatus);

	public void update(ConsultationStatus consultationStatus);

	public ConsultationStatus getStartPointStatus(Organisation organisation);

	public ConsultationStatus getEndPointStatus(Organisation organisation);

}
