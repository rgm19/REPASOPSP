/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatAuxiliar;

import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author ruben
 */
public class Cifrado {
    private String frase;
    private String clave="clave15";
    private SecretKey pass;
    Cipher cifrar;
    
    public Cifrado(){
        pass=generaPass();
        initCipher();
    }
    
        public void setFrase(String fr){
        frase=fr;
    }
        
        public void initCipher(){
            try{
                cifrar = Cipher.getInstance("DESede");
            }catch(Exception e){}
        }
        
        public String Codifica(){
            byte[]bfrase, ebfrase=null;
            String frase_enc="";
            
            try{
                String frasecod="";
                cifrar.init(Cipher.ENCRYPT_MODE, pass);
                bfrase = frase.getBytes("UTF8");
                ebfrase = cifrar.doFinal(bfrase);
            }catch(Exception e){}
            
            Base64.Encoder encoder = Base64.getEncoder();
            frase_enc= encoder.encodeToString(ebfrase);
            return frase_enc;
        }
        
        
        public String Decodifica(){
        byte [] frase1, frase2=null;
        Base64.Decoder decoder = Base64.getDecoder();
        frase1= decoder.decode(frase);
        
        try{
            cifrar.init(Cipher.DECRYPT_MODE, pass);
            frase2 = cifrar.doFinal(frase1);
        }catch(Exception e){}
        
        Base64.Encoder encoder = Base64.getEncoder();
        String fraseb64 = encoder.encodeToString(frase2);
        return new String(decoder.decode(fraseb64));
        
        } 
        
        public SecretKey generaPass(){
            KeySpec sc;
            SecretKeyFactory skf;
            SecretKey sk = null;
            byte[]clave1, clave2;
            
            try{
                clave1 = clave.getBytes("UTF8");
                clave2 = copyOf(clave1, 24);
                sc = new DESedeKeySpec(clave2);
                skf = SecretKeyFactory.getInstance("DESede");
                sk= skf.generateSecret(sc);
            }catch(Exception e){}
        return sk;
        }
}
