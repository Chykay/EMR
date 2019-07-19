package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.BankAccForm;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BankAccount;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.Bank;

public interface GLMappingBo {

	public LedgerAccount getReserveGL();
	
	public LedgerAccount reserveGL(LedgerAccForm ledgerAccForm);

	public List<BankAccount> getAllBankAccs(Organisation branch);

	List<Bank> getAllBanks();

	public BankAccount addBankAcc(BankAccForm bankAccForm);
}
