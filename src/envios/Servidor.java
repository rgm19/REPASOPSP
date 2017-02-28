/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envios;

import java.net.ServerSocket;

/**
 *
 * @author ruben
 */
public class Servidor {
    static int puerto=15000;
    public static void main(String[]args){
        System.out.println("Servidor abierto...");
        try(
                   ServerSocket serv = new ServerSocket(puerto);
           ){
            while(true){
                HiloServidor mh = new HiloServidor(serv.accept());
                Thread hilo = new Thread(mh);
                hilo.start();
            }
            
        }catch(Exception e){}
    }
    
}
