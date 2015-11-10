/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.controller.conditions.Condition;
import entity.controller.exceptions.NonexistentEntityException;
import entity.controller.utils.Utils;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.controller.conditions.Condition.Operation;

/**
 *
 * @author Javier
 * @param <T>
 */
public class JpaController<T extends Serializable, KT> implements Serializable{
    // una clave primaria puede estar compuesta por varios campos.
    // por lo tanto una id puede ser una lista o un array
    // por lo cual deveríamos almacenar todos los campos con la anotación de Id
    // y usar el find(al que se le deve pasar un array por parámetro) para encontrar el entity adecuado.
    
    private final Class<T> clase;
    private EntityManagerFactory emf = null;
    
    
    public JpaController( Class<T> clase, EntityManagerFactory emf) {
        this.emf = emf;
        this.clase = clase;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            KT id;
            if (msg == null || msg.length() == 0) {
                id = (KT)Utils.getId(entity);
                if ( find( id ) == null) {
                    throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy( KT id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T entity;
            try {
                entity = em.getReference( clase, id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.", enfe);
            }
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void destroy( T ent ) throws NonexistentEntityException, IllegalAccessException{
        EntityManager em = null;
        KT id = (KT)Utils.getId(ent);
        destroy( id );
    }
    
    public T find( KT id ) {
        EntityManager em = getEntityManager();
        try {
            return em.find(clase, id);
        } finally {
            em.close();
        }
    }
    
    public List<T> findEntities() {
        return findEntities(true, -1, -1);
    }
    
    public List<T> findEntities( int maxResults, int firstResult ){
        return findEntities(false, maxResults, firstResult);
    }
    
    public List<T> findEntities( Condition... cons ) {
        return findEntities( true, -1, -1, cons );
    }
    
    public List<T> findEntities( int maxResults, int firstResult, Condition... cons ){
        return findEntities( false, maxResults, firstResult, cons );
    }
    
    private List<T> findEntities(boolean all, int maxResults, int firstResult, Condition... cons) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(clase);
            cq = cq.select(root);
            
            for( Condition con : cons ){
                
                Operation operation = con.getOperacion();
                if( operation != null ){

                    String name = con.getNombre();
                    Object valueO = con.getValue();
                    Number valueN = null;

                    if( valueO instanceof Number )
                        valueN = (Number)valueO;

                    switch( operation ){
                        case EQUALS:
                            cq.where( cb.equal( root.get(name), valueO ) );
                            break;
                        case GREATER_EQUALS:
                            cq.where( cb.ge(root.get(name), valueN ) );
                            break;
                        case GREATER:
                            cq.where( cb.gt(root.get(name), valueN ) );
                            break;
                        case LOWER:
                            cq.where( cb.lt(root.get(name), valueN ) );
                            break;
                        case LOWER_EQUALS:
                            cq.where( cb.le(root.get(name), valueN ) );
                    }
                }
            }
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getEntitiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(clase);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
