package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.user.models.RoleAssignment;
import org.calminfotech.user.models.RoleAssignment_log;

public interface RoleAssgnmentDao {
	
	public RoleAssignment getRoleAssgnmentById(int id);
	
	public void save(RoleAssignment roleAssignment);
	
	List<RoleAssignment> deleteAllCheckedValues(Integer roleId);

	void save(RoleAssignment roleAssignment,
			RoleAssignment_log roleAssignmentlog);
}