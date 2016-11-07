/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Logica.Palabra;
import Logica.Vocabulario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gisela
 */
public class Persistencia {
    public void insertar(){
        
    }
       public boolean buscarEnBD(Palabra pal) {
        Acceso acc = new Acceso();
        try {

            Connection c = acc.conectar();
            c.setAutoCommit(false);
            Statement stm = c.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM VOCABULARIO WHERE PALABRA=" + pal.getPalabra();
            rs = stm.executeQuery(sql);

            stm.close();
            c.commit();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vocabulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
}
