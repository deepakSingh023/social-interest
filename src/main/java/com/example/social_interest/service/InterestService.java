package com.example.social_interest.service;

import com.example.social_interest.dto.InterestRequest;

public interface InterestService {
    void updateInterest(String userId, InterestRequest request);
}
