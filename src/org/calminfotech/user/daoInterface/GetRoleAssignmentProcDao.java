package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.GetRoleAssignmentProc;

public interface GetRoleAssignmentProcDao {
	
	List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId,Integer orgId);
}
