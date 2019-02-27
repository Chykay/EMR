package org.calminfotech.visitqueue.daoInterface;

import java.util.Date;
import java.util.List;

//import org.calminfotech.consultation.models.VisitConsultation;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.Organisation;

import org.calminfotech.visitqueue.models.AppointmentSchedule;

public interface AppointmentScheduleDao {
	
	public List< AppointmentSchedule> fetchAll();

	public List< AppointmentSchedule> fetchAllByOrgainsation(
			Organisation organisation);

	public List< AppointmentSchedule> fetchAllByPatient(Patient patient);
	
//	public List< AppointmentSchedule> fetchAllByConsultation(VisitConsultation consultation);

	public void save(AppointmentSchedule  appointment);

	public  AppointmentSchedule getAppointmentScheduleById(int id);

	public void update( AppointmentSchedule appointment);

	public List<AppointmentSchedule> fetchallbydateorg(Date dat1, Date dat2 ,Integer orgid);

	public List<AppointmentSchedule> fetchallbydateuserorg(Date dat1, Date dat2,Integer userid,Integer orgid);

	public List<AppointmentSchedule> fetchallbydatepatientorg(Date dat1, Date dat2,Integer patientid,Integer orgid);

	public List<AppointmentSchedule> fetchallbydateuserpatientorg(Date dat1, Date dat2,Integer userid,Integer patientid,Integer orgid);

	public List<AppointmentSchedule> fetchallbytop100org(Integer orgid);

}
