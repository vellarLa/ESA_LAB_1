package com.example.javaee.dto;

import com.example.javaee.data.Film;
import com.example.javaee.data.Ticket;
import com.example.javaee.data.Timetable;
import com.example.javaee.data.Visitor;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class TicketDto {
    private Long id;
    private int seat;
    private int row;
    private double cost;
    private VisitorDto visitor;
    private TimetableDto timetable;

    public Ticket toEntity() {
        return new Ticket(null, this.seat, this.row, this.cost, this.visitor.toEntity(), this.timetable.toEntity());
    }

}
