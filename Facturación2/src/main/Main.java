/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Cliente;
import entity.Factura;
import entity.controller.JpaController;
import entity.controller.conditions.Condition;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Javier
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        EntityManager em = Persistence.createEntityManagerFactory("baseDeDatos").createEntityManager();
//      INSERT DE LA TABLA CLIENTE Y FACTURA
//        Cliente c = new Cliente("Antonio", "76753248v", null, null, null, null, null, null, null, null, false);
//        Factura f = new Factura("Factura", "F1", LocalDateTime.now(), null, 0.0F, null, null, c, null, null);
//        em.getTransaction().begin();
//        em.persist(c);
//        em.getTransaction().commit();
//        em.getTransaction().begin();
//        em.persist(f);
//        em.getTransaction().commit();  
        
//        SELECT DE LA TABLA FACTURA        
//        List<Factura> lista = em.createQuery("SELECT f FROM Factura f").getResultList();
//        em.getTransaction().begin();
//        lista.stream().forEach((e)->{
//            System.out.println(e);
//            em.remove(e.getCliente());
//            em.remove(e);
//        });
//        em.getTransaction().commit();
        
//      SELECT A TRAVES DE JPACONTROLLER
        /**
         * Esto es mas raro que un gato verde: si no pongo en la declaración de jpa Y EN SU INICIALIZACIÓN la clase adecuada, luego el compilador se traga cosas como esta:
         * JpaController jpa = new JpaController(Factura.class, Persistence.createEntityManagerFactory("baseDeDatos"));
         * List<Cliente> lista2 = jpa.findEntities(); // NO DA ERROR DE COMPILACIÓN!
         * // o
         * JpaController<Cliente> jpa = new JpaController( Factura.class, Persistence.createEntityManagerFactory("baseDeDatos") ); // NO DA ERROR DE COMPILACIÓN!
         * List<Cliente> lista2 = jpa.findEntities(); // NI AQUÍ! LO DARÁ EN TIEMPO DE EJECUCIÓN!!!
         */
        JpaController<Cliente, Integer> jpa = new JpaController( Cliente.class, Persistence.createEntityManagerFactory("baseDeDatos") );
        
        List<Cliente> lista2 = jpa.findEntities();
        
        for( Cliente c1 : lista2 ){
            System.out.print( c1.getNombre() );
            
            Condition con = new Condition("personaContacto", Condition.Operation.EQUALS, c1 );
            List<Cliente> personaContactoIsC1 = jpa.findEntities(con);
            for( Cliente client : personaContactoIsC1 ){
                client.setPersonaContacto(null);
                jpa.edit(client);
            }
            con = new Condition("cliente", Condition.Operation.EQUALS, c1);
            JpaController<Factura, Integer> jpaF = new JpaController(Factura.class, Persistence.createEntityManagerFactory("baseDeDatos"));
            List<Factura> facturaCliente = jpaF.findEntities(con);
            for( Factura fac : facturaCliente ){
                fac.setCliente(null);
                jpaF.edit(fac);
            }
            jpa.destroy(c1);
            System.out.println();
        }
        /**
         * 14/10/2015 : Creado JpaController, siguiente paso: comprobar todos los método y crear las ventanas
         */
    }
    
}
