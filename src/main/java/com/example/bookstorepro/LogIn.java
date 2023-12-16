package com.example.bookstorepro;
import com.example.bookstorepro.AdministratorFiles.AdministratorGUI;
import com.example.bookstorepro.LibrarianFiles.LibrariansGUI;
import com.example.bookstorepro.ManagerFiles.ManagerGUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogIn extends Application {
    private static String username;
    public static void setUsername(String username) {
        LogIn.username = username;
    }
    public static String getUsername() {
        return username;
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome!");
        Font font = Font.loadFont(new FileInputStream("lib/Astrella.ttf"), 23);
        sceneTitle.setId("welcome-text");
        sceneTitle.setFont(font);
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Log in");
        grid.add(btn, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);
        grid.setStyle("-fx-background-color: #EAE1DF");

        pwBox.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> handleLogin(userTextField.getText(), pwBox.getText(), primaryStage, actionTarget));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin(String username, String password, Stage primaryStage, Text actionTarget) {
        boolean loginSuccessful = authenticate(username, password);
        if (loginSuccessful) {
            String role = determineUserRole(username);
            launchAppropriateGUI(role, primaryStage);
        } else {
            actionTarget.setText("Sign in failed!");
        }
    }

    private boolean authenticate(String username, String password) {
        try {
            ReadData.read();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        for (int i = 0; i < ReadData.users.size(); i++) {
            if (username.equals(ReadData.usernames.get(i)) && password.equals(ReadData.passwords.get(i))) {
                setUsername(username);
                return true;
            }
        }
        return false;
    }

    private String determineUserRole(String username) {
        int index = ReadData.usernames.indexOf(username);
        return index != -1 ? ReadData.roles.get(index) : null;
    }

    private void launchAppropriateGUI(String role, Stage primaryStage) {
        if (role != null) {
            switch (role) {
                case "Librarian":
                    LibrariansGUI librarian = new LibrariansGUI();
                    launchGUI(librarian, primaryStage);
                    break;
                case "Manager":
                    ManagerGUI manager = new ManagerGUI();
                    launchGUI(manager, primaryStage);
                    break;
                case "Administrator":
                    AdministratorGUI admin = new AdministratorGUI();
                    launchGUI(admin, primaryStage);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }
        }
    }

    private void launchGUI(Application application, Stage primaryStage) {
        try {
            application.start(new Stage());
            primaryStage.close(); // Close the login window after successful login
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
