package com.ucsal.ReservationService.exceptions;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String msg) { super(msg); }
}
