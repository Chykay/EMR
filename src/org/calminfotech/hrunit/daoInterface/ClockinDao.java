package org.calminfotech.hrunit.daoInterface;

import java.util.List;

import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;
import org.calminfotech.hrunit.models.ClockinLog;
//import org.calminfotech.setup.models.UnitCategoryView;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ClockinDao {
	
public ClockAssignment getClockinAssgnmentById(int id);
	
	public void save(ClockAssignment clockAssignment);
	
	
	public void save(ClockinLog clocklog);
	
	public List<ClockAssignment> deleteAllCheckedValues(Integer userId);
	
	/*public ClockAssignment getClockinById(int id);
	
	public ClockAssignment getClockinByUnitId(int unitId);
	public List<ClockAssignment> fetchAllByOrganisation(int organisationId);
	*/
	public List<ClockAssignment> fetchAllByUnitId(int unitId);

	public 	List<ClockAssignment> fetchAllByUserId(int userid);
	
	

}
