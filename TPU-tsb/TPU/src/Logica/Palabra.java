package Logica;


import Persistencia.Acceso;
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
    private  boolean insertada;
    
    public Palabra ()
    {
        
    }
    public void setInsertada(boolean ban){
        insertada=ban;
    }
    public boolean getInsertada(){
        return insertada;
    }
    public Palabra(String p, int f, ArrayList<String> lista) //uso para materializar
    {
        palabra=p;
        frecuencia=f;
        documentos=lista;
        insertada=true;
    }
    public Palabra(String p, String doc)
    {
        palabra=p;
        frecuencia=1;
        documentos= new ArrayList <>();
        documentos.add(doc);
        insertada=false;
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
     
    @Override
    public int compareTo(Palabra o) {
        return palabra.compareToIgnoreCase(o.palabra);
    }

}
