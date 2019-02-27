package org.calminfotech.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AutoGenerate {
	
	public String mygen() {
		String strNum = "";
			 Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhmmss");
				String formattedDate = sdf.format(date);
				System.out.println(formattedDate);	
			 
		 strNum= formattedDate;
		return strNum;
		 }
	
	public static void main(String[] gen) {
		new AutoGenerate().mygen();
	}
	

}
