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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "point_inventory_orders")
@SQLDelete(sql = "UPDATE point_inventory_orders SET is_deleted = 1 WHERE order_id = ?")
@Where(clause = "is_deleted <> 1")
public class Order extends Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "order_id", unique = true, updatable = false)
	private String orderId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id asc")
	private Set<OrderLine> orderLines;

	@OneToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;

	@Column(name = "date_of_order")
	@Temporal(TemporalType.DATE)
	private Date dateOfOrder;

	@Column(name = "order_by")
	private char orderBy;

	@Transient
	private double totalPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public char getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(char orderBy) {
		this.orderBy = orderBy;
	}

	public double getTotalPrice() {

		double totalPrice = 0;
		if (this.orderLines != null) {
			for (OrderLine line : this.orderLines) {

				totalPrice += (line.getUnitPrice() * line.getQuantity());
			}

		}
		return totalPrice;
	}
	

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public VisitWorkflowPoint getVisitWorkflowPoint() {
		return visitWorkflowPoint;
	}

	public void setVisitWorkflowPoint(VisitWorkflowPoint visitWorkflowPoint) {
		this.visitWorkflowPoint = visitWorkflowPoint;
	}

}
