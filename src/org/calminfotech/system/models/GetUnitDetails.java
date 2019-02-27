package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/*@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hr_user_unit")*/
public class GetUnitDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "unit_id")
	private int unit_Id;
	
	@Column(name = "unit_name")
	private String unit_name;

	public int getUnit_Id() {
		return unit_Id;
	}


	public void setUnit_Id(int unit_Id) {
		this.unit_Id = unit_Id;
	}


	public String getUnit_name() {
		return unit_name;
	}


	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	
	
	

}
