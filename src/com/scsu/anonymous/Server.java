/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Swathi
 */
public class Server {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> Participants = new ArrayList<String>();
    
    public static void main(String[] args) throws IOException
    {
        
        try
        {
            final int PORT = 444;
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for clients...");
            
            while(true)
            {
                Socket socket = serverSocket.accept();
                ConnectionArray.add(socket);
                
                System.out.println("Client connected from: " + socket.getLocalAddress().getHostName());
                
                addParticipants(socket);
                
                ChatServer chatServer = new ChatServer(socket);
                Thread thread = new Thread(chatServer);
                thread.start();
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public static void addParticipants(Socket X) throws IOException
    {
        Scanner INPUT = new Scanner(X.getInputStream());
        String email = INPUT.nextLine();
        System.out.println("email server add participants:  "+email);
        Participants.add(email);
        
        for(int i = 0; i < ConnectionArray.size(); i++)
        {
            Socket TEMP_SOCK = (Socket) ConnectionArray.get(i);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" + Participants);
            OUT.flush();
            System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getHostName());
        }
    }

}
