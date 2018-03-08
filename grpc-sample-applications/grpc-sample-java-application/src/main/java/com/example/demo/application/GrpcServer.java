package com.example.demo.application;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HotelService()).build();

        server.start();

        System.out.println("Server started.");
        server.awaitTermination();
    }
}
