package org.calminfotech.inventory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_stock_in_line_items")
@SQLDelete(sql = "UPDATE inventory_stock_in_line_items SET is_deleted = 1 WHERE line_item_id = ?")
@Where(clause = "is_deleted <> 1")
public class StockInLine extends LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "line_item_id", unique = true, nullable = false)
	private Integer id;

	// private String batchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private StockIn stockInBatch;

	@Column(name = "cost")
	private Double cost;

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

	public StockIn getStockInBatch() {
		return stockInBatch;
	}

	public void setStockInBatch(StockIn stockInBatch) {
		this.stockInBatch = stockInBatch;
	}

}
