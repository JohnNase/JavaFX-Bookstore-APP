import com.example.bookstorepro.LogIn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {
    @Test
    public void testGetUsername() {
        // Set the username
        LogIn.username = "testUser";
        // Test getUsername
        assertEquals("testUser", LogIn.getUsername());
    }


}