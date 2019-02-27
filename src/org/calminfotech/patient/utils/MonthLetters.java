package org.calminfotech.patient.utils;

public class MonthLetters {

	
	public static String monthInLetters(int month)
	{
		String character = "";
		
		switch(month)
		{
			case 1:
				character = "A";
				break;
			
			case 2:
				character = "B";
				break;
			
			case 3:
				character = "C";
				break;
			
			case 4:
				character = "D";
				break;
			
			case 5:
				character = "E";
				break;
			
			case 6:
				character = "F";
				break;
			
			case 7:
				character = "G";
				break;
			
			case 8:
				character = "H";
				break;
			
			case 9:
				character = "I";
				break;
			
			case 10:
				character = "J";
				break;
			
			case 11:
				character = "K";
				break;
			
			case 12:
				character = "L";
				break;		
		}
		
		return character;		
	}
	
	
	
	
	
	
	
	
	/*
	public static void main(String[] args) {
		
		MonthLetters letter = new MonthLetters();
		System.out.println(letter.monthInLetters(1));

	}*/

}
