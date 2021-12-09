package com.am.gui.controller;

import com.am.serialport.MySerialPort;
import com.am.serialport.SerialPortService;
import com.am.serialport.listener.SerialPortReadMessageListener;
import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@FxmlView("uart.fxml")
@RequiredArgsConstructor
public class UartController {

    private final SerialPortService serialPortService;

    private final MySerialPort selectedPort;

    private final SerialPortReadMessageListener listener;

    @FXML
    private ChoiceBox<String> uartChoiceBox;

    @FXML
    private ToggleButton uartToggleButton;

    @FXML
    void ChoiceBoxHandler(KeyEvent event) {

    }

    @FXML
    void toggleButtonHandler(ActionEvent event) {
        List<SerialPort> serialPorts = serialPortService.getComputerSerialPorts();
        if (selectedPort.getPort() == null) {
            for (SerialPort port : serialPorts) {
                if (port.getDescriptivePortName().equals(uartChoiceBox.getValue())) {
                    selectedPort.setPort(port);
                }
            }
        }

        if (selectedPort.getPort() != null) {
            if (!selectedPort.getPort().isOpen()) {
                selectedPort.getPort().addDataListener(listener);
                selectedPort.getPort().openPort();
                log.info("Порт {} был открыт.", selectedPort.getPort().getSystemPortName());
            } else {
                selectedPort.getPort().removeDataListener();
                selectedPort.getPort().closePort();
                log.info("Порт {} был закрыт.", selectedPort.getPort().getSystemPortName());
            }
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