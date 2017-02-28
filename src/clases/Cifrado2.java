package clases;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author ruben
 */
public class Cifrado2{
    
    
      public static String cifrarBase64(byte[]a){
            Base64.Encoder encoder = Base64.getEncoder();
            String b = encoder.encodeToString(a);
            return b;    
       }
      
      public static String descifrarBase64(String a){
         Base64.Decoder decoder = Base64.getDecoder();
         byte [] decodedByteArray = decoder.decode(a);
         
         String b = new String(decodedByteArray);
          return b;
      } 
      
      
      public static void main(String[]args){
          String clave="claveCifrado";
          String frase = "cigraremos esta Frase";
          byte [] bclaveprov = null;
          byte[]bclave;
          byte []bfrase=null;
          byte[] befrase = null;
          byte [] bpfrase = null;
          KeySpec ks;
          SecretKeyFactory skf;
          Cipher cifrar;
          SecretKey clave_priv;
          
          
          try{
              bclaveprov = clave.getBytes("UTF8");
              //Algoritmo DESEde espera un array de bytes de 24
              bclave = copyOf(bclaveprov, 24);
              ks = new DESedeKeySpec(bclave);
              skf = SecretKeyFactory.getInstance("DESede");
              clave_priv = skf.generateSecret(ks);
              cifrar = Cipher.getInstance("DESede");
              
              bfrase = frase.getBytes("UTF8");
              cifrar.init(Cipher.ENCRYPT_MODE, clave_priv);
              befrase = cifrar.doFinal(bfrase);
              
              cifrar.init(Cipher.DECRYPT_MODE, clave_priv);
              bpfrase = cifrar.doFinal(bfrase);
          }catch(Exception e){}
          
          System.out.println("Frase Original: " + frase);
        System.out.println("Frase cifrada utilizando TripleDES (DESede)");
       System.out.println(cifrarBase64(befrase));
       System.out.println("Frase vuelta a descifrar: ");
       //Pasamos el array descifrado a base64 y lo decodificamos
       String df = cifrarBase64(bpfrase);
       System.out.println(descifrarBase64(df));
          
      }
        
    
}
