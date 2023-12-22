package UnitTests;

import com.example.bookstorepro.ActionsWithBooks.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
    }

    @Test
    public void testSetName() {
        book.setName("Book1");
        assertEquals("Book1", book.getName());
    }

    @Test
    public void testSetQuantity() {
        book.setQuantity(10);
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void testSetSellPrice() {
        book.setSellPrice(29.99);
        assertEquals(29.99, book.getSellPrice());
    }

    @Test
    public void testDefaultValues() {
        assertEquals(null, book.getName());
        assertEquals(0, book.getQuantity());
        assertEquals(0.0, book.getSellPrice());
    }

    @Test
    public void testSetNameEmptyString() {
        book.setName("");
        assertEquals("", book.getName());
    }

    @Test
    public void testSetNegativeQuantity() {
        book.setQuantity(-5);
        assertEquals(-5, book.getQuantity());
    }

    @Test
    public void testSetNegativeSellPrice() {
        book.setSellPrice(-15.5);
        assertEquals(-15.5, book.getSellPrice());
    }
}
