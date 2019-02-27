package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.daoInterface.GroupDao;
import org.calminfotech.user.models.Group;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupBoImpl implements GroupBo {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void save(Group group) {
		// TODO Auto-generated method stub
		groupDao.save(group);
	}

	@Override
	public void delete(Group group) {
		// TODO Auto-generated method stub
		groupDao.delete(group);
	}

	@Override
	public void update(Group group) {
		// TODO Auto-generated method stub
		groupDao.update(group);
	}

	@Override
	public Group getGroupById(int id) {
		// TODO Auto-generated method stub
		return groupDao.getGroupById(id);
	}

	@Override
	public List<Group> fetchAll() {
		// TODO Auto-generated method stub
		return groupDao.fetchAll();
	}

	@Override
	public List<Group> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.groupDao.fetchAllByOrganisation(this.userIdentity
				.getOrganisation());
	}

	@Override
	public Group getAdminGroupByOrganisation() {
		return this.groupDao.getAdminGroupByOrganisation(this.userIdentity
				.getOrganisation());
	}

	@Override
	public Group getAdminGroupByOrganisation(Organisation organsation) {
		// TODO Auto-generated method stub
		return this.groupDao.getAdminGroupByOrganisation(organsation);
	}

}
