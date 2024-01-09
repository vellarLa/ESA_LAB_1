package com.example.javaee.dao;

import com.example.javaee.data.Visitor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Stateless
public class VisitorDAO {
    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public Visitor save(Visitor visitor) {
        em.persist(visitor);
        em.flush();
        return visitor;
    }
    public Visitor getById (Long id) {
        /*return em.createQuery("SELECT f from Film f WHERE :id = f.id", Film.class)
                .setParameter("id", uuid)
                .getSingleResult();*/
        return em.find(Visitor.class, id);
    }

    public List<Visitor> getAll() {
        return em.createQuery("SELECT b from Visitor b", Visitor.class)
                .getResultList();
    }

    public void update(Visitor visitor) {
        em.merge(visitor);
    }

    public void deleteById(Long id) {
        Visitor visitor = em.find(Visitor.class, id);
        em.remove(visitor);
    }
}
