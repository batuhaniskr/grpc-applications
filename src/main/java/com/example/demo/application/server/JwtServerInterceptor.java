package com.example.demo.application.server;

import com.example.demo.Constant;
import com.auth0.jwt.JWTVerifier;
import io.grpc.*;

import java.util.Map;

public class    JwtServerInterceptor implements ServerInterceptor {

    private static final ServerCall.Listener NOOP_LISTENER = new ServerCall.Listener() {
    };

    private final String secret;
    private final JWTVerifier verifier;

    public JwtServerInterceptor(String secret) {
        this.secret = secret;
        this.verifier = new JWTVerifier(secret);
    }


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {

        String jwt = metadata.get(Constant.JWT_METADATA_KEY);
        if (jwt == null) {
            serverCall.close(Status.UNAUTHENTICATED.withDescription("JWT Token is missing from Metadata"), metadata);
            return NOOP_LISTENER;
        }

        Context ctx;

        try {
            Map<String, Object> verified = verifier.verify(jwt);
            ctx = Context.current().withValue(Constant.USER_ID_CTX_KEY, verified.getOrDefault("sub", "anonymous").toString())
                    .withValue(Constant.JWT_CTX_KEY, jwt);
        } catch (Exception e) {
            System.out.println("Verification failed - Unauthenticated!");
            serverCall.close(Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e), metadata);
            return NOOP_LISTENER;
        }

        return Contexts.interceptCall(ctx, serverCall, metadata, serverCallHandler);
    }
}
