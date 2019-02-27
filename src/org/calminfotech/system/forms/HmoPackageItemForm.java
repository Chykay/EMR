package org.calminfotech.system.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

public class HmoPackageItemForm {

	private Integer packageId;
	
	@Range(min=1, message = "Select a item")
	private int itemId;
	
	@Pattern(regexp = "[0-9]*\\.[0-9]+|[0-9]+", message = "Insert a valid price! Eg: 1000 or 1500.50")
	private String price;

	/**
	 * @return the packageId
	 */
	public Integer getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	
	
}
