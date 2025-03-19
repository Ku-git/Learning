package com.ku.grpc;

import com.ku.grpc.HelloRequest;
import com.ku.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloClient {

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName("Ku").build();

        com.ku.grpc.HelloResponse response = stub.sayHello(request);
        System.out.println(response);

        channel.shutdownNow();
    }
}
