package com.am.gui.controller;

import com.am.serialport.data.SerialPortData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FxmlView("index.fxml")
public class IndexController {

    private final FxWeaver fxWeaver;
    private final SerialPortData data;

    @FXML
    private Menu indexMenu;

    @FXML
    private MenuBar indexMenuBar;

    @FXML
    private MenuItem indexMenuItem1;

    @FXML
    void setUpUart(ActionEvent event) {

        Parent root = fxWeaver.loadView(UartController.class);

        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);

        Group group = new Group();
        group.getChildren().add(borderPane);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }
}
