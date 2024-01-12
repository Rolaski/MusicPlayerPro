package com.example.musicplayerpro.files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Objects;

import static com.example.musicplayerpro.files.Main.sceneManager;

public class RegisterController {

    @FXML
    private TextField name;
    @FXML
    private PasswordField password, passwordAgain;
    @FXML
    private CheckBox checkbox;

    @FXML
    private Label errorLabelUser, errorLabelPassword;
    @FXML
    private ImageView registerIcon;
    @FXML
    private ImageView previous;


    @FXML
    void previousDefault()
    {
        Image defaultImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/previous.png")).toExternalForm());
        previous.setImage(defaultImage);
    }
    @FXML
    void previousHover()
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/previousHover.png")).toExternalForm());
        previous.setImage(hoverImage);
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
    private void initialize()
    {
        checkbox.setOnAction(event -> {
            if (checkbox.isSelected()) {
                passwordAgain.setDisable(true);
                passwordAgain.setPromptText(passwordAgain.getText());
                passwordAgain.setText("");
            } else {
                passwordAgain.setDisable(false);
                passwordAgain.setText(passwordAgain.getPromptText());
                passwordAgain.setPromptText("Password");
            }
        });
    }
    //method for passwords validation
    private boolean checkUserValidity()
    {
        String nameText = name.getText();

        if (nameText.isEmpty())
        {
            errorLabelUser.setText("Pole 'Name' nie może być puste");
            return false;
        }
        else if (!isValidUserName(nameText))
        {
            errorLabelUser.setText("Nieprawidłowe znaki w polu 'Name'");
            return false;
        }
        else
        {
            errorLabelUser.setText("");
            return true;
        }
    }

    private boolean isValidUserName(String userName)
    {
        //accepted characters are: a-z, A-Z, 0-9, _-?!
        return userName.matches("[a-zA-Z0-9_\\-!\\?]+");
    }

    //method for passwords validation
    private boolean checkPasswordValidity()
    {
        String passwordText = password.getText();
        String passwordAgainText = passwordAgain.getText();

        if (passwordText.isEmpty() || passwordAgainText.isEmpty())
        {
            errorLabelPassword.setText("Pole nie może być puste");
            return false;
        }
        else if (!passwordText.equals(passwordAgainText))
        {
            errorLabelPassword.setText("Hasła są różne");
            return false;
        }
        else if (passwordText.length() < 6)
        {
            errorLabelPassword.setText("Hasło musi mieć co najmniej 5 znaków");
            return false;
        }
        else if (!containsSpecialCharacter(passwordText))
        {
            errorLabelPassword.setText("Hasło musi zawierać co najmniej 1 znak specjalny");
            return false;
        }
        else
        {
            errorLabelPassword.setText("");
            return true;
        }
    }
    //method for special characters in password
    private boolean containsSpecialCharacter(String password)
    {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
        for (char c : password.toCharArray())
        {
            if (specialCharacters.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    //method for check validation
    //if all is correct -> add user to database
    @FXML
    private void registerSuccessful(ActionEvent event)
    {
        if(checkUserValidity() && checkPasswordValidity())
        {
            User user = new User();
            user.setUserName(name.getText());
            String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
            user.setUserPassword(hashedPassword);
            user.setUserType(UserType.user);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            try {
                // Zapis nowego użytkownika do bazy danych
                session.save(user);

                // Zatwierdzenie transakcji
                transaction.commit();

                // Informacja o udanej rejestracji
                errorLabelUser.setText("Rejestracja udana!");
            } catch (Exception e) {
                // Obsługa błędu, np. wypisanie na konsoli
                e.printStackTrace();

                // Wycofanie transakcji w przypadku błędu
                transaction.rollback();

                // Informacja o błędzie rejestracji
                errorLabelUser.setText("Błąd podczas rejestracji");
            } finally {
                // Zamknięcie sesji
                session.close();
            }

        }
    }

    //method to return to the previous page
    @FXML
    private void goToLoginPanel(ActionEvent event)
    {
        sceneManager.switchScene("LoginController");
    }
}
