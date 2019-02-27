package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetassignedunit1",
	query = "{CALL sp_assigned_unit1(:itemId)}",
	callable = true,
	resultClass = GlobalItemUnitofMeasure.class
)
@Table(name = "globalitem_unitofmeasure")
public class GlobalItemUnitofMeasure {
	//Variables
	
		//getter and setter
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Integer id;
		
		
		
		@ManyToOne
		@JoinColumn(name = "unitofmeasure_id")
		private GlobalUnitofMeasure  unitofmeasure;
		
	
		@ManyToOne
		@JoinColumn(name = "globalitem_id")
		private GlobalItem  globalitem;
		
			
		@Column(name = "containedvalue")
		private Integer containedvalue;
		

		@Column(name = "is_prescribe")
		private Boolean  is_precribe;
		
		
		private Integer  containedmeasure_id;
		
		/*
		@OneToMany
		@JoinColumn(name = "globalitemunitofmeasure_id")
		private Set<BillSchemeMeasurePrice> billschememeasureprice;
		
		*/
		
		

	
		public Boolean getIs_precribe() {
			return is_precribe;
		}

		public void setIs_precribe(Boolean is_precribe) {
			this.is_precribe = is_precribe;
		}

	/*	public Set<BillSchemeMeasurePrice> getBillschememeasureprice() {
			return billschememeasureprice;
		}

		public void setBillschememeasureprice(
				Set<BillSchemeMeasurePrice> billschememeasureprice) {
			this.billschememeasureprice = billschememeasureprice;
		}*/

		@Column(name = "created_by")
		private String createdby;
		
		@Column(name = "created_Date")
		private Date createDate;
		
		@Column(name = "modified_by")
		private String modifiedBy;
		
		@Column(name = "modified_date")
		private Date modifiedDate;
		
		@Column(name = "is_deleted")
		private boolean isDeleted;
		
		
		
		@Column(name = "organisation_id")
		private Integer organisation_id;

		

		public Integer getContainedmeasure_id() {
			return containedmeasure_id;
		}

		public void setContainedmeasure_id(Integer containedmeasure_id) {
			this.containedmeasure_id = containedmeasure_id;
		}


		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		

		public GlobalUnitofMeasure getUnitofmeasure() {
			return unitofmeasure;
		}

		public void setUnitofmeasure(GlobalUnitofMeasure unitofmeasure) {
			this.unitofmeasure = unitofmeasure;
		}

		
		
		public GlobalItem getGlobalitem() {
			return globalitem;
		}

		public void setGlobalitem(GlobalItem globalitem) {
			this.globalitem = globalitem;
		}

		public Integer getContainedvalue() {
			return containedvalue;
		}

		public void setContainedvalue(Integer containedvalue) {
			this.containedvalue = containedvalue;
		}

		public String getCreatedby() {
			return createdby;
		}

		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		
		public Date getModifiedDate() {
			return modifiedDate;
		}

		public void setModifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Integer getOrganisation_id() {
			return organisation_id;
		}

		public void setOrganisation_id(Integer organisation_id) {
			this.organisation_id = organisation_id;
		}

		
		
		
		
		
		}