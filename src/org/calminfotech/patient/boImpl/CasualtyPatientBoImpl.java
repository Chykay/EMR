package org.calminfotech.patient.boImpl;

import java.util.List;




import org.calminfotech.patient.boInterface.CasualtyPatientBo;
import org.calminfotech.patient.daoInterface.CasualtyPatientDao;
import org.calminfotech.patient.forms.CasualtyPatientForm;
import org.calminfotech.patient.models.CasPatient;
import org.calminfotech.system.daoInterface.LanguageDao;
import org.calminfotech.system.daoInterface.TitleDao;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Title;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGen;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CasualtyPatientBoImpl implements CasualtyPatientBo{
	
	@Autowired
	private CasualtyPatientDao casualtyPatientDao;

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GenderList genderList;
	
	@Autowired
	private MaritalStatusList maritalStatusList;
	
	@Autowired
	private StatesList stateList;

	@Autowired
	private LocalGovernmentAreaList lgaList;
	
	@Autowired
	private LanguageDao languageDao;

	@Override
	public List<CasPatient> fetchAll() {
		// TODO Auto-generated method stub
		return this.casualtyPatientDao.fetchAll();
	}

	@Override
	public List<CasPatient> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
	//	return this.casualtyPatientDao.fetchAllByOrganisation(this.userIdentity
		//		.getOrganisation());
		return null;
				
	}

	@Override
	public CasPatient getcasPatientById(int id) {
		// TODO Auto-generated method stub
		return this.casualtyPatientDao.getCasPatientById(id);
	}

	@Override
	public CasPatient save(CasualtyPatientForm casualtyPatientForm) {
		// TODO Auto-generated method stub
		
		
		CasPatient casPatient = new CasPatient();

		casPatient.setPatient_id(new AutoGen().genNum());
	
		casPatient.setSurname(casualtyPatientForm.getSurname());
		casPatient.setOthernames(casualtyPatientForm.getOthernames());
		
		casPatient.setEmail(casualtyPatientForm.getEmail());
		casPatient.setAddress(casualtyPatientForm.getAddress());
		casPatient.setPhoneNumber(casualtyPatientForm.getPhoneNumber());
		casPatient.setDob(casualtyPatientForm.getDob());
		casPatient.setBldgrp(casualtyPatientForm.getBldgrp());
		casPatient.setGenotype(casualtyPatientForm.getGenotype());
		casPatient.setHeight(casualtyPatientForm.getHeight());

		casPatient.setLga(this.lgaList.getLgaById(casualtyPatientForm.getLgaId()));
		casPatient.setState(this.stateList.getStateById(casualtyPatientForm.getStateId()));
		casPatient.setMaritalStatus(this.maritalStatusList.getMartialStatusById(casualtyPatientForm.getStatusId()));
	
		casPatient.setCreatedBy(userIdentity.getUsername());
		casPatient.setHomeNumber(casualtyPatientForm.getHomeNumber());

	Language language = this.languageDao.getLanguageById(casualtyPatientForm.getLanguageId());
	casPatient.setLanguage(language);
	
	
	Title title = this.titleDao.getTitleById(casualtyPatientForm.getTitleId());
	casPatient.setTitle(title);

	Gender gender = this.genderList.getGenderById(casualtyPatientForm.getGenderId());
	casPatient.setGender(gender);

	casPatient.setActive(true);

	//casPatient.setOrganisation(userIdentity.getOrganisation());

	this.casualtyPatientDao.save(casPatient);

	return casPatient;
	
	
	}

	@Override
	public void delete(CasPatient casPatient) {
		// TODO Auto-generated method stub
		this.casualtyPatientDao.delete(casPatient);
	}

	@Override
	public void update(CasualtyPatientForm casualtyPatientForm) {
		// TODO Auto-generated method stub

	
		CasPatient casPatient = this.getcasPatientById(casualtyPatientForm.getId());

		casPatient.setSurname(casualtyPatientForm.getSurname());
		casPatient.setOthernames(casualtyPatientForm.getOthernames());
		casPatient.setEmail(casualtyPatientForm.getEmail());
		casPatient.setAddress(casualtyPatientForm.getAddress());
		casPatient.setPhoneNumber(casualtyPatientForm.getPhoneNumber());
		casPatient.setBldgrp(casualtyPatientForm.getBldgrp());
		casPatient.setGenotype(casualtyPatientForm.getGenotype());
		casPatient.setHeight(casualtyPatientForm.getHeight());
		casPatient.setHomeNumber(casualtyPatientForm.getHomeNumber());
		
		Title title = this.titleDao.getTitleById(casualtyPatientForm.getTitleId());
		casPatient.setTitle(title);

		Gender gender = this.genderList.getGenderById(casualtyPatientForm.getGenderId());
		casPatient.setGender(gender);
		
	/*	Language language = this.languageDao.getLanguageById(casualtyPatientForm.getLanguageId());
		casPatient.setLanguage(language);*/
//		
	/*	casPatient.setLga(this.lgaList.getLgaById(casualtyPatientForm.getLgaId()));
		casPatient.setState(this.stateList.getStateById(casualtyPatientForm.getStateId()));*/
	//	casPatient.setMaritalStatus(this.maritalStatusList.getMartialStatusById(casualtyPatientForm.getStatusId()));
				

	
		this.casualtyPatientDao.update(casPatient);

	}

	@Override
	public void update(CasPatient casPatient) {
		// TODO Auto-generated method stub
		this.casualtyPatientDao.update(casPatient);
	}

}
