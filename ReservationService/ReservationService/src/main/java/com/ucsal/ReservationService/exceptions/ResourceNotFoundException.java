package com.ucsal.ReservationService.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String msg) { super(msg); }
}
