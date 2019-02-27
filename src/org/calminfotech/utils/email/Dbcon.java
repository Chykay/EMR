/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.calminfotech.utils.email;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ajiboye
 */

public class Dbcon {

	static Connection conn = null;
	 static String url =
	 "jdbc:sqlserver://134.213.17.169:1433;databaseName=medical";
//	static String url = "jdbc:sqlserver://192.168.200.69:1433;databaseName=medical";

	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dbusername = "flora";
	static String dbpassword = "United05";

	public static Connection getConnection() {

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
