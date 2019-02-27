package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesCategoryList;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.system.models.DiseasesType;

public interface DiseasesCategoryBo {

	public DiseasesCategory getGlobalCategoryItemById(int id);
	
	public List<DiseasesCategory> fetchDiseasesCategoryByItemTypeId(DiseasesType DiseasesTypId);

	public void save(DiseasesCategory globalCategoryItem);
	
	public void saveCategoryForm(DiseasesCategoryForm cForm);

	public void delete(DiseasesCategory globalCategoryItem);

	public void update(DiseasesCategory globalCategoryItem);
	
	public void updateForm(DiseasesCategoryForm cForm);
		
	public List<DiseasesCategory> fetchAll();

	public List<DiseasesCategory> fetchAllByOrganisation();
	
	public List<DiseasesCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<DiseasesCategory> fetchAll(int organisationid);

	DiseasesCategory getDiseasesCategoryById(int categoryId);

	//List<DiseasesCategoryOuterRecursive> fetchAllTypes();

	List<DiseasesCategory> fetchAllbyOrganisation(int organisationid);

	List<DiseasesCategory> fetchTop50byOrganisation(int organisationid);

	List<DiseasesCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
