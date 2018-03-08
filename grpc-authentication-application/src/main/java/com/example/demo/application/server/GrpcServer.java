package com.example.demo.application.server;

import com.example.demo.Constant;
import com.example.demo.HotelService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;


public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        JwtServerInterceptor jwtInterceptor = new JwtServerInterceptor(Constant.JWT_SECRET);

        Server server = ServerBuilder
                .forPort(8080)
                .addService(ServerInterceptors.
                        intercept(new HotelService(), jwtInterceptor, new TraceIdServerInterceptor())).build();
        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }
}
