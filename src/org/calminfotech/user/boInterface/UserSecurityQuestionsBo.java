package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.UserSecurityQuestions;

public interface UserSecurityQuestionsBo {

	void save(UserSecurityQuestions userSecurityQuestion);

	void update(UserSecurityQuestions userSecurityQuestion);

	void delete(UserSecurityQuestions userSecurityQuestion);

	List<UserSecurityQuestions> fetchAll();

	UserSecurityQuestions getUserSecurityQuestionsById(int id);

	UserSecurityQuestions getUserSecurityQuestionsByUserId(int userId);

}
