package chatRoom.lib;

import java.util.ArrayList;

public class rooms {
    public static ArrayList<rooms> roomsList = new ArrayList<rooms>();
    public String roomName;
    public int port;
    public rooms(String roomName, int port){
        this.roomName = roomName;
        this.port = port;
    }

    public static void addRoom(String roomName, int port){
        roomsList.add(new rooms(roomName,port));
    }
}
