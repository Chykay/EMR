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
@Table(name = "inventory_point_stock_in_line_items")
@SQLDelete(sql = "UPDATE inventory_point_stock_in_line_items SET is_deleted = 1 WHERE line_item_id = ?")
@Where(clause = "is_deleted <> 1")
public class PointStockInLine extends LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "line_item_id", unique = true, nullable = false)
	private Integer id;

	// private String batchId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id", nullable = false)
	private PointStockIn stockInBatch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PointStockIn getStockInBatch() {
		return stockInBatch;
	}

	public void setStockInBatch(PointStockIn stockInBatch) {
		this.stockInBatch = stockInBatch;
	}

}
