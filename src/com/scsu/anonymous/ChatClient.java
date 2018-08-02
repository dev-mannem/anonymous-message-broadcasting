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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Swathi
 */
public class ChatClient implements Runnable {

    Socket socket;
    Scanner INPUT;
    Scanner SEND = new Scanner(System.in);
    PrintWriter OUT;
    DefaultTableModel e = new DefaultTableModel();
    public String participant;
    ChatModel chatModel = new ChatModel();
    String meetNum;

    ChatClient(Socket socket) {
        this.socket = socket;
        e = (DefaultTableModel) Chat.ParticipantsTable.getModel();
        meetNum = Chat.MeetingNumber1.getText();
    }

    @Override
    public void run() {
        try {
            try {
                INPUT = new Scanner(socket.getInputStream());
                OUT = new PrintWriter(socket.getOutputStream());
                OUT.flush();
                checkForMessage();
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void checkForMessage() {
        while (true) {
            receiveMessage();
        }
    }

    void sendMessage(String text) {
        OUT.println(text);
        OUT.flush();
        Chat.EnterMessage.setText("");
    }

    void disconnectClient() throws IOException {
        OUT.println("SOMEONEHASDISCONNECTED");
        OUT.flush();
        socket.close();
        JOptionPane.showMessageDialog(Chat.jPanel1, "SessionClosed");  
        Chat.SendBtn.setEnabled(false);
        Chat.ConnectBtn.setEnabled(false);
        Chat.DisconnectBtn.setEnabled(false);
        Chat.CloseBtn.setVisible(true);
    }

    private void receiveMessage() {
        if (INPUT.hasNext()) {
            String MESSAGE = INPUT.nextLine();

            if (MESSAGE.contains("#?!")) {
                String TEMP1 = MESSAGE.substring(3);
                TEMP1 = TEMP1.replace("[", "");
                TEMP1 = TEMP1.replace("]", "");

                String[] participants = TEMP1.split(", ");
                for (int j = 0; j < participants.length; j++) {
                    System.out.println("receive msg:  " + participants[j]);
                }
                e.setRowCount(0);
                for (int j = 0; j < participants.length; j++) {
                    System.out.println("participants length: " + participants.length);
                    participant = participants[j];
                    System.out.println("participants length: " + participant);                    
                    e.addRow(new Object[]{participant});
                    e.fireTableDataChanged();

                }
                int c =0;
                for(int i = 0 ; i < Chat.ParticipantsTable.getRowCount() ; i++){
            if(Chat.Username.getText().equals(Chat.ParticipantsTable.getModel().getValueAt(i,0).toString())){
                c++;                
            }
            }
                if(c > 1){
                    JOptionPane.showMessageDialog(null, "User ACTIVE in another session");
                System.exit(0);
                }
                int resp = chatModel.checkParticipantCount(meetNum);
                System.out.println("no .of rows returned  " + resp);
                int rows = Chat.ParticipantsTable.getRowCount();
                if (resp == rows) {
                    Chat.ConnectBtn.setEnabled(true);
                }
            } else if(MESSAGE.equalsIgnoreCase("SOMEONEHASDISCONNECTED")){
                try{
                   disconnectClient(); 
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }                
            } else {
                Chat.ChatArea.append(MESSAGE + "\n");
            }
        }
    }

}
