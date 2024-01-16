package test.java.SystemTesting;

import com.example.bookstorepro.LogIn;
import com.example.bookstorepro.UserController;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class LoginUITest extends ApplicationTest {
    private static UserController userController;

    @BeforeEach
    public void setup() throws Exception {

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogIn.class);
    }

    private static void signUpTestUser(String firstName, String lastName, String email, String username, String role, String password, String verifyPassword) {
        userController = new UserController();
        userController.signUp(firstName, lastName, email, username, role, password, verifyPassword);
    }


    @Override
    public void start(Stage stage) throws Exception {
        // The start method is called by FxToolkit.setupApplication
    }

    @Test
    public void testLoginLibrarian() {
        signUpTestUser( "Robert", "Williams", "Robert@gmail.com", "rwilliams", "librarian", "password", "password");
        userController.saveData();
        clickOn("#usernameField").write("rwilliams");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(5000); //  a small delay to allow the GUI to update
        verifyThat("#librariansPane", NodeMatchers.isVisible());
    }

    @Test
    public void testLoginManager() {
        signUpTestUser("Susan", "Miller", "susanmiller@gmail.com", "susanmiller", "manager", "password", "password");
        userController.saveData();

        clickOn("#usernameField").write("susanmiller");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(5000);
        verifyThat("#managersPane", NodeMatchers.isVisible());
    }

    @Test
    public void testLoginAdmin() {
        signUpTestUser("James", "Wilson", "wilson324@gmail.com", "jwilson22", "administrator", "password", "password");
        userController.saveData();

        clickOn("#usernameField").write("jwilson22");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(5000);

        verifyThat("#administratorsPane", NodeMatchers.isVisible());
    }
}


