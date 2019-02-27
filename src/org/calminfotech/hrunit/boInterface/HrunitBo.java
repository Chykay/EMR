package org.calminfotech.hrunit.boInterface;

import java.util.List;


import org.calminfotech.hrunit.forms.HrunitForm;
import org.calminfotech.hrunit.models.Hrunit;




public interface HrunitBo {


	public List<Hrunit> fetchAllByOrganisation();

	public Hrunit getHrunitById(int id);

	public void save(HrunitForm iForm);

	public void delete(Hrunit gblItem);

	public void update(HrunitForm iForm);
		
	public List<Hrunit> fetchAll();

	public void save(Hrunit hrunit);

	//List<HrunitCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Hrunit hrunit);

	//List<HrunitCategoryOuterRecursive> fetchAllTypes();

	public List<Hrunit> fetchAllByOgranisation(int organisationId);

	public List<Hrunit> fetchAll(Integer id);

	public List<Hrunit> fetchTop50byOrganisation(Integer id);


}
