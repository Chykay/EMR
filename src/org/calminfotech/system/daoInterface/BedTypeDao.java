package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.BedType;

public interface BedTypeDao {
	public BedType getBedTypeById(int id);

	public void save(BedType BedType);

	public void delete(BedType BedType);

	public void update(BedType BedType);
		
	public List<BedType> fetchAll();

	public List<BedType> fetchAllByOrganisation(Organisation organisation);

	List<BedType> fetchAllByOrganisation();
}
