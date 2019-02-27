package org.calminfotech.system.boImpl;

import org.calminfotech.system.boInterface.OrganisationDocumentBo;
import org.calminfotech.system.daoInterface.AdminDocumentDao;
import org.calminfotech.system.models.OrganisationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDocumentBoImpl implements OrganisationDocumentBo {

	@Autowired
	private AdminDocumentDao adminDocumentDao;

	@Override
	public void save(OrganisationDocument adminDocument) {
		// TODO Auto-generated method stub
		this.adminDocumentDao.save(adminDocument);
	}

	@Override
	public void delete(OrganisationDocument adminDocument) {
		// TODO Auto-generated method stub
		this.adminDocumentDao.delete(adminDocument);
	}

	@Override
	public OrganisationDocument getAdminDocumentById(int id) {
		// TODO Auto-generated method stub
		return this.adminDocumentDao.getAdminDocumentById(id);
	}
	
	
}

