/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abosl
 */
public class clubJoinExit extends javax.swing.JFrame {

    private String KAU_ID;

    /**
     * Creates new form clubJoinExit
     */
    public clubJoinExit() {
        initComponents();
        show_clubs();
        
    }
    public clubJoinExit(String KAU_ID) {
        initComponents();
        this.KAU_ID=KAU_ID;
        userInfoShow(KAU_ID);
        show_clubs();
        show_myClubs();
    }
    
    
    
    
    void userInfoShow(String KAU_ID){
      
           try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
            String query="select * from student where KAU_ID="+KAU_ID;
         
           PreparedStatement pst = con.prepareStatement(query);
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query);
            String stuName="";
           if(rs.next()){
           stuName+=rs.getString("studentName");
           }
      stuNameLabel.setText("studentName : "+stuName);
      KAU_IDLabel.setText("KAU ID : "+KAU_ID);
           
           
           
           
           }
           
           
           
           catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    
    
     public ArrayList<club> clubList(){
        ArrayList<club> clubList = new ArrayList<>();
         try{
 
             
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
             
             
             
             
            String query1="SELECT * FROM clubBase";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query1);
            club clubO;
            while(rs.next()){
                clubO=new club(rs.getInt("clubCode"), rs.getString("clubName"), rs.getString("clubDescription"), rs.getDate("clubFounded"));
                clubList.add(clubO);
            }
         }
         catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return clubList;
    }

    
     public void show_clubs(){
        ArrayList<club> list = clubList();
        DefaultTableModel model = (DefaultTableModel)allAvailableClubs.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getClubCode();
            row[1]=list.get(i).getClubName();
            row[2]=list.get(i).getClubDescription();
            row[3]=list.get(i).getClubFounded();
     
            model.addRow(row);
        }
    }
    
    
    
    
    
    public ArrayList<studentClubs> myClubList(){
        ArrayList<studentClubs> myClubList = new ArrayList<>();
         try{
 
             
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
             
             
             
             String value=this.KAU_ID;
            String query1="SELECT * FROM studentClubs where KAU_ID="+value;
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query1);
            
            
            
        
            
     
            studentClubs clubO;
            while(rs.next()){
                clubO=new studentClubs(rs.getInt("clubCode"),rs.getInt("KAU_ID"), rs.getDate("JoinDate"));
                     String query2="SELECT clubName FROM clubBase where clubCode="+clubO.getClubCode();
            Statement st2= con.createStatement();
            ResultSet rs2= st2.executeQuery(query2);
                if(rs2.next()){
                clubO.setClubName(rs2.getString("clubName"));
                }
       
            
            myClubList.add(clubO);
                
            }
            
             
          

            
            
            
         }
         catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return myClubList;
    }

    
     public void show_myClubs(){
        ArrayList<studentClubs> list = myClubList();
        DefaultTableModel model = (DefaultTableModel)showMyClubs.getModel();
         model.setRowCount(0);
        Object[] row = new Object[3];
     
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getClubCode();
            row[1]=list.get(i).getClubName();
            row[2]=list.get(i).getJoinDate();
            
   
     
            model.addRow(row);
            
        }
          
     
    
    
    
    
    
    
    
    
    
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        allAvailableClubs = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        showMyClubs = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        joinClubButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        leaveBtn = new javax.swing.JButton();
        stuNameLabel = new javax.swing.JLabel();
        KAU_IDLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 500));

        allAvailableClubs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        allAvailableClubs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Club code", "Club name", "Club description", "Club founded"
            }
        ));
        jScrollPane1.setViewportView(allAvailableClubs);

        showMyClubs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        showMyClubs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Club Code", "Club Name", "JoinDate"
            }
        ));
        jScrollPane2.setViewportView(showMyClubs);

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 18)); // NOI18N
        jLabel1.setText("My Clubs");

        joinClubButton.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        joinClubButton.setText("Join Club");
        joinClubButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinClubButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 18)); // NOI18N
        jLabel2.setText("All Clubs");

        leaveBtn.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
        leaveBtn.setText("Leave ");
        leaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveBtnActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(stuNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(KAU_IDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(83, 83, 83)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(backBtn)
                                .addGap(146, 146, 146)
                                .addComponent(joinClubButton)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(leaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(132, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(266, 266, 266))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(KAU_IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(joinClubButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(leaveBtn)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void joinClubButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinClubButtonActionPerformed
       
        
        
          try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
       
       int row= allAvailableClubs.getSelectedRow();
       
            String value=KAU_ID;
             String value1=(allAvailableClubs.getModel().getValueAt(row, 0).toString());
            
//             long millis=System.currentTimeMillis();  
//      
//  
//    java.sql.Date date = new java.sql.Date(millis);       
java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


  
            String query = " insert into studentClubs(KAU_ID,clubCode,JoinDate)values(?,?,?)";
            
         
          PreparedStatement pst = con.prepareStatement(query);
          pst.setString(1, value);
          pst.setString(2, value1);
          pst.setDate(3, date);
          pst.executeUpdate();
          DefaultTableModel model = (DefaultTableModel)allAvailableClubs.getModel();
            model.setRowCount(0);
             JOptionPane.showMessageDialog(null, "Joined Sucessfully!");
             show_clubs();
             show_myClubs();
           
          
          }
          
          
          catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_joinClubButtonActionPerformed

    private void leaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveBtnActionPerformed
      
        
        
        
        
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
            int row= showMyClubs.getSelectedRow();
            String value=(showMyClubs.getModel().getValueAt(row, 0).toString());
            String query="DELETE FROM studentClubs where KAU_ID="+KAU_ID+"and clubCode="+value;
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)showMyClubs.getModel();
            model.setRowCount(0);
            show_myClubs();
            JOptionPane.showMessageDialog(null,"You leaved the club Successfully!");
          }
          catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        
        
    }//GEN-LAST:event_leaveBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        userFunctions backField = new userFunctions(KAU_ID);
        backField.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

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
            java.util.logging.Logger.getLogger(clubJoinExit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clubJoinExit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clubJoinExit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clubJoinExit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clubJoinExit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KAU_IDLabel;
    private javax.swing.JTable allAvailableClubs;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton joinClubButton;
    private javax.swing.JButton leaveBtn;
    private javax.swing.JTable showMyClubs;
    private javax.swing.JLabel stuNameLabel;
    // End of variables declaration//GEN-END:variables
}
