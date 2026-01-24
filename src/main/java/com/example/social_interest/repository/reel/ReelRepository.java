package com.example.social_interest.repository.reel;

import com.example.social_interest.entity.Reel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReelRepository extends MongoRepository<Reel, String> {
}
