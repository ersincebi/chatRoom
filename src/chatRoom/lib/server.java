package chatRoom.lib;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws Exception {
        rooms.setRoomByAvailablePort();
        for (rooms item:rooms.roomsList) {
            System.out.println(item.port);
            new server(item.port);
        }
    }

    public server(int port) throws Exception{
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            HandleAClient task = new HandleAClient(socket);
            new Thread(task).start();
    }
    class HandleAClient implements Runnable {
        private Socket socket;
        public HandleAClient(Socket socket) { this.socket = socket;}
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String receivedMessage = inputFromClient.readLine();
                    outputToClient.writeBytes(receivedMessage);
                }
            } catch(IOException e) { System.err.println(e); }
        }
    }
}