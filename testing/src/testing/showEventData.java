/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abosl
 */
public class showEventData extends javax.swing.JFrame {

    /**
     * Creates new form showEventData
     */
    public showEventData() {
        initComponents();
        show_events();
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
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
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
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 450));

        jTable1.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 22 ));
        jTable1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "event Num", "event Name", "event Description", "event Date", "Admin ID"
            }
        ));
        jTable1.setPreferredSize(new java.awt.Dimension(850, 450));
        jScrollPane1.setViewportView(jTable1);

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backBtn)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        AdminFunctions Field =new AdminFunctions();
        Field.setVisible(true);
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
            java.util.logging.Logger.getLogger(showEventData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showEventData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showEventData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showEventData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new showEventData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
