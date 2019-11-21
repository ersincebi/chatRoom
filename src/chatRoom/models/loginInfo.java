package chatRoom.models;

public class loginInfo {
    public static String username;
    public static int roomConnected;

    /**
     * the will be displayed on the chatpanel
     * @param userName
     *
     * the port number user connected
     * @param port
     */
    public void setUserInfo(String userName, int port){
        this.username = userName;
        this.roomConnected = port;
    }
}
