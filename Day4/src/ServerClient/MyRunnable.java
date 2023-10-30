package ServerClient;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

//Defines MyRunnable class, implement Runnable interface.
//This class is meant to be used as a separate thread to handle client requests.
public class MyRunnable implements Runnable {
    //Represents the client's connection to the server
    Socket client;
//    const serverURL = "ws://localhost:8080/";
//    let ws = new WebSocket(serverURL);

    //Constructor
    public MyRunnable(Socket client) {
        this.client = client;
    }

    private void handleWebSocketMessages() throws IOException {
        //Store WebSocket frame control information.
        //Will be used to determine the characteristics of the incoming WebSocket message.
        Boolean fin_;
        Boolean mask_;
        //For storing the length of the payload.
        long payloadLength_;
        //An empty byte array to store the mask that may be applied to payload data.
        byte[] maskArray = new byte[0];
        //Reads data from client's input stream.
        //Used to receive data from the client.
        DataInputStream dataStream = new DataInputStream(client.getInputStream());

        //Reads through WebSocket frames from the client until an exit condition is met.
        //Loop keeps reading frames as they arrive.
        while (true){
            //Reads the first two bytes of the WebSocket frame.
            //The first two bytes contain control information.
            System.out.println("************Start of the while loop*******");
            System.out.println("Available bytes in dataStream: " + dataStream.available());
            byte[] input = dataStream.readNBytes(2);
            System.out.println(input);
            //Extracting the fin and mask bits from the first byte of the frame.
            System.out.println("Input before anding: "+ input[0]);
            fin_ = (input[0] & 0x80) > 0;
            System.out.println("Input after anding: " + input[1]);
            mask_ = (input[1] & 0x80) > 0;
            //Extracts the payload length, which is in the second byte of the frame.
            payloadLength_ = (input[1] & 0x7f);

            //Check if length is 126 or 127.
            if(payloadLength_ < 126){
                System.out.println("Payload is not extended, length: " + payloadLength_);
            }
            else if (payloadLength_==126){
                payloadLength_= dataStream.readShort();
                System.out.println("Payload is extended: 2 bits, length: " + payloadLength_);
            }
            else if (payloadLength_==127){
                payloadLength_= dataStream.readLong();
                System.out.println("Payload is extended: 8 bits, length: " + payloadLength_);
            }
            System.out.println("Masked: "+ mask_+"Length: "+payloadLength_);

            //If payload length is masked, the code reads 4 bytes from the unput stream
            //into the "maskArray". The mask is used to decode the payload data.
            if(mask_){
                maskArray=dataStream.readNBytes(4);
            }
            //Reads the payload data into the array of the designated length.
            byte[] encodedPayloadArray = dataStream.readNBytes((int) payloadLength_);
            byte[] decodedPayloadArray = new byte[(int)payloadLength_];
            //If payload is masked, the code is decoded by applying XOR operation.
            //XOR operation ensures that payload is in its original form.
            if (mask_){
                for (int i = 0; i < encodedPayloadArray.length; i++){
                    decodedPayloadArray[i] = (byte)(encodedPayloadArray[i] ^ maskArray[i%4]);
                }
            }
            //Convert the payload data into a String and the message is printed out.
            String message = new String(decodedPayloadArray, StandardCharsets.UTF_8);
            System.out.println("message" + message);
            sendMessage(message);
            //return message;
        }
    }
    //Overriding a method from the "Runnable" interface.
    //Run is a required method.
    @Override
    public void run() throws RuntimeException {
        HTTPResponse response = new HTTPResponse();
        HTTPRequest request = null;
        try{
            //An attempt to create a new request
            request = new HTTPRequest(client);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        boolean parsed = request.parseRequest();
        try {
            //Checks if the request object was successfully created.
            //If not, send a websocket response.
//            if (!request.parseRequest()){
//                System.out.println("Server received a bad http... ignoring...");
//                return;
////                response.sendWebSocketResponse(client, request.getWebSocketKey());
////                    continue;
//            }
            //If the request is a WebSocket request, send a response and
            //call handleWebSocketMessages method.
            if (request.isWebSocket()){
                response.sendWebSocketResponse(client, request.getWebSocketKey());
                //while(true){
                    handleWebSocketMessages();
                    System.out.println("Request is being sent");

                //}
            //Send create File response
            } else {
                //Creates the file needed to send through sendResponse
                File file = response.createFile(request.getParameter());
                //Send response to client
                response.sendResponse(client, file, request.getFileType());
                System.out.println("Response is being sent");
            }
        //If an exception occurs, catch it and send the error response (404).
        } catch (IOException e) {
            try{
                response.sendErrorResponse(client);
            } catch (IOException ex){
                throw new RuntimeException(e);
            }
        }
        }
    public void sendMessage(String message){
        try{
            DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            byte[] frame = new byte[messageBytes.length + 2];
            frame[0] = (byte) 0x01;
            frame[1] = (byte) messageBytes.length;

            System.arraycopy(messageBytes, 0, frame, 2, messageBytes.length);
            dataOut.write(frame);
            dataOut.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}