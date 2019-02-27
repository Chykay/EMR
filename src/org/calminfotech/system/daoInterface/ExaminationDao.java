package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Examination;
//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Examination;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationDao {

public List<Examination> fetchAll(int id);
	

	public List<Examination> fetchAllByOgranisation(int organisationId);

	//public Examination getCategoryItemById(int itemId);

	//public List<ExaminationCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Examination Examination);

	public void update(Examination Examination);

	public void delete(Examination Examination);

	
	public List<Examination> fetchTop50byOrganisation(int id);


	public Examination getExaminationById(int id);

}
