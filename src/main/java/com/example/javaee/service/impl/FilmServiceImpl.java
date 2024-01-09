package com.example.javaee.service.impl;

import com.example.javaee.dao.FilmDAO;
import com.example.javaee.data.Film;
import com.example.javaee.dto.FilmDto;
import com.example.javaee.service.FilmService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class FilmServiceImpl implements FilmService {
    @Inject
    FilmDAO filmDAO;

    @Override
    public void save(FilmDto filmDto) {
        filmDAO.save(filmDto.toEntity());
    }

    @Override
    public void update(FilmDto filmDto, Long id) {
        Film film = filmDto.toEntity();
        film.setId(id);
        filmDAO.update(film);
    }
    @Override
    public void delete(Long id) {
        filmDAO.deleteById(id);
    }
    @Override
    public FilmDto getById(Long id) {
        return filmDAO.getById(id).toDto();
    }
    @Override
    public List<FilmDto> getAll() {
        return filmDAO.getAll().stream().map(Film::toDto).collect(Collectors.toList());
    }
}
