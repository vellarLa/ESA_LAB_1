package com.example.javaee.service;

import com.example.javaee.dto.TimetableDto;

import java.util.List;
import java.util.UUID;

public interface TimetableService {
    void save(TimetableDto timetableDto);
    List<TimetableDto> getTimetableForFilm(String filmName);
    void update(TimetableDto timetableDto, Long id);
    void delete(Long id);
    TimetableDto getById(Long id);
    List<TimetableDto> getAll();
}
