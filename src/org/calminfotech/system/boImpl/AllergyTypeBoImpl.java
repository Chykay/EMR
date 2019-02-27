package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.AllergyTypeBo;
import org.calminfotech.system.daoInterface.AllergyTypeDao;
import org.calminfotech.system.forms.AllergyTypeForm;
import org.calminfotech.system.models.AllergyType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergyTypeBoImpl implements AllergyTypeBo{
	
	@Autowired
	private AllergyTypeDao AllergyTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public AllergyType getAllergyTypeById(int id) {
		// TODO Auto-generated method stub
		return this.AllergyTypeDao.getAllergyTypeById(id);
	}

	@Override
	public void save(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		AllergyTypeDao.save(AllergyType);
	}

	@Override
	public void saveForm(AllergyTypeForm gTForm) {
		// TODO Auto-generated method stub
		//AllergyTypeDao.save(gTForm);
	}

	@Override
	public void delete(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		AllergyTypeDao.delete(AllergyType);
	}

	@Override
	public void update(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		AllergyTypeDao.update(AllergyType);
	}

	@Override
	public List<AllergyType> fetchAll() {
		// TODO Auto-generated method stub
		return AllergyTypeDao.fetchAll();
	}

	@Override
	public List<AllergyType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return AllergyTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return AllergyTypeDao.fetchAll();
	}

}
