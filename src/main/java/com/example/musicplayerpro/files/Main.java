package com.example.musicplayerpro.files;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application
{
    public static SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws IOException {
        sceneManager = SceneManager.getInstance(primaryStage);
        sceneManager.loadScene("LoginController", "/com/example/musicplayerpro/LoginPanel.fxml");
        sceneManager.loadScene("SongController", "/com/example/musicplayerpro/MusicPlayer.fxml");
        sceneManager.loadScene("RegisterController", "/com/example/musicplayerpro/RegisterPanel.fxml");


        sceneManager.switchScene("LoginController");
        InputStream iconStream = getClass().getResourceAsStream("/com/example/images/theme.png");
        Image icon = new Image(iconStream);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("MusicPlayerPro");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}