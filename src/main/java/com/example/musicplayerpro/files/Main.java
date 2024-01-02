package com.example.musicplayerpro.files;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Objects;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/musicplayerpro/MusicPlayer.fxml")));
            primaryStage.setTitle("Music Player PRO");
            InputStream iconStream = getClass().getResourceAsStream("/com/example/images/theme.png");
            Image Icon = new Image(iconStream);
            primaryStage.getIcons().add(Icon);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}