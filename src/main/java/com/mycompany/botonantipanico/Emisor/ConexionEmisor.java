/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Emisor;


import excepciones.PortOrIpInvalidException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import models.IEmisor;
import models.Solicitud;

/**
 *
 * @author franc
 */
public class ConexionEmisor implements IEmisor
{
 
     
    public ConexionEmisor()
    {
        
    }
    
    /*Metodo que hace la conexion con el receptor cada vez que se envia una solicitud.*/
    /*Envia el mensaje a traves del IN, en este caso el mensaje sera el tipo de solicitud.*/
    
    @Override
    public void makeRequest(ControladorEmisor controlador,String IP, String port, Solicitud solicitud) throws PortOrIpInvalidException
    {
        if(controlador.validaConexion(IP,port))
        {
            new Thread()
            {
                public void run()
                {
                    try 
                    {
                        System.out.println(IP + port);
                        Socket socket = new Socket(IP,Integer.parseInt(port));
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(solicitud);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                        String msg = in.readLine();
                        controlador.response(msg);
            
                        if(!msg.equals("Rechazado"))
                        {
                            controlador.response(in.readLine()); //si no se rechazo, que lea el segundo mensaje, sino que cierre.
                            oos.close();
                            this.stop();
                        }
                        else
                        {
                            controlador.response("La peticion:" + solicitud.getNombre() + "no puede ser enviada!");
                            oos.close();
                            this.stop();
                        }
                                                
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error, no hay conexiones esperando al puerto ingresado");
                    }
                }
            
            }.start();
       

        }
        else
            throw new PortOrIpInvalidException("PUERTO o IP invalido");
    }
    

        
    
    
}
