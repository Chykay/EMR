package org.calminfotech.hrunit.daoInterface;

import java.util.List;

import org.calminfotech.hrunit.models.GetClockingUnitProc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface GetClockingUnitProcDao {
	List<GetClockingUnitProc> fetchClockinUnit(Integer userId);

}
