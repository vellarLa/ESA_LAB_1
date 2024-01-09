package com.example.javaee.service;

import com.example.javaee.dto.VisitorDto;

import java.util.List;
import java.util.UUID;

public interface VisitorService {
    VisitorDto save(VisitorDto visitorDto);
    void update(VisitorDto visitorDto, Long id);
    void delete(Long id);
    VisitorDto getById(Long id);
    List<VisitorDto> getAll();
}
