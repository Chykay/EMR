package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.XrayTypeForm;
import org.calminfotech.system.models.XrayType;

public interface XrayTypeBo {
	public XrayType getXrayTypeById(int id);
	
	public void save(XrayType XrayType);
	
	public void saveForm(XrayTypeForm dForm);

	public void delete(XrayType XrayType);

	public void update(XrayType XrayType);
		
	public List<XrayType> fetchAll();

	public List<XrayType> fetchAllByOrganisation();
}
