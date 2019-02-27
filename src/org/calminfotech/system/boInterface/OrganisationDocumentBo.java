package org.calminfotech.system.boInterface;

import org.calminfotech.system.models.OrganisationDocument;


public interface OrganisationDocumentBo {

	public void save(OrganisationDocument adminDocument);

	public void delete(OrganisationDocument adminDocument);

	public OrganisationDocument getAdminDocumentById(int id);

}
