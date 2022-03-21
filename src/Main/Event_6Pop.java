package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author houren
 */
public class Event_6Pop extends javax.swing.JFrame {

    private String inputInteger;
    private String input;
    private ArrayList<String> arrList;

    /**
     * Creates new form Event_6Pop
     */
    public Event_6Pop() throws InterruptedException {

        initComponents();
        jTextArea2.setEditable(false);
        jTextArea1.setText("Welcome to Event 6...\n");
        jTextArea1.append("Please input the Integer...\n");
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(true);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(425, 260));
        setSize(new java.awt.Dimension(425, 260));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMaximumSize(new java.awt.Dimension(425, 260));
        jPanel1.setMinimumSize(new java.awt.Dimension(425, 260));
        jPanel1.setPreferredSize(new java.awt.Dimension(425, 283));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setOpaque(false);
        jTextArea1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 280, 120));

        jButton1.setText("Ok");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 235, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 280, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/Event 6 pop resized.jpg"))); // NOI18N
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
            inputInteger = jTextArea2.getText();           
            if (!isNumber(inputInteger)) {
                jTextArea1.append("Invalid input!!\n");
                jTextArea1.append("Please Try Again!!\n");
                jTextArea2.setText("");
            } else {
                jTextArea2.setText("");
                jTextArea1.append("Well Done!!\n");
                jTextArea1.append("Please input the existing friendships...\n");
                jButton1.setText("Send");
                
            }
        } else if (jButton1.getText().equals("Send") && jButton1.isEnabled()) {
            input = jTextArea2.getText();
            String s[] = jTextArea2.getText().split("\\r?\\n");
            arrList = new ArrayList<>(Arrays.asList(s)) ;
            if (!correctElements(arrList)) {
                jTextArea1.append("Invalid input!!\n");
                jTextArea1.append("Please Try Again!!\n");
                jTextArea2.setText("");
            } else {
                jButton1.setText("Nice!");
                jTextArea2.setText("");
                jTextArea2.setEnabled(false);
                System.out.println(inputInteger + " " + input);
                jTextArea1.setText("");
                callEvent6(Integer.parseInt(inputInteger), arrList);
            }

        } else if (jButton1.getText().equals("Nice!") && jButton1.isEnabled()) {
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean isNumber(String str) {
        boolean isNumbers = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(inputInteger.charAt(i))) {
                isNumbers = true;
            } else {
                isNumbers = false;
            }
        }
        return isNumbers;
    }

    public boolean correctElements(ArrayList<String> str) {
        boolean correctElements = true;
        if(str.size()!=Integer.parseInt(inputInteger))
            return false;
        for (int i = 0; i < str.size(); i++) {
            String sSplit[] = str.get(i).split(" ");
            try {
                        int a = Integer.parseInt(sSplit[0]);
                        int b = Integer.parseInt(sSplit[1]);
                        if ((a > Integer.parseInt(inputInteger) || a <= 0) || (b > Integer.parseInt(inputInteger) || b <= 0)) {
                            jTextArea1.append("Invalid Input");
                            return false;
                            
                        }
                    } catch (Exception e) {
                        return false;
                    }
            if(sSplit.length!=2)
            { jTextArea1.append("Invalid Input");
                return false;}
        }
    return correctElements;
        
    }

    public void callEvent6(int inputInteger, ArrayList<String> list) {
        Event_6 event6 = new Event_6(inputInteger, list);
        jTextArea1.append("Loading...\n");

        ArrayList<String> print = event6.getPrint();
        for (int i = 0; i < print.size(); i++) {
            jTextArea1.append(print.get(i) + "\n");
        }
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
//            java.util.logging.Logger.getLogger(Event_6Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Event_6Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Event_6Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Event_6Pop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new Event_6Pop().setVisible(true);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Event_6Pop.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}