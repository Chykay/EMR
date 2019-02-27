package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.UserAssignment_log;

public interface UserAssgnmentDao {
	
	public UserAssignment getUserAssgnmentById(int id);
	
	public void save(UserAssignment userAssignment);
	
	List<UserAssignment> deleteAllUserCheckedValues(Integer userId);

	public void save(UserAssignment userAssignment,
			UserAssignment_log userAssignment_log);
}
