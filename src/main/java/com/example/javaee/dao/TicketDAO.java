package com.example.javaee.dao;

import com.example.javaee.data.Ticket;
import com.example.javaee.dto.SeatDto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class TicketDAO {
    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void save(Ticket ticket) {
        em.persist(ticket);
    }
    public Ticket getById (Long id) {
        /*return em.createQuery("SELECT f from Film f WHERE :id = f.id", Film.class)
                .setParameter("id", uuid)
                .getSingleResult();*/
        return em.find(Ticket.class, id);
    }

    public List<Ticket> getAll() {
        return em.createQuery("SELECT b from Ticket b", Ticket.class)
                .getResultList();
    }

    public void update(Ticket ticket) {
        em.merge(ticket);
    }

    public void deleteById(Long id) {
        Ticket ticket = em.find(Ticket.class, id);
        em.remove(ticket);
    }

    public List<SeatDto> getBusySeats(Long timetableId) {
        return em.createQuery("SELECT b from Ticket b WHERE :id = b.timetable.id", Ticket.class)
                .setParameter("id", timetableId)
                .getResultList()
                .stream().map((e) -> new SeatDto(e.getSeat(), e.getRow()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getVisitorTickets(Long visitorId) {
        return em.createQuery("SELECT b from Ticket b WHERE :id = b.visitor.id", Ticket.class)
                .setParameter("id", visitorId)
                .getResultList();
    }
}
