module com.example.bookstorepro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;
    opens com.example.bookstorepro to javafx.fxml;
    exports com.example.bookstorepro;
}