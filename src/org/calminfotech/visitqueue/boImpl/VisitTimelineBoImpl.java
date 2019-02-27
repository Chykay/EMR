package org.calminfotech.visitqueue.boImpl;

import java.util.Date;
import java.util.List;

//import org.calminfotech.consultation.daoInterface.VisitConsultationDao;
//import org.calminfotech.consultation.forms.VisitDoctorForm;
//import org.calminfotech.consultation.forms.VisitLaboratoryForm;
//import org.calminfotech.consultation.forms.VisitPharmacyForm;
//import org.calminfotech.consultation.forms.VisitVitalsForm;
//import org.calminfotech.consultation.forms.VisitationForm;
//import org.calminfotech.consultation.models.VisitConsultation;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.boInterface.VisitTimelineBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.daoInterface.VisitTimelineDao;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.calminfotech.visitqueue.models.VisitTimeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.hr.forms.AssignForm;

@Service
public class VisitTimelineBoImpl implements VisitTimelineBo {
	
	@Autowired
	private HrunitCategoryBo unitCatBo;

	@Autowired
	private VisitTimelineDao visittimelineDao;

	@Autowired
	private VisitStatusBo visitStatusBo;
	
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private UserIdentity userIdentity;
	
//	@Autowired
//	private VisitConsultationDao visitConsultationDao;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

	@Autowired
	private UserBo userBo;

	

	
	
	
	
	
	@Override
	public List<VisitTimeline> fetchAllByOrganisation(int id) {
		// TODO Auto-generated method stub
		return this.visittimelineDao.fetchAllByOrganisation(id);
	}

	@Override
	public List<VisitTimeline> fetchByVisitId(int visitid) {
		// TODO Auto-generated method stub
		return this.visittimelineDao.fetchByVisitId(visitid);
	}

	

	@Override
	public VisitTimeline getVisitationById(int id)
 {
		// TODO Auto-generated method stub
		return this.visittimelineDao.getVisitationById(id);
	}
	
	@Override
	public void save(VisitTimeline timeline){
	// TODO Auto-generated method stub
	this.visittimelineDao.save(timeline);
		}

	@Override
	public void delete(VisitTimeline timeline) {
		// TODO Auto-generated method stub
		this.visittimelineDao.delete(timeline);
	}

	
	@Override
	public void update(VisitTimeline timeline) {
		// TODO Auto-generated method stub
		this.visittimelineDao.update(timeline);
	}

}
