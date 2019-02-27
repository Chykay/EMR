package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SurgeryType;

public interface SurgeryTypeDao {
	public SurgeryType getSurgeryTypeById(int id);

	public void save(SurgeryType SurgeryType);

	public void delete(SurgeryType SurgeryType);

	public void update(SurgeryType SurgeryType);
		
	public List<SurgeryType> fetchAll();

	public List<SurgeryType> fetchAllByOrganisation(Organisation organisation);

	List<SurgeryType> fetchAllByOrganisation();
}
