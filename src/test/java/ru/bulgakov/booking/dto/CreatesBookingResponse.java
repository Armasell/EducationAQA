package ru.bulgakov.booking.dto;

import lombok.Data;

@Data
public class CreatesBookingResponse {

    private Integer bookingid;
    private CreateBookingDto booking;
}
