package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.RoleAssgnmentBo;
import org.calminfotech.user.models.RoleAssignment;
import org.calminfotech.user.models.RoleAssignment_log;
import org.calminfotech.system.daoInterface.RoleAssgnmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleAssgnmentBoImpl implements RoleAssgnmentBo {

	@Autowired
	private RoleAssgnmentDao roleAssgnmentDao;
	
	@Override
	public RoleAssignment getRoleAssgnmentById(int id) {
		return this.roleAssgnmentDao.getRoleAssgnmentById(id);
	}
	
	@Override
	public void save(RoleAssignment roleAssignment) {
		this.roleAssgnmentDao.save(roleAssignment);
		
	}

	
	
	@Override
	public List<RoleAssignment> deleteAllCheckedValues(Integer roleId) {
		return this.roleAssgnmentDao.deleteAllCheckedValues(roleId);
	}

	@Override
	public void save(RoleAssignment roleAssign,
			RoleAssignment_log roleAssign_log) {
		// TODO Auto-generated method stub
		 this.roleAssgnmentDao.save(roleAssign, roleAssign_log);
		
	}

}
