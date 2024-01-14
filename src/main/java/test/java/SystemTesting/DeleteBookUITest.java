package test.java.SystemTesting;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import com.example.bookstorepro.ActionsWithBooks.DeleteBookGUI;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBookUITest extends ApplicationTest {

    private final FxRobot robot = new FxRobot();

    @BeforeAll
    public static void setUp() {
        // Add a book for the test
        AddBookGUI.addBook("TestBook", "TestAuthor", "1234567890", "TestGenre", 1, 10.0, 15.0, null, "TestSupplier");
    }

    @Override
    public void start(Stage stage) throws Exception {
        new DeleteBookGUI().start(stage);
    }

    @Test
    public void testDeleteBookButton() {
        robot.clickOn("#bookNameField").write("TestBook");
        robot.sleep(1000); // Pause for 1 second
        robot.clickOn("#ISBNField").write("1234567890");
        robot.sleep(1000); // Pause for 1 second

        // Click on the "Delete Book" button
        robot.clickOn("#addButton");
        robot.sleep(1000);

        // Verify that the book was deleted successfully
        assertEquals(":( Book deleted successfully", DeleteBookGUI.label);
        robot.sleep(4000);
    }
}