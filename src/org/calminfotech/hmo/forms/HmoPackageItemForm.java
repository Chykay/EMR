package org.calminfotech.hmo.forms;

public class HmoPackageItemForm {

	private Integer id;

	private String name;

	private String description;
	private String spendingLimit;
	private Integer period_id;
	private Integer timeNo;
	private Integer periodNo;
	private boolean useaspercent;

	private String billingitem_id;

	private Integer hmopackage_id;

	private Integer status_id;

	private Boolean Isall;

	public Boolean getUseaspercent() {
		return useaspercent;
	}

	public void setUseaspercent(Boolean useaspercent) {
		this.useaspercent = useaspercent;
	}

	public Boolean getIsall() {
		return Isall;
	}

	public void setIsall(Boolean isall) {
		Isall = isall;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public String getBillingitem_id() {
		return billingitem_id;
	}

	public void setBillingitem_id(String billingitem_id) {
		this.billingitem_id = billingitem_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public Integer getHmopackage_id() {
		return hmopackage_id;
	}

	public void setHmopackage_id(Integer hmopackage_id) {
		this.hmopackage_id = hmopackage_id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(String spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public Integer getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(Integer period_id) {
		this.period_id = period_id;
	}

	public Integer getTimeNo() {
		return timeNo;
	}

	public void setTimeNo(Integer timeNo) {
		this.timeNo = timeNo;
	}

	public Integer getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}

}
