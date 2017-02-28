/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApoyoSerializable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author ruben
 */
public class HiloServidor implements Runnable {

    Socket con;
    File fichero;
    
    public HiloServidor(Socket s){
        con = s;
    }
    
    @Override
    public void run() {
        try(
            ObjectInputStream ois = new ObjectInputStream(con.getInputStream());
            ){
            //me llega el nmbre del mensaje
            Object mensaje = ois.readObject();
            if(mensaje instanceof aux.FicheroEntrada){
                String nombre = ((aux.FicheroEntrada) mensaje).getNombre_fichero();
                //Se abre un fichero para empezar a copiar lo que se reciba
                FileOutputStream fos = new FileOutputStream(nombre);
                aux.FicheroSalida mensajeRecibido;
                Object mensajeAux;
                
                do{
                    //leemos el mensaje en una variable auxiliar 'mensajeaux'
                    mensajeAux = ois.readObject();
                    
                    if(mensajeAux instanceof aux.FicheroSalida){
                        mensajeRecibido = (aux.FicheroSalida) mensajeAux;
                        fos.write(mensajeRecibido.contenidoFichero,0, mensajeRecibido.bytesValidos);
                    }else{
                        
                        System.err.println("Error, el mensaje NO es de la clase esperada!!!");
                        break;
                    }
                    
                }while(!mensajeRecibido.isUltimoMensaje());
                fos.close();
                
            }else{
                System.err.println("Mensaje no esperado "
                            + mensaje.getClass().getName());
                    
            }
        }catch(Exception e){}
    }
    
}
