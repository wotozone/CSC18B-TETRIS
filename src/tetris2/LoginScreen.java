/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import Database.DataLoader;
import Database.DatabaseManager;
import Server.ClientConnector;
import Server.ServerScreen;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import tetris2.FileChooser;
import tetris2.LoginScreen_Local;
import tetris2.ObjectStream;
import tetris2.RegularExpression;
import tetris2.RoomScreen1;
import tetris2.threads.DropDownThread;

/**
 *
 * @author minjikim
 */
public class LoginScreen extends javax.swing.JFrame {

    /**
     * Creates new form LoginScreen
     */
    public LoginScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 60)); // NOI18N
        jLabel1.setText("TETRIS");
        jLabel1.setToolTipText("");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Type your Account");

        jButton1.setText("Log in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("To Log in or Create a new account");

        jButton2.setText("Open Server Manager");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText(" ");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jButton3.setText("FOR TEST");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Set your nickname");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPasswordField1)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))))
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        System.out.println(evt.getActionCommand());
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(offline){
            LoginScreen_Local localLogin = new LoginScreen_Local();
            this.dispose();
        }else{
            switch(logInState){
                case 0://first log in
                    String name =jTextField1.getText();
                    if(checkID(name)==true){
                        savedUserName=name;
                        if(DatabaseManager.dbm.checkAccountInfo(name,"username","account")){
                            jTextField1.setEditable(false);
                            jLabel4.setText("Type your password");
                            jPasswordField1.setVisible(true);
                            jLabel3.setText("Password must include at least one numerical, special, and capital letter");
                            jButton1.setText("Create Account");
                            jTextField2.setVisible(true);
                            jLabel6.setVisible(true);
                            logInState=1;
                        }else{
                            jTextField1.setEditable(false);
                            jLabel3.setText("Type your password");
                            jPasswordField1.setVisible(true);
                            //jLabel5.setText("Password must include at least one numerical, special, and capital letter");
                            logInState=2;
                        }

                        //ObjectStream.saveID(s);
                        //ObjectStream.closeSaveStream();
                        //startGame();
                    }
                    break;
                case 1://to Create New Account
                    savedPassword =jPasswordField1.getText();
                    savedNickname =jTextField2.getText();
                    if(checkPassword(savedPassword)==true){
                        if(savedNickname==""){
                            jLabel5.setText("Set your nickname Correctly");
                        }else if(RegularExpression.checkNickname(savedNickname)==false){
                            jLabel5.setText("Incorrect nickname");
                        }else if(DatabaseManager.dbm.checkAccountInfo(savedNickname,"nickname","account")){
                            DatabaseManager.dbm.tempUsername=savedUserName;
                            DatabaseManager.dbm.tempPassword=savedPassword;
                            DatabaseManager.dbm.tempNickname=savedNickname;
                            DatabaseManager.dbm.saveNewAccountID(savedUserName, savedPassword, savedNickname);
                            startGame();
                        }else{
                            jLabel5.setText("the nickname is already exist");
                        }
                    }else{
                        jLabel5.setText("Your password is too weak");
                    }
                    break;
                case 2://to Log in
                    savedPassword =jPasswordField1.getText();
                    if(!DatabaseManager.dbm.checkAccountInfo(savedPassword, "password", "account")){
                        DatabaseManager.dbm.tempUsername=savedUserName;
                        DatabaseManager.dbm.tempPassword=savedPassword;
                        DatabaseManager.dbm.getInternalID();
                        startGame();
                    }else{
                        jLabel5.setText("Password is incorrect");
                    }
                    break;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        ServerScreen.ss.initializer();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
        System.out.println(evt.getActionCommand());
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        savedUserName="TESTER";
        savedPassword="Testing#33";
        jTextField1.setText(savedUserName);
        jTextField1.setEditable(false);
        jLabel3.setText("Type your password");
        jPasswordField1.setText(savedPassword);
        jPasswordField1.setVisible(true);
        //jLabel5.setText("Password must include at least one numerical, special, and capital letter");
        logInState=2;
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        System.out.println(evt.getActionCommand());
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    //Custom var
    public static boolean offline=false;
    private static int logInState=0;
    private static String savedUserName;
    private static String savedPassword;
    private static String savedNickname;
    private static int savedScore;
    private static Path fileLocation;
    //Custom method
    
    private void loadInfoFromFile(){
        ObjectStream.fileLoadStream(fileLocation);
        ObjectStream.loadID();
        savedUserName=ObjectStream.us.getUserName();
        savedPassword=ObjectStream.us.getPassword();
        ObjectStream.closeLoadStream();
        jTextField1.setText(savedUserName);
        jTextField1.setEditable(false);
        jLabel3.setText("Type your password");
        jPasswordField1.setVisible(true);
        //jLabel5.setText("Password must include at least one numerical, special, and capital letter");
        logInState=2;
    }
    
    private void selectSaveFile() throws IOException{
        FileChooser application = new FileChooser();
        //application.setSize(700,400);
        //application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //application.setVisible(false);
        fileLocation = application.savedFile;
        String s = String.format("%s",application.savedFile);
        //System.out.println(s);
    }
    
    private boolean matchPassword(String password){
        if(password.equals(savedPassword))return true;
        return false;
    }
    
    private boolean checkID(String name){
        if(RegularExpression.checkID(name)==false){
            jLabel4.setText("Your Account might have incorrect letter or length");
            return false;
        }
        return true;
    }
    
    private boolean checkPassword(String password){
        if(RegularExpression.checkPassword(password)==false){
            return false;
        }
        return true;
    }
    
    private void startGame(){
        this.dispose();
        //this.setVisible(false);
        //new StartScreen().setVisible(true);
        //new MainCanvas();
        
        RoomScreen1.rs.initializer();
        
        /*
        new Initializer();
        Thread t = new Thread(new Testing());
        t.start();
        */

        //Thread d = new Thread(new DropDownThread());
        //d.start();
    }
    
    private void serverUnavalable(){
    }
    
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
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginScreen ls= new LoginScreen();
                ls.setVisible(true);
                ls.jPasswordField1.setVisible(false);
                ls.jTextField2.setVisible(false);
                ls.jLabel6.setVisible(false);
                ls.jButton2.setVisible(false);
                ls.setLocationRelativeTo(null);
                //ls.jButton3.setVisible(false);//FOR TEST 
                if(!ClientConnector.cc.checkConnection()||!DatabaseManager.dbm.checkConnection()){  
                    //ls.jButton1.setVisible(false);
                    ls.jButton1.setText("PLAY OFFLINE");
                    offline=true;
                    ls.jButton2.setVisible(false);
                    ls.jButton3.setVisible(false);
                    ls.jLabel1.setVisible(false);
                    ls.jLabel2.setVisible(false);
                    ls.jLabel3.setVisible(false);
                    ls.jLabel4.setVisible(false);
                    ls.jLabel5.setVisible(false);
                    ls.jLabel6.setVisible(true);
                    ls.jPasswordField1.setVisible(false);
                    ls.jTextField1.setVisible(false);
                    ls.jTextField2.setVisible(false);
                    ls.jLabel6.setFont(new java.awt.Font("Arial", 1, 30));
                    ls.jLabel6.setText("Server is currently unavailable");
                    if(DatabaseManager.dbm.checkConnection())ls.jButton2.setVisible(true);
                }else{
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
