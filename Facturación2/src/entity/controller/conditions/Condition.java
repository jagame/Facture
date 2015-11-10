/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller.conditions;

/**
 *
 * @author Javier
 */
public class Condition{
    private String nombre;
    private Operation operacion;
    private Object value;

    public Condition(String nombreCampo, Operation operacion, Object value) {
        
        this.nombre = nombreCampo;
        this.operacion = operacion;
        
        if( ! Operation.EQUALS.equals(operacion) ){
            if( value instanceof Number )
                this.value = value;
            else
                throw new RuntimeException(operacion.toString()+" is only available for Number values");
        }
        else{
            this.value = value;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Operation getOperacion() {
        return operacion;
    }

    public void setOperacion(Operation operacion) {
        this.operacion = operacion;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public enum Operation{
        EQUALS, GREATER_EQUALS, GREATER, LOWER, LOWER_EQUALS;
    }
}
