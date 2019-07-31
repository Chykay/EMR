package org.calminfotech.billing.boInterface;

import java.util.Date;
import java.util.List;

import org.calminfotech.hmo.models.HmoTransaction;

public interface HmoTransactionBo {
	public List<HmoTransaction> fetchAllByOrganisationbyform(Date startdate,
			Date enddate);

	public HmoTransaction getHmoTransactionById(int id);

	public void save(HmoTransaction HmoTransaction);

	public void delete(HmoTransaction HmoTransaction);

	public void update(HmoTransaction HmoTransaction);

	public List<HmoTransaction> fetchAllByOrganisation50();

	List<HmoTransaction> fetchAllByOrganisation();
}