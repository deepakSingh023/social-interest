package com.example.social_interest.repository;

import com.example.social_interest.entity.UserInterest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestRepository extends MongoRepository<String, UserInterest> {
}
