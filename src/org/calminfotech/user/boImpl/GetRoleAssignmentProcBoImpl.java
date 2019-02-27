package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.GetRoleAssignmentProcBo;
import org.calminfotech.user.daoInterface.GetRoleAssignmentProcDao;
import org.calminfotech.user.models.GetRoleAssignmentProc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetRoleAssignmentProcBoImpl implements GetRoleAssignmentProcBo {

	@Autowired
	private GetRoleAssignmentProcDao roleAssignmentProcDao;

	@Override
	public List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId,Integer orgId) {
		// TODO Auto-generated method stub
		return this.roleAssignmentProcDao.fetchRolePermission(roleId,orgId);
	}
}
