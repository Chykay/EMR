package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.system.models.Organisation;

public interface HmoPackageDao {

	public HmoPackage getHmoPackageById(int id);

	public void save(HmoPackage hmoPackage);

	public void delete(HmoPackage hmoPackage);

	public void update(HmoPackage hmoPackage);

	public List<HmoPackage> fetchAll(Organisation organisationId);

	public List<HmoPackage> fetchAllForuse(Organisation organisationId);

}
