package Logica;


import Persistencia.Acceso;
import EstructuraDatos.SimpleList;
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
    private Vocabulario voc;
    public Gestor(){        
        voc=new Vocabulario();
    }

    public Vocabulario getVoc() {
        return voc;
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
                char[] lineaVec = linea.toCharArray();
                for (int i = 0; i < lineaVec.length; i++) 
                {
                    if(lineaVec[i]!=' ')
                    { 
                        if(Character.isAlphabetic(lineaVec[i]))
                            a+=lineaVec[i];
                    }
                    else
                    {   
                        voc.agregarPalabra(a.toLowerCase(), f.getName()); 
                        a="";
                    }                 
                }
                voc.agregarPalabra(a.toLowerCase(),f.getName());
                linea = in.readLine();
            } while (linea != null);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
 
   public void procesarLibros(ArrayList lista){
       for (int i = 0; i < lista.size(); i++) {
           leerDoc(lista.get(i).toString());
       }  
   }
   public SimpleList<Palabra> encontrarPorPrimerasLetras(String letras)
   {
       SimpleList ret = new SimpleList(); 
       int longHash = voc.getVocabulario().getItems().length;
       for(int i=0;i<longHash;i++){
           for(int j=0; j<voc.getVocabulario().getItems()[i].size(); j++){
               String pal = voc.getVocabulario().getItems()[i].get(j).getPalabra();
               if(pal.startsWith(letras))
               {
                   ret.addLast(voc.getVocabulario().getItems()[i].get(j));
               }
           }
       }
       return ret;
   }
    public void actualizarTabla() throws SQLException {
        Acceso acc = new Acceso();
        String sql = "INSERT INTO PALABRA (PALABRA, FRECUENCIA,CANTDOCUMENTOS) VALUES (?, ?, ?)";
        String sql2="UPDATE PALABRA SET FRECUENCIA=?, CANTDOCUMENTOS=? WHERE PALABRA=?";
        Connection con = acc.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        for (int i = 0; i < voc.getVocabulario().getItems().length; i++) {
            for (int j = 0; j < voc.getVocabulario().getItems()[i].size(); j++) {
                    if(voc.getVocabulario().getItems()[i].get(j).getInsertada())
                    {   ps2.setString(1, String.valueOf(voc.getVocabulario().getItems()[i].get(j).getFrecuencia()));
                        ps2.setString(2, String.valueOf(voc.getVocabulario().getItems()[i].get(j).getDocumentos().size()));
                        ps2.setString(3, voc.getVocabulario().getItems()[i].get(j).getPalabra());
                        ps2.addBatch();
                    }
                    else
                    {
                        ps.setString(1, voc.getVocabulario().getItems()[i].get(j).getPalabra());
                        ps.setString(2, String.valueOf(voc.getVocabulario().getItems()[i].get(j).getFrecuencia()));
                        ps.setString(3, String.valueOf(voc.getVocabulario().getItems()[i].get(j).getDocumentos().size()));
                        ps.addBatch();  
                        voc.getVocabulario().getItems()[i].get(j).setInsertada(true);                     
                    }
            }
        }
        
            ps2.executeBatch();
            ps.executeBatch();
            ps.close();
            ps2.close();
            con.close();
    }
  
    public void insertar(){
        
    }
    public void modificar(){
        
    }
    public void materializar ()
    {
        voc.materializar();
    }

    
    
}
