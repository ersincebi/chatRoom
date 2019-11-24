package lib;

import chatapp.models.ClientInfo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server implements Runnable {

    private static DatagramSocket socket;

    // a basic semaphore
    private static boolean running;

    private static int ClientID;
    private static ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();
    private int port;
    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        start(port);
    }

    public static void start(int port) {
        try {
            socket = new DatagramSocket(port);

            running = true;
            listen();

            System.out.println("Server on port " + port + " is online");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadCast(String message) {
        for(ClientInfo info : clients){
            send(message, info.getAddress(), info.getPort());
        }
    }

    private static void send(String message, InetAddress address, int port) {
        try {
            message += "\\e";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listen() {
        Thread listenThread = new Thread() {
            public void run(){
                try {
                    while (running) {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        socket.receive(packet);

                        String message = new String(data);
                        /**
                         * because of our byte array is not gonna fill until the end off it
                         * there will be a mark that marks the end of message \\e
                         * I simply say take the message until the end mark
                         */
                        message = message.substring(0, message.indexOf("\\e"));

                        // manage message
                        if(!isCommand(message,packet))
                            broadCast(message);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        listenThread.start();
    }

    /**
     * SERVER COMMAND LIST
     * \con:[name] -> Connects handleClient to Server
     *
     * our message to check
     * @param message
     *
     * @param packet
     * @return
     */
    private static boolean isCommand(String message, DatagramPacket packet) {
        if(message.startsWith("\\con:")){
            //RUN CONNECTION COMMAND
            String name = message.substring(message.indexOf(":")+1);
            clients.add(new ClientInfo(name, ClientID++, packet.getAddress(), packet.getPort()));
            broadCast("User " + name + " is Connected!");
            return true;
        }
        return false;
    }

    public static void stop() {
        running = false;
    }
}
