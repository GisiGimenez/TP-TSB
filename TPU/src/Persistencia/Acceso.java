/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gisela
 */
public class Acceso {
    private static String rutaConexion;
    public Acceso(){
        rutaConexion="jdbc:derby://localhost:1527/Vocabulario";
    }
    public Connection conectar(){
        Connection c=null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            c=DriverManager.getConnection(rutaConexion);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() +": "+e.getMessage());
            System.exit(0);
        }
        return c;
    }
    
}
