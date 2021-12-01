package org.utl.patron.controlador;

///*
// * ---------------------------------------------------------------- 
// * Archivo: Controller Materia                                              
// * Version: 1.0.0                                                     
// * Autor: Mauricio Castro											                         	
// * Fecha de elaboracion: 01-11-2021                                 
// * ----------------------------------------------------------------
//*/
//
//package org.utl.patron.controller;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.List;
//
////import org.utl.dongalleto.controller.ConexionMySQL;
//import org.utl.dongalleto.model.Materia;
//import org.utl.dongalleto.model.Persona;
//import org.utl.dongalleto.model.Proveedor;
//
//public class ControllerMateria {
//    
//    /**
//     * --------------- Insertar Material
//     *
//     * @param m
//     * @return
//     * @throws Exception
//     */
//    public int insert(Materia m) throws Exception {
//        String sql = "{CALL insertarMateria(?,?,?,?,?)}";      // Datos Material
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        CallableStatement cstmt = conn.prepareCall(sql);
//
//        // Datos Material
//        cstmt.setString(1, m.getNombreMateria());
//        cstmt.setDouble(2, m.getPrecio());
//        cstmt.setString(3, m.getUnidad());
//        cstmt.setDouble(4, m.getCantidad());
//        cstmt.setInt(5, m.getIdProveedor());
//        // ID Materia
//        cstmt.registerOutParameter(6, Types.INTEGER);
//
//        cstmt.execute();
//
//        m.setIdMateria(cstmt.getInt(6));
//         
//        cstmt.close();
//        connMySQL.cerrar();
//
//        return m.getIdMateria();
//    }
//
//    /**
//     * --------------- Actualizar Material
//     *
//     * @param m
//     * @throws Exception
//     */
//    public void update(Materia m) throws Exception {
//        String sql = "{CALL actualizarMateria(?,?,?,?,?,?)}";    // Datos Material
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        CallableStatement cstmt = conn.prepareCall(sql);
//
//        // Datos Material
//        cstmt.setInt(1, m.getIdMateria());
//        cstmt.setString(2, m.getNombreMateria());
//        cstmt.setDouble(3, m.getPrecio());
//        cstmt.setString(4, m.getUnidad());
//        cstmt.setDouble(5, m.getCantidad());
//        cstmt.setInt(6, m.getIdProveedor());
//
//        cstmt.execute();
//
//        cstmt.close();
//        connMySQL.cerrar();
//    }
//    
//    
//    /**
//     * --------------- Actualizar Cantidad Material
//     *
//     * @param m
//     * @throws Exception
//     */
//    public void updateCantidad(Materia m) throws Exception {
//        String sql = "{CALL actualizarExistenciasMateria(?,?)}";    // Datos Material
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        CallableStatement cstmt = conn.prepareCall(sql);
//
//        // Datos Material
//        cstmt.setInt(1, m.getIdMateria());
//        cstmt.setDouble(2, m.getCantidad());
//
//        cstmt.execute();
//
//        cstmt.close();
//        connMySQL.cerrar();
//    }
//    
//
//    /**
//     * --------------- Eliminar Material
//     *
//     * @param id
//     * @throws Exception
//     */
//    public void delete(int id) throws Exception {
//        String sql = "{CALL eliminarMateria(" + id + ")}";
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        CallableStatement cstmt = conn.prepareCall(sql);
//
//        cstmt.execute();
//
//        cstmt.close();
//        connMySQL.cerrar();
//    }
//
//    /**
//     * --------------- Buscar Material
//     *
//     * @param rs
//     * @return
//     * @throws Exception
//     */
//    public Materia fill(ResultSet rs) throws Exception {
//        Materia materia = new Materia();
//
//        materia.setIdMateria(rs.getInt("idMaterial"));
//        materia.setNombreMateria(rs.getString("nombre"));
//        materia.setPrecio(rs.getDouble("precio"));
//        materia.setUnidad(rs.getString("unidad"));
//        materia.setCantidad(rs.getDouble("cantidad"));
//        materia.setIdProveedor(rs.getInt("idProveedor"));
//        materia.setEstatus(rs.getInt("estatus"));
//
//        return materia;
//    }
//
//    /**
//     * --------------- Buscar Material mediante su ID
//     *
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    public Materia findById(int id) throws Exception {
//        String sql = "Select * from Materia where idMateria = " + id;
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//        Materia materia = null;
//        
//        while (rs.next()) {
//            materia = fill(rs);
//        }
//        
//        return materia;
//    }
//
//    /**
//     * --------------- Consulta la tabla de Materia
//     *
//     * @param filtro
//     * @return
//     * @throws Exception
//     */
//    public List<Materia> getAll(String filtro) throws Exception {
//        String sql = "SELECT * FROM materia";
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//
//        List<Materia> listMateria = new ArrayList<>();
//
//        Materia materia = null;
//
//        while (rs.next()) {
//            materia = fill(rs);
//            listMateria.add(materia);
//        }
//
//        return listMateria;
//    }
//    
//    
//    /**
//     * --------------- Consulta la tabla de Materia Activa
//     *
//     * @param filtro
//     * @return
//     * @throws Exception
//     */
//    public List<Materia> getActivos(String filtro) throws Exception {
//        String sql = "SELECT * FROM materia WHERE estatus = 1";
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//
//        List<Materia> listMateria = new ArrayList<>();
//
//        Materia materia = null;
//
//        while (rs.next()) {
//            materia = fill(rs);
//            listMateria.add(materia);
//        }
//
//        return listMateria;
//    }
//    
//    
//    /**
//     * --------------- Consulta la tabla de Materia en Inventario
//     *
//     * @param filtro
//     * @return
//     * @throws Exception
//     */
//    public List<Materia> getInventario(String filtro) throws Exception {
//        String sql = "SELECT * FROM materia WHERE cantidad > 0";
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//
//        List<Materia> listMateria = new ArrayList<>();
//
//        Materia materia = null;
//
//        while (rs.next()) {
//            materia = fill(rs);
//            listMateria.add(materia);
//        }
//
//        return listMateria;
//    }
//    
//
//    /**
//     * --------------- Ultimo Material
//     *
//     * @return
//     * @throws Exception
//     */
//    public int getLastIdMateria() throws Exception {
//        String sql = "SELECT MAX(idMateria) as idMateria FROM materia;";
//
//        ConexionMySQL connMySQL = new ConexionMySQL();
//        Connection conn = connMySQL.abrir();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//
//        Materia m = new Materia();
//        
//        if (rs.next()) {
//            m.setIdMateria(rs.getInt("idMateria"));
//        }
//
//        return m.getIdMateria();
//    }
//    
////////////////////////////////////////Versión César//////////////////////////////////////////
//    public ArrayList<Materia> selecAll() throws SQLException, Exception{
//        String sql = "SELECT * FROM vistaMateriaProveedor WHERE estatus = 1;";
//        ConexionMySQL objConn = new ConexionMySQL();
//        Connection conn = null;
//        Statement stmt = null;
//        ArrayList<Materia> materia = new ArrayList<>();
//        try {
//            conn = objConn.abrir();
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//                while (rs.next()) {
//                                  
//                 Persona objPe = new Persona();
//                 objPe.setIdPersona(rs.getInt(9));
//                 objPe.setNombre(rs.getString(10));
//                 
//                 Proveedor objP = new Proveedor();
//                 objP.setIdProveedor(rs.getInt(7));
//                 objP.setEmpresa(rs.getString(8));
//                 objP.setPersona(objPe);
//                 
//                 Materia objM=new Materia();
//                 objM.setIdMateria(rs.getInt(1));
//                 objM.setNombreMateria(rs.getString(2));
//                 objM.setPrecio(rs.getDouble(3));
//                 objM.setUnidad(rs.getString(4));
//                 objM.setCantidad(rs.getDouble(5));
//                 objM.setEstatus(rs.getInt(6));
//                 objM.setProveedor(objP);
//
//                 materia.add(objM);      
//                }
//            
//            rs.close();
//            stmt.close();
//            objConn.cerrar();
//        } catch (Exception ex) {
//            if (stmt != null) {
//                System.out.println("Error de consultar producto: " + ex.toString());
//                stmt.close();
//            }
//            objConn.cerrar();
//            throw ex;
//        }
//        
//        return materia;
//    }
//    
//    public ArrayList<Materia> selecAllDelete() throws SQLException, Exception{
//        String sql = "SELECT * FROM vistaMateriaProveedor WHERE estatus = 0;";
//        ConexionMySQL objConn = new ConexionMySQL();
//        Connection conn = null;
//        Statement stmt = null;
//        ArrayList<Materia> materia = new ArrayList<>();
//        try {
//            conn = objConn.abrir();
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//                while (rs.next()) {
//                                  
//                 Persona objPe = new Persona();
//                 objPe.setIdPersona(rs.getInt(9));
//                 objPe.setNombre(rs.getString(10));
//                 
//                 Proveedor objP = new Proveedor();
//                 objP.setIdProveedor(rs.getInt(7));
//                 objP.setEmpresa(rs.getString(8));
//                 objP.setPersona(objPe);
//                 
//                 Materia objM=new Materia();
//                 objM.setIdMateria(rs.getInt(1));
//                 objM.setNombreMateria(rs.getString(2));
//                 objM.setPrecio(rs.getDouble(3));
//                 objM.setUnidad(rs.getString(4));
//                 objM.setCantidad(rs.getDouble(5));
//                 objM.setEstatus(rs.getInt(6));
//                 objM.setProveedor(objP);
//
//                 materia.add(objM);      
//                }
//            
//            rs.close();
//            stmt.close();
//            objConn.cerrar();
//        } catch (Exception ex) {
//            if (stmt != null) {
//                System.out.println("Error de consultar producto: " + ex.toString());
//                stmt.close();
//            }
//            objConn.cerrar();
//            throw ex;
//        }
//        
//        return materia;
//    }
//    
//    public boolean insertMateria(Materia materia){
//        ConexionMySQL conexion = new ConexionMySQL();
//        boolean resp= false; 
//        try {
//            Connection c = conexion.abrir();
//            String sql = "call insertarMateria(?,?,?,?,?);";
//            try (CallableStatement stmt = (CallableStatement) c.prepareCall(sql)) {
//                stmt.setString(1, materia.getNombre());
//                stmt.setDouble(2, materia.getPrecio());
//                stmt.setString(3, materia.getUnidad());
//                stmt.setDouble(4, materia.getCantidad());
//                stmt.setInt(5, materia.getProveedor().getIdProveedor());               
//                
//                resp=true;
//                stmt.execute();
//            }
//            conexion.cerrar();
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp=false;
//        }
//        return resp;     
//    }
//    
//    public boolean updateMateria(Materia materia) {
//        ConexionMySQL conexion = new ConexionMySQL();
//         boolean resp= false; 
//        try {
//            Connection c = conexion.abrir();
//            String sql = " call actualizarMateria(?,?,?,?,?,?);";
//            try (CallableStatement stmt = (CallableStatement) c.prepareCall(sql)) {                
//                stmt.setString(1, materia.getNombre());
//                stmt.setDouble(2, materia.getPrecio());
//                stmt.setString(3, materia.getUnidad());
//                stmt.setDouble(4, materia.getCantidad());
//                stmt.setInt(5, materia.getProveedor().getIdProveedor());
//                stmt.setInt(6, materia.getIdMateria());
//                resp=true;
//                stmt.execute();
//            }
//            conexion.cerrar();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return resp;
//    }
//    
//    public boolean actualizarCantidad(Materia materia) throws Exception {
//        ConexionMySQL conexion = new ConexionMySQL();
//         boolean resp= false; 
//        try {
//            Connection c = conexion.abrir();
//            String sql = "{CALL actualizarExistenciasMateria(?,?)};";
//            try (CallableStatement stmt = (CallableStatement) c.prepareCall(sql)) {  
//                stmt.setInt(1, materia.getIdMateria());
//                stmt.setDouble(2, materia.getCantidad());
//                
//                resp=true;
//                stmt.execute();
//            }
//            conexion.cerrar();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return resp;
//    }
//}
//
