package org.calminfotech.system.boImpl;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.daoInterface.ResourceDao;
import org.calminfotech.system.forms.ResourceActionForm;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.models.Group;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
@Transactional
public class ResourceBoImpl implements ResourceBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private GroupBo groupBo;

	@Autowired
	private RequestMappingHandlerMapping mappingHandler;

	@Override
	public List<Resource> fetchAll() {
		// TODO Auto-generated method stub
		return this.resourceDao.fetchAll();
	}

	@Override
	public Resource getResourceById(int id) {
		// TODO Auto-generated method stub
		return this.resourceDao.getReourceById(id);
	}

	@Override
	public Resource getResourceByUrlPattern(String urlPattern) {
		//return this.resourceDao.getResourceByUrlPattern(urlPattern,
			//	this.userIdentity.getOrganisation());
return null;
	}

	@Override
	public void save(Resource resource) {
		// TODO Auto-generated method stub
		this.resourceDao.save(resource);
	}

	@Override
	public void update(Resource resource) {
		// TODO Auto-generated method stub
		this.resourceDao.update(resource);
	}

	@Override
	public void clearResources() {
		// TODO Auto-generated method stub
	//	this.resourceDao.clearResources(this.userIdentity.getOrganisation());
	
	}

	@Override
	public List<Resource> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return this.resourceDao.fetchAllByOrganisation(this.userIdentity
				//.getOrganisation());
	return null;
	}

	@Override
	public Group denyAccess(ResourceActionForm resourceActionForm) {
		// TODO Auto-generated method stub
		Group group = this.groupBo
				.getGroupById(resourceActionForm.getGroupId());
		Resource resource = this.getResourceById(resourceActionForm
				.getResourceId());

		// Remove relationship
		resource.getGroups().remove(group);
		this.update(resource);
		return group;
	}

	@Override
	public Group allowAccess(ResourceActionForm resourceActionForm) {
		// TODO Auto-generated method stub
		Group group = this.groupBo
				.getGroupById(resourceActionForm.getGroupId());
		Resource resource = this.getResourceById(resourceActionForm
				.getResourceId());

		// Create relationship
		resource.getGroups().add(group);
		this.update(resource);
		return group;
	}

	@Override
	public void reBuildResources() {
		// TODO Auto-generated method stub

		Group adminGroup = this.groupBo.getAdminGroupByOrganisation();

		// Iterate through all the urls in the application
		for (Entry<RequestMappingInfo, HandlerMethod> m : mappingHandler
				.getHandlerMethods().entrySet()) {

			RequestMappingInfo k = m.getKey();

			// Get all the url patterns
			Set<String> urlLists = k.getPatternsCondition().getPatterns();

			// Strip out all the other url that aren't GET
			if ("[GET]".equals(k.getMethodsCondition().toString())
					|| "[]".equals(k.getMethodsCondition().toString())) {

				String url = (String) urlLists.toArray()[0];

				// If the url doesn't contain "/utilities/" and "/user/"
				// Utilities is a low level module which should nop be seen
				// User module should be access to everyone so, it should be
				// accessible by everyone
				if (!url.trim().contains("/utilities/")
						&& !url.trim().contains("/user/")) {

					// Use this to get all the urls
					System.out.println(url);
					System.out.println(k.getMethodsCondition().toString());

					Resource resource = new Resource();
					resource.setUrlPattern(url);
					//resource.setOrganisation(this.userIdentity
						//	.getOrganisation());

					// Save the resource
					this.save(resource);
					resource.getGroups().add(adminGroup);
					this.update(resource);

				}
			}
		}
	}

	@Override
	public void buildResourcesForOrganisation(Organisation organsation) {
		// TODO Auto-generated method stub

		Group adminGroup = this.groupBo
				.getAdminGroupByOrganisation(organsation);

		// Iterate through all the urls in the application
		for (Entry<RequestMappingInfo, HandlerMethod> m : mappingHandler
				.getHandlerMethods().entrySet()) {

			RequestMappingInfo k = m.getKey();

			// Get all the url patterns
			Set<String> urlLists = k.getPatternsCondition().getPatterns();

			// Strip out all the other url that aren't GET
			if ("[GET]".equals(k.getMethodsCondition().toString())
					|| "[]".equals(k.getMethodsCondition().toString())) {

				String url = (String) urlLists.toArray()[0];

				// If the url doesn't contain "/utilities/" and "/user/"
				// Utilities is a low level module which should nop be seen
				// User module should be access to everyone so, it should be
				// accessible by everyone
				if (!url.trim().contains("/utilities/")
						&& !url.trim().contains("/user/")) {

					// Use this to get all the urls
					System.out.println(url);
					System.out.println(k.getMethodsCondition().toString());

					Resource resource = new Resource();
					resource.setUrlPattern(url);
					resource.setOrganisation(organsation);

					// Save the resource
					this.save(resource);
					resource.getGroups().add(adminGroup);
					this.update(resource);

				}
			}
		}
	}

	@Override
	public List<Resource> getResourcesByGroup(Group group) {
		// TODO Auto-generated method stub
		return this.resourceDao.getResourcesByGroup(group);
	}

}
