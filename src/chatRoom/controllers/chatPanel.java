package chatRoom.controllers;

import chatRoom.models.loginInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.*;
import java.io.IOException;
import java.util.ResourceBundle;


public class chatPanel implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(500);
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    sendReceive(new String(receivePacket.getData()));
                } catch (Exception e) {}
            }
        });
        thread.start();
    }

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
        String messageText = loginInfo.username + " > " + sendArea.getText();
        sendData = messageText.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, loginInfo.roomConnected);
        clientSocket.send(sendPacket);
    }

    private void sendReceive(String messageText) throws Exception {
        messageArea.setText("\n" + messageText);
    }

    @FXML
    public void endSession(){
        clientSocket.close();
    }
}
