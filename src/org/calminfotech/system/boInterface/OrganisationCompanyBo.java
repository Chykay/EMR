package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationCompanyForm;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.SysSettings;

public interface OrganisationCompanyBo {

	public List<OrganisationCompany> fetchAll(int id);

	public List<OrganisationCompany> Top50fetchAll(int id);

	public OrganisationCompany getOrganisationById(int id);

	public OrganisationCompany save(OrganisationCompanyForm OrganisationForm);

	public void delete(OrganisationCompany organisation);

	public void update(OrganisationCompanyForm organisationForm);

	public void update(SysSettings syssettings);

	public SysSettings getSettingsById(Integer id);

	public List<SysSettings> fetchallsettings(Integer id);

	public void update(OrganisationCompany organisation);

	public void save(OrganisationCompany organisation);

}
