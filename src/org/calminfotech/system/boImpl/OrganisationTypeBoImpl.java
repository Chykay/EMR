package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.OrganisationTypeBo;
import org.calminfotech.system.daoInterface.OrganisationTypeDao;
import org.calminfotech.system.forms.OrganisationTypeForm;
import org.calminfotech.system.models.OrganisationType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationTypeBoImpl implements OrganisationTypeBo{
	
	@Autowired
	private OrganisationTypeDao OrganisationTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public OrganisationType getOrganisationTypeById(int id) {
		// TODO Auto-generated method stub
		return this.OrganisationTypeDao.getOrganisationTypeById(id);
	}

	@Override
	public void save(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		OrganisationTypeDao.save(OrganisationType);
	}

	@Override
	public void saveForm(OrganisationTypeForm gTForm) {
		// TODO Auto-generated method stub
		//OrganisationTypeDao.save(gTForm);
	}

	@Override
	public void delete(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		OrganisationTypeDao.delete(OrganisationType);
	}

	@Override
	public void update(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		OrganisationTypeDao.update(OrganisationType);
	}

	@Override
	public List<OrganisationType> fetchAll() {
		// TODO Auto-generated method stub
		return OrganisationTypeDao.fetchAll();
	}

	@Override
	public List<OrganisationType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return OrganisationTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return OrganisationTypeDao.fetchAll();
	}

}
