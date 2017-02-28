/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatAuxiliar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ruben
 */
public class Servidor {
        static int pto = 15000;
    public static void main(String [] args) throws IOException{
        Cifrado cf = new Cifrado();
        String cad="";
        Socket con;
        boolean salir=false;
        System.out.println("-------- SERVIDOR ESCUCHANDO -----------");
        try(
            ServerSocket tub= new ServerSocket(pto);
            Socket sc1 = tub.accept();
            BufferedReader ENT = new BufferedReader(new InputStreamReader(sc1.getInputStream()))
            )
        {   
            while(!salir){
                cad=ENT.readLine().trim();
                cf.setFrase(cad);
                System.out.println(cf.Decodifica());
            }
        
   
}
    }
}
