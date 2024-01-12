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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Objects;
import javafx.scene.input.KeyCode;




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
    @FXML
    private ImageView adminIcon;


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
    void adminDefault(MouseEvent event)
    {
        Image adminDefault = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/admin.png")).toExternalForm());
        adminIcon.setImage(adminDefault);
    }
    @FXML
    void adminHover(MouseEvent event)
    {
        Image adminHover = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/adminHover.png")).toExternalForm());
        adminIcon.setImage(adminHover);
    }

    @FXML
    private void registerPanel()
    {
        sceneManager.switchScene("RegisterController");
    }

    @FXML
    private void adminPanel(ActionEvent event)
    {
        System.out.println("ADMIN PANEL RIGHT HERE!");
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

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try(Session session = sessionFactory.openSession())
        {
            String hql = "FROM User u WHERE u.userName = :userName AND u.userPassword = :userPassword";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userName", userName);
            query.setParameter("userPassword", userPassword);

            List<User> users = query.list();
            if (!users.isEmpty())
            {
                loginSuccessful(event);
            }
            else
            {
                errorLabel.setText("Incorrect user name or password");
            }
        }
        catch (Exception e)
        {
            System.out.println("An error occurred while logging in : " + e.getMessage());
        }
    }

    private void loginSuccessful(ActionEvent event)
    {
        name.setText("");
        password.setText("");
        sceneManager.switchScene("SongController");

    }

}
