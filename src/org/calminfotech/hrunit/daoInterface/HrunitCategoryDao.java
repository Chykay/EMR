package org.calminfotech.hrunit.daoInterface;
import java.util.List;

import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitCategoryList;

//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface HrunitCategoryDao {

	public List<HrunitCategory> fetchAll(int organisationid);

	public HrunitCategory getCategoryById(int categoryId);

	//public List<HrunitCategoryOuterRecursive> fetchAllTypes();

	public void save(HrunitCategory category);

	public void update(HrunitCategory HrunitCategory);
	
	public List<HrunitCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	public List<HrunitCategory> fetchAllByOrganisationbyqueuebypoint(Integer pointid);
	
	void delete(HrunitCategory category);

	public List<HrunitCategory> fetchAll();
	
	public List<HrunitCategoryList>	fetchAllCategoryList(int id);
	
	public List<HrunitCategory> fetchAllbyOrganisation(int organisationid);
	
	public List<HrunitCategory>  fetchAllByOrganisationbyqueue();
	
	public List<HrunitCategory> fetchTop50byOrganisation(int id);

	public List<HrunitCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
