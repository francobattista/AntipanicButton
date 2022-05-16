/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models;

import com.mycompany.botonantipanico.Receptor.ControladorReceptor;
import excepciones.PeticionInvalidaException;
import excepciones.PortOrIpInvalidException;

/**
 *
 * @author franc
 */
public interface IReceptor {
    public void connectSocket(String port,ControladorReceptor controlador) throws PortOrIpInvalidException,PeticionInvalidaException;
    
}
