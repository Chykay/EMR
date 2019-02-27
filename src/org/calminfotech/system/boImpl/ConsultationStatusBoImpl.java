package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.ConsultationStatusBo;
import org.calminfotech.system.daoInterface.ConsultationStatusDao;
import org.calminfotech.system.forms.ConsultationStatusForm;
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultationStatusBoImpl implements ConsultationStatusBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private ConsultationStatusDao consultationStatusDao;

	@Override
	public List<ConsultationStatus> fetchAll() {
		// TODO Auto-generated method stub
		return this.consultationStatusDao.fetchAll();
	}

	@Override
	public List<ConsultationStatus> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//Organisation organisation = this.userIdentity.getOrganisation();
		//return this.consultationStatusDao.fetchAllByOrganisation(organisation);
	return null;
	}

	@Override
	public ConsultationStatus getStatusById(int id) {
		// TODO Auto-generated method stub
		return this.consultationStatusDao.getStatusById(id);
	}

	@Override
	public void save(ConsultationStatusForm consultationStatusForm) {
		// TODO Auto-generated method stub
		ConsultationStatus status = new ConsultationStatus();
		status.setType(consultationStatusForm.getType());
		status.setCreatedBy(this.userIdentity.getUsername());
		//status.setOrganisation(this.userIdentity.getOrganisation());

		this.consultationStatusDao.save(status);
	}

	@Override
	public void delete(ConsultationStatus consultationStatus) {
		// TODO Auto-generated method stub
		this.consultationStatusDao.delete(consultationStatus);

	}
	@Override
	public void update(ConsultationStatusForm consultationStatusForm) {
		// TODO Auto-generated method stub
		ConsultationStatus status = this.getStatusById(consultationStatusForm
				.getId());
		status.setType(consultationStatusForm.getType());

		this.consultationStatusDao.update(status);
	}

	@Override
	public void changeStartPoint(ConsultationStatusForm form) {
		// TODO Auto-generated method stub
		// Get and change the current start point
		ConsultationStatus currentStartPoint = this.getStartPointStatus();
		if (null != currentStartPoint) {
			currentStartPoint.setStartPoint(false);
			this.consultationStatusDao.update(currentStartPoint);
		}

		// Get and change target start point
		ConsultationStatus targetStartPoint = this.getStatusById(form.getId());
		targetStartPoint.setStartPoint(true);
		this.consultationStatusDao.update(targetStartPoint);
	}

	@Override
	public void changeEndPoint(ConsultationStatusForm form) {

		// Get and change current end point
		ConsultationStatus currentEndPoint = this.getEndPointStatus();
		if (null != currentEndPoint) {
			currentEndPoint.setEndPoint(false);
			this.consultationStatusDao.update(currentEndPoint);
		}

		// Get and change target end point
		ConsultationStatus targetEndPoint = this.getStatusById(form.getId());
		targetEndPoint.setEndPoint(true);
		this.consultationStatusDao.update(targetEndPoint);
	}

	@Override
	public ConsultationStatus getStartPointStatus() {
	//	return this.consultationStatusDao.getStartPointStatus(this.userIdentity
		//		.getOrganisation());
		return null;
	}

	@Override
	public ConsultationStatus getEndPointStatus() {
		// TODO Auto-generated method stub
		//return this.consultationStatusDao.getEndPointStatus(this.userIdentity
			//	.getOrganisation());
	return null;
	}

}
