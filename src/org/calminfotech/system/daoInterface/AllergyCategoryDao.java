package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.DrugsCategoryForm;
import org.calminfotech.system.models.AllergyCategory;

import org.calminfotech.system.models.AllergyCategoryList;
//import org.calminfotech.system.models.DrugsCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyCategoryDao {

	public List<AllergyCategory> fetchAll(int organisationid);

	public AllergyCategory getCategoryById(int categoryId);

	//public List<DrugsCategoryOuterRecursive> fetchAllTypes();

	public void save(AllergyCategory category);

	public void update(AllergyCategory AllergyCategory);
	
	public List<AllergyCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(AllergyCategory category);

	public List<AllergyCategory> fetchAll();
	
	
	public List<AllergyCategory> fetchTop50byOrganisation(int id);

	public List<AllergyCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
