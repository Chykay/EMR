package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.UserAssignment_log;

public interface UserAssgnmentBo {

	public UserAssignment getUserAssgnmentById(int id);
	
	public void save(UserAssignment userAssignment);
	
	public void save(UserAssignment userAssignment,UserAssignment_log userAssignment_log);
	
	List<UserAssignment> deleteAllUserCheckedValues(Integer userId);
	
	 
}
