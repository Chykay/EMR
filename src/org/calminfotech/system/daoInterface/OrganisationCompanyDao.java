package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.SysSettings;

public interface OrganisationCompanyDao {

	public List<OrganisationCompany> fetchAll(int id);

	public List<OrganisationCompany> Top50fetchAll(int id);

	public OrganisationCompany getOrganisationById(int id);

	public void save(OrganisationCompany organisation);

	public void delete(OrganisationCompany organisation);

	public void update(OrganisationCompany organisation);

	public List<SysSettings> fetchallsetting(int orgid);

	public void update(SysSettings syssettings);

	public SysSettings getSettingsById(Integer id);

}
