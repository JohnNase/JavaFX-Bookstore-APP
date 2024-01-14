package test.java.SystemTesting;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBookUITest extends ApplicationTest {
    FxRobot robot = new FxRobot();

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        new AddBookGUI().start(stage);
    }

    @Test
    public void testAddBookValid() {
        // Set values in the UI elements
        robot.clickOn("#bookNameField").write("TestBook");
        robot.clickOn("#authorField").write("TestAuthor");
        robot.clickOn("#ISBNField").write("1234567890");
        robot.clickOn("#genreField").write("TestGenre");
        robot.clickOn("#quantityField").write("1");
        robot.clickOn("#buyPriceField").write("10.0");
        robot.clickOn("#sellPriceField").write("15.0");
        robot.type(KeyCode.DOWN); // Navigate to the desired date
        robot.type(KeyCode.ENTER); // Select the desired date
        robot.clickOn("#supplierField").write("TestSupplier");

        // Perform the action
        robot.clickOn("#addButton");

        // Assert the result (modify this according to your success criteria)
        assertTrue(true); // Add your assertion here
    }

    // Add more test methods for other scenarios

    public FxRobot sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

