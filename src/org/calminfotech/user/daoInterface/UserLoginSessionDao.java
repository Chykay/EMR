package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.UserLoginSession;

public interface UserLoginSessionDao {

	public void save(UserLoginSession userLoginSession);

	public List<UserLoginSession> fetchAll();

	public List<UserLoginSession> fetchAllByUsername(String username);
	
	UserLoginSession getUserLoginSessionById(int id);
	
	List<UserLoginSession> search(UserLoginSession startDate, UserLoginSession endDate);

}
