package com.example.bookstorepro.ActionsWithBooks;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class AddBookGUI extends Application {
    static Connection con;
    private static TextField bookNameField;
    private static TextField authorField;
    private static TextField ISBNField;
    private static TextField genreField;
    private static TextField quantityField;
    private static TextField buyPriceField;
    private static TextField sellPriceField;
    private static TextField supplierField;
    public static DatePicker datePicker;
    static GridPane grid = new GridPane();
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        System.out.println("AddBookGUI");
            Scene scene = new Scene(grid, 500, 500);
            primaryStage.setScene(scene);
            AddBookInterface(grid);
            primaryStage.show();
        }

        public static void AddBookInterface(GridPane grid) throws FileNotFoundException {
            Label title = new Label("Add new Book in Inventory");
            Font font = Font.loadFont(new FileInputStream("lib/Astrella.ttf"), 25);
            title.setFont(font);
            title.setStyle("-fx-color:#0000FF;");
            title.setTextAlignment(TextAlignment.CENTER);
            grid.add(title,0,0);
            grid.setStyle("-fx-background-color: #F2E9E4; ");

            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Label bookNameLabel = new Label("Book Name:");
            grid.add(bookNameLabel, 0, 2);

            bookNameField = new TextField();
            grid.add(bookNameField, 1, 2);

            Label authorLabel = new Label("Author:");
            grid.add(authorLabel, 0, 3);

            authorField = new TextField();
            grid.add(authorField, 1, 3);

            Label ISBNLabel = new Label("ISBN:");
            grid.add(ISBNLabel, 0, 4);

            ISBNField = new TextField();
            grid.add(ISBNField, 1, 4);

            Label genreLabel = new Label("Genre:");
            grid.add(genreLabel, 0, 5);

            genreField = new TextField();
            grid.add(genreField, 1, 5);

            Label quantityLabel = new Label("Quantity:");
            grid.add(quantityLabel, 0, 6);

            quantityField = new TextField();
            grid.add(quantityField, 1, 6);

            Label buyPriceLabel = new Label("Buy Price:");
            grid.add(buyPriceLabel, 0, 7);

            buyPriceField = new TextField();
            grid.add(buyPriceField, 1, 7);

            Label sellPriceLabel = new Label("Sell Price:");
            grid.add(sellPriceLabel, 0, 8);

            sellPriceField = new TextField();
            grid.add(sellPriceField, 1, 8);

            Label supplierLabel = new Label("Supplier:");
            grid.add(supplierLabel, 0, 9);

            supplierField = new TextField();
            grid.add(supplierField, 1, 9);

            datePicker = new DatePicker();
            grid.add(datePicker, 1, 10);

            Button addButton = new Button("Add Book");
            addButton.setOnAction(e -> {
                addBook(bookNameField.getText(),
                        authorField.getText(),
                        ISBNField.getText(),
                        genreField.getText(),
                        Integer.parseInt(quantityField.getText()),
                        Double.parseDouble(buyPriceField.getText()),
                        Double.parseDouble(sellPriceField.getText()),
                        datePicker.getValue(),
                        supplierField.getText());

            });

            grid.add(addButton, 1, 11);
            addButton.setStyle("-fx-color: #C9ADA7");
        }

    public static boolean addBook(String bookName, String author, String ISBN, String genre, int quantity, double buyPrice, double sellPrice, LocalDate localDate, String supplier) {
        if(bookName.isEmpty() || author.isEmpty() || ISBN.isEmpty() || genre.isEmpty() || quantity == 0 || buyPrice == 0 || sellPrice == 0 || supplier.isEmpty()
        || bookName.isBlank()|| author.isBlank() || ISBN.isBlank() || genre.isBlank() || supplier.isBlank()){
            System.out.println("Please fill all the fields");
            return false;
        }
        if(localDate == null){
            addBook(bookName, author, ISBN, genre, quantity, buyPrice, sellPrice, LocalDate.now(), supplier);
            return true;
        }
        if(bookName.matches("[0-9]+") || author.matches("[0-9]+") || genre.matches("[0-9]+") || supplier.matches("[0-9]+")){
            System.out.println("Field cannot contain only numbers");
            return false;
        }
        if(bookName.length() > 50|| genre.length() > 50 || supplier.length() > 50 || author.length() > 50){
            System.out.println("Field cannot be longer than 50 characters");
            return false;
        }
        if(buyPrice > sellPrice){
            System.out.println("Buy price cannot be greater than sell price");
            return false;
        }
        if(quantity < 0){
            System.out.println("Quantity cannot be negative");
            return false;
        }
        if(buyPrice < 0 || sellPrice < 0){
            System.out.println("Price cannot be negative");
            return false;
        }
        if(ISBN.length() != 10 && ISBN.length() != 13){
            System.out.println("ISBN must be 10 or 13 digits");
            return false;
        }
        if(!ISBN.matches("[0-9]+")){
            System.out.println("ISBN must contain only digits");
            return false;
        }
        try (Connection con = getConnection()) {

            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date =  java.util.Date.from(instant);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            PreparedStatement preparedStatement = con.prepareStatement("insert into booklist (bookname,Author,ISBN,genre,quantity,buyPrice, sellPrice,supplier,datePurchases)" + " values(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, ISBN);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setDouble(6, buyPrice);
            preparedStatement.setDouble(7, sellPrice);
            preparedStatement.setString(8, supplier);
            preparedStatement.setDate(9,  sqlDate);
            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Book added successfully");
                return true;
            }
            else{
                System.out.println("Book not added");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection() {
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/booklist";
                String username = "root";
                String password = "sarasara1";

                // Register the MySQL JDBC driver (for MySQL 8.x)
                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return con;
        }



    public static void main(String[] args) {
        launch(args);
    }
}

