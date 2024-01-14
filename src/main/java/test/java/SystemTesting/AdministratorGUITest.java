package test.java.SystemTesting;

import com.example.bookstorepro.AdministratorFiles.AdministratorGUI;
import com.example.bookstorepro.ReadData;
import com.example.bookstorepro.User;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;

import static com.example.bookstorepro.AdministratorFiles.AdministratorGUI.dialog;
import static com.example.bookstorepro.AdministratorFiles.AdministratorGUI.userTable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class AdministratorGUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new AdministratorGUI().start(stage);
    }

    @Test
    public void testChangeUserRoleToLibrarian() throws FileNotFoundException {
        clickOn("#ManageUsersButton");
        lookup("#administratorsPane").query();
        TableView.TableViewSelectionModel selectionModel =
                userTable.getSelectionModel();
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        interact(() -> {
            userTable.getSelectionModel().selectLast();
        });
        sleep(500);
        clickOn("#changeRoleButton");
        sleep(1000);
        waitForFxEvents();
        clickOn("#okButton");
        sleep(1000);
        System.out.println(selectedUser);
        type(KeyCode.ESCAPE);
        sleep(1000);
    }

    @Test
    public void testDeleteUser(){
        clickOn("#ManageUsersButton");
        lookup("#administratorsPane").query();
        TableView.TableViewSelectionModel selectionModel =
                userTable.getSelectionModel();
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        interact(() -> {
            userTable.getSelectionModel().selectLast();
        });
        sleep(500);
        clickOn("#deleteUserButton");
        sleep(1000);
        waitForFxEvents();
        sleep(1000);
        type(KeyCode.ESCAPE);
        sleep(1000);
    }



}
