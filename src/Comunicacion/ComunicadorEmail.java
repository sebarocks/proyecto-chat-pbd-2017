
package Comunicacion;

import static Comunicacion.Comunicador.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static Comunicacion.Comunicador.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ComunicadorEmail {    
    
    
       
    public static String[] SolicitudPass(String texto)
    {
        String[] resultado = new String[3] ;
        resultado[0]=""; //USUARIO
        resultado[1]=""; //PASS
        resultado[2]=""; //CORREO
        
        if  (isCorreo(texto))        {
                resultado[0]= BuscarUsuarioEnvioCorreo(texto,0,0);                 
                if (!resultado[0].equals("-1")) {
                    resultado[1]= BuscarUsuarioEnvioCorreo(texto,0,1);
                    resultado[2]= texto;
                }
                else
                {
                   // System.out.println(resultado[0] + resultado[1] + resultado[2] + "el correo");              
                   
                }          
        }
        else
        {
            resultado[2]= BuscarUsuarioEnvioCorreo(texto,1,2);               
            if (!resultado[2].equals("-1")) 
            {
                resultado[1]= BuscarUsuarioEnvioCorreo(texto,1,1);
                resultado[0]= texto;
            }
            else
            {
                 //System.out.println(resultado[0] + resultado[1] + resultado[2] + "no correo");
            }     
        }      
     
        return resultado;
    }   
    //BENJA
    public static boolean isCorreo(String texto)
    {
         for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '@') { 
                return true;
            }
         }        
        return false;
    }
    //BENJA
    public static String BuscarUsuarioEnvioCorreo(String texto, int i, int j)
    {
                                         //correo      nombre          condicion
          
         // i = 0 : Correo Electronico uso para llamado : adquiere el-- user y pass  
         // i = 1 : Usuario uso para llamado : adquiere si existe  ---- correo y pass  
        
                                    //j = 0 : usuario
                                    //j = 1 : pass
                                    //j = 2 : correo
            
        //respuestas : "Lo_encontrado" o "-1" :  no existe nada registrado;
        String respuesta = "";
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin ? := FUNC_BUSCAR_ENVIAR_CORREO(?,?,?); end;");
            
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, texto);
            cstmt.setInt(3, i);
            cstmt.setInt(4, j);
            
            cstmt.executeUpdate();           
            respuesta = cstmt.getString(1);
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
           return "-1";            
        }
        
        return respuesta;
    }
    
    //BENJA
     public static int  EnviarMensaje(String[] resultado, int motivo)
    {
        String Username = "pbdchatsoporte@gmail.com";
        String PassWord = "abcd14abcd";
        String Subject = "";
         String Mensaje = "";
         Date date=new Date(); 
         
         DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");       
         
        String To =resultado[2]; //correo
        if (motivo==0)  //recuperacion de clave
        {
             Subject ="Solicitud de recuperación de contraseña (PDB CHAT)"; //
             Mensaje = "Has solicitado la recuperación de tu contraseña "
                     + "Si no solicitaste esto, ignora este correo electrónico \n \n CHAT PDB "
                     + "\n Solicitud realizada a las  " + hourdateFormat.format(date)
                          +"\n \n Usuario: " + resultado[0] 
                          +"\n Contraseña: " + resultado[1]
                          +"\n \n                Atentamente SOPORTE CHAT PBD";
        }
        if(motivo ==1 ) // new registro
        {
            Subject ="Gracias por registrarte a PBD CHAT  -"; //
             Mensaje = "Te has registro de CHAT PBD "
                      + "\n Solicitud del registro realizada a las  " + hourdateFormat.format(date)
                          +"\n \n Usuario: " + resultado[0] 
                          +"\n Contraseña: " + resultado[1]
                          +"\n \n Atentamente SOPORTE CHAT PBD";
        }
       
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session;
        
        session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(Username, PassWord);
            }
        }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensaje);

            Transport.send(message);         

        } catch (MessagingException e) {
            return -1;
        }     
        return 1;
    }
}

