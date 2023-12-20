package com.example.bookstorepro.LibrarianFiles;

import com.example.bookstorepro.Bill.BillGenerator;
import com.example.bookstorepro.Database.DB;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

import static javafx.geometry.Pos.CENTER;

public class LibrariansGUI extends Application {

    ArrayList<String> book = new ArrayList<>();
    public static int bookNo = 0;
    ArrayList<String> ISBN = new ArrayList<>();
    public static ObservableList<ObservableList> data = FXCollections.observableArrayList();
    private static TableView tableview;
    BorderPane librariansPane = new BorderPane();

    public static void main(String[] args) {
        launch(args);
    }

    public LibrariansGUI() {
        tableview = new TableView<>();
    }

    public static TableView buildData() {
        Connection c = null;
        //TABLE VIEW AND DATA

        try {
            c = DB.getConnection();
            //SQL FOR SELECTING THE WHOLE TABLE FROM BOOKLIST
            String useDatabaseSQL = "USE booklist";
            c.createStatement().execute(useDatabaseSQL);

            String SQL = "SELECT * from booklist";
            //ResultSet
            ResultSet rs = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL);

            //DYNAMICALLY ADDED TABLE COLUMN
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (SQLException e) {
            System.out.println("Error building data: " + e.getMessage());
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }
        return tableview;  }

    @Override
    public void start(Stage stage) {
        try {
            configureStage(stage);
            createLayout(stage);
            handleLowQuantityAlert();
        } catch (Exception e) {
            handleException(e);
        }
    }
    private void configureStage(Stage stage) {
        stage.setTitle("Librarian Dashboard");
        stage.setResizable(false);
    }

    private void createLayout(Stage stage) throws FileNotFoundException {
        librariansPane.setStyle("-fx-background-color: #FFFAE2; ");

        Label hello = new Label("Hello Librarian!");
        hello.setFont(Font.loadFont(new FileInputStream("lib/Astrella.ttf"), 40));
        hello.setTextFill(Color.WHITE);
        hello.setTextAlignment(TextAlignment.CENTER);
        hello.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0) ;");

        HBox HelloUser = new HBox();
        HelloUser.getChildren().add(hello);
        HelloUser.setPadding(new Insets(20, 20, 20, 20));
        hello.setAlignment(CENTER);
        HelloUser.setStyle("-fx-background-color: #92977E; ");

        VBox leftSide = new VBox(50);
        leftSide.setPadding(new Insets(0, 12, 12, 12));
        leftSide.setStyle("-fx-background-color: #EADDA6; ");

        Button InventoryButton = new Button("Books Table");
        Button BillButton = new Button("Generate Bill");

        InventoryButton.setStyle("-fx-color: #FFFAE2; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0) ;");
        InventoryButton.setTranslateY(50);
        BillButton.setStyle("-fx-color: #FFFAE2;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0) ; ");
        BillButton.setTranslateY(100);
        leftSide.getChildren().addAll(InventoryButton, BillButton);

        librariansPane.setTop(HelloUser);
        librariansPane.setLeft(leftSide);

        InventoryButton.setOnAction(e -> {
            tableview.getColumns().clear();
            data.clear();
            tableview = buildData();
            tableview.refresh();
            librariansPane.setCenter(tableview);
        });

        BillButton.setOnAction(e -> {
            BillGenerator bill = new BillGenerator();
            Stage testStage = new Stage();
            try {
                bill.start(testStage);
            } catch (FileNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Scene scene = new Scene(librariansPane, 1000, 600);
        tableview.setEditable(true);

        stage.setScene(scene);
        stage.show();
    }


    private void handleLowQuantityAlert() {
        try {
            fetchLowQuantityBooks();
            createLowQuantityAlert();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void fetchLowQuantityBooks() {
        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM booklist WHERE quantity=0")) {

            while (resultSet.next()) {
                ISBN.add(resultSet.getString(3));
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void createLowQuantityAlert() {
        try {
            getContent();
            displayAlert();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void getContent() {
        try (Connection con = DB.getConnection()) {
            for (String isbn : ISBN) {
                fetchBookDetails(isbn, con);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void fetchBookDetails(String isbn, Connection con) throws SQLException {
        String queryString = "SELECT * FROM booklist WHERE ISBN=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(queryString)) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    book.add((bookNo + 1) + ".\t" + resultSet.getString(1) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(8));
                    bookNo++;
                }
            }
        }
    }

    private void displayAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Low Quantity Alert");
        alert.setHeaderText("The following books are out of stock:");
        //show the book arraylist without the brackets and commas
        alert.setContentText(book.toString().replace("[", "").replace("]", ""));
        if (!ISBN.isEmpty()) {
            alert.showAndWait();
        }
    }

    private void handleException(Exception e) {
        e.printStackTrace();
        //show the error in the window
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
