package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.GetRoleAssignmentProc;

public interface GetRoleAssignmentProcBo {
	
	List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId,Integer orgId);
}
