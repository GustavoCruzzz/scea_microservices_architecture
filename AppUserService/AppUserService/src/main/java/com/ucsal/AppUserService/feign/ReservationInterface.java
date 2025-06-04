package com.ucsal.AppUserService.feign;

import com.ucsal.AppUserService.dto.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ReservationService", url = "http://localhost:9082")
public interface ReservationInterface {
    @GetMapping("/api/v1/reservations/user/{professorId}")
    List<ReservationDTO> getReservationsByUser(@PathVariable("id") Long id);
}
