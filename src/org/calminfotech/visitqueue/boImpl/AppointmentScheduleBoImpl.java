package org.calminfotech.visitqueue.boImpl;

import java.util.Date;

import org.calminfotech.visitqueue.models.AppointmentSchedule;
import org.calminfotech.visitqueue.boInterface.AppointmentScheduleBo;

import java.util.List;
import org.calminfotech.visitqueue.daoInterface.AppointmentScheduleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentScheduleBoImpl implements AppointmentScheduleBo {

	@Autowired
	private AppointmentScheduleDao appscheduleDao;
	
	
	@Override
	public void save(AppointmentSchedule appSchedule) {
		// TODO Auto-generated method stub
		this.appscheduleDao.save(appSchedule);
	}

	@Override
	public void update(AppointmentSchedule appSchedule) {
		// TODO Auto-generated method stub
		this.appscheduleDao.update(appSchedule);
	}

	@Override
	public AppointmentSchedule getAppointmentScheduleById(int id) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.getAppointmentScheduleById(id);
	}

	@Override
	public List<AppointmentSchedule> fetchallbytop100org(Integer orgid) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.fetchallbytop100org(orgid);
		}

	
	@Override
	public List<AppointmentSchedule> fetchallbydateorg(Date dat1, Date dat2,
			Integer orgid) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.fetchallbydateorg(dat1,dat2,orgid);
		}

	@Override
	public List<AppointmentSchedule> fetchallbydateuserorg(Date dat1, Date dat2,
			Integer userid, Integer orgid) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.fetchallbydateuserorg(dat1,dat2,userid,orgid);
	}

	@Override
	public List<AppointmentSchedule> fetchallbydatepatientorg(Date dat1, Date dat2,
			Integer patientid, Integer orgid) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.fetchallbydatepatientorg(dat1,dat2,patientid,orgid);
	}

	@Override
	public List<AppointmentSchedule> fetchallbydateuserpatientorg(Date dat1,
			Date dat2, Integer userid, Integer patientid, Integer orgid) {
		// TODO Auto-generated method stub
		return this.appscheduleDao.fetchallbydateuserpatientorg(dat1,dat2,userid,patientid,orgid);
	}
	
	

}
