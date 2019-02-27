package org.calminfotech.visitqueue.daoInterface;

import java.util.Date;
import java.util.List;

//import org.calminfotech.consultation.models.VisitConsultation;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;

public interface VisitDao {


	public List<Visit> fetchAllByOrganisation(int id);
	
	public List<Visit> fetchByPatientId(int patientId);
	
	public List<Visit> fetchAllbytoday(int organisationid);

	public List<Visit> fetchAllByOrganisationmyqueue(int organisationid);
	

	public List<Visit> fetchAllByOrganisationmyqueuebyparam(Date dat1, Date dat2,int statusid );
	

	public List<Visit> fetchAllByOrganisationmyqueuefrontdesk(int organisationid);
	

	public List<Visit> fetchAllByOrganisationmyqueuebyparamfrontdesk( Date dat1, Date dat2,int statusid);

	

	public List<Visit> fetchAllByOrganisationmyqueueaccount(int organisationid);
	

	public List<Visit> fetchAllByOrganisationmyqueuebyparamaccount( Date dat1, Date dat2,int statusid);

	
	
	
	public List<Visit> fetchAllByThese(int userId,Date from, Date to,int mstatus, String chkothers );
	
	public List<Visit> fetchAllByThese(int userId, int pointId, Date from,
			Date to, int mstatus, boolean chkothers);
	public Visit getVisitationById(int id);
	
	public Visit save(Visit visit);

	public void delete(Visit visit);
	
	public void update(Visit visit);

	public List<Visit> fetchAllByOrganisationmyqueuevitals(int organisationid);

	public List<Visit> fetchAllByOrganisationmyqueuebyparamvitals(Date dat1,
			Date dat2, int statusid);
	
}
