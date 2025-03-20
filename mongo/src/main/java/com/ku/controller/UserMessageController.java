package com.ku.controller;

import com.ku.entity.UserMessage;
import com.ku.request.ChatRequest;
import com.ku.service.UserMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class UserMessageController {

    private final UserMessageService userMessageService;

    @PostMapping("/send/{userId}")
    public Mono<Void> sendMessage(@PathVariable String userId, @RequestBody ChatRequest request) {

        return userMessageService.saveMessage(userId, request.getContent());
    }

    @GetMapping("/history/{userId}/{date}")
    public Flux<UserMessage> getHistory(@PathVariable String userId, @PathVariable String date) {
        Instant instant = Instant.parse(date).atZone(ZoneOffset.UTC) // Convert to ZonedDateTime
                .toLocalDate() // Extract LocalDate (removes time)
                .atStartOfDay(ZoneOffset.UTC) // Set time to 00:00:00 UTC
                .toInstant();
        return userMessageService.getMessages(userId, Timestamp.from(instant));
    }


}
