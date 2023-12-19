import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
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

    @Test
    public void testAddBook() throws SQLException {
        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Test Genre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();

        // Mock DataSource and Connection
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier, mockDataSource);

        assertTrue(result);
        verify(mockStatement).setString(1, bookName);
        verify(mockStatement).setString(2, author);
        verify(mockStatement).setString(3, ISBN);
        verify(mockStatement).setString(4, genre);
        verify(mockStatement).setInt(5, quantity);
        verify(mockStatement).setDouble(6, buyPrice);
        verify(mockStatement).setDouble(7, sellPrice);
        verify(mockStatement).setString(8, supplier);
        verify(mockStatement).setDate(9, java.sql.Date.valueOf(localDate));


        verify(mockStatement).executeUpdate();
    }

    @Test
    public void testAddBookThrowsException() throws Exception {

        String bookName = "Test Book";
        String author = "Test Author";
        String ISBN = "1234567890";
        String genre = "Test Genre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "Test Supplier";
        LocalDate localDate = LocalDate.now();

        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenThrow(SQLException.class);

        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier, mockDataSource);

        assertFalse(result);
    }
}
