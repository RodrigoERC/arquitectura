/////*
//// * ---------------------------------------------------------------- 
//// * Archivo: Controller Proveedor                                              
//// * Version: 1.0.0                                                     
//// * Autor: Mauricio Castro											                         	
//// * Fecha de elaboracion: 01-11-2021                                 
//// * ----------------------------------------------------------------
////*/
////
////package org.utl.patron.controller;
////
////import java.sql.CallableStatement;
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.sql.Statement;
////import java.sql.Types;
////
////import java.util.ArrayList;
////import java.util.List;
////import javafx.collections.FXCollections;
////import javafx.collections.ObservableList;
////
//////import org.utl.dongalleto.controller.ConexionMySQL;
////import org.utl.dongalleto.model.Persona;
////import org.utl.dongalleto.model.Proveedor;
////
////public class ControllerProveedor {
////    
//// /**
////     * --------------- Insertar Proveedor
////     * @param p
////     * @return
////     * @throws Exception 
////     */
////    public int insert(Proveedor p) throws Exception
////    {
////        String sql = "{call insertarProveedor(?,?,?,?,?,"  // Datos de Persona
////                                            + "?,"        // Datos Proveedor
////                                            + "?,?)}";      // ID´s 
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        
////        CallableStatement cstmt = conn.prepareCall(sql);
////        
////        // Persona
////        cstmt.setString(1, p.getNombre());
////        cstmt.setString(2, p.getPrimerApellido());
////        cstmt.setString(3, p.getSegundoApellido());
////        cstmt.setString(4, p.getTel1());
////        cstmt.setString(5, p.getTel2());
////        // Trabajador
////        cstmt.setString(6, p.getEmpresa());
////        // ID´s
////        cstmt.registerOutParameter(7, Types.INTEGER);
////        cstmt.registerOutParameter(8, Types.INTEGER);
////        
////        cstmt.execute();
////        
////        p.setIdPersona(cstmt.getInt(7));
////        p.setIdProveedor(cstmt.getInt(8));
////        
////        cstmt.close();
////        connMySQL.cerrar();
////                
////        return p.getIdProveedor();
////    }
////    
////    /**
////     * --------------- Actualizar Proveedor
////     * @param p
////     * @throws Exception 
////     */
////    public void update(Proveedor p) throws Exception
////    {
////        String sql = "{call actualizarProveedor(?,"            // ID Proveedor
////                                            + "?,?,?,?,?,"      // Datos Persona
////                                            + "?)}";          // Datos Proveedor
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        
////        CallableStatement cstmt = conn.prepareCall(sql);
////        
////        // ID Trabajador
////        cstmt.setInt(1, p.getIdProveedor());
////        // Datos Persona
////        cstmt.setString(2, p.getNombre());
////        cstmt.setString(3, p.getPrimerApellido());
////        cstmt.setString(4, p.getSegundoApellido());
////        cstmt.setString(5, p.getTel1());
////        cstmt.setString(6, p.getTel2());
////        // Datos Trabajador
////        cstmt.setString(7, p.getEmpresa());
////        
////        cstmt.execute();
////      
////        cstmt.close();
////        connMySQL.cerrar();
////    }
////    
////    /**
////     * --------------- Eliminar Proveedor
////     * @param id
////     * @throws Exception 
////     */
////    public void delete(int id) throws Exception
////    {
////        String sql = "{CALL eliminarProveedor("+id+")}";       // ID Proveedor
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        CallableStatement cstmt = conn.prepareCall(sql);
////        
////        cstmt.execute();
////        
////        cstmt.close();
////        connMySQL.cerrar();
////    }
////    
////    /**
////     * --------------- Buscar Proveedor
////     * @param rs
////     * @return
////     * @throws Exception 
////     */
////    public Proveedor fill(ResultSet rs)throws Exception
////    {
////        Proveedor prov = new Proveedor();
////        
////        // Datos Persona
////        prov.setIdPersona(rs.getInt("idPersona"));
////        prov.setNombre(rs.getString("nombre"));
////        prov.setPrimerApellido(rs.getString("apellido1"));
////        prov.setSegundoApellido(rs.getString("apellido2"));
////        prov.setTel1(rs.getString("tel1"));
////        prov.setTel2(rs.getString("tel2"));
////        prov.setEstatusPersona(rs.getInt("estatus"));
////        // Datos Proveedor 
////        prov.setIdProveedor(rs.getInt("idProveedor"));
////        prov.setEmpresa(rs.getString("empresa"));
////  
////        return prov;
////    }
////    
////    /**
////     * --------------- Buscar Proveedor mediante ID
////     * @param id
////     * @return
////     * @throws Exception 
////     */
////    public Proveedor findById(int id) throws Exception
////    {
////        String sql = "Select * from proveedor where idProveedor = " + id;
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        PreparedStatement pstmt = conn.prepareStatement(sql);
////        
////        ResultSet rs = pstmt.executeQuery();
////        Proveedor prov = null;
////        
////        while(rs.next())
////        {
////            prov = fill(rs);
////        }
////        
////        return prov;
////    }
////    
////    /**
////     * --------------- Consulta a la Tabla de Proveedores
////     * @param filtro
////     * @return
////     * @throws Exception 
////     */
////    public List<Proveedor> getAll(String filtro) throws Exception
////    {
////        String sql = "SELECT * FROM proveedor";
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        PreparedStatement pstmt = conn.prepareStatement(sql);
////        
////        ResultSet rs = pstmt.executeQuery();
////        
////        List<Proveedor> listProveedor = new ArrayList<>();
////        
////        Proveedor prov = null;
////        
////        while(rs.next())
////        {
////            prov = fill(rs);
////            listProveedor.add(prov);
////        }
////        
////        return listProveedor;
////    }
////    
////    /**
////     * --------------- Consulta a la Tabla de Proveedores Activos
////     * @param filtro
////     * @return
////     * @throws Exception 
////     */
////    public List<Proveedor> getActivos(String filtro) throws Exception
////    {
////        String sql = "SELECT * FROM Proveedor WHERE estatus = 1";
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        PreparedStatement pstmt = conn.prepareStatement(sql);
////        
////        ResultSet rs = pstmt.executeQuery();
////        
////        List<Proveedor> listProveedor = new ArrayList<>();
////        
////        Proveedor prov = null;
////        
////        while(rs.next())
////        {
////            prov = fill(rs);
////            listProveedor.add(prov);
////        }
////        
////        return listProveedor;
////    }
////    
////    /**
////     * --------------- Trae el ultimo Proveedor
////     * @return
////     * @throws Exception 
////     */
////    public int getLastIdProveedor() throws Exception
////    {
////        String sql = "SELECT MAX(idProveedor) as idProveedor FROM proveedor;";
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        PreparedStatement pstmt = conn.prepareStatement(sql);
////        
////        ResultSet rs = pstmt.executeQuery();
////        
////        Proveedor p = new Proveedor();
////        if(rs.next())
////        {
////            p.setIdProveedor(rs.getInt("idProveedor"));
////        }
////        
////        
////        return p.getIdProveedor();
////    }
////    
////    
////    public ObservableList<String> getProveedores() throws Exception
////    {
////        String sql = "SELECT * FROM proveedor WHERE estatus = 1;";
////        
////        ConexionMySQL connMySQL = new ConexionMySQL();
////        Connection conn = connMySQL.abrir();
////        PreparedStatement pstmt = conn.prepareStatement(sql);
////        
////        ResultSet rs = pstmt.executeQuery();
////        
////        ObservableList<String> obsm = FXCollections.observableArrayList();
////        
////        Proveedor p = null;
////        
////        while(rs.next())
////        {
////            p = fill(rs);
////            String nombre = p.getNombre() +" "+p.getPrimerApellido();
////            obsm.add(nombre);
////        }
////        
////        return obsm;
////    }
////    
////    public ArrayList<Proveedor> selecAll() throws SQLException, Exception{
////        String sql = "SELECT * FROM vistaProveedorPersona WHERE estatus = 1;";
////        ConexionMySQL objConn = new ConexionMySQL();
////        Connection conn = null;
////        Statement stmt = null;
////        ArrayList<Proveedor> proveedor = new ArrayList<>();
////        try {
////            conn = objConn.abrir();
////            stmt = conn.createStatement();
////            ResultSet rs = stmt.executeQuery(sql);
////                while (rs.next()) {
////                                  
////                 Persona objPe = new Persona();
////                 objPe.setIdPersona(rs.getInt(1));
////                 objPe.setNombre(rs.getString(2));
////                 objPe.setPrimerApellido(rs.getString(3));
////                 objPe.setSegundoApellido(rs.getString(4));
////                 objPe.setTel1(rs.getString(5));
////                 objPe.setTel2(rs.getString(6));
////                 objPe.setEstatusPersona(rs.getInt(7));
////                 
////                 Proveedor objP = new Proveedor();
////                 objP.setIdProveedor(rs.getInt(8));
////                 objP.setEmpresa(rs.getString(9));
////                 objP.setPersona(objPe);
////
////                 proveedor.add(objP);      
////                }
////            
////            rs.close();
////            stmt.close();
////            objConn.cerrar();
////        } catch (Exception ex) {
////            if (stmt != null) {
////                System.out.println("Error de consultar producto: " + ex.toString());
////                stmt.close();
////            }
////            objConn.cerrar();
////            throw ex;
////        }
////        
////        return proveedor;
////    }
////    
////}
////
