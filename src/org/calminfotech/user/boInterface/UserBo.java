package org.calminfotech.user.boInterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserBo {

	void save(User user);

	void delete(User user);

	void update(User user);

	User getUserByEmail(String email, Integer orgid);

	User getUserByEmail(String email);

	List<User> fetchAll();

	User getUserById(int userId);

	List<Organisation> getOrganisationByEmail(String email);

	User getUserByEmailAndPassword(String email, String password, Integer orgid);

	List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password, Integer orgid);

	void createUser(RedirectAttributes redirectAttributes, UserForm userForm,
			HttpServletRequest httpServletRequest);

	public List<User> fetchAllByOrganisation();

	List<User> checkUserCredentialsByEmailAndPasswordWebAccess(String usr,
			String encrypt, Integer orgid);

}
