/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Receptor;

import excepciones.SolicitudVaciaException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JOptionPane;
import models.Solicitud;


/**
 *
 * @author franc
 */
public class ControladorReceptor implements ActionListener
{

    private IVistaReceptor vista = null;
    
    private ConexionReceptor conexion = new ConexionReceptor(); //Pensar si va a ser una clase estatica 
    
    private ConfiguracionReceptor config = null;
    
            
    private String puerto=null;
    
    private boolean conectionOn = false;
    
    private HashMap<String,Boolean> acciones = new HashMap<String,Boolean>();
    
    public ControladorReceptor(IVistaReceptor vista)
    {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.config = new ConfiguracionReceptor(this);
        
        /*EMULA PERSISTENCIA */
        
        this.puerto="1234";
               
        this.acciones.put("Asistencia medica",true);
        this.acciones.put("Solicitud seguridad",true);
        this.acciones.put("Notificacion incendio",true);
        this.config.setMainConfiguration(acciones,this.puerto);
       
    
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try {
            String command = e.getActionCommand(); //usar el equals.
            if(command.equals("iniciarConexion"))
            {
                if(this.conectionOn==false && this.puerto!=null && !this.puerto.equals("")) //despues debo usar un metodo para validar el puerto y q no sea numerico          
                {
                    this.setConectionOn(true);  
                    this.conexion.connectSocket(puerto, this);
                }
                else
                    JOptionPane.showMessageDialog(null, "ERROR, ya hay una conexion para este puerto o sus datos son incorrectos. ");
            }
            else if(command.equals("eliminarSolicitud"))
                this.generarRecibido();
            else if(command.equals("configurationOpen"))
                this.config.show(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    public HashMap<String,Boolean> getHash(){
        return this.acciones;
    }
    
    public void setConectionOn(boolean cnt){
        this.conectionOn=cnt;
    }
    
    public boolean getConectionOn(){
        return this.conectionOn; 
    }
    
    public void setPort(String port){
        this.puerto=port;
    }
            
                
    
    public void updateComponents(Solicitud sol){
        this.vista.nuevaSolicitud(sol);
    }
    
    public void generarRecibido() throws SolicitudVaciaException
    {
        Solicitud sol = this.vista.getSolicitudSelected();
        if(sol!=null)
        {
            this.conexion.enviarRecibido(sol);
            this.vista.eliminaSolicitud(sol);
        }
        else
            throw new SolicitudVaciaException("ERROR, no selecciono solicitud");
    }
   
    
    public boolean validaConexion(String ip, String port)
    {
        boolean rta = true;
        
        if(ip.equals("") || ip.equals(" ") || ip==null)
            rta=false;
        if(port.equals("") || port.equals(" ") || port==null)
            rta=false;
               
        
        if(!(ip.matches("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) || !(port.matches("[0-9]*")))
            rta=false;

        return rta;
        
    }
    
    public boolean validaPeticion(String c){
        boolean rta= this.acciones.get(c);
        return rta; //validar no existencia.
    }
    
      public void setActions(HashMap<String,Boolean> hash)
    {
        this.acciones = hash;
    }

    public String getPort() {
        return this.puerto;
    }
    
}
