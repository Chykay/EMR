package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.HmoCode;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.system.models.Organisation;

public interface HmoDao {

	public List<Hmo> fetchAll(Organisation organisationId);

	public Hmo getHmoById(int id);

	public void save(Hmo hmo);

	public void delete(Hmo hmo);

	public void update(Hmo hmo);

	public List<HmoCode> fetchAllGlobal();

	public HmoCode fetchGlobalByCode(String code);

}
