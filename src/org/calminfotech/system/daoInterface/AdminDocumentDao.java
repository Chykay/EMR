package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationDocument;


public interface AdminDocumentDao {

	public List<OrganisationDocument> fetchAll();

	public List<OrganisationDocument> fetchAllByOrgainsation(
			Organisation organisation);

	public List<OrganisationDocument> fetchAllByOrganisation(Organisation organisation);

	public void save(OrganisationDocument adminDocument);

	public OrganisationDocument getAdminDocumentById(int id);

	public void delete(OrganisationDocument adminDocument);
}
