/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Swathi
 */
public class ChatServer implements Runnable{
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE = "";
    
    public ChatServer(Socket X)
    {
        this.SOCK=X;
    }
    
    public void CheckConnection() throws IOException
    {
        if(!SOCK.isConnected())
        {
            for(int i = 1; i <= Server.ConnectionArray.size(); i++)
            {
                if(Server.ConnectionArray.get(i)==SOCK)
                {
                    Server.ConnectionArray.remove(i);
                }
            }
            for(int i = 1; i <= Server.ConnectionArray.size(); i++)
            {
                Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
                PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName()+" disconnected!");
            }
            
        }
    }
    
    public void run()
    {
        try
        {
            try
            {
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                
                while(true)
                {
                    CheckConnection();
                    
                    if(!INPUT.hasNext())
                    {
                        return;
                    }
                    
                    MESSAGE = INPUT.nextLine();
                    
                    System.out.println("Client said: " + MESSAGE);
                    
                    for(int i = 0; i < Server.ConnectionArray.size(); i++)
                    {
                        Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i);
                        PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                        TEMP_OUT.println(MESSAGE);
                        TEMP_OUT.flush();
                        System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getHostName());
                    }
                }
            }
            finally
            {
                SOCK.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
