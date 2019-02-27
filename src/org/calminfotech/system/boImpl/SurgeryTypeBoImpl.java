package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.SurgeryTypeBo;
import org.calminfotech.system.daoInterface.SurgeryTypeDao;
import org.calminfotech.system.forms.SurgeryTypeForm;
import org.calminfotech.system.models.SurgeryType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurgeryTypeBoImpl implements SurgeryTypeBo{
	
	@Autowired
	private SurgeryTypeDao SurgeryTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public SurgeryType getSurgeryTypeById(int id) {
		// TODO Auto-generated method stub
		return this.SurgeryTypeDao.getSurgeryTypeById(id);
	}

	@Override
	public void save(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		SurgeryTypeDao.save(SurgeryType);
	}

	@Override
	public void saveForm(SurgeryTypeForm gTForm) {
		// TODO Auto-generated method stub
		//SurgeryTypeDao.save(gTForm);
	}

	@Override
	public void delete(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		SurgeryTypeDao.delete(SurgeryType);
	}

	@Override
	public void update(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		SurgeryTypeDao.update(SurgeryType);
	}

	@Override
	public List<SurgeryType> fetchAll() {
		// TODO Auto-generated method stub
		return SurgeryTypeDao.fetchAll();
	}

	@Override
	public List<SurgeryType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return SurgeryTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return SurgeryTypeDao.fetchAll();
	}

}
