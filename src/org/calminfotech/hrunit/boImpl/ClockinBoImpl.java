package org.calminfotech.hrunit.boImpl;

import java.util.List;


import org.calminfotech.hrunit.boInterface.ClockinBo;
import org.calminfotech.hrunit.daoInterface.ClockinDao;
import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockinBoImpl implements ClockinBo {
	
	@Autowired
	private ClockinDao clockinDao;

	@Override
	public ClockAssignment getClockinAssgnmentById(int id) {
		return this.clockinDao.getClockinAssgnmentById(id);
	}

	@Override
	public void save(ClockAssignment clockAssignment) {
	this.clockinDao.save(clockAssignment);
		
	}

	
	@Override
	public void save(ClockinLog clocklog) {
	this.clockinDao.save(clocklog);
		
	}
	@Override
	public List<ClockAssignment> deleteAllCheckedValues(Integer userId) {
		return this.clockinDao.deleteAllCheckedValues(userId);
	}

	@Override
	public List<ClockAssignment> fetchAllByUnitId(int unitId) {
		return this.clockinDao.fetchAllByUnitId(unitId);
	}

	

	@Override
	public List<ClockAssignment> fetchAllByUserId(int userid) {
		return this.clockinDao.fetchAllByUserId(userid);
	}

	
	
	@Override
	public void delete(ClockAssignment clockAssignment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ClockAssignment clockAssignment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClockAssignment getClockingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/*@Override
	public ClockAssignment getClockinById(int id) {
		return this.clockinDao.getClockinById(id);
	}

	@Override
	public List<ClockAssignment> fetchAllByOrganisation(int organisationId) {
		return this.clockinDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<ClockAssignment> fetchAllByUnitId(int unitId) {
		return this.clockinDao.fetchAllByUnitId(unitId);
	}
	return this.clockinDao.getClockinByUnitId(unitId);
*/
	

}
