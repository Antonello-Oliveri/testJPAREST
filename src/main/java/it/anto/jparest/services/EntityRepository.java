/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.anto.jparest.services;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
/**
 *
 * @author Anto
 */
@Component
@Primary
public class EntityRepository implements IEntityRepository {
    
    private EntityManagerFactory emf;

    public EntityRepository() {
        try
        {
            emf = Persistence.createEntityManagerFactory("AntoPersistence");
           
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }
        
    }

    @Override
    public <T> void createEntity(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(entity); // Salva l'entità nel database
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public <T extends Object> T findEntity(Long id , Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(clazz, id);
        em.close();
        return entity;
    }
    @Override
    public <T extends Object> T find(Long id , Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(clazz, id);
        em.close();
        return entity;
    }
    public void close() {
        emf.close();
    }
}
