package com.example.musicplayerpro.files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.Objects;

import static com.example.musicplayerpro.files.Main.sceneManager;


public class AdminController {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> columnID;

    @FXML
    private TableColumn<User, String> columnName;

    @FXML
    private TableColumn<User, UserType> columnUserType;


    @FXML
    private TextField updateId;
    @FXML
    private TextField updateName;
    @FXML
    private ChoiceBox<UserType> choiceBox;
    @FXML
    private TextField deleteId;
    @FXML
    private TextField deleteName;
    @FXML
    private ImageView previous;
    @FXML
    private ImageView updateIcon;
    @FXML
    private ImageView deleteIcon;
    private User selectedUser;

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
    void updateDefault()
    {
        Image defaultImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/update.png")).toExternalForm());
        updateIcon.setImage(defaultImage);
    }
    @FXML
    void updateHover()
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/updateHover.png")).toExternalForm());
        updateIcon.setImage(hoverImage);
    }
    @FXML
    void deleteDefault()
    {
        Image defaultImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/delete.png")).toExternalForm());
        deleteIcon.setImage(defaultImage);
    }
    @FXML
    void deleteHover()
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/deleteHover.png")).toExternalForm());
        deleteIcon.setImage(hoverImage);
    }


    @FXML
    private void initialize()
    {
        choiceBox.setValue(UserType.user);
        // initialization tableView
        columnID.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        refreshTableView();

        //setting the value from userType to choiceBox
        choiceBox.setItems(FXCollections.observableArrayList(UserType.values()));

        //which line the user will press
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                selectedUser = newSelection;

                updateId.setText(String.valueOf(newSelection.getIdUser()));
                updateName.setText(newSelection.getUserName());
                choiceBox.setValue(newSelection.getUserType());

                deleteId.setText(String.valueOf(newSelection.getIdUser()));
                deleteName.setText(newSelection.getUserName());
            }
        });

    }

    //refreshing tableView
    private void refreshTableView()
    {
        List<User> userList = DatabaseManager.getAllUsers();
        tableView.setItems(FXCollections.observableArrayList(userList));
    }

    //method for update database button
    @FXML
    private void handleUpdateButton()
    {
        try
        {
            int userId = Integer.parseInt(updateId.getText());
            String newName = updateName.getText().trim();
            UserType newUserType = choiceBox.getValue();

            if (!isIdUnique(userId) || !isNameValid(newName) || !isUserTypeValid(newUserType))
            {
                System.out.println("Nieprawidłowe dane.");
                return;
            }

            //checking whether the user entered data manually
            if (tableView.getSelectionModel().getSelectedItem() != null)
            {
                //use selected by mouse, user
                selectedUser = tableView.getSelectionModel().getSelectedItem();
            }
            else
            {
                //searching for user in the database
                selectedUser = DatabaseManager.getUserById(userId);
            }

            //operation on the selected user
            if (selectedUser != null)
            {
                selectedUser.setUserName(newName);
                selectedUser.setUserType(newUserType);

                DatabaseManager.updateUser(selectedUser);
                refreshTableView();
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Incorrect ID format");
        }
    }

    //method for checking if ID isn't repeated
    private boolean isIdUnique(int userId)
    {
        User existingUser = DatabaseManager.getUserById(userId);
        return existingUser == null || existingUser.getIdUser() == userId;
    }

    //method for checking name validation
    private boolean isNameValid(String name)
    {
        return name.matches("^[a-zA-Z0-9]+$");
    }

    //method for checking if userType is null
    private boolean isUserTypeValid(UserType userType)
    {
        return userType != null;
    }

    //method for delete user in database by button
    @FXML
    private void handleDeleteButton()
    {
        try
        {
            int userId = Integer.parseInt(deleteId.getText());

            //checking whether id exist
            User userToDelete = DatabaseManager.getUserById(userId);

            if (userToDelete != null)
            {
                DatabaseManager.deleteUser(userToDelete);
                refreshTableView();
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Incorrect ID format");
        }
    }
    //method to return to the previous page
    @FXML
    private void goToLoginPanel(ActionEvent event)
    {
        sceneManager.switchScene("LoginController");
    }

}
