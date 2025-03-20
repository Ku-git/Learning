package com.ku.repository;

import com.ku.entity.UserMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
public interface UserMessageRepository extends ReactiveMongoRepository<UserMessage, String> {

    Flux<UserMessage> findByUserIdAndDate(String userId, Timestamp date);
}
