package chatRoom.lib;

import java.io.IOException;
import java.net.*;

public class server {

    static byte[] receiveData = new byte[1024];
    static byte[] sendData  = new byte[1024];
    static DatagramSocket serverSocket;
    static DatagramPacket receivePacket;
    static InetAddress IPAddress;
    static DatagramPacket sendPacket;

    public static void main(String[] args) throws Exception {
        rooms.setRoomByAvailablePort();

    }

    public void receiveData() throws IOException {
        serverSocket = new DatagramSocket(9876);

        while(true) {
            System.out.println("Server on port .... is online");

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());

            IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);
        }
    }

    public void endSession(){
        serverSocket.close();
    }
}