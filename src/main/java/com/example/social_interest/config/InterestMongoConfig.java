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
        basePackages = "com.example.social_interest.repository.InterestRepository",
        mongoTemplateRef = "interestMongoTemplate"
)
public class InterestMongoConfig {

    @Bean
    @Primary
    public MongoDatabaseFactory postsMongoDbFactory(
            @Value("${spring.data.mongodb.interests.uri}") String uri) {
        return new SimpleMongoClientDatabaseFactory(uri);
    }

    @Bean(name = "interestMongoTemplate")
    @Primary
    public MongoTemplate interestMongoTemplate(
            MongoDatabaseFactory postsMongoDbFactory) {
        return new MongoTemplate(postsMongoDbFactory);
    }
}
