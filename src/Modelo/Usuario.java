/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Alumno
 */
public class Usuario {

    private int id;
    private String nombre;
    private String email;
    
    public Usuario(int id, String nombre , String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
    
    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.email = "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString()
    {
        return String.format("id: %s , nombre: %s", id, nombre);
    }

}
