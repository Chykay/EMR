package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.DiseasesType;

public interface DiseasesTypeDao {
	public DiseasesType getDiseasesTypeById(int id);

	public void save(DiseasesType DiseasesType);

	public void delete(DiseasesType DiseasesType);

	public void update(DiseasesType DiseasesType);
		
	public List<DiseasesType> fetchAll();

	public List<DiseasesType> fetchAllByOrganisation(Organisation organisation);

	List<DiseasesType> fetchAllByOrganisation();
}
