/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Id;

/**
 *
 * @author Javier
 */
public class Utils {
    public static Object getId( Serializable obj ) throws IllegalAccessException{
        List id = new ArrayList(1);
        for( Field f : obj.getClass().getDeclaredFields() ){
            f.setAccessible(true);
            if( f.getAnnotation( Id.class ) != null ){
                // ZONA DE PELIGRO: Puede ser que pete al pedir que devuelva el valor como Object.class
                id.add(f.get(obj));
                // ZONA DE PELIGRO
            }
        }
        return id.size()==1?id.get(0):id.toArray();
    }
    
    public static Map<String, ? extends Object> getFieldsValues( Object o ) throws IllegalAccessException{
        Field[] fields = o.getClass().getFields();
        Map res = new HashMap();
        for( Field f : fields ){
            f.setAccessible(true);
            res.put( f.getName() ,f.get(o) );
        }
        
        return res;
    }
}
