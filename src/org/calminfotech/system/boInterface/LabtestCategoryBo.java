package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.LabtestCategoryForm;
import org.calminfotech.system.forms.LabtestCategoryForm;
import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestCategoryList;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.system.models.LabtestType;

public interface LabtestCategoryBo {

	public LabtestCategory getGlobalCategoryItemById(int id);
	
	public List<LabtestCategory> fetchLabtestCategoryByItemTypeId(LabtestType LabtestTypId);

	public void save(LabtestCategory globalCategoryItem);
	
	public void saveCategoryForm(LabtestCategoryForm cForm);

	public void delete(LabtestCategory globalCategoryItem);

	public void update(LabtestCategory globalCategoryItem);
	
	public void updateForm(LabtestCategoryForm cForm);
		
	public List<LabtestCategory> fetchAll();

	public List<LabtestCategory> fetchAllByOrganisation();
	
	public List<LabtestCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<LabtestCategory> fetchAll(int organisationid);

	LabtestCategory getLabtestCategoryById(int categoryId);

	//List<LabtestCategoryOuterRecursive> fetchAllTypes();

	List<LabtestCategory> fetchAllbyOrganisation(int organisationid);

	List<LabtestCategory> fetchTop50byOrganisation(int organisationid);

	List<LabtestCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
