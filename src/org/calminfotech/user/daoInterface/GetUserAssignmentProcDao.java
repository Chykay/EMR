package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.GetUserAssignmentProc;

public interface GetUserAssignmentProcDao {

	List<GetUserAssignmentProc> fetchUserPermission(Integer userId,Integer orgId);
}
