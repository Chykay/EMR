package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.GetUserAssignmentProc;

public interface GetUserAssignmentProcBo {

	List<GetUserAssignmentProc> fetchUserPermission(Integer userId, Integer orgId);
}
