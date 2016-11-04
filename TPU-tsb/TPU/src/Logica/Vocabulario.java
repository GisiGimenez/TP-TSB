package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Vocabulario {

    private HashTable<Palabra> vocabulario;

    public Vocabulario() {
        vocabulario = new HashTable<>();
        this.materializar();
    }
    public HashTable<Palabra> getVocabulario(){
        return vocabulario;
    }
    public Vocabulario(Palabra p) {
        vocabulario = new HashTable<>();
        vocabulario.put(p);
    }

    public void agregarPalabra(String p, String doc) {
        Palabra pal = new Palabra(p, doc);

        if (vocabulario.contains(pal)) {
            Palabra pal2 = (Palabra) vocabulario.get(pal);
            pal2.sumarUno();
            pal2.agregarDocumento(doc);
        } else {
            vocabulario.put(pal);
        }
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

    public void materializar() {
        Acceso acc = new Acceso();
        String sql = "";
        Palabra pal;
        String nombre;
        int frecuencia;
        ArrayList<String> documentos = new ArrayList<>();
        try {

            Connection c = acc.conectar();
            c.setAutoCommit(false);
            Statement stm = c.createStatement();
            ResultSet rs;
            sql = "SELECT * FROM PALABRA ";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString(1);
                frecuencia = Integer.parseInt(rs.getString(2));
                documentos = materializarDocs(nombre);
                pal = new Palabra(nombre, frecuencia, documentos);
                vocabulario.put(pal);
            }
            stm.close();
            c.commit();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Vocabulario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<String> materializarDocs(String pal) {
        Acceso acc = new Acceso();
        String sql = "";
        String nombre;
        ArrayList<String> documentos = new ArrayList();
        try {

            Connection c = acc.conectar();
            c.setAutoCommit(false);
            Statement stm = c.createStatement();
            ResultSet rs;
            sql = "SELECT * FROM PALABRA V JOIN PALABRASXDOCUMENTO PD ON V.PALABRA=PD.PALABRA "
                    + "JOIN DOCUMENTOS D ON PD.IDDOCUMENTO=D.ID WHERE V.PALABRA= '" + pal + "'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                documentos.add(rs.getString(7));
            }

            stm.close();
            c.commit();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vocabulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentos;
    }

    public void insert() {

    }

    @Override
    public String toString() {
        return vocabulario.toString();
    }

    
//    for (int i = 0; i < vocabulario.getItems().length; i++) 
//            {
//                for (int j = 0; j < vocabulario.getItems()[i].size(); j++) 
//                {
//                   sql="SELECT * FROM VOCABULARIO WHERE PALABRA= '"+vocabulario.getItems()[i].get(i).getPalabra();
//                    
//                }
//            }
    }
