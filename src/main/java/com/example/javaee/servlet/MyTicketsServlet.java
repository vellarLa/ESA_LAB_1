package com.example.javaee.servlet;

import com.example.javaee.dto.SeatDto;
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

@WebServlet(name = "myTicketServlet", value = "/my-ticket")
public class MyTicketsServlet extends HttpServlet {
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
        request.setAttribute("visitor", visitorDto);
        request.setAttribute("myTickets", ticketService.getVisitorTickets(visitorDto));
        if (request.getParameter("filmId") != null) {
            request.setAttribute("film", filmService.getById(Long.parseLong(request.getParameter("filmId"))));
        }
        if (request.getParameter("timetableId") != null) {
            TimetableDto timetableDto = timetableService.getById(Long.parseLong(request.getParameter("timetableId")));
            List<SeatDto> seatDtos = ticketService.getFreeSeats(timetableDto);
            request.setAttribute("seats", seatDtos);
            request.setAttribute("timetable", timetableDto);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VisitorDto visitorDto = visitorService.getById(Long.parseLong(request.getParameter("visitorId")));
        TimetableDto timetableDto = timetableService.getById(Long.parseLong(request.getParameter("timetableId")));
        ticketService.delete(Long.parseLong(request.getParameter("ticketToDelete")));
        List<SeatDto> seatDtos = ticketService.getFreeSeats(timetableDto);
        request.setAttribute("seats", seatDtos);
        request.setAttribute("timetable", timetableDto);
        request.setAttribute("film", filmService.getById(Long.parseLong(request.getParameter("filmId"))));
        request.setAttribute("visitor", visitorDto);
        request.setAttribute("myTickets", ticketService.getVisitorTickets(visitorDto));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}