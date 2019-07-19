package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.BankAccount;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.models.Bank;

public interface GLMappingDao {

	public LedgerAccount getReserveGL();

	public List<BankAccount> getAllBankAccs(Organisation branch);

	public List<Bank> getAllBanks();

	public BankAccount addBankAcc(BankAccount bankAccount);
}
