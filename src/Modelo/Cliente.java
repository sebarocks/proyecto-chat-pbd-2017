/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Seba
 */
public class Cliente extends Usuario {
    
    private String pass;

    public Cliente(int id, String nombre, String pass, String email) {
        super(id, nombre, email);
        this.pass = pass;
    }
    
    public Cliente(int id, String nombre, String pass) {
        super(id, nombre);
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    @Override
    public String toString()
    {
        return super.toString()+String.format(" , pass: %s",pass);
    }
    
}
