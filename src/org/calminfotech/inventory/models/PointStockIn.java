package org.calminfotech.inventory.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_point_stock_in")
@SQLDelete(sql = "UPDATE inventory_point_stock_in SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")

public class PointStockIn extends Common{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "stock_in_batch_id",unique = true, updatable=false)
	private String batchId;

	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendor vendor;*/
	
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "stockInBatch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause="is_deleted <> '1'")
    @OrderBy("id ASC")
    private Set<PointStockInLine> stockInLines;
	
    
	
	@Column(name = "date_stock_added")
	@Temporal(TemporalType.DATE)
	private Date stockInDate;
/*
	@ManyToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;
	*/
	
	@ManyToOne
	@JoinColumn(name = "unit_id", nullable = false)
   	private HrunitCategory unit;


	@ManyToOne
	@JoinColumn(name = "unitfrom_id", nullable = false)
   	private HrunitCategory unitfrom;


	@Column(name = "description")
	private String description;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HrunitCategory getUnitfrom() {
		return unitfrom;
	}

	public void setUnitfrom(HrunitCategory unitfrom) {
		this.unitfrom = unitfrom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}


  	public Set<PointStockInLine> getStockInLines() {
		return stockInLines;
	}

	public void setStockInLines(Set<PointStockInLine> stockInLines) {
		this.stockInLines = stockInLines;
	}

	public Date getStockInDate() {
		return stockInDate;
	}

	public void setStockInDate(Date stockInDate) {
		this.stockInDate = stockInDate;
	}

	/*
	public VisitWorkflowPoint getVisitWorkflowPoint() {
		return visitWorkflowPoint;
	}

	public void setVisitWorkflowPoint(VisitWorkflowPoint visitWorkflowPoint) {
		this.visitWorkflowPoint = visitWorkflowPoint;
	}
*/
	public HrunitCategory getUnit() {
		return unit;
	}

	public void setUnit(HrunitCategory unit) {
		this.unit = unit;
	}
	
	


}
