package test.java.UnitTests;

import com.example.bookstorepro.LibrarianFiles.Librarian;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {

    @Test
    public void testDefaultConstructor() {
        Librarian librarian = new Librarian();
        assertNull(librarian.getUsername());
        assertNull(librarian.getPassword());
        assertNull(librarian.getRole());
        assertEquals(0.0, librarian.getPerformance());
    }

    @Test
    public void testSetters() {
        Librarian librarian = new Librarian();

        librarian.setUsername("newUsername");
        librarian.setPassword("newPassword");
        librarian.setRole("librarian");

        assertEquals("newUsername", librarian.getUsername());
        assertEquals("newPassword", librarian.getPassword());
        assertEquals("librarian", librarian.getRole());
    }

    @Test
    public void testGetPerformance() {
        Librarian librarian = new Librarian();
        assertEquals(0.0, librarian.getPerformance());
        librarian.setPerformance(95.5);
        assertEquals(95.5, librarian.getPerformance());
    }

    @Test
    public void testParameterizedConstructorAndGetters() {
        String username = "testUser";
        String password = "testPassword";
        String role = "librarian";
        Librarian librarian = new Librarian(username, password, role);

        assertEquals(username, librarian.getUsername());
        assertEquals(password, librarian.getPassword());
        assertEquals(role, librarian.getRole());
        assertEquals(0.0, librarian.getPerformance());
    }


    @Test
    public void testPerformanceForDifferentRoles() {
        Librarian librarian = new Librarian("user1", "pass1", "librarian");

        // Simulate different roles affecting performance
        librarian.setPerformance(50.0);
        assertEquals(50.0, librarian.getPerformance());

        librarian.setRole("admin");
        librarian.setPerformance(70.0);
        assertEquals(70.0, librarian.getPerformance());
    }


    @Test
    public void testPerformanceSetter() {
        Librarian librarian = new Librarian();

        double performance = 95.5;
        librarian.setPerformance(performance);
        assertEquals(performance, librarian.getPerformance());
    }

    @Test
    public void testNullGetters() {
        Librarian librarian = new Librarian();

        assertNull(librarian.getUsername());
        assertNull(librarian.getPassword());
        assertNull(librarian.getRole());
    }

    @Test
    public void testSettersWithNullValues() {
        Librarian librarian = new Librarian();

        librarian.setUsername(null);
        librarian.setPassword(null);
        librarian.setRole(null);
        assertNull(librarian.getUsername());
        assertNull(librarian.getPassword());
        assertNull(librarian.getRole());
    }


    @Test
    public void testEqualsAndHashCodeForInequality() {
        Librarian librarian1 = new Librarian("user1", "pass1", "librarian");
        Librarian librarian2 = new Librarian("user2", "pass2", "librarian");

        assertNotEquals(librarian1, librarian2);
        assertNotEquals(librarian1.hashCode(), librarian2.hashCode());
    }

    @Test
    public void testEqualsAndHashCode() {
        Librarian librarian1 = new Librarian("user1", "pass1", "librarian");
        Librarian librarian2 = new Librarian("user1", "pass1", "librarian");

        assertEquals(librarian1, librarian2);
        assertEquals(librarian1.hashCode(), librarian2.hashCode());
    }

    @Test
    public void testNullHandlingInConstructor() {
        Librarian librarian = new Librarian(null, null, null);

        assertNull(librarian.getUsername());
        assertNull(librarian.getPassword());
        assertNull(librarian.getRole());
        assertEquals(0.0, librarian.getPerformance());
    }

}
