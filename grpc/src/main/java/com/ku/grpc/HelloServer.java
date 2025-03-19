package com.ku.grpc;

import com.ku.grpc.HelloRequest;
import com.ku.grpc.HelloResponse;
import com.ku.grpc.HelloServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class HelloServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        System.out.println("Server started at port: " + server.getPort());
        server.awaitTermination();
    }

    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            String name = request.getName();
            String greeting = "Hello, " + name + "!";
            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting(greeting)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
