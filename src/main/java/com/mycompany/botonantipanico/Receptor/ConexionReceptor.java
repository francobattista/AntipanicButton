/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Receptor;

import excepciones.PeticionInvalidaException;
import excepciones.PortOrIpInvalidException;
import java.awt.Toolkit;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import models.IReceptor;
import models.Solicitud;

/**
 *
 * @author franc
 */
public class ConexionReceptor implements IReceptor
{
    
    
    private Toolkit tk = Toolkit.getDefaultToolkit ();
      
    
    public ConexionReceptor(){

        
    }
    
    
    
    @Override
    public void connectSocket(String port,ControladorReceptor controlador) throws PortOrIpInvalidException,PeticionInvalidaException
    {
        Toolkit tk = this.tk;
        if(controlador.validaConexion("0.0.0.0",port))
        {
            new Thread() 
            {
                public void run() 
                {
                    try 
                    {
                        System.out.println(Integer.parseInt(port));
                        ServerSocket s = new ServerSocket(Integer.parseInt(port));
                        System.out.println("Esperando conexiones en puerto " + port + "\n");
                        while (true) 
                        {
                            Socket soc = s.accept();
                            ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
                            Solicitud solicitud = (Solicitud) ois.readObject();
                            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
                            if(controlador.validaPeticion(solicitud.getNombre()))
                            {      
                                
                                solicitud.setPw(out);                               
                                controlador.updateComponents(solicitud);
                                tk.beep();
                            }
                            else
                            {
                                out.println("Rechazado");
                            }                            
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }

                }
            }.start();   
        }
        else
            throw new PortOrIpInvalidException("IP o PUERTO invalido");
    }
    
    
    public void enviarRecibido(Solicitud solicitud)
    {

        try
        {


            PrintWriter out = solicitud.getPw();
            out.println("Se atendio con exito la solicitud " + solicitud.getNombre());
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

    }
    
    
}

    

