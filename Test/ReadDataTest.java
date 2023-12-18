import com.example.bookstorepro.ReadData;
import com.example.bookstorepro.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReadDataTest {

    @BeforeEach
    public void setup() {
        ReadData.users = new ArrayList<>();
    }

    private void initUsersList() {
        ReadData.users = new ArrayList<>();
        // Add a user to the list
        ReadData.users.add(new User("Charlotte", "Dobre", "chdobre@yahoo.com", "chdobre", "librarian", "pass"));
        ReadData.roles.add("manager");

    }

    // Helper method to manually create a test file
    private void createTestFile(String filename, Object data) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(data);
        }
    }

    // Helper method to manually clear the test file
    private void clearTestFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, false);
        fw.write(""); // Clearing the content of the file
        fw.close();
    }

    @Test
    public void testReadWithData() {
        try {
            ArrayList<User> sampleUsers = new ArrayList<>();
            sampleUsers.add(new User("david", "dobrick", "ddobrick@gmail.com", "ddobrick85", "manager", "pass"));
            createTestFile("history.dat", sampleUsers);

            ReadData.read();

            // Check if the arrays are populated correctly
            assertEquals(1, ReadData.usernames.size());
            assertEquals("ddobrick85", ReadData.usernames.get(0));

            assertEquals(1, ReadData.passwords.size());
            assertEquals("pass", ReadData.passwords.get(0));

            assertEquals(1, ReadData.roles.size());
            assertEquals("manager", ReadData.roles.get(0));

            clearTestFile("history.dat");

        } catch (IOException e) {
            fail("Exception not expected here");
        }
    }

    @Test
    public void testUpdateUserRole_UserExists() throws FileNotFoundException {
        initUsersList();

        try {
            // Call updateUserRole for an existing user
            ReadData.updateUserRole("chdobre", "manager");

            // Check if the role is updated
            assertEquals("manager", ReadData.roles.get(0));

            // Ensure the file is updated with the new role
            BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if(userData[0].equals("chdobre")) {
                    assertEquals("manager", userData[2]);
                    break;
                }
            }
            br.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
}
