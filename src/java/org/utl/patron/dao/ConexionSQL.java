/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utl.patron.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HOME
 */
public class ConexionSQL {
    String userName;
    String password;
    String url;
    Connection conautoguia;
    
    public ConexionSQL(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            userName="root";
            password="root";
            url="jdbc:mysql://localhost:3306/DonGalleto";
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Connection abrir() throws Exception{
        conautoguia = DriverManager.getConnection(url, userName, password);
        return conautoguia;
    }
    
    public void cerrar() throws Exception{
        try{
        if (conautoguia != null) {
            conautoguia.close();
            conautoguia=null;
        }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public Connection getConexion(){
        return conautoguia;
    }
}
