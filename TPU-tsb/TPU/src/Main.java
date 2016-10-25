
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author a2
 */
public class Main {

    public static void main(String[] args) {
        // Buscar NULK INSERT para hacer varios insert juntos
        // autocommit desactivado
       Vocabulario v = new Vocabulario();
       File f=new File("ju.txt");
//        if (!f.getName().endsWith(".txt")) {
//            return;
//        }
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "8859_1"));
            String linea = in.readLine();
            boolean ban=false;
            do {                

                String a = "";
                char ultimo;
                
                char[] hola = linea.toCharArray();
                
                
            for (int i = 0; i < hola.length; i++) 
            {
                if(!Character.isSpaceChar(hola[i]))
                {
                    if(Character.isAlphabetic(hola[i]))
                        a+=hola[i];
                    else
                    {
                        if(Character.isSpaceChar(hola[i+1]))
                        v.agregarPalabra(a, f.getName());
                        a="";
                    }
                }
                else
                {
                    if(Character.isAlphabetic(hola[i-2]))
                    v.agregarPalabra(a, f.getName());
                    a="";
                } 
//                if(Character.isAlphabetic(hola[i]))
//                {
//                    if(!Character.isSpaceChar(hola[i]))
//                    { 
//                        ultimo=hola[i];
//                        a+=hola[i];}
//                    else
//                    {
//                        v.agregarPalabra(a, f.getName());
//                        a="";
//                    }
//                }
//                else
//                {
//                    v.agregarPalabra(a, f.getName());
//                    a="";
//                    
//                } 
                
            }
             linea = in.readLine();
            } while (linea != null);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(v);
    }

}
