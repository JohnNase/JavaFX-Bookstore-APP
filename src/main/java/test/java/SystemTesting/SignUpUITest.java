package test.java.SystemTesting;

import com.example.bookstorepro.LogIn;
import com.example.bookstorepro.SignUp;
import com.example.bookstorepro.UserController;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignUpUITest extends ApplicationTest {

    @BeforeAll
    public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogIn.class);
        FxToolkit.setupApplication(SignUp.class);

        signUpTestUser();
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    @Test
    public void testSignUpSuccess() {
        clickOn("#firstNameTF").write("Sierra");
        clickOn("#lastNameTF").write("Mendes");
        clickOn("#emailTF").write("smendes@example.com");
        clickOn("#usernameTF").write("smendes");
        clickOn("#roleTF").write("librarian");
        clickOn("#passwordTF").write("password");
        clickOn("#verifyPasswordTF").write("password");
        clickOn("#signUpButton");

        assertEquals("User created!", SignUp.successString);
    }



    private void signUpTestUser() {
        UserController userController = new UserController();
        userController.signUp("Sierra", "Mendes", "smendes@example.com", "smendes", "librarian", "password", "password");
        userController.saveData();
    }


}
