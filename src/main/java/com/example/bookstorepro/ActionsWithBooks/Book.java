package com.example.bookstorepro.ActionsWithBooks;
public class Book {
    private String bookName;
    private String ISBN;
    private String genre;
    private String supplier;
    private int quantity = 0;
    private double sellPrice ;
    public Book(){
    }

    //getters
    public String getName() {
        return bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    //setters
    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }


}



