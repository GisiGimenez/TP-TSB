package Logica;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class Palabra implements Comparable<Palabra>{
    private String palabra;
    private int frecuencia;
    private ArrayList <String> documentos;
    private final boolean insertada=false;
    
    public Palabra ()
    {
        
    }
    public Palabra(String p, int f, ArrayList<String> lista){
        palabra=p;
        frecuencia=f;
        documentos=lista;
    }
    public Palabra(String p, String doc)
    {
        palabra=p;
        frecuencia=1;
        documentos= new ArrayList <>();
        documentos.add(doc);
    }

    @Override
    public int hashCode()
    {
        return Math.abs(palabra.hashCode()); 
    }
    
    public String getPalabra() {
        return palabra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }
    public void sumarUno()
    {
        frecuencia++;
    }
            
    public ArrayList<String> getDocumentos() {
        return documentos;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setDocumentos(ArrayList<String> documentos) {
        this.documentos = documentos;
    }
    public void agregarDocumento (String doc)
    {
        if(!documentos.contains(doc))
            documentos.add(doc);
    }
    @Override
    public String toString ()
    {
        return "Palabra: "+ palabra+" - Frecuencia: "+frecuencia+" - Documentos: "+documentos.toString();
    }
    public void desMaterializar(){
        Acceso acc=new Acceso();
        String sql;
        try {
            
            Connection c=acc.conectar();
            c.setAutoCommit(false);
            Statement stm=c.createStatement();
            ResultSet rs;
            if(documentos.size()>0)
            {
                sql="UPDATE VOCABULARIO SET FRECUENCIA :"+frecuencia+" WHERE PALABRA = '"+palabra+"'";
                  
            }
            else
            {    
                sql="INSERT INTO VOCABULARIO (palabra,frecuencia,cantidad) VALUES( '"+palabra+"',"+frecuencia+",1)";
                for (int i = 0; i < documentos.size(); i++) {
                  sql="\n INSERT INTO DOCUMENTOS (NOMBRE) VALUES ('"+documentos.get(i).toString()+"')";  
                }
                
            }
            
                rs=stm.executeQuery(sql);
            
                
            
            stm.close();
            c.commit();
            c.close();
            
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Vocabulario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @Override
    public int compareTo(Palabra o) {
        return palabra.compareToIgnoreCase(o.palabra);
    }

}
