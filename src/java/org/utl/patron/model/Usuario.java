/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utl.patron.model;

/**
 *
 * @author HOME
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String username;
    private String fechaNacimiento;
    private String correo;

    public int getidUsuario() {
        return idUsuario;
    }

    public void setIdSujeto(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

//    public Usuario(int idUsuario, String nombre, String username, String fechaNacimiento, String correo) {
//        this.idUsuario = idUsuario;
//        this.nombre = nombre;
//        this.username = username;
//        this.fechaNacimiento = fechaNacimiento;
//        this.correo = correo;
//    }
    
      public Usuario(String nombre, String username) {        
        this.nombre = nombre;
        this.username = username;
    }
    
     
    
}
