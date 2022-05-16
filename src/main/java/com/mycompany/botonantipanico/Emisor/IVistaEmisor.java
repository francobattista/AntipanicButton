/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.botonantipanico.Emisor;

import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @author franc
 */
public interface IVistaEmisor {

    public void setActionListener(ActionListener listener);
    
    public void changeNotifications(HashMap<String,Boolean> hash);
    
    public void updateText(String txt);

    
}
