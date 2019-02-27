package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.GetUserAssignmentProcBo;
import org.calminfotech.user.daoInterface.GetUserAssignmentProcDao;
import org.calminfotech.user.models.GetUserAssignmentProc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class GetUserAssignmentProcBoImpl implements GetUserAssignmentProcBo {
	
	@Autowired
	private GetUserAssignmentProcDao getUserAssignmentProcDao;

	@Override
	public List<GetUserAssignmentProc> fetchUserPermission(Integer userId,Integer orgId) {
		// TODO Auto-generated method stub
		return this.getUserAssignmentProcDao.fetchUserPermission(userId,orgId);
	}

}
