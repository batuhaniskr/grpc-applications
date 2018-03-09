## gRPC Authentication Application

json web token based grpc authentication secure application

<b>hotelService.proto </b>

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

If you will do project from scratch, you can use command:

<pre>protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/HelloService.proto</pre>

<pre>cd grpc-authentication-application</pre>
<li>Close the server in the previous application. </li>

<li>You need to start the GrpcServer.java. Server runs on port 8080. </li>

<li>Client.java runs for the consuming service. </li>
</br>
<b>Console message of Client: </b>
<pre>
Created a JWT:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoQ2xpZW50Iiwic3ViIjoidGVzdCIsImV4cCI6MTUyMDUxNDYwMSwiaWF0IjoxNTIwNTE0NTQxfQ.pfS9QvP4UBje55WRmb2zMY7wspEYmI3UygG2Fy5VlK8
localhost:8080
Responce received from server: hotelText: "Hotel, id: 1  name: Washington"
</pre>
<br>
<b>Console message of GrpcServer: </b>

<pre>Server started
id: 1
hotelName: "Washington"</pre>
