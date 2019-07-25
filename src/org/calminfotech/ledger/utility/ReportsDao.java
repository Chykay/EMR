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
	public List<Object> getGLBalancesCompany() {
		
		String hsql="SELECT A.gLAccountNo,  SUM(A.currBalance) AS curr_balance "
				+ "FROM GenLedgBalance A WHERE A.orgCoy.id = ? GROUP BY A.gLAccountNo ";
				
				Query query = sessionFactory.getCurrentSession()
					.createQuery(hsql)
					.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId());

				List<Object> result = (List<Object>) query.list();
				
			     
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getGLBalancesByParent(Integer categoryID, String ledgerType1, String ledgerType2) {
		String hsql="SELECT A FROM LedgerAccount A "
		+ "WHERE  ledgerCatID = ? AND company_id = ?  AND (account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%')";
		
		
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hsql)
			.setParameter(0, categoryID)
			.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
			.setParameter(2, ledgerType1)
			.setParameter(3, ledgerType2);
			
       List<LedgerAccount> list = (List<LedgerAccount>) query.list();  
       
       
       return list;

		
	}
	
	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getGLBalancesByParentReserve(Integer categoryID, String ledgerType1, String ledgerType2, String ledgerType3) {
		String hsql="SELECT A FROM LedgerAccount A "
		+ "WHERE  ledgerCatID = ? AND company_id = ?  AND (account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%' OR account_no LIKE '6%')";
		
		
		/*String hsql="SELECT A FROM LedgerAccount A, GenLedgBalance B "
		+ "WHERE A.accountNo = B.gLAccountNo AND A.ledgerCatID = ? AND B.organisation.Id = ? AND B.orgCoy.Id = ?  AND (A.accountNo LIKE ''+ ? + '%' OR A.accountNo LIKE ''+ ? + '%')";
		*/
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hsql)
			.setParameter(0, categoryID)
			.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
			.setParameter(2, ledgerType1)
			.setParameter(3, ledgerType2)
			.setParameter(4, ledgerType3);
			
       List<LedgerAccount> list = (List<LedgerAccount>) query.list();  
       
       
       return list;

		
	}
	
/*	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getGLBalancesByParentCompany(Integer categoryID, String ledgerType1, String ledgerType2) {
		String hsql="SELECT A FROM LedgerAccount A "
		+ "WHERE  ledgerCatID = ? AND company_id = ?  AND (account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%')";
		
		
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hsql)
			.setParameter(0, categoryID)
			.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
			.setParameter(2, ledgerType1)
			.setParameter(3, ledgerType2);
			
       List<LedgerAccount> list = (List<LedgerAccount>) query.list();  
       
       
       return list;

		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getGLBalancesByParentReserveCompany(Integer categoryID, String ledgerType1, String ledgerType2, String ledgerType3) {
		String hsql="SELECT A FROM LedgerAccount A "
		+ "WHERE  ledgerCatID = ?  AND company_id = ?  AND (account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%' OR account_no LIKE ''+ ? + '%' OR account_no LIKE '6%')";
		
		
		
		Query query = sessionFactory.getCurrentSession()
			.createQuery(hsql)
			.setParameter(0, categoryID)
			.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
			.setParameter(2, ledgerType1)
			.setParameter(3, ledgerType2)
			.setParameter(4, ledgerType3);
			
       List<LedgerAccount> list = (List<LedgerAccount>) query.list();  
       
       
       return list;

	}

		*/
}
