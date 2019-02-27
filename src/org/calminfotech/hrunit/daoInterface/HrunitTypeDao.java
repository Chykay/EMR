package org.calminfotech.hrunit.daoInterface;

import java.util.List;

import org.calminfotech.hrunit.models.HrunitType;
import org.calminfotech.system.models.Organisation;

public interface HrunitTypeDao {
	public HrunitType getHrunitTypeById(int id);

	public void save(HrunitType HrunitType);

	public void delete(HrunitType HrunitType);

	public void update(HrunitType HrunitType);
		
	public List<HrunitType> fetchAll();

	public List<HrunitType> fetchAllByOrganisation(Organisation organisation);

	List<HrunitType> fetchAllByOrganisation();
}
