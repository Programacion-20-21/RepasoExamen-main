/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.Usuario;
import Vista.VentanaUsuario;
import java.security.interfaces.RSAKey;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javierlegua
 */
public class IODatos {
    
    private static final String RUTA = "jdbc:mysql://34.229.80.204:3306/magallon";
    private static final String USU = "magallon";
    private static final String PASS = "magallon";
    
    public static void InsertarUsuEjemplo(){
        
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS)){
           
            System.out.println("Conexión realizada");
            
            String sql = "insert into Usuario values('magallon', 'Admin1234')";
            
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conexion cerrada");
        
    }

    public static Usuario comprobarlogin(String nombre, String pass) {
     
        
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
           
            System.out.println("Conexión realizada");
            
            String sql = "select * from Usuario where nombre= '"+nombre+"'and contraseña='"+pass+"';";
            
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                System.out.println(rs.getString(1) + rs.getString(2));
                
                if((rs.getString(1).equalsIgnoreCase(nombre)) && (rs.getString(2).equalsIgnoreCase(pass))){
                    Usuario u = new Usuario(rs.getString(1), rs.getString(2));
                    return u;
                }
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conexion cerrada");
        return null;
    }

    public static DefaultTableModel rellenarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(); 
        try (Connection con = DriverManager.getConnection(RUTA, USU, PASS);){
            String sentencia = "Select * from Usuario";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            modelo.addColumn("Nombre");
            modelo.addColumn("Contraseña");
          
            
            while(rs.next()){
                Object[] fila = {rs.getString(1), rs.getString(2)};
                modelo.addRow(fila);
            }
            
            
           
                    return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(IODatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    
    
    
}

