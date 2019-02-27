package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.OrganisationSettingForm;
import org.calminfotech.system.models.GetsettingsAssignmentProc;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;

public interface SettingBo {

	public void update(OrganisationSettingForm organisationSettingForm);

	public List<GetsettingsAssignmentProc> fetchallsettingsbyorg(Integer orgid);

	void save(SettingsAssignment settingsassignment,
			SettingsAssignment_log settingsassignment_log);

	public void deleteassingment(Integer orgid);

	public SettingsAssignment fetchsettings(String code, Integer orgId);

}
