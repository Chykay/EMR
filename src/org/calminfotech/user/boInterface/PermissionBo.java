package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.Authorization;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;

public interface PermissionBo {

	void save(Permission permission);
	
	void update(Permission permission);
	
	void delete(Permission permission);
	
	Permission getPermissionById(int id);
	
	List<Permission> fetchAll();
	
	List<Authorization> fecthAuthorizationlist(Integer userid, String auhtcode);
	
	
	List<Permission> getPermissionByRole(Role role);
	
	List<Permission> fetchByOrganisation(Organisation organisation);
	
	List<Permission> fetchMenuPermissionByOrganisation(Organisation organisation, String val);
}
