# gRPC-applications

Sample gRPC Java And Python client - server applications and gRPC authentication application
 
## gRPC Sample Application

gRPC Sample unsecure application based client-server model of remote procedure call.

hotelService.proto

<pre>
syntax = "proto3";
option java_multiple_files = true;
package com.example.demo;

message HotelRequest {
    int32 id = 1;
    string hotelName = 2;
}

message HotelResponse {
    string hotelText = 1;
}

service HotelService {
    rpc getHotel(HotelRequest) returns (HotelResponse);
}
</pre>

If you will do project, you can use command:

<pre>protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/HelloService.proto</pre>

or you can using maven plugin. </br>
Created HotelService.java for implementation all operations.
</br>
Now, You need to start the GrpcServer.java. Server runs on port 8080.
</br>
Client.java runs for the consuming service.

## gRPC Client with Python

### Prerequisites
<pre>cd grpc-sample-applications/grpc-python-client/</pre>
<pre>pip install -r requirements.txt</pre>

<pre>python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. hotelService.proto</pre>

<li>hotelService_pb2.py contains message classes. </li>

<li>hotelService_pb2_grpc.py contains server and client classes. </li>

<li>Added client.py for consume the service</li>

<pre>$ python client.py</pre>
<pre> hotelText: "Hotel, id: 1  name: testHotel"</pre>

## gRPC Authentication Application

grpc authentication secure application json web token authentication based.

<pre>cd grpc-authentication-application</pre>
Close the server in the previous application.

You need to start the GrpcServer.java. Server runs on port 8080.

Client.java runs for the consuming service.
<b>Console message: </b>
<pre>
Created a JWT:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoQ2xpZW50Iiwic3ViIjoidGVzdCIsImV4cCI6MTUyMDUxNDYwMSwiaWF0IjoxNTIwNTE0NTQxfQ.pfS9QvP4UBje55WRmb2zMY7wspEYmI3UygG2Fy5VlK8
localhost:8080
Responce received from server: hotelText: "Hotel, id: 1  name: Washington"
</pre>



