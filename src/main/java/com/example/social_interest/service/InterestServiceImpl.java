package com.example.social_interest.service;

import com.example.social_interest.dto.InterestRequest;
import com.example.social_interest.entity.InterestScore;
import com.example.social_interest.entity.Reel;
import com.example.social_interest.entity.UserInterest;
import com.example.social_interest.repository.interest.InterestRepository;
import com.example.social_interest.repository.reel.ReelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final ReelRepository reelRepository;
    private final InterestRepository interestRepository;

    @Override
    public void updateInterest(String userId, InterestRequest request) {

        Reel reel = reelRepository.findById(request.getReelId())
                .orElseThrow(() -> new RuntimeException("Reel not found"));

        Set<String> semanticTags = reel.getSemanticTags();
        if (semanticTags == null || semanticTags.isEmpty()) {
            return; // nothing to learn
        }


        UserInterest userInterest = interestRepository
                .findByUserId(userId)
                .orElseGet(() -> {
                    UserInterest ui = new UserInterest();
                    ui.setUserId(userId);
                    ui.setLastUpdated(Instant.now());
                    return ui;
                });

        Instant now = Instant.now();


        for (String tag : semanticTags) {

            InterestScore score = userInterest.getInterests()
                    .getOrDefault(tag, new InterestScore(0.1, now));


            long hoursPassed = Duration
                    .between(score.getLastUpdated(), now)
                    .toHours();

            double decayFactor = Math.exp(-0.03 * hoursPassed);
            double decayedScore = score.getScore() * decayFactor;


            double boostedScore = decayedScore + boostForEvent(request.getEvent());


            boostedScore = Math.max(0.0, Math.min(1.0, boostedScore));

            score.setScore(boostedScore);
            score.setLastUpdated(now);

            userInterest.getInterests().put(tag, score);
        }


        userInterest.setLastUpdated(now);
        interestRepository.save(userInterest);
    }

    // 🎯 Event → boost mapping
    private double boostForEvent(String event) {
        return switch (event) {
            case "WATCH_50" -> 0.06;
            case "WATCH_90" -> 0.12;
            case "LIKE"     -> 0.20;
            case "SHARE"    -> 0.30;
            default         -> 0.03;
        };
    }
}
