package org.calminfotech.visitqueue.boImpl;

import java.util.Date;
import java.util.List;

/*import org.calminfotech.consultation.daoInterface.VisitConsultationDao;
import org.calminfotech.consultation.forms.VisitDoctorForm;
//import org.calminfotech.consultation.forms.VisitLaboratoryForm;
import org.calminfotech.consultation.forms.VisitPharmacyForm;
import org.calminfotech.consultation.forms.VisitVitalsForm;
import org.calminfotech.consultation.forms.VisitationForm;
import org.calminfotech.consultation.models.VisitConsultation;*/
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.boInterface.VisitBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.daoInterface.VisitDao;
import org.calminfotech.visitqueue.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.calminfotech.hr.forms.AssignForm;

@Service
public class VisitBoImpl implements VisitBo {
	
	@Autowired
	private HrunitCategoryBo unitCatBo;

	@Autowired
	private VisitDao visitDao;

	@Autowired
	private VisitStatusBo visitStatusBo;
	
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private UserIdentity userIdentity;
	/*
	@Autowired
	private VisitConsultationDao visitConsultationDao;*/

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

	@Autowired
	private UserBo userBo;

	

	
	
	
	
	
	@Override
	public List<Visit> fetchAllByOrganisation(int id) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByOrganisation(id);
	}

	

	
	@Override
	public List<Visit> fetchAllByOrganisationmyqueue(int organisationid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByOrganisationmyqueue(organisationid);
	}

	
	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparam( Date dat1, Date dat2,int statusid)
	{
	// TODO Auto-generated method stub
			return this.visitDao.fetchAllByOrganisationmyqueuebyparam( dat1, dat2,statusid);
		}
	

	@Override
	public List<Visit> fetchAllByOrganisationmyqueuevitals(int organisationid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByOrganisationmyqueuevitals(organisationid);
	}

	
	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamvitals( Date dat1, Date dat2,int statusid)
	{
	// TODO Auto-generated method stub
			return this.visitDao.fetchAllByOrganisationmyqueuebyparamvitals( dat1, dat2,statusid);
		}

	
	
	@Override
	public List<Visit> fetchAllByOrganisationmyqueuefrontdesk(int organisationid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByOrganisationmyqueuefrontdesk(organisationid);
	}


	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamfrontdesk( Date dat1, Date dat2,int statusid)
	{
	// TODO Auto-generated method stub
			return this.visitDao.fetchAllByOrganisationmyqueuebyparamfrontdesk( dat1, dat2,statusid);
		}
	


	@Override
	public List<Visit> fetchAllByOrganisationmyqueueaccount(int organisationid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByOrganisationmyqueueaccount(organisationid);
	}


	@Override
	public List<Visit> fetchAllByOrganisationmyqueuebyparamaccount( Date dat1, Date dat2,int statusid)
	{
	// TODO Auto-generated method stub
			return this.visitDao.fetchAllByOrganisationmyqueuebyparamaccount( dat1, dat2,statusid);
		}
	

	
	
	
	
	@Override
	public List<Visit> fetchByPatientId(int patientid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchByPatientId(patientid);
	}

	@Override
	public List<Visit>  fetchAllbytoday(int organisationid) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllbytoday(organisationid);
	}

	

	@Override
	public Visit getVisitationById(int id)
 {
		// TODO Auto-generated method stub
		return this.visitDao.getVisitationById(id);
	}
	
	@Override
	public Visit save(Visit visit){
	// TODO Auto-generated method stub
	return		this.visitDao.save(visit);
		}

	@Override
	public void delete(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.delete(visit);
	}

	
	@Override
	public void update(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.update(visit);
	}

	@Override
	public List<Visit> fetchAllByThese(int userId, Date from, Date to,
			int mstatus, boolean chkothers) {
		// TODO Auto-generated method stub

		return this.visitDao.fetchAllByThese(userId,
				this.userIdentity.getCurrentPointId(), from, to, mstatus,
				chkothers);
		// return this.visitDao.fetchAllByThese(userId, from,
		// to,mstatus,chkothers);
	}




	@Override
	public List<Visit> fetchAllByThese(int userId, Date from, Date to,
			int mstatus, String chkothers) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Visit> fetchAllByThese(int userId, int pointId, Date from,
			Date to, int mstatus, boolean chkothers) {
		// TODO Auto-generated method stub
		return null;
	}
}
