/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Alumno
 */
public class Mensaje {
    
    private Usuario autor;
    private Date fechaEnvio;
    private boolean recibido;
    private String texto;

    public Mensaje(Usuario autor, Date fechaEnvio, boolean recibido, String texto) {
        this.autor = autor;
        this.fechaEnvio = fechaEnvio;
        this.recibido = recibido;
        this.texto = texto;
    }
    
    
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
   @Override
    public String toString() {
        return "fechaEnvio=" + fechaEnvio + ", recibido=" + recibido + ", texto=" + texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }



    
    
   
    
    
    
}
