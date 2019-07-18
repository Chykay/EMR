package org.calminfotech.inventory.service;

import java.util.Date;
import java.util.List;

import org.calminfotech.inventory.daoInterface.VendorDaoInterface;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.exceptions.UnableToGenerateVendorCodeException;
import org.calminfotech.inventory.forms.VendorForm;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorManagerImpl implements VendorManagerInterface {

	@Autowired
	private VendorDaoInterface vendorDaoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Utilities utilities;

	@Override
	public Vendor save(VendorForm vendorForm)
			throws UnableToGenerateVendorCodeException {
		// TODO Auto-generated method stub

		Vendor vendor = new Vendor();

		vendor.setContactAddress(vendorForm.getContactAddress());
		vendor.setEmail(vendorForm.getEmail());
		vendor.setTelephoneNumber(vendorForm.getTelephoneNumber());
		vendor.setUrl(vendorForm.getUrl());
		vendor.setVendorName(vendorForm.getVendorName());
		vendor.setFax(vendorForm.getFax());
		vendor.setVendorId(this.getVendorCode(vendor.getVendorName())); // auto
																		// generate
																		// vendor
																		// id
																		// here
		vendor.setCreateDate(new Date());
		vendor.setCreatedBy(this.userIdentity.getUsername());
		vendor.setOrganisation(userIdentity.getOrganisation());
		this.vendorDaoImpl.save(vendor);

		return vendor;
	}

	private String getVendorCode(String vendorName)
			throws UnableToGenerateVendorCodeException {
		// TODO Auto-generated method stub
		//
		if (vendorName != null && vendorName.length() > 3) {
			String vendorShortNameCode = null;
			String code = null;

			vendorShortNameCode = vendorName.substring(0, 3).toUpperCase();

			// get last generated code for vendor with same vendor short name
			// code if exist otherwise create a new code
			// for this vendor

			String lastVendorCodeGen = this.vendorDaoImpl
					.getLastVendorCodeGenWithShortName(vendorShortNameCode);

			if (lastVendorCodeGen != null && !lastVendorCodeGen.isEmpty()) {

				String details[] = lastVendorCodeGen.split("-");

				if (details != null && details.length > 2) {
					try {
						String subCode = details[1];
						int intSubCode = Integer.parseInt(subCode);
						intSubCode++;
						if (intSubCode != 0) {
							int numPad = 4;
							int cntrl = numPad
									- String.valueOf(intSubCode).length();
							String zeroPaddings = "";
							for (int i = 0; i < cntrl; i++) {
								zeroPaddings += 0;
							}
							subCode = zeroPaddings + intSubCode;
							code = vendorShortNameCode + "-" + subCode;
							return code;
						}
					} catch (NumberFormatException e) {
					}
				}
				return vendorShortNameCode + "-0001";

			} else {
				return vendorShortNameCode + "-0001";
			}
		}
		throw new UnableToGenerateVendorCodeException(
				"Unable to generate vendor code:Either vendor name does not exist or less than 3 characters");
	}

	@Override
	public Vendor update(VendorForm vendorForm) {
		// TODO Auto-generated method stub

		try {
			Vendor vendor = this.vendorDaoImpl.getVendorDetailsById(vendorForm
					.getId());

			vendor.setContactAddress(vendorForm.getContactAddress());
			vendor.setEmail(vendorForm.getEmail());
			vendor.setTelephoneNumber(vendorForm.getTelephoneNumber());
			vendor.setUrl(vendorForm.getUrl());
			vendor.setVendorName(vendorForm.getVendorName());
			vendor.setFax(vendorForm.getFax());

			vendor.setId(vendorForm.getId());
			vendor.setVendorId(vendorForm.getVendorId());
			vendor.setModifiedDate(new Date());

			this.vendorDaoImpl.update(vendor);

			return vendor;
		} catch (RecordNotFoundException e) {
		}
		return null;
	}

	@Override
	public List<Vendor> getVendorsList() throws RecordNotFoundException {
		// TODO Auto-generated method stub

		return this.vendorDaoImpl.getVendorsList();
	}

	@Override
	public Vendor getVendorDetailsById(int id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.vendorDaoImpl.getVendorDetailsById(id);
	}

	@Override
	public void delete(Vendor vendor) {
		// TODO Auto-generated method stub
		this.vendorDaoImpl.delete(vendor);

	}

}
