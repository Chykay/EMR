package org.calminfotech.ledger.boImpl;

import java.util.Date;

import org.calminfotech.ledger.boInterface.GLSetupBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GLSetupDao;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GLSetupBoImpl implements GLSetupBo{
	@Autowired
	private GLSetupDao glSetupDao;
	
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private SettingBo settingBo;
	
	
	public LedgerAccount getReserveGL() {
		return this.glSetupDao.getReserveGL();
	}

	public LedgerAccount reserveGL(LedgerAccForm ledgerAccForm) {

		LedgerAccount account = this.getReserveGL();
		SettingsAssignment sAssignment = new SettingsAssignment();
		
		if (account == null) {
			account = new LedgerAccount();
			account = this.ledgerAccBo.save(ledgerAccForm);
			
			sAssignment.setCreatedBy("root");
			sAssignment.setCreatedDate(new Date(System.currentTimeMillis()));
			sAssignment.setDeleted(false);
			sAssignment.setOrganisation(account.getOrgCoy());
			sAssignment.setSettings_code("reserve-GLP");
			sAssignment.setSettings_value(account.getAccountNo());
			
			SettingsAssignment_log sLog= new SettingsAssignment_log();
			sLog.setCreatedBy("root");
			sLog.setCreatedDate(new Date(System.currentTimeMillis()));
			sLog.setDeleted(false);
			sLog.setOrganisation(account.getOrgCoy());
			sLog.setSettings_code("reserve-GLP");
			sLog.setSettings_value(account.getAccountNo());
			
			this.settingBo.save(sAssignment, sLog);
			
		} else {
			account = this.ledgerAccBo.update(ledgerAccForm, account.getId());
			
			sAssignment = this.settingBo.fetchsettings("reserve-GLP", 2);
			sAssignment.setSettings_value(account.getAccountNo());
			
			this.settingBo.update(sAssignment);
		}
		
		return account;
	}
}
