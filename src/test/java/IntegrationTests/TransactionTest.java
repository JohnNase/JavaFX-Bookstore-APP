package IntegrationTests;

import com.example.bookstorepro.Transaction;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {
    @Test
    public void testTransaction() {
        String librarianName = "Test Librarian";
        LocalDate transactionDate = LocalDate.now();
        int quantity = 5;
        double price = 20.0;
        Transaction transaction = new Transaction(librarianName, transactionDate, quantity, price);

        assertEquals(librarianName, Transaction.getLibrarianName());
        assertEquals(Date.valueOf(transactionDate), Transaction.getTransactionDate());
        assertEquals(quantity, Transaction.getQuantity());
        assertEquals(price, Transaction.getPrice(), 0.001);
    }

    @Test
    public void testSetQuantity() {
        int quantity = 10;
        Transaction.setQuantity(quantity);
        assertEquals(quantity, Transaction.getQuantity());
    }

    @Test
    public void testToString() {
        String librarianName = "Test Librarian";
        LocalDate transactionDate = LocalDate.now();
        int quantity = 5;
        double price = 20.0;
        Transaction transaction = new Transaction(librarianName, transactionDate, quantity, price);
        String expected = librarianName + " " + Date.valueOf(transactionDate) + " " + quantity + " " + price;
        assertEquals(expected, transaction.toString());
    }

    @Test
    public void testTransactionWithNullLibrarianName() {
        assertThrows(NullPointerException.class, () -> {
            new Transaction(null, LocalDate.now(), 5, 20.0);
        });
    }

    @Test
    public void testTransactionWithNullDate() {
        assertThrows(NullPointerException.class, () -> {
            new Transaction("Test Librarian", null, 5, 20.0);
        });
    }

    @Test
    public void testTransactionWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("Test Librarian", LocalDate.now(), -5, 20.0);
        });
    }

    @Test
    public void testTransactionWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("Test Librarian", LocalDate.now(), 5, -20.0);
        });
    }
}
