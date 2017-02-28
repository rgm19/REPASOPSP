/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrar_descifrar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import static java.util.Arrays.copyOf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author ruben
 */
public class Cifrado {
    
    private String frase;
    private String clave;
    private SecretKey pass;
    Cipher cipher;
    private File fichero;
    
    Cifrado(File fichero, String clave){
        this.clave=clave;
        this.fichero=fichero;
        this.pass = generaPass();
        initCipher();
    }            
    
        
     public void setFrase(String fr){
            frase=fr;
     } 
     
     public void initCipher(){
         try{
             cipher = Cipher.getInstance("DESede");
         } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     } 
     
     
     private SecretKey generaPass(){
         KeySpec sc;
         SecretKeyFactory skf;
         SecretKey sk=null;
         byte[] clave1;
         byte [] clave2;
         try{
             clave1 = clave.getBytes("UTF8");
             clave2= copyOf(clave1, 24);
             sc = new DESedeKeySpec(clave2);
             skf = SecretKeyFactory.getInstance("DESede");
             sk = skf.generateSecret(sc);
         }catch(Exception e){}
         
         return sk;
     } 
     
     public void Codifica(){
         byte [] bfrase = new byte[1024];
         File fout = new File(fichero.getPath()+".cif");
         try{
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichero));
             
             cipher.init(Cipher.ENCRYPT_MODE, pass);
             
             //*****************************************************************
             //Parte del codifo que habia en el foro
             //*****************************************************************
             CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(fout), cipher);
             int lectura= 0;
             while((lectura=bis.read(bfrase))>0){
                 cos.write(bfrase,0,lectura);
             }
             cos.close();
             //*****************************************************************
             
         }catch(Exception e){}
     }
     
     public void Descodifica(){
         byte[]bfrase= new byte[1024];
         File fout = new File(fichero.getPath()+".des");
         
         try{
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichero));
             
             cipher.init(Cipher.DECRYPT_MODE, pass);
             CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(fout),cipher);
             int lectura =0;
             while((lectura = bis.read(bfrase))>0){
                 cos.write(bfrase, 0 , lectura);
             }
             cos.close();
         }catch(Exception e){}
     }
    
}
