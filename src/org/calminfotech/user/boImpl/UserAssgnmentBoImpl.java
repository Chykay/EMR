package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.UserAssgnmentBo;
import org.calminfotech.user.daoInterface.UserAssgnmentDao;
import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.UserAssignment_log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAssgnmentBoImpl implements UserAssgnmentBo {
	
	@Autowired
	private UserAssgnmentDao userAssigned;

	@Override
	public void save(UserAssignment userAssignment) {
		userAssigned.save(userAssignment);
	}

	@Override
	public List<UserAssignment> deleteAllUserCheckedValues(Integer userId) {
		// TODO Auto-generated method stub
		return this.userAssigned.deleteAllUserCheckedValues(userId);
	}

	@Override
	public UserAssignment getUserAssgnmentById(int id) {
		// TODO Auto-generated method stub
		return this.userAssigned.getUserAssgnmentById(id);
	}

	@Override
	public void save(UserAssignment userAssignment,
			UserAssignment_log userAssignment_log) {
		// TODO Auto-generated method stub
		userAssigned.save(userAssignment,userAssignment_log);
	}

}
