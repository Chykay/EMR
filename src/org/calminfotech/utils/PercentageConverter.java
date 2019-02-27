package org.calminfotech.utils;

public class PercentageConverter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercentageConverter pcnt = new PercentageConverter();
		int val = 2000, dbVal =2000;
		pcnt.percent(val, dbVal);
	}
	
	public String percent(double val, double dbVal){
		String result = null;
		System.out.println(" 1% "+((double)(1*(dbVal/100.0f))));
		System.out.println(" 99% "+((double)(99*(dbVal/100.0f))));
		System.out.println(" 100% "+((double)(100*(dbVal/100.0f))));
		System.out.println(val);
		if(((double)(0 *(dbVal/100.0f))) >= val ){
			System.out.println(((double)(0*(dbVal/100.0f))));
			result = "Yet to pay";
			
		}else if(val >= ((double)(1 *(dbVal/100.0f))) || val <= ((double)(99 *(dbVal/100.0f))) ){
			System.out.println(((double)(99*(dbVal/100.0f))));
			result = "Incomplete";
		}else if(((double)(100*(dbVal/100.0f)))== val){
			result = "Fully paid";
			System.out.println(((double)(100*(dbVal/100.0f))));
		}
		System.out.println(result);
		return result;
	}
}
