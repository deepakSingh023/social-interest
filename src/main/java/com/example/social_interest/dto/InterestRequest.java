package com.example.social_interest.dto;


import lombok.Data;

@Data
public class InterestRequest {

    private String userId;

    private String reelId;

    private String event;
}
