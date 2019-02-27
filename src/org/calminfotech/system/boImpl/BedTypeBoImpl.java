package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.BedTypeBo;
import org.calminfotech.system.daoInterface.BedTypeDao;
import org.calminfotech.system.forms.BedTypeForm;
import org.calminfotech.system.models.BedType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedTypeBoImpl implements BedTypeBo{
	
	@Autowired
	private BedTypeDao BedTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public BedType getBedTypeById(int id) {
		// TODO Auto-generated method stub
		return this.BedTypeDao.getBedTypeById(id);
	}

	@Override
	public void save(BedType BedType) {
		// TODO Auto-generated method stub
		BedTypeDao.save(BedType);
	}

	@Override
	public void saveForm(BedTypeForm gTForm) {
		// TODO Auto-generated method stub
		//BedTypeDao.save(gTForm);
	}

	@Override
	public void delete(BedType BedType) {
		// TODO Auto-generated method stub
		BedTypeDao.delete(BedType);
	}

	@Override
	public void update(BedType BedType) {
		// TODO Auto-generated method stub
		BedTypeDao.update(BedType);
	}

	@Override
	public List<BedType> fetchAll() {
		// TODO Auto-generated method stub
		return BedTypeDao.fetchAll();
	}

	@Override
	public List<BedType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return BedTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return BedTypeDao.fetchAll();
	}

}
