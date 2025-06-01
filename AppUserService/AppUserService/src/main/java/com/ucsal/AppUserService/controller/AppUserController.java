package com.ucsal.AppUserService.controller;


import com.maviniciusdev.back.registration.token.ConfirmationTokenService;
import com.ucsal.AppUserService.service.AppUserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AppUserController {
}
