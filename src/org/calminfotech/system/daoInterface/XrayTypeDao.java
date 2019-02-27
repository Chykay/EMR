package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.XrayType;

public interface XrayTypeDao {
	public XrayType getXrayTypeById(int id);

	public void save(XrayType XrayType);

	public void delete(XrayType XrayType);

	public void update(XrayType XrayType);
		
	public List<XrayType> fetchAll();

	public List<XrayType> fetchAllByOrganisation(Organisation organisation);

	List<XrayType> fetchAllByOrganisation();
}
