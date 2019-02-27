package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.forms.ConsultationStatusForm;
import org.calminfotech.system.models.ConsultationStatus;

public interface ConsultationStatusBo {

	public List<ConsultationStatus> fetchAll();

	public List<ConsultationStatus> fetchAllByOrganisation();

	public ConsultationStatus getStatusById(int id);

	public void save(ConsultationStatusForm consultationStatusForm);

	public void delete(ConsultationStatus consultationStatus);

	public void update(ConsultationStatusForm consultationStatusForm);

	public void changeStartPoint(ConsultationStatusForm form);

	public void changeEndPoint(ConsultationStatusForm form);

	public ConsultationStatus getStartPointStatus();

	public ConsultationStatus getEndPointStatus();

}
