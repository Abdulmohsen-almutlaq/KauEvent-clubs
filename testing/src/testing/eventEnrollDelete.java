
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




public class eventEnrollDelete extends javax.swing.JFrame {

    private String KAU_ID;


    
    
    public eventEnrollDelete() {
        initComponents();
    }

   eventEnrollDelete(String KAU_ID) {
        initComponents();
        this.KAU_ID=KAU_ID;
        userInfoShow(KAU_ID);
        show_events();
        show_myEvents();
   
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
      KAUNameLabel.setText("studentName : "+stuName);
      KAUIDLabel.setText("KAU ID : "+KAU_ID);
           
           
           
           
           }
           
            catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    
    
    
    }
    
    
    
    
    public ArrayList<event> eventList(){
        ArrayList<event> eventList = new ArrayList<>();
         try{
 
             
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
             
             
             
             
            String query1="SELECT * FROM eventBase";
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query1);
            event eventO;
            while(rs.next()){
                eventO=new event(rs.getInt("eventNum"), rs.getString("eventName"), rs.getString("eventDescription"), rs.getDate("eventDate"),rs.getInt("Admin_ID"));
                eventList.add(eventO);
            }
         }
         catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return eventList;
    }

    
     public void show_events(){
        ArrayList<event> list = eventList();
        DefaultTableModel model = (DefaultTableModel)allEventsTable.getModel();
        Object[] row = new Object[5];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getEventNum();
            row[1]=list.get(i).getEventName();
            row[2]=list.get(i).getEventDescription();
            row[3]=list.get(i).getEventDate();
            row[4]=list.get(i).getAdmin_ID();
     
            model.addRow(row);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        allEventsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        myEventsTable = new javax.swing.JTable();
        enrollEventBtn = new javax.swing.JButton();
        deleteEventBtn = new javax.swing.JButton();
        KAUNameLabel = new javax.swing.JLabel();
        KAUIDLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Number", "Event Name", "Event Description", "Event Date", "By Admin"
            }
        ));
        jScrollPane1.setViewportView(allEventsTable);

        myEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Number", "Event Name", "EventDate"
            }
        ));
        jScrollPane2.setViewportView(myEventsTable);

        enrollEventBtn.setText("Enroll Event");
        enrollEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollEventBtnActionPerformed(evt);
            }
        });

        deleteEventBtn.setText("Delete Event");
        deleteEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventBtnActionPerformed(evt);
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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(KAUNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KAUIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enrollEventBtn)
                .addGap(329, 329, 329)
                .addComponent(deleteEventBtn)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(KAUNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(KAUIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteEventBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enrollEventBtn)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enrollEventBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollEventBtnActionPerformed




         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);

       int row= allEventsTable.getSelectedRow();

            String value=KAU_ID;
             String value1=(allEventsTable.getModel().getValueAt(row, 0).toString());
            String value2=(allEventsTable.getModel().getValueAt(row, 1).toString());

java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());



            String query = " insert into studentEvents(KAU_ID,eventNum,eventName)values(?,?,?)";


          PreparedStatement pst = con.prepareStatement(query);
          pst.setString(1, value);
          pst.setString(2, value1);
          pst.setString(3, value2);
          pst.executeUpdate();
          DefaultTableModel model = (DefaultTableModel)allEventsTable.getModel();
            model.setRowCount(0);
             JOptionPane.showMessageDialog(null, "Enrolled Sucessfully!");
             show_events();
             show_myEvents();
//             show_clubs();
//             show_myClubs();


          }


          catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }









    }//GEN-LAST:event_enrollEventBtnActionPerformed

    
    
    
 public ArrayList<studentEvents> myEventsList(){
        ArrayList<studentEvents> myEventsList = new ArrayList<>();
         try{
 
             
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
             
             
             
             String value=this.KAU_ID;
            String query1="SELECT * FROM studentEvents where KAU_ID="+value;
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(query1);
            
            
            
        
            
     
            studentEvents eventO;
            while(rs.next()){
                eventO=new studentEvents(rs.getInt("eventNum"),rs.getString("eventName"), value);
                     String query2="SELECT eventDate FROM eventBase where eventNum="+eventO.getEventNum();
            Statement st2= con.createStatement();
            ResultSet rs2= st2.executeQuery(query2);
                if(rs2.next()){
                eventO.setEventDate(rs2.getDate("eventDate"));
                }
       
            
            myEventsList.add(eventO);
                
            }
            
             
          

            
            
            
         }
         catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return myEventsList;
    }
    
    
    
 
 
 
 public void show_myEvents(){
        ArrayList<studentEvents> list = myEventsList();
        DefaultTableModel model = (DefaultTableModel)myEventsTable.getModel();
         model.setRowCount(0);
        Object[] row = new Object[3];
     
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getEventNum();
            row[1]=list.get(i).getEventName();
            row[2]=list.get(i).getEventDate();
            
   
     
            model.addRow(row);
            
        }}
 
 
 
 
    
    
    
    private void deleteEventBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEventBtnActionPerformed
                                       
      
        
        
        
        
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           String url="jdbc:sqlserver://sleman20.database.windows.net:1433;database=KAU_EVENT_AZURE;user=CloudSA8549a509@sleman20;password={Break2020};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
            int row= myEventsTable.getSelectedRow();
            String value=(myEventsTable.getModel().getValueAt(row, 0).toString());
            String query="DELETE FROM studentEvents where KAU_ID="+KAU_ID+"and eventNum="+value;
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)myEventsTable.getModel();
            model.setRowCount(0);
            show_myEvents();
            JOptionPane.showMessageDialog(null,"You deleted the event Successfully!");
          }
          catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
          }
                
        
        
        
        
        
        
        
    }//GEN-LAST:event_deleteEventBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        userFunctions backField = new userFunctions(KAU_ID);
        backField.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

  
    public static void main(String args[]) {

        
        
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
            java.util.logging.Logger.getLogger(eventEnrollDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eventEnrollDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eventEnrollDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eventEnrollDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eventEnrollDelete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KAUIDLabel;
    private javax.swing.JLabel KAUNameLabel;
    private javax.swing.JTable allEventsTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteEventBtn;
    private javax.swing.JButton enrollEventBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable myEventsTable;
    // End of variables declaration//GEN-END:variables
}
