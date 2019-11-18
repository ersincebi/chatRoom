package chatRoom.lib;

public class userInfo {
    public String username;
    public int roomConnected;

    /**
     * the will be displayed on the chatpanel
     * @param username
     *
     * the port number user connected
     * @param port
     */
    public userInfo(String username, int port){
        this.username = username;
        this.roomConnected = port;
    }
}
