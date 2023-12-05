package com.example.bookstorepro;

import java.sql.Date;
import java.time.LocalDate;
public class Transaction {
    private static double price;
    private static LocalDate transactionDate;
    private static int quantity;
    private static String librarianName;
//    UPDATED: Added exception handling in constructor for negative quantity and price and non-null librarianName and transactionDate
    public Transaction(String librarianName, LocalDate transactionDate, int quantity, double price) {
        if(librarianName!=null && transactionDate!=null) {
            if(quantity > 0 && price > 0) {
                Transaction.librarianName = librarianName;
                Transaction.transactionDate = transactionDate;
                Transaction.quantity = quantity;
                Transaction.price = price;
            }
            else throw new IllegalArgumentException();
        }
            else throw new NullPointerException();
    }
    public static double getPrice() {
        return price;
    }
    public static Date getTransactionDate() {
        return Date.valueOf(transactionDate);
    }
    public static int getQuantity() {
        return quantity;
    }
    public static void setQuantity(int quantity) {
        Transaction.quantity = quantity;
    }
    public static String getLibrarianName() {
        return librarianName;
    }
    @Override
    public String toString() {
        return getLibrarianName() +" "+ getTransactionDate() +" "  + getQuantity() +" " + getPrice() ;
    }
}