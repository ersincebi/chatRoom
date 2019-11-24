package chatapp.models;

import java.net.InetAddress;

public class ClientInfo {
    private String name;
    private int id;
    private InetAddress address;
    private int port;

    public ClientInfo(String name, int id, InetAddress address, int port) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }
}
