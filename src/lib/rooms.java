package lib;

import java.util.ArrayList;

public class rooms {
    public static ArrayList<rooms> roomsList = new ArrayList<rooms>();
    public String roomName;
    public int port;
    public rooms(String roomName, int port){
        this.roomName = roomName;
        this.port = port;
    }

    /**
     * takes the send information
     * inserts those information inside roomList
     *
     * the room name will be displayed on Login page
     * @param roomName
     *
     * the port number of the room
     * @param port
     */
    public static void addRoom(String roomName, int port){
        roomsList.add(new rooms(roomName,port));
    }

    /**
     * inserts the port inside roomList by using addRoom function
     */
    public static void setRoomByAvailablePort() {
        addRoom("Room 1",9999);
        addRoom("Room 2",9998);
        addRoom("Room 3",9997);
        addRoom("Room 4",9996);
    }

    /**
     * returns the room list from populated rooms class
     * @return
     */
    public static ArrayList<rooms> getRooms(){
        return rooms.roomsList;
    }
}
