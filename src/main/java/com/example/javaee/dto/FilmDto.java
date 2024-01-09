package com.example.javaee.dto;

import com.example.javaee.data.Film;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class FilmDto {
    private Long id;
    private String name;
    private String genre;
    private String country;
    private String director;

    public Film toEntity() {
        return new Film(this.id, this.name, this.genre, this.country, this.director);
    }
}
