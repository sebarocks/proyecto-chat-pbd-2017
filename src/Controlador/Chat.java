/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.*;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Chat {
    
    private static Cliente cliente;
    private static ArrayList<Usuario> contactos = new ArrayList<>(); 
    private static ArrayList<Conversacion> alConversacion= new ArrayList<>();

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente aCliente) {
        cliente = aCliente;
    }
    
    public static int getClienteId(){
        return cliente.getId();
    }
    
    public static String getClienteNombre(){
        return cliente.getNombre();
    }
    
    public static ArrayList<Conversacion> getAlConversacion() {
        return alConversacion;
    }
    
    public static void setAlConversacion(ArrayList<Conversacion> aAlConversacion) {
        alConversacion = aAlConversacion;
    } 
    
    public static void Agregar(Conversacion conversa){  // falta verificar que no sea null!
        alConversacion.add(conversa);
    }
    
    public static int getConversaIndex(int idConversa){
        for(Conversacion conv : alConversacion){
            if(conv.getId()==idConversa){
                return alConversacion.indexOf(conv);
            }
        }
        return -1;
    }

    public static void addContacto(Usuario contacto) {
        contactos.add(contacto);
    }
    
    
    public static Usuario getUsuario(int idUsuario){
        if(cliente.getId()==idUsuario){
            return cliente;
        }
        else {
            for(Usuario usr : contactos){
                if(usr.getId()==idUsuario){
                    return usr;
                }
            }
            return null;
        }
    }

    public static void addConversacion(Conversacion nConversa) {
        alConversacion.add(nConversa);
    }
    
    public static boolean tieneContacto(int idContacto){
        for(Usuario c : contactos){
            if(c.getId()==idContacto){
                return true;
            }
        }
        return false;
    }
    
}
