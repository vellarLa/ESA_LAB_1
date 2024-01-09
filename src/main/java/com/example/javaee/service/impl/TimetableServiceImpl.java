package com.example.javaee.service.impl;

import com.example.javaee.dao.TimetableDAO;
import com.example.javaee.data.Timetable;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.service.TimetableService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class TimetableServiceImpl implements TimetableService {
    @Inject
    TimetableDAO timetableDAO;

    @Override
    public void save(TimetableDto timetableDto) {
        timetableDAO.save(timetableDto.toEntity());
    }
    @Override
    public List<TimetableDto> getTimetableForFilm(String filmName) {
        return timetableDAO.getByFilmName(filmName)
                .stream().map(Timetable::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TimetableDto timetableDto, Long id) {
        Timetable timetable= timetableDto.toEntity();
        timetable.setId(id);
        timetableDAO.update(timetable);
    }
    @Override
    public void delete(Long id) {
        timetableDAO.deleteById(id);
    }
    @Override
    public TimetableDto getById(Long id) {
        return timetableDAO.getById(id).toDto();
    }
    @Override
    public List<TimetableDto> getAll() {
        return timetableDAO.getAll().stream().map(Timetable::toDto).collect(Collectors.toList());
    }
}
