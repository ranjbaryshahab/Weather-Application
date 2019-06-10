package com.shahab.weather.service;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

/**
 * connected to url
 */
public class Url_Connection {
    private URL url ;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }


    public Boolean connection(URL url){
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String openStream(URL url){
        String inline = "";
        Scanner sc ;
        try {
            sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline = inline.concat(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Exist");
            alert.setHeaderText("City is not exist ... !");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
            }
        }

        return inline;

    }
}
