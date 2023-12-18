package com.example.bookstorepro.LibrarianFiles;
import com.example.bookstorepro.Database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Librarian {
    private String username, password, role;
    private double performance = 0;

    //constructors
    public Librarian(){
    }
    public Librarian(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //getters
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public double getPerformance(){
        return performance;
    }

    //setters
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setPerformance(double performance){
        this.performance = performance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian)) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(username, librarian.username) &&
                Objects.equals(password, librarian.password) &&
                Objects.equals(role, librarian.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }



}




