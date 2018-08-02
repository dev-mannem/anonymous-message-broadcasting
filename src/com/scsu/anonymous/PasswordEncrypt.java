/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Swathi
 */
public class PasswordEncrypt {
    
    public String MD5(String password) {
		// TODO Auto-generated method stub
		
		
		
			         
	        String md5 = null;
	         
	        if(null == password) return null;
	         
	        try {
	             
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	         
	        //Update input string in message digest
	        digest.update(password.getBytes(), 0, password.length());
	 
	        //Converts message digest value in base 16 (hex) 
	        md5 = new BigInteger(1, digest.digest()).toString(16);
	 
	        } catch (NoSuchAlgorithmException e) {
	 
	            e.printStackTrace();
	        }
	       
		return md5;
	}

}
