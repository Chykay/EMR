package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.DiseasesTypeBo;
import org.calminfotech.system.daoInterface.DiseasesTypeDao;
import org.calminfotech.system.forms.DiseasesTypeForm;
import org.calminfotech.system.models.DiseasesType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseasesTypeBoImpl implements DiseasesTypeBo{
	
	@Autowired
	private DiseasesTypeDao DiseasesTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public DiseasesType getDiseasesTypeById(int id) {
		// TODO Auto-generated method stub
		return this.DiseasesTypeDao.getDiseasesTypeById(id);
	}

	@Override
	public void save(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		DiseasesTypeDao.save(DiseasesType);
	}

	@Override
	public void saveForm(DiseasesTypeForm gTForm) {
		// TODO Auto-generated method stub
		//DiseasesTypeDao.save(gTForm);
	}

	@Override
	public void delete(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		DiseasesTypeDao.delete(DiseasesType);
	}

	@Override
	public void update(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		DiseasesTypeDao.update(DiseasesType);
	}

	@Override
	public List<DiseasesType> fetchAll() {
		// TODO Auto-generated method stub
		return DiseasesTypeDao.fetchAll();
	}

	@Override
	public List<DiseasesType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return DiseasesTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return DiseasesTypeDao.fetchAll();
	}

}
