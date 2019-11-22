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


import java.io.*;
import java.net.*;
import java.util.ResourceBundle;


public class chatPanel implements Initializable {
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField sendArea;
    @FXML
    private Button send;

    String sendData = "";
    String receiveData ="";

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(()->{
            try {
                socket = new Socket("localhost",loginInfo.roomConnected);
                dataInputStream = new DataInputStream(socket.getInputStream());
            } catch (Exception e) { }
            while(true){
                try {
                    Thread.sleep(500);
                    receiveData = dataInputStream.readUTF();
                    writeReceiveData(receiveData);
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
    public void sendMessage(ActionEvent event) throws Exception {
        socket = new Socket("localhost",loginInfo.roomConnected);
        sendData = loginInfo.username + " > " + sendArea.getText();
        System.out.println(sendData + ">" + loginInfo.roomConnected);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(sendData);
        dataOutputStream.flush();
    }

    private void writeReceiveData(String messageText) throws Exception {
        messageArea.setText("\n" + messageText);
    }

    @FXML
    public void endSession() throws Exception { socket.close(); }
}
