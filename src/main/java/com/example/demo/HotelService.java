package com.example.demo;


import com.example.demo.generated.HotelRequest;
import com.example.demo.generated.HotelResponse;
import com.example.demo.generated.HotelServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HotelService extends HotelServiceGrpc.HotelServiceImplBase {

    @Override
    public void getHotel(HotelRequest request, StreamObserver<HotelResponse> responseObserver) {

        System.out.println(request);

        String userId = Constant.USER_ID_CTX_KEY.get();
        System.out.println("Hotel Service Trace Id: " + Constant.TRACE_ID_CTX_KEY.get());
        System.out.println("Hotel Service User Id: " + userId);

        String hotelText = new StringBuilder()
                .append("Hotel, id: ")
                .append(request.getId())
                .append("  name: ")
                .append(request.getHotelName())
                .toString();

        HotelResponse response = HotelResponse.newBuilder()
                .setHotelText(hotelText)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
