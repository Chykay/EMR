package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SysSettings;

public interface OrganisationDao {

	public List<Organisation> fetchAll(int id);
	
	public List<Organisation> Top50fetchAll(int id);
	

	public Organisation getOrganisationById(int id);

	public void save(Organisation organisation);

	public void delete(Organisation organisation);

	public void update(Organisation organisation);

	public List<SysSettings> fetchallsetting(int orgid);

	public void update(SysSettings syssettings);

	public SysSettings getSettingsById(Integer id);

}
