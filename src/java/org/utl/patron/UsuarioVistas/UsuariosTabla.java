/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utl.patron.UsuarioVistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.utl.patron.dao.ConexionSQL;
import org.utl.patron.model.Usuario;

/**
 *
 * @author sopsoftware18
 */
public class UsuariosTabla {
      //DatosVehiculo dv;
    Statement stmt;
    ConexionSQL con;
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;

    public List<Usuario> getAll() {

        String query = "SELECT * FROM usuario;";
        con = new ConexionSQL();
        List<Usuario> listaVehiculo = new ArrayList<>();

        try {
            con.abrir();
            stmt = con.getConexion().prepareStatement(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                listaVehiculo.add(new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("userName"),
                        rs.getString("correo"),
                        rs.getString("fechaNacimiento")                        
                ));
            }

            stmt.close();
            rs.close();
            con.cerrar();
            return listaVehiculo;

        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
