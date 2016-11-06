package Logica;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author a2
 */
public class Gestor {
    private Cola cola;
    private Vocabulario v;
    public Gestor(){
        cola=new Cola();
        v=new Vocabulario();
    }

    public Vocabulario getV() {
        return v;
    }
    
    public void leerDoc(String nombre)
    {
         
       File f=new File(nombre);
        if (!f.getName().endsWith(".txt")) {
            return;
        }
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "8859_1"));
            String linea = in.readLine();
            do {                
                String a = "";
                char[] hola = linea.toCharArray();
                for (int i = 0; i < hola.length; i++) 
                {
                    if(hola[i]!=' ')
                    { 
                        if(Character.isAlphabetic(hola[i]))
                            a+=hola[i];
                    }
                    else
                    {   
//                        if(a!="")
                        v.agregarPalabra(a.toLowerCase(), f.getName()); 
                        a="";
                    }                 
                }
                v.agregarPalabra(a.toLowerCase(),f.getName());
                linea = in.readLine();
            } while (linea != null);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(v);
        
    }
    public void leerLibros(){
        
    }
   public void procesarLibros(ArrayList lista){
       cola.addAll(lista);
       while(!cola.isEmpty()){
           leerDoc(cola.poll().toString());
       }
       
   }
    public void actualizarTabla() throws SQLException {
        Acceso acc = new Acceso();
        String sql = "INSERT INTO PALABRA (PALABRA, FRECUENCIA,CANTIDADDOC) VALUES (?, ?, ?) ";
        String sql2="UPDATE PALABRA SET FRECUENCIA=?, CANTIDADDOC=? WHERE PALABRA=?";
        Connection con = acc.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        for (int i = 0; i < v.getVocabulario().getItems().length; i++) {
            for (int j = 0; j < v.getVocabulario().getItems()[i].size(); j++) {
                    if(!this.estaEnBD(v.getVocabulario().getItems()[i].get(j)))
                    {   ps.setString(1, v.getVocabulario().getItems()[i].get(j).getPalabra());
                        ps.setString(2, String.valueOf(v.getVocabulario().getItems()[i].get(j).getFrecuencia()));
                        ps.setString(3, String.valueOf(v.getVocabulario().getItems()[i].get(j).getDocumentos().size()));
                        ps.addBatch();
                        System.out.println("jaja");
                    }
                    else
                    {
                        System.out.println("jajaja");
                        ps2.setString(1, String.valueOf(v.getVocabulario().getItems()[i].get(j).getFrecuencia()));
                        ps2.setString(2, String.valueOf(v.getVocabulario().getItems()[i].get(j).getDocumentos().size()));
                        ps2.setString(3, v.getVocabulario().getItems()[i].get(j).getPalabra());
                        ps2.addBatch();                       
                    }
            }
        }
            ps2.executeBatch();
            ps.executeBatch();
            ps.close();
            ps2.close();
            con.close();
    }
       public boolean estaEnBD(Palabra pal) {
        Acceso acc = new Acceso();
        boolean ban=false;
        try {

            Connection c = acc.conectar();
            c.setAutoCommit(false);
            Statement stm = c.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM PALABRA WHERE PALABRA= '" + pal.getPalabra()+"'";
            rs = stm.executeQuery(sql);
            rs.next();
            if(rs.getRow()>0)
                ban=true;
            stm.close();
            c.commit();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vocabulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;

    }
    public void insertar(){
        
    }
    public void modificar(){
        
    }
    public void materializar ()
    {
        v.materializar();
    }
    
}
