package com.example.demo.application;

import com.example.demo.HotelRequest;
import com.example.demo.HotelResponse;
import com.example.demo.HotelServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext(true)
                .build();

        HotelServiceGrpc.HotelServiceBlockingStub stub = HotelServiceGrpc.newBlockingStub(channel);

        HotelResponse hotelResponse = stub.getHotel(HotelRequest.newBuilder()
                .setHotelName("Washington")
                .setId(1)
                .build());

        System.out.println(hotelResponse);
        channel.shutdown();
    }
}
