package com.example.javaee.data;

import com.example.javaee.dto.VisitorDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "visitors", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visitor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "benefits")
    private Boolean benefits;

    public VisitorDto toDto() {
        return VisitorDto.builder()
                .id(this.id)
                .name(this.name)
                .benefits(this.benefits)
                .build();
    }
}
