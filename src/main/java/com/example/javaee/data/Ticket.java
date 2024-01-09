package com.example.javaee.data;

import com.example.javaee.dto.TicketDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "tickets", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "seat")
    private int seat;

    @Column(name = "row")
    private int row;

    @Column(name = "cost")
    private double cost;

    @ManyToOne(targetEntity = Visitor.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @ManyToOne(targetEntity = Timetable.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    public TicketDto toDto() {
        return TicketDto.builder()
                .id(this.id)
                .cost(this.cost)
                .seat(this.seat)
                .row(this.row)
                .visitor(this.visitor.toDto())
                .timetable(this.timetable.toDto())
                .build();
    }

}
