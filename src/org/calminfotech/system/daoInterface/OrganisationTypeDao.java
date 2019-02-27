package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationType;

public interface OrganisationTypeDao {
	public OrganisationType getOrganisationTypeById(int id);

	public void save(OrganisationType OrganisationType);

	public void delete(OrganisationType OrganisationType);

	public void update(OrganisationType OrganisationType);
		
	public List<OrganisationType> fetchAll();

	public List<OrganisationType> fetchAllByOrganisation(Organisation organisation);

	List<OrganisationType> fetchAllByOrganisation();
}
