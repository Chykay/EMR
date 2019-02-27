package org.calminfotech.system.daoInterface;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.models.Group;

public interface ResourceDao {

	public List<Resource> fetchAll();
	
	public List<Resource> fetchAllByOrganisation(Organisation organisation);

	public Resource getReourceById(int id);

	public Resource getResourceByUrlPattern(String urlPattern);

	public void save(Resource resource);
	
	public void update(Resource resource);

	public void clearResources(Organisation organisation);

	Resource getResourceByUrlPattern(String urlPattern,
			Organisation organisation);

	public List<Resource> getResourcesByGroup(Group group);

}
