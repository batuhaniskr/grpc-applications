package com.example.demo.application.client;

import io.grpc.*;

import java.util.concurrent.Executor;

/**
 * Created by rayt on 10/6/16.
 */
public class JwtCallCredential implements CallCredentials {
    private final String jwt;

    public JwtCallCredential(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public void applyRequestMetadata(MethodDescriptor<?, ?> methodDescriptor, Attributes attributes, Executor executor, MetadataApplier metadataApplier) {
        String authority = attributes.get(ATTR_AUTHORITY);
        System.out.println(authority);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Metadata headers = new Metadata();
                    Metadata.Key<String> jwtKey = Metadata.Key.of("jwt", Metadata.ASCII_STRING_MARSHALLER);
                    headers.put(jwtKey, jwt);
                    metadataApplier.apply(headers);
                } catch (Throwable e) {
                    metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
                }
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {

    }
}
