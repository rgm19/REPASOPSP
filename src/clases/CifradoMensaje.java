/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author ruben
 */
public class CifradoMensaje{ 
    public static void main(String [] args){
        
        String texto = "-- Mensaje a cifrar...";
        String cad="";
        MessageDigest md = null;
        
        try{
            md = MessageDigest.getInstance("SHA-512"); 
        }catch(NoSuchAlgorithmException ex){
            System.err.println("Error algorritmo SHA-256 no disponible");
        }
        
        //Trabajamos en bytes pasamos el texto y se cifra
        md.update(texto.getBytes());
        byte [] cifrado = md.digest();
        
        //Pasamos a base 64 para ver el array de bytes
        Base64.Encoder codi = Base64.getEncoder();
        String ba = codi.encodeToString(cifrado);
        System.out.println("Frase en BASE64: "+ba+"\n");
        
        //o lo pasamos a hezadecimal
        for(byte aux : cifrado){
            int b = aux & 0xff;
            if(Integer.toHexString(b).length() == 1){
                //los numeros de 0 a F los pone 00, 01, ...0F
                cad += "0";
            }
            
        }
        System.out.println("Hexadecimal: "+ cad);
        
    }    
}
