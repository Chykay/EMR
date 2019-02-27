package org.calminfotech.system.forms;

public class GlobalItemUnitOfMeasureForm {

    private Integer id;

	private Integer globalitem_id;
	
	private Integer unitofmeasure_id;
	
	private Integer containedvalue;
	
	private Integer containedmeasure_id;
	

	private boolean is_prescribe;
	


	public boolean isIs_prescribe() {
		return is_prescribe;
	}

	public void setIs_prescribe(boolean is_prescribe) {
		this.is_prescribe = is_prescribe;
	}

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

	public Integer getUnitofmeasure_id() {
		return unitofmeasure_id;
	}

	public void setUnitofmeasure_id(Integer unitofmeasure_id) {
		this.unitofmeasure_id = unitofmeasure_id;
	}

	public Integer getContainedvalue() {
		return containedvalue;
	}

	public void setContainedvalue(Integer containedvalue) {
		this.containedvalue = containedvalue;
	}
	
	
	
	
	
}
