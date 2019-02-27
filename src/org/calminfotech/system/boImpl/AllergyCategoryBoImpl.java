package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.AllergyCategoryBo;
import org.calminfotech.system.daoInterface.AllergyCategoryDao;

import org.calminfotech.system.forms.AllergyCategoryForm;


import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.system.models.AllergyCategoryList;

//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
import org.calminfotech.system.models.AllergyType;
//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyCategoryBoImpl implements AllergyCategoryBo {
	@Autowired
	private AllergyCategoryDao CategoryDao;

	@Override
	public List<AllergyCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public AllergyCategory getAllergyCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(AllergyCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(AllergyCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<AllergyCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(AllergyCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<AllergyCategory> fetchAllergyCategoryByItemTypeId(
			AllergyType AllergyTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<AllergyCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(AllergyCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(AllergyCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AllergyCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<AllergyCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<AllergyCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public AllergyCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AllergyCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AllergyCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
