package org.calminfotech.inventory.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.calminfotech.billing.models.VendorTransaction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_vendors")
@SQLDelete(sql = "UPDATE inventory_vendors SET is_deleted = 1 WHERE vendor_id = ?")
@Where(clause = "is_deleted <> 1")
public class Vendor extends Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "vendor_code", unique = true)
	private String vendorId;

	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone_no")
	private String telephoneNumber;

	@Column(name = "contact_address")
	private String contactAddress;

	@Column(name = "url")
	private String url;

	@Column(name = "fax")
	private String fax;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	@Where(clause = "is_deleted <> 1")
	@OrderBy("id DESC")
	private Set<VendorTransaction> vendortransaction = new HashSet<VendorTransaction>();

	public Set<VendorTransaction> getVendortransaction() {
		return vendortransaction;
	}

	public void setVendortransaction(Set<VendorTransaction> vendortransaction) {
		this.vendortransaction = vendortransaction;
	}

	@Transient
	private Map mfig;

	public Map getMfig() {

		Double totamt = 0.00;
		Double totpaymt = 0.00;
		Double tothmo = 0.00;
		Double totcash = 0.00;
		Double totbal = 0.00;
		Double totdebit = 0.00;
		Double totcredit = 0.00;

		Double totcashmode = 0.00;
		Double totposmode = 0.00;
		Double tottransfermode = 0.00;
		Double totaccountmode = 0.00;

		Double totcustbal = 0.00;

		Map<String, Double> mfig = new HashMap<String, Double>();

		try {
			for (VendorTransaction pay : this.getVendortransaction()) {
				try {
					totcustbal = totcustbal + pay.getAmount();

				} catch (Exception e) {
					System.out
							.println("Problem in getting vendor attached to invoice set");
				}

				if (pay.getAmount().doubleValue() < 0
						&& pay.getDrcr().equalsIgnoreCase("Dr")) {
					try {
						totdebit = totdebit + pay.getAmount();

					} catch (Exception e) {
						System.out
								.println("Problem in getting vendor debit to invoice set");
					}
				}

				if (pay.getAmount().doubleValue() > 0
						&& pay.getDrcr().equalsIgnoreCase("Cr")) {
					try {
						totcredit = totcredit + pay.getAmount();

					} catch (Exception e) {
						System.out
								.println("Problem in getting vendor credit to invoice set");
					}

				}

			}

		} catch (Exception e) {
			System.out
					.println("Problem in getting vendor bal attached to invoice set");
		}

		// mfig.put("totamt", totamt);
		// mfig.put("totpaymt", totpaymt);
		// mfig.put("totcash", totcash);
		// mfig.put("tothmo", tothmo);

		mfig.put("totcustbal", totcustbal);
		mfig.put("totdebit", totdebit);
		mfig.put("totcredit", totcredit);

		// System.out.print("mifgavailable" + mfig.get("totcustavailablebal"));

		// System.out.print("totcash" + totcash);
		// System.out.print("totpayment" + totpaymt);

		// System.out.print("totbal" + totbal);

		return mfig;

	}

	public void setMfig(Map mfig) {
		this.mfig = mfig;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor", cascade = CascadeType.ALL)
	private Set<StockIn> batchSupplies;

	public String getVendorId() {
		return vendorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Set<StockIn> getBatchSupplies() {
		return batchSupplies;
	}

	public void setBatchSupplies(Set<StockIn> batchSupplies) {
		this.batchSupplies = batchSupplies;
	}

}
