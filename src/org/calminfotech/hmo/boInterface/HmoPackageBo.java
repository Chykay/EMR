package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.system.models.Organisation;

public interface HmoPackageBo {

	public List<HmoPackage> fetchAll(Organisation organisationId);

	public List<HmoPackage> fetchAllForuse(Organisation organisationId);

	public HmoPackage getHmoPackageById(int id);

	public void save(HmoPackage hmoPackage);

	public void delete(HmoPackage hmoPackage);

	public void update(HmoPackage hmoPackage);

}
