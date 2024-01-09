package com.example.javaee.servlet;

import com.example.javaee.dto.FilmDto;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.dto.VisitorDto;
import com.example.javaee.service.FilmService;
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

@WebServlet(name = "filmServlet", value = "/film")
public class FilmServlet extends HttpServlet {
    @Inject
    FilmService filmService;

    @Inject
    TimetableService timetableService;
    @Inject
    VisitorService visitorService;


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        FilmDto filmDto = filmService.getById(Long.parseLong(request.getParameter("filmId")));
        List<TimetableDto> timetableDtos = timetableService.getTimetableForFilm(filmDto.getName());
        request.setAttribute("timetables", timetableDtos);
        request.setAttribute("film", filmDto);
        request.setAttribute("visitor", visitorService.getById(Long.parseLong(request.getParameter("visitorId"))));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
