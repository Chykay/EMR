package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.SecurityQuestion;

public interface SecurityQuestionBo {

	void save(SecurityQuestion securityQuestion);

	void update(SecurityQuestion securityQuestion);

	void delete(SecurityQuestion securityQuestion);

	SecurityQuestion getQuestionById(int id);

	List<SecurityQuestion> fetchAll();
}
