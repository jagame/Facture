/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Remesa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vto;
    private LocalDateTime fechaCreacion;
    private String descripcion;
    private String C19;

    public Remesa() {
    }

    public Remesa(long id, String vto, LocalDateTime fechaCreacion, String descripcion, String C19) {
        this.id = id;
        this.vto = vto;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.C19 = C19;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVto() {
        return vto;
    }

    public void setVto(String vto) {
        this.vto = vto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getC19() {
        return C19;
    }

    public void setC19(String C19) {
        this.C19 = C19;
    }
    
    
}
