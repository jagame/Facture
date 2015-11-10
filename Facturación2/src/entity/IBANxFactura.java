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
public class IBANxFactura implements Serializable{
    @Id
    @ManyToOne
    private IBAN iban;
    @Id
    @ManyToOne
    private Factura factura;

    public IBANxFactura() {
    }

    public IBANxFactura(IBAN iban, Factura factura) {
        this.iban = iban;
        this.factura = factura;
    }

    public IBAN getIban() {
        return iban;
    }

    public void setIban(IBAN iban) {
        this.iban = iban;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    
}
