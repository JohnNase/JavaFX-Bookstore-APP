import com.example.bookstorepro.SignUp;
import com.example.bookstorepro.User;
import javafx.application.Platform;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest {
    private SignUp signUp;


    @AfterEach
    void cleanUp() {
        File file = new File("History.dat");
        if (file.exists()) {
            file.delete(); // Clean up the file after each test
        }
    }
    @BeforeAll
    public static void setUpJavaFX() {
        Platform.startup(() -> {
        });
    }

    @AfterAll
    public static void tearDownJavaFX() {
        Platform.exit();
    }

    @BeforeEach
    void setUp() {
        signUp = new SignUp();
    }

    @Test
    void testAddUser_Success() {
        // Mock the UserController or provide necessary setup
        // For example, set up newUserController with a mock object

        boolean result = signUp.newUserController.signUp("John", "Doe", "john@example.com", "johndoe", "ManAger", "password", "password");

        assertTrue(result);
    }

    //first name and last name are only spaces
    @Test
    void testAddUser_Failure() {
        // Test the failure scenario where not all fields are filled
        boolean result = signUp.newUserController.signUp("  ", "          ", "john@example.com", "johndoe", "user", "password", "password");

        assertFalse(result);
        // Add more assertions if needed
    }

    //first name is null, last name is space, verify password is null
    @Test
    void testAddUser_Failure2() {
        // Test the failure scenario where not all fields are filled
        boolean result = signUp.newUserController.signUp(null, "          ", "john@example.com", "johndoe", "user", "password", null);

        assertFalse(result);
        // Add more assertions if needed
    }

    //email is invalid
    @Test
    void testAddUser_Failure3() {
        // Test the failure scenario where not all fields are filled
        boolean result = signUp.newUserController.signUp("Emma", "Stone", "emma.com", "emmaStone12", "administrator", "password", "password");
        assertFalse(result);
    }

    //username is empty
    @Test
    void testAddUser_Failure4() {
        boolean result = signUp.newUserController.signUp("Emma", "Stone", "emma.com", "", "administrator", "password", "password");
        assertFalse(result);
    }

    //role is invalid
    @Test
    void testAddUser_Failure5() {
        boolean result = signUp.newUserController.signUp("Bradley", "Cooper", "bradley@gmail.com", "justBradley134", "user", "password", "password");
        assertFalse(result);
    }

    //passwords do not match
    @Test
    void testAddUser_Failure6() {
        boolean result = signUp.newUserController.signUp("Bradley", "Cooper", "bradley@gmail.com", "justBradley134", "user", "password", "password22");
        assertFalse(result);
    }

    //password is empty
    @Test
    void testAddUser_Failure7() {
        boolean result = signUp.newUserController.signUp("Bradley", "Cooper", "brad@gmail.com", "justBradley134", "user", "", "password22");
        assertFalse(result);
    }

}
