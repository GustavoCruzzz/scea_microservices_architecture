package com.ucsal.ReservationService.feign;

import com.ucsal.ReservationService.Dtos.AcademicSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SPACESERVICE")
public interface SpaceServiceClient {

    @GetMapping("/api/v1/spaces/{id}")
    AcademicSpaceDTO getSpaceById(@PathVariable("id") Long id);
}
