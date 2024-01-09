package com.example.javaee.service.impl;

import com.example.javaee.dao.TicketDAO;
import com.example.javaee.data.Ticket;
import com.example.javaee.dto.*;
import com.example.javaee.service.TicketService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class TicketServiceImpl implements TicketService {
    private final Double BENEFIT_COST = 200.00;
    private final Double COMMON_COST = 310.00;

    private final Integer MAX_SEAT_NUM = 3;
    private final Integer MAX_ROW_NUM = 3;

    @Inject
    TicketDAO ticketDAO;

    @Override
    public void save(TicketDto ticketDto) {
        ticketDAO.save(ticketDto.toEntity());
    }
    @Override
    public void buyTicket(VisitorDto visitorDto, TimetableDto timetableDto, SeatDto seatDto) {
        TicketDto ticketDto = TicketDto.builder()
                .visitor(visitorDto)
                .timetable(timetableDto)
                .seat(seatDto.getSeat())
                .row(seatDto.getRow())
                .cost(visitorDto.getBenefits() ?  BENEFIT_COST : COMMON_COST)
                .build();
        save(ticketDto);
    }
    @Override
    public List<SeatDto> getFreeSeats(TimetableDto timetableDto) {
        List<SeatDto> freeSeats = new ArrayList<>();
        List<SeatDto> busySeats = ticketDAO.getBusySeats(timetableDto.getId());
        for (int row = 1; row <= MAX_ROW_NUM; row++) {
            for (int seat = 1; seat <= MAX_SEAT_NUM; seat++) {
                SeatDto seatDto = new SeatDto(seat, row);
                if (! busySeats.contains(seatDto))
                    freeSeats.add(seatDto);
            }
        }
        return freeSeats;
    }
    @Override
    public List<TicketDto> getVisitorTickets(VisitorDto visitorDto) {
        return ticketDAO.getVisitorTickets(visitorDto.getId())
                .stream().map(Ticket::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public void update(TicketDto ticketDto, Long id) {
        Ticket ticket = ticketDto.toEntity();
        ticket.setId(id);
        ticketDAO.update(ticket);
    }
    @Override
    public void delete(Long id) {
        ticketDAO.deleteById(id);
    }
    @Override
    public TicketDto getById(Long id) {
        return ticketDAO.getById(id).toDto();
    }
    @Override
    public List<TicketDto> getAll() {
        return ticketDAO.getAll().stream().map(Ticket::toDto).collect(Collectors.toList());
    }


}
