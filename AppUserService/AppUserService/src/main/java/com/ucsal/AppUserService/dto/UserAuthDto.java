package com.ucsal.AppUserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public record UserAuthDto(
        Long id,
        String email,
        String passwordHash,
        boolean enabled,
        boolean locked,
        String role
) {}
