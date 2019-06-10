package com.shahab.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class WeatherApp extends Application {
    Stage stage;
    static AnchorPane anchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        mainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(WeatherApp.class.getResource("/fxml/Ui.fxml"));
            anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().addAll(getClass().getResource("/css/style.css").toExternalForm());
            stage.setResizable(true);
            stage.setTitle("Weather Application");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static AnchorPane getAnchorPane() {
        return anchorPane;
    }
}


