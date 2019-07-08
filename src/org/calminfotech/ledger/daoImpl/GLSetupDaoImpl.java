package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.GLSetupDao;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.user.utils.UserIdentity;
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
}
