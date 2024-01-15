package test.java.SystemTesting;

import com.example.bookstorepro.AdministratorFiles.AdministratorGUI;
import com.example.bookstorepro.User;
import com.example.bookstorepro.UserController;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;

import static com.example.bookstorepro.AdministratorFiles.AdministratorGUI.userTable;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class AdministratorGUITest extends ApplicationTest {
    private static UserController userController;
    @Override
    public void start(Stage stage) throws Exception {
        new AdministratorGUI().start(stage);
    }

    @BeforeEach
    public void setup(){
        userController = new UserController();
        signUpTestUser("Galileo", "Galilei", "g@example.com", "ggalilei", "Manager", "password", "password");
        signUpTestUser("Isaac", "Newton", "isaac@gmail.com", "inewton", "manager", "password", "password");
        signUpTestUser("Albert", "Einstein", "albert@gmail.com", "aeinstein", "administrator", "password", "password");
        signUpTestUser("Nikola", "Tesla", "ntesla@gmail.com", "ntesla", "librarian", "password", "password");
        signUpTestUser("Marie", "Curie", "marie@example.com", "mcurie", "manager", "password", "password");
        signUpTestUser("Charles", "Darwin", "chdarwin@gmail.com", "cdarwin", "administrator", "password", "password");
        signUpTestUser("Stephen", "Hawking", "shawking@hotmail.com", "shawking", "librarian", "password", "password");
        signUpTestUser("Ada", "Lovelace", "ada@hotmail.com", "alovelace", "manager", "password", "password");
        userController.saveData();
    }

    private static void signUpTestUser(String firstName, String lastName, String email, String username, String role, String password, String verifyPassword) {
        userController.signUp(firstName, lastName, email, username, role, password, verifyPassword);
        userController.saveData();
    }

    @Test
    public void testChangeUserRoleToLibrarian() throws FileNotFoundException {
          clickOn("#ManageUsersButton");
        lookup("#administratorsPane").query();
        TableView.TableViewSelectionModel selectionModel =
                userTable.getSelectionModel();
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        interact(() -> {
            userTable.getSelectionModel().selectFirst();
        });
        sleep(4000);
        clickOn("#changeRoleButton");
        sleep(4000);
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
            userTable.getSelectionModel().select(3);
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
