package org.calminfotech.ledger.utility;

import java.util.List;

import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@SuppressWarnings("unchecked")
	public List<GenLedgBalance> getGLBalances(int branchID) {
		List<GenLedgBalance> list = sessionFactory.getCurrentSession()
				.createQuery("from GenLedgBalance  where company_id = ?  AND organisation_id = ?")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, branchID)
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getGLBalancesByParent(Integer categoryID, String ledgerType1, String ledgerType2) {
		String hsql="SELECT A FROM LedgerAccount A, GenLedgBalance B "
		+ "WHERE A.accountNo = B.gLAccountNo AND A.ledgerCatID = ? AND B.organisation.Id = ? AND B.orgCoy.Id = ?  AND (A.accountNo LIKE ''+ ? + '%' OR A.accountNo LIKE ''+ ? + '%')";
		
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hsql)
			.setParameter(0, categoryID)
			.setParameter(1, userIdentity.getOrganisation().getId())
			.setParameter(2, this.userIdentity.getOrganisation().getOrgCoy().getId())
			.setParameter(3, ledgerType1)
			.setParameter(4, ledgerType2);
			
       List<LedgerAccount> list = (List<LedgerAccount>) query.list();  
       
       System.out.println("category: " + categoryID + " :Size: " + list.size());
       
       return list;

		
	}

}
