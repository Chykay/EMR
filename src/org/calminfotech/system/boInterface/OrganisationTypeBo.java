package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationTypeForm;
import org.calminfotech.system.models.OrganisationType;

public interface OrganisationTypeBo {
	public OrganisationType getOrganisationTypeById(int id);

	public void save(OrganisationType OrganisationType);

	public void saveForm(OrganisationTypeForm dForm);

	public void delete(OrganisationType OrganisationType);

	public void update(OrganisationType OrganisationType);

	public List<OrganisationType> fetchAll();

	public List<OrganisationType> fetchAllByOrganisation();
}
