/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.botonantipanico.Receptor;


/**
 *
 * @author franc
 */
public class MainReceptor {
        
    
        public static void main(String[] args)
        {
            vistaReceptor vista = new vistaReceptor();
            ControladorReceptor controlador = new ControladorReceptor(vista);
            vista.setVisible(true);
    }
    
    
    
}
