package org.calminfotech.hrunit.boImpl;

import java.util.List;

import org.calminfotech.hrunit.boInterface.GetClockingUnitProcBo;
import org.calminfotech.hrunit.daoInterface.GetClockingUnitProcDao;
import org.calminfotech.hrunit.models.GetClockingUnitProc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GetClockingUnitProcBoImpl implements GetClockingUnitProcBo {
	
	@Autowired
	private GetClockingUnitProcDao getClockingUnitProcDao;

	@Override
	public List<GetClockingUnitProc> fetchClockinUnit(Integer userId) {
		// TODO Auto-generated method stub
		return this.getClockingUnitProcDao.fetchClockinUnit(userId);
	}

}
