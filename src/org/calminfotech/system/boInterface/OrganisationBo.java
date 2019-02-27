package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationForm;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SysSettings;

public interface OrganisationBo {

	public List<Organisation> fetchAll(int id);

	public List<Organisation> Top50fetchAll(int id);

	public Organisation getOrganisationById(int id);

	public Organisation save(OrganisationForm OrganisationForm);

	public void delete(Organisation organisation);

	public void update(OrganisationForm organisationForm);

	public void update(SysSettings syssettings);

	public SysSettings getSettingsById(Integer id);

	public List<SysSettings> fetchallsettings(Integer id);

	public void update(Organisation organisation);

}
