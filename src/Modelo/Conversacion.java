
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Conversacion {
    
    private int id;
    private Usuario contacto;
    private ArrayList<Mensaje> mensajes;

    public Conversacion(int id, Usuario receptor) {
        this.id=id;
        this.contacto = receptor;
        mensajes = new ArrayList<>();
    }
    
    public Usuario getContacto() {
        return contacto;
    }
    public String getMensajes() {
        StringBuilder sbMensajes = new StringBuilder();
        for(Mensaje msj : mensajes){
            String hora = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(msj.getFechaEnvio());
            String emisor = msj.getAutor().getNombre();
            String texto = msj.getTexto();
            sbMensajes.append("["+hora+"] "+emisor+": "+texto+"\n");            
        }
        return sbMensajes.toString();
    }
    public int getId() {
        return id;
    }

    
    public void setContacto(Usuario contacto) {
        this.contacto = contacto;
    }
    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMensaje(Mensaje mensajito) {
        mensajes.add(mensajito);
    }


}
