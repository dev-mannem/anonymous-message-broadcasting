/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

/**
 *
 * @author Swathi
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEncryptedPassword
{
   public String sendEncryptedPassword(String meetNum, String msg, String dest)
   {    
      // Recipient's email ID needs to be mentioned.
      String to = dest;

      // Sender's email ID needs to be mentioned
      String from = "smannem@stcloudstate.edu";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.auth", "true");
       properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "outlook.office365.com");
       properties.setProperty("mail.smtp.port", "587");      


      // Get the default Session object.
      //Session session = Session.getDefaultInstance(properties);
      Session session = Session.getDefaultInstance(properties,
    new Authenticator() {
        protected PasswordAuthentication  getPasswordAuthentication() {
        return new PasswordAuthentication(
                    "smannem@stcloudstate.edu", "Radharao@19");
                }
    });
      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Anonymous Message Broadcast password Reset");

         // Now set the actual message
         message.setText("Ciphet Text for Meeting# "+meetNum+" is: \n\t\tEncrypted Password: "+msg);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
         return "Success";
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
      return "Failure";
   }
}
