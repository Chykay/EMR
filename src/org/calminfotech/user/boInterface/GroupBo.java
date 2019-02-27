package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.Group;

public interface GroupBo {

	void save(Group group);

	void delete(Group group);

	void update(Group group);

	Group getGroupById(int id);

	List<Group> fetchAll();

	public List<Group> fetchAllByOrganisation();

	public Group getAdminGroupByOrganisation();

	public Group getAdminGroupByOrganisation(Organisation organsation);
}
