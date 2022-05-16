/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Emisor;
import excepciones.PortOrIpInvalidException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import models.Solicitud;


/**
 *
 * @author franc
 */
public class ControladorEmisor implements ActionListener
{
    
    private String puerto;
    private String IP;
    private int nroPuesto;
    
    private IVistaEmisor vista = null;
    private ConexionEmisor conexion = new ConexionEmisor(); //Pensar si va a ser una clase estatica.
    private ConfiguracionEmisor config = null;
               
    private  HashMap<String,Boolean> acciones = new HashMap();
    
    
    
    public ControladorEmisor(IVistaEmisor vista)
    {
        
        this.vista = vista;
        this.vista.setActionListener(this);
        
        this.config =new ConfiguracionEmisor(this);
        
        /*EMULA LA PERSISTENCIA DE LOS DATOS.*/
        this.acciones.put("Asistencia medica", false);
        this.acciones.put("Solicitud seguridad", true);
        this.acciones.put("Notificacion incendio", true);
        
        this.IP="192.168.0.31";
        this.puerto="1234";
        this.nroPuesto=1;
               
        
        this.setActions(this.acciones);
        if(this.validaConexion(IP, puerto))
            this.config.setMainConfiguration(this.acciones,this.IP,this.puerto,this.nroPuesto);
        else
            JOptionPane.showMessageDialog(null,"PUERTO o IP invalida");
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                
        String command = e.getActionCommand();
        System.out.println(command);
        if(command=="asistenciaMedica") //hacer equals
            this.makeRequest("Asistencia medica");
        else if(command.equals("solicitudSeguridad"))
            this.makeRequest("Solicitud seguridad");
        else if(command.equals("notificarIncendio"))
            this.makeRequest("Notificacion incendio");
        else if(command == "configurationOpen")
            this.config.show(true);           
        
    }
   
    
    public void makeRequest(String sol){
        Solicitud solicitud = new Solicitud(sol,new Date(), this.nroPuesto, this.IP,this.puerto);
        try {   
            this.conexion.makeRequest(this,this.IP, this.puerto, solicitud);
        } catch (PortOrIpInvalidException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
    }
    
    
    public HashMap<String,Boolean> getHash(){
        return this.acciones;
    }
    
    public void setPort(String port){
        this.puerto=port;
    }
    
    public String getPort()
    {
        return this.puerto;
    }
    
    public String getIP()
    {
        return this.IP;
    }
    
    public int getNroPuesto(){
        return this.nroPuesto;
    }
            
            
    public void setIP(String ip){
         this.IP=ip;
    }
    
    public void setNroPuesto(int nroPuesto){
        this.nroPuesto=nroPuesto;
    }
    
    public void setActions(HashMap<String,Boolean> hash)
    {
        this.vista.changeNotifications(hash);
    }
    
    public void updateComponents(){
        
    }
    
    public void response(String txt){
        this.vista.updateText(txt);
    }
    
    public boolean validaConexion(String ip, String port)
    {
        boolean rta = true;
        
        if(ip.equals("") || ip.equals(" ") || ip==null)// || ip.contains('0'..'9') )
            rta=false;
        if(port.equals("") || port.equals(" ") || port==null)
            rta=false;
               
        
        if(!(ip.matches("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) || !(port.matches("[0-9]*")))
            rta=false;

        return rta;
        
    }

}
