package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.daoInterface.RoleDao;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class RoleBoImpl implements RoleBo {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public void save(Role role) {
		this.roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		this.roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		this.roleDao.update(role);
	}

	@Override
	public Role getRoleById(int id) {
		return this.roleDao.getRoleById(id);
	}

	@Override
	public List<Role> fetchAll() {
		return this.roleDao.fetchAll();
	}

	@Override
	public List<Role> fetchAllRoleByOrganisation(Organisation organisation) {
		return this.roleDao.fetchAllRoleByOrganisation(organisation);
	}

	@Override
	public Role getRoleByAdmin(Organisation organisation) {
		return this.roleDao.getRoleByAdmin(this.userIdentity.getOrganisation());
	}
}
