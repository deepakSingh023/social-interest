package com.example.social_interest.config;

import org.springframework.beans.factory.annotation.Qualifier;
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
        basePackages = "com.example.social_interest.repository.interest",
        mongoTemplateRef = "interestMongoTemplate"
)

public class InterestMongoConfig {

    @Bean(name = "interestMongoDbFactory")
    @Primary
    public MongoDatabaseFactory interestMongoDbFactory(
            @Value("${spring.data.mongodb.interests.uri}") String uri) {
        return new SimpleMongoClientDatabaseFactory(uri);
    }

    @Bean(name = "interestMongoTemplate")
    @Primary
    public MongoTemplate interestMongoTemplate(
            @Qualifier("interestMongoDbFactory") MongoDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }
}
