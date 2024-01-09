package com.example.javaee.dao;

import com.example.javaee.data.Film;
import com.example.javaee.data.Timetable;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;

@Stateless
public class TimetableDAO {
    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void save(Timetable timetable) {
        em.persist(timetable);
    }
    public Timetable getById (Long id) {
        /*return em.createQuery("SELECT f from Film f WHERE :id = f.id", Film.class)
                .setParameter("id", uuid)
                .getSingleResult();*/
        return em.find(Timetable.class, id);
    }

    public List<Timetable> getByFilmName (String filmName) {
        return em.createQuery("SELECT f from Timetable f WHERE :name= f.film.name", Timetable.class)
                .setParameter("name", filmName)
                .getResultList();
    }

    public List<Timetable> getAll() {
        return em.createQuery("SELECT b from Timetable b", Timetable.class)
                .getResultList();
    }

    public void update(Timetable timetable) {
        em.merge(timetable);
    }

    public void deleteById(Long id) {
        Timetable timetable = em.find(Timetable.class, id);
        em.remove(timetable);
    }
}
