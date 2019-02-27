package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.BedCategoryForm;

import org.calminfotech.system.models.BedCategory;
import org.calminfotech.system.models.BedCategory;
import org.calminfotech.system.models.BedCategoryList;
//import org.calminfotech.system.models.BedCategoryOuterRecursive;
import org.calminfotech.system.models.BedType;

public interface BedCategoryBo {

	public BedCategory getGlobalCategoryItemById(int id);
	
	public List<BedCategory> fetchBedCategoryByItemTypeId(BedType BedTypId);

	public void save(BedCategory globalCategoryItem);
	
	public void saveCategoryForm(BedCategoryForm cForm);

	public void delete(BedCategory globalCategoryItem);

	public void update(BedCategory globalCategoryItem);
	
	public void updateForm(BedCategoryForm cForm);
		
	public List<BedCategory> fetchAll();

	public List<BedCategory> fetchAllByOrganisation();
	
	public List<BedCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	public List<BedCategory> fetchAllByOrganisationByCategoryHR(Integer hr);

	List<BedCategory> fetchAll(int organisationid);

	public BedCategory getBedCategoryById(int categoryId);

	//List<BedCategoryOuterRecursive> fetchAllTypes();

	List<BedCategory> fetchAllbyOrganisation(int organisationid);

	List<BedCategory> fetchTop50byOrganisation(int organisationid);

	List<BedCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
