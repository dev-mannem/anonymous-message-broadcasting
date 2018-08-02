/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scsu.anonymous;

import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Swathi
 */
public class Chat extends javax.swing.JFrame {
    
    public ChatModel chatModel = new ChatModel();
    ChatHome chatHome;
    public static ChatClient chatClient;
    DefaultTableModel e = new DefaultTableModel();

    /** Creates new form Chat */
    public Chat() {
        initComponents();  
        this.setTitle("Chat Client");
        CloseBtn.setVisible(false);
        MeetingNumber1.setText(ChatHome.MeetingNum.getText());
    }
    
    private void fillParticipantsTable(){
        
        
        DbConnection dbConnection = new DbConnection();
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        String email;
        try{
            String query = "select Participant_eMail from Participants where Meeting_Number = '"+MeetingNumber1.getText()+"'";
            con = dbConnection.createNewConnection();
            stmt = con.createStatement();
			rs= stmt.executeQuery(query);
			while(rs.next())
			{
                            email = rs.getString("Participant_eMail");
                            e.addRow(new Object[] {false,email});                            
                        }                        
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        finally{
            try{
                rs.close();
                stmt.close();
            }
            catch(Exception exc){
                
            }
        }
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        SubmitBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        EnterMessage = new javax.swing.JTextField();
        SendBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChatArea = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        ParticipantsTable = new javax.swing.JTable();
        DisconnectBtn = new javax.swing.JButton();
        Participants = new javax.swing.JLabel();
        ConnectBtn = new javax.swing.JButton();
        MeetingNum = new javax.swing.JLabel();
        MeetingNumber1 = new javax.swing.JTextField();
        CloseBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter your username");

        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Enter your Message");

        EnterMessage.setEnabled(false);

        SendBtn.setText("Send");
        SendBtn.setEnabled(false);
        SendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendBtnActionPerformed(evt);
            }
        });

        ChatArea.setColumns(20);
        ChatArea.setRows(5);
        ChatArea.setEnabled(false);
        jScrollPane1.setViewportView(ChatArea);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ParticipantsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ParticipantsTable.setEnabled(false);
        jScrollPane2.setViewportView(ParticipantsTable);

        DisconnectBtn.setText("Disconnect");
        DisconnectBtn.setEnabled(false);
        DisconnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisconnectBtnActionPerformed(evt);
            }
        });

        Participants.setText("Participants List");

        ConnectBtn.setText("Connect");
        ConnectBtn.setEnabled(false);
        ConnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectBtnActionPerformed(evt);
            }
        });

        MeetingNum.setText("Meeting #");

        MeetingNumber1.setEditable(false);

        CloseBtn.setText("Close ChatRoom");
        CloseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EnterMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(Username))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1))
                .addGap(52, 52, 52)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ConnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DisconnectBtn))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Participants, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(MeetingNum)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MeetingNumber1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(CloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(Username))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EnterMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MeetingNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MeetingNum))
                        .addGap(18, 18, 18)
                        .addComponent(Participants, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ConnectBtn)
                            .addComponent(DisconnectBtn))
                        .addGap(24, 24, 24)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        // TODO add your handling code here:
        String uname = Username.getText();
        String cmpval = Authentication.UserName.getText();
        System.out.println("uname:"+uname);
        if (uname == null || uname == "" || uname.isEmpty()) {
            JOptionPane.showMessageDialog(jPanel1, "Please enter Username");
            Username.setText("");
        } else if(cmpval.equals(uname)){
            String resp = chatModel.checkParticipants(uname,MeetingNumber1.getText());
            if (resp.equalsIgnoreCase("SUCCESS")) {
                JOptionPane.showMessageDialog(jPanel1, "Success");
                SubmitBtn.setEnabled(false);
                connectClient();
            }
        }              
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void ConnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectBtnActionPerformed
        // TODO add your handling code here:
        
        EnterMessage.setEnabled(true);
            SendBtn.setEnabled(true);
            DisconnectBtn.setEnabled(true); 
    }//GEN-LAST:event_ConnectBtnActionPerformed

    private void SendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendBtnActionPerformed
        // TODO add your handling code here:
        if(!EnterMessage.getText().equals(""))
        {
            chatClient.sendMessage(EnterMessage.getText());
            EnterMessage.requestFocus();
        }
    }//GEN-LAST:event_SendBtnActionPerformed

    private void DisconnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisconnectBtnActionPerformed
        // TODO add your handling code here:
        try
        {
            chatClient.disconnectClient();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_DisconnectBtnActionPerformed

    private void CloseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseBtnActionPerformed
        // TODO add your handling code here:
        String resp = chatModel.checkParticipantDesignation(Username.getText());
        if(resp.equalsIgnoreCase("MANAGER")){
            dispose();
            new ManagerHome().setVisible(true);
        } else if(resp.equalsIgnoreCase("EMPLOYEE")){
            dispose();
            new EmployeeHome().setVisible(true);
        }
    }//GEN-LAST:event_CloseBtnActionPerformed

    
    public static void connectClient(){
        try
        {
            final int PORT = 444;
            final String HOST = "10.100.24.254";
            Socket SOCK = new Socket(HOST, PORT);
            System.out.println("You connected to: "+ HOST);
            
            chatClient = new ChatClient(SOCK);
            
            PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
            OUT.println(Username.getText());
            OUT.flush();
            
            Thread X = new Thread(chatClient);
            X.start();
            
        }
        catch(Exception e)
        {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Server not responding.");
            System.exit(0);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea ChatArea;
    public static javax.swing.JButton CloseBtn;
    public static javax.swing.JButton ConnectBtn;
    public static javax.swing.JButton DisconnectBtn;
    public static javax.swing.JTextField EnterMessage;
    private javax.swing.JLabel MeetingNum;
    public static javax.swing.JTextField MeetingNumber1;
    private javax.swing.JLabel Participants;
    public static javax.swing.JTable ParticipantsTable;
    public static javax.swing.JButton SendBtn;
    private javax.swing.JButton SubmitBtn;
    public static javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}