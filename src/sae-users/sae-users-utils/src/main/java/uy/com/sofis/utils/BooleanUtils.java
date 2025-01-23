/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.utils;

import java.io.Serializable;

/**
 *
 * @author Gustavo Cirigliano
 */
public class BooleanUtils implements Serializable{
    
    
    public static Boolean sonIguales(Boolean val1, Boolean val2) {
        Boolean respuesta = false;
        if (val1!=null) {
            if (val2!=null) {
                respuesta=val1.equals(val2);
            } else{
               respuesta=false;
            }
        } else {
            respuesta=val2==null;
        }
        return respuesta;
    }
}
