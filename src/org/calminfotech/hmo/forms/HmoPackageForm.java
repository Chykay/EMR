package org.calminfotech.hmo.forms;

public class HmoPackageForm {

	private Integer id;

	// @NotBlank(message = "Field cannot be empty!")
	private String name;

	private Integer hmo_id;
	private Integer billingscheme_id;
	private String claims;

	private Integer status_id;
	private Double percentcover;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBillingscheme_id() {
		return billingscheme_id;
	}

	public void setBillingscheme_id(Integer billingscheme_id) {
		this.billingscheme_id = billingscheme_id;
	}

	public Integer getHmo_id() {
		return hmo_id;
	}

	public void setHmo_id(Integer hmo_id) {
		this.hmo_id = hmo_id;
	}

	public Double getPercentcover() {
		return percentcover;
	}

	public void setPercentcover(Double percentcover) {
		this.percentcover = percentcover;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public String getClaims() {
		return claims;
	}

	public void setClaims(String claims) {
		this.claims = claims;
	}

}
