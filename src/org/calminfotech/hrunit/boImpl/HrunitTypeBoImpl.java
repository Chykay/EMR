package org.calminfotech.hrunit.boImpl;

import java.util.List;

import org.calminfotech.hrunit.boInterface.HrunitTypeBo;
import org.calminfotech.hrunit.daoInterface.HrunitTypeDao;
import org.calminfotech.hrunit.forms.HrunitTypeForm;
import org.calminfotech.hrunit.models.HrunitType;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrunitTypeBoImpl implements HrunitTypeBo{
	
	@Autowired
	private HrunitTypeDao HrunitTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public HrunitType getHrunitTypeById(int id) {
		// TODO Auto-generated method stub
		return this.HrunitTypeDao.getHrunitTypeById(id);
	}

	@Override
	public void save(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		HrunitTypeDao.save(HrunitType);
	}

	@Override
	public void saveForm(HrunitTypeForm gTForm) {
		// TODO Auto-generated method stub
		//HrunitTypeDao.save(gTForm);
	}

	@Override
	public void delete(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		HrunitTypeDao.delete(HrunitType);
	}

	@Override
	public void update(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		HrunitTypeDao.update(HrunitType);
	}

	@Override
	public List<HrunitType> fetchAll() {
		// TODO Auto-generated method stub
		return HrunitTypeDao.fetchAll();
	}

	@Override
	public List<HrunitType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return HrunitTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return HrunitTypeDao.fetchAll();
	}

}
