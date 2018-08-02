/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationModel {
	DbConnection dbConnection = new DbConnection();
        PasswordEncrypt pwdEncrypt = new PasswordEncrypt();
	public String createNewUser(String fname, String lname, String email, String password, String desig){	
            
		password=pwdEncrypt.MD5(password);
		String query = "INSERT INTO NewUser (FirstName,LastName,Email,Password,Designation) VALUES (?,?,?,?,?)";
		Connection con= dbConnection.createNewConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
                        pstmt.setString(5, desig);
			int result = pstmt.executeUpdate();
			if(result==1){
				return "Success";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		

		
		return null;
	}	
}
