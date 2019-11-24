package lib;

import chatapp.controllers.ChatPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class handleClient {

    private static DatagramSocket socket;
    private InetAddress address;
    private int port;
    private String name;

    // a basic semaphore
    private static boolean running;

    public handleClient(String name, String address, int port) {
        try {
            this.name = name;
            this.address = InetAddress.getByName(address);
            this.port = port;
            socket = new DatagramSocket();

            running = true;
            listen();
            send("\\con:" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void send(String message) {
        try {
            if(!message.startsWith("\\"))
                message = "[" + name + "]: " + message;

            message += "\\e";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        Thread listenThread = new Thread("Chat Listener") {
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
                            ChatPanel.printToPanel(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        listenThread.start();
    }

    private static boolean isCommand(String message, DatagramPacket packet) {
        if(message.startsWith("\\con:"))
            return true;

        return false;
    }
}
