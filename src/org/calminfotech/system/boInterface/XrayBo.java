package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.XrayForm;

import org.calminfotech.system.models.Xray;



public interface XrayBo {


	public List<Xray> fetchAllByOrganisation();

	public Xray getXrayById(int id);

	public void save(XrayForm iForm);

	public void delete(Xray gblItem);

	public void update(XrayForm iForm);
		
	public List<Xray> fetchAll();

	public void save(Xray xray);

	//List<XrayCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Xray xray);

	//List<XrayCategoryOuterRecursive> fetchAllTypes();

	public List<Xray> fetchAllByOgranisation(int organisationId);

	public List<Xray> fetchAll(Integer id);

	public List<Xray> fetchTop50byOrganisation(Integer id);


}
