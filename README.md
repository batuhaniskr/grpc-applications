# gRPC-applications

Sample gRPC Java And Python client - server application and gRPC authentication application 

<li><a href="https://github.com/batuhaniskr/grpc-applications/tree/master/grpc-sample-applications">grpc-sample-application</a></li>
 
## 2. gRPC Authentication Application

json web token based grpc authentication secure application
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
