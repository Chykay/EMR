package org.calminfotech.system.boImpl;

import java.util.HashSet;
import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.daoInterface.GlobalItemCategoryDao;
import org.calminfotech.system.forms.GlobalItemCategoryForm;

import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryList;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemKind;
import org.calminfotech.system.models.GlobalItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GlobalItemCategoryBoImpl implements GlobalItemCategoryBo {

	@Autowired
	private GlobalItemCategoryDao CategoryDao;

	@Override
	public List<GlobalItemCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public GlobalItemCategory getGlobalItemCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(GlobalItemCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(GlobalItemCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	@Override
	public List<GlobalItemCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}

	@Override
	public void update(GlobalItemCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(
			GlobalItemType globalItemTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<GlobalItemCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(GlobalItemCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(GlobalItemCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GlobalItemCategory> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<GlobalItemCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<GlobalItemCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public GlobalItemCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GlobalItemCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GlobalItemCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GlobalItemKind> fetchAllKindByOrganisationByCategoryType(
			Integer cattypeid) {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllKindByOrganisationByCategoryType(cattypeid);	
	}

	
}
