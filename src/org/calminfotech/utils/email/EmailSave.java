/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.calminfotech.utils.email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ajiboye
 */
public class EmailSave {
	String insert, numTrial = "1";

	public EmailSave() {
	}

	public void inserData(String username, String password, String to,
			String header, String message, String time, String status) {
		String numbrOfTrial = "1";
		insert = "INSERT INTO mailqueue([from], [to], header, body, status, lastprocessdate, numberTrial)"
				+ " VALUES ('"
				+ username
				+ "','"
				+ to
				+ "','"
				+ header
				+ "','"
				+ message
				+ "','"
				+ status
				+ "','"
				+ time
				+ "','"
				+ numbrOfTrial + "')";
		try {
			PreparedStatement pst;
			pst = Dbcon.getConnection().prepareStatement(insert);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.err.println("Error Occurred in database" + e);
		}
	}
}