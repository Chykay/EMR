package org.calminfotech.visitqueue.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.daoInterface.AppointmentScheduleDao;
import org.calminfotech.visitqueue.models.AppointmentSchedule;
//import org.calminfotech.consultation.models.VisitConsultation;
import org.calminfotech.patient.models.Patient;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/*@Repository
@Transactional
public class AppointmentScheduleDaoImpl implements AppointmentScheduleDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	
	

	@Override
	public List<AppointmentSchedule> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(AppointmentSchedule.class).list();
	}

	@Override
	public List<AppointmentSchedule> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(AppointmentSchedule.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<AppointmentSchedule> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(AppointmentSchedule.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId())).list();
	
	}

	@Override
	public List<AppointmentSchedule> fetchAllByConsultation(
			VisitConsultation consultation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(AppointmentSchedule.class)
				.createAlias("consultation", "consultation")
				.add(Restrictions.eq("consultation.id", consultation.getId())).list();
	
	}

	@Override
	public void save(AppointmentSchedule appointment) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(appointment);
	
	}

	@Override
	public AppointmentSchedule getAppointmentScheduleById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule WHERE eventId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (AppointmentSchedule) list.get(0);

		return null;
	}

	@Override
	public void update(AppointmentSchedule appointment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(appointment);
		
	}

	@Override
	public List<AppointmentSchedule> fetchallbydateorg(Date dat1, Date dat2,
			Integer orgid) {
		// TODO Auto-generated method stub
		

		List<AppointmentSchedule> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule  WHERE startDay >= ? and endDay <= ? and organisation_id=? and isDeleted=0 order by id desc")
			.setParameter(0, dat1)
			.setParameter(1, dat2)
			.setParameter(2, userIdentity.getOrganisation().getId())
			.list();
		
		Iterator f  = (Iterator) list.iterator();
		while ( f.hasNext())
			
		{
			f.next();
			
		}
		
	
		
	
		
		
		System.out.print("dat" + dat1);
		System.out.print("dat2" + dat2);
		
	return list;
		
		
		
		
		
	}

	@Override
	public  List<AppointmentSchedule> fetchallbydateuserorg(Date dat1, Date dat2,
			Integer userid, Integer orgid) {
		// TODO Auto-generated method stub


		List<AppointmentSchedule> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule  WHERE startDay >= ? and endDay <= ? and user.userId=? and organisation_id=? and isDeleted=0 order by id desc")
			.setParameter(0, dat1)
			.setParameter(1, dat2)
			.setParameter(2, userid)
			.setParameter(3, userIdentity.getOrganisation().getId())
			.list();
	return list;
	}

	@Override
	public  List<AppointmentSchedule> fetchallbydatepatientorg(Date dat1, Date dat2,
			Integer patientid, Integer orgid) {
		// TODO Auto-generated method stub

		List<AppointmentSchedule> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule  WHERE startDay >= ? and endDay <= ? and patient.patientId=? and organisation_id=? and isDeleted=0 order by id desc")
			.setParameter(0, dat1)
			.setParameter(1, dat2)
			.setParameter(2, patientid)
			.setParameter(3, userIdentity.getOrganisation().getId())
			.list();
	return list;	}

	@Override
	
	public  List<AppointmentSchedule> fetchallbydateuserpatientorg(Date dat1,
			Date dat2, Integer userid, Integer patientid, Integer orgid) {
		// TODO Auto-generated method stub
		List<AppointmentSchedule> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule  WHERE startDay >= ? and endDay <= ? and user.userId=? and  patient.patientId=? and organisation_id=? and isDeleted=0 order by id desc")
			.setParameter(0, dat1)
			.setParameter(1, dat2)
			.setParameter(2, userid)
			
			.setParameter(3, patientid)
			.setParameter(4, userIdentity.getOrganisation().getId())
			.list();
	return list;
	}

	@Override
	public List<AppointmentSchedule> fetchallbytop100org(Integer orgid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<AppointmentSchedule> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM AppointmentSchedule  where organisation_id=? and isDeleted=0 order by id desc")
			.setParameter(0, orgid)
			.setMaxResults(100)
			.list();
		
		
	return list;
	}
	
	

}
*/