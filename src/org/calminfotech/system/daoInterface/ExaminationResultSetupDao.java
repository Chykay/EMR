package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.ExaminationResultSetup;
//import org.calminfotech.system.models.ExaminationResultSetupCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.ExaminationResultSetup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationResultSetupDao {

public List<ExaminationResultSetup> fetchAll(int id);
	

	public List<ExaminationResultSetup> fetchAllByOgranisation(int organisationId);

	//public ExaminationResultSetup getCategoryItemById(int itemId);

	//public List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypesNew();

	public void save(ExaminationResultSetup ExaminationResultSetup);

	public void update(ExaminationResultSetup ExaminationResultSetup);

	public void delete(ExaminationResultSetup ExaminationResultSetup);

	
	public List<ExaminationResultSetup> fetchTop50byOrganisation(int id);


	public ExaminationResultSetup getExaminationResultSetupById(int id);

}
