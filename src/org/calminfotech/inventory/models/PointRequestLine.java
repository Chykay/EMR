package org.calminfotech.inventory.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_point_request_line_items")
@SQLDelete(sql = "UPDATE inventory_point_request_line_items SET is_deleted = 1 WHERE point_request_line_id = ?")
@Where(clause = "is_deleted <> 1")
public class PointRequestLine extends LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_request_line_id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "point_request_id", nullable = false)
	private PointRequest pointRequest;

	@Column(name = "qty_approved")
	private int quantityApproved;

	@Transient
	private int quantityAvailable;

	@Column(name = "request_status")
	private char requestStatus;

	@OneToMany
	@OrderBy("id ASC")
	@JoinColumn(name = "requestid")
	private Set<Storeissue_log> storeissue;

	public Set<Storeissue_log> getStoreissue() {
		return storeissue;
	}

	public void setStoreissue(Set<Storeissue_log> storeissue) {
		this.storeissue = storeissue;
	}

	public void setTotdisp(Integer totdisp) {
		this.totdisp = totdisp;
	}

	@Transient
	private Integer totdisp;

	public Integer getTotdisp() {

		totdisp = 0;

		for (Storeissue_log disp : this.getStoreissue()) {
			if (disp.getAction().equals("Given")) {
				totdisp = totdisp + disp.getQty();
			}
		}

		return totdisp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PointRequest getPointRequest() {
		return pointRequest;
	}

	public void setPointRequest(PointRequest pointRequest) {
		this.pointRequest = pointRequest;
	}

	public int getQuantityApproved() {
		return quantityApproved;
	}

	public void setQuantityApproved(int quantityApproved) {
		this.quantityApproved = quantityApproved;
	}

	public char getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(char requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

}
