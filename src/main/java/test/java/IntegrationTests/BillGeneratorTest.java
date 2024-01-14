package test.java.IntegrationTests;

import com.example.bookstorepro.ActionsWithBooks.Book;
import com.example.bookstorepro.Bill.BillGenerator;
import com.example.bookstorepro.Database.DB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testfx.framework.junit5.ApplicationTest; // Assuming TestFX for JavaFX UI testing

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillGeneratorTest extends ApplicationTest {

    @Test
    public void testCalculateTotal() {

        Book mockBook = new Book();
        Book mockBook1 = new Book();
        mockBook.setSellPrice(9);
        mockBook.setQuantity(5);
        mockBook1.setSellPrice(10);
        mockBook1.setQuantity(4);
        BillGenerator.books.add(mockBook);
        BillGenerator.books.add(mockBook1);

        double total = BillGenerator.calculateTotal();

        assertEquals(85,total);
    }


}


