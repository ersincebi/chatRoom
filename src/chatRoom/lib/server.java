package chatRoom.lib;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class server implements Runnable{
    static ArrayList<rooms> list;
    String receiveData = "";
    String sendData = "";
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    static Thread serverThread[];
    public int port;

    public server(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        __initServers(getServerRooms());
    }

    public static void __initServers(ArrayList<rooms> list) throws Exception {
        int listSize = list.size();
        int index = 0;

        serverThread = new Thread[listSize];

        for (rooms itemPort : list) {
            serverThread[index] = new Thread(new server(itemPort.port));
            serverThread[index++].start();
        }

    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            System.out.println("Server on port " + port + " is online...");

            socket = serverSocket.accept();

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while(true) {
                Thread.sleep(500);
                receiveData = dataInputStream.readUTF();
                System.out.println(receiveData);
                dataOutputStream.writeUTF(sendData);
                dataOutputStream.flush();
            }
        } catch (Exception e) { }
    }

    public static ArrayList<rooms> getServerRooms(){
        rooms.setRoomByAvailablePort();
        return rooms.getRooms();
    }

    public void endSession() throws Exception { socket.close(); }

}