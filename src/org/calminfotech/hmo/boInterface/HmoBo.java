package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.HmoForm;
import org.calminfotech.hmo.models.HmoCode;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.system.models.Organisation;

//import org.calminfotech.system.forms.DataTableForm;

public interface HmoBo {

	public List<Hmo> fetchAll(Organisation organisationId);

	public Hmo getHmoById(int id);

	public Hmo save(HmoForm hmoForm);

	public void delete(Hmo hmo);

	public void update(HmoForm hmoForm);

	public List<HmoCode> fetchAllGlobal();

	public HmoCode fetchGlobalByCode(String code);
}
