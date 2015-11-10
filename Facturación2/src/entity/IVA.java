/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Javier
 */
@Entity
public class IVA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private float recargo;

    public IVA() {
    }

    public IVA(int id, String tipo, float recargo) {
        this.id = id;
        this.tipo = tipo;
        this.recargo = recargo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getRecargo() {
        return recargo;
    }

    public void setRecargo(float recargo) {
        this.recargo = recargo;
    }
    
    
}
