/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.controller.utils.Utils;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Javier
 */
public abstract class EntityBase implements Serializable{

    @Override
    public boolean equals(Object o) {
        Serializable other = (Serializable)o;
        Map<String,? extends Object> fieldsThis;
        Map<String,? extends Object> fieldsOther;
        boolean res = true;
        try{
            fieldsThis = Utils.getFieldsValues(this);
            fieldsOther = Utils.getFieldsValues(other);
            for( String s : fieldsThis.keySet() ){
                if( ! fieldsThis.get(s).equals(fieldsOther.get(s)) )
                    res = false;
            }
            
        }catch(IllegalAccessException e){
            throw new RuntimeException( "This class or the parameter Object dont have one id, they must implements @Entity if you want to use EntityBase.equals()" );
        }catch(NullPointerException e){
            throw new NullPointerException("Someone dont have a id value, see if this objects are saved and loaded from the DataBase");
        }
        return res;
    }

    @Override
    public int hashCode() {
        Object id;
        int res = 0;
        try{
            id = Utils.getId(this);
            if( id instanceof Object[] ){
                for( Object o : (Object[])id )
                    res += id.hashCode();
            }
            else
                res = id.hashCode();
        }catch(IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return res;
    }
    
    
    
}
