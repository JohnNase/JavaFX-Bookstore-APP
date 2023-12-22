package IntegrationTests;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import com.example.bookstorepro.Database.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddBookTest {

    @BeforeAll
    public static void SetUpDatabase(){
        DB.getConnection();
    }
    @Test
    public void testAddBookValid() {
        String bookName = "Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Test Genre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();

        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);

        assertTrue(result);

    }
    @Test
    public void testAddBook_EmptyBookName() {
        String bookName = "";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Test Genre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_EmptyAuthor() {
        String bookName = "Test Book";
        String author = "";
        String ISBN = "1234567890";
        String genre = "Test Genre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }
    @Test
    public void testAddBook_EmptyGenre() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }
    @Test
    public void testAddBook_LocalDateNull() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = null;
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertTrue(result);
    }

    @Test
    public void testAddBook_nameNumbers() {
        String bookName = "123456";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_authorNumbers() {
        String bookName = "Test Book";
        String author = "12345678";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }
    @Test
    public void testAddBook_genreNumbers() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_SupplierNumbers() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "987654321";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_VeryLongBook() {
        String bookName = "Aequeosalinocalcalinoceraceoaluminosocupreovitriolic";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_VeryLongAuthor() {
        String bookName = "Test Book";
        String author = "Aequeosalinocalcalinoceraceoaluminosocupreovitriolic";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_VeryLongSupplier() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Aequeosalinocalcalinoceraceoaluminosocupreovitriolic";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_VeryLongGenre() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Aequeosalinocalcalinoceraceoaluminosocupreovitriolic";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_InvalidPrice() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 100.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_NegativeBuyPrice() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = -10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_NegativeSellPrice() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = -15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }

    @Test
    public void testAddBook_NegativeQuantity() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Fantasy";
        int quantity = -5;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }
    @Test
    public void testAddBook_ISBNLengthNot13or10() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "12345678901";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }
    @Test
    public void testAddBook_ISBNLetters() {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "invalidddd";
        String genre = "Fantasy";
        int quantity = 1;
        double buyPrice = 3.0;
        double sellPrice = 15.0;
        String supplier = "Adrion";
        LocalDate localDate = LocalDate.now();
        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);
        assertFalse(result);
    }









}
