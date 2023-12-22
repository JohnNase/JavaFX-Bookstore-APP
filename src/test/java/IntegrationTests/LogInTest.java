package IntegrationTests;

import com.example.bookstorepro.LogIn;
import com.example.bookstorepro.ReadData;
import com.example.bookstorepro.SignUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogInTest {
    @BeforeEach
    public void clearUserDataFile() {
        try {
            FileWriter fw = new FileWriter("UserDataMock.txt", false);
            fw.write(""); // Clearing the content of the file
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUsername() {
        // Set the username
        LogIn.setUsername("testUser");
        // Test getUsername
        assertEquals("testUser", LogIn.getUsername());
    }

    @Test
    public void testNonExistentUserAuthenticate() {
        String username = "unknown";
        String password = "password";
        ReadData.usernames = new ArrayList<>(Arrays.asList(username));
        ReadData.passwords = new ArrayList<>(Arrays.asList(password));

        LogIn login = new LogIn();
        boolean result = login.authenticate(username, password);
        assertFalse(result);  // Test when the user does not exist
    }


    @Test
    public void testDetermineUserRole() {
        // Test when the user role exists
        String existingUser = "testUser";
        ReadData.usernames = new ArrayList<>(Arrays.asList("testUser"));
        ReadData.roles = new ArrayList<>(Arrays.asList("Librarian"));

        LogIn login = new LogIn();
        String role = login.determineUserRole(existingUser);
        assertEquals("Librarian", role);

        // Test when the user role doesn't exist
        String nonExistingUser = "nonExistingUser";
        role = login.determineUserRole(nonExistingUser);
        assertNull(role);
    }
}
