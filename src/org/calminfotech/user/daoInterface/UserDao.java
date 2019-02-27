package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface UserDao {

	List<User> fetchAll();

	void save(User user);

	void delete(User user);

	void update(User user);

	User getUserByEmail(String email, Integer orgid);

	User getUserById(int userId);

	User getUserByEmailAndPassword(String email, String password, Integer orgid);

	List<Organisation> getOrganisationByEmail(String email);

	List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password, Integer orgid);

	public List<User> fetchAllByOrganisation(Organisation organisation);

	List<User> checkUserCredentialsByEmailAndPasswordWebAccess(String email,
			String password, Integer orgid);

	User getUserByEmail(String email);

}
