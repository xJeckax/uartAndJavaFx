package com.am.gui.javafxcontroller;

import com.am.models.ValuesRestrictionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@FxmlView("dataControl.fxml")
@RequiredArgsConstructor
public class DataControlController {
    private final ValuesRestrictionData valuesRestrictionData;
    private final SendToArduinoController sendToArduinoController;

    private final Map<Integer, Double> remember = new ConcurrentHashMap<>();

    @FXML
    void rememberValues(ActionEvent event) {
        remember.clear();
        remember.putAll(valuesRestrictionData.getValuesRestriction());
    }

    @FXML
    void restoreValues(ActionEvent event) {
        sendToArduinoController.restoreValues(remember);
    }

    @FXML
    void saveValues(ActionEvent event) {

    }

}
