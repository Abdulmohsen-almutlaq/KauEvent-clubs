package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class eventRegisterDelete extends javax.swing.JFrame {

    private String KAU_ID;

    public eventRegisterDelete() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    eventRegisterDelete(String KAU_ID) {
        initComponents();
        this.KAU_ID = KAU_ID;
        userInfoShow(KAU_ID);
        show_events();
        show_myEvents();

    }

    void userInfoShow(String KAU_ID) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            Connection con = DriverManager.getConnection(url);
            String query = "select * from student where KAU_ID=" + KAU_ID;

            PreparedStatement pst = con.prepareStatement(query);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            String stuName = "";
            if (rs.next()) {
                stuName += rs.getString("studentName");
            }
            KAUNameLabel.setText("studentName : " + stuName);
            KAUIDLabel.setText("KAU ID : " + KAU_ID);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public ArrayList<event> eventList() {
        ArrayList<event> eventList = new ArrayList<>();
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            Connection con = DriverManager.getConnection(url);

            String query1 = "SELECT * FROM eventBase";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            event eventO;
            while (rs.next()) {
                eventO = new event(rs.getInt("eventNum"), rs.getString("eventName"), rs.getString("eventDescription"), rs.getDate("eventDate"), rs.getInt("Admin_ID"));
                eventList.add(eventO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return eventList;
    }

    public void show_events() {
        ArrayList<event> list = eventList();
        DefaultTableModel model = (DefaultTableModel) allEventsTable.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEventNum();
            row[1] = list.get(i).getEventName();
            row[2] = list.get(i).getEventDescription();
            row[3] = list.get(i).getEventDate();
            row[4] = list.get(i).getAdmin_ID();

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
        registerEventBtn = new javax.swing.JButton();
        deleteEventBtn = new javax.swing.JButton();
        KAUNameLabel = new javax.swing.JLabel();
        KAUIDLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        allEventsTable.setAutoCreateRowSorter(true);
        allEventsTable.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        allEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Number", "Event Name", "Event Description", "Event Date", "By Admin"
            }
        ));
        allEventsTable.setAlignmentX(0.7F);
        allEventsTable.setAlignmentY(0.7F);
        allEventsTable.setColumnSelectionAllowed(true);
        allEventsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        allEventsTable.setDragEnabled(true);
        allEventsTable.setName(""); // NOI18N
        jScrollPane1.setViewportView(allEventsTable);

        myEventsTable.setAutoCreateRowSorter(true);
        myEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Number", "Event Name", "EventDate"
            }
        ));
        jScrollPane2.setViewportView(myEventsTable);

        registerEventBtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        registerEventBtn.setText("Register  Event");
        registerEventBtn.setActionCommand(" register Event");
        registerEventBtn.setAutoscrolls(true);
        registerEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerEventBtnActionPerformed(evt);
            }
        });

        deleteEventBtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        deleteEventBtn.setText("Delete Event");
        deleteEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventBtnActionPerformed(evt);
            }
        });

        KAUNameLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        KAUIDLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        backBtn.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("registered Events");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(KAUNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(KAUIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addGap(118, 118, 118)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(backBtn)
                        .addGap(497, 497, 497)
                        .addComponent(registerEventBtn)
                        .addGap(694, 694, 694)
                        .addComponent(deleteEventBtn)))
                .addGap(236, 589, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(KAUNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(KAUIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteEventBtn)
                    .addComponent(registerEventBtn)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerEventBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerEventBtnActionPerformed

        try {
            int row = allEventsTable.getSelectedRow();
            String ID = KAU_ID;  // Replace this with the actual KAU_ID

            String value1 = (allEventsTable.getModel().getValueAt(row, 0).toString());
            String value2 = (allEventsTable.getModel().getValueAt(row, 1).toString());

            // Call the registerEventStudent method and check the return value
            if (Student.RegisterEventStudent(ID, value1, value2)) {
                // If successful
                DefaultTableModel model = (DefaultTableModel) allEventsTable.getModel();
                model.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Enrolled Successfully!");
                show_events();
                show_myEvents();
            } else {
                // If unsuccessful
                JOptionPane.showMessageDialog(null, "Enrollment failed. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_registerEventBtnActionPerformed

    public ArrayList<studentEvents> myEventsList() {
        ArrayList<studentEvents> myEventsList = new ArrayList<>();
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            Connection con = DriverManager.getConnection(url);

            String value = this.KAU_ID;
            String query1 = "SELECT * FROM studentEvents where KAU_ID=" + value;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);

            studentEvents eventO;
            while (rs.next()) {
                eventO = new studentEvents(rs.getInt("eventNum"), rs.getString("eventName"), value);
                String query2 = "SELECT eventDate FROM eventBase where eventNum=" + eventO.getEventNum();
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery(query2);
                if (rs2.next()) {
                    eventO.setEventDate(rs2.getDate("eventDate"));
                }

                myEventsList.add(eventO);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return myEventsList;
    }

    public void show_myEvents() {
        ArrayList<studentEvents> list = myEventsList();
        DefaultTableModel model = (DefaultTableModel) myEventsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEventNum();
            row[1] = list.get(i).getEventName();
            row[2] = list.get(i).getEventDate();

            model.addRow(row);

        }
    }


    private void deleteEventBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEventBtnActionPerformed
        try {
            int row = myEventsTable.getSelectedRow();
            String value = (myEventsTable.getModel().getValueAt(row, 0).toString());

            // Call the deleteEvent method and check the return value
            if (Student.deleteEvent(KAU_ID, value)) {
                // If successful
                DefaultTableModel model = (DefaultTableModel) myEventsTable.getModel();
                model.setRowCount(0);
                show_myEvents();
                JOptionPane.showMessageDialog(null, "You deleted the event Successfully!");
            } else {
                // If unsuccessful
                JOptionPane.showMessageDialog(null, "Event deletion failed. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }//GEN-LAST:event_deleteEventBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        StudentDashboard backField = new StudentDashboard(Student.getKAU_ID());
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
            java.util.logging.Logger.getLogger(eventRegisterDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eventRegisterDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eventRegisterDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eventRegisterDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eventRegisterDelete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KAUIDLabel;
    private javax.swing.JLabel KAUNameLabel;
    private javax.swing.JTable allEventsTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteEventBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable myEventsTable;
    private javax.swing.JButton registerEventBtn;
    // End of variables declaration//GEN-END:variables
}
