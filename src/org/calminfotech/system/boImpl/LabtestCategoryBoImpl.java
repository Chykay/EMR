package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.LabtestCategoryBo;
import org.calminfotech.system.daoInterface.LabtestCategoryDao;

import org.calminfotech.system.forms.LabtestCategoryForm;


import org.calminfotech.system.models.LabtestCategory;
import org.calminfotech.system.models.LabtestCategoryList;

//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.system.models.LabtestType;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabtestCategoryBoImpl implements LabtestCategoryBo {
	@Autowired
	private LabtestCategoryDao CategoryDao;

	@Override
	public List<LabtestCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public LabtestCategory getLabtestCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(LabtestCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(LabtestCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<LabtestCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(LabtestCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<LabtestCategory> fetchLabtestCategoryByItemTypeId(
			LabtestType LabtestTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<LabtestCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(LabtestCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(LabtestCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LabtestCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<LabtestCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<LabtestCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public LabtestCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabtestCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabtestCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
