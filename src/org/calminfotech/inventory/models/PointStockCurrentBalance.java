package org.calminfotech.inventory.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_point_stock_current_balances")
@SQLDelete(sql = "UPDATE inventory_point_stock_current_balances SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
// public class PointStockOpeningBalance extends StockOpeningBalance{
public class PointStockCurrentBalance extends Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	private GlobalItem globalItem;

	@Column(name = "current_balance")
	private int currentBalance;


	//@Temporal(TemporalType.DATE)
	@Column(name = "date_added")
	private Date dateAdded;
	
	

	/*@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	private GlobalUnitofMeasure globalUnitofMeasure;

	
	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	protected GlobalItemUnitofMeasureVw globalUnitofMeasure;
*/
	
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@ManyToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", nullable = false)
   	private HrunitCategory unit;

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

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}

	/*public GlobalUnitofMeasure getGlobalUnitofMeasure() {
		return globalUnitofMeasure;
	}

	public void setGlobalUnitofMeasure(GlobalUnitofMeasure globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}*/
	
	

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
	
	

}
