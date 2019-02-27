package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.ExaminationCategoryBo;
import org.calminfotech.system.daoInterface.ExaminationCategoryDao;

import org.calminfotech.system.forms.ExaminationCategoryForm;


import org.calminfotech.system.models.ExaminationCategory;
import org.calminfotech.system.models.ExaminationCategoryList;

//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
import org.calminfotech.system.models.ExaminationType;
//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExaminationCategoryBoImpl implements ExaminationCategoryBo {
	@Autowired
	private ExaminationCategoryDao CategoryDao;

	@Override
	public List<ExaminationCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public ExaminationCategory getExaminationCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(ExaminationCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(ExaminationCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<ExaminationCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(ExaminationCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<ExaminationCategory> fetchExaminationCategoryByItemTypeId(
			ExaminationType ExaminationTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<ExaminationCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(ExaminationCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(ExaminationCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExaminationCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<ExaminationCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<ExaminationCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public ExaminationCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExaminationCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExaminationCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
