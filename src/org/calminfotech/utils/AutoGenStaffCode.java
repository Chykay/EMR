package org.calminfotech.utils;

import java.util.Calendar;
import java.util.Random;

public class AutoGenStaffCode {
	Calendar calendar = Calendar.getInstance();
	public String genCode() {
		String strNum = "";
		try {
			Random randomNumber = new Random();
			int i = randomNumber.nextInt(10);
			int j = randomNumber.nextInt(100);
			int k = randomNumber.nextInt(100);
			int l = randomNumber.nextInt(40);
			int m = randomNumber.nextInt(10);
			strNum ="SC"+(calendar.get(Calendar.YEAR))+"/"+ i + "" + j +"/"+ k + "" + l;
			
			System.out.println( strNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNum;
	}
	public static void main(String[] gen) {
		new AutoGenStaffCode().genCode();
	}

}
