package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.OrganisationDirector;

public interface OrganisationDirectorBo {

	public List<OrganisationDirector> fetchAll();

	public OrganisationDirector getOrganisationDirectorId(int id);

	public void save(OrganisationDirector director);

	public void update(OrganisationDirector director);

	public void delete(OrganisationDirector director);

	public Object fetchAllDirectorByOrganisation(OrganisationCompany organ);

}
