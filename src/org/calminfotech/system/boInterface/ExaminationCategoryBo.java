package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.ExaminationCategoryForm;
import org.calminfotech.system.forms.ExaminationCategoryForm;
import org.calminfotech.system.models.ExaminationCategory;
import org.calminfotech.system.models.ExaminationCategory;
import org.calminfotech.system.models.ExaminationCategoryList;
//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
import org.calminfotech.system.models.ExaminationType;

public interface ExaminationCategoryBo {

	public ExaminationCategory getGlobalCategoryItemById(int id);
	
	public List<ExaminationCategory> fetchExaminationCategoryByItemTypeId(ExaminationType ExaminationTypId);

	public void save(ExaminationCategory globalCategoryItem);
	
	public void saveCategoryForm(ExaminationCategoryForm cForm);

	public void delete(ExaminationCategory globalCategoryItem);

	public void update(ExaminationCategory globalCategoryItem);
	
	public void updateForm(ExaminationCategoryForm cForm);
		
	public List<ExaminationCategory> fetchAll();

	public List<ExaminationCategory> fetchAllByOrganisation();
	
	public List<ExaminationCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<ExaminationCategory> fetchAll(int organisationid);

	ExaminationCategory getExaminationCategoryById(int categoryId);

	//List<ExaminationCategoryOuterRecursive> fetchAllTypes();

	List<ExaminationCategory> fetchAllbyOrganisation(int organisationid);

	List<ExaminationCategory> fetchTop50byOrganisation(int organisationid);

	List<ExaminationCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
