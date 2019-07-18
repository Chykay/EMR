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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

////import org.calminfotech.system.models.Department;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_point_requests")
@SQLDelete(sql = "UPDATE inventory_point_requests SET is_deleted = 1 WHERE point_request_id = ?")
@Where(clause = "is_deleted <> 1")
public class PointRequest extends Common {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_request_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "point_request_batch_id", unique = true, updatable = false)
	private String requestBatchId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pointRequest", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id asc")
	@Where(clause = "is_deleted <> 1")
	private Set<PointRequestLine> pointRequestLines;

	@Column(name = "date_of_request")
	@Temporal(TemporalType.DATE)
	private Date requestDate;

	@Column(name = "request_status")
	private char requestStatus;

	@Transient
	private String requestStatusTitle;

	// @Column(name = "point_id")
	// private int pointId;

	@ManyToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "unit_id")
	private HrunitCategory unit;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "unitto_id", nullable = false)
	private HrunitCategory unitto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRequestBatchId() {
		return requestBatchId;
	}

	public void setRequestBatchId(String requestBatchId) {
		this.requestBatchId = requestBatchId;
	}

	public Set<PointRequestLine> getPointRequestLines() {
		return pointRequestLines;
	}

	public void setPointRequestLines(Set<PointRequestLine> pointRequestLines) {
		this.pointRequestLines = pointRequestLines;
	}

	public VisitWorkflowPoint getVisitWorkflowPoint() {
		return visitWorkflowPoint;
	}

	public void setVisitWorkflowPoint(VisitWorkflowPoint visitWorkflowPoint) {
		this.visitWorkflowPoint = visitWorkflowPoint;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public char getRequestStatus() {

		return requestStatus;
	}

	public void setRequestStatus(char requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestStatusTitle() {

		PointRequestStatus reqStat = PointRequestStatus
				.fromCode(this.requestStatus);
		if (reqStat != null) {
			return reqStat.name();
		}

		return null;
	}

	public void setRequestStatusTitle(String requestStatusTitle) {
		this.requestStatusTitle = requestStatusTitle;
	}

	public HrunitCategory getUnit() {
		return unit;
	}

	public void setUnit(HrunitCategory unit) {
		this.unit = unit;
	}

	public HrunitCategory getUnitto() {
		return unitto;
	}

	public void setUnitto(HrunitCategory unitto) {
		this.unitto = unitto;
	}

}
