package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.Authorization;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;

public interface PermissionDao {

	void save(Permission permission);
	
	void update(Permission permission);
	
	void delete(Permission permission);
	
	Permission getPermissionById(int id);
	
	List<Authorization> fetchAuthorizationlist(Integer userid, String auhtcode);
	
	List<Permission> fetchAll();
	
	List<Permission> getPermissionByRole(Role role);
	
	List<Permission> fetchByOrganisation(Organisation organisation);
	
	List<Permission> fetchMenuPermissionByOrganisation(Organisation organisation, String val);
}
