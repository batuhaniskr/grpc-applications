package com.example.demo.Application;

import com.example.demo.HotelService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

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
