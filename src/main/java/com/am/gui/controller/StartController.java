package com.am.gui.controller;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@Setter
@FxmlView("startScene.fxml")
public class StartController {

    private ObservableList<String> portList = FXCollections.observableArrayList();
    @FXML
    private Button acceptPort;

    @FXML
    private ChoiceBox<String> serialPortList;


    @FXML
    void initialize() {
        acceptPort.setOnAction(event -> {
            System.out.println("Погнали нахуй");

            System.out.println("");
        });
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (int i = 0; i < serialPorts.length; i++) {
            if(i == 0){
                serialPortList.setValue(serialPorts[i].getSystemPortName());
            }
            portList.add(serialPorts[i].getPortDescription());
        }
        serialPortList.setItems(portList);
    }
}
