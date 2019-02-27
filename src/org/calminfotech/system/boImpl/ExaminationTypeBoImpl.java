package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.ExaminationTypeBo;
import org.calminfotech.system.daoInterface.ExaminationTypeDao;
import org.calminfotech.system.forms.ExaminationTypeForm;
import org.calminfotech.system.models.ExaminationType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationTypeBoImpl implements ExaminationTypeBo{
	
	@Autowired
	private ExaminationTypeDao ExaminationTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public ExaminationType getExaminationTypeById(int id) {
		// TODO Auto-generated method stub
		return this.ExaminationTypeDao.getExaminationTypeById(id);
	}

	@Override
	public void save(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		ExaminationTypeDao.save(ExaminationType);
	}

	@Override
	public void saveForm(ExaminationTypeForm gTForm) {
		// TODO Auto-generated method stub
		//ExaminationTypeDao.save(gTForm);
	}

	@Override
	public void delete(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		ExaminationTypeDao.delete(ExaminationType);
	}

	@Override
	public void update(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		ExaminationTypeDao.update(ExaminationType);
	}

	@Override
	public List<ExaminationType> fetchAll() {
		// TODO Auto-generated method stub
		return ExaminationTypeDao.fetchAll();
	}

	@Override
	public List<ExaminationType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return ExaminationTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return ExaminationTypeDao.fetchAll();
	}

}
