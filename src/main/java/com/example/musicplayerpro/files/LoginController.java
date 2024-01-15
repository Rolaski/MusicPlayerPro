package com.example.musicplayerpro.files;
import static com.example.musicplayerpro.files.Main.sceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;
import javafx.scene.input.KeyCode;
import org.mindrot.jbcrypt.BCrypt;


public class LoginController
{

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Label errorLabel;

    @FXML
    private ImageView loginIcon;
    @FXML
    private ImageView registerIcon;

    public static String userType;
    public static boolean isPremium;


    @FXML
    void loginDefault(MouseEvent event)
    {
        Image loginDefault = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/login.png")).toExternalForm());
        loginIcon.setImage(loginDefault);
    }
    @FXML
    void loginHover(MouseEvent event)
    {
        Image loginHover = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/loginHover.png")).toExternalForm());
        loginIcon.setImage(loginHover);
    }
    @FXML
    void registerDefault(MouseEvent event)
    {
        Image registerDefault = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/register.png")).toExternalForm());
        registerIcon.setImage(registerDefault);
    }
    @FXML
    void registerHover(MouseEvent event)
    {
        Image registerHover = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/registerHover.png")).toExternalForm());
        registerIcon.setImage(registerHover);
    }

    @FXML
    private void registerPanel()
    {
        sceneManager.switchScene("RegisterController");
    }


    @FXML
    private void initialize()
    {
        password.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleLoginButton(new ActionEvent());
            }
        });

        name.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleLoginButton(new ActionEvent());
            }
        });

        checkbox.setOnAction(event -> {
            if (checkbox.isSelected()) {
                password.setDisable(true);
                password.setPromptText(password.getText());
                password.setText("");
            } else {
                password.setDisable(false);
                password.setText(password.getPromptText());
                password.setPromptText("Password");
            }
        });

    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        String userName = name.getText();
        String userPassword = password.getText();

        try {
            User user = DatabaseManager.getUserByUserName(userName);

            if (user != null && BCrypt.checkpw(userPassword, user.getUserPassword())) {
                userType = String.valueOf(user.getUserType());
                if(userType.equals("premium") || userType.equals("admin"))
                {
                    isPremium = true;
                }
                else
                {
                    isPremium = false;
                }
                loginSuccessful(event);
            } else {
                errorLabel.setText("Incorrect user name or password");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while logging in : " + e.getMessage());
        }
    }

    private void loginSuccessful(ActionEvent event) throws IOException
    {
        name.setText("");
        password.setText("");
        errorLabel.setText("");
        sceneManager.loadScene("SongController", "/com/example/musicplayerpro/MusicPlayer.fxml");
        sceneManager.switchScene("SongController");

    }

}
