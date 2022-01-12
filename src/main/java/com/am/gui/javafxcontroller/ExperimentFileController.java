package com.am.gui.javafxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Service;

@Service
@FxmlView("")
public class ExperimentFileController {
    @FXML
    private Button apply;

    @FXML
    private ListView<?> listExperiments;

    @FXML
    void setSelectedExperiment(ActionEvent event) {

    }
}
