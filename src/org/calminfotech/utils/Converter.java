package org.calminfotech.utils;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Double toDouble(Integer value){
		Integer intVal = new Integer(value);
		double doubleVal = intVal.doubleValue();
		return doubleVal;
	}
	
	public Float toFloat(Integer value){
		Integer intVal = new Integer(value);
		float floatVal = intVal.floatValue();
		return floatVal;
	}
	
	public Double toDouble(String value){
		String strVal = new String(value);
		double doubleVal = Double.parseDouble(value);
		return doubleVal;
	}

}
