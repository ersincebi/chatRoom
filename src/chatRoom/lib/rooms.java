package chatRoom.lib;

import java.net.Socket;
import java.util.ArrayList;

public class rooms {
    public static ArrayList<rooms> roomsList = new ArrayList<rooms>();
    public String roomName;
    public int port;
    static int roomCount = 5;
    public rooms(String roomName, int port){
        this.roomName = roomName;
        this.port = port;
    }

    /**
     * takes the send information
     * inserts those information inside roomList
     *
     * the room name will be displayed on login page
     * @param roomName
     *
     * the port number of the room
     * @param port
     */
    public static void addRoom(String roomName, int port){
        roomsList.add(new rooms(roomName,port));
    }

    /**
     * finds available port by trying
     * when finds an unused port
     * inserts the port inside roomList by using addRoom function
     */
    public static void setRoomByAvailablePort() {
        int port = 9999;
        int result = port-roomCount;
        int i = 1;
        Socket socket;
        while(port > result){
            try {
                socket = new Socket("localhost",port);
            }catch (Exception e){
                addRoom("Room " + i,port);
                i++;
            }
            port--;
        }
    }
}
