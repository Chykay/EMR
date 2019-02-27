package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.LabtestType;

public interface LabtestTypeDao {
	public LabtestType getLabtestTypeById(int id);

	public void save(LabtestType LabtestType);

	public void delete(LabtestType LabtestType);

	public void update(LabtestType LabtestType);
		
	public List<LabtestType> fetchAll();

	public List<LabtestType> fetchAllByOrganisation(Organisation organisation);

	List<LabtestType> fetchAllByOrganisation();
}
