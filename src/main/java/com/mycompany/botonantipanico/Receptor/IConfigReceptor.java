/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.botonantipanico.Receptor;

import java.util.HashMap;

/**
 *
 * @author franc
 */
public interface IConfigReceptor {
    
    public String getPort();
    
    public void setActions();
    
    public void setMainConfiguration(HashMap<String,Boolean> hash,String port);
    
}
