package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GetsettingsAssignmentProc;
import org.calminfotech.system.models.Setting;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;

public interface SettingDao {

	public Setting getSetting();

	public void update(Setting setting);

	List<GetsettingsAssignmentProc> fectallbyorg(Integer orgid);

	public void save(SettingsAssignment settingsassignment,
			SettingsAssignment_log settingsassignment_log);

	public void deleteassingment(Integer orgid);

	public SettingsAssignment fetchsettings(String code, Integer orgId);

	public List<String> fetchAllGLSettings(int company_id);

}
