package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationCategoryForm;
import org.calminfotech.system.models.OrganisationCategory;
import org.calminfotech.system.models.OrganisationCategoryList;
import org.calminfotech.system.models.OrganisationType;

//import org.calminfotech.system.models.OrganisationCategoryOuterRecursive;

public interface OrganisationCategoryBo {

	public OrganisationCategory getGlobalCategoryItemById(int id);

	public List<OrganisationCategory> fetchOrganisationCategoryByItemTypeId(
			OrganisationType OrganisationTypId);

	public void save(OrganisationCategory CategoryItem);

	public void saveCategoryForm(OrganisationCategoryForm cForm);

	public void delete(OrganisationCategory CategoryItem);

	public void update(OrganisationCategory CategoryItem);

	public void updateForm(OrganisationCategoryForm cForm);

	public List<OrganisationCategory> fetchAll();

	public List<OrganisationCategory> fetchAllByOrganisation();

	public List<OrganisationCategory> fetchAllByOrganisationByCategoryType(
			Integer cattypeid);

	List<OrganisationCategory> fetchAll(int organisationid);

	OrganisationCategory getOrganisationCategoryById(int categoryId);

	// List<OrganisationCategoryOuterRecursive> fetchAllTypes();

	List<OrganisationCategory> fetchAllbyOrganisation(int organisationid);

	List<OrganisationCategory> fetchTop50byOrganisation(int organisationid);

	List<OrganisationCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

}
