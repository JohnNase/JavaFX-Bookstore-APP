module com.example.bookstorepro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires testfx.core;
    requires testfx.junit5;
    requires javafx.graphics;
    requires mysql.connector.j;
    opens com.example.bookstorepro to javafx.fxml,testfx.core;

    exports com.example.bookstorepro;
    exports com.example.bookstorepro.LibrarianFiles;
    exports com.example.bookstorepro.ManagerFiles;
    exports com.example.bookstorepro.Bill;
    exports com.example.bookstorepro.Database;
    exports com.example.bookstorepro.ActionsWithBooks;
    exports com.example.bookstorepro.AdministratorFiles;
}


