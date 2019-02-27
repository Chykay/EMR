package org.calminfotech.hrunit.boInterface;

import java.util.List;

import org.calminfotech.hrunit.models.GetClockingUnitProc;

public interface GetClockingUnitProcBo {
	List<GetClockingUnitProc> fetchClockinUnit(Integer userId);

}
