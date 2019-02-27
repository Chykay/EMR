package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.daoInterface.GlobalItemDao;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemReorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GlobalItemBoImpl implements GlobalItemBo {

	@Autowired
	private GlobalItemDao ItemDao;


	@Override
	public GlobalItem getGlobalItemById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getGlobalItemById(id);
	}

	@Override
	public void save(GlobalItem Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(GlobalItem Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(GlobalItem category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}
	

	@Override
	public GlobalItemReorder getGlobalItemReorder(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getGlobalItemReorder(id);
	}

	@Override
	public void save(GlobalItemReorder Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public List<GlobalItemCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GlobalItemCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}

	@Override
	public List<GlobalItem> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	
	
	@Override
	
	public List<GlobalItem> fetchAllByType(Integer typeid)
	{
	return ItemDao.fetchAllByType(typeid);
	}

	
	
@Override
	
	public List<GlobalItem> fetchAllByKind_room(String typeid)
	{
	return ItemDao.fetchAllByKind_room(typeid);
	}



@Override

public List<GlobalItem> fetchAllByKind(String code)
{
return ItemDao.fetchAllByKind(code);
}



@Override

public List<GlobalItem> fetchAllByType_room(Integer typeid)
{
return ItemDao.fetchAllByType_room(typeid);
}








	@Override
	public void save(GlobalItemForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GlobalItemForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GlobalItem> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GlobalItem> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<GlobalItem> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<GlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll();
	}

	@Override
	public List<GlobalItem> fetchAllByKind_bed(String code) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAllByKind_bed(code);
	}
	


}
