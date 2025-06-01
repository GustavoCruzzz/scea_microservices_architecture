package com.ucsal.AppUserService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "RESERVATIONSERVICE")
public interface ReservationInterface {

    // Pega uma reserva por Id :p
    @GetMapping("/reservations/{id}")
    ResponseEntity<Map<String, Object>> getReservationById(@PathVariable Long id);

    // Pega todas reservas do usuario por Id :3
    @GetMapping("/reservations/user/{userId}")
    ResponseEntity<List<Map<String, Object>>> getReservationsByUser(@PathVariable Long userId);
}
