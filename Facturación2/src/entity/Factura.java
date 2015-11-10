/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Javier
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint( columnNames={"numero"} ) )
@CascadeOnDelete
public class Factura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    private String numero;
    private LocalDateTime fecha;
    private String comentario;
    private float irpf;
    private String domicilioCobro;
    @ManyToOne
    private IVA iva;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private ModoPago modoPago;
    @ManyToOne
    private Remesa remesa;

    public Factura() {
    }

    public Factura(String tipo, String numero, LocalDateTime fecha, String comentario, float irpf, String domicilioCobro, IVA iva, Cliente cliente, ModoPago modoPago, Remesa remesa) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
        this.fecha = fecha;
        this.comentario = comentario;
        this.irpf = irpf;
        this.domicilioCobro = domicilioCobro;
        this.iva = iva;
        this.cliente = cliente;
        this.modoPago = modoPago;
        this.remesa = remesa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getIrpf() {
        return irpf;
    }

    public void setIrpf(float irpf) {
        this.irpf = irpf;
    }

    public String getDomicilioCobro() {
        return domicilioCobro;
    }

    public void setDomicilioCobro(String domicilioCobro) {
        this.domicilioCobro = domicilioCobro;
    }

    public IVA getIva() {
        return iva;
    }

    public void setIva(IVA iva) {
        this.iva = iva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ModoPago getModoPago() {
        return modoPago;
    }

    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    public Remesa getRemesa() {
        return remesa;
    }

    public void setRemesa(Remesa remesa) {
        this.remesa = remesa;
    }
    
    
}
