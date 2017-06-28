/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Comunicacion.ComunicadorEmail;
import static Comunicacion.ComunicadorEmail.*;
import javax.swing.JOptionPane;

/**
 *
 * @author benja
 */
public class Recuperar_Login extends javax.swing.JFrame {

    /**
     * Creates new form Recuperar_Login
     */
    public Recuperar_Login() {
        initComponents();
        setTitle("**** RECUPERAR CLAVE **** ");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jTextRecuperarCorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("RECUPERAR CONTRASEÑA");

        jToggleButton1.setText("Atras");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuario/Correo Electronico:");

        jToggleButton2.setText("Enviar Solicitud");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextRecuperarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton2)
                        .addGap(82, 82, 82)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextRecuperarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jToggleButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
         new Login().setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:        
        String texto = jTextRecuperarCorreo.getText().toLowerCase();      
        
        String[] user = new String[3];
        
        user[0]="User";user[1]="Pass";user[2]="Correo";
        
        if (texto.length() > 0) {
                      
            user = SolicitudPass(texto);
            
            if (user[0].equals("-1")) {
                JOptionPane.showMessageDialog(this, "Usuario no existe");
            }
            if (user[2].equals("-1")) {
                JOptionPane.showMessageDialog(this, "Correo no existe");
            }
            if(!user[0].equals("-1") || !user[2].equals("-1"))
            {
              //  JOptionPane.showMessageDialog(this, "Cuenta " + user[0] +  " " + user[1] + " " + user[2]);
                
                //enviar correo
               if(ComunicadorEmail.EnviarMensaje(user,0) == 1)
               {
                     JOptionPane.showMessageDialog(this, "Correo ha sido enviado");
               }
               else
               {
                    JOptionPane.showMessageDialog(this, "Correo no se ha enviado");
               }
               
            }
            
            
                        
            // new Login().setVisible(true);
            // this.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su correo o usuario para recuperar");
        }
        
        
    }//GEN-LAST:event_jToggleButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextRecuperarCorreo;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
