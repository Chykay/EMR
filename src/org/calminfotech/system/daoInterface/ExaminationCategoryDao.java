package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.ExaminationCategoryForm;
import org.calminfotech.system.models.ExaminationCategory;

import org.calminfotech.system.models.ExaminationCategoryList;
//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationCategoryDao {

	public List<ExaminationCategory> fetchAll(int organisationid);

	public ExaminationCategory getCategoryById(int categoryId);

	//public List<ExaminationCategoryOuterRecursive> fetchAllTypes();

	public void save(ExaminationCategory category);

	public void update(ExaminationCategory ExaminationCategory);
	
	public List<ExaminationCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(ExaminationCategory category);

	public List<ExaminationCategory> fetchAll();
	
	
	public List<ExaminationCategory> fetchTop50byOrganisation(int id);

	public List<ExaminationCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
