package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.AllergyCategoryForm;
import org.calminfotech.system.forms.AllergyCategoryForm;
import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.system.models.AllergyCategoryList;
//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
import org.calminfotech.system.models.AllergyType;

public interface AllergyCategoryBo {

	public AllergyCategory getGlobalCategoryItemById(int id);
	
	public List<AllergyCategory> fetchAllergyCategoryByItemTypeId(AllergyType AllergyTypId);

	public void save(AllergyCategory globalCategoryItem);
	
	public void saveCategoryForm(AllergyCategoryForm cForm);

	public void delete(AllergyCategory globalCategoryItem);

	public void update(AllergyCategory globalCategoryItem);
	
	public void updateForm(AllergyCategoryForm cForm);
		
	public List<AllergyCategory> fetchAll();

	public List<AllergyCategory> fetchAllByOrganisation();
	
	public List<AllergyCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<AllergyCategory> fetchAll(int organisationid);

	AllergyCategory getAllergyCategoryById(int categoryId);

	//List<AllergyCategoryOuterRecursive> fetchAllTypes();

	List<AllergyCategory> fetchAllbyOrganisation(int organisationid);

	List<AllergyCategory> fetchTop50byOrganisation(int organisationid);

	List<AllergyCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
