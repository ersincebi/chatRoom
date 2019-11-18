package chatRoom.controllers;

import chatRoom.lib.rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    public void userLogin(ActionEvent event){
        // new userInfo(username.getText())
        System.out.println(username.getText());
        System.out.println(selectedRooms.getValue());
        System.out.println(getRoomPort(selectedRooms.getSelectionModel().getSelectedIndex()));

    }

    /**
     * initializes the chat room list with preset port numbers
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms.addRoom("Room 1", 9000);
        rooms.addRoom("Room 2", 8000);
        ArrayList<rooms> list = getRooms();
        populateComboBox();

    }

    /**
     * populates combobox when it called
     */
    public void populateComboBox(){
        ArrayList<rooms> list = getRooms();
        for (rooms item:list) {
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
        ArrayList<rooms> list = getRooms();
        rooms item = list.get(index);
        return item.port;
    }

    /**
     * returns the room list from populated rooms class
     * @return
     */
    public ArrayList<rooms> getRooms(){
        return rooms.roomsList;
    }
}
