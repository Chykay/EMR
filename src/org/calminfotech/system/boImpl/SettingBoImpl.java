package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.daoInterface.OrganisationDao;
import org.calminfotech.system.daoInterface.SettingDao;
import org.calminfotech.system.forms.OrganisationSettingForm;
import org.calminfotech.system.models.GetsettingsAssignmentProc;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingBoImpl implements SettingBo {

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private OrganisationDao organisationDao;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void update(OrganisationSettingForm organisationSettingForm) {
		// TODO Auto-generated method stub

		Organisation organisation = this.organisationDao
				.getOrganisationById(this.userIdentity.getOrganisation()
						.getId());
		organisation.setName(organisationSettingForm.getName());
		organisation.setAddress(organisationSettingForm.getAddress());
		organisation.setEmail(organisationSettingForm.getSystemEmail());
		// organisation.setConsultationCode(organisationSettingForm
		// .getConsultationCode());

		this.organisationDao.update(organisation);
		// Update the userIdentity Bean
		// this.userIdentity.setOrganisation(organisation);
	}

	@Override
	public List<GetsettingsAssignmentProc> fetchallsettingsbyorg(Integer orgid) {
		// TODO Auto-generated method stub
		return this.settingDao.fectallbyorg(orgid);
	}

	@Override
	public void save(SettingsAssignment settingsassignment,
			SettingsAssignment_log settingsassignment_log) {
		// TODO Auto-generated method stub

		this.settingDao.save(settingsassignment, settingsassignment_log);
	}

	@Override
	public void deleteassingment(Integer orgid) {
		// TODO Auto-generated method stub
		this.settingDao.deleteassingment(orgid);
	}

	@Override
	public SettingsAssignment fetchsettings(String code, Integer orgId) {
		return this.settingDao.fetchsettings(code, orgId);
	}

}
