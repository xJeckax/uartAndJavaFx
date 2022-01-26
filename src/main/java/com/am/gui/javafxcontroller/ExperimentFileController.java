package com.am.gui.javafxcontroller;

import com.am.models.ExperimentData;
import com.am.service.impl.ExperimentDataServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@FxmlView("ExperimentFileView.fxml")
@RequiredArgsConstructor
public class ExperimentFileController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SendToArduinoController sendToArduinoController;
    private final ExperimentDataServiceImpl experimentDataService;

    @FXML
    private Button apply;

    @FXML
    private TextFlow textFlow;

    @FXML
    private ListView<ExperimentData> listExperiments = new ListView<>();

    private List<ExperimentData> experimentData = new ArrayList<>();

    @FXML
    void selectFileForLoad(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("TEXT", "*.txt")
        );
        String directoryPath = sendToArduinoController.getDirectory().getText();
        if (Path.of(directoryPath).isAbsolute()) {
            fileChooser.setInitialDirectory(new File(directoryPath));
        }
        fileChooser.setTitle("Select the experiment file");
        File file = fileChooser.showOpenDialog(apply.getScene().getWindow());
        experimentData.clear();
        experimentData.addAll(experimentDataService.load(file.getAbsolutePath()));

        listExperiments.getItems().addAll(experimentData);
        listExperiments.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ExperimentData data, boolean b) {
                super.updateItem(data, b);
                if (data != null) {
                    setText(data.getTimestamp().toString());
                }
            }
        });
        listExperiments.refresh();
    }

    @FXML
    void printObject(MouseEvent event) {
        try {
            textFlow.getChildren().clear();
            if (!listExperiments.getItems().isEmpty()) {
                ExperimentData experimentData = listExperiments.getSelectionModel().getSelectedItem();
                ObservableList list = textFlow.getChildren();
                list.add(new Text(objectMapper.writeValueAsString(experimentData)));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setSelectedExperiment(ActionEvent event) {
        if (!listExperiments.getItems().isEmpty()) {
            ExperimentData experimentData = listExperiments.getSelectionModel().getSelectedItem();
            sendToArduinoController.restoreValues(experimentData.getRestrictions());
        }

    }

    @FXML
    private void initialize() {
        listExperiments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
