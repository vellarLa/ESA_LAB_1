package com.example.javaee.data;

import com.example.javaee.dto.TimetableDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "timetable", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timetable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Film.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "hall")
    private int hall;

    @Column(name = "date")
    private LocalDateTime date;

    public TimetableDto toDto() {
        return TimetableDto.builder()
                .id(this.id)
                .film(this.film.toDto())
                .hall(this.hall)
                .date(this.date)
                .build();
    }
}
