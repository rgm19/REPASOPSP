/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptador;

import java.util.Base64;

/**
 *
 * @author ruben
 */
public class Principal {

      public static void main(String[]args){
                Encriptador encrip = new Encriptador();
                encrip.setTitle("Encriptador/Descriptador");
                encrip.setVisible(true);
                encrip.setLocationRelativeTo(null);
      }
//------------------------------------------------------------------------------      
      public static String descifrarBase64(String a){
          Base64.Decoder decoder = Base64.getDecoder();
          byte[] decodedByteArray = decoder.decode(a);
          
          String b = new String(decodedByteArray);
          
          return b;
      }
//------------------------------------------------------------------------------

    public static String cifrarBase64(byte []a){
        Base64.Encoder encoder = Base64.getEncoder();
        String b = encoder.encodeToString(a);
        return b;
    }
}
