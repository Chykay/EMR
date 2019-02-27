package org.calminfotech.visitqueue.boInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.visitqueue.models.AppointmentSchedule;


public interface AppointmentScheduleBo {
	
	
	public void save(AppointmentSchedule appSchedule);

	public void update(AppointmentSchedule appSchedule);

	public AppointmentSchedule getAppointmentScheduleById(int id);
	
	
	public List<AppointmentSchedule> fetchallbydateorg(Date dat1, Date dat2 ,Integer orgid);

	public List<AppointmentSchedule> fetchallbydateuserorg(Date dat1, Date dat2,Integer userid,Integer orgid);

	public List<AppointmentSchedule> fetchallbydatepatientorg(Date dat1, Date dat2,Integer patientid,Integer orgid);

	public List<AppointmentSchedule> fetchallbydateuserpatientorg(Date dat1, Date dat2,Integer userid,Integer patientid,Integer orgid);

public	List<AppointmentSchedule> fetchallbytop100org(Integer orgid);


}
