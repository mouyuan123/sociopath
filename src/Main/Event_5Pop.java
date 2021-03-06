/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author houren
 */
public class Event_5Pop extends javax.swing.JFrame {

    private String answerStudentNum;
    private Student user, crush;
    ArrayList<Student> choiceList = new ArrayList<Student>();

    /**
     * Creates new form Event_5Pop
     */
    public Event_5Pop(Student user) throws InterruptedException {
        this.user = user;
        initComponents();
        jTextField1.setEditable(false);
        jTextArea1.setText("Welcome to Event 5...\n");
        jTextArea1.append("Please key in the number of your crush...\n");

        int k = 0;
        for (int i = 0; i < StudentList.studentList.size(); i++) {
            StudentList.studentList.get(i).setRumor(false);
            StudentList.studentList.get(i).searched = false;
            StudentList.studentList.get(i).convinced = false;
            StudentList.studentList.get(i).user = false;
        }
        StudentList.rumorList.clear();
        for (int i = 0; i < StudentList.studentList.size(); i++) {
               if(StudentList.studentList.get(i).compareTo(user)!=0)
                {jTextArea1.append(k + " " + StudentList.studentList.get(i).getName() + "\n");
                choiceList.add(StudentList.studentList.get(i));
                k++;}
            
        }
        jTextArea1.setEditable(false);
        jTextField1.setEditable(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(425, 260));
        setSize(new java.awt.Dimension(425, 260));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMaximumSize(new java.awt.Dimension(425, 260));
        jPanel1.setMinimumSize(new java.awt.Dimension(425, 260));
        jPanel1.setPreferredSize(new java.awt.Dimension(425, 283));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setOpaque(true);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 205, 280, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setFocusable(false);
        jTextArea1.setOpaque(false);
        jTextArea1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 280, 120));

        jButton1.setText("Ok");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/Event 5 pop resized.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jButton1.getText().equals("Ok") && jButton1.isEnabled()) {
            answerStudentNum = jTextField1.getText();
            if (!isNumber(answerStudentNum)) {
                jTextArea1.append("Invalid input!!\n");
                jTextArea1.append("Please Try Again!!\n");
                jTextField1.setText("");
            } else if (Integer.parseInt(answerStudentNum) >= choiceList.size()) {
                jTextArea1.append("Invalid input!!\n");
                jTextArea1.append("Please Try Again!!\n");
                jTextField1.setText("");
            } else {
                jTextField1.setText("");
                jTextArea1.append("Starting the event...\n");
                jTextArea1.setText("");
                crush = choiceList.get(Integer.parseInt(answerStudentNum));
                callEvent5(user,crush);
                jTextField1.setEnabled(false);
            }
        } else if ((jButton1.getText().equals("Yay!!!")||jButton1.getText().equals("Nooo!!!")) && jButton1.isEnabled()) {
            dispose();
        } 
        
        


    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean isNumber(String str) {
        boolean isNumbers = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(answerStudentNum.charAt(i))) {
                isNumbers = true;
            } else {
                isNumbers = false;
            }
        }
        return isNumbers;
    }

    public void callEvent5(Student user, Student crush) {
        Event_5 event5 = new Event_5(user, crush);
        ArrayList<String> print = event5.getPrint();
        for (int i = 0; i < print.size(); i++) {
            jTextArea1.append(print.get(i));
        }
        if(event5.spreadToCrush())
        jButton1.setText("Nooo!!!");
        else
        jButton1.setText("Yay!!!");    
    }


    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Event_5Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Event_5Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Event_5Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Event_5Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new Event_5Pop(StudentList.studentList.get(0)).setVisible(true);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Event_5Pop.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
