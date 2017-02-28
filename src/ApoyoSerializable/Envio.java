/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApoyoSerializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author ruben
 */
public class Envio {
    private InetAddress dip;
    private int pto;
    private File file;
    private Socket tuberia;
    
    public Envio(InetAddress d, int p, File f){
        dip= d;
        pto = p;
        file= f;
    }
    
    public void enviaArchivo(){
        try(
                Socket con = new Socket (dip, pto);
                ObjectOutputStream OUT = new ObjectOutputStream(con.getOutputStream());
                ){
            //Mandamos el nombre del archivo pimero 
            aux.FicheroEntrada mensaje = new aux.FicheroEntrada();
            mensaje.setNombre_fichero(file.getName());
            //mensamos el nombre al servuidor
            OUT.writeObject(mensaje);
            //mandamos el fichero
            enviaFichero(OUT);
            
        }catch(Exception e){}
    } 
    
    public void enviaFichero(ObjectOutputStream oos){
        try{
            //Abrimos fichero
            FileInputStream fis = new FileInputStream(file);
            aux.FicheroSalida mensaje = new aux.FicheroSalida();
            mensaje.setNombre_fichero(file.getName());
            //leemos los bytes del fichero
            int leidos;
            do{
                leidos= fis.read(mensaje.contenidoFichero);
                if(leidos<1024){
                    
                    mensaje.setUltimoMensaje(true);
                    mensaje.bytesValidos= leidos;
                    
                    
                }else{
                    mensaje.setUltimoMensaje(false);
                    mensaje.bytesValidos=1024;
                    
                }
                //enviamos por el socket
                oos.writeObject(mensaje);
                if(mensaje.isUltimoMensaje(false)) break;
                //creamos un nuevo kensaje
                mensaje = new aux.FicheroSalida();
                mensaje.setNombre_fichero(file.getName());
                //volvemos a leer                             
            }while(leidos>-1);
            
        }catch(Exception e){}
    }
}
