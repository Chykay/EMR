package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.RoleAssignment;
import org.calminfotech.user.models.RoleAssignment_log;

public interface RoleAssgnmentBo {
	
	public RoleAssignment getRoleAssgnmentById(int id);
	
	public void save(RoleAssignment roleAssignment);
	
	List<RoleAssignment> deleteAllCheckedValues(Integer roleId);

	public void save(RoleAssignment roleAssign,
			RoleAssignment_log roleAssign_log);
}
