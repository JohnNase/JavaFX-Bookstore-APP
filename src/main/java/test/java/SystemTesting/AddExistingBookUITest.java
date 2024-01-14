package test.java.SystemTesting;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import com.example.bookstorepro.ActionsWithBooks.AddExistingBookGUI;
import com.example.bookstorepro.ActionsWithBooks.DeleteBookGUI;
import com.example.bookstorepro.Database.DB;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

public class AddExistingBookUITest extends ApplicationTest {

    private final FxRobot robot = new FxRobot();

    @BeforeAll
    public static void SetUp(){
        DB.getConnection();

        String bookName = "ExistingBookName";
        String author = "ExistingBookAuthor";
        String ISBN = "1234567890";
        String genre = "ExistingBookGenre";
        int quantity = 1;
        double buyPrice = 10.0;
        double sellPrice = 15.0;
        String supplier = "ExistingBookSupplier";
        LocalDate localDate = LocalDate.now();

        boolean result = AddBookGUI.addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, localDate, supplier);

        assertTrue(result);

    }
    @Override
    public void start(Stage stage) throws Exception {
        new AddExistingBookGUI().start(stage);
    }

    @Test
    public void testSubmitButton() {
        robot.clickOn("#bookNameField").write("ExistingBookName");
        robot.clickOn("Submit");

        // Verify that the fields are filled with the existing book's details
        assertThat(robot.lookup("#authorField").queryTextInputControl()).hasText("ExistingBookAuthor");
        assertThat(robot.lookup("#ISBNField").queryTextInputControl()).hasText("1234567890");
        assertThat(robot.lookup("#genreField").queryTextInputControl()).hasText("ExistingBookGenre");
        assertThat(robot.lookup("#supplierField").queryTextInputControl()).hasText("ExistingBookSupplier");

        // Verify that the quantity, buy price, and sell price fields are empty
        assertThat(robot.lookup("#quantityField").queryTextInputControl()).hasText("");
        assertThat(robot.lookup("#buyPriceField").queryTextInputControl()).hasText("");
        assertThat(robot.lookup("#sellPriceField").queryTextInputControl()).hasText("");
    }

    @Test
    public void testAddBookButton() {
        // Submit the book name to fill the existing book's details
        robot.clickOn("#bookNameField").write("ExistingBookName");
        robot.clickOn("#buttonSubmitName");

        // Enter the quantity, buy price, and sell price
        robot.clickOn("#quantityField").write("1");
        robot.clickOn("#buyPriceField").write("10.0");
        robot.clickOn("#sellPriceField").write("22.0");

        // Select the current date
        robot.clickOn("#datePicker");
        robot.type(KeyCode.ENTER); // Select the current date

        // Click on the "Add Book" button
        robot.clickOn("#addButton");

        // Verify that the book was added successfully
        assertEquals("Existing book added successfully.", AddExistingBookGUI.label);
    }

    @AfterAll
    public static void finish() throws SQLException {
        Connection con = DB.getConnection();

        String sql = "delete from booklist where bookname = 'ExistingBookName'";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        if (preparedStatement.executeUpdate() == 1) {
            System.out.println("Existing book deleted successfully.");
        }
        else {
            System.out.println("Existing book was not deleted.");
        }

    }
}
