package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.OrganisationCategoryForm;
import org.calminfotech.system.models.OrganisationCategory;

import org.calminfotech.system.models.OrganisationCategoryList;
//import org.calminfotech.system.models.OrganisationCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface OrganisationCategoryDao {

	public List<OrganisationCategory> fetchAll(int organisationid);

	public OrganisationCategory getCategoryById(int categoryId);

	//public List<OrganisationCategoryOuterRecursive> fetchAllTypes();

	public void save(OrganisationCategory category);

	public void update(OrganisationCategory OrganisationCategory);
	
	public List<OrganisationCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(OrganisationCategory category);

	public List<OrganisationCategory> fetchAll();
	
	
	public List<OrganisationCategory> fetchTop50byOrganisation(int id);

	public List<OrganisationCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
