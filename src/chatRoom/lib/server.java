package chatRoom.lib;

import java.io.IOException;
import java.net.*;

public class server implements Runnable{
    static byte[] receiveData = new byte[1024];
    static byte[] sendData  = new byte[1024];
    static DatagramSocket serverSocket;
    static DatagramPacket receivePacket;
    static InetAddress IPAddress;
    static DatagramPacket sendPacket;
    public int port;

    public server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new DatagramSocket(port);
            while(true) {
                Thread.sleep(500);
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                IPAddress = receivePacket.getAddress();
                sendData = sentence.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException | InterruptedException e) { }
    }

    public void endSession(){
        serverSocket.close();
    }

}