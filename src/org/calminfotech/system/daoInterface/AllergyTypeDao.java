package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.AllergyType;

public interface AllergyTypeDao {
	public AllergyType getAllergyTypeById(int id);

	public void save(AllergyType AllergyType);

	public void delete(AllergyType AllergyType);

	public void update(AllergyType AllergyType);
		
	public List<AllergyType> fetchAll();

	public List<AllergyType> fetchAllByOrganisation(Organisation organisation);

	List<AllergyType> fetchAllByOrganisation();
}
