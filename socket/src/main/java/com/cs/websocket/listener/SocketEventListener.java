package com.cs.websocket.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.listener.DataListener;
import com.cs.websocket.model.ChatObject;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.OnMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketEventListener {

    private final SocketIOServer server;

    private final RedissonClient redissonClient;

    @PostConstruct
    private void init() {
        server.addConnectListener(this::onConnect);
        server.addDisconnectListener(this::onDisconnect);
        server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {

                System.out.println(client.get("userName") + ":" + data.getMessage());
                // broadcast messages to all clients
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        });
    }

//    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("onConnect, client={}", client.getSessionId());

        RSet<String> connectedUsers = redissonClient.getSet("socketio:connectedUsers");
        connectedUsers.add(String.valueOf(client.getSessionId()));

        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        String roomName = "room_" + userId;
        client.joinRoom(roomName);

        boolean isManager = Boolean.parseBoolean(client.getHandshakeData().getSingleUrlParam("role"));
        if (isManager) {
            client.joinRoom("room_manager");
            log.info("manager join room, roomName={}", roomName);
        }
//        redissonClient.getSet("socketio:rooms").add(roomName);

        log.info("onConnect, {} join roomName={}", server.getAllClients().stream()
                .map(socketIOClient -> client.getAllRooms()).toList(), roomName);
    }

    public void onDisconnect(SocketIOClient client) {
        log.info("onDisconnect, client={}", client.getSessionId());
        client.disconnect();
    }

    @OnMessage
    public void onMessage(SocketIOClient client, String message) {
        log.info("onMessage, client={}", client);

        System.out.println(client.getSessionId() + ":" + message);
    }

}
