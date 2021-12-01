package com.am.gui.controller;

import com.am.serialport.SerialPortService;
import com.am.serialport.listener.SerialPortReadDataListener;
import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@Controller
@FxmlView("uart.fxml")
@RequiredArgsConstructor
public class UartController {

    private final SerialPortService serialPortService;

    @FXML
    private ChoiceBox<String> uartChoiceBox;

    @FXML
    private ToggleButton uartToggleButton;

    @FXML
    void ChoiceBoxHandler(KeyEvent event) {

    }

    @FXML
    void toggleButtonHandler(ActionEvent event) {
        SerialPort choicePort = null;
        List<SerialPort> serialPorts = Arrays.asList(SerialPort.getCommPorts());
        for (SerialPort port : serialPorts) {
            if (port.getDescriptivePortName().equals(uartChoiceBox.getValue())) {
                choicePort = port;
            }
        }
        if (choicePort != null) {
            if (choicePort.isOpen()) {
                choicePort.closePort();
            }
            choicePort.openPort();
            SerialPortReadDataListener listener = new SerialPortReadDataListener();
            choicePort.addDataListener(listener);
        }else{
            System.out.println("Нет такого порта");
        }
    }

    @FXML
    public void initialize() {
        uartChoiceBox.setValue("выберите порт");
        uartChoiceBox.setOnShowing(actionEvent -> {
            List<SerialPort> portsAction = serialPortService.getComputerSerialPorts();
            ObservableList<String> namePorts = FXCollections.observableArrayList();
            portsAction.forEach(serialPort -> {
                namePorts.add(serialPort.getDescriptivePortName());
            });
            uartChoiceBox.setItems(namePorts);
        });
    }
}