package com.example.social_interest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.social_interest.repository.ReelRepository",
        mongoTemplateRef = "reelMongoTemplate"
)
public class ReelMongoConfig {

    @Bean
    public MongoDatabaseFactory postsMongoDbFactory(
            @Value("${spring.data.mongodb.reels.uri}") String uri) {
        return new SimpleMongoClientDatabaseFactory(uri);
    }

    @Bean(name = "reelMongoTemplate")
    public MongoTemplate reelMongoTemplate(
            MongoDatabaseFactory postsMongoDbFactory) {
        return new MongoTemplate(postsMongoDbFactory);
    }
}
