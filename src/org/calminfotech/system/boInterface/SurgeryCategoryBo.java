package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.SurgeryCategoryForm;
import org.calminfotech.system.forms.SurgeryCategoryForm;
import org.calminfotech.system.models.SurgeryCategory;
import org.calminfotech.system.models.SurgeryCategory;
import org.calminfotech.system.models.SurgeryCategoryList;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
import org.calminfotech.system.models.SurgeryType;

public interface SurgeryCategoryBo {

	public SurgeryCategory getGlobalCategoryItemById(int id);
	
	public List<SurgeryCategory> fetchSurgeryCategoryByItemTypeId(SurgeryType SurgeryTypId);

	public void save(SurgeryCategory globalCategoryItem);
	
	public void saveCategoryForm(SurgeryCategoryForm cForm);

	public void delete(SurgeryCategory globalCategoryItem);

	public void update(SurgeryCategory globalCategoryItem);
	
	public void updateForm(SurgeryCategoryForm cForm);
		
	public List<SurgeryCategory> fetchAll();

	public List<SurgeryCategory> fetchAllByOrganisation();
	
	public List<SurgeryCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<SurgeryCategory> fetchAll(int organisationid);

	SurgeryCategory getSurgeryCategoryById(int categoryId);

	//List<SurgeryCategoryOuterRecursive> fetchAllTypes();

	List<SurgeryCategory> fetchAllbyOrganisation(int organisationid);

	List<SurgeryCategory> fetchTop50byOrganisation(int organisationid);

	List<SurgeryCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
