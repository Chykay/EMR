package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.system.models.Organisation;

public interface BillSchemeDao {

	public List<BillScheme> fetchAllByOrganisationBytype(
			Organisation organisationId, int billtype);

	public List<BillScheme> fetchAllByOrganisation(Organisation organisationId);

	public BillScheme getBillSchemeById(int id);

	public void save(BillScheme billScheme);

	public void delete(BillScheme billScheme);

	public void update(BillScheme billScheme);

}
