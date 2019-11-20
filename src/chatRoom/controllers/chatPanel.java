package chatRoom.controllers;

import chatRoom.lib.userInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class chatPanel {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    @FXML
    private TextArea messageArea;
    @FXML
    private TextField sendArea;
    @FXML
    private Button send;

    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    DatagramSocket clientSocket;
    InetAddress IPAddress;
    DatagramPacket sendPacket;
    DatagramPacket receivePacket;

    public void openChatPanel(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../views/chatPanel.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Send Receive Mail");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void sendMessage(ActionEvent event) throws Exception{
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("localhost");
        String messageText = userInfo.username + " > " + sendArea.getText();

        sendData = messageText.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, userInfo.roomConnected);

        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence  = new String(receivePacket.getData());

        sendReceive(modifiedSentence);

    }

    private void sendReceive(String messageText) throws Exception {
        messageArea.setText("\n" + messageText);
    }

    @FXML
    public void endSession(){
        clientSocket.close();
    }
}
