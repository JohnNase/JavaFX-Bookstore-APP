import com.example.bookstorepro.SignUp;
import com.example.bookstorepro.User;
import com.example.bookstorepro.UserController;
import javafx.application.Platform;
import org.junit.jupiter.api.*;
import java.io.*;
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
    void testUserControllerInitialization() {
        assertNotNull(signUp.newUserController.getUsers());
        assertTrue(signUp.newUserController.getUsers().isEmpty());
    }

    //Test for valid inputs
    @Test
    void testAddUser_Success() {
        // Mock the UserController or provide necessary setup
        // For example, set up newUserController with a mock object

        boolean result = signUp.newUserController.signUp("Jeniffer", "Aniston", "janniston@example.com", "jan321", "ManAger", "password", "password");

        assertTrue(result);
    }

    //first name has only space
    @Test
    void testInvalidFirstName() {
        boolean result = signUp.newUserController.signUp("  ", "valid", "john@example.com", "johndoe", "user", "password", "password");

        assertFalse(result);
    }
    //first name has only symbol
    @Test
    void testInvalidFirstName2() {
        boolean result = signUp.newUserController.signUp("+", "valid", "john@example.com", "johndoe", "user", "password", "password");

        assertFalse(result);
    }

    //last name has only space
    @Test
    void testInvalidLastName() {
        boolean result = signUp.newUserController.signUp("", " ", "john@example.com", "johndoe", "user", "password", "password");

        assertFalse(result);
    }
    //last name has only symbol
    @Test
    void testInvalidLastName2() {
        boolean result = signUp.newUserController.signUp("", "*", "john@example.com", "johndoe", "user", "password", "password");
        assertFalse(result);
    }
    //username has only space
    @Test
    void testAddUser_Failure1() {

        boolean result = signUp.newUserController.signUp(null, null, "jlo@example.com", "jlo", "Manager", "password", null);

        assertFalse(result);
        // Add more assertions if needed
    }

    //first name is null, last name is null, verify password is null
    @Test
    void testAddUser_Failure2() {
        boolean result = signUp.newUserController.signUp(null, null, "jlo@example.com", "jlo", "Manager", "password", null);

        assertFalse(result);
        // Add more assertions if needed
    }

    //email is invalid
    @Test
    void testAddUser_Failure3() {

        boolean result = signUp.newUserController.signUp("Emma", "Stone", "emma.com", "emmaStone12", "administrator", "password", "password");
        assertFalse(result);
    }

    //username is empty
    @Test
    void testAddUser_Failure4() {
        boolean result = signUp.newUserController.signUp("Emma", "Stone", "emma.com", "  ", "administrator", "password", "password");
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
    @Test
    void testWriteAndRead() {
        // Creating sample data for testing
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Margottt", "Robbie", "margotRobbie@example.com", "barbie", "Manager", "password"));
        users.add(new User("Emma", "Smith", "emma@example.com", "emma213", "Librarian", "password"));

        // Setting up the UserController with sample data
        signUp.newUserController.setUsers(users);

        try {
            // Testing the write method
            signUp.write();

            // Close the ObjectOutputStream
            signUp.output.close();

            // Testing the read method
            signUp.read();

            // Get the users after read
            ArrayList<User> readUsers = signUp.newUserController.getUsers();

            // Check each element for equality
            for (int i = 0; i < users.size(); i++) {
                assertEquals(users.get(i), readUsers.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReadFileNotFound() {
        // Ensure IOException is thrown when the file doesn't exist
        assertThrows(IOException.class, () -> {
            // Provide a file path that doesn't exist to force a FileNotFoundException
            signUp.input = new ObjectInputStream(new FileInputStream("NonExistentFile.dat"));
            signUp.read();
        });
    }

    // Test when empty strings are passed as inputs
    @Test
    void testAddUser_EmptyStrings() {
        boolean result = signUp.newUserController.signUp("", "", "", "", "", "", "");
        assertFalse(result);
    }

    @Test
    void testPrintUsers() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Alice", "Smith", "alice@example.com", "alice", "librarian", "password"));
        users.add(new User("Bob", "Johnson", "bob@example.com", "bob", "administrator", "password"));

        signUp.newUserController.setUsers(users);
        signUp.newUserController.printUsers();

        String expectedOutput = "Name: Alice Last Name: Smith Email: alice@example.com Username: alice Role: librarian"
                + System.lineSeparator() +
                "Name: Bob Last Name: Johnson Email: bob@example.com Username: bob Role: administrator"
                + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }

    // Test for the maximum length of inputs
    @Test
    void testAddUser_MaxLength() {
        // Max-length strings for each input field
        boolean result = signUp.newUserController.signUp(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ", // 52 characters
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ", // 52 characters
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz0123456789abcdefghijklmnopqrstuvwxyz0123456789@example.com", // 100 characters
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz0123456789", // 50 characters
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ", // 52 characters
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ", // 52 characters
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"  // 52 characters
        );
        assertFalse(result);
    }


    // Test invalid file format or corrupted file
    @Test
    void testInvalidFileFormat() {
        // Prepare invalid serialized data by creating a byte array with arbitrary content
        byte[] invalidSerializedData = "InvalidSerializedData".getBytes();

        try {
            // Use ByteArrayInputStream to simulate an input stream with invalid serialized data
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(invalidSerializedData);
            signUp.input = new ObjectInputStream(byteArrayInputStream);

            // The read operation should throw a ClassNotFoundException
            assertThrows(ClassNotFoundException.class, () -> {
                signUp.read();
            });
        } catch (IOException e) {
            // Handle any IO exceptions during the setup
            e.printStackTrace();
        }
    }



}
