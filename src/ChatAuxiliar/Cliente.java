/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatAuxiliar;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author ruben
 */
public class Cliente {
        public static void main(String [] args){
        Cifrado cf=new Cifrado();
        InetAddress ip=null;
        Scanner teclado = new Scanner(System.in);
        String cad="";
        
        try{
            ip = InetAddress.getByName("localhost");
                
        }catch(Exception ex){
        
        }
        
        try(
                Socket sc = new Socket(ip, 15000);
                PrintWriter SAL = new PrintWriter(sc.getOutputStream(), true)
            )
        {
            while(true){
                cad=teclado.nextLine();
                if(cad==null) break;
                cf.setFrase(cad);
                SAL.println(cf.Codifica());
            }
        }catch(Exception ex){
            System.err.println("Error, mensaje: " + ex.getMessage());
        
        }
    
}
}
