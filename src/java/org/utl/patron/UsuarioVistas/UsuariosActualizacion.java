/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utl.patron.UsuarioVistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.utl.patron.dao.ConexionSQL;
import org.utl.patron.model.Usuario;

/**
 *
 * @author sopsoftware18
 */
public class UsuariosActualizacion {
     Statement stmt;
    ConexionSQL con;
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;

    public boolean update(Usuario u) throws Exception {
        String query = "update usuario set"
                + " userName ='" + u.getNombre() + "',"
                + " where idUsuario = " + u.getidUsuario()+ ";";

        System.out.println(query);
        con = new ConexionSQL();

        try {
            con.abrir();
            conn = con.getConexion();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(query);
            int res = stmt.executeUpdate(query);

            if (res == 0) {
                throw new SQLException("ERROR");
            }
            conn.commit();
            con.cerrar();
            stmt.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString());

            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            return false;

        } finally {
            try {
                conn.setAutoCommit(true);
                stmt.close();
                conn.close();
                con.cerrar();
            } catch (SQLException exc) {
                System.out.println(exc.toString());
            }
        }
    }
}
