package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.SecurityQuestion;

public interface SecurityQuestionDao {

	void save(SecurityQuestion securityQuestion);

	void update(SecurityQuestion securityQuestion);

	void delete(SecurityQuestion securityQuestion);

	SecurityQuestion getQuestionById(int id);

	List<SecurityQuestion> fetchAll();
}
