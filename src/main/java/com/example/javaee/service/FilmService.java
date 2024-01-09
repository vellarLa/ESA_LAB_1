package com.example.javaee.service;

import com.example.javaee.dto.FilmDto;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    void save(FilmDto filmDto);
    void update(FilmDto filmDto, Long id);
    void delete(Long id);
    FilmDto getById(Long id);
    List<FilmDto> getAll();
}
