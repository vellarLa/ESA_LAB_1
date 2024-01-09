package com.example.javaee.data;

import com.example.javaee.dto.FilmDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "films", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "country")
    private String country;

    @Column(name = "director")
    private String director;

    public FilmDto toDto() {
        return FilmDto.builder()
                .id(this.id)
                .name(this.name)
                .country(this.country)
                .director(this.director)
                .genre(this.genre)
                .build();
    }
}
