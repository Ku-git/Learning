package com.cs.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Value("${socketio.host:127.0.0.1}")
    private String host;

    @Value("${socketio.port:9092}")
    private Integer port;

    @Value("${socketio.bossCount:1}")
    private int bossCount;

    @Value("${socketio.httpCompression:true}")
    private boolean httpCompression;

    @Value("${socketio.websocketCompression:true}")
    private boolean websocketCompression;

    @Value("${socketio.workCount:1}")
    private int workCount;

    @Value("${socketio.allowCustomRequests:false}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout:1000}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout:6000}")
    private int pingTimeout;

    @Value("${socketio.pingInterval:25000}")
    private int pingInterval;

    @Bean("customSocketConfig")
    public com.corundumstudio.socketio.Configuration customSocketConfig(RedissonClient redissonClient) {

        SocketConfig socketConfig = new SocketConfig();

        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9092);
        config.setHttpCompression(httpCompression);
        config.setWebsocketCompression(websocketCompression);
        //若使用 Redis Cluster，則 bossCount 可以與 Redis 節點數量無關，因為 Redis Cluster 會自動管理負載均衡。
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        config.setSocketConfig(socketConfig);

        //TODO: change to redis cluster
        config.setStoreFactory(new RedissonStoreFactory(redissonClient));

        return config;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(redissonConfig);
        return redissonClient;
    }

    @Bean
    public SocketIOServer socketIOServer(com.corundumstudio.socketio.Configuration customSocketConfig) {

        return new SocketIOServer(customSocketConfig);
    }

}
