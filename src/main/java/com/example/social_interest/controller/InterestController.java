package com.example.social_interest.controller;

import com.example.social_interest.dto.InterestRequest;
import com.example.social_interest.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @PostMapping("/update")
    public ResponseEntity<Void> updateInterest(
            @RequestBody InterestRequest request

    ) {
        interestService.updateInterest(request.getUserId(), request);
        return ResponseEntity.noContent().build();
    }
}

