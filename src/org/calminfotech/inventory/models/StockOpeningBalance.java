package org.calminfotech.inventory.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;

@Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_stock_opening_balances")
@SQLDelete(sql = "UPDATE inventory_stock_opening_balances SET is_deleted = 1 WHERE id = ?")
// @Where(clause = "is_deleted <> 1")
public class StockOpeningBalance extends Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;

	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	protected GlobalItem globalItem;

	@Column(name = "opening_balance")
	protected int openingBalance;

	@ManyToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;

	@ManyToOne
	@JoinColumn(name = "unit_id", nullable = false)
	private HrunitCategory unit;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_added")
	private Date dateAdded;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "unit_of_measure_id", nullable = false) protected
	 * GlobalUnitofMeasure globalUnitofMeasure;
	 */

	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	protected GlobalItemUnitofMeasureVw globalUnitofMeasure;

	@Column(name = "description")
	private String description;

	@Column(name = "action")
	private String action;

	@Column(name = "batchno")
	private String batchno;

	/* gt tin */
	@Column(name = "gtinno")
	private String tinno;

	/* serial */
	@Column(name = "serialno")
	private String serialno;

	@Column(name = "reorderlevel")
	private Integer reorderlevel;

	@Column(name = "cost")
	private Double cost;

	@Temporal(TemporalType.DATE)
	@Column(name = "manufacturedate")
	private Date manufacturedate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expirydate")
	private Date expirydate;

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GlobalItem getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(GlobalItem globalItem) {
		this.globalItem = globalItem;
	}

	public int getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VisitWorkflowPoint getVisitWorkflowPoint() {
		return visitWorkflowPoint;
	}

	public void setVisitWorkflowPoint(VisitWorkflowPoint visitWorkflowPoint) {
		this.visitWorkflowPoint = visitWorkflowPoint;
	}

	public HrunitCategory getUnit() {
		return unit;
	}

	public void setUnit(HrunitCategory unit) {
		this.unit = unit;
	}

	public GlobalItemUnitofMeasureVw getGlobalUnitofMeasure() {
		return globalUnitofMeasure;
	}

	public void setGlobalUnitofMeasure(
			GlobalItemUnitofMeasureVw globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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

	public Integer getReorderlevel() {
		return reorderlevel;
	}

	public void setReorderlevel(Integer reorderlevel) {
		this.reorderlevel = reorderlevel;
	}

	/*
	 * public GlobalUnitofMeasure getGlobalUnitofMeasure() { return
	 * globalUnitofMeasure; }
	 * 
	 * public void setGlobalUnitofMeasure(GlobalUnitofMeasure
	 * globalUnitofMeasure) { this.globalUnitofMeasure = globalUnitofMeasure; }
	 */

}
