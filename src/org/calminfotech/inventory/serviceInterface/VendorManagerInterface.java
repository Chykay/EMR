package org.calminfotech.inventory.serviceInterface;

import java.util.List;

import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.exceptions.UnableToGenerateVendorCodeException;
import org.calminfotech.inventory.forms.VendorForm;
import org.calminfotech.inventory.models.Vendor;

public interface VendorManagerInterface {

	public Vendor save(VendorForm vendorForm) throws UnableToGenerateVendorCodeException;

	public List<Vendor> getVendorsList() throws RecordNotFoundException;

	public Vendor getVendorDetailsById(int id) throws RecordNotFoundException;
	
	public Vendor update(VendorForm vendorForm);

	public void delete(Vendor vendor);



}
