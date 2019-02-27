package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.daoInterface.GlobalItemTypeDao;
import org.calminfotech.system.forms.GlobalItemTypeForm;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalItemTypeBoImpl implements GlobalItemTypeBo{
	
	@Autowired
	private GlobalItemTypeDao globalItemTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public GlobalItemType getGlobalItemTypeById(int id) {
		// TODO Auto-generated method stub
		return this.globalItemTypeDao.getGlobalItemTypeById(id);
	}

	@Override
	public void save(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		globalItemTypeDao.save(globalItemType);
	}

	@Override
	public void saveForm(GlobalItemTypeForm gTForm) {
		// TODO Auto-generated method stub
		//globalItemTypeDao.save(gTForm);
	}

	@Override
	public void delete(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		globalItemTypeDao.delete(globalItemType);
	}

	@Override
	public void update(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		globalItemTypeDao.update(globalItemType);
	}

	@Override
	public List<GlobalItemType> fetchAll() {
		// TODO Auto-generated method stub
		return globalItemTypeDao.fetchAll();
	}

	@Override
	public List<GlobalItemType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return globalItemTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return globalItemTypeDao.fetchAll();
	}

}
