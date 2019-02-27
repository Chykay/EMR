package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.boInterface.PermissionBo;
import org.calminfotech.user.daoInterface.PermissionDao;
import org.calminfotech.user.models.Authorization;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionBoImpl implements PermissionBo {
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
/*	@Autowired
	private Role role;*/

	@Override
	public void save(Permission permission) {
		this.permissionDao.save(permission);
	}

	@Override
	public void update(Permission permission) {
		this.permissionDao.update(permission);
	}

	@Override
	public void delete(Permission permission) {
		this.permissionDao.delete(permission);
	}
	
	@Override
	public Permission getPermissionById(int id) {
		return this.permissionDao.getPermissionById(id);
	}

	@Override
	public List<Permission> fetchAll() {
		return this.permissionDao.fetchAll();
	}

	@Override
	public List<Permission> getPermissionByRole(Role role) {
		return this.permissionDao.getPermissionByRole(role);
	}

	@Override
	public List<Permission> fetchByOrganisation(Organisation organisation) {
		// pass organisation into the method for useridentity bean
		return this.permissionDao.fetchByOrganisation(userIdentity.getOrganisation());
	}

	@Override
	public List<Permission> fetchMenuPermissionByOrganisation(
			Organisation organisation, String val) {
		// TODO Auto-generated method stub
		return this.permissionDao.fetchMenuPermissionByOrganisation(organisation, val);
	}
	@Override

	public List<Authorization> fecthAuthorizationlist(Integer userid, String authcode)
	{
	
		// TODO Auto-generated method stub
		return this.permissionDao.fetchAuthorizationlist( userid,  authcode);
	}
}
