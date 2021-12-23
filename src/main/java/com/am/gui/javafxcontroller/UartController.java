package com.am.gui.javafxcontroller;

import com.am.serialport.MySerialPort;
import com.am.serialport.SerialPortService;
import com.am.serialport.listener.SerialPortReadMessageListener;
import com.am.serialport.listener.SerialPortWriteListener;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@FxmlView("uart.fxml")
@RequiredArgsConstructor
public class UartController {

    private final SerialPortService serialPortService;

    private final MySerialPort selectedPort;

    private final SerialPortReadMessageListener readListener;
    private final SerialPortWriteListener writeListener;

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
                selectedPort.getPort().addDataListener(readListener);
                selectedPort.getPort().addDataListener(writeListener);
                selectedPort.getPort().setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 200);
                selectedPort.getPort().setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
                selectedPort.getPort().openPort();
                log.info("The port {} was open.", selectedPort.getPort().getSystemPortName());
            } else {
                selectedPort.getPort().removeDataListener();
                selectedPort.getPort().closePort();
                log.info("The port {} was close.", selectedPort.getPort().getSystemPortName());
            }
        }
    }

    @FXML
    public void initialize() {
        uartChoiceBox.setValue("pick a port");
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