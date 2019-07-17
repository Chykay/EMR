package org.calminfotech.billing.forms;
import org.hibernate.validator.constraints.NotBlank;

public class BillSchemeRankingPriceForm {

	private Integer id;
	
	private Integer globalitemranking_id;
	
	private Integer billscheme_id;
	
	private Double price;

	
	private Integer organisationId;
	
	private Integer status_id;
	
	
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getGlobalitemranking_id() {
		return globalitemranking_id;
	}
	public void setGlobalitemranking_id(Integer globalitemranking_id) {
		this.globalitemranking_id = globalitemranking_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getBillscheme_id() {
		return billscheme_id;
	}
	public void setBillscheme_id(Integer billscheme_id) {
		this.billscheme_id = billscheme_id;
	}
	
	
	
	
	
}
