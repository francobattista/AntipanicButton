/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models;

import com.mycompany.botonantipanico.Emisor.ControladorEmisor;
import excepciones.PortOrIpInvalidException;

/**
 *
 * @author franc
 */
public interface IEmisor {
    public void makeRequest(ControladorEmisor controlador,String IP, String port, Solicitud solicitud) throws PortOrIpInvalidException;
}
