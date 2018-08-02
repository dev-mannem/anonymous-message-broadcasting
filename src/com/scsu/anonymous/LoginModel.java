/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;



public class LoginModel {
	DbConnection dbConnection = new DbConnection();
        PasswordEncrypt pwdEncrypt = new PasswordEncrypt();
	public String uname;
	public String pwd;
        public String desig;
	public String login(String uname,String password){	
		
		password = pwdEncrypt.MD5(password);
		String query = "SELECT * FROM NewUser where email='"+uname+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				uname = rs.getString("email");
				pwd = rs.getString("password");
                                desig = rs.getString("designation");
				if(uname.equalsIgnoreCase(uname) && pwd.equalsIgnoreCase(password)){
					return desig;
				}
				
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		

		System.out.println("failure");
		return "Failure";
	}	
        
        public String UpdatePassword(String uname, String password){
            PreparedStatement pstmt;
                
                int result =0;
                password = pwdEncrypt.MD5(password);
        String query = "update newuser set Password = ? where Email = ?";
        Connection con= dbConnection.createNewConnection();
        
                      
		try {			
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1, password);
                        pstmt.setString(2, uname);
                        result = pstmt.executeUpdate();
                        if(result == 1){
                            return "Success";
                        }
                }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}       
              
		System.out.println("failure");
		return "Failure";
        }
        
        public String checkPassword(String uname,String password){	
		
		password = pwdEncrypt.MD5(password);
		String query = "SELECT * FROM NewUser where email='"+uname+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				uname = rs.getString("email");
				pwd = rs.getString("password");
				if(uname.equalsIgnoreCase(uname) && pwd.equalsIgnoreCase(password)){
					return "Success";
				}
				
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		

		System.out.println("failure");
		return "Failure";
	}
        
        public String name(String uname, String desig){
            String fname;
            String lname;
            String name = null;
            String query = "SELECT * FROM NewUser where email='"+uname+"' and designation ='"+desig+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				fname = rs.getString("FirstName");
                                lname = rs.getString("LastName");
                                name = lname +","+fname;
				
					return name;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("failure");
		return "Failure";
        }
}
