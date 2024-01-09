package com.example.javaee.servlet;

import com.example.javaee.data.Film;
import com.example.javaee.dto.FilmDto;
import com.example.javaee.dto.VisitorDto;
import com.example.javaee.service.FilmService;
import com.example.javaee.service.VisitorService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "visitorServlet", value = "/visitor")
public class VisitorServlet  extends HttpServlet {
    @Inject
    VisitorService visitorService;

    @Inject
    FilmService filmService;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        VisitorDto visitorDto = VisitorDto.builder()
                .name(request.getParameter("name"))
                .benefits(request.getParameter("benefits") != null)
                .build();
        visitorDto =  visitorService.save(visitorDto);

        List<FilmDto> films = filmService.getAll();
        request.setAttribute("visitor", visitorDto);
        request.setAttribute("filmsList", films);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
