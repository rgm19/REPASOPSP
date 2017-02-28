/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author ruben
 */
public class HiloServidor implements Runnable {

    Socket conex;
    File fichero;
    
    public HiloServidor(Socket sck){
        conex=sck;
    }
    
    
    @Override
    public void run() {
        try(
                ObjectInputStream ois = new ObjectInputStream(conex.getInputStream());
          ){
            
            //llega el nombre del archio primero
            Object mensaje = ois.readObject();
            if(mensaje instanceof PedirFicher){
                String nombre = ((PedirFicher)mensaje).getNombreFichero(); 
                System.out.println("Recibiendo fichero: "+nombre + "...");
                //Se abre un fichero para empezar a copiar lo que se reciba
                FileOutputStream fos = new FileOutputStream(nombre);
                EnviarFichero ficheroRecibido;
                Object ficheroAux;
                
                do{
                    //leemos el mensaje en una variable auxiliar 'mensajeAux'
                    ficheroAux=ois.readObject();
                    if(ficheroAux instanceof EnviarFichero){
                        ficheroRecibido = (EnviarFichero) ficheroAux;
                        fos.write(ficheroRecibido.contenidoFicheros,0,ficheroRecibido.bytesValidos);
                    }else{
                        System.err.println("Error, el mensaje no es dfe la clase esperada");
                        break;
                    }
                }while(!ficheroRecibido.isUltimoMensaje());
                
                System.out.println("Fichero \"" +nombre+ "\" recibido correctamente");
                    fos.close();
            }else{
                 System.err.println("Mensaje no esperado " + mensaje.getClass().getName());      
            }
        
        }catch(Exception e){}
    }
    
}
