package com.example.demo.application.client;

import com.example.demo.Constant;
import com.example.demo.generated.HotelRequest;
import com.example.demo.generated.HotelResponse;
import com.example.demo.generated.HotelServiceGrpc;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.auth0.jwt.JWTSigner;

import java.util.HashMap;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        String jwt = createJwt(Constant.JWT_SECRET, "authClient", "test");
        System.out.println("Created a JWT:" + jwt);
        JwtCallCredential callCredential = new JwtCallCredential(jwt);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext(true)
                .intercept(new TraceIdClientInterceptor())
                .build();

        Context.current().withValue(Constant.TRACE_ID_CTX_KEY, "1").run(() -> {
            HotelServiceGrpc.HotelServiceBlockingStub hotelServiceBlockingStub =
                    HotelServiceGrpc.newBlockingStub(channel).withCallCredentials(callCredential);
            HotelResponse hotelResponse = hotelServiceBlockingStub.getHotel(HotelRequest.newBuilder()
                    .setId(1)
                    .setHotelName("Washington")
                    .build());

            System.out.println("Responce received from server: " + hotelResponse);
        });

        channel.shutdown();
    }

    public static String createJwt(String secret, String issuer, String subject) {

        final long iat = System.currentTimeMillis() / 1000l;
        final long exp = iat + 60L;

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();

        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put("sub", subject);

        return signer.sign(claims);
    }
}
