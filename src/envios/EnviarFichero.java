/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envios;

/**
 *
 * @author ruben
 */
public class EnviarFichero {
    public String nombreFichero;
    public boolean ultimoMensaje;
    public int bytesValidos;
    public final static int LONGITUD_MAXIMA=1024;
    public byte [] contenidoFicheros =  new byte[LONGITUD_MAXIMA];

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public boolean isUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(boolean ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public int getBytesValidos() {
        return bytesValidos;
    }

    public void setBytesValidos(int bytesValidos) {
        this.bytesValidos = bytesValidos;
    }

    public byte[] getContenidoFicheros() {
        return contenidoFicheros;
    }

    public void setContenidoFicheros(byte[] contenidoFicheros) {
        this.contenidoFicheros = contenidoFicheros;
    }
    
    
}
