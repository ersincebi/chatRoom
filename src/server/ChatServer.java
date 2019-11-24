package server;

import lib.Server;
import lib.rooms;

public class ChatServer {
    public static void main(String[] args) {
        int index = 0;
        rooms.setRoomByAvailablePort();
        Thread[] serverThread = new Thread[4];
        for (rooms room:rooms.getRooms()) {
            serverThread[index] = new Thread(new Server(room.port));
            serverThread[index++].start();
        }
    }
}
