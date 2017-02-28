/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CifradoSHA{
	File fichero;

	CifradoSHA(File fichero){
		this.fichero=fichero;
	}

	public void generarSHA(){
		MessageDigest digest;

		try(
			InputStream istream = new FileInputStream(fichero);
			FileWriter fw = new FileWriter(fichero.getPath()+".sha512");
		){
			digest= MessageDigest.getInstance("SHA-512");
                        byte []buffer = new byte[(int)fichero.length()];
                        int lectura =0;
                        while((lectura = istream.read(buffer)) > 0){
                            digest.update(buffer,0,lectura);
                        }

                        
			byte[] sha = digest.digest();
                        BigInteger bigInt = new BigInteger(1, sha);
                        String output = bigInt.toString();
                        
                        fw.write(output + "\t" + fichero.getPath());
                        
                        
		}catch(Exception e){}
	}
}