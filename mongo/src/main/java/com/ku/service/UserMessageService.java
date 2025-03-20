package com.ku.service;

import com.ku.entity.UserMessage;
import com.ku.repository.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMessageService {

    private final UserMessageRepository userMessageRepository;

    public Mono<Void> saveMessage(String userId, String content) {
        Instant now = Instant.now().atZone(ZoneOffset.UTC) // Convert to ZonedDateTime
                .toLocalDate() // Extract LocalDate (removes time)
                .atStartOfDay(ZoneOffset.UTC) // Set time to 00:00:00 UTC
                .toInstant();
        String id = userId + "#" + now.toString();

        UserMessage.MessageContent msg = UserMessage.MessageContent.builder()
                .msgId(UUID.randomUUID().toString())
                .timestamp(Timestamp.from(now))
                .content(content)
                .build();

        return userMessageRepository.findById(id)
                .defaultIfEmpty(UserMessage.builder()
                        .id(id)
                        .date(now)
                        .userId(userId)
                        .messages(new ArrayList<>())
                        .build())
                .flatMap(userMessage -> {
                    userMessage.getMessages().add(msg);
                    return userMessageRepository.save(userMessage);
                })
                .then();
    }

    public Flux<UserMessage> getMessages(String userId, Timestamp date) {
        return userMessageRepository.findByUserIdAndDate(userId, date);
    }
}
