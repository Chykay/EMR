package org.calminfotech.patient.boImpl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.system.daoInterface.LanguageDao;
import org.calminfotech.system.daoInterface.TitleDao;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.CountryList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.StatesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientBoImpl implements PatientBo {

	@Autowired
	private PatientDao patientDao;

	/*
	 * @Autowired private PatientDaoImpl pl;
	 */
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
	private CountryList countryList;

	@Autowired
	private LocalGovernmentAreaList lgaList;

	@Autowired
	private BloodgroupList bloodgroupList;

	@Autowired
	private BloodgenotypeList bloodgenotypeList;

	@Autowired
	private LifestatusList lifestatusList;

	@Autowired
	private OccupationList occupationList;

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private PatientDocumentDao patientDocumentDao;

	@Autowired
	PatientCodeGenerator patientCodeGenerator;

	@Override
	public List<Patient> fetchAll(int start) {
		// TODO Auto-generated method stub

		return this.patientDao.fetchAll(start);

	}

	/*
	 * @Override public List<Patient> fetchAllByOrganisation() { // TODO
	 * Auto-generated method stub return
	 * this.patientDao.fetchAllByOrganisation(this.userIdentity
	 * .getOrganisation()); }
	 */
	@Override
	public List<Patient> fetchAllByOrganisation(int organisationId) {
		return this.patientDao.fetchAllByOrganisation(organisationId);

	}

	@Override
	public List<Patient> fetchTop50ByOrganisation(int organisationId) {
		return this.patientDao.fetchTop50ByOrganisation(organisationId);

	}

	@Override
	public List<Patient> fetchByOrganisationrec(int organisationId) {
		return this.patientDao.fetchByOrganisationrec(organisationId);

	}

	@Override
	public List<Patient> fetchByOrganisationrecbypatient(int pid) {
		return this.patientDao.fetchByOrganisationrecbypatient(pid);

	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		return this.patientDao.getPatientById(id);

	}

	@Override
	public Patient save(PatientForm patientForm) {
		// TODO Auto-generated method stub

		Patient patient = new Patient();

		patient.setSurname(patientForm.getSurname());
		patient.setFirstName(patientForm.getFirstName());
		patient.setOthernames(patientForm.getOthernames());

		if (!patientForm.getEmail().equals("")) {
			patient.setEmail(patientForm.getEmail());
		}

		if (!patientForm.getPatientfileno().equals(""))

		{
			patient.setPatientFileno(patientForm.getPatientfileno());
		}

		if (!patientForm.getPatientbvn().equals(""))

		{
			patient.setPatientBvn(patientForm.getPatientbvn());
		}

		if (!patientForm.getPatientnid().equals(""))

		{
			patient.setPatientNid(patientForm.getPatientnid());
		}

		if (!patientForm.getPatientpid().equals(""))

		{
			patient.setPatientPid(patientForm.getPatientpid());
		}

		try {
			patient.setHeight(patientForm.getHeight());
		} catch (Exception e) {

		}

		try {
			patient.setCreditlimit(patientForm.getCreditlimit());
		} catch (Exception e) {

		}

		if (!patientForm.getDob().equals("")
				&& DateUtils.isValidDate(patientForm.getDob()))

		{
			patient.setDob(DateUtils.formatStringToDate(patientForm.getDob()));
		}

		if (!patientForm.getStartdate().equals("")
				&& DateUtils.isValidDate(patientForm.getStartdate())) {
			patient.setStartdate(DateUtils.formatStringToDate(patientForm
					.getStartdate()));
		} else {
			patient.setStartdate(null);
		}

		if (!patientForm.getStatusdate().equals("")
				&& DateUtils.isValidDate(patientForm.getStatusdate())) {
			patient.setStatusdate(DateUtils.formatStringToDate(patientForm
					.getStatusdate()));
		} else {
			patient.setStatusdate(null);
		}

		if (!patientForm.getDob().equals("")
				&& DateUtils.isValidDate(patientForm.getDob())) {

			patient.setPatientCode(patientCodeGenerator
					.processNumber(patientForm.getDob()));
		}

		if (patientForm.getBldgrpId().intValue() != 0) {
			patient.setBloodgroup(bloodgroupList.getBloodgroupById(patientForm
					.getBldgrpId()));

		}

		if (patientForm.getGenotypeId().intValue() != 0) {

			patient.setBloodgenotype(bloodgenotypeList
					.getBloodgenotypeById(patientForm.getGenotypeId()));
		}

		if (patientForm.getOccupationId().intValue() != 0) {
			patient.setOccupation(this.occupationList
					.getOccupationById(patientForm.getOccupationId()));
		}

		if (patientForm.getLanguageId().intValue() != 0) {
			patient.setLanguage(this.languageDao.getLanguageById(patientForm
					.getLanguageId()));
		}

		if (patientForm.getTitleId().intValue() != 0) {
			patient.setTitle(this.titleDao.getTitleById(patientForm
					.getTitleId()));

		}

		if (patientForm.getLifestatusId().intValue() != 0) {
			patient.setLifestatus(this.lifestatusList
					.getLifestatusById(patientForm.getLifestatusId()));
		}

		if (patientForm.getGenderId().intValue() != 0) {
			patient.setGender(this.genderList.getGenderById(patientForm
					.getGenderId()));
		} else {
			patient.setGender(null);
		}

		if (!patientForm.getLgaCode().equals("0")) {
			patient.setLga(this.lgaList.getLgaByCode(patientForm.getLgaCode()));
		} else {
			patient.setLga(null);
		}

		if (!patientForm.getStateCode().equals("0")) {
			patient.setState(this.stateList.getStateByCode(patientForm
					.getStateCode()));
		} else {

			patient.setState(null);
		}

		if (!patientForm.getCountryCode().equals("0")) {
			patient.setCountry(this.countryList.getCountryByCode(patientForm
					.getCountryCode()));
		} else {

			patient.setCountry(null);
		}

		if (patientForm.getMaritalstatusId().intValue() != 0) {
			patient.setMaritalStatus(this.maritalStatusList
					.getMartialStatusById(patientForm.getMaritalstatusId()));
		} else {
			patient.setMaritalStatus(null);
		}

		patient.setCreatedBy(userIdentity.getUsername());
		patient.setCreatedDate(new GregorianCalendar().getTime());

		patient.setOrganisation(userIdentity.getOrganisation());
		patient.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());

		patient.setIsDeleted(false);

		this.patientDao.save(patient);

		return patient;
	}

	@Override
	public void delete(Patient patient) {
		// TODO Auto-generated method stub
		this.patientDao.delete(patient);
	}

	@Override
	public void update(PatientForm patientForm) {
		// TODO Auto-generated method stub

		// Patient patient = new Patient();
		Patient patient = patientDao.getPatientById(patientForm.getPatientId());

		patient.setPatientId(patientForm.getPatientId());

		patient.setSurname(patientForm.getSurname());
		patient.setFirstName(patientForm.getFirstName());
		patient.setOthernames(patientForm.getOthernames());

		if (!patientForm.getEmail().equals("")) {
			patient.setEmail(patientForm.getEmail());
		}

		try {
			patient.setCreditlimit(patientForm.getCreditlimit());
		} catch (Exception e) {
		}

		try {
			patient.setHeight(patientForm.getHeight());
		} catch (Exception e) {
		}

		if (!patientForm.getPatientfileno().equals(""))

		{
			patient.setPatientFileno(patientForm.getPatientfileno());
		}

		if (!patientForm.getPatientbvn().equals(""))

		{
			patient.setPatientBvn(patientForm.getPatientbvn());
		}

		if (!patientForm.getPatientnid().equals(""))

		{
			patient.setPatientNid(patientForm.getPatientnid());
		}

		if (!patientForm.getPatientpid().equals(""))

		{
			patient.setPatientPid(patientForm.getPatientpid());
		}

		if (!patientForm.getDob().equals("")
				&& DateUtils.isValidDate(patientForm.getDob())) {
			patient.setDob(DateUtils.formatStringToDate(patientForm.getDob()));
		} else {
			patient.setDob(null);
		}

		if (!patientForm.getStartdate().equals("")
				&& DateUtils.isValidDate(patientForm.getStartdate())) {
			patient.setStartdate(DateUtils.formatStringToDate(patientForm
					.getStartdate()));
		} else {
			patient.setStartdate(null);
		}

		if (!patientForm.getStatusdate().equals("")
				&& DateUtils.isValidDate(patientForm.getStatusdate())) {
			patient.setStatusdate(DateUtils.formatStringToDate(patientForm
					.getStatusdate()));
		} else {
			patient.setStatusdate(null);
		}

		// patient.setAddress(patientForm.getAddress());

		if (!patientForm.getDob().equals("")
				&& DateUtils.isValidDate(patientForm.getDob())
				&& patientForm.getPatientcode().equals("")) {

			patient.setPatientCode(patientCodeGenerator
					.processNumber(patientForm.getDob()));
		}

		// patient.setPhoneNumber(patientForm.getPhoneNumber());

		if (patientForm.getBldgrpId().intValue() != 0) {
			patient.setBloodgroup(bloodgroupList.getBloodgroupById(patientForm
					.getBldgrpId()));

		} else {

			patient.setBloodgroup(null);
		}

		if (patientForm.getGenotypeId().intValue() != 0) {

			patient.setBloodgenotype(bloodgenotypeList
					.getBloodgenotypeById(patientForm.getGenotypeId()));
		} else {
			patient.setBloodgenotype(null);
		}

		if (patientForm.getOccupationId().intValue() != 0) {
			patient.setOccupation(this.occupationList
					.getOccupationById(patientForm.getOccupationId()));
		} else {
			patient.setOccupation(null);
		}

		if (patientForm.getLanguageId().intValue() != 0) {
			patient.setLanguage(this.languageDao.getLanguageById(patientForm
					.getLanguageId()));
		} else {
			patient.setLanguage(null);
		}

		if (patientForm.getTitleId().intValue() != 0) {
			patient.setTitle(this.titleDao.getTitleById(patientForm
					.getTitleId()));
		} else {
			patient.setTitle(null);
		}

		if (patientForm.getLifestatusId().intValue() != 0) {
			patient.setLifestatus(this.lifestatusList
					.getLifestatusById(patientForm.getLifestatusId()));
		}

		if (patientForm.getGenderId().intValue() != 0) {
			patient.setGender(this.genderList.getGenderById(patientForm
					.getGenderId()));
		} else {
			patient.setGender(null);
		}

		if (!patientForm.getLgaCode().equals("0")) {
			patient.setLga(this.lgaList.getLgaByCode(patientForm.getLgaCode()));
		} else {
			patient.setLga(null);
		}

		if (!patientForm.getStateCode().equals("0")) {
			patient.setState(this.stateList.getStateByCode(patientForm
					.getStateCode()));
		} else {
			patient.setState(null);
		}

		if (!patientForm.getCountryCode().equals("0")) {
			patient.setCountry(this.countryList.getCountryByCode(patientForm
					.getCountryCode()));
		} else {
			patient.setCountry(null);
		}

		if (patientForm.getMaritalstatusId().intValue() != 0) {
			patient.setMaritalStatus(this.maritalStatusList
					.getMartialStatusById(patientForm.getMaritalstatusId()));
		}

		else

		{
			patient.setMaritalStatus(null);
		}

		if (patientForm.getTitleId().intValue() != 0) {
			patient.setTitle(this.titleDao.getTitleById(patientForm
					.getTitleId()));
		} else {
			patient.setTitle(null);
		}

		patient.setModifiedBy(userIdentity.getUsername());
		patient.setModifiedDate(new GregorianCalendar().getTime());

		patient.setOrganisation(userIdentity.getOrganisation());

		patient.setIsDeleted(false);

		this.patientDao.update(patient);

	}

	@Override
	public void update(Patient patient) {
		// TODO Auto-generated method stub
		this.patientDao.update(patient);
	}

	public List<PatientDocument> getPatientDocumentByPatient(Patient patient) {
		return this.patientDocumentDao.fetchAllByPatient(patient);
	}

	@Override
	public Patient findByBirthDay(String patientDob) {
		return patientDao.findByBirthDay(patientDob);
	}

	@Override
	public Patient checkIfPatientIdExist(String patientId) {
		return patientDao.checkIfPatientIdExist(patientId);
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

}
