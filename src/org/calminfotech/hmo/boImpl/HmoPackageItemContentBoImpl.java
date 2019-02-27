package org.calminfotech.hmo.boImpl;

import java.util.List;

import org.calminfotech.hmo.boInterface.HmoPackageItemBo;
import org.calminfotech.hmo.boInterface.HmoPackageItemContentBo;
import org.calminfotech.hmo.daoInterface.HmoPackageItemContentDao;
import org.calminfotech.hmo.daoInterface.HmoPackageItemDao;
import org.calminfotech.hmo.forms.HmoPackageItemForm;
import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.hmo.models.HmoPackageItemContent;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoPackageItemContentBoImpl implements HmoPackageItemContentBo {

	@Autowired
	private HmoPackageItemContentDao hmoContentDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<HmoPackageItemContent> fetchAll() {
		// TODO Auto-generated method stub
		return hmoContentDao.fetchAll();
	}

	@Override
	public HmoPackageItemContent getHmoItemContentById(int id) {
		// TODO Auto-generated method stub
		return hmoContentDao.getHmoItemContentById(id);
	}

	@Override
	public void save(HmoPackageItemContent hmoContent) {

		hmoContentDao.save(hmoContent);
		
		
	}

	@Override
	public void delete(HmoPackageItemContent hmoContent) {
		// TODO Auto-generated method stub
		hmoContentDao.delete(hmoContent);
	}

	@Override
	public void update(HmoPackageItemContent hmoContent) {
		/*HmoItem category = hmoItemDao.getHmoItemById(hmoItem.getItemId());
		hmoItem.setName(category.getName());
		hmoItem.setDescription(category.getDescription());
		hmoItem.setPackageId(category.getPackageId());*/
		//System.out.println("yemiId/"+hmoItem.getItemId());
        this.hmoContentDao.update(hmoContent);
        
	
	}

}
