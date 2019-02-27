package org.calminfotech.utils;

import java.util.Date;
import java.util.Random;

public class AutoGen {

	
	public Integer genNum() {
		Integer strNum = 0;
		try {
			Random randomNumber = new Random();
			int i = randomNumber.nextInt(10);
			int j = randomNumber.nextInt(100);
			int k = randomNumber.nextInt(100);
			int l = randomNumber.nextInt(40);
			int m = randomNumber.nextInt(10);
			//strNum ="PX"+(new Date().getYear())+"/"+ i + "" + j +"/"+ k + "" + l;
			
		///	strNum ="HSP"+(new Date().getYear())+"/"+ i + "" + j ;
			System.out.println( strNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNum;
	}
	public static void main(String[] gen) {
		new AutoGen().genNum();
	}
	
	
}
