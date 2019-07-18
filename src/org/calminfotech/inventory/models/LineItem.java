package org.calminfotech.inventory.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;

@MappedSuperclass
public class LineItem extends Common {

	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	protected GlobalItemUnitofMeasureVw globalUnitofMeasure;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "unit_of_measure_id", nullable = false) protected
	 * GlobalUnitofMeasure globalUnitofMeasure;
	 */

	@Column(name = "batchno")
	private String batchno;

	@Column(name = "gtinno")
	private String tinno;

	@Column(name = "serialno")
	private String serialno;

	@Temporal(TemporalType.DATE)
	@Column(name = "manufacturedate")
	private Date manufacturedate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate")
	private Date expirydate;

	@ManyToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	protected GlobalItem globalItem;

	@Column(name = "quantity")
	protected int quantity;

	public GlobalItemUnitofMeasureVw getGlobalUnitofMeasure() {
		return globalUnitofMeasure;
	}

	public void setGlobalUnitofMeasure(
			GlobalItemUnitofMeasureVw globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}

	/*
	 * public GlobalUnitofMeasure getGlobalUnitofMeasure() { return
	 * globalUnitofMeasure; }
	 * 
	 * public void setGlobalUnitofMeasure(GlobalUnitofMeasure
	 * globalUnitofMeasure) { this.globalUnitofMeasure = globalUnitofMeasure; }
	 */

	public GlobalItem getGlobalItem() {
		return globalItem;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public void setGlobalItem(GlobalItem globalItem) {
		this.globalItem = globalItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getManufacturedate() {
		return manufacturedate;
	}

	public void setManufacturedate(Date manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

}
