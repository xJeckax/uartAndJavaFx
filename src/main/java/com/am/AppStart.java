package com.am;

import com.am.gui.JavaFXApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args)  {
        Application.launch(JavaFXApplication.class, args);
    }
}