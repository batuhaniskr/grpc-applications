package com.example.demo;

import com.example.demo.HotelRequest;
import com.example.demo.HotelResponse;
import com.example.demo.HotelServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HotelService extends HotelServiceGrpc.HotelServiceImplBase {

    @Override
    public void getHotel(HotelRequest request, StreamObserver<HotelResponse> responseObserver) {

        String hotelText = new StringBuilder()
                .append("Hotel, id: ")
                .append(request.getId())
                .append("  name: ")
                .append(request.getHotelName())
                .toString();
        HotelResponse hotelResponse = HotelResponse.newBuilder()
                .setHotelText(hotelText)
                .build();

        responseObserver.onNext(hotelResponse);
        responseObserver.onCompleted();
    }
}
