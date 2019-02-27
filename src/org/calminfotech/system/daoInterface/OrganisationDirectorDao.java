package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.OrganisationDirector;

public interface OrganisationDirectorDao {

	public List<OrganisationDirector> fetchAll();	

	public OrganisationDirector getOrganisationDirectorId(int id);
	
	public void save(OrganisationDirector director);
	
	public void update(OrganisationDirector director);
	
	public void delete(OrganisationDirector director);

	

}
