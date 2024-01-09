package com.example.javaee.dto;

import com.example.javaee.data.Visitor;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class VisitorDto {
    private Long id;
    private String name;
    private Boolean benefits;

    public Visitor toEntity() {
        return new Visitor(this.id, this.name, this.benefits);
    }
}
