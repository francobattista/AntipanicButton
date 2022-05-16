/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.botonantipanico.Receptor;

import java.awt.event.ActionListener;
import models.Solicitud;

/**
 *
 * @author franc
 */
public interface IVistaReceptor {
    public void setActionListener(ActionListener listener);
    
     public void nuevaSolicitud(Solicitud sol);
    
     public void eliminaSolicitud(Solicitud sol);
     
     public Solicitud getSolicitudSelected();
}
