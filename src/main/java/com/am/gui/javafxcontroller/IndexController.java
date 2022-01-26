package com.am.gui.javafxcontroller;

import com.am.serialport.MySerialPort;
import com.am.serialport.data.SerialPortData;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
@FxmlView("index.fxml")
public class IndexController {
    private String EMERGENCY_SHUTDOWN = "~";

    private Stage stageUart;
    private final FxWeaver fxWeaver;
    private Stage stageSendValuesToArduino;
    private final MySerialPort selectedPort;
    private final SerialPortData dataFromArduino;


    private final SimpleStringProperty propertySN1 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN2 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN3 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN4 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN5 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN6 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN7 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN8 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN9 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN10 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN11 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN12 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN13 = new SimpleStringProperty();
    private final SimpleStringProperty propertySN14 = new SimpleStringProperty();

    private final SimpleStringProperty propertyRs1 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs2 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs3 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs4 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs5 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs6 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs7 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs8 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs9 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs10 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs11 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs12 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs13 = new SimpleStringProperty();
    private final SimpleStringProperty propertyRs14 = new SimpleStringProperty();

    @FXML
    private TextField sensor1;

    @FXML
    private TextField sensor2;

    @FXML
    private TextField sensor3;

    @FXML
    private TextField sensor4;

    @FXML
    private TextField sensor5;

    @FXML
    private TextField sensor6;

    @FXML
    private TextField sensor7;

    @FXML
    private TextField sensor8;

    @FXML
    private TextField sensor9;

    @FXML
    private TextField sensor10;

    @FXML
    private TextField sensor11;

    @FXML
    private TextField sensor12;

    @FXML
    private TextField sensor13;

    @FXML
    private TextField sensor14;

    @FXML
    private TextField restriction1;

    @FXML
    private TextField restriction2;

    @FXML
    private TextField restriction3;

    @FXML
    private TextField restriction4;

    @FXML
    private TextField restriction5;

    @FXML
    private TextField restriction6;

    @FXML
    private TextField restriction7;

    @FXML
    private TextField restriction8;

    @FXML
    private TextField restriction9;

    @FXML
    private TextField restriction10;

    @FXML
    private TextField restriction11;

    @FXML
    private TextField restriction12;

    @FXML
    private TextField restriction13;

    @FXML
    private TextField restriction14;


    @FXML
    private Menu indexMenu;

    @FXML
    private Button setValues;

    @FXML
    private MenuBar indexMenuBar;

    @FXML
    private MenuItem indexMenuItem1;

    @FXML
    void emergencyShutdown(ActionEvent event) {
        if (selectedPort.getPort() != null) {
            selectedPort.writeBytes(EMERGENCY_SHUTDOWN.getBytes());
        }
    }

    @FXML
    void setValuesRestriction(ActionEvent event) {
        if (stageSendValuesToArduino == null) {
            Parent root = fxWeaver.loadView(SendToArduinoController.class);
            stageSendValuesToArduino = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(root);

            Group group = new Group();
            group.getChildren().add(borderPane);

            Scene scene = new Scene(group);
            stageSendValuesToArduino.setScene(scene);
            stageSendValuesToArduino.setAlwaysOnTop(true);
            stageSendValuesToArduino.show();
        } else {
            stageSendValuesToArduino.show();
        }
    }

    @FXML
    void setUpUart(ActionEvent event) {
        if (stageUart == null) {
            Parent root = fxWeaver.loadView(UartController.class);
            stageUart = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(root);

            Group group = new Group();
            group.getChildren().add(borderPane);

            Scene scene = new Scene(group);
            stageUart.setScene(scene);
            stageUart.setAlwaysOnTop(true);
            stageUart.setResizable(false);
            stageUart.show();
        } else {
            stageUart.show();
        }
    }

    @FXML
    public void initialize() {
        sensor1.textProperty().bind(propertySN1);
        sensor2.textProperty().bind(propertySN2);
        sensor3.textProperty().bind(propertySN3);
        sensor4.textProperty().bind(propertySN4);
        sensor5.textProperty().bind(propertySN5);
        sensor6.textProperty().bind(propertySN6);
        sensor7.textProperty().bind(propertySN7);
        sensor8.textProperty().bind(propertySN8);
        sensor9.textProperty().bind(propertySN9);
        sensor10.textProperty().bind(propertySN10);
        sensor11.textProperty().bind(propertySN11);
        sensor12.textProperty().bind(propertySN12);
        sensor13.textProperty().bind(propertySN13);
        sensor14.textProperty().bind(propertySN14);

        restriction1.textProperty().bind(propertyRs1);
        restriction2.textProperty().bind(propertyRs2);
        restriction3.textProperty().bind(propertyRs3);
        restriction4.textProperty().bind(propertyRs4);
        restriction5.textProperty().bind(propertyRs5);
        restriction6.textProperty().bind(propertyRs6);
        restriction7.textProperty().bind(propertyRs7);
        restriction8.textProperty().bind(propertyRs8);
        restriction9.textProperty().bind(propertyRs9);
        restriction10.textProperty().bind(propertyRs10);
        restriction11.textProperty().bind(propertyRs11);
        restriction12.textProperty().bind(propertyRs12);
        restriction13.textProperty().bind(propertyRs13);
        restriction14.textProperty().bind(propertyRs14);

        UpdateTextFields timerTask = new UpdateTextFields();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    class UpdateTextFields extends TimerTask {

        @Override
        public void run() {
            try {
                if (dataFromArduino.getReadDataSensors().isEmpty()) {
                    propertySN1.setValue(dataFromArduino.getReadDataSensors().getOrDefault(0, 0.0).toString());
                    propertySN2.setValue(dataFromArduino.getReadDataSensors().getOrDefault(1, 0.0).toString());
                    propertySN3.setValue(dataFromArduino.getReadDataSensors().getOrDefault(2, 0.0).toString());
                    propertySN4.setValue(dataFromArduino.getReadDataSensors().getOrDefault(3, 0.0).toString());
                    propertySN5.setValue(dataFromArduino.getReadDataSensors().getOrDefault(4, 0.0).toString());
                    propertySN6.setValue(dataFromArduino.getReadDataSensors().getOrDefault(5, 0.0).toString());
                    propertySN7.setValue(dataFromArduino.getReadDataSensors().getOrDefault(6, 0.0).toString());
                    propertySN8.setValue(dataFromArduino.getReadDataSensors().getOrDefault(7, 0.0).toString());
                    propertySN9.setValue(dataFromArduino.getReadDataSensors().getOrDefault(8, 0.0).toString());
                    propertySN10.setValue(dataFromArduino.getReadDataSensors().getOrDefault(9, 0.0).toString());
                    propertySN11.setValue(dataFromArduino.getReadDataSensors().getOrDefault(10, 0.0).toString());
                    propertySN12.setValue(dataFromArduino.getReadDataSensors().getOrDefault(11, 0.0).toString());
                    propertySN13.setValue(dataFromArduino.getReadDataSensors().getOrDefault(12, 0.0).toString());
                    propertySN14.setValue(dataFromArduino.getReadDataSensors().getOrDefault(13, 0.0).toString());
                } else {
                    propertySN1.setValue(dataFromArduino.getReadDataSensors().getOrDefault(0, Double.parseDouble(propertySN1.getValue())).toString());
                    propertySN2.setValue(dataFromArduino.getReadDataSensors().getOrDefault(1, Double.parseDouble(propertySN2.getValue())).toString());
                    propertySN3.setValue(dataFromArduino.getReadDataSensors().getOrDefault(2, Double.parseDouble(propertySN3.getValue())).toString());
                    propertySN4.setValue(dataFromArduino.getReadDataSensors().getOrDefault(3, Double.parseDouble(propertySN4.getValue())).toString());
                    propertySN5.setValue(dataFromArduino.getReadDataSensors().getOrDefault(4, Double.parseDouble(propertySN5.getValue())).toString());
                    propertySN6.setValue(dataFromArduino.getReadDataSensors().getOrDefault(5, Double.parseDouble(propertySN6.getValue())).toString());
                    propertySN7.setValue(dataFromArduino.getReadDataSensors().getOrDefault(6, Double.parseDouble(propertySN7.getValue())).toString());
                    propertySN8.setValue(dataFromArduino.getReadDataSensors().getOrDefault(7, Double.parseDouble(propertySN8.getValue())).toString());
                    propertySN9.setValue(dataFromArduino.getReadDataSensors().getOrDefault(8, Double.parseDouble(propertySN9.getValue())).toString());
                    propertySN10.setValue(dataFromArduino.getReadDataSensors().getOrDefault(9, Double.parseDouble(propertySN10.getValue())).toString());
                    propertySN11.setValue(dataFromArduino.getReadDataSensors().getOrDefault(10, Double.parseDouble(propertySN11.getValue())).toString());
                    propertySN12.setValue(dataFromArduino.getReadDataSensors().getOrDefault(11, Double.parseDouble(propertySN12.getValue())).toString());
                    propertySN13.setValue(dataFromArduino.getReadDataSensors().getOrDefault(12, Double.parseDouble(propertySN13.getValue())).toString());
                    propertySN14.setValue(dataFromArduino.getReadDataSensors().getOrDefault(13, Double.parseDouble(propertySN14.getValue())).toString());
                }

                if(dataFromArduino.getReadDataRestrictions().isEmpty()){
                    propertyRs1.setValue("0.0");
                    propertyRs2.setValue("0.0");
                    propertyRs3.setValue("0.0");
                    propertyRs4.setValue("0.0");
                    propertyRs5.setValue("0.0");
                    propertyRs6.setValue("0.0");
                    propertyRs7.setValue("0.0");
                    propertyRs8.setValue("0.0");
                    propertyRs9.setValue("0.0");
                    propertyRs10.setValue("0.0");
                    propertyRs11.setValue("0.0");
                    propertyRs12.setValue("0.0");
                    propertyRs13.setValue("0.0");
                    propertyRs14.setValue("0.0");
                }else{
                    propertyRs1.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(0, 0.0).toString());
                    propertyRs2.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(1, 0.0).toString());
                    propertyRs3.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(2, 0.0).toString());
                    propertyRs4.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(3, 0.0).toString());
                    propertyRs5.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(4, 0.0).toString());
                    propertyRs6.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(5, 0.0).toString());
                    propertyRs7.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(6, 0.0).toString());
                    propertyRs8.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(7, 0.0).toString());
                    propertyRs9.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(8, 0.0).toString());
                    propertyRs10.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(9, 0.0).toString());
                    propertyRs11.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(10, 0.0).toString());
                    propertyRs12.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(11, 0.0).toString());
                    propertyRs13.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(12, 0.0).toString());
                    propertyRs14.setValue(dataFromArduino.getReadDataRestrictions().getOrDefault(13, 0.0).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
