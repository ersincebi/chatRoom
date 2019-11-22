package chatRoom.controllers;

import chatRoom.lib.rooms;
import chatRoom.lib.server;
import chatRoom.models.loginInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class login implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private ComboBox selectedRooms;
    @FXML
    private Button connect;

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        loginInfo loginInfo = new loginInfo();
        loginInfo.setUserInfo(username.getText(), getRoomPort(selectedRooms.getSelectionModel().getSelectedIndex()));
        chatPanel chatPanel = new chatPanel();
        chatPanel.openChatPanel(event);
    }

    /**
     * initializes the chat room list with preset port numbers
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateComboBox(server.getServerRooms());
    }


    /**
     * populates combobox when it called
     */
    public void populateComboBox(ArrayList<rooms> list){
        for (rooms item:list)
            selectedRooms.getItems().add(item.roomName);
    }

    /**
     * gives the port number of selected chatRoom
     * according to index value reaches the value inside arrayList
     * and gets the int value of port from rooms class
     * @param index
     * @return
     */
    public int getRoomPort(int index){
        ArrayList<rooms> list = rooms.getRooms();
        rooms item = list.get(index);
        return item.port;
    }
}
