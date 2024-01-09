package com.example.javaee.dao;

import com.example.javaee.data.Film;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;

@Stateless
public class FilmDAO {

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void save(Film film) {
        em.persist(film);
    }
    public Film getById (Long id) {
        /*return em.createQuery("SELECT f from Film f WHERE :id = f.id", Film.class)
                .setParameter("id", uuid)
                .getSingleResult();*/
        return em.find(Film.class, id);
    }

    public Film getByName (String name) {
        return em.createQuery("SELECT f from Film f WHERE :name = f.name", Film.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Film> getAll() {
        return em.createQuery("SELECT f from Film f", Film.class)
                .getResultList();
    }

    public List<String> getAllNames() {
        return em.createQuery("SELECT b.name from Film b", String.class)
                .getResultList();
    }

    public void update(Film film) {
        em.merge(film);
    }

    public void deleteById(Long id) {
        Film film = em.find(Film.class, id);
        em.remove(film);
    }
}
