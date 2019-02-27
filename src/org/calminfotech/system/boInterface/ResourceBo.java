package org.calminfotech.system.boInterface;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.forms.ResourceActionForm;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.models.Group;

public interface ResourceBo {

	public List<Resource> fetchAll();

	public List<Resource> fetchAllByOrganisation();

	public Resource getResourceById(int id);

	public Resource getResourceByUrlPattern(String urlPattern);

	public void save(Resource resource);

	public void update(Resource resource);

	public void clearResources();

	public Group denyAccess(ResourceActionForm resourceActionForm);

	public Group allowAccess(ResourceActionForm resourceActionForm);
	
	public void reBuildResources();

	void buildResourcesForOrganisation(Organisation organsation);

	public List<Resource> getResourcesByGroup(Group group);
}
