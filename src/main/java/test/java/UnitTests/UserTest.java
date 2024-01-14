package test.java.UnitTests;

import com.example.bookstorepro.User;
import com.example.bookstorepro.UserController;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertEquals("Brad Pitt", user.getFirstName());
        assertEquals("Pitt", user.getLastName());
        assertEquals("brad.pitt@example.com", user.getEmail());
        assertEquals("bradpitt", user.getUsername());
        assertEquals("Librarian", user.getRole());
        assertEquals("password", user.getPassword());
    }



    @Test
    public void testUserConstructorWithNullFirstName() {
        assertThrows(NullPointerException.class, () -> {
            new User(null, "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        });
    }

    @Test
    public void testUserConstructorWithNullEmail() {
        assertThrows(NullPointerException.class, () -> {
            new User("Brad Pitt", "Pitt", null, "bradpitt", "Librarian", "password");
        });
    }

    @Test
    public void testUserConstructorWithNullUsername() {
        assertThrows(NullPointerException.class, () -> {
            new User("Brad Pitt", "Pitt", "brad.pitt@example.com", null, "Librarian", "password");
        });
    }

    @Test
    public void testUserConstructorWithNullRole() {
        assertThrows(NullPointerException.class, () -> {
            new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", null, "password");
        });
    }

    @Test
    public void testUserConstructorWithNullPassword() {
        assertThrows(NullPointerException.class, () -> {
            new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", null);
        });
    }

    @Test
    public void testSetFirstName() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setFirstName("George Clooney");
        assertEquals("George Clooney", user.getFirstName());
    }

    @Test
    public void testSetFirstNameWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setFirstName(null);
        });
    }

    @Test
    public void testSetLastName() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setLastName("Clooney");
        assertEquals("Clooney", user.getLastName());
    }

    @Test
    public void testSetLastNameWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setLastName(null);
        });
    }

    @Test
    public void testSetEmail() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setEmail("george.clooney@example.com");
        assertEquals("george.clooney@example.com", user.getEmail());
    }

    @Test
    public void testSetEmailWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setEmail(null);
        });
    }

    @Test
    public void testSetUsername() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setUsername("georgeclooney");
        assertEquals("georgeclooney", user.getUsername());
    }

    @Test
    public void testSetUsernameWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setUsername(null);
        });
    }

    @Test
    public void testSetRole() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setRole("Admin");
        assertEquals("Admin", user.getRole());
    }

    @Test
    public void testSetRoleWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setRole(null);
        });
    }

    @Test
    public void testSetPassword() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetPasswordWithNull() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        assertThrows(NullPointerException.class, () -> {
            user.setPassword(null);
        });
    }

    @Test
    public void testToString() {
        User user = new User("Brad Pitt", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        String expected = "Name: Brad Pitt Last Name: Pitt Email: brad.pitt@example.com Username: bradpitt Role: Librarian";
        assertEquals(expected, user.toString());
    }

    @Test
    public void testEqualsMethod() {
        User user1 = new User("Brad", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        User user2 = new User("Brad", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        User user3 = new User("George", "Clooney", "george@example.com", "george", "Actor", "ocean");

        // Test equality with same object
        assertEquals(user1, user1);

        // Test equality with equivalent object
        assertEquals(user1, user2);

        // Test inequality with different object
        assertNotEquals(user1, user3);

        // Test inequality with null
        assertNotEquals(user1, null);
    }

    @Test
    public void testSerializationDeserialization() {
        User originalUser = new User("Brad", "Pitt", "brad.pitt@example.com", "bradpitt", "Librarian", "password");
        User deserializedUser = null;

        try {
            // Serialize the object
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(originalUser);
            objectOutputStream.close();

            // Deserialize the object
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            deserializedUser = (User) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Check if deserialized object equals the original object
        assertNotNull(deserializedUser);
        assertEquals(originalUser, deserializedUser);
    }

    //Test for user controller helper methods
    @Test
    void testIsNullOrEmpty_NullInput() {
        assertTrue(UserController.isNullOrEmpty(null));
    }

    @Test
    void testIsNullOrEmpty_EmptyInput() {
        assertTrue(UserController.isNullOrEmpty(""));
    }

    @Test
    void testIsNullOrEmpty_NonEmptyInput() {
        assertFalse(UserController.isNullOrEmpty("someValue"));
    }

    @Test
    void testIsValidRole_ValidRoles() {
        assertTrue(UserController.isValidRole("administrator"));
        assertTrue(UserController.isValidRole("manager"));
        assertTrue(UserController.isValidRole("librarian"));
    }

    @Test
    void testIsValidRole_InvalidRole() {
        assertFalse(UserController.isValidRole("invalidRole"));
    }

    @Test
    void testContainsOnlySpaces_OnlySpaces() {
        assertTrue(UserController.containsOnlySpaces("    "));
    }

    @Test
    void testContainsOnlySpaces_NonSpaceString() {
        assertFalse(UserController.containsOnlySpaces("someNonSpaceString"));
    }

    @Test
    void testContainsSymbol_ContainsSymbols() {
        assertTrue(UserController.containsSymbol("str@ng$"));
    }

    @Test
    void testContainsSymbol_NoSymbols() {
        assertFalse(UserController.containsSymbol("noSymbolString"));
    }

    @Test
    void testIsValidEmail_ValidEmail() {
        assertTrue(UserController.isValidEmail("example@example.com"));
    }

    @Test
    void testIsValidEmail_InvalidEmail() {
        assertFalse(UserController.isValidEmail("invalidEmail"));
    }

    @Test
    void testGetAndSetUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Alice", "Smith", "alice@example.com", "alice", "librarian", "password"));
        users.add(new User("Bob", "Johnson", "bob@example.com", "bob", "administrator", "password"));

        UserController userController = new UserController();
        userController.setUsers(users);

        ArrayList<User> retrievedUsers = userController.getUsers();

        assertEquals(users.size(), retrievedUsers.size());
        assertTrue(retrievedUsers.containsAll(users));
    }

    @Test
    void testDeleteUser() {
        UserController userController = new UserController();
        User userToDelete = new User("Alice", "Smith", "alice@example.com", "alice", "librarian", "password");
        userController.setUsers(new ArrayList<>(List.of(userToDelete)));

        userController.deleteUser(userToDelete);

        assertFalse(userController.getUsers().contains(userToDelete));
    }

    @Test
    void testDeleteNonExistingUser() {
        // Create a UserController instance
        UserController userController = new UserController();

        // Add a user to the controller
        User existingUser = new User("John", "Doe", "john@example.com", "johndoe", "Librarian", "password");
        userController.setUsers(new ArrayList<>(List.of(existingUser)));

        // Attempt to delete a user that doesn't exist in the list
        User nonExistingUser = new User("Jane", "Smith", "jane@example.com", "janesmith", "Manager", "pass123");

        assertDoesNotThrow(() -> userController.deleteUser(nonExistingUser));

        // Ensure that the existing user is still in the list and the non-existing user hasn't been added
        assertTrue(userController.getUsers().contains(existingUser));
        assertFalse(userController.getUsers().contains(nonExistingUser));
    }

    @Test
    void testUpdateRole() {
        UserController userController = new UserController();
        User user = new User("Alice", "Smith", "alice@example.com", "alice", "librarian", "password");
        userController.setUsers(new ArrayList<>(List.of(user)));

        userController.updateRole(user, "administrator");

        assertEquals("administrator", user.getRole());
    }

    @Test
    void testSaveData_SuccessfulWrite() {
        // Create a UserController instance and some sample data
        UserController userController = new UserController();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Alice", "Smith", "alice@example.com", "alice", "librarian", "password"));
        users.add(new User("Bob", "Johnson", "bob@example.com", "bob", "administrator", "password"));
        userController.setUsers(users);

        try {
            // Call the method being tested
            userController.saveData();

            // Check if the file was created
            File file = new File("History.dat");
            assertTrue(file.exists());

            // Read the data back from the file to verify
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("History.dat"));
            ArrayList<User> readUsers = (ArrayList<User>) input.readObject();
            input.close();

            assertEquals(users.size(), readUsers.size());
            assertTrue(readUsers.containsAll(users));
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Clean up: delete the test file after the test
            File file = new File("History.dat");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    void testSaveData_ExceptionHandling() {
        // Create a UserController instance without setting users
        UserController userController = new UserController();

        // Call the method being tested without setting users
        assertDoesNotThrow(userController::saveData);
    }
}
