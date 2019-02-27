package org.calminfotech.hrunit.boInterface;

import java.util.List;

//import org.calminfotech.hrunit.forms.GetClockingUnitProcForm;
//import org.calminfotech.patient.models.PatientMedicalHistory;
//import org.calminfotech.hrunit.forms.ClockingForm;


import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;

	
import org.calminfotech.user.models.UserLoginSession;


public interface ClockinBo {


	public void save(ClockinLog clocklog);
	
public void save(ClockAssignment clockAssignment);
	
	
   public    List<ClockAssignment> deleteAllCheckedValues(Integer userId);
	
	public List<ClockAssignment> fetchAllByUserId(int userid);
	
	public List<ClockAssignment> fetchAllByUnitId(int unitId);
	
	public void delete(ClockAssignment clockAssignment);
	
	public void update(ClockAssignment clockAssignment);

	public ClockAssignment getClockingById(int id);

	public ClockAssignment getClockinAssgnmentById(int id);
	
	
}
