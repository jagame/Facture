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
public class Cliente extends EntityBase implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String nif;
    private String direccion;
    private String cp;
    private String localidad;
    private String provincia;
    private String telefono;
    private String fax;
    private String email;
    @ManyToOne
    private Cliente personaContacto;
    private boolean rec;

    public Cliente(){
        
    }

    public Cliente(String nombre, String nif, String direccion, String cp, String localidad, String provincia, String telefono, String fax, String email, Cliente personaContacto, boolean rec) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.fax = fax;
        this.email = email;
        this.personaContacto = personaContacto;
        this.rec = rec;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(Cliente personaContacto) {
        this.personaContacto = personaContacto;
    }

    public boolean isRec() {
        return rec;
    }

    public void setRec(boolean rec) {
        this.rec = rec;
    }
    
    
}
