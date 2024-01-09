package com.example.javaee.service.impl;

import com.example.javaee.dao.VisitorDAO;
import com.example.javaee.data.Visitor;
import com.example.javaee.dto.VisitorDto;
import com.example.javaee.service.VisitorService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class VisitorServiceImpl implements VisitorService {
    @Inject
    VisitorDAO visitorDAO;

    @Override
    public VisitorDto save(VisitorDto visitorDto) {
        return visitorDAO.save(visitorDto.toEntity()).toDto();
    }
    @Override
    public void update(VisitorDto visitorDto, Long id) {
        Visitor visitor = visitorDto.toEntity();
        visitor.setId(id);
        visitorDAO.update(visitor);
    }
    @Override
    public void delete(Long id) {
        visitorDAO.deleteById(id);
    }
    @Override
    public VisitorDto getById(Long id) {
        return visitorDAO.getById(id).toDto();
    }
    @Override
    public List<VisitorDto> getAll() {
        return visitorDAO.getAll().stream().map(Visitor::toDto).collect(Collectors.toList());
    }
}
