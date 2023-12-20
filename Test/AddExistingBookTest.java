import com.example.bookstorepro.ActionsWithBooks.AddExistingBookGUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.bookstorepro.ActionsWithBooks.AddExistingBookGUI.UpdateBook;
import static com.example.bookstorepro.ActionsWithBooks.AddExistingBookGUI.getContent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class AddExistingBookTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDatabaseUpdateSuccess() throws Exception {
        // Mocking database connection and execution
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Providing mocked connection
        AddExistingBookGUI.setConnectionProvider(() -> mockConnection);

        assertTrue(AddExistingBookGUI.UpdateBook(10, 20.0, 30.0, "Watch Out"));
    }

    @Test
    public void testDatabaseUpdateFailure() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        AddExistingBookGUI.setConnectionProvider(() -> mockConnection);

        assertFalse(AddExistingBookGUI.UpdateBook(0, 0, 0, ""));
    }


    @Test
    public void testAddExistingBook() {
        String bookName = "Monster Strike";
        ArrayList<String> RealContent;
        ArrayList<String> ExpectedContent = new ArrayList<>();
        ExpectedContent.add("Rihanna Rocha");
        ExpectedContent.add("9783122813413");
        ExpectedContent.add("Action");
        ExpectedContent.add("Adrion Ltd");
        RealContent = getContent(bookName);
        assertEquals(ExpectedContent, RealContent);
    }

    @Test
    public void testAddNonExistingBook() {
        String bookName = "Memexico";
        assertTrue(getContent(bookName).isEmpty());
    }

    @Test
    public void testAddValid(){
        String bookName = "Watch Out";
        assertTrue(UpdateBook(12,24,39,bookName));
    }


    @Test
    public void testAddBlankBook() {
        String bookName = "";
        assertFalse(UpdateBook(12,24,39,bookName));
    }

    @Test
    public void testAddNegativeQuantity() {
        String bookName = "Watch Out";
        assertFalse(UpdateBook(-12,24,39,bookName));
    }

    @Test
    public void testAddNegativeSellPrice() {
        String bookName = "Watch Out";
        assertFalse(UpdateBook(12,24,-39,bookName));
    }

    @Test
    public void testAddNegativeBuyPrice() {
        String bookName = "Watch Out";
        assertFalse(UpdateBook(12,-24,39,bookName));
    }
    @Test
    public void testAddInvalidPrice() {
        String bookName = "Watch Out";
        assertFalse(UpdateBook(12,39,24,bookName));
    }
    @Test
    public void testUpdateBookWithAllZeroValues() {
        assertFalse(UpdateBook(0, 0, 0, ""));
    }





}