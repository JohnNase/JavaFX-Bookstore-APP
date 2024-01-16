package test.java.SystemTesting;

import com.example.bookstorepro.ActionsWithBooks.AddBookGUI;
import com.example.bookstorepro.ActionsWithBooks.AddExistingBookGUI;
import com.example.bookstorepro.ActionsWithBooks.DeleteBookGUI;
import com.example.bookstorepro.Bill.BillGenerator;
import com.example.bookstorepro.LogIn;
import com.example.bookstorepro.Transaction;
import com.example.bookstorepro.User;
import com.example.bookstorepro.UserController;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

import static com.example.bookstorepro.AdministratorFiles.AdministratorGUI.userTable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.util.WaitForAsyncUtils.waitFor;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;
import static test.java.SystemTesting.BillGeneratorUITest.insertTransaction;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class System1Test extends ApplicationTest {
    private static UserController userController;
    private static final String[] ADJECTIVES = {"Beautiful", "Mysterious", "Enchanting", "Captivating", "Whimsical"};
    private static final String[] NOUNS = {"Adventure", "Journey", "Dream", "Secret", "Quest"};


    @BeforeEach
    public void setup() throws Exception {

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogIn.class);
        userController = new UserController();
        signUpTestUser("Galileo", "Galilei", "g@example.com", "ggalilei", "Manager", "password", "password");
        signUpTestUser("Isaac", "Newton", "isaac@gmail.com", "inewton", "manager", "password", "password");
        signUpTestUser("Albert", "Einstein", "albert@gmail.com", "aeinstein", "administrator", "password", "password");
        signUpTestUser("Nikola", "Tesla", "ntesla@gmail.com", "ntesla", "librarian", "password", "password");
        signUpTestUser("Marie", "Curie", "marie@example.com", "mcurie", "manager", "password", "password");
        signUpTestUser("Charles", "Darwin", "chdarwin@gmail.com", "cdarwin", "administrator", "password", "password");
        signUpTestUser("Stephen", "Hawking", "shawking@hotmail.com", "shawking", "librarian", "password", "password");
        signUpTestUser("Ada", "Lovelace", "ada@hotmail.com", "alovelace", "manager", "password", "password");
        userController.saveData();
    }

    private static void signUpTestUser(String firstName, String lastName, String email, String username, String role, String password, String verifyPassword) {
        userController = new UserController();
        userController.signUp(firstName, lastName, email, username, role, password, verifyPassword);
    }


    @Override
    public void start(Stage stage) throws Exception {
        // The start method is called by FxToolkit.setupApplication
    }

    @Test
    public void testPathLibrarian() {
        signUpTestUser( "Robert", "Williams", "Robert@gmail.com", "rwilliams", "librarian", "password", "password");
        userController.saveData();

        //LOGIN FIRST
        clickOn("#usernameField").write("rwilliams");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(5000); //  a small delay to allow the GUI to update
        verifyThat("#librariansPane", NodeMatchers.isVisible());

        //LOOK AT INVENTORY OF BOOKS
        clickOn("#InventoryButton");
        sleep(5000);

        //GENERATE BILL
        clickOn("#BillButton");
        sleep(5000);
        clickOn("#tfBook").write("Me Before You");
        clickOn("#tfQuantity").write("7");
        //        13 * 7 = 91, so the total amount should be 91
        sleep(3000);
        // Click on Submit button
        clickOn("#Submit");
        sleep(3000);
        String expected = "91.0";

        assertEquals(expected, lookup("#lblTotalPrice").queryAs(Label.class).getText());
        // Click on the button that triggers the file save
        clickOn("#saveButton");
        sleep(3000);
        int billNumber = BillGenerator.getBillNumber() -1 ; // -1 because the bill number is incremented after the bill is generated
        Transaction transaction = new Transaction(LogIn.getUsername(), LocalDate.now(), 7, 91);
        insertTransaction(transaction);

        // Check that the file exists in the directory
        Path filePath = Paths.get("Generated_Bills/Bill"+billNumber+".txt");
        System.out.println(filePath);
        assertTrue(Files.exists(filePath));

    }

    @Test
    public void testPathManager() {
        signUpTestUser("Susan", "Miller", "susanmiller@gmail.com", "susanmiller", "manager", "password", "password");
        userController.saveData();

        //LOGIN FIRST

        clickOn("#usernameField").write("susanmiller");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(5000);
        verifyThat("#managersPane", NodeMatchers.isVisible());

        //CHECK LIST OF BOOKS
        clickOn("#InventoryButton");
        sleep(5000);

        //ADD A NEW BOOK
        clickOn("#AddBookButton");
        clickOn("#bookNameField").write(generateRandomBookName());
        clickOn("#authorField").write("Patricia Larking");
        clickOn("#ISBNField").write(generateISBN13());
        clickOn("#genreField").write("Romance");
        clickOn("#quantityField").write("192");
        clickOn("#buyPriceField").write("10.0");
        clickOn("#sellPriceField").write("15.0");
        type(KeyCode.DOWN); // Navigate to the desired date
        type(KeyCode.ENTER); // Select the desired date
        clickOn("#supplierField").write("Bookland Inc.");
        // Perform the action
        clickOn("#addButton");
        sleep(9000);

        //ADD AN EXISTING BOOK
        clickOn("#AddExistingBookButton");
        clickOn("#bookNameField").write("The Dark Dragon");
        clickOn("Submit");
        // Verify that the fields are filled with the existing book's details
        assertThat(lookup("#authorField").queryTextInputControl()).hasText("Cameron Woodard");
        assertThat(lookup("#ISBNField").queryTextInputControl()).hasText("9781234567897");
        assertThat(lookup("#genreField").queryTextInputControl()).hasText("Fantasy");
        assertThat(lookup("#supplierField").queryTextInputControl()).hasText("Adrion Ltd");

        // Verify that the quantity, buy price, and sell price fields are empty
        assertThat(lookup("#quantityField").queryTextInputControl()).hasText("");
        assertThat(lookup("#buyPriceField").queryTextInputControl()).hasText("");
        assertThat(lookup("#sellPriceField").queryTextInputControl()).hasText("");

        // Enter the quantity, buy price, and sell price
        clickOn("#quantityField").write("143");
        clickOn("#buyPriceField").write("10.0");
        clickOn("#sellPriceField").write("22.0");

        // Select the current date
       clickOn("#datePicker");
        type(KeyCode.ENTER); // Select the current date

        // Click on the "Add Book" button
        clickOn("#addButton");

        // Verify that the book was added successfully
        assertEquals("Existing book added successfully.", AddExistingBookGUI.label);

        //CHECK PERFORMANCE
        clickOn("#CheckPerformanceButton");
        sleep(9000);
        clickOn("#btnTransactions");
        sleep(1000);
        clickOn("#txtUsername").write("SaraBerberi");
        clickOn("#dpStartDate").write("11/1/2023");
        clickOn("#dpEndDate").write("11/1/2024");
        clickOn("#btSubmit");
        sleep(9000);
        clickOn("#btnOtherUser");
        clickOn("#txtUsername").write("JohnNase");
        clickOn("#dpStartDate").write("11/1/2023");
        clickOn("#dpEndDate").write("11/1/2024");
        clickOn("#btSubmit");
        sleep(2000);
        clickOn("#btnChart");
        sleep(9000);
    }

    public static String generateISBN13() {
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            isbn.append(new Random().nextInt(10));
        }

        int checkDigit = 10 - (isbn.chars().map(i -> i - '0').map(i -> (i * ((i + 1) % 2 * 2) % 9)).sum() % 10);
        isbn.append(checkDigit % 10);

        return isbn.toString();
    }

    public static String generateRandomBookName() {
        String adjective = ADJECTIVES[new Random().nextInt(ADJECTIVES.length)];
        String noun = NOUNS[new Random().nextInt(NOUNS.length)];

        return adjective + " " + noun;
    }


    @Test
    public void testPathAdmin() {
        signUpTestUser("James", "Wilson", "wilson324@gmail.com", "jwilson22", "administrator", "password", "password");
        userController.saveData();

        clickOn("#usernameField").write("jwilson22");
        clickOn("#passwordField").write("password");
        clickOn("#loginButton");
        sleep(3000);
        verifyThat("#administratorsPane", NodeMatchers.isVisible());

        //CHECK LIST OF BOOKS
        clickOn("#InventoryButton");
        sleep(3000);

        //ADD A NEW BOOK
        clickOn("#AddBookButton");
        clickOn("#bookNameField").write(generateRandomBookName());
        clickOn("#authorField").write("Patricia Larking");
        clickOn("#ISBNField").write(generateISBN13());
        clickOn("#genreField").write("Romance");
        clickOn("#quantityField").write("192");
        clickOn("#buyPriceField").write("10.0");
        clickOn("#sellPriceField").write("15.0");
        type(KeyCode.DOWN); // Navigate to the desired date
        type(KeyCode.ENTER); // Select the desired date
        clickOn("#supplierField").write("Bookland Inc.");
        // Perform the action
        clickOn("#addButton");
        sleep(1000);

        //ADD AN EXISTING BOOK
        clickOn("#AddExistingBookButton");
        clickOn("#bookNameField").write("The Dark Dragon");
        clickOn("Submit");
        assertThat(lookup("#authorField").queryTextInputControl()).hasText("Cameron Woodard");
        assertThat(lookup("#ISBNField").queryTextInputControl()).hasText("9781234567897");
        assertThat(lookup("#genreField").queryTextInputControl()).hasText("Fantasy");
        assertThat(lookup("#supplierField").queryTextInputControl()).hasText("Adrion Ltd");
        assertThat(lookup("#quantityField").queryTextInputControl()).hasText("");
        assertThat(lookup("#buyPriceField").queryTextInputControl()).hasText("");
        assertThat(lookup("#sellPriceField").queryTextInputControl()).hasText("");
        // Enter the quantity, buy price, and sell price
        clickOn("#quantityField").write("456");
        clickOn("#buyPriceField").write("10.0");
        clickOn("#sellPriceField").write("22.0");
        // Select the current date
        clickOn("#datePicker");
        type(KeyCode.ENTER); // Select the current date
        // Click on the "Add Book" button
        clickOn("#addButton");
        // Verify that the book was added successfully
        assertEquals("Existing book added successfully.", AddExistingBookGUI.label);

        //CHECK PERFORMANCE
        clickOn("#CheckPerformanceButton");
        sleep(1000);
        clickOn("#btnTransactions");
        sleep(1000);
        clickOn("#txtUsername").write("SaraBerberi");
        clickOn("#dpStartDate").write("11/1/2023");
        clickOn("#dpEndDate").write("11/1/2024");
        clickOn("#btSubmit");
        sleep(1000);
        clickOn("#btnOtherUser");
        clickOn("#txtUsername").write("JohnNase");
        clickOn("#dpStartDate").write("11/1/2023");
        clickOn("#dpEndDate").write("11/1/2024");
        clickOn("#btSubmit");
        sleep(1000);
        clickOn("#btnChart");
        sleep(1000);

        //DELETE BOOK
        clickOn("#DeleteBookButton");
        AddBookGUI.addBook("TestBook", "TestAuthor", "1234567890", "TestGenre", 1, 10.0, 15.0, null, "TestSupplier");
        clickOn("#bookNameField").write("TestBook");
        sleep(1000); // Pause for 1 second
        clickOn("#ISBNField").write("1234567890");
        sleep(1000); // Pause for 1 second
        // Click on the "Delete Book" button
        clickOn("#addButton");
        sleep(1000);
        // Verify that the book was deleted successfully
        assertEquals(":( Book deleted successfully", DeleteBookGUI.label);
        sleep(1000);

        //MANAGE USERS - CHANGE ROLE
        clickOn("#ManageUsersButton");
        lookup("#administratorsPane").query();
        TableView.TableViewSelectionModel selectionModel = userTable.getSelectionModel();
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        interact(() -> {
            userTable.getSelectionModel().selectFirst();
        });
        sleep(1000);
        clickOn("#changeRoleButton");
        sleep(1000);
        waitForFxEvents();
        clickOn("#okButton");
        sleep(1000);
        System.out.println(selectedUser);
        type(KeyCode.ESCAPE);
        sleep(1000);

        //MANAGE USERS - DELETE USER
        clickOn("#ManageUsersButton");
        interact(() -> userTable.getSelectionModel().selectFirst());
        sleep(500);
        clickOn("#deleteUserButton");
        sleep(1000);
        waitForFxEvents();
        sleep(1000);
        type(KeyCode.ESCAPE);
        sleep(6000);

    }


}



