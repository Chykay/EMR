package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.ExaminationType;

public interface ExaminationTypeDao {
	public ExaminationType getExaminationTypeById(int id);

	public void save(ExaminationType ExaminationType);

	public void delete(ExaminationType ExaminationType);

	public void update(ExaminationType ExaminationType);
		
	public List<ExaminationType> fetchAll();

	public List<ExaminationType> fetchAllByOrganisation(Organisation organisation);

	List<ExaminationType> fetchAllByOrganisation();
}
