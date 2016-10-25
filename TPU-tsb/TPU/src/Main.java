
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
            do {                
                String a = "";
                char[] hola = linea.toCharArray();
            for (int i = 0; i < hola.length; i++) // esta version me separa Cha7u en cha - u
            {
                if(!Character.isSpaceChar(hola[i]))
                { 
                    if(Character.isAlphabetic(hola[i]))
                        a+=hola[i];
                    else
                    {
                        if(a!="")
                        v.agregarPalabra(a.toUpperCase(), f.getName()); //las paso a mayuscula para que no me las cuente por separado(se puede a minuscula tmb)
                        a="";
                    }
                }
                else
                {   
                    if(a!="")
                    v.agregarPalabra(a.toUpperCase(), f.getName()); 
                    a="";
                }                 
            }
             linea = in.readLine();
            } while (linea != null);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(v);
    }

}
