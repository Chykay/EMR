package org.calminfotech.ledger.utility;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.report.models.PatientListing;
import org.calminfotech.user.utils.UserIdentity;
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

}
