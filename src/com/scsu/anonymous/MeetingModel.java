/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swathi
 */
public class MeetingModel {
    DbConnection dbConnection = new DbConnection();
    PasswordEncrypt pwdEncrypt = new PasswordEncrypt();
    
    
    public String createMeeting(String meetingNum, String password, Timestamp date){
        
        password = pwdEncrypt.MD5(password);
        String query = "INSERT INTO Meetings (Meeting_Number,Meeting_Password,Meeting_Date_Time) VALUES (?,?,?)";
        Connection con= dbConnection.createNewConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, meetingNum);
            pstmt.setString(2, password);
            pstmt.setTimestamp(3, date);
            int result = pstmt.executeUpdate();
            if(result == 1){
                return "Success";
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("failure");
        return "Failure";
        
    }
    
    public String cancelMeeting(String MeetingNum){
        
        String query = "delete from Meetings where Meeting_Number = '"+MeetingNum+"'";
        Connection con= dbConnection.createNewConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(query);            
            int result = pstmt.executeUpdate();
            if(result == 1){
                return "Success";
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("failure");
        return "Failure";
    }
    
    public String addParticipants(String MeetingNum, List list){  
                
                PreparedStatement pstmt;
                String email;
                int result =0;
        String query = "insert into Participants (Participant_eMail,Meeting_Number) values (?,?)";
        Connection con= dbConnection.createNewConnection();
        for(int i=0; i < list.size();i++){
            email = list.get(i).toString();            
		try {			
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1, email);
                        pstmt.setString(2, MeetingNum);
                        result = pstmt.executeUpdate();
                        if(result != 1){
                            return "Failure";
                        }
                }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
        }
        if(result == 1){
            return "Success";
        }
        
		System.out.println("failure");
		return "Failure";
    }
    
    public String retrieveParticipants(String MeetingNum){
        PreparedStatement pstmt;
        String participant;
        String resp;
        MeetingCancellationMail meetingCancellationMail = new MeetingCancellationMail();
        String query = "select Participant_eMail from Participants where Meeting_Number = ?";
        Connection con= dbConnection.createNewConnection();
        try {
                 pstmt = con.prepareStatement(query);
                 pstmt.setString(1, MeetingNum);
                 ResultSet rs = pstmt.executeQuery();
                 while(rs.next()){                     
                     participant = rs.getString("Participant_eMail");
                     resp = meetingCancellationMail.meetingCancel(MeetingNum,participant);
                     if(resp.equalsIgnoreCase("FAILURE")){
                         return "Failure";
                     }
                 }
                 return "Success";
             } catch (SQLException ex) {
                 Logger.getLogger(MeetingCreated.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        return "Failure";
    }
       
    public String deleteRows(String MeetingNum){
        
        String query = "delete from Participants where Meeting_Number = '"+MeetingNum+"'";
        Connection con= dbConnection.createNewConnection();
        try {
			Statement stmt = con.createStatement();
			int r = stmt.executeUpdate(query);
			if(r == 1){
                            return "Success";
                        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
                return "Failure";
    }
        
        
    public int userId(String email){
        PreparedStatement pstmt;                
//                String userid;
                String mailid;
                int id;
        String query = "select NewUser_id, Email from NewUser where Email = ?";
        Connection con= dbConnection.createNewConnection();
        try {
                 pstmt = con.prepareStatement(query);
                 pstmt.setString(1, email);
                 ResultSet rs = pstmt.executeQuery();
                 while(rs.next()){
                     id = rs.getInt("NewUser_id");
                     mailid = rs.getString("Email");
                     if(mailid.equals(email)){
//                         id = Integer.parseInt(userid);
                         return id;
                     }
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(MeetingCreated.class.getName()).log(Level.SEVERE, null, ex);
             }
        return 0;
    }
    
    
    public String timeStamp(String meetingNumber){
        PreparedStatement pstmt;
        String meetNum;
        String time;
        
        String query = "select Meeting_Date_Time,Meeting_Number from Meetings where Meeting_Number = ?";
        Connection con= dbConnection.createNewConnection();
        try {
                 pstmt = con.prepareStatement(query);
                 pstmt.setString(1, meetingNumber);
                 ResultSet rs = pstmt.executeQuery();
                 while(rs.next()){
                     time =String.valueOf(rs.getTimestamp("Meeting_Date_Time"));
                     meetNum = rs.getString("Meeting_Number");
                     if(meetNum.equals(meetingNumber)){
                         System.out.println("time:  "+time);
                         return time;
                     }
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(MeetingCreated.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        return "Failure";
    }
    
    public String storeInfo(String key, String quotient, String cipher, String email, String meetNum){
        PreparedStatement pstmt;
                int result =0;
                cipher = pwdEncrypt.MD5(cipher);
        String query = "update Participants set Pass_Key=?, Quotient=?, Cipher=? where Participant_eMail =? and Meeting_Number =?";
        Connection con= dbConnection.createNewConnection();          
		try {			
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1, key);
                        pstmt.setString(2, quotient);
                        pstmt.setString(3, cipher);
                        pstmt.setString(4, email);
                        pstmt.setString(5, meetNum);
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
    
}
