/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

/**
 *
 * @author Swathi
 */
public class ChatModel {
    
    DbConnection dbConnection = new DbConnection();
    PasswordEncrypt pwdEncrypt = new PasswordEncrypt();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
Calendar calobj = Calendar.getInstance();
public String key;
public String quoient;

    
    public String checkParticipant(String email, Timestamp timeStamp){
        String mailid;
        String meetNum;
        int x = 300;
        Timestamp later = new Timestamp(timeStamp.getTime() + 300000);
        Timestamp previous = new Timestamp(timeStamp.getTime() - 300000);
        String query = "select Meeting_Number from Meetings where Meeting_Date_Time > '"+previous+"' and Meeting_Date_Time < '"+later+"'";
        Connection con= dbConnection.createNewConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                meetNum = rs.getString("Meeting_Number");
                String q = "select Participant_eMail,Meeting_Number from Participants where Participant_eMail = '"+email+"' and Meeting_Number = '"+meetNum+"'";
                ResultSet r = stmt.executeQuery(q);
                while(r.next()){
                    mailid = r.getString("Participant_eMail");
                    if(mailid.equals(email)){
                    return meetNum;
                }
                }
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return "Failure";
    }
    
    public int checkParticipantCount(String meetingNumber){
        int c;
        String query = "select count(*) as count from Participants where Meeting_Number = '"+meetingNumber+"'";
        Connection con= dbConnection.createNewConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                c = Integer.parseInt(rs.getString("count"));
                return c;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    

    public String checkParticipantDesignation(String email){
        String desig;
        String query = "select designation from newuser where email = '"+email+"'";
        Connection con= dbConnection.createNewConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                desig = rs.getString("designation");
                return desig;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return "Failure";
    }

    public String checkParticipants(String uname, String text) {
        String email;
         String query = "select Participant_eMail from Participants where Participant_eMail = '"+uname+"' and Meeting_Number = '"+text+"'";
        Connection con= dbConnection.createNewConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                email = rs.getString("Participant_eMail");
                if(email.equals(uname))
                return "Success";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return "Failure";
    }
    
    
    public String checkPassword(String meetNum, String password){
        String pwd;
        password = pwdEncrypt.MD5(password);
        String query = "select Meeting_Password from Meetings where Meeting_Number = '"+meetNum+"' and Meeting_Password = '"+password+"'";
        Connection con= dbConnection.createNewConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                pwd = rs.getString("Meeting_Password");
                if(pwd.equals(password))
                return "Success";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return "Failure";
    }
    
    public String checkCipher(String uname,String cipher, String meetNum){	
		
		cipher = pwdEncrypt.MD5(cipher);
                String email;
		String query = "SELECT Cipher,Participant_eMail FROM Participants where Participant_eMail='"+uname+"' and Meeting_Number ='"+meetNum+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				email = rs.getString("Participant_eMail");
				cipher = rs.getString("cipher");
				if(uname.equalsIgnoreCase(email)){
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
    
    public String getKey(String uname, String meetNum){
        String email;
        String meet;
        String query = "SELECT * FROM Participants where Participant_eMail='"+uname+"' and Meeting_Number ='"+meetNum+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				key = rs.getString("Pass_Key");
				quoient = rs.getString("Quotient");
                                email = rs.getString("Participant_eMail");
                                meet = rs.getString("Meeting_Number");
				if(uname.equalsIgnoreCase(email) && meetNum.equalsIgnoreCase(meet)){
					return key;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("failure");
		return "Failure";
    }
    
    public String getQuotient(String uname, String meetNum){
        String email;
        String meet;
        String query = "SELECT * FROM Participants where Participant_eMail='"+uname+"' and Meeting_Number ='"+meetNum+"'";
		Connection con= dbConnection.createNewConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				key = rs.getString("Pass_Key");
				quoient = rs.getString("Quotient");
                                email = rs.getString("Participant_eMail");
                                meet = rs.getString("Meeting_Number");
				if(uname.equalsIgnoreCase(email) && meetNum.equalsIgnoreCase(meet)){
					return quoient;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("failure");
		return "Failure";
    }
    
}
