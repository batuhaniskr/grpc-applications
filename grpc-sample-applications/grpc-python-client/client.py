import hotelService_pb2
import hotelService_pb2_grpc
import grpc

def run():
    channel = grpc.insecure_channel('localhost:8080')
    stub = hotelService_pb2_grpc.HotelServiceStub(channel)

    response = stub.getHotel(hotelService_pb2.HotelRequest(id=1, hotelName= "testHotel"))
    print(response)


if __name__ == "__main__":
    run()