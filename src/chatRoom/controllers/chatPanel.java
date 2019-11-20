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


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class chatPanel {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    @FXML
    private TextArea messageArea;
    @FXML
    private TextField sendArea;
    @FXML
    private Button send;

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
        System.out.println(userInfo.username);
        System.out.println(userInfo.roomConnected);
        Socket s = new Socket("localhost", userInfo.roomConnected);
        fromServer = new DataInputStream(s.getInputStream());
        toServer = new DataOutputStream(s.getOutputStream());
        String messageText = userInfo.username + " > " + sendArea.getText();
        while (true) {
            sendReceive(messageText);
        }
    }

    private void sendReceive(String messageText) throws Exception {
        toServer.writeBytes(messageText);
        toServer.flush();
        String receiveMessageText = fromServer.readLine();
        messageArea.setText("\n" + receiveMessageText);
    }
}