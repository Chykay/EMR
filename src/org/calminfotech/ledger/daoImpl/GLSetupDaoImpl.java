package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.GLSetupDao;
import org.calminfotech.ledger.models.BankAccount;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Bank;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GLSetupDaoImpl implements GLSetupDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@SuppressWarnings("unchecked")
	public LedgerAccount getReserveGL() {
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE organisation_id = ? AND company_id = ? AND account_no LIKE '6%'")
				.setParameter(0, this.userIdentity.getOrganisation().getId())
				.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		
		if (ledgerAccounts.size() > 0)
			return ledgerAccounts.get(0);
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> getAllBankAccs(Organisation branch) {

		List<BankAccount> bankAccounts = this.sessionFactory.getCurrentSession()
				.createQuery("from BankAccount WHERE organisation_id = ?")
				.setParameter(0, branch.getId())
				.list();

		return bankAccounts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getAllBanks() {

		List<Bank> banks = this.sessionFactory.getCurrentSession()
				.createQuery("from Bank")
				.list();

		return banks;
	}

	@Override
	public BankAccount addBankAcc(BankAccount bankAccount) {
		 bankAccount.setId((Integer) this.sessionFactory.getCurrentSession().save(bankAccount));
		 return bankAccount;
	}
}
