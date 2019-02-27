package org.calminfotech.hmo.boInterface;

import java.util.List;
import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

public interface HmoPackageBo {

		public List<HmoPackage> fetchAll(int organisationId);
		
		public List<HmoPackage> fetchAllForuse (int organisationId);
		
		
		
		public HmoPackage getHmoPackageById(int id);
		
		public void save(HmoPackage hmoPackage);

		public void delete(HmoPackage hmoPackage);

		public void update(HmoPackage hmoPackage);
		

	
	

}
