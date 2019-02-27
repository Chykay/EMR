package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.SurgeryCategoryForm;
import org.calminfotech.system.models.SurgeryCategory;

import org.calminfotech.system.models.SurgeryCategoryList;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface SurgeryCategoryDao {

	public List<SurgeryCategory> fetchAll(int organisationid);

	public SurgeryCategory getCategoryById(int categoryId);

	//public List<SurgeryCategoryOuterRecursive> fetchAllTypes();

	public void save(SurgeryCategory category);

	public void update(SurgeryCategory SurgeryCategory);
	
	public List<SurgeryCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(SurgeryCategory category);

	public List<SurgeryCategory> fetchAll();
	
	
	public List<SurgeryCategory> fetchTop50byOrganisation(int id);

	public List<SurgeryCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
