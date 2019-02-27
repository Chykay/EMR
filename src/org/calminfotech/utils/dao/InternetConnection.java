package org.calminfotech.utils.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class InternetConnection {

	// dafault url
	static Boolean status;

	public static boolean checkConnection() throws UnknownHostException,
			IOException {
		try {
			try {
				URL url = new URL("http://www.google.com");
				System.out.println(url.getHost());
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				if (con.getResponseCode() == 200) {
					System.out.println("Connection established!!");
					status = true;
				}
			} catch (Exception exception) {
				System.out.println("No Connection");
				status = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return status;
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		if (checkConnection()) {
			System.out.println("Check Status :" + checkConnection());
		} else {
			System.out.println("Check Status :" + checkConnection());
		}
	}
}
