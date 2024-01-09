package com.example.javaee.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class SeatDto {
    private int seat;
    private int row;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        SeatDto seatDto = (SeatDto) obj;
        return seatDto.getSeat() == this.getSeat() && seatDto.getRow() == this.getRow();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + seat;
        result = prime * result + row;
        return result;
    }
}
