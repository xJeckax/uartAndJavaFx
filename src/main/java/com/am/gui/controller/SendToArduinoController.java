package com.am.gui.controller;

import com.am.serialport.SendDataToArduino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@FxmlView("valuesRestriction.fxml")
@RequiredArgsConstructor
public class SendToArduinoController {
    private final SendDataToArduino sendDataToArduino;

    @FXML
    private TextField section1;

    @FXML
    private TextField section2;

    @FXML
    private TextField section3;

    @FXML
    private TextField section4;

    @FXML
    private TextField section5;

    @FXML
    private TextField section6;

    @FXML
    private TextField section7;

    @FXML
    private TextField section8;

    @FXML
    private TextField section9;

    @FXML
    private TextField section10;

    @FXML
    private TextField section11;

    @FXML
    private TextField section12;

    @FXML
    private TextField section13;

    @FXML
    private TextField section14;

    @FXML
    private Button setValues;

    @FXML
    void setValues(ActionEvent event) {
        try {
            Map<Integer, Double> valuesRestriction = new HashMap<>();
            valuesRestriction.put(0, Double.parseDouble(section1.textProperty().getValue()));
            valuesRestriction.put(1, Double.parseDouble(section2.textProperty().getValue()));
            valuesRestriction.put(2, Double.parseDouble(section3.textProperty().getValue()));
            valuesRestriction.put(3, Double.parseDouble(section4.textProperty().getValue()));
            valuesRestriction.put(4, Double.parseDouble(section5.textProperty().getValue()));
            valuesRestriction.put(5, Double.parseDouble(section6.textProperty().getValue()));
            valuesRestriction.put(6, Double.parseDouble(section7.textProperty().getValue()));
            valuesRestriction.put(7, Double.parseDouble(section8.textProperty().getValue()));
            valuesRestriction.put(8, Double.parseDouble(section9.textProperty().getValue()));
            valuesRestriction.put(9, Double.parseDouble(section10.textProperty().getValue()));
            valuesRestriction.put(10, Double.parseDouble(section11.textProperty().getValue()));
            valuesRestriction.put(11, Double.parseDouble(section12.textProperty().getValue()));
            valuesRestriction.put(12, Double.parseDouble(section13.textProperty().getValue()));
            valuesRestriction.put(13, Double.parseDouble(section14.textProperty().getValue()));

            sendDataToArduino.write(valuesRestriction);
        } catch (NumberFormatException e) {
            log.info("use next format 2.0 or 2 or 2.24");
        }
    }

    @FXML
    public void initialize(){
        section1.textProperty().setValue("0.0");
        section2.textProperty().setValue("0.0");
        section3.textProperty().setValue("0.0");
        section4.textProperty().setValue("0.0");
        section5.textProperty().setValue("0.0");
        section6.textProperty().setValue("0.0");
        section7.textProperty().setValue("0.0");
        section8.textProperty().setValue("0.0");
        section9.textProperty().setValue("0.0");
        section10.textProperty().setValue("0.0");
        section11.textProperty().setValue("0.0");
        section12.textProperty().setValue("0.0");
        section13.textProperty().setValue("0.0");
        section14.textProperty().setValue("0.0");
    }
}
