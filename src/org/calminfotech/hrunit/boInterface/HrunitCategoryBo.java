package org.calminfotech.hrunit.boInterface;

import java.util.List;

import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitCategoryList;
import org.calminfotech.hrunit.models.HrunitType;

public interface HrunitCategoryBo {

	
	
	public List<HrunitCategory> fetchHrunitCategoryByItemTypeId(HrunitType HrunitTypId);

	public void save(HrunitCategory globalCategoryItem);
	
	public void saveCategoryForm(HrunitCategoryForm cForm);

	public void delete(HrunitCategory globalCategoryItem);

	public void update(HrunitCategory globalCategoryItem);
	
	public void updateForm(HrunitCategoryForm cForm);
		
	public List<HrunitCategoryList> fetchAllCategoryList(int organisationid);

	public List<HrunitCategory> fetchAllByOrganisationbyqueue();
	
	public List<HrunitCategory> fetchAllByOrganisationbyqueuebypoint(Integer pointid);
	
	public List<HrunitCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	public List<HrunitCategory> fetchAll(int organisationid);

	public HrunitCategory getHrunitCategoryById(int categoryId);

	//List<HrunitCategoryOuterRecursive> fetchAllTypes();

	List<HrunitCategory> fetchAllbyOrganisation(int organisationid);

	List<HrunitCategory> fetchTop50byOrganisation(int organisationid);

	List<HrunitCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
