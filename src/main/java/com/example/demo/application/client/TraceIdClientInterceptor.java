package com.example.demo.application.client;

import com.example.demo.Constant;
import io.grpc.*;

public class TraceIdClientInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                if (Constant.TRACE_ID_CTX_KEY.get() != null) {
                    headers.put(Constant.TRACE_ID_METADATA_KEY, Constant.TRACE_ID_CTX_KEY.get());
                }
                super.start(responseListener, headers);
            }
        };
    }
}
