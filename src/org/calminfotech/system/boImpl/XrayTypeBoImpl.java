package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.boInterface.XrayTypeBo;
import org.calminfotech.system.daoInterface.XrayTypeDao;
import org.calminfotech.system.forms.XrayTypeForm;
import org.calminfotech.system.models.XrayType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XrayTypeBoImpl implements XrayTypeBo{
	
	@Autowired
	private XrayTypeDao XrayTypeDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public XrayType getXrayTypeById(int id) {
		// TODO Auto-generated method stub
		return this.XrayTypeDao.getXrayTypeById(id);
	}

	@Override
	public void save(XrayType XrayType) {
		// TODO Auto-generated method stub
		XrayTypeDao.save(XrayType);
	}

	@Override
	public void saveForm(XrayTypeForm gTForm) {
		// TODO Auto-generated method stub
		//XrayTypeDao.save(gTForm);
	}

	@Override
	public void delete(XrayType XrayType) {
		// TODO Auto-generated method stub
		XrayTypeDao.delete(XrayType);
	}

	@Override
	public void update(XrayType XrayType) {
		// TODO Auto-generated method stub
		XrayTypeDao.update(XrayType);
	}

	@Override
	public List<XrayType> fetchAll() {
		// TODO Auto-generated method stub
		return XrayTypeDao.fetchAll();
	}

	@Override
	public List<XrayType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		//return XrayTypeDao.fetchAllByOrganisation(userIdentity.getOrganisation());
		return XrayTypeDao.fetchAll();
	}

}
