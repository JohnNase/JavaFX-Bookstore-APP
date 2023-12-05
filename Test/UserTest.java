import com.example.bookstorepro.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


}