package com.am.gui.javafxcontroller;

import com.am.serialport.MySerialPort;
import com.am.serialport.data.SerialPortData;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
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


    private final SimpleStringProperty property1 = new SimpleStringProperty();
    private final SimpleStringProperty property2 = new SimpleStringProperty();
    private final SimpleStringProperty property3 = new SimpleStringProperty();
    private final SimpleStringProperty property4 = new SimpleStringProperty();
    private final SimpleStringProperty property5 = new SimpleStringProperty();
    private final SimpleStringProperty property6 = new SimpleStringProperty();
    private final SimpleStringProperty property7 = new SimpleStringProperty();
    private final SimpleStringProperty property8 = new SimpleStringProperty();
    private final SimpleStringProperty property9 = new SimpleStringProperty();
    private final SimpleStringProperty property10 = new SimpleStringProperty();
    private final SimpleStringProperty property11 = new SimpleStringProperty();
    private final SimpleStringProperty property12 = new SimpleStringProperty();
    private final SimpleStringProperty property13 = new SimpleStringProperty();
    private final SimpleStringProperty property14 = new SimpleStringProperty();

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
            stageUart.setResizable(false);
            stageUart.show();
        } else {
            stageUart.show();
        }
    }

    @FXML
    public void initialize() {
        sensor1.textProperty().bind(property1);
        sensor2.textProperty().bind(property2);
        sensor3.textProperty().bind(property3);
        sensor4.textProperty().bind(property4);
        sensor5.textProperty().bind(property5);
        sensor6.textProperty().bind(property6);
        sensor7.textProperty().bind(property7);
        sensor8.textProperty().bind(property8);
        sensor9.textProperty().bind(property9);
        sensor10.textProperty().bind(property10);
        sensor11.textProperty().bind(property11);
        sensor12.textProperty().bind(property12);
        sensor13.textProperty().bind(property13);
        sensor14.textProperty().bind(property14);

        UpdateTextFields timerTask = new UpdateTextFields();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    class UpdateTextFields extends TimerTask {

        @Override
        public void run() {
            try {
                if (dataFromArduino.getReadDataFromArduino().isEmpty()) {
                    property1.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(0, 0.0).toString());
                    property2.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(1, 0.0).toString());
                    property3.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(2, 0.0).toString());
                    property4.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(3, 0.0).toString());
                    property5.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(4, 0.0).toString());
                    property6.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(5, 0.0).toString());
                    property7.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(6, 0.0).toString());
                    property8.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(7, 0.0).toString());
                    property9.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(8, 0.0).toString());
                    property10.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(9, 0.0).toString());
                    property11.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(10, 0.0).toString());
                    property12.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(11, 0.0).toString());
                    property13.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(12, 0.0).toString());
                    property14.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(13, 0.0).toString());
                } else {
                    property1.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(0, Double.parseDouble(property1.getValue())).toString());
                    property2.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(1, Double.parseDouble(property2.getValue())).toString());
                    property3.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(2, Double.parseDouble(property3.getValue())).toString());
                    property4.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(3, Double.parseDouble(property4.getValue())).toString());
                    property5.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(4, Double.parseDouble(property5.getValue())).toString());
                    property6.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(5, Double.parseDouble(property6.getValue())).toString());
                    property7.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(6, Double.parseDouble(property7.getValue())).toString());
                    property8.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(7, Double.parseDouble(property8.getValue())).toString());
                    property9.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(8, Double.parseDouble(property9.getValue())).toString());
                    property10.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(9, Double.parseDouble(property10.getValue())).toString());
                    property11.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(10, Double.parseDouble(property11.getValue())).toString());
                    property12.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(11, Double.parseDouble(property12.getValue())).toString());
                    property13.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(12, Double.parseDouble(property13.getValue())).toString());
                    property14.setValue(dataFromArduino.getReadDataFromArduino().getOrDefault(13, Double.parseDouble(property14.getValue())).toString());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
