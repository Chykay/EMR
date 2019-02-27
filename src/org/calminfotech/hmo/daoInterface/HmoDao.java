package org.calminfotech.hmo.daoInterface;

import java.util.List;
import org.calminfotech.hmo.models.Hmo;

public interface HmoDao {

	public List<Hmo> fetchAll(int organisationId);
	
	public Hmo getHmoById(int id);

	public void save(Hmo hmo);

	public void delete(Hmo hmo);

	public void update(Hmo hmo);



}
