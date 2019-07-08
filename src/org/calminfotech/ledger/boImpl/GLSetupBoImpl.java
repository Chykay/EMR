package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.GLSetupBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GLSetupDao;
import org.calminfotech.ledger.forms.BankAccForm;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BankAccount;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Bank;
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
	
	@Autowired
	private UserIdentity userIdentity;
	
	
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

	@Override
	public List<BankAccount> getAllBankAccs(Organisation branch) {
		return this.glSetupDao.getAllBankAccs(branch);
	}
	
	@Override
	public List<Bank> getAllBanks() {
		return this.glSetupDao.getAllBanks();
	}

	@Override
	public BankAccount addBankAcc(BankAccForm bankAccForm) {
		User user = this.userIdentity.getUser();
		BankAccount bankAccount = new BankAccount();
		
		bankAccount.setAccountNo(bankAccForm.getAccountNo());
		bankAccount.setBank(bankAccForm.getBank());
		bankAccount.setLedgerAccount(this.ledgerAccBo.getLedgerById(bankAccForm.getLedgerAccID()));
		
		bankAccount.setCreate_date(new Date(System.currentTimeMillis()));
		bankAccount.setCreated_by(user);
		bankAccount.setOrganisation(user.getOrganisation());
		bankAccount.setOrgCoy(user.getOrganisation().getOrgCoy());
		
		return this.glSetupDao.addBankAcc(bankAccount);
	}
}
