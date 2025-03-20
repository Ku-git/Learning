package com.ku.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "user_messages")
public class UserMessage {

    @Id
    private String id; // userId#date
    private String userId;
    private Instant date;

    @Builder.Default
    private List<MessageContent> messages = new ArrayList<>();

    @Data
    @Builder
    public static class MessageContent {
        private String msgId;
        private Date timestamp;
        private String content;
    }
}



