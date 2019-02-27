package org.calminfotech.patient.utils;

public class ReverseMonthLetter {	
	
//	enum MonthLetter
//	{
	//	A, B, C, D, E, F, G, H, I, J, K, L ;
//	}
	

	//public String monthInLetters(int mnth)
	public String monthInLetters(String mnth)	
	{
		 
		String letter = mnth;
		
		
		String character = "0";			
		
		
		
		if (letter.equals("A"))  character="01";

		if (letter.equals("B"))  character="02";

		if (letter.equals("C"))  character="03";

		if (letter.equals("D"))  character="04";

		if (letter.equals("E"))  character="05";

		if (letter.equals("F"))  character="06";

		if (letter.equals("G"))  character="07";

		if (letter.equals("H"))  character="08";

		if (letter.equals("I"))  character="09";

		if (letter.equals("J"))  character="10";

		if (letter.equals("K"))  character="11";
		

		if (letter.equals("L"))  character="12";
		
		return character;		
	}	
	
	
	

}
