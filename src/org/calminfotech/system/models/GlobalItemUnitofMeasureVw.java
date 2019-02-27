package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@NamedNativeQuery(
//	name = "spGetassignedunit1",
//	query = "{CALL sp_assigned_unit1(:itemId)}",
//	callable = true,
//	resultClass = GlobalItemUnitofMeasure.class
//)
@Table(name = "vw_globalitem_unitofmeasure")

public class GlobalItemUnitofMeasureVw {
		//getter and setter
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Integer id;
	
		
		@Column(name = "unitofmeasure_id")
		private Integer unitofmeasure_id;
		
		@Column(name = "measurename")
		private String measurename;
	
		@Column(name = "globalitem_id")
		private Integer globalitem_id;
		
		
		@Column(name = "globalitemname")
		private String globalitemname;
		
		@Column(name = "is_prescribe")
		private Boolean  is_precribe;
		
		@Column(name = "containedvalue")
		private Integer containedvalue;
		

		@Column(name = "containedmeasure_id")
		
		private Integer  containedmeasure_id;
		
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
		
		@Column(name = "containedmeasurename")
		private String containedmeasurename;
		
		@Column(name = "searchname")
		private String searchname;
		
		
		
		
		public Boolean getIs_precribe() {
			return is_precribe;
		}

		public void setIs_precribe(Boolean is_precribe) {
			this.is_precribe = is_precribe;
		}

		public Integer getUnitofmeasure_id() {
			return unitofmeasure_id;
		}

		public void setUnitofmeasure_id(Integer unitofmeasure_id) {
			this.unitofmeasure_id = unitofmeasure_id;
		}

		public String getMeasurename() {
			return measurename;
		}

		public void setMeasurename(String measurename) {
			this.measurename = measurename;
		}

		public String getSearchname() {
			return searchname;
		}

		public void setSearchname(String searchname) {
			this.searchname = searchname;
		}
/*
		@OneToMany
		@JoinColumn(name = "globalitemunitofmeasure_id")
		private Set<BillSchemeMeasurePrice> billschememeasureprice;
		
		*/
		
		public String getGlobalitemname() {
			return globalitemname;
		}

		public void setGlobalitemname(String globalitemname) {
			this.globalitemname = globalitemname;
		}

	
		
		

		public String getContainedmeasurename() {
			return containedmeasurename;
		}

		public void setContainedmeasurename(String containedmeasurename) {
			this.containedmeasurename = containedmeasurename;
		}

		/*public Set<BillSchemeMeasurePrice> getBillschememeasureprice() {
			return billschememeasureprice;
		}

		public void setBillschememeasureprice(
				Set<BillSchemeMeasurePrice> billschememeasureprice) {
			this.billschememeasureprice = billschememeasureprice;
		}*/

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

		

		public Integer getGlobalitem_id() {
			return globalitem_id;
		}

		public void setGlobalitem_id(Integer globalitem_id) {
			this.globalitem_id = globalitem_id;
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