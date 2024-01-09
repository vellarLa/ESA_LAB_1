package com.example.javaee.servlet;

import com.example.javaee.dto.SeatDto;
import com.example.javaee.dto.TicketDto;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.dto.VisitorDto;
import com.example.javaee.service.FilmService;
import com.example.javaee.service.TicketService;
import com.example.javaee.service.TimetableService;
import com.example.javaee.service.VisitorService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ticketServlet", value = "/ticket")
public class TicketServlet extends HttpServlet {
    @Inject
    TimetableService timetableService;
    @Inject
    VisitorService visitorService;
    @Inject
    FilmService filmService;
    @Inject
    TicketService ticketService;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        VisitorDto visitorDto = visitorService.getById(Long.parseLong(request.getParameter("visitorId")));
        TimetableDto timetableDto = timetableService.getById(Long.parseLong(request.getParameter("timetableId")));
        ticketService.buyTicket(visitorDto, timetableDto, new SeatDto(
                Integer.parseInt(request.getParameter("seat")),
                Integer.parseInt(request.getParameter("row"))
        ));
        List<SeatDto> seatDtos = ticketService.getFreeSeats(timetableDto);
        request.setAttribute("seats", seatDtos);
        request.setAttribute("timetable", timetableDto);
        request.setAttribute("film", filmService.getById(Long.parseLong(request.getParameter("filmId"))));
        request.setAttribute("visitor", visitorDto);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    public void destroy() {
    }
}