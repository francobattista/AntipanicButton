/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author franc
 */
public class Solicitud implements Serializable
{
    private String nombre;
    private Date fecha;
    private int nroPuesto;
    private PrintWriter pw;
    private String IP;
    private String port;
    

    public Solicitud(String nombre, Date fecha, int nroPuesto, String IP, String port){
        this.nombre=nombre;
        this.fecha=fecha;
        this.nroPuesto=nroPuesto;
        this.IP=IP;
        this.port=port;
       
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
       
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNroPuesto() {
        return nroPuesto;
    }

    public void setNroPuesto(int nroPuesto) {
        this.nroPuesto = nroPuesto;
    }
    
    public String getInfo(){
       return "Nombre de la solicitud: " + this.nombre + " Fecha: " + this.fecha + " Numero de puesto: " + this.nroPuesto;
    }

    
    @Override
    public String toString(){
        return this.nombre;
    }
    
}
