package test.java.SystemTesting;

import com.example.bookstorepro.Bill.BillGenerator;
import com.example.bookstorepro.Database.DB;
import com.example.bookstorepro.LogIn;
import com.example.bookstorepro.SignUp;
import com.example.bookstorepro.Transaction;
import com.mysql.cj.log.Log;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillGeneratorUITest extends ApplicationTest {
    FxRobot robot = new FxRobot();

    @Override
    public void start(Stage primaryStage) throws Exception {
        new BillGenerator().start(primaryStage);
    }

    @BeforeEach
    void setUp() {
        SignUp signUp = new SignUp();
        boolean result =  signUp.newUserController.signUp("Testing", "Test", "testtestest@example.com", "testFXRobot", "Manager", "password", "password");
        System.out.println("Sign Up:"+result); // true if the sign up was successful
        LogIn.setUsername("testFXRobot");
    }

        @Test
        public void testBuyOneBookSaveBill() {
            robot.clickOn("#tfBook").write("Me Before You");
            robot.clickOn("#tfQuantity").write("7");
    //        13 * 7 = 91, so the total amount should be 91

            robot.sleep(3000);

            // Click on Submit button
            robot.clickOn("#Submit");
            robot.sleep(3000);

            String expected = "91.0";


            assertEquals(expected, robot.lookup("#lblTotalPrice").queryAs(Label.class).getText());
            // Click on the button that triggers the file save
            robot.clickOn("#saveButton");
            robot.sleep(3000);
            int billNumber = BillGenerator.getBillNumber() -1 ; // -1 because the bill number is incremented after the bill is generated
            Transaction transaction = new Transaction(LogIn.getUsername(), LocalDate.now(), 7, 91);
            insertTransaction(transaction);

            // Check that the file exists in the directory
            Path filePath = Paths.get("Generated_Bills/Bill"+billNumber+".txt");
            System.out.println(filePath);
            assertTrue(Files.exists(filePath));

        }

//
//    @Test
//    public void testBuyMultipleBooksSaveBill() {
//        robot.clickOn("#tfBook").write("Me Before You");
//        robot.clickOn("#tfQuantity").write("1");
//        //        1 * 7 = 7, so the initial amount should be 7
//        robot.sleep(2000);
//
//        robot.clickOn("#btnNewBook");
//        robot.clickOn("#tfBook").write("Earth To Unknown");
//        robot.clickOn("#tfQuantity").write("14");
//        //        14 * 7 = 98, so the total amount should be 105
//
//        robot.sleep(2000);
//
//        robot.clickOn("#btnNewBook");
//        robot.clickOn("#tfBook").write("Dead Man’s Wish");
//        robot.clickOn("#tfQuantity").write("6");
//        //        6 * 4 = 24, so the total amount should be 129
//
//        // Click on Submit button
//        robot.clickOn("#Submit");
//        robot.sleep(3000);
//
//        String expected = "129.0";
//
//
//        assertEquals(expected, robot.lookup("#lblTotalPrice").queryAs(Label.class).getText());
//        // Click on the button that triggers the file save
//        robot.clickOn("#saveButton");
//        robot.sleep(3000);
//        int billNumber = BillGenerator.getBillNumber() -1 ; // -1 because the bill number is incremented after the bill is generated
//        Transaction transaction = new Transaction(LogIn.getUsername(), LocalDate.now(), 7, 91);
//        insertTransaction(transaction);
//
//        // Check that the file exists in the directory
//        Path filePath = Paths.get("Generated_Bills/Bill"+billNumber+".txt");
//        System.out.println(filePath);
//        assertTrue(Files.exists(filePath));
//    }
    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public static void insertTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transactions (librarianName,quantity,price,dateoftransaction) VALUES (?, ?, ?,?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, LogIn.getUsername());
            preparedStatement.setInt(2,transaction.getQuantity());
            preparedStatement.setDouble(3,transaction.getPrice());
            preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}