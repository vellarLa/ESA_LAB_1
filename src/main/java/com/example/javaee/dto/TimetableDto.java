package com.example.javaee.dto;

import com.example.javaee.data.Film;
import com.example.javaee.data.Timetable;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class TimetableDto {
    private Long id;
    private FilmDto film;
    private int hall;
    private LocalDateTime date;

    public Timetable toEntity(){
        return new Timetable(this.id, film.toEntity(), hall, date);
    }
}
