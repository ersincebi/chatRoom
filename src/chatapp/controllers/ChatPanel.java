package chatapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lib.handleClient;

import java.io.IOException;

public class ChatPanel {
    @FXML
    private static TextArea messageArea;
    @FXML
    private TextField sendArea;
    @FXML
    private Button send;

    private static handleClient handleClient;

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
    public void initialize(String username, int port) {
        messageArea = new TextArea();
        handleClient = new handleClient(username,"localhost", port);
    }

    @FXML
    public void sendMessage(ActionEvent event){
        if(!sendArea.getText().equals("")){
            handleClient.send(sendArea.getText());
            sendArea.setText("");
        }
    }

    public static void printToPanel(String message) {
        System.out.println(message);
        messageArea.setText(messageArea.getText()+message+"\n");
    }

}
