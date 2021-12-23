package com.am.gui;

import com.am.AppStart;
import com.am.gui.javafxcontroller.IndexController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {
    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(AppStart.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(IndexController.class);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);

        Group group = new Group();
        group.getChildren().add(borderPane);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Artificial Uterus");
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        this.applicationContext.stop();
        Platform.exit();
    }
}
