/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Emisor;

/**
 *
 * @author franc
 */
public class MainEmisor 
{
    
    public static void main(String[] args){
        vistaEmisor vista = new vistaEmisor();
        ControladorEmisor controlador = new ControladorEmisor(vista);
        vista.setVisible(true);
    }
    
    
}
