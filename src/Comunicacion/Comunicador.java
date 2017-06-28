/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacion;

import Controlador.*;
import Modelo.*;
import java.sql.*;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Seba
 */
public class Comunicador {
    
    //datos de conexion
    static final String IP = "localhost"; /// 25.9.180.116
    static final String URL = "jdbc:oracle:thin:@"+IP+":1521:XE";
    static final String USER = "Proyecto_pbd";//"chat";
    static final String PASS = "chat";//"duocxd";
    
    // OK
    public static int usuarioAcceso(String nombre, String pass) {
        
        // llamar funcion FUNC_VALIDAR_USUARIO 
        //   0: error conexion
        //   1: no existe user
        //   2: pass incorrecta
        //   3: exito :)
        
        int respuesta = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin ? := FUNC_VALIDAR_USUARIO(?,?); end;");
            
            cstmt.registerOutParameter(1, Types.NUMERIC);
            cstmt.setString(2, nombre);
            cstmt.setString(3, pass);
            
            cstmt.executeUpdate();
            
            respuesta = cstmt.getInt(1);
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
            return 0;
        }
        
        return respuesta;
    }
    
    // OK
    public static int usuarioObtenerId(String nombre, String pass){
        
        // llamar procedimiento prc_obtener_id( nombre varchar2, pass varchar2 )
        //   0: error conexion
        
        int id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin ? := FUNC_OBTENER_ID(?,?); end;");
            
            cstmt.registerOutParameter(1, Types.NUMERIC);
            cstmt.setString(2, nombre);
            cstmt.setString(3, pass);
            
            cstmt.executeUpdate();
            
            id = cstmt.getInt(1);
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
            return 0;
        }
        
        return id;
    }
    
    // OK
    public static int usuarioCrear(String nombre, String pass){
        
        // llamar procedimiento prc_usuario_crear (entrega ID)
        //   0: usuario existente
        //  -1: error de conexion
        
        int id = -1;
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin ? := FUNC_CREAR_USUARIO(?,?); end;");
            
            cstmt.registerOutParameter(1, Types.NUMERIC);
            cstmt.setString(2, nombre);
            cstmt.setString(3, pass);
            
            cstmt.executeUpdate();
            
            id = cstmt.getInt(1);
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
            return -1;
        }
        
        return id;
    }
    
    // BENJA
    public static int conversacionObtener(int nombre, String mensaje){
        
              
        // 0 error
        // 1 enviado y guardado
               
        int id = 0;
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin ? := FUNC_ENVIAR_MENSAJE(?,?); end;");
            
            cstmt.registerOutParameter(1, Types.NUMERIC);
          //  cstmt.setString(2, nombre);
            cstmt.setInt(2, id);
            cstmt.setString(3, mensaje);
            
            cstmt.executeUpdate();
            
            id = cstmt.getInt(1);
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
            return 0;
        }
        
        return id;     
        
    }
    
    // OK
    public static String chatObtener(int idConversacion){
        
        Connection con = null;
        CallableStatement cstm = null;
        int index = Chat.getConversaIndex(idConversacion); // posicion en AL conversaciones
        ArrayList<Mensaje> mensajes2 = new ArrayList<>(); // reemplazara AL mensajes
        
        try{
            con = DriverManager.getConnection(URL,USER,PASS);
            cstm = con.prepareCall("begin ? := func_mensajes_obtener(?); end;");
            //cstm = con.prepareCall("proc_mensajes_obtener(?,?); end;");
            
            cstm.registerOutParameter(1, OracleTypes.CURSOR);
            cstm.setInt(2, idConversacion);
            cstm.execute();
            
            ResultSet cursor = (ResultSet) cstm.getObject(1);
            
            while(cursor.next()){
                // obtiene datos
                Date horaenvio = cursor.getDate("horaenvio");
                String texto = cursor.getString("texto");
                int idAutor = cursor.getInt("id_autor");
                //crea objetos
                Usuario autor = Chat.getUsuario(idAutor);
                Mensaje mensajito = new Mensaje(autor,horaenvio,true,texto);
                // agrega mensaje
                mensajes2.add(mensajito);
            }
            // remplaza antiguo arraylist de mensajes
            Chat.getAlConversacion().get(index).setMensajes(mensajes2);
        }
        catch (SQLException e){
           System.out.println(e.getMessage()); 
        }
        
        return "";
    }
    
    // OK
    public static boolean mensajeEnviar(String mensaje, int idCliente, int idConversa){
               
        Connection con = null;
        CallableStatement cstmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            cstmt = con.prepareCall("begin PRC_MENSAJE_ENVIAR(?,?,?); end;");
            
            cstmt.setInt(1, idConversa);
            cstmt.setInt(2, idCliente);
            cstmt.setString(3, mensaje);
            
            cstmt.executeUpdate();
            
            cstmt.close();
            con.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;        
    }
    
    // OK
    public static void conversacionesObtener(int idCliente){
        ArrayList<Conversacion> conversas = new ArrayList<>();
        Connection con = null;
        CallableStatement stmt = null;
        
        try{
            con = DriverManager.getConnection(URL, USER, PASS);
            stmt = con.prepareCall("begin ?:= func_conversas_obtener(?); end;");
            
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setInt(2, idCliente);
            stmt.execute();
            
            ResultSet cursor = (ResultSet) stmt.getObject(1);
            
            while (cursor.next()){                
                int idConversa = cursor.getInt("ID_CONVERSA");
                int idContacto = cursor.getInt("ID_USUARIO");
                String nombreContacto = cursor.getString("NOMBRE");
                String mail = cursor.getString("EMAIL");
                
                Usuario contacto = new Usuario(idContacto,nombreContacto,mail);
                Conversacion conversa = new Conversacion(idConversa, contacto);                
                conversas.add(conversa);
                Chat.addContacto(contacto);
            }            
            Chat.setAlConversacion(conversas);
            stmt.close();
            con.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());            
        }
    }
    
    // OK?
    public static int crearConversa(int idCliente, int idContacto){
        // 0 : error indefinido
        // other : idConversa
        // -1: error de conexion
        Connection con = null;
        CallableStatement stmt = null; 
        int resultado = 0;
        
        try{
            con = DriverManager.getConnection(URL,USER,PASS);
            stmt = con.prepareCall("begin ? := func_conversa_crear(?,?); end;");
            
            stmt.registerOutParameter(1, Types.NUMERIC);
            stmt.setInt(2, idCliente);
            stmt.setInt(3, idContacto);
            
            stmt.executeUpdate();
            
            resultado = stmt.getInt(1);
            
            stmt.close();
            con.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
        
        return resultado;
    }

    public static int obtenerIdContacto(String nombreContacto) {
        // 0 : no encontrado
        // other : idContacto
        // -1: error de conexion
        Connection con = null;
        CallableStatement stmt = null; 
        int resultado = 0;
        
        try{
            con = DriverManager.getConnection(URL,USER,PASS);
            stmt = con.prepareCall("begin ? := func_id_contacto(?); end;");
            
            stmt.registerOutParameter(1, Types.NUMERIC);
            stmt.setString(2, nombreContacto);
            
            stmt.executeUpdate();
            
            resultado = stmt.getInt(1);
            
            stmt.close();
            con.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return -1;
        }
        
        return resultado;
    }
}
