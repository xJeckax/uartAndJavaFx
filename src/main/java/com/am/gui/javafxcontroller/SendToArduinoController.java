package com.am.gui.javafxcontroller;

import com.am.models.ExperimentData;
import com.am.models.ValuesRestrictionData;
import com.am.serialport.SendDataToArduino;
import com.am.serialport.data.SerialPortData;
import com.am.service.ExperimentDataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@FxmlView("valuesRestriction.fxml")
@RequiredArgsConstructor
public class SendToArduinoController {
    private Stage stageDataControl;
    private final FxWeaver fxWeaver;
    private final SerialPortData serialPortData;
    private final SendDataToArduino sendDataToArduino;
    private final ValuesRestrictionData valuesRestrictionData;
    private final ExperimentDataService experimentDataService;

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
    private TextField directory;

    @FXML
    private Button selectDirectory;

    @FXML
    void loadExperiment(ActionEvent event) {

    }

    public void restoreValues(Map<Integer, Double> rememberedValues) {
        section1.textProperty().setValue(rememberedValues.get(0).toString());
        section2.textProperty().setValue(rememberedValues.get(1).toString());
        section3.textProperty().setValue(rememberedValues.get(2).toString());
        section4.textProperty().setValue(rememberedValues.get(3).toString());
        section5.textProperty().setValue(rememberedValues.get(4).toString());
        section6.textProperty().setValue(rememberedValues.get(5).toString());
        section7.textProperty().setValue(rememberedValues.get(6).toString());
        section8.textProperty().setValue(rememberedValues.get(7).toString());
        section9.textProperty().setValue(rememberedValues.get(8).toString());
        section10.textProperty().setValue(rememberedValues.get(9).toString());
        section11.textProperty().setValue(rememberedValues.get(10).toString());
        section12.textProperty().setValue(rememberedValues.get(11).toString());
        section13.textProperty().setValue(rememberedValues.get(12).toString());
        section14.textProperty().setValue(rememberedValues.get(13).toString());
    }

    @FXML
    private void dataControl(ActionEvent event) {
        if (stageDataControl == null) {
            Parent root = fxWeaver.loadView(DataControlController.class);
            stageDataControl = new Stage();
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(root); 
            Group group = new Group();
            group.getChildren().add(borderPane);

            Scene scene = new Scene(group);
            stageDataControl.setScene(scene);
            stageDataControl.setAlwaysOnTop(true);
            stageDataControl.show();
        } else {
            stageDataControl.show();
        }
    }


    @FXML
    void saveValuesInFile(ActionEvent event) {
        ExperimentData data = new ExperimentData();
        mergeExperimentData(data);

        if (!directory.textProperty().isEmpty().get()) {
            experimentDataService.save(directory.getText(), data);
        }
    }

    @FXML
    void selectDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        File file = directoryChooser.showDialog(selectDirectory.getScene().getWindow());
        if (file != null) {
            directory.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void setValues(ActionEvent event) {
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

            valuesRestrictionData.addReadData(valuesRestriction);
            sendDataToArduino.write(valuesRestriction);
        } catch (NumberFormatException e) {
            log.info("use next format 2.0 or 2 or 2.24");
        }
    }

    private void mergeExperimentData(ExperimentData data) {
        data.setSensorsData(serialPortData.getReadDataSensors());
        data.setRestrictions(serialPortData.getReadDataRestrictions());
        data.setTimestamp(new Date());
    }

    @FXML
    private void initialize() {
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

        directory.setText("Please, select a directory");
    }
}
