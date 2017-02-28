/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envios;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author ruben
 */
public class Cliente {
    private File fichero;
    private InetAddress ip;
    private int puerto;
    
    public Cliente(File fichero, InetAddress ip, int puerto){
        this.fichero=fichero;
        this.ip=ip;
        this.puerto=puerto;
    }
    
    public void enviarArchivo(){
        try(
            Socket con = new Socket(ip, puerto);
            ObjectOutputStream OUT = new ObjectOutputStream(con.getOutputStream());
            )
        { 
            //Mandomos el nombre del archivo
            PedirFicher mensaje = new PedirFicher();
            mensaje.setNombreFichero(fichero.getName());
            //mandamos el nombre al servidor
            OUT.writeObject(mensaje);
            //Mandamos el fichero
            enviarFichero(OUT);
            
            
        }catch(Exception e){}
    }

    private void enviarFichero(ObjectOutputStream OUT) {
        try{
            //abrimos el fichero
            FileInputStream fis = new FileInputStream(fichero);
            EnviarFichero mensaje = new EnviarFichero();
            mensaje.setNombreFichero(fichero.getName());
            //leemos los primeros bytes del fichero
            int leidos;
            
            do{
                leidos = fis.read(mensaje.contenidoFicheros);
                if(leidos>1024){
                    mensaje.setUltimoMensaje(true);
                    mensaje.bytesValidos=leidos;
                }else{
                    mensaje.setUltimoMensaje(false);
                    mensaje.bytesValidos=1024;
                }
                
                //enviamos por el socket
                OUT.writeObject(mensaje);
                if(mensaje.isUltimoMensaje()) break;
                //creamos el nuevo mensaje
                mensaje = new EnviarFichero();
                mensaje.setNombreFichero(fichero.getCanonicalPath());
                //volvemos a leer
            }while(leidos>-1);
            
            
        }catch(Exception e){}
    }
}
