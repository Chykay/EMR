package org.calminfotech.hmo.boImpl;

import java.util.List;

import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hmo.daoInterface.HmoPackageItemDao;
import org.calminfotech.hmo.forms.HmoPackageItemForm;
import org.calminfotech.hmo.models.HmoPackageItem;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoPackageItemBoImpl implements HmoPackageItemBo {

	@Autowired
	private HmoPackageItemDao hmoItemDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<HmoPackageItem> fetchAll() {
		// TODO Auto-generated method stub
		return hmoItemDao.fetchAll();
	}

	@Override
	public HmoPackageItem getHmoItemById(int id) {
		// TODO Auto-generated method stub
		return hmoItemDao.getHmoItemById(id);
	}

	@Override
	public void save(HmoPackageItem hmoItem) {

		hmoItemDao.save(hmoItem);
		
		
	}

	@Override
	public void delete(HmoPackageItem hmoItem) {
		// TODO Auto-generated method stub
		hmoItemDao.delete(hmoItem);
	}

	@Override
	public void update(HmoPackageItem hmoItem) {
		/*HmoItem category = hmoItemDao.getHmoItemById(hmoItem.getItemId());
		hmoItem.setName(category.getName());
		hmoItem.setDescription(category.getDescription());
		hmoItem.setPackageId(category.getPackageId());*/
		//System.out.println("yemiId/"+hmoItem.getItemId());
        this.hmoItemDao.update(hmoItem);
        
	}
        
	
	

	@Override
	public HmoPackageItem getHmoItemByAll(int id) {
		// TODO Auto-generated method stub
		return hmoItemDao.getHmoItemByAll(id);
		
	}

}
