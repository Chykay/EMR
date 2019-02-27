package org.calminfotech;

import java.math.BigDecimal;

public class SampleStringFormat {

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// String url = "/admin/user/fancy";
	// String[] parts = url.split("/");
	//
	// System.out.println(parts.length);
	// for (int i = 0; i < parts.length; i++)
	// System.out.println(parts[i]);
	// }

	public static void main(String[] args) {

		BigDecimal bd = new BigDecimal(0);
		bd.add(new BigDecimal(30));

		bd = bd.add(new BigDecimal(50));
		System.out.println("Big Decimal sum: " + bd);
		bd = bd.add(new BigDecimal(50));
		System.out.println("Big Decimal sum: " + bd);
		bd = bd.add(new BigDecimal(50));
		System.out.println("Big Decimal sum: " + bd);
		
	}
}
