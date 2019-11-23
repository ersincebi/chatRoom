package controllers;

import lib.rooms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    private TextField username;
    @FXML
    private ComboBox selectedRooms;
    @FXML
    private Button connect;

    @FXML
    public void userLogin(ActionEvent event) throws Exception {
        ChatPanel chatPanel = new ChatPanel();
        chatPanel.openChatPanel(event);
        chatPanel.initialize(
                username.getText()
                ,getRoomPort(selectedRooms.getSelectionModel().getSelectedIndex()));
    }

    /**
     * initializes the chat room list with preset port numbers
     */
    @FXML
    public void initialize() {
        rooms.setRoomByAvailablePort();
        populateComboBox();
    }

    /**
     * populates combobox when it called
     */
    public void populateComboBox(){
        for (rooms item:rooms.getRooms()) {
            selectedRooms.getItems().add(item.roomName);
        }
    }

    /**
     * gives the port number of selected chatRoom
     * according to index value reaches the value inside arrayList
     * and gets the int value of port from rooms class
     * @param index
     * @return
     */
    public int getRoomPort(int index){
        return rooms.getRooms().get(index).port;
    }

}
