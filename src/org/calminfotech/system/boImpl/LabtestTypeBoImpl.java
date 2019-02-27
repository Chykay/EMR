package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.LabtestTypeBo;
import org.calminfotech.system.daoInterface.LabtestTypeDao;
import org.calminfotech.system.forms.LabtestTypeForm;
import org.calminfotech.system.models.LabtestType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabtestTypeBoImpl implements LabtestTypeBo{
	
	@Autowired
	private LabtestTypeDao LabtestTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public LabtestType getLabtestTypeById(int id) {
		// TODO Auto-generated method stub
		return this.LabtestTypeDao.getLabtestTypeById(id);
	}

	@Override
	public void save(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		LabtestTypeDao.save(LabtestType);
	}

	@Override
	public void saveForm(LabtestTypeForm gTForm) {
		// TODO Auto-generated method stub
		//LabtestTypeDao.save(gTForm);
	}

	@Override
	public void delete(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		LabtestTypeDao.delete(LabtestType);
	}

	@Override
	public void update(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		LabtestTypeDao.update(LabtestType);
	}

	@Override
	public List<LabtestType> fetchAll() {
		// TODO Auto-generated method stub
		return LabtestTypeDao.fetchAll();
	}

	@Override
	public List<LabtestType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return LabtestTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return LabtestTypeDao.fetchAll();
	}

}
