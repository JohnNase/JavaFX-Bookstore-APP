package test.java.IntegrationTests;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import com.example.bookstorepro.ActionsWithBooks.DeleteBookGUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteBookTest {

    @BeforeEach
    public void setUp() {
        AddBookGUI.addBook("test", "test", "1234567890", "test", 190, 1.0, 18.0, LocalDate.now(), "test");
    }

    @Test
    public void testDeleteBookSuccess() {
        assertTrue(DeleteBookGUI.deleteBook("test", "1234567890"));
    }

    @Test
    public void testDeleteBookFailure() {
        assertFalse(DeleteBookGUI.deleteBook("NonExistingBook", "0000000000"));
    }

    @Test
    public void testDeleteBookWithEmptyInputs() {
        assertFalse(DeleteBookGUI.deleteBook("", ""));
    }
}
