package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/*@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hr_user_point")*/
public class GetPointDetails {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "point_id")
	private int point_id;
	
	@Column(name = "point_name")
	private String point_name;

	public int getPoint_id() {
		return point_id;
	}

	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}

	public String getPoint_name() {
		return point_name;
	}

	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}

	
}
