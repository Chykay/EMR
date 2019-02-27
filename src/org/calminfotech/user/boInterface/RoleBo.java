package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.Role;

public interface RoleBo {

	void save(Role role);

	void delete(Role role);

	void update(Role role);

	Role getRoleById(int id);

	List<Role> fetchAll();

	List<Role> fetchAllRoleByOrganisation(Organisation organisation);

	Role getRoleByAdmin(Organisation organisation);
}
