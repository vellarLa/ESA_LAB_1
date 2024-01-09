package com.example.javaee.service;

import com.example.javaee.dto.SeatDto;
import com.example.javaee.dto.TicketDto;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.dto.VisitorDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    void save(TicketDto ticketDto);
    void buyTicket(VisitorDto visitorDto, TimetableDto timetableDto, SeatDto seatDto);
    List<SeatDto> getFreeSeats(TimetableDto timetableDto);
    List<TicketDto> getVisitorTickets(VisitorDto visitorDto);
    void update(TicketDto ticketDto, Long id);
    void delete(Long id);
    TicketDto getById(Long id);
    List<TicketDto> getAll();
}
