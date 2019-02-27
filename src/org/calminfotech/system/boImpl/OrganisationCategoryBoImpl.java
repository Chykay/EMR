package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.OrganisationCategoryBo;
import org.calminfotech.system.daoInterface.OrganisationCategoryDao;

import org.calminfotech.system.forms.OrganisationCategoryForm;


import org.calminfotech.system.models.OrganisationCategory;
import org.calminfotech.system.models.OrganisationCategoryList;

//import org.calminfotech.system.models.OrganisationCategoryOuterRecursive;
import org.calminfotech.system.models.OrganisationType;
//import org.calminfotech.system.models.OrganisationCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationCategoryBoImpl implements OrganisationCategoryBo {
	@Autowired
	private OrganisationCategoryDao CategoryDao;

	@Override
	public List<OrganisationCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public OrganisationCategory getOrganisationCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(OrganisationCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(OrganisationCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<OrganisationCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(OrganisationCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<OrganisationCategory> fetchOrganisationCategoryByItemTypeId(
			OrganisationType OrganisationTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<OrganisationCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(OrganisationCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(OrganisationCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrganisationCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<OrganisationCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<OrganisationCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public OrganisationCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganisationCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganisationCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
