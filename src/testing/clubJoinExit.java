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
import javax.swing.JFrame;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public clubJoinExit(String KAU_ID) {
        initComponents();
        this.KAU_ID = KAU_ID;
        userInfoShow(KAU_ID);
        show_clubs();
        show_myClubs();
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
            stuNameLabel.setText("studentName : " + stuName);
            KAU_IDLabel.setText("KAU ID : " + KAU_ID);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<club> clubList() {
        ArrayList<club> clubList = new ArrayList<>();
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            Connection con = DriverManager.getConnection(url);

            String query1 = "SELECT * FROM clubBase";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            club clubO;
            while (rs.next()) {
                clubO = new club(rs.getInt("clubCode"), rs.getString("clubName"), rs.getString("clubDescription"), rs.getDate("clubFounded"));
                clubList.add(clubO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return clubList;
    }

    public void show_clubs() {
        ArrayList<club> list = clubList();
        DefaultTableModel model = (DefaultTableModel) allAvailableClubs.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getClubCode();
            row[1] = list.get(i).getClubName();
            row[2] = list.get(i).getClubDescription();
            row[3] = list.get(i).getClubFounded();

            model.addRow(row);
        }
    }

    public ArrayList<studentClubs> myClubList() {
        ArrayList<studentClubs> myClubList = new ArrayList<>();
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KAU_Events_Clubs;user=sa;password=12345";
            Connection con = DriverManager.getConnection(url);

            String value = this.KAU_ID;
            String query1 = "SELECT * FROM studentClubs where KAU_ID=" + value;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);

            studentClubs clubO;
            while (rs.next()) {
                clubO = new studentClubs(rs.getInt("clubCode"), rs.getInt("KAU_ID"), rs.getDate("JoinDate"));
                String query2 = "SELECT clubName FROM clubBase where clubCode=" + clubO.getClubCode();
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery(query2);
                if (rs2.next()) {
                    clubO.setClubName(rs2.getString("clubName"));
                }

                myClubList.add(clubO);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return myClubList;
    }

    public void show_myClubs() {
        ArrayList<studentClubs> list = myClubList();
        DefaultTableModel model = (DefaultTableModel) showMyClubs.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getClubCode();
            row[1] = list.get(i).getClubName();
            row[2] = list.get(i).getJoinDate();

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

        joinClubButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        joinClubButton.setText("Enroll Club");
        joinClubButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinClubButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 18)); // NOI18N
        jLabel2.setText("All Clubs");

        leaveBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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
                .addContainerGap(204, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stuNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(KAU_IDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(178, 178, 178)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(393, 393, 393)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200))))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(backBtn)
                .addGap(530, 530, 530)
                .addComponent(joinClubButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(KAU_IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(405, 405, 405)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joinClubButton)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveBtn))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void joinClubButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinClubButtonActionPerformed
        try {
            int row = allAvailableClubs.getSelectedRow();
            String ID = KAU_ID;  // Replace this with the actual KAU_ID
            String clubCode = (allAvailableClubs.getModel().getValueAt(row, 0).toString());

            // Call the joinClub method and check the return value
            if (Student.joinClub(ID, clubCode)) {
                // If successful
                DefaultTableModel model = (DefaultTableModel) allAvailableClubs.getModel();
                model.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Joined Successfully!");
                show_clubs();
                show_myClubs();
            } else {
                // If unsuccessful
                JOptionPane.showMessageDialog(null, "Club join failed. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_joinClubButtonActionPerformed

    private void leaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveBtnActionPerformed
        try {
            int row = showMyClubs.getSelectedRow();
            String clubCode = (showMyClubs.getModel().getValueAt(row, 0).toString());

            // Call the leaveClub method and check the return value
            if (Student.leaveClub(KAU_ID, clubCode)) {
                // If successful
                DefaultTableModel model = (DefaultTableModel) showMyClubs.getModel();
                model.setRowCount(0);
                show_myClubs();
                JOptionPane.showMessageDialog(null, "You left the club Successfully!");
            } else {
                // If unsuccessful
                JOptionPane.showMessageDialog(null, "Club leave failed. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_leaveBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed

        StudentDashboard backField = new StudentDashboard(KAU_ID);
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
