package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.calminfotech.system.daoInterface.OrganisationDirectorDao;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.OrganisationDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationDirectorBoImpl implements OrganisationDirectorBo {

	@Autowired
	private OrganisationDirectorDao directorDao;
	
	@Override
	public List<OrganisationDirector> fetchAll() {
		return directorDao.fetchAll();
	}

	@Override
	public OrganisationDirector getOrganisationDirectorId(int id) {
		return directorDao.getOrganisationDirectorId(id);
	}

	@Override
	public void save(OrganisationDirector director) {
		directorDao.save(director);
	}

	@Override
	public void update(OrganisationDirector director) {
		directorDao.update(director);
	}

	@Override
	public void delete(OrganisationDirector director) {
		directorDao.delete(director);
	}

	@Override
	public Object fetchAllDirectorByOrganisation(OrganisationCompany organ) {
		// TODO Auto-generated method stub
		return null;
	}

}
