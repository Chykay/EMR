package org.calminfotech.patient.utils;

public class RandomString {

	public static enum RandomStringMode {
		ALPHA, ALPHANUMERIC, NUMERIC
	}

	public static String generateRandomString(int length, RandomStringMode mode) {
		StringBuffer buffer = new StringBuffer();
		String characters = "";

		switch (mode) {
		case ALPHA:
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case NUMERIC:
			characters = "1234567890";
			break;
		}

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}

		return buffer.toString();

	}

	public static void main(String[] args) throws Exception {
		System.out.println(generateRandomString(3, RandomStringMode.ALPHA));
		System.out.println(generateRandomString(10, RandomStringMode.ALPHANUMERIC));
		System.out.println(generateRandomString(5, RandomStringMode.NUMERIC));		

	}

}
